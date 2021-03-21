package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public abstract class User implements HasID, SafeDeletable {
    protected Integer id;
    protected String name;
    protected String username;
    protected String password;
    protected Boolean isDeleted;
}
