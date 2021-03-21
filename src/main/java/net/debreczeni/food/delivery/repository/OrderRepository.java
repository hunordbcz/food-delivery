package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.dto.ItemDTO;
import net.debreczeni.food.delivery.dto.OrderDTO;

import java.sql.Timestamp;
import java.util.Date;

public class OrderRepository extends AbstractRepository<OrderDTO>{
    private static final String TABLE_NAME = "orders";

    public OrderRepository() {
        super(TABLE_NAME);
    }

    @Override
    public Boolean softDelete(OrderDTO orderDTO) {
        orderDTO.setIs_deleted(true);
        return update(orderDTO);
    }
}
