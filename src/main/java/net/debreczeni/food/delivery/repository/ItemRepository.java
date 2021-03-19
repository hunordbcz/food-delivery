package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.dto.ItemDTO;

public class ItemRepository extends AbstractRepository<ItemDTO>{
    private static final String TABLE_NAME = "items";

    protected ItemRepository() {
        super(TABLE_NAME);
    }
}