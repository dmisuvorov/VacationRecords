package com.media.dmitry68.vacationrecords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.calendar.CalendarFactory;
import com.media.dmitry68.vacationrecords.calendar.CalendarFragment;
import com.media.dmitry68.vacationrecords.calendar.OnFragmentCalendarInteractionListener;
import com.media.dmitry68.vacationrecords.settings.SettingsActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements OnFragmentCalendarInteractionListener {
    private static final int REQUEST_SETTINGS = 200;
    private CalendarFactory calendarFactory = new CalendarFactory();
    private TextView txtPickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton btnPickDate = findViewById(R.id.btn_pick_date);
        AppCompatButton btnReset = findViewById(R.id.btn_reset);
        AppCompatButton btnSettings = findViewById(R.id.btn_settings);
        txtPickDate = findViewById(R.id.txt_pick_date);
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new CalendarFragment())
                        .addToBackStack(CalendarFragment.CALENDAR_FRAGMENT_TAG)
                        .commit();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(settingsIntent, REQUEST_SETTINGS);
            }
        });
    }


    @Override
    public void onStartDatePick(Calendar startDate) {
        calendarFactory.setStartDateCalendar(startDate);
        txtPickDate.setText(calendarFactory.getStartDateString());
    }

    @Override
    public void onDateRangePick(Calendar startDate, Calendar endDate) {
        calendarFactory.setStartDateCalendar(startDate);
        calendarFactory.setEndDateCalendar(endDate);
        String txtPickDateString = calendarFactory.getStartDateString() + "-" + calendarFactory.getEndDateString();
        txtPickDate.setText(txtPickDateString);
    }
}
