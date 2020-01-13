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

public class GetOrderToUserIdController extends HttpServlet {
    private static Long USER_ID=1L;
    @Inject
    private static UserService userService;
    @Inject
    private static OrderService orderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user=userService.get(USER_ID);
        List<Order> orderList=orderService.getOrdersForUser(USER_ID);

        req.setAttribute("orders", orderList);
        req.getRequestDispatcher("/WEB-INF/views/userOrder.jsp").forward(req,resp);

    }
}
