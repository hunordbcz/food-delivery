package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.model.Customer;

public class CustomerOrderTableModel extends OrderTableModel {

    private final Customer customer;

    public CustomerOrderTableModel(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void refresh() {
        orders = orderBLL.findByUserID(customer.getId());
        fireTableDataChanged();
    }
}
