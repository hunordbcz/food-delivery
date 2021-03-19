package net.debreczeni.food.delivery.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Customer extends User {
    private String nrIdentity;
    private Integer cnp;
    private String address;
    private Boolean isLoyal;

    public Customer(Integer id, String name, String username, String password, String nrIdentity, Integer cnp, String address, Boolean isLoyal) {
        super(id, name, username, password);
        this.nrIdentity = nrIdentity;
        this.cnp = cnp;
        this.address = address;
        this.isLoyal = isLoyal;
    }
    public Customer(Integer id, String name, String username, String password, String nrIdentity, Integer cnp, String address) {
        super(id, name, username, password);
        this.nrIdentity = nrIdentity;
        this.cnp = cnp;
        this.address = address;
        this.isLoyal = false;
    }

    public Customer(String name, String username, String password, String nrIdentity, Integer cnp, String address) {
        super(name, username, password);
        this.nrIdentity = nrIdentity;
        this.cnp = cnp;
        this.address = address;
        this.isLoyal = false;
    }
}
