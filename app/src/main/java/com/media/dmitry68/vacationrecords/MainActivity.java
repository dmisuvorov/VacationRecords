package com.media.dmitry68.vacationrecords;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.action.DialogBuilderConfirmationAction;

import com.media.dmitry68.vacationrecords.adapters.EmployerGridAdapter;
import com.media.dmitry68.vacationrecords.adapters.EmployerGridAdapterCallback;
import com.media.dmitry68.vacationrecords.calendar.CalendarFactory;
import com.media.dmitry68.vacationrecords.calendar.CalendarFragment;
import com.media.dmitry68.vacationrecords.calendar.OnFragmentCalendarInteractionListener;
import com.media.dmitry68.vacationrecords.employer.EmployerCallback;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.employer.EmployerFactory;
import com.media.dmitry68.vacationrecords.settings.SettingsActivity;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderCallback;

import java.util.Calendar;
import java.util.List;

import co.ceryle.fitgridview.FitGridView;

public class MainActivity extends AppCompatActivity implements OnFragmentCalendarInteractionListener, DialogBuilderCallback, EmployerCallback, EmployerGridAdapterCallback {
    private static final int REQUEST_SETTINGS = 200;
    private CalendarFactory calendarFactory = new CalendarFactory();
    private TextView txtPickDate;
    private TextView txtPickAction;
    private TextView txtPickEmployer;
    private FitGridView fitGridView;
    private FragmentManager fragmentManager;
    private AppCompatActivity mainActivity;
    private EmployerGridAdapter fitGridAdapter;
    private EmployerFactory employerFactory = new EmployerFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        AppCompatButton btnPickDate = findViewById(R.id.btn_pick_date);
        AppCompatButton btnPickAction = findViewById(R.id.btn_pick_action);
        AppCompatButton btnReset = findViewById(R.id.btn_reset);
        AppCompatButton btnSettings = findViewById(R.id.btn_settings);
        txtPickDate = findViewById(R.id.txt_pick_date);
        txtPickAction = findViewById(R.id.txt_pick_action);
        txtPickEmployer = findViewById(R.id.txt_pick_employer);
        fragmentManager = getSupportFragmentManager();

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .add(android.R.id.content, new CalendarFragment())
                        .addToBackStack(CalendarFragment.CALENDAR_FRAGMENT_TAG)
                        .commit();
            }
        });
        btnPickAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBuilderConfirmationAction dialog = new DialogBuilderConfirmationAction(mainActivity);
                dialog.showDialog();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetView();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(settingsIntent, REQUEST_SETTINGS);
            }
        });

        loadListOfEmployers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadListOfEmployers();
    }

    @Override
    public void onEmployerLoaded(List<EmployerEntity> employerEntities) {
        this.fitGridAdapter = new EmployerGridAdapter(mainActivity, employerEntities);
        initGridView();
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

    @Override
    public void onPickEmployer(EmployerEntity employerEntity) {
        txtPickEmployer.setText(employerEntity.getName());
    }

    @Override
    public void onDialogSetPositiveButton(String actionName) {
        txtPickAction.setText(actionName);
    }

    @Override
    public void updateGridAdapter(int numRows, int numColumns) {
        fitGridView.setNumRows(numRows);
        fitGridView.setNumColumns(numColumns);
        updateGridView();
    }

    private void loadListOfEmployers() {
        employerFactory.getEmployerEntities(this);
    }

    private void initGridView() {
        fitGridView = findViewById(R.id.gridView);
        fitGridView.setFitGridAdapter(fitGridAdapter);
        resetView();
    }

    private void updateGridView() {
        fitGridView.update();
    }

    private void resetView() {
        fitGridAdapter.resetAdapter();
    }
}
