package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;
import com.media.dmitry68.vacationrecords.action.ActionFactory;
import com.media.dmitry68.vacationrecords.adapters.ActionAdapterCallback;
import com.media.dmitry68.vacationrecords.adapters.ActionListAdapter;
import com.media.dmitry68.vacationrecords.ui.BasePickFragment;
import com.media.dmitry68.vacationrecords.action.DialogBuilderAddAction;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderCallback;
import com.media.dmitry68.vacationrecords.ui.ToolbarActionMode;
import com.media.dmitry68.vacationrecords.ui.ToolbarActionModeCallback;

import java.util.List;

public class SettingsActionFragment extends BasePickFragment implements ActionSettingsCallback, ToolbarActionModeCallback, DialogBuilderCallback, ActionAdapterCallback {
    public static final String SETTINGS_ACTION_FRAGMENT_TAG = "settings_action_fragment_tag";
    private ActionMode actionMode;
    private ActionListAdapter actionAdapter;
    private ListView actionListView;
    private ActionFactory actionFactory = new ActionFactory();
    private List<ActionEntity> actionEntities;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_action_settings, container, false);
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
    public void onAddAction(ActionEntity actionEntity) {
        actionEntities.add(actionEntity);
        actionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateAction(ActionEntity actionEntity) {
        actionEntities.set(actionEntities.indexOf(actionEntity), actionEntity);
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
        actionMode.finish();
    }

    @Override
    public void setNullToActionMode() {
        if (actionMode != null) {
            actionMode = null;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        parentActivity.getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add:
                DialogBuilderAddAction dialogBuilderAddAction = new DialogBuilderAddAction(getContext(), this);
                dialogBuilderAddAction.showDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogSetPositiveButton(String actionName) {
        actionFactory.addActionEntity(this, actionName);
    }

    @Override
    public void onUpdateItemColor(ActionEntity updateActionEntity, String newColorHex) {
        actionFactory.updateActionEntityColor(this, updateActionEntity, newColorHex);
    }

    private void initList() {
        actionAdapter = new ActionListAdapter(getContext(), actionEntities, this);
        actionListView = rootView.findViewById(R.id.action_list_view);
        actionListView.setAdapter(actionAdapter);
        implementListViewClickListener();
    }

    private void implementListViewClickListener() {
        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    onListItemSelect(position);
                }
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
