package internetShop.service;

import internetShop.model.Bucket;
import internetShop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketService {
    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    Optional<Bucket> update(Bucket bucket);

    void delete(Long bucketId);



    void addItem(Long bucketId, Long itemId);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);

    List<Bucket> getAll();

    Bucket getByUserId(Long userId);

}


