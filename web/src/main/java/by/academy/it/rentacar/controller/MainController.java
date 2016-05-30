package by.academy.it.rentacar.controller;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap modelMap) {
        page = "login";
        log.info("MainController mainPage used...");
        User user = (User) modelMap.get("user");
        if (user == null) {
            user = userService.exitUser();
        }
        modelMap.put("user", user);
        log.info("MainController mainPage returned: " + page + ".jsp");
        return page;
    }

    //TODO: нужно ли будет где-то закрывать сессию или она 1 на транзакцию в нашем случае?
}