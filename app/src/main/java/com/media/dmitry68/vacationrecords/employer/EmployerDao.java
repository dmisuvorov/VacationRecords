package com.media.dmitry68.vacationrecords.employer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface EmployerDao {

    @Query("SELECT * FROM employerentity")
    Flowable<List<EmployerEntity>> getAll();

    @Query("SELECT * FROM employerentity WHERE id = :id")
    EmployerEntity getById(long id);

    @Insert
    void insertAll(EmployerEntity... actionEntities);

    @Insert
    void insert(EmployerEntity actionEntity);

    @Update
    void update(EmployerEntity actionEntity);

    @Delete
    void delete(EmployerEntity actionEntity);
}
