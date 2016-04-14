/**
 * 
 */
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.beans.User;
import by.academy.it.rentacar.dao.UserDAO;
import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * Class UserService
 * 
 * Class UserService responsible for user login to the site
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class UserService {

	private volatile static UserService instance;

	private UserService(){}

	public static UserService getInstance() {
		if (instance == null) {
			synchronized (UserService.class) {
				if (instance == null) {
					instance = new UserService();
				}
			}
		}
		return instance;
	}

	/**
	 * Method registerUser() registers user
	 *
	 * @param user
	 * @return successRegistrate
     */
	public int registerUser(User user){
		int successRegistrate = 1;

		UserDAO userDAO = UserDAO.getInstance();

		try {
			if (!userDAO.checkLogin(user.getLogin().trim())) {
				successRegistrate = -1;
				Logger.getLogger(userDAO.getClass()).error("User has yet registered with the login");
			}
			userDAO.add(user);

		} catch (SQLException e) {
			successRegistrate = 0;
			Logger.getLogger(userDAO.getClass()).error(e.getMessage());
		}

		return successRegistrate;
	}

	/**
	 * Method loginUser() searches user by the login and the password
	 *
	 * @param login
	 * @param password
     * @return
     */
	public User loginUser(String login, String password){
		User user = null;
		try {
			user = UserDAO.getInstance().getUser(login, password);
		} catch (SQLException e) {
			Logger.getLogger(UserDAO.class).error(e.getMessage());
		}
		return user;
	}
}
