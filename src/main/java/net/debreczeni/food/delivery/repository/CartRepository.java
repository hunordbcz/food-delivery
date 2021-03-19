package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.dto.CartDTO;

public class CartRepository extends AbstractRepository<CartDTO>{
    private static final String TABLE_NAME = "shopping_cart";

    protected CartRepository() {
        super(TABLE_NAME);
    }
}
