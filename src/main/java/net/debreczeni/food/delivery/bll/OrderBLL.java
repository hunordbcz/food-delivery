package net.debreczeni.food.delivery.bll;

import net.debreczeni.food.delivery.dao.OrderDAO;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.model.PaymentType;

import java.sql.Timestamp;
import java.util.List;

public class OrderBLL {
    private final OrderDAO orderDAO = new OrderDAO();

    public void makeOrder(Customer customer, List<Item> items, String deliveryAddress, PaymentType paymentType, Double discount, Double total) {
        orderDAO.makeOrder(customer, items, deliveryAddress, paymentType, discount, total);
    }

    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    public List<Order> findByUserID(int id) {
        return orderDAO.findByUserID(id);
    }

    public void update(Order order) {
        orderDAO.update(order);
    }

    public void cancel(Order order) {
        orderDAO.cancel(order);
    }

    public List<Order> findAll(boolean showDeleted) {
        return orderDAO.findAll(showDeleted);
    }

    public List<Order> findFrom(Timestamp currentStartInterval) {
        return orderDAO.findFrom(currentStartInterval);
    }
}
