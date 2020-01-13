package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.service.BucketService;
import internetShop.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemFromBucket extends HttpServlet {
    private static Long USER_ID = 1L;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Bucket bucket = bucketService.getByUserId(USER_ID);
        String itemID = req.getParameter("item_id");
        Item item = itemService.get(Long.valueOf(itemID));

        bucketService.deleteItem(bucket,item);
        resp.sendRedirect(req.getContextPath()+"/bucket");


    }
}
