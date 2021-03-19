package net.debreczeni.food.delivery.model;

import lombok.Getter;

@Getter
public enum PaymentType {
    CASH(0),
    CARD(1),
    TRANSFER(2);

    private final int code;

    PaymentType(int code) {
        this.code = code;
    }

    public static PaymentType getType(int code){
        switch (code){
            case 0:
                return CASH;
            case 1:
                return CARD;
            case 2:
                return TRANSFER;
            default:
                return null;
        }
    }
}
