package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.model.Customer;

import java.sql.ResultSet;

public class CustomerGateWay implements GateWay<Customer>{
    @Override
    public ResultSet find(int id) {
        return null;
    }

    @Override
    public void update(Customer object) {

    }

    @Override
    public void insert(Customer object) {

    }

    @Override
    public void delete(Customer object) {

    }

    @Override
    public void delete(int id) {

    }
}
