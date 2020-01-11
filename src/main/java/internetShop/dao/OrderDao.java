package internetShop.dao;

import internetShop.model.Order;

import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long orderId);

    Order update(Order order);

    boolean delete(Long orderId);


}
