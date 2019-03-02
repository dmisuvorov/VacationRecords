package com.media.dmitry68.vacationrecords.employer;

import com.media.dmitry68.vacationrecords.ItemFactory;
import com.media.dmitry68.vacationrecords.settings.EmployerSettingsCallback;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

    public void deleteEmployerEntity(final EmployerSettingsCallback employerCallback, final EmployerEntity employerEntity) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employerDao.delete(employerEntity);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        employerCallback.onDeleteEmployer(employerEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        employerCallback.onDataNotAvailable();
                    }
                });
    }

    public void addEmployerEntity(final EmployerSettingsCallback employerCallback, final String employerEntityName) {
        final EmployerEntity newEmployerEntity = new EmployerEntity(employerEntityName);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                employerDao.insert(newEmployerEntity);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        employerCallback.onAddEmployer(newEmployerEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        employerCallback.onDataNotAvailable();
                    }
                });
    }

    public void updateEmployerEntityName(final EmployerSettingsCallback employerCallback, final EmployerEntity employerEntity, final String newEmployerEntityName) {
        employerEntity.setName(newEmployerEntityName);
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                employerDao.update(employerEntity);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        employerCallback.onUpdateEmployer(employerEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        employerCallback.onDataNotAvailable();
                    }
                });
    }
}
