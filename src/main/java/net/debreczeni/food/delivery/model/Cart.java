package net.debreczeni.food.delivery.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart implements HasID{
    private int id;
    private User user;
    private final List<Item> items = new ArrayList<>();
    private String deliveryAddress;
    private String paymentType;
    private boolean isProcessed;
    private Date createdAt;
}
