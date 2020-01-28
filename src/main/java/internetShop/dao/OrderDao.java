package internetShop.dao;

import internetShop.model.Order;

import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> get(Long orderId);

    Optional<Order> update(Order order);

    void delete(Long orderId);


}
