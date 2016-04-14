package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.UserService;
import by.academy.it.rentacar.beans.User;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

/** Class RegisterUserAction
 * 
 * Class RegisterUserAction responsible for the registration of the user
 * 
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 *
 */
public class RegisterUserAction extends Action{
	
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
    	User user = new User();

    	int access = 1;
    	
        user.setName(request.getParameter("name").trim());
        user.setSurname(request.getParameter("surname").trim());
        user.setPassportNumber(request.getParameter("passportNumber").trim());
        user.setPassportIssue(Date.valueOf(request.getParameter("passportIssue").trim()));
        user.setPassportExpire(Date.valueOf(request.getParameter("passportExpire").trim()));
        user.setPassportAuthority(request.getParameter("passportAuthority").trim());
        user.setBirthday(Date.valueOf(request.getParameter("birthday").trim()));
        user.setLogin(request.getParameter("login").trim());
        user.setPassword(request.getParameter("password").trim());
        user.setEmail(request.getParameter("email").trim());
        user.setPhone(request.getParameter("phone").trim());
        user.setAccess(access);
        user.setType(TypeUser.USER);

        int successRegister = UserService.getInstance().registerUser(user);

        switch (successRegister){
            case -1:
                session.setAttribute("errorLoginPassMessage", errorManager.getProperty("error.reg"));
                return ConfigurationManager.getProperty("page.reg");
            case 0:
                return ConfigurationManager.getProperty("page.main");
        }

        //Если авторизация выполнена, то присваиваем сессии соответствующие атрибуты и устанавливаем куки
        session.setAttribute("access", access);
        session.setAttribute("user", user);
        
        request.getSession().setAttribute("errorLoginPassMessage", errorManager.getProperty("success.reg"));
        return ConfigurationManager.getProperty("page.main");
    }
}
