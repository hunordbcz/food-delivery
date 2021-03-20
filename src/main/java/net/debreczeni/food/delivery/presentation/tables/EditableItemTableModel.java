package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.model.Item;

import java.util.List;

public class EditableItemTableModel extends ItemTableModel {


    public EditableItemTableModel() {
        super();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        final Item item = items.get(rowIndex);
        switch (columnIndex) {
            case NAME:
                item.setName((String) aValue);
                break;
            case PRICE:
                item.setPrice((Double) aValue);
                break;
            default:
                return;
        }

        itemBLL.update(item);
    }

    @Override
    public void removeItem(int row) {
        itemBLL.delete(items.get(row));
        refresh();
    }
}
