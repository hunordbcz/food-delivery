package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class User extends HasID {
    protected String name;
    protected String username;
    protected String password;

    public User(Integer id, String name, String username, String password) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
