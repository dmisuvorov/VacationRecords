package com.media.dmitry68.vacationrecords.employer;

import com.media.dmitry68.vacationrecords.ItemFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class EmployerFactory extends ItemFactory {
    private EmployerDao employerDao;

    public EmployerFactory() {
        super();
        employerDao = databaseVacation.employerDao();
    }

    public void getEmployerEntities(final EmployerCallback employerCallback) {
        employerDao.getAll().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<EmployerEntity>>() {
                    @Override
                    public void accept(List<EmployerEntity> employerEntities) {
                        employerCallback.onEmployerLoaded(employerEntities);
                    }
                });
    }
}
