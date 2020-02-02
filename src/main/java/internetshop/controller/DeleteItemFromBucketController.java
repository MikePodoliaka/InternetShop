package internetshop.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.lib.anotations.Inject;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import org.apache.log4j.Logger;

public class DeleteItemFromBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Inject
    private static ItemService itemService;

    private Logger logger = Logger.getLogger(DeleteItemFromBucketController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            Item item = itemService.get(Long.valueOf(req.getParameter("item_id")));
            bucketService.deleteItem(bucket, item);
        } catch (DataProcessingExeption dataProcessingExeption) {
            logger.error(dataProcessingExeption);
            req.setAttribute("errorMsg", dataProcessingExeption.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/servlet/bucket");
    }
}