package internetShop.model;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private static Long idGenerator = 0L;

    private Long userId;
    private Long bucketId;
    private List<Item> items;

    public Bucket(Long userId) {
        this.userId = userId;
        bucketId = idGenerator++;
        items = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "userId=" + userId +
                ", bucketId=" + bucketId +
                ", items=" + items +
                '}';
    }
}
