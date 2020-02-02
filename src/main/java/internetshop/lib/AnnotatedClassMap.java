package internetshop.lib;

import java.util.HashMap;
import java.util.Map;
import internetshop.dao.BucketDao;
import internetshop.dao.ItemDao;
import internetshop.dao.OrderDao;
import internetshop.dao.UserDao;
import internetshop.factory.DaoAndServiceFactory;
import internetshop.service.BucketService;
import internetshop.service.ItemService;
import internetshop.service.OrderService;
import internetshop.service.UserService;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static  {
        classMap.put(BucketDao.class, DaoAndServiceFactory.getBucketDaoInstance());
        classMap.put(ItemDao.class, DaoAndServiceFactory.getItemDaoInstance());
        classMap.put(OrderDao.class, DaoAndServiceFactory.getOrderDaoInstance());
        classMap.put(UserDao.class, DaoAndServiceFactory.getUserDaoInstance());

        classMap.put(BucketService.class, DaoAndServiceFactory.getBucketServiceInstance());
        classMap.put(ItemService.class, DaoAndServiceFactory.getItemServiceInstance());
        classMap.put(OrderService.class, DaoAndServiceFactory.getOrderServiceInstance());
        classMap.put(UserService.class, DaoAndServiceFactory.getUserServiceInstance());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}