package internetShop;

import internetShop.lib.Inject;
import internetShop.lib.Injector;

import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.User;
import internetShop.service.BucketService;
import internetShop.service.ItemService;
import internetShop.service.OrderService;
import internetShop.service.UserService;

public class Main {
    @Inject
    private static ItemService itemService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}


