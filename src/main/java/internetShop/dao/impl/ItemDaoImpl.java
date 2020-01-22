package internetShop.dao.impl;

import internetShop.lib.Dao;
import internetShop.dao.ItemDao;
import internetShop.dao.Storage;
import internetShop.model.Item;

import java.util.NoSuchElementException;
import java.util.Optional;


public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
       Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Optional.ofNullable(Storage.items
                .stream()
                .filter(i -> i.getItemId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't  find item with id " + id)));
    }

    @Override
    public Optional<Item> update(Item item) {
       Optional<Item> updateItem=get(item.getItemId());
       updateItem.get().setName(item.getName());
       updateItem.get().setPrice(item.getPrice());
        return updateItem;
    }

    @Override
    public boolean delete(Long itemId) {
       Optional  opItem = Optional.ofNullable(Storage.items.stream()
        .filter(i->i.getItemId().equals(itemId)).findFirst());
        return Storage.items.remove(opItem);

    }

    @Override
    public Item delete(Item item) {
        Storage.items.remove(item);
        return item;
    }
}
