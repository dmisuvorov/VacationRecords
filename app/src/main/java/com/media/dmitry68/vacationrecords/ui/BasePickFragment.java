package com.media.dmitry68.vacationrecords.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.media.dmitry68.vacationrecords.R;

public abstract class BasePickFragment extends Fragment {
    protected AppCompatActivity parentActivity;
    protected FragmentManager fragmentManager;
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        parentActivity = (AppCompatActivity) getActivity();
        fragmentManager = getFragmentManager();
        super.onCreate(savedInstanceState);
    }

    protected void initToolbar() {
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        if (parentActivity != null) {
            parentActivity.setSupportActionBar(toolbar);
            ActionBar actionBar = parentActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (fragmentManager != null) {
                            fragmentManager.popBackStack();
                        }
                    }
                });
            }
        }
    }
}
