package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.model.Item;

import java.util.List;

public class CustomItemTableModel extends ItemTableModel {

    public CustomItemTableModel(List<Item> items) {
        this.items = items;
    }

    @Override
    public void refresh() {
    }
}
