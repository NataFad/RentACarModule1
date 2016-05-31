package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.viewobject.UserVO;

/**
 * Created by Nata on 21.05.2016.
 */
public interface IUserService {

    int registeredUser(User user);
    UserVO loginUser(UserVO user);
    UserVO exitUser();
}
