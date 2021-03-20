package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.presentation.tables.UserTableModel;
import net.debreczeni.food.delivery.presentation.tables.EditableItemTableModel;
import net.debreczeni.food.delivery.presentation.tables.ItemTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Comparator;

public class AdminFrame extends JFrame {

    private JPanel dashboardPanel;
    private JTable itemTable;
    private JButton refreshTablesButton;
    private JButton backToLoginButton;
    private JTable userTable;
    private JTable ordersTable;
    private JButton addItemButton;
    private JButton deleteItemButton;
    private JButton addAdminButton;
    private JButton deleteUserButton;
    private final ItemTableModel itemTableModel;
    private final UserTableModel userTableModel;

    public AdminFrame(Component relativeTo) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        userTableModel = new UserTableModel();
        userTable.setModel(userTableModel);
        final ListSelectionModel userSelectionModel = userTable.getSelectionModel();
        userSelectionModel.addListSelectionListener(l -> {
            if(l.getValueIsAdjusting()){
                return;
            }

            deleteUserButton.setEnabled(true);
        });
        deleteItemButton.addActionListener(l -> {
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
            if(l.getValueIsAdjusting()){
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

        refreshTablesButton.addActionListener(l -> {
            itemTableModel.refresh();
            userTableModel.refresh();
        });
    }
}
