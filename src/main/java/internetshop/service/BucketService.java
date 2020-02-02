package internetshop.service;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.model.Bucket;
import internetshop.model.Item;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Item item) throws DataProcessingExeption;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingExeption;

    void clear(Bucket bucket) throws DataProcessingExeption;

    Bucket getByUserId(Long userId) throws DataProcessingExeption;
}