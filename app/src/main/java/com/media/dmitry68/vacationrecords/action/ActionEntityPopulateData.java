package com.media.dmitry68.vacationrecords.action;

import android.content.Context;
import com.media.dmitry68.vacationrecords.R;

public class ActionEntityPopulateData {
    private Context context;

    public ActionEntityPopulateData(Context context) {
        this.context = context;
    }

    public ActionEntity[] populateData() {
        return new ActionEntity[] {
                new ActionEntity(context.getResources().getString(R.string.vacation), "#00FF00"),
                new ActionEntity(context.getResources().getString(R.string.sickLeave), "#FF0000")
        };
    }
}
