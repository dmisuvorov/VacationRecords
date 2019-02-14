package com.media.dmitry68.vacationrecords;

import android.app.Application;
import android.arch.persistence.room.Room;

public class ApplicationVacation extends Application {
    private static ApplicationVacation instanceApplicationVacation;

    private DatabaseVacation databaseVacation;

    @Override
    public void onCreate() {
        super.onCreate();
        instanceApplicationVacation = this;
        databaseVacation = Room.databaseBuilder(this, DatabaseVacation.class, "databaseVacation").build();
    }

    public static ApplicationVacation getInstance() {
        return instanceApplicationVacation;
    }

    public DatabaseVacation getDatabaseVacation() {
        return databaseVacation;
    }

}
