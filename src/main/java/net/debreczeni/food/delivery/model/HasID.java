package net.debreczeni.food.delivery.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public interface HasID {
    public Integer getId();

    public void setId(Integer id);
}
