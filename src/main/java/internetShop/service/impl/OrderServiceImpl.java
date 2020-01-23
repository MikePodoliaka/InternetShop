package internetShop.service.impl;

import internetShop.lib.Inject;
import internetShop.lib.Service;
import internetShop.dao.OrderDao;
import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.Order;
import internetShop.model.User;
import internetShop.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public Optional<Order> update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long orderId) {

         orderDao.delete(orderId);
    }

    @Override
    public Order completeOrder(List<Item>items, User user) {
        Order order = new Order(items, user.getUserId());
        order.setItems(items);
        orderDao.create(order);
        return order;
    }

    @Override
    public List<Order> getOrdersForUser(Long userID) {
        return null;
    }
}
