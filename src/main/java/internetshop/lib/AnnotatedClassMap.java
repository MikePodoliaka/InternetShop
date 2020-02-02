package internetshop.lib;

import java.util.HashMap;
import java.util.Map;
import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.factory.Factory;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static  {
        classMap.put(BucketDao.class, Factory.getBucketDaoInstance());
        classMap.put(ItemDao.class, Factory.getItemDaoInstance());
        classMap.put(OrderDao.class, Factory.getOrderDaoInstance());
        classMap.put(UserDao.class, Factory.getUserDaoInstance());

        classMap.put(BucketService.class, Factory.getBucketServiceInstance());
        classMap.put(ItemService.class, Factory.getItemServiceInstance());
        classMap.put(OrderService.class, Factory.getOrderServiceInstance());
        classMap.put(UserService.class, Factory.getUserServiceInstance());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}