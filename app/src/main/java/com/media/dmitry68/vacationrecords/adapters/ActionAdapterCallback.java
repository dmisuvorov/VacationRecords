package com.media.dmitry68.vacationrecords.adapters;

import com.media.dmitry68.vacationrecords.action.ActionEntity;

public interface ActionAdapterCallback {
    void onUpdateItemColor(ActionEntity updateActionEntity, String newColorHex);
}
