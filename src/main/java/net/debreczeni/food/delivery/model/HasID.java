package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class HasID {
    protected final Integer id;
}
