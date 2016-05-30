package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.controller.MainController;
import by.academy.it.rentacar.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Class ExitUserAction
 * 
 * Class ExitUserAction responsible for user logout from the site
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
@Controller
public class ExitController{

	static Logger log = Logger.getLogger(MainController.class);
	String page;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, HttpSession httpSession) {
		page = "login";
		log.info("MainController logout used...");
		httpSession.invalidate();
		modelMap.clear();
		User user = userService.exitUser();
		modelMap.put("user", user);
		log.info("MainController logout returned: " + page + ".jsp");
		return page;
	}

}
