package internetShop.controller.item;

import internetShop.lib.Inject;
import internetShop.model.Item;
import internetShop.model.User;
import internetShop.service.ItemService;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("items", itemService.getAllItems());
        req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req,resp);
    }
}
