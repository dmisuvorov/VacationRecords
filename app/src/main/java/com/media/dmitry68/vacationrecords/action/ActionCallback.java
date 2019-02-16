package com.media.dmitry68.vacationrecords.action;

import java.util.List;

public interface ActionCallback {
    void onActionLoaded(List<ActionEntity> actionEntities);

    void onDeleteAction(ActionEntity actionEntity);

    void onDataNotAvailable();
}
