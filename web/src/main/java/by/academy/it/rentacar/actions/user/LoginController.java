/**
 *
 */
package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.controller.MainController;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.managers.ErrorManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@Controller
//@WebServlet(urlPatterns = {"/pages/login.jsp"})
public class LoginController{

    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(ModelMap modelMap, @ModelAttribute User user, HttpSession httpSession) {
        log.info("MainController checkLogin used...");
        log.info("Processing client: " + user);
        String login = user.getLogin();
        String password = user.getPassword();
        User userReg = userService.loginUser(login, password, user);
        if (userReg == null) {
            modelMap.addAttribute("errorLoginPassMessage", ErrorManager.getInstance().getProperty("error.login"));
        } else {
            // Если авторизация выполнена, то присваиваем сессии соответствующие атрибуты
            int access = userReg.getAccess();
            user = userReg;
        }
        modelMap.put("user", user);
        httpSession.setAttribute("access", user.getAccess());
        httpSession.setAttribute("userType", TypeUser.fromValue(user.getAccess()));
        page = "login";
        log.info("MainController checkLogin returned: " + page + ".jsp");
        return page;
    }
}
