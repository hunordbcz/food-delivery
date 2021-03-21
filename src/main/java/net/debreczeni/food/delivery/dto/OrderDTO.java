package net.debreczeni.food.delivery.dto;

import lombok.*;
import net.debreczeni.food.delivery.model.HasID;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements HasID, SafeDeletableDTO {
    public final static int PAYMENT_CASH = 0;
    public final static int PAYMENT_CARD = 1;

    private Integer id;
    private String items_list;
    private Integer user_id;
    private Timestamp created_at;
    private String delivery_address;
    private Boolean is_processed;
    private Integer payment_type;
    private Boolean is_deleted;
}
