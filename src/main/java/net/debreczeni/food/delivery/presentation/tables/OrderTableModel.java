package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.bll.OrderBLL;
import net.debreczeni.food.delivery.model.Order;

import javax.swing.table.AbstractTableModel;
import java.sql.Timestamp;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {

    protected final static int ID = 0;
    protected final static int NR_ITEMS = 1;
    protected final static int USER = 2;
    protected final static int CREATED_AT = 3;
    protected final static int DELIVERY_ADDRESS = 4;
    protected final static int IS_PROCESSED = 5;
    protected final static int PAYMENT_TYPE = 6;
    protected final static int DISCOUNT = 7;
    protected final static int TOTAL = 8;

    protected final OrderBLL orderBLL = new OrderBLL();
    protected List<Order> orders;

    public OrderTableModel() {
    }

    public OrderTableModel(List<Order> orders) {
        this.orders = orders;
    }

    public void refresh() {
        orders = orderBLL.findAll();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if (orders == null) {
            refresh();
        }
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
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
            case DISCOUNT:
                return order.getDiscount();
            case TOTAL:
                return order.getTotal();
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
            case DISCOUNT:
                return "Discount";
            case TOTAL:
                return "Total";
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
            case DISCOUNT:
            case TOTAL:
                return Double.class;
            default:
                return null;
        }
    }

    public void cancelOrder(int row) {
        orderBLL.cancel(orders.get(row));
        refresh();
    }

    public Order getOrder(Integer orderId) {
        return orders.get(orderId);
    }
}
