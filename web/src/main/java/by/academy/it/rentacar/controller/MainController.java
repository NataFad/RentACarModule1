package by.academy.it.rentacar.controller;

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

@Controller
public class MainController {
    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(ModelMap modelMap, HttpSession httpSession) {
        //public String mainPage() {
        page = "login";
        log.info("MainController mainPage used...");
        UserVO user = (UserVO) modelMap.get("userVO");
        if (user == null) {
            user = userService.exitUser();
        }
        modelMap.addAttribute("userVO", user);
        httpSession.setAttribute("userType", TypeUser.fromValue(user.getAccess()));
        log.info("MainController mainPage returned: " + page + ".jsp");
        return page;
    }
}