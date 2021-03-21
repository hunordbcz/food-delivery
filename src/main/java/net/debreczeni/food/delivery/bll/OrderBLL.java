package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.OrderDAO;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.model.PaymentType;

import java.util.Collection;
import java.util.List;

public class OrderBLL {
    private final OrderDAO orderDAO = new OrderDAO();

    public void makeOrder(Customer customer, List<Item> items, String deliveryAddress, PaymentType paymentType) {
        orderDAO.makeOrder(customer, items, deliveryAddress, paymentType);
    }

    public List<Order> getAll() {
        return orderDAO.getAll();
    }
}
