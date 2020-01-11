package internetShop.service.impl;

import internetShop.lib.Inject;
import internetShop.lib.Service;
import internetShop.dao.OrderDao;
import internetShop.dao.Storage;
import internetShop.model.Bucket;
import internetShop.model.Order;
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
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long orderId) {
        return orderDao.delete(orderId);
    }

    @Override
    public Order completeOrder(Bucket bucket) {
        Order order = new Order(bucket.getUserId(), bucket.getItems());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public List<Order> getOrdersForUser(Long userID) {
        return Storage.orders.stream()
                .filter(i -> i.getOrderId().equals(userID))
                .collect(Collectors.toList());
    }
}
