package by.academy.it.rentacar.controller;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {
    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/main")
    public String mainPage(ModelMap modelMap, HttpSession httpSession) {
        //public String mainPage() {
        log.info("MainController mainPage used...");
        User user = (User) httpSession.getAttribute("userVO");
        if (user == null) {
            user = userService.exitUser();
        }
        httpSession.setAttribute("userVO", user);
        httpSession.setAttribute("userType", TypeUser.fromValue(user.getAccess()));
        return "login";
    }

    /**
     * return form for add user
     *
     * @return
     */
    @RequestMapping(value = "/registration")
    public String form(ModelMap model) {
        return "registration";
    }

    /**
     * create new user
     *
     * @param model
     * @param userReg
     * @return
     */
    @RequestMapping(value = "/addUser")
    public String add(ModelMap model, @Valid User userReg, BindingResult br, HttpSession httpSession) {
        if (br.hasErrors()) {
            return "registration";
        }
        int success = userService.registeredUser(userReg);
        if (success == 1) {
            httpSession.setAttribute("userVO", userReg);
            httpSession.setAttribute("userType", TypeUser.fromValue(userReg.getAccess()));
            return "login";
        }
        return "main";
    }
}