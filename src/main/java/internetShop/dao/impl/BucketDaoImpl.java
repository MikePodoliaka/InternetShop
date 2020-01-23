package internetShop.dao.impl;

import internetShop.dao.BucketDao;
import internetShop.lib.Dao;
import internetShop.model.Bucket;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@Dao
public class BucketDaoImpl extends AbstractDao <Bucket> implements BucketDao {
    public BucketDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        return null;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Optional.empty();
    }

    @Override
    public List<Bucket> getAll() {
        return null;
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long bucketId) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }
}
