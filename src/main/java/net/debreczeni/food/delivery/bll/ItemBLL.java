package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.ItemDAO;
import net.debreczeni.food.delivery.model.Item;

import java.util.List;

public class ItemBLL {
    private final ItemDAO itemDAO = new ItemDAO();

    public void addNewItem(String name, String price) {
        itemDAO.addNewItem(name, price);
    }

    public List<Item> getAll() {
        return itemDAO.getAll();
    }

    public void update(Item item) {
        itemDAO.update(item);
    }

    public void delete(Item item) {
        itemDAO.delete(item);
    }
}
