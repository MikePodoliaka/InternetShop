package internetshop.dao;

import java.util.List;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.model.Order;
import internetshop.model.User;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(User user) throws DataProcessingExeption;
}