package internetShop.service;

import internetShop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item create(Item item);

    Item get(Long id);

    Optional<Item> update(Item item);

    void delete(Long id);



    List<Item> getAllItems();


}
