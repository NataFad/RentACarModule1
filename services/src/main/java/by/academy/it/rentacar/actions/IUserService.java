package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.UserEntity;
import by.academy.it.rentacar.viewobject.UserVO;

/**
 * Created by Nata on 21.05.2016.
 */
public interface IUserService {

    int registeredUser(UserEntity user);
    UserVO loginUser(UserVO user);
    UserEntity getUserByLogin(String login);
    UserVO exitUser();
}
