package net.debreczeni.food.delivery.db.service;

import net.debreczeni.food.delivery.model.HasID;

import java.util.List;

public interface Service<T extends HasID> {
    List<T> findAll();

    List<T> findAll(boolean showDeleted);

    boolean insert(T t);

    T findByID(int id);

    boolean update(T t);

    boolean delete(T t);

    boolean softDelete(T t);
}
