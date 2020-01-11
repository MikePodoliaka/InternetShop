package internetShop.service.impl;

import internetShop.lib.Inject;
import internetShop.lib.Service;
import internetShop.dao.BucketDao;
import internetShop.dao.ItemDao;
import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.service.BucketService;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        Optional<Bucket> bucket = bucketDao.get(bucketId);
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long bucketId) {
        return bucketDao.deleteById(bucketId);
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        Bucket newBucket = bucketDao.get(bucket.getBucketId()).get();
        newBucket.getItems().add(item);
        bucketDao.update(newBucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        Bucket newBucket = bucketDao.get(bucket.getBucketId()).get();
        List<Item> itemOfBucket = newBucket.getItems();
        itemOfBucket.remove(item);
        bucketDao.update(newBucket);
    }

    @Override
    public void clear(Bucket bucket) {
        Bucket tempBucket = bucketDao.get(bucket.getBucketId()).get();
        tempBucket.getItems().clear();
        bucketDao.update(tempBucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getBucketId()).get().getItems();
    }
}
