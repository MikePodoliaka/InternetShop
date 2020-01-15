package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Order;
import internetShop.model.User;
import internetShop.service.OrderService;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllUserOrdersController extends HttpServlet {

    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user=userService.get(userId);
        List<Order> orderList=orderService.getOrdersForUser(userId);

        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("/WEB-INF/views/userOrders.jsp").forward(req,resp);

    }
}
