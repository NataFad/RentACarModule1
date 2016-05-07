/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.constants.ISqlQuery;
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
	 * Implements #ADD_USER
	 */
	public void add(Object o){
		User user = (User) o;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				 PreparedStatement ps = connection.prepareStatement(ISqlQuery.ADD_USER)){
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
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method update() updates data of user in the table
	 *
	 * Implements #UPDATE_USER
	 */
	public void update(Object o){
		User user = (User) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.UPDATE_USER)){
			ps.setString(1, user.getPassportNumber());
			ps.setDate(2, getCurrentSQLDate(user.getPassportIssue()));
			ps.setDate(3, getCurrentSQLDate(user.getPassportExpire()));
			ps.setString(4, user.getPassportAuthority());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
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
	 * Implements #GET_ALL_USERS
	 */
	public ArrayList<User> getAll() {
		ArrayList<User> list = new ArrayList<User>();
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_ALL_USERS);
				ResultSet result = ps.executeQuery()){
			list = getListUserFromResult(result);
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Method getUser() searches user by login and password
	 *
	 * Implements #GET_USER
	 */
	public User getUser(String login, String password){
		User user = null;
		String pass = CoderManager.getHashCode(password);
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_USER)){
			ps.setString(1, login);
			ps.setString(2, pass);
			try (ResultSet result = ps.executeQuery()) {
				ArrayList<User> list = getListUserFromResult(result);
				if (list.size() > 0) {
					user = list.get(0);
				}
			}catch (SQLException e){
				Logger.getLogger(UserDAO.class).error(e.getMessage());
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Method getById() searches object user by id
	 *
	 * Implements #GET_USER_BY_ID
	 */
	public User getById(int id){
		User user = null;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_USER_BY_ID)){
			ps.setInt(1, id);
			try (ResultSet result = ps.executeQuery()) {
				ArrayList<User> list = getListUserFromResult(result);
				if (list.size() > 0) {
					user = list.get(0);
				}
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	 /**
	  * Method checkLogin() checks for entry to the entered login
	  *
      * implements #CHECK_LOGIN
      */
  	public boolean checkLogin(String login){
			boolean checkResult = false;
			try (Connection	connection = DBConnectionPool.getInstance().getConnection();
					PreparedStatement ps = connection.prepareStatement(ISqlQuery.CHECK_LOGIN)){
		    ps.setString(1, login);
    	  try (ResultSet result = ps.executeQuery()) {
					if (!result.next()) {
						checkResult = true;
					}
				} catch (SQLException e) {
					Logger.getLogger(UserDAO.class).error(e.getMessage());
					e.printStackTrace();
				}
			} catch (SQLException | IOException | PropertyVetoException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
      return checkResult;
    }
    
    /**
	 * Method getAccess() gets the type of user access
	 *
     * implements #GET_ACCESS
     */
	public int getAccess(String id) {
    int access = -1;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_ACCESS)){
			ps.setInt(1, Integer.parseInt(id));
    	try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					access = result.getInt(COLUMN_NAME_ACCESS);
				} else {
					throw new RuntimeException("UserDAO: no such user");
				}
			} catch (SQLException e) {
				Logger.getLogger(UserDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
    return access;
  }
    
  /**
	 * Method updateAccess() updates the type of user access
	 *
   * implements #UPDATE_ACCESS
   */
  public void updateAccess(int id, int access) {
    if(access < 0 || access > 2) {
      throw new IllegalArgumentException("Unknown Access Level");
    }
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.UPDATE_ACCESS)){
      ps.setInt(1, access);
     	ps.setInt(2, id);
    	ps.executeUpdate();
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method count() gets count of entries in the table
	 *
	 * Implements #COUNT_USERS
	 */
	public int count(){
		int count = -1;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.COUNT_USERS);
				ResultSet result = ps.executeQuery()){
			if (result.next()) {
				count = result.getInt("COUNT(*)");
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * Method delete() deletes object user from the table
	 * Implements #DELETE_USER
	 *
	 * @param o
   */
	public void delete(Object o) {
		User user = (User) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.DELETE_USER)) {
			ps.setInt(1, user.getId());
			ps.executeUpdate();
		} catch (SQLException | PropertyVetoException | IOException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}
}
