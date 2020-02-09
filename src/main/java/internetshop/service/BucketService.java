package internetshop.service;

import internetshop.exceptions.DataProcessingException;
import internetshop.model.Bucket;
import internetshop.model.Item;

public interface BucketService extends GenericService<Bucket, Long> {

    void addItem(Bucket bucket, Item item) throws DataProcessingException;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingException;

    void clear(Bucket bucket) throws DataProcessingException;

    Bucket getByUserId(Long userId) throws DataProcessingException;
}