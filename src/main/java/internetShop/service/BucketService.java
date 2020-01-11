package internetShop.service;

import internetShop.model.Bucket;
import internetShop.model.Item;
import internetShop.model.User;

import java.util.List;
import java.util.Optional;

public interface BucketService {
    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    Bucket update(Bucket bucket);

    boolean delete(Long bucketId);

    boolean delete(Bucket bucket);

    void addItem(Bucket bucket, Item item);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);

}
