/**
 *
 */
package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.UserService;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class LoginUserAction
 * <p>
 * Class LoginUserAction responsible for user login to the site
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
public class LoginUserAction extends Action {

    private final String LOGIN = "login";
    private final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        ConfigurationManager.getInstance();
        // извлечение из запроса логина и пароля
        String login = request.getParameter(LOGIN).trim();
        String password = request.getParameter(PASSWORD).trim();

        User userReg = UserService.getInstance().loginUser(login, password, user);
        if (userReg == null) {
            session.setAttribute("errorLoginPassMessage", errorManager.getProperty("error.login"));
       } else {
            // Если авторизация выполнена, то присваиваем сессии соответствующие атрибуты
            int access = userReg.getAccess();
            session.setAttribute("access", access);
            session.setAttribute("user", userReg);
        }
        return ConfigurationManager.getProperty("page.main");
    }
}