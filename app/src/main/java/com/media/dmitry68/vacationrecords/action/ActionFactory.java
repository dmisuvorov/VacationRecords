package com.media.dmitry68.vacationrecords.action;

import com.media.dmitry68.vacationrecords.ApplicationVacation;
import com.media.dmitry68.vacationrecords.DatabaseVacation;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class ActionFactory {
    private ActionDao actionDao;
    private Flowable<List<ActionEntity>> flowableActionEntity;

    public ActionFactory() {
        DatabaseVacation databaseVacation = ApplicationVacation.getInstance().getDatabaseVacation();
        actionDao = databaseVacation.actionDao();
        flowableActionEntity = actionDao.getAll();
    }

    public Flowable<List<ActionEntity>> getFlowableActionEntity() {
        return flowableActionEntity;
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
