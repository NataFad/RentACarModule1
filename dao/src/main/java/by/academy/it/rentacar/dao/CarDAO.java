/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Car;
import by.academy.it.rentacar.enums.Transmission;

import java.math.BigDecimal;
import java.sql.*;
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
public class CarDAO extends DAO {

	private volatile static CarDAO instance;

	private final String COLUMN_NAME_ID = "id";
	private final String COLUMN_NAME_REGNUMBER = "registrationNumber";
	private final String COLUMN_NAME_TRANSMISSION = "transmission";
	private final String COLUMN_NAME_RATING_ID = "ratings_id";
	private final String COLUMN_NAME_TYPE_ID = "types_id";
	private final String COLUMN_NAME_MODEL_ID = "modelsAndMarks_id";
	private final String COLUMN_NAME_FUEL_ID = "fuels_id";
	private final String COLUMN_NAME_PRICE_ID = "price_id";
	private final String COLUMN_NAME_DESCRIPTION = "description";
	private final String COLUMN_NAME_COSTOFDAY = "costOfDay";
	private final String COLUMN_NAME_DISCOUNT = "discount";

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
	 * Method add() writes object car in the table
	 *
	 * Implements #SQL_ADD_CAR
	 */
	public void add(Object o) throws SQLException {
		Car car = (Car) o;
		Connection connection = poolInstance.getConnection();
		String query = sqlManager.getProperty(sqlManager.SQL_ADD_CAR);
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setString(1, car.getRegistrationNumber());
		ps.setString(2, car.getTransmission().value().toLowerCase());
		ps.setInt(3, car.getRatingId());
		ps.setInt(4, car.getTypeId());
		ps.setInt(5, car.getModelAndMarkId());
		ps.setInt(6, car.getPriceId());
		ps.setInt(7, car.getFuelId());
		ps.setBigDecimal(8, car.getCostOfDay());
		ps.setBigDecimal(9, car.getDiscount());
		ps.setString(10, car.getDescription());
		ps.executeUpdate();
		
		poolInstance.freeConnection(connection);
	}

	/**
	 * Method getListCarFromResult()
	 * transforms result of sql-query in object car and adds in list
	 *
	 * @param result
	 * @param countDays
	 * @return ArrayList<Car>
	 * @throws SQLException
     */
	private ArrayList<Car> getListCarFromResult(ResultSet result, int countDays) throws SQLException {
		ArrayList<Car> list = new ArrayList<Car>();

		while (result.next()) {
			Car car = new Car();

			car.setId(result.getInt(COLUMN_NAME_ID));
			car.setRegistrationNumber(result.getString(COLUMN_NAME_REGNUMBER));
			car.setTransmission(Transmission.valueOf(result.getString(COLUMN_NAME_TRANSMISSION).trim().toUpperCase()));
			car.setRatingId(result.getInt(COLUMN_NAME_RATING_ID));
			car.setTypeId(result.getInt(COLUMN_NAME_TYPE_ID));
			car.setModelAndMarkId(result.getInt(COLUMN_NAME_MODEL_ID));
			car.setFuelId(result.getInt(COLUMN_NAME_FUEL_ID));
			car.setPriceId(result.getInt(COLUMN_NAME_PRICE_ID));
			car.setDescription(result.getString(COLUMN_NAME_DESCRIPTION));
			car.setCostOfDay(result.getBigDecimal(COLUMN_NAME_COSTOFDAY));
			car.setDiscount(result.getBigDecimal(COLUMN_NAME_DISCOUNT));
			BigDecimal costOfDay =  car.getCostOfDay();
			car.setCost(costOfDay.multiply(new BigDecimal(Integer.toString(countDays))));

			list.add(car);
		}
		return list;
	}

	/**
	 * Method getAllCars() reads from the table Car and add in the list
	 *
	 * Implements #SQL_GET_ALL_CARS
	 */
	public ArrayList<Car> getAll() throws SQLException {
		String query = sqlManager.getProperty(sqlManager.SQL_GET_ALL_CARS);
		Connection connection = poolInstance.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();

		ArrayList<Car> list = getListCarFromResult(result, 1);

		poolInstance.freeConnection(connection);
		return list;
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
	public ArrayList<Car> searchCar(Date fromDate, Date byDate, HashMap<String, String> filterValues) throws SQLException {
		// query text writing
		String query = "SELECT * FROM cars WHERE NOT EXISTS (SELECT cars_id FROM orders "
				+ "WHERE NOT (fromdate > '" + byDate + "' OR bydate < '" + fromDate + "') AND cars_id = cars.id)";
		String transmission = filterValues.get("transmission");
		if (transmission != null) {
			query = query + " AND transmission='" + transmission.toLowerCase() + "'";
		}
		String fuelId = filterValues.get("fuelId");
		if (fuelId != null) {
			query = query + " AND fuels_id=" + Integer.parseInt(fuelId);
		}
		String typeId = filterValues.get("typeId");
		if (typeId != null) {
			query = query + " AND types_id=" + Integer.parseInt(typeId);
		}
		String ratingId = filterValues.get("ratingId");
		if (ratingId != null) {
			query = query + " AND ratings_id=" + Integer.parseInt(ratingId);
		}
		
		Connection connection = poolInstance.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		
		long difference = byDate.getTime() - fromDate.getTime();
		int days = (int) (difference / (24 * 60 * 60 * 1000) + 1);

		ArrayList<Car> list = getListCarFromResult(result, days);

		poolInstance.freeConnection(connection);
		return list;
	}

	public void update(Object o) throws SQLException {
	}

	public void delete(Object o) throws SQLException {
	}

	public int count() throws SQLException {
		return 0;
	}
}
