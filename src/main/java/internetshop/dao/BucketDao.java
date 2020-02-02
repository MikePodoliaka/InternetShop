package internetshop.dao;

import java.util.Optional;

import internetshop.exceptions.DataProcessingExeption;
import internetshop.model.Bucket;

public interface BucketDao extends GenericDao<Bucket, Long> {

    Optional<Bucket> getByUserId(Long userId) throws DataProcessingExeption;

    void clear(Bucket bucket) throws DataProcessingExeption;
}