package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class User implements HasID {
    protected final Integer id;
    protected final String name;
    protected final String username;
    protected final String password;
}
