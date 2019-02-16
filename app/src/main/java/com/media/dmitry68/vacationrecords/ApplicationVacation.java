package com.media.dmitry68.vacationrecords;

import android.app.Application;

public class ApplicationVacation extends Application {
    private static ApplicationVacation instanceApplicationVacation;

    private DatabaseVacation databaseVacation;

    @Override
    public void onCreate() {
        super.onCreate();
        instanceApplicationVacation = this;
        databaseVacation = DatabaseVacation.getInstance(getApplicationContext());
    }

    public static ApplicationVacation getInstance() {
        return instanceApplicationVacation;
    }

    public DatabaseVacation getDatabaseVacation() {
        return databaseVacation;
    }

}
