package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.model.HasID;

import java.sql.ResultSet;

public interface GateWay<T extends HasID> {
    ResultSet find(int id);

    void update(T object);

    void insert(T object);

    void delete(T object);

    void delete(int id);
}
