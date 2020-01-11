package internetShop.dao.impl;

import internetShop.lib.Dao;
import internetShop.dao.ItemDao;
import internetShop.dao.Storage;
import internetShop.model.Item;
import internetShop.web.IdGenerator;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item create(Item item) {
        Item newItem=item;
        newItem.setItemId(IdGenerator.getNewItemId());
        Storage.items.add(newItem);
        return newItem;
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
    public Item update(Item item) {
        int itemPos = 0;
        for (Item i : Storage.items) {
            if (i.getItemId().equals(item.getItemId())) {
                break;
            }
            itemPos++;
        }
        Storage.items.set(itemPos, item);
        return item;
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
