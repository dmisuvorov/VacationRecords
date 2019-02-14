package com.media.dmitry68.vacationrecords.action;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ActionEntity {
    @PrimaryKey
    public long id;

    String name;

    String colorHex;
}
