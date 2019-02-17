package com.media.dmitry68.vacationrecords.action;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ActionEntity {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String name;

    private String colorHex;

    public ActionEntity(String name, String colorHex) {
        this.name = name;
        this.colorHex = colorHex;
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

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ActionEntity) {
            return (this.id.equals(((ActionEntity) obj).id));
        } else return false;
    }
}
