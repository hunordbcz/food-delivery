package net.debreczeni.food.delivery.dao;

import net.debreczeni.food.delivery.db.service.OrderService;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.model.PaymentType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private final OrderService orderService = new OrderService();

    public void makeOrder(Customer customer, List<Item> items, String deliveryAddress, PaymentType paymentType, Double discount, Double total) {
        orderService.insert(new Order(
                -1,
                customer,
                items,
                deliveryAddress,
                paymentType,
                false,
                discount,
                total,
                new Timestamp(new Date().getTime()),
                false
        ));
    }

    public List<Order> findAll() {
        return orderService.findAll();
    }

    public List<Order> findByUserID(int id) {
        return orderService.findByUserID(id);
    }

    public void update(Order order) {
        orderService.update(order);
    }

    public void cancel(Order order) {
        orderService.softDelete(order);
    }

    public List<Order> findAll(boolean showDeleted) {
        return orderService.findAll(showDeleted);
    }

    public List<Order> findFrom(Timestamp currentStartInterval) {
        return orderService.findFrom(currentStartInterval);
    }
}
