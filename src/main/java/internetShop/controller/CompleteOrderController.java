package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.Order;
import internetShop.model.User;
import internetShop.service.BucketService;
import internetShop.service.OrderService;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CompleteOrderController extends HttpServlet {

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    @Inject
    private static OrderService orderService;

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user = userService.get(userId);
        Bucket bucket = bucketService.getByUserId(userId);
        Order order = orderService.completeOrder(bucket.getItems(), user);
        bucketService.clear(bucket);

        List<Item> itemList = orderService.get(order.getOrderId()).get().getItems();
        req.setAttribute("items", itemList);
        req.setAttribute("order_id", order.getOrderId());

        req.getRequestDispatcher("WEB-INF/views/order.jsp").forward(req, resp);
    }
}
