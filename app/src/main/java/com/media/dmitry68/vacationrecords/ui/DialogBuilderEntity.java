package com.media.dmitry68.vacationrecords.ui;

import android.content.Context;
import android.content.DialogInterface;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.R;

public class DialogBuilderEntity {
    private Context context;
    private String entity;
    private DialogBuilderCallback callback;

    public DialogBuilderEntity(Context context, DialogBuilderCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void showDialog(String textDialogTitle, String textHint, String textEntity) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_manager_entity, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextEntity = dialogView.findViewById(R.id.edit_text_entity);
        final TextInputLayout textInputLayout = dialogView.findViewById(R.id.manageTaskEntityLayout);

        editTextEntity.setText(textEntity, TextView.BufferType.EDITABLE);

        textInputLayout.setHint(textHint);

        dialogBuilder.setTitle(textDialogTitle);
        dialogBuilder.setPositiveButton(context.getString(R.string.btn_text_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                entity = editTextEntity.getText().toString().trim();
                callback.onDialogSetPositiveButton(entity);
            }
        });
        dialogBuilder.setNegativeButton(context.getString(R.string.btn_text_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
