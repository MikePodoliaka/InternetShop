package internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import internetshop.dao.BucketDao;
import internetshop.exceptions.DataProcessingExeption;
import internetshop.lib.anotations.Inject;
import internetshop.lib.anotations.Service;
import internetshop.model.Bucket;
import internetshop.model.Item;
import internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingExeption {
        bucket.getItems().add(item);
        bucketDao.update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingExeption {
        List<Item> items = bucket.getItems();
        items.remove(item);
        bucketDao.update(bucket);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingExeption {
        bucketDao.clear(bucket);
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingExeption {
        Optional<Bucket> optionalBucket = bucketDao.getByUserId(userId);
        if (optionalBucket.isPresent()) {
            return optionalBucket.get();
        }
        Bucket bucket = new Bucket();
        bucket.setUserId(userId);
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingExeption {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) throws DataProcessingExeption {
        return bucketDao.get(bucketId)
                .orElseThrow(() -> new NoSuchElementException("Can't find bucket with id "
                        + bucketId));
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingExeption {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean deleteById(Long bucketId) throws DataProcessingExeption {
        return bucketDao.deleteById(bucketId);
    }

    @Override
    public boolean delete(Bucket bucket) throws DataProcessingExeption {
        return bucketDao.delete(bucket);
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingExeption {
        return bucketDao.getAll();
    }
}