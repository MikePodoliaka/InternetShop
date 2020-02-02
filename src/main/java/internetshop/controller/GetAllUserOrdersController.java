package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.lib.anotations.Inject;
import internetshop.model.User;
import internetshop.service.OrderService;
import internetshop.service.UserService;
import org.apache.log4j.Logger;

public class GetAllUserOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    private Logger logger = Logger.getLogger(GetAllUserOrdersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        try {
            User user = userService.get(userId);
            req.setAttribute("orders", orderService.getUserOrders(user));
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/allUserOrders.jsp").forward(req, resp);
    }
}