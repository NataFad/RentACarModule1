package by.academy.it.rentacar.filters;

import by.academy.it.rentacar.managers.ConfigurationManager;
import by.academy.it.rentacar.managers.ErrorManager;
import by.academy.it.rentacar.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**Filter AdminAccessFilter
 * 
 * Servlet Filter implementation class AdminAccessFilter
 * Filter AdminAccessFilter filters the actions of admin
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class AdminAccessFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		int access = Integer.parseInt(session.getAttribute("access").toString());
		session.removeAttribute("addCarMessage");
		
		if (access != 2) {
			session.setAttribute("errorLoginPassMessage", ErrorManager.getInstance().getProperty("error.access"));
			ConfigurationManager.getInstance();
			RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty("page.main"));
			dispatcher.forward(request, response);
			return;
		}
		chain.doFilter(request, response);
		HibernateUtil.getInstance().closeSession();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
