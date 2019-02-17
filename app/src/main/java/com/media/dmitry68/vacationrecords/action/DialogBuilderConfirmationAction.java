package com.media.dmitry68.vacationrecords.action;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.media.dmitry68.vacationrecords.adapters.ActionAdapter;

import java.util.List;

public class DialogBuilderConfirmationAction { //https://stackoverflow.com/questions/11664167/android-how-to-have-list-of-checkbox-with-image-in-alertdialog
    private Context context;
    private List<ActionEntity> actionEntities;
    private ActionAdapter actionAdapter;

    public DialogBuilderConfirmationAction(Context context, List<ActionEntity> actionEntities) {
        this.context = context;
        this.actionEntities = actionEntities;
    }

    public void showDialog() {
        actionAdapter = new ActionAdapter(context, actionEntities);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setSingleChoiceItems(actionAdapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
