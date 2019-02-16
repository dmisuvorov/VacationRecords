package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.adapters.ActionAdapter;
import com.media.dmitry68.vacationrecords.action.ActionCallback;
import com.media.dmitry68.vacationrecords.action.ActionEntity;
import com.media.dmitry68.vacationrecords.action.ActionFactory;
import com.media.dmitry68.vacationrecords.ui.ToolbarActionMode;
import com.media.dmitry68.vacationrecords.ui.ToolbarActionModeCallback;

import java.util.List;


public class SettingsActionFragment extends Fragment implements ActionCallback, ToolbarActionModeCallback {
    public static final String SETTINGS_ACTION_FRAGMENT_TAG = "settings_action_fragment_tag";
    private AppCompatActivity parentActivity;
    private View rootView;
    private ActionMode actionMode;
    private ActionAdapter actionAdapter;
    private ListView actionListView;
    private FragmentManager fragmentManager;
    private ActionFactory actionFactory = new ActionFactory();
    private List<ActionEntity> actionEntities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_action_settings, container, false);
        fragmentManager = getFragmentManager();
        parentActivity = (AppCompatActivity) getActivity();
        initToolbar();
        actionFactory.getActionEntities(this);
        return rootView;
    }

    @Override
    public void onActionLoaded(List<ActionEntity> actionEntities) {
        this.actionEntities = actionEntities;
        initList();
    }

    @Override
    public void onDeleteAction(ActionEntity actionEntity) {
        actionEntities.remove(actionEntity);
        actionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDataNotAvailable() {
        throw new IllegalStateException("Error: Data not available");
    }

    @Override
    public void deleteRows() {
        SparseBooleanArray selected = actionAdapter.getSelectedActionEntities();
        for (int i = (selected.size() - 1); i >= 0; i--) {
            actionFactory.deleteActionEntity(this, actionEntities.get(selected.keyAt(i)));
        }
    }

    @Override
    public void setNullToActionMode() {
        if (actionMode != null) {
            actionMode = null;
        }
    }

    private void initToolbar() {
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

    private void initList() {
        actionAdapter = new ActionAdapter(getContext(), actionEntities);
        actionListView = rootView.findViewById(R.id.action_list_view);
        actionListView.setAdapter(actionAdapter);
        implementListViewClickListener();
    }

    private void implementListViewClickListener() {
        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        actionListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemSelect(position);
                return true;
            }
        });
    }

    private void onListItemSelect(int position) {
        actionAdapter.toggleSelection(position);
        boolean hasCheckedItems = actionAdapter.getSelectedCount() > 0;
        if (hasCheckedItems && actionMode == null) {
            if (parentActivity != null) {
                actionMode = parentActivity.startSupportActionMode(new ToolbarActionMode(this, actionAdapter));
            }
        } else if (!hasCheckedItems && actionMode != null){
            actionMode.finish();
        }
    }
}
