package com.media.dmitry68.vacationrecords.settings;

import com.media.dmitry68.vacationrecords.action.ActionCallback;
import com.media.dmitry68.vacationrecords.action.ActionEntity;

public interface ActionSettingsCallback extends ActionCallback {
    void onDeleteAction(ActionEntity actionEntity);

    void onAddAction(ActionEntity actionEntity);

    void onDataNotAvailable();

}
