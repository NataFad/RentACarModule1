package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.User;

/**
 * Created by Nata on 21.05.2016.
 */
public interface IUserService {

    int registeredUser(User user);
    User loginUser(User user);
    User getUserByLogin(String login);
    User exitUser();
}
