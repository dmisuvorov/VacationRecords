package com.media.dmitry68.vacationrecords.action;

import com.media.dmitry68.vacationrecords.ApplicationVacation;
import com.media.dmitry68.vacationrecords.DatabaseVacation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActionFactory {
    private ActionDao actionDao;

    public ActionFactory() {
        DatabaseVacation databaseVacation = ApplicationVacation.getInstance().getDatabaseVacation();
        actionDao = databaseVacation.actionDao();
    }

    public void getActionEntities(final ActionCallback actionCallback) {
        actionDao.getAll().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ActionEntity>>() {
                    @Override
                    public void accept(List<ActionEntity> actionEntities) throws Exception {
                        actionCallback.onActionLoaded(actionEntities);
                    }
                });
    }

    public void deleteActionEntity(final ActionCallback actionCallback, final ActionEntity actionEntity) {
        Completable.fromAction(new Action() {
            @Override
            public void run() {
                actionDao.delete(actionEntity);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        actionCallback.onDeleteAction(actionEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        actionCallback.onDataNotAvailable();
                    }
                });
    }
}
