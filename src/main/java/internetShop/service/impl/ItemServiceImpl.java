package internetShop.service.impl;

import internetShop.lib.Inject;
import internetShop.lib.Service;
import internetShop.dao.ItemDao;
import internetShop.dao.Storage;
import internetShop.model.Item;
import internetShop.service.ItemService;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }

    @Override
    public List<Item> getAllItems() {
        return Storage.items;
    }


}
