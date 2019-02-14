package com.media.dmitry68.vacationrecords.calendar;

import android.content.Context;
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

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.media.dmitry68.vacationrecords.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment {
    public static final String CALENDAR_FRAGMENT_TAG = "calendar_fragment_tag";
    private static final String LOG = "TAG";
    private OnFragmentCalendarInteractionListener calendarInteractionListener;
    private FragmentManager fragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentCalendarInteractionListener) {
            calendarInteractionListener = (OnFragmentCalendarInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement OnFragmentCalendarInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View calendarView = inflater.inflate(R.layout.fragment_calendar, container, false);
        fragmentManager = getFragmentManager();
        initCalendar(calendarView);
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

    private void initCalendar(View rootView) {
        DateRangeCalendarView calendarView = rootView.findViewById(R.id.calendarView);
        calendarView.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
                calendarInteractionListener.onStartDatePick(startDate);
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                calendarInteractionListener.onDateRangePick(startDate, endDate);
            }
        });
    }
}
