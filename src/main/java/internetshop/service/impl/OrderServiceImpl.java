package internetshop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import internetshop.dao.OrderDao;
import internetshop.exceptions.DataProcessingExeption;
import internetshop.lib.anotations.Inject;
import internetshop.lib.anotations.Service;
import internetshop.model.Item;
import internetshop.model.Order;
import internetshop.model.User;
import internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order completeOrder(List<Item> items, User user) throws DataProcessingExeption {
        Order order = new Order();
        order.setItems(new ArrayList<>(items));
        order.setUserId(user.getId());
        return orderDao.create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) throws DataProcessingExeption {
        return orderDao.getUserOrders(user);
    }

    @Override
    public Order create(Order order) throws DataProcessingExeption {
        return orderDao.create(order);
    }

    @Override
    public Order get(Long orderId) throws DataProcessingExeption {
        return orderDao.get(orderId)
                .orElseThrow(() -> new NoSuchElementException("Can't find order with id "
                        + orderId));
    }

    @Override
    public Order update(Order order) throws DataProcessingExeption {
        return orderDao.update(order);
    }

    @Override
    public boolean deleteById(Long orderId) throws DataProcessingExeption {
        return orderDao.deleteById(orderId);
    }

    @Override
    public boolean delete(Order order) throws DataProcessingExeption {
        return orderDao.delete(order);
    }

    @Override
    public List<Order> getAll() throws DataProcessingExeption {
        return orderDao.getAll();
    }
}