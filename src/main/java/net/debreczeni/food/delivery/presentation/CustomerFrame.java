package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.presentation.tables.ItemTableModel;
import net.debreczeni.food.delivery.service.ItemService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class CustomerFrame extends JFrame {
    private final ItemTableModel cartTableModel;
    private final ItemTableModel itemTableModel;
    private ItemService itemService = new ItemService();
    private JTabbedPane tabbedPane;
    private JPanel dashboardPanel;
    private JButton addButton;
    private JTable itemsTable;
    private JButton removeButton;
    private JTable cartTable;
    private JScrollPane pastOrdersTable;
    private JLabel totalPrice;
    private JButton checkoutButton;

    public CustomerFrame(Component relativeTo) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        cartTableModel = new ItemTableModel();
        cartTable.setModel(cartTableModel);
        cartTableModel.addTableModelListener(l -> {
            totalPrice.setText(cartTableModel.getTotal().toString());
        });
        final ListSelectionModel cartTableSelection = cartTable.getSelectionModel();
        cartTableSelection.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            removeButton.setEnabled(true);
        });

        removeButton.addActionListener(e -> {
            Arrays.stream(cartTable.getSelectedRows())
                    .map(row -> cartTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(cartTableModel::removeItem);

            cartTableSelection.clearSelection();
            removeButton.setEnabled(false);
        });

        itemTableModel = new ItemTableModel();
        itemsTable.setModel(itemTableModel);
        final ListSelectionModel itemsTableSelection = itemsTable.getSelectionModel();
        itemsTableSelection.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            addButton.setEnabled(true);
        });


        addButton.addActionListener(e -> {
            Arrays.stream(itemsTable.getSelectedRows()).forEach(row -> {
                cartTableModel.addItem(itemTableModel.getItem(row));
            });
            itemsTableSelection.clearSelection();
            addButton.setEnabled(false);
        });

        checkoutButton.addActionListener(e -> {

        });
    }
}
