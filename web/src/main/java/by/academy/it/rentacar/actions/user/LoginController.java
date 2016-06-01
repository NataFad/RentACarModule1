/**
 *
 */
package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.controller.MainController;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.managers.ErrorManager;
import by.academy.it.rentacar.viewobject.UserVO;
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
public class LoginController {

    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(ModelMap modelMap, @ModelAttribute UserVO userVO, HttpSession httpSession) {
        log.debug("MainController checkLogin used...");
        log.debug("Processing client: " + userVO);
        UserVO userReg = userService.loginUser(userVO);
        modelMap.remove("userVO");
        if (userReg == null) {
            modelMap.addAttribute("errorLoginPassMessage", ErrorManager.getInstance().getProperty("error.login"));
            userReg = userService.exitUser();
        }
//        else {
//            // Если авторизация выполнена, то присваиваем сессии соответствующие атрибуты
////            int access = userReg.getAccess();
////            user = userReg;
//            modelMap.remove("userVO");
//            modelMap.addAttribute("userVO");
//        }
        log.debug("regUser: " + userReg);
       // modelMap.put("userVO", user);
        modelMap.addAttribute("userVO", userReg);
        httpSession.setAttribute("carVO", null);
        httpSession.setAttribute("userType", TypeUser.fromValue(userReg.getAccess()));
        page = "login";
        log.debug("MainController checkLogin returned: " + page + ".jsp");
        return page;
    }
}
