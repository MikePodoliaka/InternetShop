package internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.lib.anotations.Inject;
import internetshop.model.Item;
import internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class AddItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;

    private Logger logger = Logger.getLogger(AddItemController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Item item = new Item();
        item.setName(req.getParameter("name"));
        item.setPrice(Double.valueOf(req.getParameter("price")));
        try {
            itemService.create(item);
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("item", item);
        req.getRequestDispatcher("/WEB-INF/views/item.jsp").forward(req, resp);
    }
}