package com.media.dmitry68.vacationrecords.action;

import com.media.dmitry68.vacationrecords.ApplicationVacation;
import com.media.dmitry68.vacationrecords.DatabaseVacation;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

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



    public List<String> getActionNames(List<ActionEntity> actionEntities) {
        List<String> actionNames = new ArrayList<>(actionEntities.size());
        for (ActionEntity entity : actionEntities) {
            actionNames.add(entity.name);
        }
        return actionNames;
    }

    public List<String> getActionColorHex(List<ActionEntity> actionEntities) {
        List<String> actionColorHex = new ArrayList<>(actionEntities.size());
        for (ActionEntity entity : actionEntities) {
            actionColorHex.add(entity.colorHex);
        }
        return actionColorHex;
    }
}
