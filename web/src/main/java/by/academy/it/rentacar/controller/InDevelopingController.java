/**
 * 
 */
package by.academy.it.rentacar.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Class InDeveloping
 * 
 * Class InDeveloping reported that the development is in functional
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
@Controller
public class InDevelopingController {

    static Logger log = Logger.getLogger(InDevelopingController.class);

    @RequestMapping(value = "/inDeveloping")
    public String inDevelopingPage (ModelMap modelMap, HttpSession httpSession,
                                    @RequestParam(value = "command", defaultValue = "test") String page) {
        log.debug("inDeveloping used...");
        return page;
    }


}
