package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.media.dmitry68.vacationrecords.action.ActionEntity;

import java.util.List;

public class ActionAdapter extends ArrayAdapter<ActionEntity> {
    protected Context context;
    List<ActionEntity> actionEntities;

    ActionAdapter(Context context, List<ActionEntity> actionEntities) {
        super(context, 0, actionEntities);
        this.context = context;
        this.actionEntities = actionEntities;
    }


}
