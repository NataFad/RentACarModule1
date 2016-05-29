package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;

/**
 * Interface IUserDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public interface IUserDAO extends IDAO<User> {

    /**
     * Method getUser() searches user by login and password
     */
    User getUser(String login, String password);

    /**
     * Method getById() searches object user by id
     */
    User getById(int id);

    /**
     * Method checkLogin() checks for entry to the entered login
     */
    boolean checkLogin(String login);

    /**
     * Method getAccess() gets the type of user access
     */
    int getAccess(String id);
}