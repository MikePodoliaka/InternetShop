package internetShop.service;

import internetShop.model.Bucket;
import internetShop.model.Order;
import internetShop.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order create(Order order);

    Optional<Order> get(Long orderId);

    Order update(Order order);

    boolean delete(Long orderId);


    Order completeOrder(Bucket bucket);


    List<Order> getOrdersForUser(Long userID);
}
