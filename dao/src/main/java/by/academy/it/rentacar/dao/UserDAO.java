/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.User;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.managers.CoderManager;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class UserDAO
 * 
 * Class UserDAO creates object User and executes queries the table Users.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class UserDAO extends DAO {

	private volatile static UserDAO instance;

	private final String COLUMN_NAME_ID = "id";
	private final String COLUMN_NAME_LOGIN = "login";
	private final String COLUMN_NAME_PASSWORD = "password";
	private final String COLUMN_NAME_ACCESS = "access";
	private final String COLUMN_NAME_PHONE = "phone";
	private final String COLUMN_NAME_NAME = "name";
	private final String COLUMN_NAME_SURNAME = "surname";
	private final String COLUMN_NAME_PASS_NUMBER = "passportNumber";
	private final String COLUMN_NAME_PASS_ISSUE = "passportIssue";
	private final String COLUMN_NAME_PASS_EXPIRE = "passportExpire";
	private final String COLUMN_NAME_PASS_AUTH = "passportAuthority";
	private final String COLUMN_NAME_BIRTHDAY = "birthday";
	private final String COLUMN_NAME_EMAIL = "email";

	private UserDAO() {
		super();
	}

	public static UserDAO getInstance() {
		if (instance == null) {
			synchronized (UserDAO.class) {
				if (instance == null) {
					instance = new UserDAO();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Method add() writes object user in the table
	 *
	 * Implements #SQL_ADD_USER
	 */
	public void add(Object o) throws SQLException {
		User user = (User) o;
		Connection connection = null;
		String query = sqlManager.getProperty(sqlManager.SQL_ADD_USER);
		PreparedStatement ps = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getLogin());
			ps.setString(4, CoderManager.getHashCode(user.getPassword()));
			ps.setInt(5, user.getAccess());
			ps.setString(6, user.getPhone());
			if ((user.getPassportNumber() == null) || (user.getPassportIssue() == null) ||
					(user.getPassportNumber() == null) || (user.getPassportAuthority() == null)){
				ps.setString(7, "");
				ps.setDate(8, null);
				ps.setDate(9, null);
				ps.setString(10, "");
			}else{
				ps.setString(7, user.getPassportNumber());
				ps.setDate(8, getCurrentSQLDate(user.getPassportIssue()));
				ps.setDate(9, getCurrentSQLDate(user.getPassportExpire()));
				ps.setString(10, user.getPassportAuthority());
			}
			ps.setDate(11, getCurrentSQLDate(user.getBirthday()));
			ps.setString(12, user.getEmail());
			ps.executeUpdate();
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method update() updates data of user in the table
	 *
	 * Implements #SQL_UPDATE_USER
	 */
	public void update(Object o) throws SQLException {
		User user = (User) o;
		Connection connection = null;
		String query = sqlManager.getProperty(sqlManager.SQL_UPDATE_USER);
		PreparedStatement ps = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getPassportNumber());
			ps.setDate(2, getCurrentSQLDate(user.getPassportIssue()));
			ps.setDate(3, getCurrentSQLDate(user.getPassportExpire()));
			ps.setString(4, user.getPassportAuthority());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method getListUserFromResult()
	 * transforms result of sql-query in object user and adds in list
	 *
	 * @param result
	 * @return ArrayList<User>
	 * @throws SQLException
	 */
	private ArrayList<User> getListUserFromResult(ResultSet result) throws SQLException {
		ArrayList<User> list = new ArrayList<User>();

		while (result.next()) {
			User user = new User();

			user.setId(result.getInt(COLUMN_NAME_ID));
			user.setName(result.getString(COLUMN_NAME_NAME));
			user.setSurname(result.getString(COLUMN_NAME_SURNAME));
			user.setLogin(result.getString(COLUMN_NAME_LOGIN));
			user.setPassword(result.getString(COLUMN_NAME_PASSWORD));
			user.setAccess(result.getInt(COLUMN_NAME_ACCESS));
			try {
				user.setType(TypeUser.stringToEnum(user.getAccess()));
			} catch (Exception e) {
				user.setType(TypeUser.USER);
			}
			user.setPhone(result.getString(COLUMN_NAME_PHONE));
			String passNumber = result.getString(COLUMN_NAME_PASS_NUMBER);
			if (!passNumber.equals("")){
				user.setPassportNumber(passNumber);
				user.setPassportIssue(getCurrentDate(result.getDate(COLUMN_NAME_PASS_ISSUE)));
				user.setPassportExpire(getCurrentDate(result.getDate(COLUMN_NAME_PASS_EXPIRE)));
				user.setPassportAuthority(result.getString(COLUMN_NAME_PASS_AUTH));
			}
			user.setBirthday(getCurrentDate(result.getDate(COLUMN_NAME_BIRTHDAY)));
			user.setEmail(result.getString(COLUMN_NAME_EMAIL));

			list.add(user);
		}
		return list;
	}

	/**
	 * Method getAll() gets data about all users
	 *
	 * Implements #SQL_GET_ALL_USERS
	 */
	public ArrayList<User> getAll() throws SQLException {
		String query = sqlManager.getProperty(sqlManager.SQL_GET_ALL_USERS);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		ArrayList<User> list = new ArrayList<User>();
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			list = getListUserFromResult(result);
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Method getUser() searches user by login and password
	 *
	 * Implements #SQL_GET_USER
	 */
	public User getUser(String login, String password) throws SQLException {
		User user = null;
		String pass = CoderManager.getHashCode(password);
		String query = sqlManager.getProperty(sqlManager.SQL_GET_USER);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, login);
			ps.setString(2, pass);
			result = ps.executeQuery();
			ArrayList<User> list = getListUserFromResult(result);
			if (list.size() > 0){
				user = list.get(0);
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return user;
	}

	/**
	 * Method getById() searches object user by id
	 *
	 * Implements #SQL_GET_USER_BY_ID
	 */
	public User getById(int id) throws SQLException {
		User user = null;
		String query = sqlManager.getProperty(sqlManager.SQL_GET_USER_BY_ID);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();
			ArrayList<User> list = getListUserFromResult(result);
			if (list.size() > 0){
				user = list.get(0);
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return user;
	}

	 /**
	  * Method checkLogin() checks for entry to the entered login
	  *
      * implements #SQL_CHECK_LOGIN
      */
  	public boolean checkLogin(String login) throws SQLException {
    	String query =  sqlManager.getProperty(sqlManager.SQL_CHECK_LOGIN);
        Connection connection = null;
        PreparedStatement ps = null;
		ResultSet result = null;
		boolean checkResult = false;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
		    ps.setString(1, login);
    	    result = ps.executeQuery();
			if(!result.next()) {
        		checkResult = true;
        	}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
        return checkResult;
    }
    
    /**
	 * Method getAccess() gets the type of user access
	 *
     * implements #SQL_GET_ACCESS
     */
	public int getAccess(String id) throws SQLException {
        int access = -1;
        String query =  sqlManager.getProperty(sqlManager.SQL_SET_ACCESS);
        Connection connection = null;
        PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(id));
    	    result = ps.executeQuery();
       		if(result.next()) {
    	        access = result.getInt(COLUMN_NAME_ACCESS);
    	    } else {
    	        throw new RuntimeException("UserDAO: no such user");
        	}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
        return access;
    }
    
    /**
	 * Method updateAccess() updates the type of user access
	 *
     * implements #SQL_UPDATE_ACCESS
     */
   	public void updateAccess(int id, int access) throws SQLException {
        if(access < 0 || access > 2) {
            throw new IllegalArgumentException("Unknown Access Level");
        }
        String query =  sqlManager.getProperty(sqlManager.SQL_UPDATE_ACCESS);
        Connection connection = null;
        PreparedStatement ps = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
       		ps.setInt(1, access);
     	    ps.setInt(2, id);
    	    ps.executeUpdate();
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
    }

	/**
	 * Method count() gets count of entries in the table
	 *
	 * Implements #SQL_COUNT_USERS
	 */
	public int count() throws SQLException {
		String query = sqlManager.getProperty(sqlManager.SQL_COUNT_USERS);
		int count = -1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			if (result.next()) {
				count = result.getInt("COUNT(*)");
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return count;
	}

	public void delete(Object o) throws SQLException {
	}
}
