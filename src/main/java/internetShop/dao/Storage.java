package internetShop.dao;

import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.Order;
import internetShop.model.User;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Item> items = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();
    public static final List<User> users = new ArrayList<>();
}
