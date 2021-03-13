package net.debreczeni.food.delivery.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String nr_identity;
    private Integer cnp;
    private String address;
    private Boolean is_admin;
    private Boolean is_loyal;
    private String username;
    private String password;
}
