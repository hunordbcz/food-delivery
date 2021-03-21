package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.model.Order;

public class EditableOrderTableModel extends OrderTableModel {
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case DELIVERY_ADDRESS:
            case IS_PROCESSED:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        final Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case DELIVERY_ADDRESS:
                order.setDeliveryAddress((String) aValue);
                break;
            case IS_PROCESSED:
                order.setIsProcessed((Boolean) aValue);
                break;
            default:
                return;
        }

        orderBLL.update(order);
        refresh();
    }
}
