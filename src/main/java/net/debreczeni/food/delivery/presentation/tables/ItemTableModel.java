package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.model.Item;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {
    private static final int NAME = 0;
    private static final int PRICE = 1;
    private final List<Item> items;

    public ItemTableModel(List<Item> items) {
        this.items = items;
    }

    public ItemTableModel() {
        this(new LinkedList<>());
    }

    public void addItem(Item item) {
        items.add(item);

        fireTableDataChanged();
    }

    public Integer getTotal() {
        return items.stream().map(Item::getPrice).reduce(0, Integer::sum);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Item item = items.get(rowIndex);
        switch (columnIndex) {
            case NAME:
                return item.getName();
            case PRICE:
                return item.getPrice();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case NAME:
                return "Name";
            case PRICE:
                return "Price";
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
            case NAME:
                return String.class;
            case PRICE:
                return Integer.class;
            default:
                return null;
        }
    }

    public Item getItem(int row) {
        return items.get(row);
    }

    public void removeItem(int row) {
        items.remove(row);

        fireTableDataChanged();
    }
}
