package net.debreczeni.food.delivery.db.repository;

import net.debreczeni.food.delivery.dto.OrderDTO;
import net.debreczeni.food.delivery.util.Pair;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static net.debreczeni.food.delivery.db.repository.SQL.ORDER_TYPE.ASC;

public class OrderRepository extends AbstractRepository<OrderDTO> {
    private static final String TABLE_NAME = "orders";

    public OrderRepository() {
        super(TABLE_NAME);
    }

    @Override
    public Boolean softDelete(OrderDTO orderDTO) {
        orderDTO.setIs_deleted(true);
        return update(orderDTO);
    }

    public List<OrderDTO> findByUserID(int id) {
        List<Pair<String, Object>> rules = new LinkedList<>();
        rules.add(new Pair<>("user_id", id));

        List<OrderDTO> response = select(rules, ASC);
        if (response != null) {
            return response;
        }

        return Collections.emptyList();
    }

    public List<OrderDTO> findFrom(Timestamp from) {
        List<Pair<String, Object>> rules = new LinkedList<>();
        rules.add(new Pair<>("created_at", from));

        List<OrderDTO> response = selectBigger(rules, ASC);
        if (response != null) {
            return response;
        }

        return Collections.emptyList();
    }
}
