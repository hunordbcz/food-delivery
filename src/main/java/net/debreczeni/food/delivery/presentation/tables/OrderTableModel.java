package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.bll.OrderBLL;
import net.debreczeni.food.delivery.model.Order;

import javax.swing.table.AbstractTableModel;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

public class OrderTableModel extends AbstractTableModel {

    private final static int ID = 0;
    private final static int NR_ITEMS = 1;
    private final static int USER = 2;
    private final static int CREATED_AT = 3;
    private final static int DELIVERY_ADDRESS = 4;
    private final static int IS_PROCESSED = 5;
    private final static int PAYMENT_TYPE = 6;

    private final OrderBLL orderBLL = new OrderBLL();
    private final Supplier<List<Order>> orderSupplier;
    private List<Order> orders;

    public OrderTableModel(Supplier<List<Order>> orderSupplier) {
        this.orderSupplier = orderSupplier;
        refresh();
    }

    public void refresh() {
        orders = orderSupplier.get();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return order.getId();
            case NR_ITEMS:
                return order.getItems().size();
            case USER:
                return order.getUser().getName();
            case CREATED_AT:
                return order.getCreatedAt();
            case DELIVERY_ADDRESS:
                return order.getDeliveryAddress();
            case IS_PROCESSED:
                return order.getIsProcessed();
            case PAYMENT_TYPE:
                return order.getPaymentType();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case ID:
                return "ID";
            case NR_ITEMS:
                return "Nr Items";
            case USER:
                return "User";
            case CREATED_AT:
                return "Created At";
            case DELIVERY_ADDRESS:
                return "Delivery Address";
            case IS_PROCESSED:
                return "Is Processed";
            case PAYMENT_TYPE:
                return "Payment Type";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
            case NR_ITEMS:
                return Integer.class;
            case USER:
            case PAYMENT_TYPE:
            case DELIVERY_ADDRESS:
                return String.class;
            case CREATED_AT:
                return Timestamp.class;
            case IS_PROCESSED:
                return Boolean.class;
            default:
                return null;
        }
    }
}
