package com.media.dmitry68.vacationrecords.action;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderCallback;

public class DialogBuilderAddAction {
    private Context context;
    private String actionName;
    private DialogBuilderCallback callback;

    public DialogBuilderAddAction(Context context, DialogBuilderCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_add_action, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextAction = dialogView.findViewById(R.id.edit_text_action);

        dialogBuilder.setTitle(R.string.title_dialog_new_action);
        dialogBuilder.setPositiveButton(context.getString(R.string.btn_text_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                actionName = editTextAction.getText().toString().trim();
                callback.onDialogSetPositiveButton(actionName);
            }
        });
        dialogBuilder.setNegativeButton(context.getString(R.string.btn_text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
