package com.media.dmitry68.vacationrecords.action;

import com.media.dmitry68.vacationrecords.ApplicationVacation;
import com.media.dmitry68.vacationrecords.DatabaseVacation;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
    private List<ActionEntity> actionEntities;

    public ActionFactory() {
        DatabaseVacation databaseVacation = ApplicationVacation.getInstance().getDatabaseVacation();
        ActionDao actionDao = databaseVacation.actionDao();
    }

    public List<ActionEntity> getActionEntities() {
        return actionEntities;
    }

    public List<String> getActionNames() {
        List<String> actionNames = new ArrayList<>(actionEntities.size());
        for (ActionEntity entity : actionEntities) {
            actionNames.add(entity.name);
        }
        return actionNames;
    }

    public List<String> getActionColorHex() {
        List<String> actionColorHex = new ArrayList<>(actionEntities.size());
        for (ActionEntity entity : actionEntities) {
            actionColorHex.add(entity.colorHex);
        }
        return actionColorHex;
    }
}
