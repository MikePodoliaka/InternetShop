package internetShop.controller.order;

import internetShop.lib.Inject;
import internetShop.service.OrderService;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderController extends HttpServlet {

    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        orderService.delete(Long.valueOf(req.getParameter("order_id")));
        resp.sendRedirect(req.getContextPath()+"/userOrders");
    }
}
