package com.media.dmitry68.vacationrecords;

public class ItemFactory {
    protected DatabaseVacation databaseVacation;

    public ItemFactory() {
        databaseVacation = ApplicationVacation.getInstance().getDatabaseVacation();
    }
}
