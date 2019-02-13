package com.media.dmitry68.vacationrecords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.media.dmitry68.vacationrecords.calendar.CalendarFactory;
import com.media.dmitry68.vacationrecords.calendar.CalendarFragment;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton btnPickDate;
    private CalendarFactory calendarFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPickDate = findViewById(R.id.btn_pick_date);
        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new CalendarFragment())
                        .addToBackStack(CalendarFragment.CALENDAR_FRAGMENT_TAG)
                        .commit();
            }
        });
    }


}
