package net.debreczeni.food.delivery.dto;

import lombok.*;
import net.debreczeni.food.delivery.model.HasID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO implements HasID, SafeDeletableDTO {
    private Integer id;
    private String name;
    private Double price;
    private Boolean is_deleted;
}
