package internetShop.controller;

import internetShop.exeptions.AuthorizationException;
import internetShop.lib.Inject;
import internetShop.model.User;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("psw");
        try {
            User user=userService.login(login, password);

            HttpSession session=req.getSession(true);
            session.setAttribute("userId", user.getUserId());

            Cookie cookie=new Cookie("LogPM",user.getToken());
            resp.addCookie(cookie);
            resp.sendRedirect (req.getContextPath()+"/index");

        } catch (AuthorizationException e) {
            e.printStackTrace();
            req.setAttribute("errorMsg","Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }

    }
}
