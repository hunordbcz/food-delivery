package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class User extends HasID {
    protected final String name;
    protected final String username;
    protected final String password;

    public User(Integer id, String name, String username, String password) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
