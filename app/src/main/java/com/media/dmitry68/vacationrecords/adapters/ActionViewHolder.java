package com.media.dmitry68.vacationrecords.adapters;

import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;


class ActionViewHolder {
    private TextView actionTextView;
    private ImageView colorImageView;
    private CheckedTextView checkedTextView;

    ActionViewHolder(View view, int textResource, int colorImageResource) {
        actionTextView = view.findViewById(textResource);
        colorImageView = view.findViewById(colorImageResource);
    }

    ActionViewHolder(View view, int textCheckedResource) {
        checkedTextView = view.findViewById(textCheckedResource);
    }

    TextView getActionTextView() {
        return actionTextView;
    }

    ImageView getColorImageView() {
        return colorImageView;
    }

    CheckedTextView getCheckedTextView() {
        return checkedTextView;
    }
}
