package by.academy.it.rentacar.filters;

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
//public class AdminAccessFilter implements Filter {
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpSession session = req.getSession();
//		int access = Integer.parseInt(session.getAttribute("access").toString());
//		session.removeAttribute("addCarMessage");
//
//		if (access != 2) {
//			session.setAttribute("errorLoginPassMessage", ErrorManager.getInstance().getProperty("error.access"));
//			ConfigurationManager.getInstance();
//			RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty("page.main"));
//			dispatcher.forward(request, response);
//			return;
//		}
//		chain.doFilter(request, response);
//
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//	}
//
//}
