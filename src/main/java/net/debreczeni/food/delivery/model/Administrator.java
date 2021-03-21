package net.debreczeni.food.delivery.model;

import java.sql.Timestamp;

public final class Administrator extends User {

    public Administrator(Integer id, String name, String username, String password, Boolean deletedAt) {
        super(id, name, username, password, deletedAt);
    }

//    public Administrator(Integer id, String name, String username, String password) {
//        super(id, name, username, password, null);
//    }
}
