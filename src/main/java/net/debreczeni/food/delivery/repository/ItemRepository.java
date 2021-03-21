package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.dto.ItemDTO;

import java.sql.Timestamp;
import java.util.Date;


public class ItemRepository extends AbstractRepository<ItemDTO>{
    private static final String TABLE_NAME = "items";

    public ItemRepository() {
        super(TABLE_NAME);
    }

    @Override
    public Boolean softDelete(ItemDTO itemDTO) {
        itemDTO.setIs_deleted(true);
        return update(itemDTO);
    }
}
