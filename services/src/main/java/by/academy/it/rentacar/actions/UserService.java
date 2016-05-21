/**
 *
 */
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.dao.UserDAO;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.exceptions.EnumNotFindException;
import by.academy.it.rentacar.managers.CoderManager;
import by.academy.it.rentacar.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

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
public class UserService implements IUserService{

	private volatile static UserService instance;
	private Logger log = Logger.getLogger(UserService.class);

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
	public int registerUser(User user) {
		int successRegistrate = 1;
		Transaction transaction = HibernateUtil.getInstance().getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }

		UserDAO userDAO = UserDAO.getInstance();
		try {
			if (!userDAO.checkLogin(user.getLogin().trim())) {
                successRegistrate = -1;
                log.error("User has yet registered with the login");
                transaction.rollback();
            } else {
                try {
                    user.setPassword(CoderManager.getHashCode(user.getPassword()));
                    userDAO.saveOrUpdate(user);
                    if (!transaction.wasCommitted()) {
                        transaction.commit();
                    }
                } catch (DAOException e) {
                    successRegistrate = -2;
                    log.error("The registration has not been completed");
                    transaction.rollback();
                }
            }
		} catch (DAOException e) {
			successRegistrate = -2;
			log.error("The registration has not been completed");
			transaction.rollback();
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
	public User loginUser(String login, String password, User user){
		User userReg = null;
		TypeUser type = null;
		Transaction transaction = HibernateUtil.getInstance().getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }

		try {
			type = TypeUser.stringToEnum(user.getAccess());
		} catch (EnumNotFindException e) {
			log.error(e.getMessage());
			transaction.rollback();
		} catch (Exception e) {
			log.error(e.getMessage());
			transaction.rollback();
		}
		// проверяем, не пытается ли пользователь повторно авторизоваться
		if (type == TypeUser.GUEST) {
			try {
				userReg = UserDAO.getInstance().getUser(login, password);
				if (!transaction.wasCommitted()) {
					transaction.commit();
				}
			} catch (DAOException ex) {
				log.error(ex.getMessage());
				transaction.rollback();
			}
		} else if (type != null) {
			userReg = user;
			transaction.commit();
		}
		return userReg;
	}

	/**
	 * Method exitUser() gets new user-guest
	 *
	 */
	public User exitUser(){
		Transaction transaction = HibernateUtil.getInstance().getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }
		User userGuest = new User();
		userGuest.setName("Гость");
		userGuest.setAccess(0);
		if (!transaction.wasCommitted()) {
			transaction.commit();
		}
		return userGuest;
	}
}
