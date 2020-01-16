package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Role;
import internetShop.model.User;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setLogin("user");
        user.setPassword("1");
        user.addRole(Role.of("USER"));
        userService.create(user);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("1");
        admin.addRole(Role.of("ADMIN"));
        userService.create(admin);

        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
