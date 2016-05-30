package by.academy.it.rentacar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import java.util.Enumeration;
//import javax.servlet.http.HttpSession;

/**
 * Servlet Controller
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Controller
public class MainController{
    /**
     *
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("greeting", "Hello World from Spring 4 MVC");
        return "main";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return "main";
    }
}