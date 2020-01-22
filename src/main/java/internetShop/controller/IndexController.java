package internetShop.controller;

import internetShop.dao.jdbc.ItemDaoJdbcImpl;
import internetShop.lib.Inject;
import internetShop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req,resp);
    }
}
