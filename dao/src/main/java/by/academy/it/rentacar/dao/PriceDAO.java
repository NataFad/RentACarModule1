/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.constants.ISqlQuery;
import by.academy.it.rentacar.enums.Transmission;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
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
	 * Implements #GET_TYPES_BY_TRANSMISSION_AND_FUEL
	 */
	public Price getByTransmissionAndFuel(Transmission transmission, int fuelId) {
		Price price = null;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				 PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_PRICE_BY_TRANSMISSION_AND_FUEL)){
			ps.setString(1, transmission.value().toLowerCase());
			ps.setInt(2, fuelId);
			try (ResultSet	result = ps.executeQuery()) {
				if (result.next()) {
					price = new Price();
					price.setId(result.getInt(COLUMN_NAME_ID));
					price.setName(result.getString(COLUMN_NAME_NAME));
					price.setTransmission(transmission);
					price.setFuelId(result.getInt(COLUMN_NAME_FUEL_ID));
					price.setCostOfDay(result.getBigDecimal(COLUMN_NAME_COSTOFDAY));
					price.setDiscount(result.getBigDecimal(COLUMN_NAME_DISCOUNT));
				}
			} catch (SQLException e) {
				Logger.getLogger(PriceDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(PriceDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return price;
	}

	/**
	 * Method add() writes object price in the table
	 * Implements #ADD_PRICE
	 *
	 * @param o
   */
	public void add(Object o) {
  	Price price = (Price) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.ADD_PRICE)) {
			ps.setString(1, price.getName());
			ps.setString(2, price.getTransmission().value().toLowerCase());
			ps.setInt(3, price.getFuelId());
			ps.setBigDecimal(4, price.getCostOfDay());
			ps.setBigDecimal(5, price.getDiscount());
			ps.executeUpdate();
		}catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(PriceDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void update(Object o){
	}

	/**
	 * Method delete() deletes object price from yhe table
	 * Implements #DELETE_PRICE
	 *
	 * @param o
   */
	public void delete(Object o){
		Price price = (Price) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.DELETE_PRICE)) {
			ps.setInt(1, price.getId());
			ps.executeUpdate();
		}catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(PriceDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Price> getAll() {
		return null;
	}
}
