package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.presentation.tables.CustomItemTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ItemsListFrame extends JFrame {
    private JTable itemsTable;
    private JPanel mainPanel;

    public ItemsListFrame(Component relativeTo, String title, List<Item> items) throws HeadlessException {
        this.setTitle(title);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);
        this.setAlwaysOnTop(true);

        final TableModel itemTableModel = new CustomItemTableModel(items);
        itemsTable.setModel(itemTableModel);
    }
}
