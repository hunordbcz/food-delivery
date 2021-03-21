package net.debreczeni.food.delivery.repository;

import java.util.List;

public interface Repository<T> {
    Boolean insert(T t);
    T findByID(int id);
    T findLast();
    List<T> findAll();
    Boolean update(T t);
    Boolean delete(T t);
    Boolean softDelete(T t);
}
