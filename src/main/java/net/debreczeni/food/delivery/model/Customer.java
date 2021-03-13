package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Customer extends User{
    private final String nrIdentity;
    private final Integer cnp;
    private final String address;
    private final Boolean isLoyal;

    public Customer(Integer id, String name, String username, String password, String nrIdentity, Integer cnp, String address, Boolean isLoyal) {
        super(id, name, username, password);
        this.nrIdentity = nrIdentity;
        this.cnp = cnp;
        this.address = address;
        this.isLoyal = isLoyal;
    }
}
