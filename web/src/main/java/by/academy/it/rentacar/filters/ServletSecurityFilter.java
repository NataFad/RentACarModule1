package by.academy.it.rentacar.filters;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class ServletSecurityFilter implements Filter {
    static Logger log = Logger.getLogger(ServletSecurityFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("ServletSecurityFilter used...");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        TypeUser type = (TypeUser) session.getAttribute("userType");
        if (type == null) {
            type = TypeUser.GUEST;
            User userGuest = new User();
            userGuest.setName("Гость");
            userGuest.setAccess(type.getValue());
            session.setAttribute("user", userGuest);
            session.setAttribute("userType", type);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
