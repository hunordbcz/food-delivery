package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.exceptions.InvalidInput;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.presentation.tables.EditableItemTableModel;
import net.debreczeni.food.delivery.presentation.tables.EditableOrderTableModel;
import net.debreczeni.food.delivery.presentation.tables.ItemTableModel;
import net.debreczeni.food.delivery.presentation.tables.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Comparator;

public class AdminFrame extends JFrame {

    private final ItemTableModel itemTableModel;
    private final UserTableModel userTableModel;
    private JPanel dashboardPanel;
    private JTable itemTable;
    private JButton refreshTablesButton;
    private JTable userTable;
    private JTable ordersTable;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JButton deleteUserButton;
    private JButton logoutButton;
    private JButton showItemsButton;
    private JButton cancelButton;
    private JButton reportsButton;

    public AdminFrame(Component relativeTo, User user) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        userTableModel = new UserTableModel();
        userTable.setModel(userTableModel);
        final ListSelectionModel userSelectionModel = userTable.getSelectionModel();
        userSelectionModel.addListSelectionListener(l -> {
            if (l.getValueIsAdjusting()) {
                return;
            }

            deleteUserButton.setEnabled(true);
        });
        deleteUserButton.addActionListener(l -> {
            Arrays.stream(userTable.getSelectedRows())
                    .map(row -> userTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(userTableModel::deleteUser);

            userSelectionModel.clearSelection();
            deleteUserButton.setEnabled(false);
        });

        itemTableModel = new EditableItemTableModel();
        itemTable.setModel(itemTableModel);
        addItemButton.addActionListener(e -> {
            AddItemFrame addItemFrame = new AddItemFrame(this);
            addItemFrame.setVisible(true);
            addItemFrame.setAlwaysOnTop(true);
            addItemButton.setEnabled(false);
            addItemFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    addItemButton.setEnabled(true);
                    itemTableModel.refresh();
                }
            });
        });

        ListSelectionModel itemTableSelection = itemTable.getSelectionModel();
        itemTableSelection.addListSelectionListener(l -> {
            if (l.getValueIsAdjusting()) {
                return;
            }

            deleteItemButton.setEnabled(true);
        });

        deleteItemButton.addActionListener(l -> {
            Arrays.stream(itemTable.getSelectedRows())
                    .map(row -> itemTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(itemTableModel::removeItem);

            itemTableSelection.clearSelection();
            deleteItemButton.setEnabled(false);
        });


        final EditableOrderTableModel orderTableModel = new EditableOrderTableModel();
        ordersTable.setModel(orderTableModel);
        final ListSelectionModel orderSelectionModel = ordersTable.getSelectionModel();
        orderSelectionModel.addListSelectionListener(l -> {
            if (l.getValueIsAdjusting()) {
                return;
            }

            cancelButton.setEnabled(true);
            showItemsButton.setEnabled(true);
        });

        logoutButton.addActionListener(e -> {
            final LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        });

        refreshTablesButton.addActionListener(l -> {
            itemTableModel.refresh();
            userTableModel.refresh();
            orderTableModel.refresh();
        });

        cancelButton.addActionListener(e -> {
            Arrays.stream(ordersTable.getSelectedRows())
                    .map(row -> ordersTable.getRowSorter().convertRowIndexToModel(row))
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .forEach(orderTableModel::cancelOrder);

            cancelButton.setEnabled(false);
            showItemsButton.setEnabled(false);
        });
        showItemsButton.addActionListener(e ->
                Arrays.stream(ordersTable.getSelectedRows())
                        .map(row -> ordersTable.getRowSorter().convertRowIndexToModel(row))
                        .boxed()
                        .sorted(Comparator.reverseOrder())
                        .forEach(orderId -> {
                            final Order order = orderTableModel.getOrder(orderId);
                            final ItemsListFrame itemsListFrame = new ItemsListFrame(
                                    this,
                                    String.format("ORDER #%d - %s", order.getId(), order.getUser().getName()),
                                    order.getItems()
                            );
                            itemsListFrame.setVisible(true);
                            itemsListFrame.setAlwaysOnTop(true);
                        }));
        reportsButton.addActionListener(e -> {
            ReportSetupFrame reportSetupFrame;
            try {
                reportSetupFrame = new ReportSetupFrame(this);
                reportSetupFrame.setVisible(true);
            } catch (InvalidInput invalidInput) {
                JOptionPane.showMessageDialog(this, invalidInput.getMessage(), null, JOptionPane.WARNING_MESSAGE);
            }

        });
    }
}
