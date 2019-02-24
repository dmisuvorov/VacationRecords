package com.media.dmitry68.vacationrecords.employer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EmployerEntity {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    public EmployerEntity(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof EmployerEntity) {
            return (this.id.equals(((EmployerEntity) obj).id));
        } else return false;
    }
}
