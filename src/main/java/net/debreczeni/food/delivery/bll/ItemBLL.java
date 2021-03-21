package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.ItemDAO;
import net.debreczeni.food.delivery.exceptions.InvalidInput;
import net.debreczeni.food.delivery.model.Item;

import java.util.List;

public class ItemBLL {
    private final ItemDAO itemDAO = new ItemDAO();

    public void addNewItem(String name, String price) throws InvalidInput {
        if(name.isEmpty()){
            throw new InvalidInput("Name mustn't be empty");
        }

        Double priceVal;
        try{
            priceVal = Double.parseDouble(price);
        }catch (Exception e){
            throw new InvalidInput("Invalid price entered");
        }
        itemDAO.addNewItem(new Item(name, priceVal));
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
