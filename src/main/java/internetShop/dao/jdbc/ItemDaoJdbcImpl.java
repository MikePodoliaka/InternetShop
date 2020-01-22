package internetShop.dao.jdbc;

import internetShop.dao.ItemDao;
import internetShop.lib.Dao;
import internetShop.model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static String DB_NAME = "internetshop";
    private static String TABLE = "items";

    public ItemDaoJdbcImpl(Connection connection) {
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
    /*public Optional<Item> get(Long itemId) {
        Statement stmt = null;
        String query = String.format("SELECT * FROM internetshop.items where item_id=%d;", itemId);
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                long Id = rs.getLong("item_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            System.out.println("problem in getItemDAO");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Can't close getItemDao");
                }
            }
        }
        return Optional.empty();
    } */

    @Override
    public Optional<Item> update(Item item) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long itemId) {
        return false;
    }

    @Override
    public Item delete(Item item) {
        return null;
    }
}
