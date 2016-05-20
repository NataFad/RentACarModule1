/**
 * 
 */
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.dao.UserDAO;
import by.academy.it.rentacar.exceptions.DAOException;
import org.apache.log4j.Logger;

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
	public int registerUser(User user) throws DAOException {
		int successRegistrate = 1;

		UserDAO userDAO = UserDAO.getInstance();
		if (!userDAO.checkLogin(user.getLogin().trim())) {
			successRegistrate = -1;
			Logger.getLogger(userDAO.getClass()).error("User has yet registered with the login");
		}
		try {
			userDAO.saveOrUpdate(user);
		} catch (DAOException e) {
			successRegistrate = -2;
			Logger.getLogger(userDAO.getClass()).error("The registration has not been completed");
			e.printStackTrace();
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
	public User loginUser(String login, String password) throws DAOException {
		User user = null;
		user = UserDAO.getInstance().getUser(login, password);
		return user;
	}
}
