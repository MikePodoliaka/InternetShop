package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.User;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User newUser=new User();
        newUser.setLogin(req.getParameter("login"));
        newUser.setPassword(req.getParameter("psw"));
        newUser.setName(req.getParameter("user_name"));
        User user=userService.create(newUser);
        Cookie cookie=new Cookie("LogPM",user.getToken());
        resp.addCookie(cookie);
        resp.sendRedirect (req.getContextPath()+"/index");
    }
}
