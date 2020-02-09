package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internetshop.exceptions.DataProcessingException;
import internetshop.lib.anotations.Inject;
import internetshop.service.OrderService;
import org.apache.log4j.Logger;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    private Logger logger = Logger.getLogger(DeleteOrderController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long userId = Long.valueOf(req.getParameter("order_id"));
            orderService.deleteById(userId);
        } catch (DataProcessingException dataProcessingException) {
            logger.error(dataProcessingException);
            req.setAttribute("errorMsg", dataProcessingException.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/allUserOrders");
    }
}