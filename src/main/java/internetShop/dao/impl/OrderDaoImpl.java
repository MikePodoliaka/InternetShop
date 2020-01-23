package internetShop.dao.impl;

import internetShop.dao.OrderDao;
import internetShop.lib.Dao;
import internetShop.model.Order;

import java.sql.Connection;
import java.util.Optional;

@Dao
public class OrderDaoImpl extends AbstractDao <Order>implements OrderDao {
    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(Order order) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long orderId) {
        return false;
    }
}
