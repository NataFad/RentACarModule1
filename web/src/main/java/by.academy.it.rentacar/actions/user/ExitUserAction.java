package by.academy.it.rentacar.actions.user;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.UserService;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ExitUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		User userGuest = UserService.getInstance().exitUser();

		session.setAttribute("access", 0);
		session.setAttribute("user", userGuest);
		session.removeAttribute("errorLoginPassMessage");
		return ConfigurationManager.getProperty("page.index");
	}

}
