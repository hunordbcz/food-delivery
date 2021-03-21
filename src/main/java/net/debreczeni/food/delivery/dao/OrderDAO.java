package net.debreczeni.food.delivery.dao;

import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.model.PaymentType;
import net.debreczeni.food.delivery.service.ItemService;
import net.debreczeni.food.delivery.service.OrderService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private final OrderService orderService = new OrderService();
    private final ItemService itemService = new ItemService();

    public void makeOrder(Customer customer, List<Item> items, String deliveryAddress, PaymentType paymentType) {
        orderService.insert(new Order(
                -1,
                customer,
                items,
                deliveryAddress,
                paymentType,
                false,
                new Timestamp(new Date().getTime()),
                null
        ));
    }

    public List<Order> getAll() {
        return orderService.findAll();
    }
}
