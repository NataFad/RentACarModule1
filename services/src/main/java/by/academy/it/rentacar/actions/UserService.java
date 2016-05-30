/**
 *
 */
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.dao.IUserDAO;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.managers.CoderManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class UserService
 * <p>
 * Class UserService responsible for user login to the site
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Service("userService")
@Transactional
public class UserService implements IUserService {

    private Logger log = Logger.getLogger(UserService.class);

    @Autowired
    private IUserDAO userDAO;

    /**
     * Method registeredUser() registers user
     *
     * @param user
     * @return successRegistrate
     */
    public int registeredUser(User user) {
        int successRegistrate = 1;
        if (!userDAO.checkLogin(user.getLogin().trim())) {
            successRegistrate = -1;
            log.error("User has yet registeredCar with the login");
        } else {
            user.setPassword(CoderManager.getHashCode(user.getPassword()));
            userDAO.saveOrUpdate(user);
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
    public User loginUser(String login, String password, User user) {
        User userReg = null;
        TypeUser type = null;
        try {
            type = TypeUser.stringToEnum(user.getAccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // проверяем, не пытается ли пользователь повторно авторизоваться
        if (type == TypeUser.GUEST) {
            userReg = userDAO.getUser(login, password);

        } else if (type != null) {
            userReg = user;
        }
        return userReg;
    }

    /**
     * Method exitUser() gets new user-guest
     */
    public User exitUser() {
        User userGuest = new User();
        userGuest.setName("Гость");
        userGuest.setAccess(0);
        return userGuest;
    }
}
