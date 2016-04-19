/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Type;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.constants.ISqlQuery;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class TypeDAO
 * 
 * Class TypeDAO creates object Type and executes queries the table types.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class TypeDAO extends DAO {

	private volatile static TypeDAO instance;

	private final String COLUMN_NAME_ID = "id";
	private final String COLUMN_NAME_NAME = "name";
	private final String COLUMN_NAME_RATECOST = "rateCost";
	private final String COLUMN_NAME_RATEDISCOUNT = "rateDiscount";

	private TypeDAO() {
		super();
	}

	public static TypeDAO getInstance() {
		if (instance == null) {
			synchronized (TypeDAO.class) {
				if (instance == null) {
					instance = new TypeDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getById() searches object type by id
	 *
	 * Implements #GET_TYPE_BY_ID
	 */
	public Type getById(int id) {
		Type type = null;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				 PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_TYPE_BY_ID)){
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					type = new Type();
					type.setId(result.getInt(COLUMN_NAME_ID));
					type.setName(result.getString(COLUMN_NAME_NAME));
					type.setRatecost(result.getBigDecimal(COLUMN_NAME_RATECOST));
					type.setRateDiscount(result.getBigDecimal(COLUMN_NAME_RATEDISCOUNT));
				}
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(TypeDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return type;
	}

	/**
	 * Method getAll() gets data about all types
	 *
	 * Implements #GET_ALL_TYPES
	 */
	public ArrayList<Type> getAll(){
		ArrayList<Type> typeList = new ArrayList<Type>();
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_ALL_TYPES);
			ResultSet result = ps.executeQuery()){
			while (result.next()) {
				Type type = new Type();
          		type.setId(result.getInt(COLUMN_NAME_ID));
				type.setName(result.getString(COLUMN_NAME_NAME));
				type.setRatecost(result.getBigDecimal(COLUMN_NAME_RATECOST));
				type.setRateDiscount(result.getBigDecimal(COLUMN_NAME_RATEDISCOUNT));
				typeList.add(type);
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(TypeDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return typeList;
	}

	/**
	 * Method add() writes object type in the table
	 * Implements #ADD_TYPE
	 *
	 * @param o
	 *
	 */
	public void add(Object o){
		Type type = (Type) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(ISqlQuery.ADD_TYPE)){
			ps.setString(1, type.getName());
			ps.setBigDecimal(2, type.getRateCost());
			ps.setBigDecimal(3, type.getRateDiscount());
			ps.executeUpdate();
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(TypeDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void update(Object o){
	}

	/**
	 * Method delete() deletes object type from the table
	 * Implements #DELETE_TYPE
	 *
	 * @param o
   */
	public void delete(Object o) {
		Type type = (Type) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				 PreparedStatement ps = connection.prepareStatement(ISqlQuery.DELETE_TYPE)) {
			ps.setInt(1, type.getId());
			ps.executeUpdate();
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(TypeDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}
}
