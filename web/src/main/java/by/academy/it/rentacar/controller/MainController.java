package by.academy.it.rentacar.controller;

import by.academy.it.rentacar.actions.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    static Logger log = Logger.getLogger(MainController.class);
    String page;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    //public String mainPage(ModelMap modelMap) {
    public String mainPage() {
        page = "login";
        log.info("MainController mainPage used...");
        //User user = (User) modelMap.get("user");
        //if (user == null) {
        //    user = userService.exitUser();
        //}
        //modelMap.put("user", user);
        log.info("MainController mainPage returned: " + page + ".jsp");
        return page;
    }

    //TODO: нужно ли будет где-то закрывать сессию или она 1 на транзакцию в нашем случае?
}