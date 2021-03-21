package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.presentation.tables.CustomItemTableModel;
import net.debreczeni.food.delivery.presentation.tables.ItemTableModel;
import net.debreczeni.food.delivery.service.ItemService;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
    private JScrollPane itemsContainerPane;
    private JTextField searchItemField;
    private CheckoutFrame checkoutFrame;
    private Customer customer;

    public CustomerFrame(Component relativeTo, User user) {
        customer = (Customer)user;
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        cartTableModel = new CustomItemTableModel(new ArrayList<>());
        cartTable.setModel(cartTableModel);
        cartTableModel.addTableModelListener(l -> {
            totalPrice.setText(cartTableModel.getTotal().toString());
            checkoutButton.setEnabled(cartTableModel.getTotal() > 0 && (checkoutFrame == null || !checkoutFrame.isDisplayable()));
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
            Arrays.stream(itemsTable.getSelectedRows())
                    .map(row -> itemsTable.getRowSorter().convertRowIndexToModel(row))
                    .forEach(row -> cartTableModel.addItem(itemTableModel.getItem(row)));
            itemsTableSelection.clearSelection();
            addButton.setEnabled(false);
        });

        checkoutButton.addActionListener(e -> {
            checkoutFrame = new CheckoutFrame(this, customer, cartTableModel.getItems());
            checkoutFrame.setAlwaysOnTop(true);
            checkoutFrame.setVisible(true);
            checkoutButton.setEnabled(false);
            checkoutFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    checkoutButton.setEnabled(true);
                }
            });
        });


        TableRowSorter<ItemTableModel> sorter = new TableRowSorter<>(((ItemTableModel) itemsTable.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(searchItemField.getText()));

        itemsTable.setRowSorter(sorter);
        searchItemField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                sorter.setRowFilter(RowFilter.regexFilter(searchItemField.getText()));
                System.out.println(e.getKeyChar());
            }
        });
    }
}
