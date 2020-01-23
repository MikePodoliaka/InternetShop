package internetShop.dao.impl;

import internetShop.dao.ItemDao;
import internetShop.lib.Dao;
import internetShop.model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao {
    private static String TABLE = "items";

    public ItemDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item entity) {
        String query = String.format("INSERT INTO %s(name, price) VALUES('%s', %f)",
                TABLE, entity.getName(), entity.getPrice());

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Can't create item");
        }
        return entity;
    }

    @Override
    public Optional<Item> get(Long entityId) {
        String query = String.format("SELECT * FROM %s WHERE item_id=%d", TABLE, entityId);

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Item item = new Item(rs.getString("name"),
                        rs.getDouble("price"));
                item.setItemId(rs.getLong("item_id"));
                return Optional.of(item);
            }
        } catch (SQLException e) {
            System.out.println("problem in getItemDAO");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Item> update(Item item) {
        String query = String.format("UPDATE items SATE name='%s', price=%f WHERE item_id=%d",
                item.getName(), item.getPrice(), item.getItemId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Can't update item");
        }
        return Optional.of(item);
    }

    @Override
    public boolean delete(Long itemId) {

        String query = String.format("DELETE FROM items WHERE item_id=%d", itemId);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Can't delete item");
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        List<Item> listItems = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", TABLE);
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Item item = new Item(rs.getString("name"),
                        rs.getDouble("price"));
                item.setItemId(rs.getLong("item_id"));
                listItems.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Can't get items");
        }
        return listItems;
    }
}
