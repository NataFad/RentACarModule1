/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.viewobject.CarViewObject;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Class CarDAO
 * <p>
 * Class CarDAO creates object Car and executes queries the table Cars.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public class CarDAO extends DAO<Car> {

    private volatile static CarDAO instance;
    private static Logger log = Logger.getLogger(CarDAO.class);

    private CarDAO() {
        super();
    }

    public static CarDAO getInstance() {
        if (instance == null) {
            synchronized (CarDAO.class) {
                if (instance == null) {
                    instance = new CarDAO();
                }
            }
        }
        return instance;
    }

    /**
     * Method sqlQueryStringByFilter()
     * returns string of sql-query based on the rental dates and defined filters
     *
     * @param fromDate
     * @param byDate
     * @param filterValues
     * @return String
     */
    public String sqlQueryStringByFilter(Date fromDate, Date byDate, HashMap<String, String> filterValues) {
        String sqlQueryFromWhere = "FROM cars AS CarVO " +
                "LEFT JOIN ratings AS R ON CarVO.ratings_id = R.id " +
                "LEFT JOIN types AS T ON CarVO.types_id = T.id " +
                "LEFT JOIN modelsandmarks AS MAndM ON CarVO.ModelsAndMarks_id = MAndM.id " +
                "LEFT JOIN fuels AS F ON CarVO.Fuels_id = F.id "
                + "WHERE NOT EXISTS (SELECT O.cars_id FROM orders AS O "
                + "WHERE NOT (O.fromdate >= '" + byDate + "' OR O.bydate <= '" + fromDate + "') AND O.cars_id = CarVO.id)";
        String transmission = filterValues.get("transmission");
        if (transmission != null) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.transmission= '" + transmission.toLowerCase() + "'";
        }
        String fuelId = filterValues.get("fuelId");
        if (!fuelId.equals("")) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.fuels_id= " + Integer.parseInt(fuelId);
        }
        String typeId = filterValues.get("typeId");
        if (!typeId.equals("")) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.types_id = " + Integer.parseInt(typeId);
        }
        String ratingId = filterValues.get("ratingId");
        if (!ratingId.equals("")) {
            sqlQueryFromWhere = sqlQueryFromWhere + " AND CarVO.ratings_id = " + Integer.parseInt(ratingId);
        }

        return sqlQueryFromWhere;
    }

    /**
     * Method searchCar()
     * performs a search based on the rental dates and defined filters
     *
     * @param fromDate
     * @param byDate
     * @param filterValues
     * @return list
     * @throws HibernateException
     */
    public List<CarViewObject> searchCar(Date fromDate, Date byDate, HashMap<String, String> filterValues) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();

        String orderBy = filterValues.get("orderBy");
        int page = Integer.parseInt(filterValues.get("page"));
        int recordsPerPage = Integer.parseInt(filterValues.get("recordsPerPage"));

        long difference = byDate.getTime() - fromDate.getTime();
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
                + sqlQueryStringByFilter(fromDate, byDate, filterValues);

        if (orderBy != null) {
            sqlQuery = sqlQuery + " ORDER BY " + orderBy;
        } else {
            sqlQuery = sqlQuery + " ORDER BY CarVO.transmission, F.name, T.name, R.name";
        }

        List<CarViewObject> list = null;
        try {
            list = session.createSQLQuery(sqlQuery).addScalar("id", StandardBasicTypes.INTEGER)
                    .addScalar("registrationNumber", StandardBasicTypes.STRING)
                    .addScalar("transmission", StandardBasicTypes.STRING)
                    .addScalar("rating", StandardBasicTypes.STRING)
                    .addScalar("typeCar", StandardBasicTypes.STRING)
                    .addScalar("model", StandardBasicTypes.STRING)
                    .addScalar("marka", StandardBasicTypes.STRING)
                    .addScalar("fuel", StandardBasicTypes.STRING)
                    .addScalar("description", StandardBasicTypes.STRING)
                    .addScalar("cost", StandardBasicTypes.BIG_DECIMAL)
                    .setResultTransformer(Transformers.aliasToBean(CarViewObject.class))
                    .setFirstResult(page - 1)
                    .setMaxResults(recordsPerPage).list();
        } catch (HibernateException e) {
            log.error("Error search cars in CarDAO " + e);
            throw new DAOException(e.getMessage());
        }
        return list;
    }

    /**
     * Method countCarByFilter()
     * performs a search based on the rental dates and defined filters
     *
     * @param fromDate
     * @param byDate
     * @param filterValues
     * @return
     * @throws SQLException
     */
    public BigInteger countCarByFilter(Date fromDate, Date byDate, HashMap<String, String> filterValues) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        // query text writing
        String sqlQuery = "SELECT count(*) "
                + sqlQueryStringByFilter(fromDate, byDate, filterValues);

        Query query = session.createSQLQuery(sqlQuery);
        BigInteger countCar = BigInteger.ZERO;

        try {
            List results = query.list();
            countCar = (BigInteger) results.get(0);
        } catch (HibernateException e) {
            log.error("Error count of the cars by filter in CarDAO " + e);
            throw new DAOException(e.getMessage());
        }
        return countCar;
    }
}
