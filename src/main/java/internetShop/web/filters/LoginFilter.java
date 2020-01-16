package internetShop.web.filters;


import internetShop.lib.Inject;
import internetShop.model.User;
import internetShop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class LoginFilter implements Filter {
    private static Logger logger = Logger.getLogger(String.valueOf(LoginFilter.class));
    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getCookies() == null) {
            processUnAuthenticated(req, resp);
            return;
        }
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("LogPM")) {
                Optional<User> user = userService.getByToken(cookie.getValue());
                if (user.isPresent()) {
                    logger.info("User" + user.get().getLogin() + "was authenticated");

                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }

            }
        }
        logger.info("User was not authenticated");
        processUnAuthenticated(req, resp);
    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
