package internetShop.controller.item;

import internetShop.lib.Inject;
import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.User;
import internetShop.service.BucketService;
import internetShop.service.ItemService;
import internetShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AddItemToBucketController extends HttpServlet {

    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession(true).getAttribute("userId");
        User user = userService.get(userId);

        Bucket bucket = bucketService.getByUserId(userId);

        String itemId = req.getParameter("item_id");
        Item item = itemService.get(Long.valueOf(itemId));

        bucketService.addItem(bucket.getBucketId(), Long.valueOf(itemId));
        resp.sendRedirect(req.getContextPath() + "/bucket");

    }
}
