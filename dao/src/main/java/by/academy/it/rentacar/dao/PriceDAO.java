/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Price;
import by.academy.it.rentacar.enums.Transmission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class PriceDAO
 * 
 * Class PriceDAO creates object Price and executes queries the table price.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class PriceDAO extends DAO {

	private volatile static PriceDAO instance;

	private final String COLUMN_NAME_ID = "id";
	private final String COLUMN_NAME_NAME = "name";
	private final String COLUMN_NAME_TRANSMISSION = "transmission";
	private final String COLUMN_NAME_FUEL_ID = "fuels_id";
	private final String COLUMN_NAME_COSTOFDAY = "costOfDay";
	private final String COLUMN_NAME_DISCOUNT = "discount";

	private PriceDAO() {
		super();
	}

	public static PriceDAO getInstance() {
		if (instance == null) {
			synchronized (PriceDAO.class) {
				if (instance == null) {
					instance = new PriceDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getByTransmissionAndFuel() searches object price by transmission and fuel
	 *
	 * Implements #SQL_GET_TYPES_BY_TRANSMISSION_AND_FUEL
	 */
	public Price getByTransmissionAndFuel(Transmission transmission, int fuelId) throws SQLException {
		Price price = null;
		String query = sqlManager.getProperty(sqlManager.SQL_GET_TYPES_BY_TRANSMISSION_AND_FUEL);
		Connection connection = poolInstance.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, transmission.value().toLowerCase());
		ps.setInt(2, fuelId);
		ResultSet result = ps.executeQuery();

		if (result.next()) {
			price = new Price();

			price.setId(result.getInt(COLUMN_NAME_ID));
			price.setName(result.getString(COLUMN_NAME_NAME));
			price.setTransmission(transmission);
			price.setFuelId(result.getInt(COLUMN_NAME_FUEL_ID));
			price.setCostOfDay(result.getBigDecimal(COLUMN_NAME_COSTOFDAY));
			price.setDiscount(result.getBigDecimal(COLUMN_NAME_DISCOUNT));
		}

		poolInstance.freeConnection(connection);
		return price;
	}

	public void add(Object o) throws SQLException {
	}

	public void update(Object o) throws SQLException {
	}

	public void delete(Object o) throws SQLException {
	}

	public ArrayList getAll() throws SQLException {
		return null;
	}

	public int count() throws SQLException {
		return 0;
	}
}
