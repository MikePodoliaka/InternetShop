package internetShop.controller;

import internetShop.lib.Inject;
import internetShop.model.Bucket;
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
    private static UserService userService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    public static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       Long userId= (Long) req.getSession(true).getAttribute("userId");
       Optional<User> user=userService.get(USER_ID);
       // Bucket bucket=user.

    }
}
