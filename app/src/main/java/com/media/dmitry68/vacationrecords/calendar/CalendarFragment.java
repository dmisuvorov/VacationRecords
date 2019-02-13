package com.media.dmitry68.vacationrecords.calendar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.dmitry68.vacationrecords.R;

public class CalendarFragment extends Fragment {
    public static final String CALENDAR_FRAGMENT_TAG = "calendar_fragment_tag";
    private static final String LOG = "TAG";
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View calendarView = inflater.inflate(R.layout.fragment_calendar, container, false);
        fragmentManager = getFragmentManager();
        initButton(calendarView);
        return calendarView;
    }

    private void initButton(View rootView) {
        AppCompatButton btnDone = rootView.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager != null) {
                    Log.d(LOG, "FragmentCalendar: popbackstack()");
                    fragmentManager.popBackStack();
                }
            }
        });
    }
}
