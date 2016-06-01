package by.academy.it.rentacar.filters;

import by.academy.it.rentacar.enums.TypeUser;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*", "/login", "/exit"})
public class ServletSecurityFilter implements Filter {
    static Logger log = Logger.getLogger(ServletSecurityFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.debug("ServletSecurityFilter used...");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        TypeUser type = (TypeUser) session.getAttribute("userType");
        if (type == null) {
            type = TypeUser.GUEST;
            session.setAttribute("userType", type);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
