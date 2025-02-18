package internetshop.service;

import java.util.List;

import internetshop.exceptions.DataProcessingException;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(List<Item> items, User user) throws DataProcessingException;

    List<Order> getUserOrders(User user) throws DataProcessingException;
}