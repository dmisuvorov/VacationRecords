package com.media.dmitry68.vacationrecords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton btnPickDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPickDate = findViewById(R.id.btn_pick_date);
    }
}
