package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.presentation.tables.CustomItemTableModel;
import net.debreczeni.food.delivery.presentation.tables.OrderTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class OrdersListFrame extends JFrame {
    private JPanel mainPanel;
    private JTable orders;

    public OrdersListFrame(Component relativeTo, String title, List<Order> items) throws HeadlessException {
        this.setTitle(title);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);
        this.setAlwaysOnTop(true);

        final TableModel ordersTableModel = new OrderTableModel(items);
        orders.setModel(ordersTableModel);
    }
}
