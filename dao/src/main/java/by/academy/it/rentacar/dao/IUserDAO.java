package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.UserEntity;
import by.academy.it.rentacar.viewobject.UserVO;

/**
 * Interface IUserDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public interface IUserDAO extends IDAO<UserEntity> {

    /**
     * Method getUser() searches user by login and password
     */
    UserVO getUser(String login, String password);

    /**
     * Method getById() searches object user by id
     */
    UserEntity getById(int id);

    /**
     * Method checkLogin() checks for entry to the entered login
     */
    boolean checkLogin(String login);

    /**
     * Method getAccess() gets the type of user access
     */
    int getAccess(String id);
}
