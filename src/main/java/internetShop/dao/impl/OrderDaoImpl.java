package internetShop.dao.impl;

import internetShop.lib.Dao;
import internetShop.dao.OrderDao;
import internetShop.dao.Storage;
import internetShop.model.Order;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
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
    public Optional<Order> update(Order order) {
        Optional<Order> updateOrder=get(order.getOrderId());
        updateOrder.get().setOrderId(order.getOrderId());
        return updateOrder;
    }

    @Override
    public boolean delete(Long orderId) {
        Optional opOder = Optional.ofNullable(Storage.orders.stream()
                .filter(i -> i.getOrderId().equals(orderId))
                .findFirst());

        return Storage.orders.remove(opOder);
    }
}
