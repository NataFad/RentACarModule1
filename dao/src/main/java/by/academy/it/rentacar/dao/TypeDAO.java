/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Type;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
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
	 * Implements #SQL_GET_TYPES_BY_ID
	 */
	public Type getById(int id) throws SQLException {
		Type type = null;
		String query = sqlManager.getProperty(sqlManager.SQL_GET_TYPES_BY_ID);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();

			if (result.next()) {
				type = new Type();
				type.setId(result.getInt(COLUMN_NAME_ID));
				type.setName(result.getString(COLUMN_NAME_NAME));
				type.setRatecost(result.getBigDecimal(COLUMN_NAME_RATECOST));
				type.setRateDiscount(result.getBigDecimal(COLUMN_NAME_RATEDISCOUNT));
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(TypeDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return type;
	}

	/**
	 * Method getAll() gets data about all types
	 *
	 * Implements #SQL_GET_ALL_TYPES
	 */
	public ArrayList<Type> getAll() throws SQLException {
		ArrayList<Type> typeList = new ArrayList<Type>();
		String query = sqlManager.getProperty(sqlManager.SQL_GET_ALL_TYPES);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();

			while (result.next()) {
				Type type = new Type();
          		type.setId(result.getInt(COLUMN_NAME_ID));
				type.setName(result.getString(COLUMN_NAME_NAME));
				type.setRatecost(result.getBigDecimal(COLUMN_NAME_RATECOST));
				type.setRateDiscount(result.getBigDecimal(COLUMN_NAME_RATEDISCOUNT));
				typeList.add(type);
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(TypeDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(TypeDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return typeList;
	}

	public void add(Object o) throws SQLException {
	}

	public void update(Object o) throws SQLException {
	}

	public void delete(Object o) throws SQLException {
	}

	public int count() throws SQLException {
		return 0;
	}
}
