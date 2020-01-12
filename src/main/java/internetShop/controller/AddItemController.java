package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Item;
import internetShop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addItem.jsp").forward(req, resp);
    }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       Item item = new Item();
       item.setName(req.getParameter("item_name"));
       item.setPrice(Double.valueOf(req.getParameter("item_price")));
       itemService.create(item);

       resp.sendRedirect(req.getContextPath() + "/getAllItems");
   }
}
