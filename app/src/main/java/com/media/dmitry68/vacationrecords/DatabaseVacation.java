package com.media.dmitry68.vacationrecords;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.media.dmitry68.vacationrecords.action.ActionDao;
import com.media.dmitry68.vacationrecords.action.ActionEntity;

@Database(entities = {ActionEntity.class}, version = 1)
public abstract class DatabaseVacation extends RoomDatabase {
    public abstract ActionDao actionDao();
}
