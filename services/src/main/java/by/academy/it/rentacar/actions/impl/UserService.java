/**
 *
 */
package by.academy.it.rentacar.actions.impl;

import by.academy.it.rentacar.actions.IUserService;
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
    @Transactional
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
     * @param user
     * @return
     */
    @Transactional(readOnly = true)
    public User loginUser(User user) {
        User userReg = null;
        TypeUser type = TypeUser.fromValue(user.getAccess());
        // проверяем, не пытается ли пользователь повторно авторизоваться
        if (type == TypeUser.GUEST) {
            userReg = userDAO.getUser(user.getLogin(), user.getPassword());
        } else if (type != null) {
            userReg = user;
        }
        return userReg;
    }

    /**
     * Method getUserByName() searches user by the login
     *
     * @param login
     * @return
     */
    @Transactional(readOnly = true)
    public User getUserByLogin(String login){
        User user = userDAO.getByKey("login", login);
        return user;
    }

    /**
     * Method exitUser() gets new user-guest
     */
    public User exitUser() {
        User userGuest = new User();
        userGuest.setName("Гость");
        userGuest.setAccess(TypeUser.GUEST.getValue());
        return userGuest;
    }
}
