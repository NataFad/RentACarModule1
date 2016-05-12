/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Car;

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
 * @version 1.1
 * @since 2016-04
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
		String hql = "SELECT TC FROM Car TC "
				+ "WHERE NOT EXISTS (SELECT O.cars_id FROM Order O "
				+ "WHERE NOT (fromdate > :fromDate OR bydate < :byDate) AND O.cars_id = TC.id)";
		String transmission = filterValues.get("transmission");
		if (transmission != null) {
			hql = hql + " AND transmission= '" + transmission.toLowerCase() + "'";
		}
		String fuelId = filterValues.get("fuelId");
		if (fuelId != null) {
			hql = hql + " AND fuels_id= :fuelId" + Integer.parseInt(fuelId);
		}
		String typeId = filterValues.get("typeId");
		if (typeId != null) {
			hql = hql + " AND types_id=:typeId" + Integer.parseInt(typeId);
		}
		String ratingId = filterValues.get("ratingId");
		if (ratingId != null) {
			hql = hql + " AND ratings_id=:ratingId" + Integer.parseInt(ratingId);
		}
		hql = hql + " ORDER BY TC.id";

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
