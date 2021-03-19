package net.debreczeni.food.delivery.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public final class Cart extends HasID {
    private final User user;
    private final List<Item> items = new ArrayList<>();
    private final String deliveryAddress;
    private final PaymentType paymentType;
    private final Boolean isProcessed;
    private final Date createdAt;

    public Cart(Integer id, User user, String deliveryAddress, PaymentType paymentType, Boolean isProcessed, Date createdAt) {
        super(id);
        this.user = user;
        this.deliveryAddress = deliveryAddress;
        this.paymentType = paymentType;
        this.isProcessed = isProcessed;
        this.createdAt = createdAt;
    }
}
