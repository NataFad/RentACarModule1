package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.User;

/**
 * Created by Nata on 21.05.2016.
 */
public interface IUserService {

    int registerUser(User user);
    User loginUser(String login, String password, User user);
    User exitUser();
}
