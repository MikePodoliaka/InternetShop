package internetShop.dao;

import internetShop.model.Bucket;

import java.util.List;
import java.util.Optional;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    List<Bucket> getAll();

    Optional<Bucket> update(Bucket bucket);

    boolean deleteById(Long bucketId);

    boolean delete(Bucket bucket);

}
