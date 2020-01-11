package internetShop.dao;

import internetShop.model.Item;

import java.util.Optional;

public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long itemId);

    Item update(Item item);

    boolean delete(Long itemId);

    Item delete(Item item);
}
