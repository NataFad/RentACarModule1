/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Car;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.enums.Transmission;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
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
	private final String COLUMN_NAME_RATING = "rating";
	private final String COLUMN_NAME_TYPE_ID = "types_id";
	private final String COLUMN_NAME_TYPE_NAME = "type_name";
	private final String COLUMN_NAME_MODEL_ID = "modelsAndMarks_id";
	private final String COLUMN_NAME_MODEL = "model";
	private final String COLUMN_NAME_MARKA = "marka";
	private final String COLUMN_NAME_FUEL_ID = "fuels_id";
	private final String COLUMN_NAME_FUEL = "fuel";
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
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			String query = sqlManager.getProperty(sqlManager.SQL_ADD_CAR);
			ps = connection.prepareStatement(query);
		
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

		} catch (IOException| PropertyVetoException e) {
			Logger.getLogger(CarDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
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
			car.setRatingName(result.getString(COLUMN_NAME_RATING));
			car.setTypeId(result.getInt(COLUMN_NAME_TYPE_ID));
			car.setTypeName(result.getString(COLUMN_NAME_TYPE_NAME));
			car.setModelAndMarkId(result.getInt(COLUMN_NAME_MODEL_ID));
			car.setModelName(result.getString(COLUMN_NAME_MARKA)+", " +result.getString(COLUMN_NAME_MODEL));
			car.setFuelId(result.getInt(COLUMN_NAME_FUEL_ID));
			car.setFuelName(result.getString(COLUMN_NAME_FUEL));
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
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		ArrayList<Car> list = new ArrayList<Car>();
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			list = getListCarFromResult(result, 1);
		} catch (IOException| PropertyVetoException e) {
			Logger.getLogger(CarDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e){
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}

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
		String query = "SELECT cars.*, ratings.name as rating, fuels.name as fuel, types.name as type_name, "
				+ " modelsandmarks.mark as marka, modelsandmarks.model as model FROM cars "
				+ "left join ratings on ratings.id = cars.ratings_id "
				+ "left join types on types.id = cars.types_id "
				+ "left join modelsandmarks on modelsandmarks.id = cars.modelsandmarks_id "
				+ "left join fuels on fuels.id = cars.fuels_id "
				+ "WHERE NOT EXISTS (SELECT cars_id FROM orders "
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

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		ArrayList<Car> list = new ArrayList<Car>();
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
		
			long difference = byDate.getTime() - fromDate.getTime();
			int days = (int) (difference / (24 * 60 * 60 * 1000) + 1);

			list = getListCarFromResult(result, days);
		} catch (IOException| PropertyVetoException e) {
			Logger.getLogger(CarDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e){
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}

	public void update(Object o) throws SQLException {
	}

	/**
	 * Method delete() car from the table
	 * @param o
	 * @throws SQLException
     */
	public void delete(Object o) throws SQLException {
		Car car = (Car) o;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			String query = sqlManager.getProperty(sqlManager.SQL_DELETE_CAR);
			ps = connection.prepareStatement(query);
			ps.setInt(1, car.getId());
			ps.executeUpdate();
		} catch (IOException| PropertyVetoException e) {
			Logger.getLogger(CarDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(CarDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public int count() throws SQLException {
		return 0;
	}
}
