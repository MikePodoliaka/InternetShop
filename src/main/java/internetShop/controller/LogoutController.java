package internetShop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutController extends HttpServlet {
    private static final String COOKIE = "LogPM";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("LogPM")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}