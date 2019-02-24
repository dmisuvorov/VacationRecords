package com.media.dmitry68.vacationrecords.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.media.dmitry68.vacationrecords.R;

public abstract class BaseListFragment extends Fragment implements ToolbarActionModeCallback, DialogBuilderCallback {
    protected AppCompatActivity parentActivity;
    protected FragmentManager fragmentManager;
    protected ActionMode actionMode;
    protected View rootView;
    protected ListView entityListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        parentActivity = (AppCompatActivity) getActivity();
        fragmentManager = getFragmentManager();
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_entity_settings, container, false);
        entityListView = rootView.findViewById(R.id.entity_list_view);
        initToolbar();
        return rootView;
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

    protected abstract void initList();
}
