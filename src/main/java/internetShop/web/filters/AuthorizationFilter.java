package internetShop.web.filters;

import internetShop.lib.Inject;
import internetShop.model.Role;
import internetShop.model.User;
import internetShop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static internetShop.model.Role.RoleName.ADMIN;
import static internetShop.model.Role.RoleName.USER;

public class AuthorizationFilter implements Filter {

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();
    private static final String EMPTY_STRING="";

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //protectedUrls.put("/allUser", ADMIN);
        //protectedUrls.put("/addItem", ADMIN);
        //protectedUrls.put("/getAllItems", USER);
        //protectedUrls.put("/userOrders", USER);
        //protectedUrls.put("/bucket", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestedUrl = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);

        Role.RoleName roleName = protectedUrls.get(requestedUrl);
        if (roleName == null) {
            processAuthorization(filterChain, req, resp);
            return;
        }

        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userService.get(userId);
        if (verifyRole(user, roleName)) {
            processAuthorization(filterChain, req, resp);
            return;
        } else {
            processDenied(req, resp);
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream().anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private void processAuthorization(FilterChain filterChain, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        filterChain.doFilter(req, resp);
    }

}