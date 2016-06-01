package by.academy.it.rentacar.controller;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.entity.UserEntity;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.viewobject.UserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class MainController {
    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(ModelMap modelMap, HttpSession httpSession) {
        //public String mainPage() {
        log.info("MainController mainPage used...");
        UserVO user = (UserVO) modelMap.get("userVO");
        if (user == null) {
            user = userService.exitUser();
        }
        modelMap.addAttribute("userVO", user);
        httpSession.setAttribute("userType", TypeUser.fromValue(user.getAccess()));
        return "login";
    }

    /**
     * return form for add user
     * @param user
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String form(@ModelAttribute UserVO user) {
        return "addUser";
    }

    /**
     * create new user
     * @param model
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String add(ModelMap model, @Validated UserEntity user, BindingResult br, HttpSession session) {
        if (br.hasErrors()) {
            return "addUser";
        }
        int success = userService.registeredUser(user);
        if (success == 1) {
            UserVO userVO = new UserVO();
            userVO.setLogin(user.getLogin());
            userVO.setPassword(user.getPassword());
            userVO = userService.loginUser(userVO);
            model.put("userVO", userVO);
            return "login";
        }
        return "main";
    }

    /**
     * after login page
     * @param session
     * @return
     */
    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public String login(HttpSession session, Principal principal) {
        String login = principal.getName();
        UserEntity userdb = null;
        if (login != null) {
            userdb = userService.getUserByLogin(login);
        }
        session.setAttribute("user", userdb);
        log.info("user:" + userdb);
        return "login";
    }


}