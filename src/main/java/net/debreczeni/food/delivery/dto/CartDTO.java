package net.debreczeni.food.delivery.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    public final static int PAYMENT_CASH = 0;
    public final static int PAYMENT_CARD = 1;
    public final static int PAYMENT_TRANSFER = 2;

    private Integer id;
    private String items_list;
    private Integer user_id;
    private Date created_at;
    private String delivery_address;
    private Boolean is_processed;
    private Integer payment_type;
}
