package internetShop.lib;

import internetShop.dao.BucketDao;
import internetShop.dao.ItemDao;
import internetShop.dao.OrderDao;
import internetShop.dao.UserDao;
import internetShop.service.BucketService;
import internetShop.service.ItemService;
import internetShop.service.OrderService;
import internetShop.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class AnnotatedClassMap {

    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
        classMap.put(UserDao.class, Factory.getUserDao());

        classMap.put(UserService.class, Factory.getUserService());
        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(OrderService.class, Factory.getOrderService());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
