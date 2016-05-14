/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Car;
import org.hibernate.type.StandardBasicTypes;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class CarDAO
 *
 * Class CarDAO creates object Car and executes queries the table Cars.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 * 
 */
public class CarDAO extends DAO<Car> {

	private volatile static CarDAO instance;

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
	 * Method searchCar()
	 * performs a search based on the rental dates and defined filters
	 *
	 * @param fromDate
	 * @param byDate
	 * @param filterValues
	 * @return
	 * @throws SQLException
     */
	public ArrayList<Car> searchCar(Date fromDate, Date byDate, HashMap<String, String> filterValues) {
		// query text writing
		String sqlQuery = "SELECT CarVO.id AS id, " +
				"CarVO.registrationNumber AS registrationNumber, " +
				"CarVO.transmission AS transmission, " +
				"R.name AS rating, " +
				"T.name AS type, " +
				"MAndM.model AS model," +
				"MAndM.mark AS marka," +
				"F.name AS fuel," +
				"CarVO.description AS description " +
				"FROM cars AS CarVO " +
				"LEFT JOIN ratings AS R ON CarVO.ratings_id = R.id " +
				"LEFT JOIN types AS T ON CarVO.types_id = T.id " +
				"LEFT JOIN modelsandmarks AS MAndM ON CarVO.ModelsAndMarks_id = MAndM.id " +
				"LEFT JOIN fuels AS F ON CarVO.Fuels_id = F.id "
				+ "WHERE NOT EXISTS (SELECT O.cars_id FROM Order AS O WHERE NOT (O.fromdate > :fromDate OR O.bydate < :byDate) AND O.car = CarVO.id)";
		String transmission = filterValues.get("transmission");
		if (transmission != null) {
			sqlQuery = sqlQuery + " AND CarVO.transmission= '" + transmission.toLowerCase() + "'";
		}
		String fuelId = filterValues.get("fuelId");
		if (fuelId != null){
			sqlQuery = sqlQuery + " AND CarVO.fuels_id= " + Integer.parseInt(fuelId);
		}
		String typeId = filterValues.get("typeId");
		if (typeId != null) {
			sqlQuery = sqlQuery + " AND CarVO.types_id = " + Integer.parseInt(typeId);
		}
		String ratingId = filterValues.get("ratingId");
		if (ratingId != null) {
			sqlQuery = sqlQuery + " AND CarVO.ratings_id = " + Integer.parseInt(ratingId);
		}
		sqlQuery = sqlQuery + " ORDER BY TC.id";

		session.createSQLQuery(sqlQuery).setParameter(1, fromDate).setParameter(2, byDate).addScalar("id", StandardBasicTypes.INTEGER )    .addScalar("firstName",StandardBasicTypes.STRING )    .addScalar("password",StandardBasicTypes.STRING )    .setResultTransformer(Transformers.aliasToBean(Employee.class))    .list();

		ArrayList<Car> list = new ArrayList<Car>();
		/**

		try (Connection connection = DBConnectionPool.getInstance().getConnection();
			 PreparedStatement ps = connection.prepareStatement(query);
			 ResultSet result = ps.executeQuery()) {
			long difference = byDate.getTime() - fromDate.getTime();
			int days = (int) (difference / (24 * 60 * 60 * 1000) + 1);
			list = getListCarFromResult(result, days);
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(CarDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		 */
		return list;
	}
}
