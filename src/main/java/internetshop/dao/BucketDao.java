package internetshop.dao;

import java.util.Optional;

import internetshop.exceptions.DataProcessingException;
import internetshop.model.Bucket;

public interface BucketDao extends GenericDao<Bucket, Long> {

    Optional<Bucket> getByUserId(Long userId) throws DataProcessingException;

    void clear(Bucket bucket) throws DataProcessingException;
}