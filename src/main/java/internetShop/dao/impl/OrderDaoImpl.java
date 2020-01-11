package internetShop.dao.impl;

import internetShop.lib.Dao;
import internetShop.dao.OrderDao;
import internetShop.dao.Storage;
import internetShop.model.Order;
import internetShop.web.IdGenerator;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Order newOrder=order;
        newOrder.setOrderId(IdGenerator.getNewOrderId());
        Storage.orders.add(newOrder);
        return newOrder;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return Optional.ofNullable(Storage.orders.stream()
                .filter(i -> i.getOrderId()
                        .equals(orderId)).
                        findFirst().orElseThrow(() ->
                        new NoSuchElementException("Can't find order with Id " + orderId)));
    }

    @Override
    public Order update(Order order) {
        int orderPos = 0;
        for (Order o : Storage.orders) {
            if (o.getOrderId().equals(order.getOrderId())) {
                break;
            }
            orderPos++;
        }
        Storage.orders.set(orderPos, order);
        return order;
    }

    @Override
    public boolean delete(Long orderId) {
        Optional opOder = Optional.ofNullable(Storage.orders.stream()
                .filter(i -> i.getOrderId().equals(orderId))
                .findFirst());

        return Storage.orders.remove(opOder);
    }
}
