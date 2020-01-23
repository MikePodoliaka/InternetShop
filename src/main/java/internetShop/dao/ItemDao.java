package internetShop.dao;

import internetShop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long itemId);

    Optional<Item> update(Item item);

    boolean delete(Long itemId);

    List<Item> getAll();
}
