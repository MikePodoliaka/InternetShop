package internetShop.controller.user;


import internetShop.lib.Inject;
import internetShop.model.User;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AllUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("greeting", "LogPM");
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/allUser.jsp").forward(req, resp);
    }
}
