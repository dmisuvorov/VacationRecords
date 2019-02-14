package com.media.dmitry68.vacationrecords.action;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ActionDao {

    @Query("SELECT * FROM actionentity")
    List<ActionEntity> getAll();

    @Query("SELECT * FROM actionentity WHERE id = :id")
    ActionEntity getById(long id);

    @Insert
    void insert(ActionEntity actionEntity);

    @Update
    void update(ActionEntity actionEntity);

    @Delete
    void delete(ActionEntity actionEntity);
}
