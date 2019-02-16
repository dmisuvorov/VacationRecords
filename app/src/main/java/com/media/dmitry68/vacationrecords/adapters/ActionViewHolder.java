package com.media.dmitry68.vacationrecords.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.R;

class ActionViewHolder {
    private TextView actionTextView;
    private ImageView colorImageView;

    ActionViewHolder(View view) {
        actionTextView = view.findViewById(R.id.text_action);
        colorImageView = view.findViewById(R.id.color_image_action);
    }

    TextView getActionTextView() {
        return actionTextView;
    }

    ImageView getColorImageView() {
        return colorImageView;
    }
}
