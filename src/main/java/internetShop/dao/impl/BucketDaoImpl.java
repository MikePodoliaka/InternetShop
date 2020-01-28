package internetShop.dao.impl;

import internetShop.dao.BucketDao;
import internetShop.lib.Dao;
import internetShop.lib.Inject;
import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.service.ItemService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class BucketDaoImpl extends AbstractDao<Bucket> implements BucketDao {
    public BucketDaoImpl(Connection connection) {
        super(connection);
    }

    @Inject
    private static ItemService itemService;

    @Override
    public Bucket create(Bucket bucket) {
        String query = "INSERT INTO buckets (user_id) VALUE (?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bucket.getUserId().toString());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    bucket.setBucketId(resultSet.getLong(1));
                } else {
                    throw new SQLException("Error obtaining bucket id ");
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't create bucket");
        }
        for (int i = 0; i < bucket.getItems().size(); i++) {
            query = "INSERT INTO buckets_items (buckets_id, items_id) values (?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bucket.getBucketId().toString());
                preparedStatement.setString(2, bucket.getItems().get(i).getItemId().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        String query = "SELECT bucket_id, user_id FROM buckets where bucket_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Bucket bucket = null;
            if (resultSet.next()) {
                bucket = new Bucket(resultSet.getLong(2));
                bucket.setBucketId(resultSet.getLong(1));
                bucket.setItems(getAllItemFromBucket(bucket.getBucketId()));
                return Optional.of(bucket);
            }
        } catch (SQLException e) {
            System.out.println("Can't get bucket by ID");
        }
        return Optional.empty();
    }

    private List<Item> getAllItemFromBucket(Long bucketId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT item_id FROM buckets_items WHERE bucket_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
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
    public List<Bucket> getAll() {
        List<Bucket> bucketsList = new ArrayList<>();
        String query = "SELECT bucket_id, user_id FROM buckets;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long bucketId = resultSet.getLong(1);
                Long userID = resultSet.getLong(2);
                Bucket bucket = new Bucket(userID);
                bucket.setBucketId(bucketId);
                bucket.setItems(getAllItemFromBucket(bucketId));
                bucketsList.add(bucket);
            }

        } catch (SQLException e) {
            System.out.println("Can't get all buckets");
        }
        return bucketsList;
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {

        String updateBucket = "DELETE FROM buckets_items where buckets_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateBucket)) {
            preparedStatement.setLong(1, bucket.getBucketId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "insert into buckets_items (buckets_id, items_id) VALUE(?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucket.getBucketId());
            for (Item item : bucket.getItems()) {
                preparedStatement.setLong(2, item.getItemId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(bucket);
    }


    @Override
    public void deleteById(Long bucketId) {
        String query = "DELETE FROM buckets WHERE bucket_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, bucketId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {

    }
}
