package internetShop.service;

import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.Order;
import internetShop.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(Order order);

    Optional<Order> get(Long orderId);

    Optional<Order> update(Order order);

    void delete(Long orderId);


    Order completeOrder(List<Item>items, User user);


    List<Order> getOrdersForUser(Long userID);
}
