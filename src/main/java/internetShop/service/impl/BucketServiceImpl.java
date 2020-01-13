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
    public Optional<Bucket> update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long bucketId) {
        return bucketDao.deleteById(bucketId);
    }



    @Override
    public void addItem(Long bucketId, Long itemId) {
        Bucket newBucket = bucketDao.get(bucketId).get();
        Item newItem=itemDao.get(itemId).get();
        newBucket.getItems().add(newItem);
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
        bucketDao.delete(bucket.getBucketId());
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getBucketId()).get().getItems();
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public Bucket getByUserId(Long userId) {
        return bucketDao.getAll().stream()
                .filter(u->u.getUserId().equals(userId))
                .findFirst()
                .orElse(create(new Bucket(userId)));
    }
}
