/**
 *
 */
package by.academy.it.rentacar.dao.impl;

import by.academy.it.rentacar.dao.ICarDAO;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.viewobject.CarVO;
import by.academy.it.rentacar.viewobject.FilterVO;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Class CarDAO
 * <p>
 * Class CarDAO creates object Car and executes queries the table Cars.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Repository("carDAO")
public class CarDAO extends DAO<Car> implements ICarDAO {

    private static Logger log = Logger.getLogger(CarDAO.class);

    public CarDAO() {
        super();
    }

    @Override
    public String sqlQueryStringByFilter(FilterVO filterVO) {
        String sqlQueryFromWhere = "FROM cars AS CarVO " +
                "LEFT JOIN ratings AS R ON CarVO.ratings_id = R.id " +
                "LEFT JOIN types AS T ON CarVO.types_id = T.id " +
                "LEFT JOIN modelsandmarks AS MAndM ON CarVO.ModelsAndMarks_id = MAndM.id " +
                "LEFT JOIN fuels AS F ON CarVO.Fuels_id = F.id "
                + "WHERE NOT EXISTS (SELECT O.cars_id FROM orders AS O "
                + "WHERE NOT (O.fromdate >= '" + trasformToSQLDate(filterVO.getByDate()) + "' " +
                " OR O.bydate <= '" + trasformToSQLDate(filterVO.getFromDate()) + "') AND O.cars_id = CarVO.id)";
        String transmission = filterVO.getTransmission();
        if (!transmission.equals("")) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.transmission= '" + transmission + "'";
        }
        int fuelId = filterVO.getFuelId();
        if (fuelId != 0) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.fuels_id= " + fuelId;
        }
        int typeId = filterVO.getTypeId();
        if (typeId != 0) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.types_id = " + typeId;
        }
        int ratingId = filterVO.getRatingId();
        if (ratingId != 0) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.ratings_id = " + ratingId;
        }
        return sqlQueryFromWhere;
    }

    @Override
    public List<CarVO> searchCar(FilterVO filterVO, int page) {
        int recordsPerPage = filterVO.getRecordPerPage();
        long difference = filterVO.getByDate().getTime() - filterVO.getFromDate().getTime();
        int days = (int) (difference / (24 * 60 * 60 * 1000) + 1);
        // query text writing
        String sqlQuery = "SELECT CarVO.id AS id, " +
                "CarVO.registrationNumber AS registrationNumber, " +
                "CarVO.transmission AS transmission, " +
                "R.name AS rating, " +
                "T.name AS typeCar, " +
                "MAndM.model AS model," +
                "MAndM.mark AS marka," +
                "F.name AS fuel," +
                "CarVO.description AS description, " +
                "ROUND(CarVO.costofday * " + days + " * (100 - CarVO.discount*" + (days - 1) + ")/100, 2) AS cost "
                + sqlQueryStringByFilter(filterVO);
        sqlQuery = sqlQuery + " ORDER BY CarVO.transmission, F.name, T.name, R.name";
        log.debug("sqlQuery: " + sqlQuery);
        List<CarVO> list = null;
        list = getSession().createSQLQuery(sqlQuery).addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("registrationNumber", StandardBasicTypes.STRING)
                .addScalar("transmission", StandardBasicTypes.STRING)
                .addScalar("rating", StandardBasicTypes.STRING)
                .addScalar("typeCar", StandardBasicTypes.STRING)
                .addScalar("model", StandardBasicTypes.STRING)
                .addScalar("marka", StandardBasicTypes.STRING)
                .addScalar("fuel", StandardBasicTypes.STRING)
                .addScalar("description", StandardBasicTypes.STRING)
                .addScalar("cost", StandardBasicTypes.BIG_DECIMAL)
                .setResultTransformer(Transformers.aliasToBean(CarVO.class))
                .setFirstResult((page-1)*recordsPerPage)
                .setMaxResults(recordsPerPage).list();
        log.info("list: " + list);
        return list;
    }

    @Override
    public int countCarByFilter(FilterVO filterVO) {
        // query text writing
        String sqlQuery = "SELECT count(*) "
                + sqlQueryStringByFilter(filterVO);
        log.debug("sqlQuery: " + sqlQuery);
        Query query = getSession().createSQLQuery(sqlQuery);
        int countCar = 0;
        List results = query.list();
        if (!results.isEmpty()) {
            BigInteger count = (BigInteger) results.get(0);
            countCar = count.intValue();
        }
        log.info("Count of the cars b the filters: " + countCar);
        return countCar;
    }
}
