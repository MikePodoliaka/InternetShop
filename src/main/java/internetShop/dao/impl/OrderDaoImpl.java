package internetShop.dao.impl;

import internetShop.dao.OrderDao;
import internetShop.lib.Dao;
import internetShop.lib.Inject;
import internetShop.model.Item;
import internetShop.model.Order;
import internetShop.service.ItemService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static String TABLE = "orders";
    @Inject
    private static ItemService itemService;

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders (user_id) VALUES(?);";
        try (PreparedStatement statement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, order.getUserId().toString());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to crate order");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Error obtaining order id ");
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't create orders");
        }
        for (int i = 0; i < order.getItems().size(); i++) {
            query = "INSERT INTO orders_items (orders_id, items_id) values (?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, order.getOrderId().toString());
                preparedStatement.setString(2, order.getItems().get(i).getItemId().toString());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Can't create orders");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        Order order = new Order();
        String query = "SELECT * FROM ORDERS WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order.setUserId(resultSet.getLong("user_id"));
            order.setOrderId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        order.setItems(getAllItems(orderId));
        return Optional.of(order);
    }

    private List<Item> getAllItems(Long orderId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT item_id FROM orders_items WHERE order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long itemId = resultSet.getLong("item_id");
                items.add(itemService.get(itemId));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Can't get all Items");
        }
        return items;
    }

    @Override
    public Optional<Order> update(Order order) {

        String removeOrders = "DELETE FROM orders_items where order_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeOrders)) {
            preparedStatement.setLong(1, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "insert into orders_items (order_id, item_id) VALUE(?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, order.getOrderId());
            for (Item item : order.getItems()) {
                preparedStatement.setLong(2, item.getItemId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(order);
    }

    @Override
    public void delete(Long orderId) {
        String query = "DELETE FROM orders WHERE order_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
