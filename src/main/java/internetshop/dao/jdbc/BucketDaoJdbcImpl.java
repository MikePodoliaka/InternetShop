package internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import internetshop.dao.BucketDao;
import internetshop.exceptions.DataProcessingException;
import internetshop.lib.anotations.Dao;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.model.Order;

@Dao
public class BucketDaoJdbcImpl extends AbcstractDao<Bucket> implements BucketDao {
    private static final String DB_NAME = "internetShop_db";
    private static final String BUCKET_TABLE = "bucket";
    private static final String BUCKET_ITEMS_TABLE = "bucket_item";
    private static final String ITEMS_TABLE = "items";

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) throws DataProcessingException {
        String query = String.format(Locale.ROOT,
                "select buckets.bucket_id, buckets.user_id, "
                        + "items.item_id, items.name, items.price\n"
                        + "from %1$s.buckets\n"
                        + "left join %1$s.buckets_items using(bucket_id)\n"
                        + "left join %1$s.items using(item_id)\n"
                        + "where user_id = ?", DB_NAME);
        return getBucket(userId, query);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        String clearBucketQuery = String.format(Locale.ROOT,
                "delete from %s.buckets_items where bucket_id = ?",
                DB_NAME);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(clearBucketQuery)) {
            preparedStatement.setLong(1, bucket.getBucketId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cant't clear bucket wit id = "
                    + bucket.getBucketId(), e);
        }
    }

    @Override
    public Bucket create(Bucket entity) throws DataProcessingException {
        String insertBucketQuery = String.format(Locale.ROOT,
                "INSERT INTO %s.buckets (user_id) VALUES (?)",
                DB_NAME);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(insertBucketQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getUserId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setBucketId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cant't create bucket with user_id = "
                    + entity.getUserId(), e);
        }
        addItemsIntoBucketsItemsTable(entity);
        return entity;
    }

    private void addItemsIntoBucketsItemsTable(Bucket entity) throws DataProcessingException {
        String insertBucketItemQuery = String.format("INSERT INTO %s.buckets_items "
                + "(bucket_id, item_id) VALUES (?, ?)", DB_NAME);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(insertBucketItemQuery)) {
            preparedStatement.setLong(1, entity.getBucketId());
            for (Item item : entity.getItems()) {
                preparedStatement.setLong(2, item.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cant't add items to bucket wit id = "
                    + entity.getBucketId(), e);
        }
    }

    @Override
    public Optional<Bucket> get(Long entityId) throws DataProcessingException {
        String query = String.format(Locale.ROOT,
                "select buckets.buckets_id, buckets.user_id, items.item_id, items.name, items.price\n"
                        + "from %1$s.orders\n"
                        + "join %1$s.buckets_items using(bucket_id)\n"
                        + "join %1$s.items using(item_id)\n"
                        + "where bucket_id = ?", DB_NAME);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Bucket bucket = null;
            while (resultSet.next()) {
                if (bucket == null) {
                    bucket = new Bucket();
                    bucket.setUserId(resultSet.getLong("user_id"));
                    bucket.setBucketId(resultSet.getLong("order_id"));
                }
                Item item = new Item();
                item.setId(resultSet.getLong("item_id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                bucket.getItems().add(item);
            }
            return Optional.ofNullable(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create bucket with bucket_id = "
                    + entityId, e);
        }
    }

    private Optional<Bucket> getBucket(Long entityId, String query) throws DataProcessingException {
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Bucket bucket = null;
            while (resultSet.next()) {
                if (bucket == null) {
                    bucket = new Bucket();
                    bucket.setUserId(resultSet.getLong("user_id"));
                    bucket.setBucketId(resultSet.getLong("bucket_id"));
                }
                if (resultSet.getString("name") != null) {
                    Item item = new Item();
                    item.setId(resultSet.getLong("item_id"));
                    item.setName(resultSet.getString("name"));
                    item.setPrice(resultSet.getDouble("price"));
                    bucket.getItems().add(item);
                }
            }
            return Optional.ofNullable(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Cant't get bucket wit bucket_id = "
                    + entityId, e);
        }
    }

    @Override
    public Bucket update(Bucket entity) throws DataProcessingException {
        String updateBucketQuery = String.format(Locale.ROOT,
                "UPDATE %s.buckets SET user_id = ? WHERE (bucket_id = ?)", DB_NAME);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(updateBucketQuery)) {
            preparedStatement.setLong(1, entity.getUserId());
            preparedStatement.setLong(2, entity.getBucketId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clear(entity);
        addItemsIntoBucketsItemsTable(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long entityId) throws DataProcessingException {
        String query = String.format(" delete buckets, buckets_items from %1$s.buckets\n"
                + "left join %1$s.buckets_items using(bucket_id)\n"
                + "where bucket_id = ?", DB_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, entityId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Cant't delete bucket wit id = "
                    + entityId, e);
        }
    }

    @Override
    public boolean delete(Bucket entity) throws DataProcessingException {
        return deleteById(entity.getBucketId());
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        String getAllBucketsQuery = String.format(Locale.ROOT,
                "select buckets.bucket_id, buckets.user_id, "
                        + "items.item_id, items.name, items.price\n"
                        + "from %1$s.buckets\n"
                        + "join %1$s.buckets_items using(bucket_id)\n"
                        + "join %1$s.items using(item_id)\n"
                        + "order by buckets.bucket_id;", DB_NAME);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(getAllBucketsQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Bucket> bucketListResult = new ArrayList<>();
            Bucket bucket = null;
            while (resultSet.next()) {
                if (bucket == null) {
                    bucket = new Bucket();
                }
                if (bucket.getBucketId() != null
                        && !bucket.getBucketId().equals(resultSet.getLong("bucket_id"))) {
                    bucketListResult.add(bucket);
                    bucket = new Bucket();
                }
                if (bucket.getBucketId() == null) {
                    bucket.setBucketId(resultSet.getLong("bucket_id"));
                    bucket.setUserId(resultSet.getLong("user_id"));
                }
                Item item = new Item();
                item.setId(resultSet.getLong("item_id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                bucket.getItems().add(item);
            }
            if (bucket != null) {
                bucketListResult.add(bucket);
            }
            return bucketListResult;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get All buckets", e);
        }
    }

}