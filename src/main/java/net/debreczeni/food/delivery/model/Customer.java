package net.debreczeni.food.delivery.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public final class Customer extends User {
    private String nrIdentity;
    private Integer cnp;
    private String address;
    private Boolean isLoyal;

    public Customer(Integer id, String name, String username, String password, String nrIdentity, Integer cnp, String address, Boolean isLoyal, Boolean isDeleted) {
        super(id, name, username, password, isDeleted);
        this.nrIdentity = nrIdentity;
        this.cnp = cnp;
        this.address = address;
        this.isLoyal = isLoyal;
    }

    public Customer(Integer id, String name, String username, String password, String nrIdentity, Integer cnp, String address) {
        this(id, name, username, password, nrIdentity, cnp, address, false, null);
    }

    public Customer(String name, String username, String password, String nrIdentity, Integer cnp, String address) {
        this(-1, name, username, password, nrIdentity, cnp, address, false, null);
    }
}
