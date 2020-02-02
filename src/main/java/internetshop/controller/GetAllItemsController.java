package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.lib.anotations.Inject;
import internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class GetAllItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    private Logger logger = Logger.getLogger(GetAllItemsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("items", itemService.getAll());
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/allItems.jsp").forward(req, resp);
    }
}