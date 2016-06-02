package by.academy.it.rentacar.controller.user;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Class ExitUserAction
 * <p>
 * Class ExitUserAction responsible for user logout from the site
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Controller
public class ExitController {

    static Logger log = Logger.getLogger(ExitController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/exit")
    public String logout(ModelMap modelMap, HttpSession httpSession) {
        page = "login";
        log.debug("MainController logout used...");
        httpSession.invalidate();
        User userVO = userService.exitUser();
        modelMap.addAttribute("userVO", userVO);
        log.debug("MainController logout returned: " + page + ".jsp");
        return page;
    }

}
