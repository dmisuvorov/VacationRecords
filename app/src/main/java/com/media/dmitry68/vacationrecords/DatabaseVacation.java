package com.media.dmitry68.vacationrecords;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.media.dmitry68.vacationrecords.action.ActionDao;
import com.media.dmitry68.vacationrecords.action.ActionEntity;
import com.media.dmitry68.vacationrecords.action.ActionEntityPopulateData;
import com.media.dmitry68.vacationrecords.employer.EmployerDao;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.employer.EmployerEntityPopulateData;

import java.util.concurrent.Executors;

@Database(entities = {ActionEntity.class, EmployerEntity.class}, version = 1)
public abstract class DatabaseVacation extends RoomDatabase {
    private static DatabaseVacation INSTANCE;
    public abstract ActionDao actionDao();
    public abstract EmployerDao employerDao();

    synchronized static DatabaseVacation getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static DatabaseVacation buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                DatabaseVacation.class,
                "database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                final DatabaseVacation databaseVacation = getInstance(context);
                                ActionEntityPopulateData actionData = new ActionEntityPopulateData(context);
                                EmployerEntityPopulateData employerData = new EmployerEntityPopulateData(context);
                                databaseVacation.actionDao().insertAll(actionData.populateData());
                                databaseVacation.employerDao().insertAll(employerData.populateData());
                            }
                        });
                    }
                })
                .build();
    }
}
