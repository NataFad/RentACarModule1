package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.viewobject.UserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String logout(ModelMap modelMap, HttpSession httpSession) {
        page = "login";
        log.debug("MainController logout used...");
        httpSession.invalidate();
        modelMap.clear();
        UserVO userVO = userService.exitUser();
        modelMap.addAttribute("userVO", userVO);
        httpSession.setAttribute("userType", TypeUser.fromValue(userVO.getAccess()));
        log.debug("MainController logout returned: " + page + ".jsp");
        return page;
    }

}
