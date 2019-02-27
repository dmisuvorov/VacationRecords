package com.media.dmitry68.vacationrecords.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class EmployerViewHolder {
    private TextView employerTextView;

    EmployerViewHolder() {
    }

    EmployerViewHolder(View view, int textResource) {
        employerTextView = view.findViewById(textResource);
    }

    TextView getEmployerText() {
        return employerTextView;
    }

    Button getEmployerButton(View view) {
        return view.findViewById(com.media.dmitry68.vacationrecords.R.id.gridButton);
    }
}
