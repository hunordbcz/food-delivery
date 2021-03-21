package net.debreczeni.food.delivery.model;

import lombok.*;
import net.debreczeni.food.delivery.util.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public final class Order implements HasID, SafeDeletable{
    private Integer id;
    private final User user;
    private List<Item> items;
    private String deliveryAddress;
    private PaymentType paymentType;
    private Boolean isProcessed;
    private Timestamp createdAt;
    private Boolean isDeleted;

//    public Integer getTotal() {
//        return items.stream().map(Item::getPrice).reduce(0, Integer::sum);
//    }
}
