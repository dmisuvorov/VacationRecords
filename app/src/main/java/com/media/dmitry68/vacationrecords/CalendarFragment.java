package com.media.dmitry68.vacationrecords;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarFragment extends Fragment {
    private static final String CALENDAR_FRAGMENT_TAG = "calendar_fragment_tag";
    private AppCompatButton btnDone;
    private FragmentManager fragmentManager = getFragmentManager();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View calendarView = inflater.inflate(R.layout.fragment_calendar, container, false);
        initButton(calendarView);
        return calendarView;
    }

    private void initButton(View rootView) {
        btnDone = rootView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager != null) {
                    fragmentManager.popBackStack();
                }
            }
        });
    }
}
