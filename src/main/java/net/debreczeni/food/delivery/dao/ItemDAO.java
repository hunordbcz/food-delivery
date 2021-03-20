package net.debreczeni.food.delivery.dao;

import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.service.ItemService;

import java.util.List;

public class ItemDAO {
    private final ItemService itemService = new ItemService();

    public void addNewItem(String name, String price) {
        Double priceVal = Double.parseDouble(price);
        itemService.insert(new Item(name, priceVal));
    }

    public List<Item> getAll() {
        return itemService.getAll();
    }

    public void update(Item item) {
        itemService.update(item);
    }

    public void delete(Item item) {
        itemService.delete(item);
    }
}
