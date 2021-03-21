package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class Item implements HasID, SafeDeletable {
    private Integer id;
    private String name;
    private Double price;
    private Boolean isDeleted;

    public Item(Integer id, String name, Double price) {
        this(id, name, price, false);
    }

    public Item(String name, Double price) {
        this(name, price, false);
    }

    public Item(String name, Double price, Boolean isDeleted) {
        this(-1, name, price, isDeleted);
    }
}
