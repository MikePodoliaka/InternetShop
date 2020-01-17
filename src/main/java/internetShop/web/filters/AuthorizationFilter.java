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

public class AuthorizationFilter implements Filter {

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();
    private static final String COOKIE = "LogPM";

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/allUser", ADMIN);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        Role.RoleName roleName = protectedUrls.get(req.getServletPath());// Check it roleNameAdmin and roleNameUser
        Role.RoleName roleNameAdmin=protectedUrls.get(servletRequest); //requestUrl
        Role.RoleName roleNameUser=protectedUrls.get(servletRequest); //requestUrl
        Role.RoleName roleNameUser1=protectedUrls.get(servletRequest); //requestUrl
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