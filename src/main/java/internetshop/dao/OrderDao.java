package internetshop.dao;

import java.util.List;

import internetshop.exceptions.DataProcessingException;
import internetshop.model.Order;
import internetshop.model.User;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getUserOrders(User user) throws DataProcessingException;
}