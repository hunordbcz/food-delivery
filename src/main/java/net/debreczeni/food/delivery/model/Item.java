package net.debreczeni.food.delivery.model;

import lombok.Getter;

@Getter
public final class Item extends HasID {
    private final String name;
    private final Integer price;

    public Item(Integer id, String name, Integer price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public Item(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
