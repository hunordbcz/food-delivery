package net.debreczeni.food.delivery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Item extends HasID {
    private String name;
    private Double price;

    public Item(Integer id, String name, Double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
