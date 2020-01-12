package internetShop.dao.impl;

import internetShop.dao.BucketDao;
import internetShop.dao.Storage;
import internetShop.lib.Dao;
import internetShop.model.Bucket;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket create(Bucket bucket) {
       Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        return Optional.ofNullable(Storage.buckets
                .stream()
                .filter(b -> b.getBucketId().equals(bucketId))
                .findFirst()
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find bucket with id: "
                        + bucketId)));
    }

    @Override
    public Optional<Bucket> update(Bucket bucket) {
       Optional<Bucket> updateBucket=get(bucket.getBucketId());
       updateBucket.get().setItems(bucket.getItems());
        return updateBucket;
    }

    @Override
    public boolean deleteById(Long bucketId) {
        Optional<Bucket> optionalBucket = Optional.ofNullable(Storage.buckets
                .stream()
                .filter(i -> i.getBucketId().equals(bucketId))
                .findFirst().get());
        if (optionalBucket.isPresent()) {
            Storage.buckets.remove(optionalBucket.get());
            return Storage.buckets.remove(optionalBucket.get());
        }
        return false;
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}

