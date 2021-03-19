package net.debreczeni.food.delivery.model;

import lombok.Getter;
import net.debreczeni.food.delivery.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Getter
public final class Cart {
//    private final User user;
    private final List<Item> items = new LinkedList<>();
//    private final String deliveryAddress;
//    private final PaymentType paymentType;
//    private final Boolean isProcessed;
//    private final Date createdAt;

    public void addItem(Item item){
        items.add(item);
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public Integer getTotal() {
        return items.stream().map(Item::getPrice).reduce(0, Integer::sum);
    }
}
