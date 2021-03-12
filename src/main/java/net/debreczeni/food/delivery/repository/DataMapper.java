package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.HasID;

import java.util.Optional;

public interface DataMapper {
    <T extends HasID> Optional<T> find(int id);

    void insert(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}
