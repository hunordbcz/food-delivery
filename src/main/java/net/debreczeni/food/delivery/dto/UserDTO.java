package net.debreczeni.food.delivery.dto;

import lombok.*;
import net.debreczeni.food.delivery.model.HasID;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements HasID, SafeDeletableDTO{
    private Integer id;
    private String name;
    private String nr_identity;
    private Integer cnp;
    private String address;
    private Boolean is_admin;
    private Boolean is_loyal;
    private String username;
    private String password;
    private Boolean is_deleted;
}
