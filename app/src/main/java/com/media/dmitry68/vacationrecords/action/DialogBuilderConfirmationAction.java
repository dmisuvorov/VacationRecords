package com.media.dmitry68.vacationrecords.action;

import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.adapters.ActionPickAdapter;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderCallback;

import java.util.List;

public class DialogBuilderConfirmationAction implements ActionCallback {
    private Context context;
    private DialogBuilderCallback callback;
    private ActionFactory actionFactory = new ActionFactory();
    private List<ActionEntity> actionEntities;

    public DialogBuilderConfirmationAction(Context context) {
        this.context = context;
        if (context instanceof DialogBuilderCallback) {
            callback = (DialogBuilderCallback) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement DialogBuilderCallback");
        }
    }

    public void showDialog() {
        actionFactory.getActionEntities(this);
    }

    @Override
    public void onActionLoaded(List<ActionEntity> actionEntities) {
        this.actionEntities = actionEntities;
        if (!actionEntities.isEmpty())
            showDialogOnActionLoaded();
    }

    private void showDialogOnActionLoaded() {
        final ActionPickAdapter actionAdapter = new ActionPickAdapter(context, actionEntities);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.title_dialog_pick_action);

        builder.setSingleChoiceItems(actionAdapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("TAG", "set item" + which);
            }
        });
        String positiveText = context.getString(R.string.btn_text_ok);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onDialogSetPositiveButton(actionEntities.get(actionAdapter.getSelectedPosition()).getName());
            }
        });
        String negativeText = context.getString(R.string.btn_text_cancel);
        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
