package by.academy.it.rentacar.filters;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter ConfigFilter filters all actions and pages
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class ConfigFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("user");
		session.removeAttribute("errorLoginPassMessage");
		session.removeAttribute("errorSearchCarMessage");
		session.removeAttribute("errorFilterCarMassager");
		
		if (user == null) {
			session.setAttribute("access", 0);
			
			User userGuest = new User();
			userGuest.setName("Гость");
			userGuest.setAccess(0);
			userGuest.setType(TypeUser.GUEST);
			session.setAttribute("user", userGuest);
			
			if (session.isNew()) {
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("page.main"));
				dispatcher.forward(req, resp);
				return;
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filter) throws ServletException {
	}

	public void destroy() {
	}
}
