package internetShop.lib;

import internetShop.dao.BucketDao;
import internetShop.dao.ItemDao;
import internetShop.dao.OrderDao;
import internetShop.dao.UserDao;
import internetShop.dao.impl.BucketDaoImpl;
import internetShop.dao.impl.ItemDaoImpl;
import internetShop.dao.impl.OrderDaoImpl;
import internetShop.dao.impl.UserDaoImpl;
import internetShop.service.BucketService;
import internetShop.service.ItemService;
import internetShop.service.OrderService;
import internetShop.service.UserService;
import internetShop.service.impl.BucketServiceImpl;
import internetShop.service.impl.ItemServiceImpl;
import internetShop.service.impl.OrderServiceImpl;
import internetShop.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Factory {
    private static Logger logger = Logger.getLogger(String.valueOf(Factory.class));
    private static Connection connection;
    private static String URL = "jdbc:mysql://localhost:3306/internetshop";
    private static String USER = "root";
    private static String PASSWORD = "5522";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Can't establish connection to our DB");
        }
    }

    private static ItemService itemServiceInstance;
    private static BucketService bucketServiceInstance;
    private static OrderService orderServiceInstance;
    private static UserService userServiceInstance;

    public static ItemService getItemService() {
        if (itemServiceInstance == null) {
            itemServiceInstance = new ItemServiceImpl();
        }
        return itemServiceInstance;
    }

    public static BucketService getBucketService() {
        if (bucketServiceInstance == null) {
            bucketServiceInstance = new BucketServiceImpl();
        }
        return bucketServiceInstance;
    }

    public static OrderService getOrderService() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderServiceImpl();
        }
        return orderServiceInstance;
    }

    public static UserService getUserService() {
        if (userServiceInstance == null) {
            userServiceInstance = new UserServiceImpl();
        }
        return userServiceInstance;
    }


    private static BucketDao bucketDaoInstance;

    public static BucketDao getBucketDao() {
        if (bucketDaoInstance == null) {
            bucketDaoInstance = new BucketDaoImpl(connection);
        }
        return bucketDaoInstance;
    }

    private static ItemDao itemDaoInstance;

    public static ItemDao getItemDao() {
        if (itemDaoInstance == null) {
            itemDaoInstance = new ItemDaoImpl(connection);
        }
        return itemDaoInstance;
    }

    private static OrderDao orderDaoInstance;

    public static OrderDao getOrderDao() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDaoImpl(connection);
        }
        return orderDaoInstance;
    }

    private static UserDao userDaoInstance;

    public static UserDao getUserDao() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoImpl(connection);
        }
        return userDaoInstance;
    }
}

