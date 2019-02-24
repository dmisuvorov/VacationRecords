package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;
import com.media.dmitry68.vacationrecords.action.ActionFactory;
import com.media.dmitry68.vacationrecords.adapters.ActionAdapterCallback;
import com.media.dmitry68.vacationrecords.adapters.ActionListAdapter;
import com.media.dmitry68.vacationrecords.ui.BaseListFragment;
import com.media.dmitry68.vacationrecords.action.DialogBuilderAddAction;
import com.media.dmitry68.vacationrecords.ui.ToolbarActionMode;

import java.util.List;

public class SettingsActionFragment extends BaseListFragment implements ActionSettingsCallback, ActionAdapterCallback {
    public static final String SETTINGS_ACTION_FRAGMENT_TAG = "settings_action_fragment_tag";
    private ActionListAdapter actionAdapter;
    private ActionFactory actionFactory = new ActionFactory();
    private List<ActionEntity> actionEntities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        actionFactory.getActionEntities(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActionLoaded(List<ActionEntity> actionEntities) {
        this.actionEntities = actionEntities;
        initList();
    }

    @Override
    public void onDeleteAction(ActionEntity actionEntity) {
        actionEntities.remove(actionEntity);
        actionAdapter.updateAdapter();
    }

    @Override
    public void onAddAction(ActionEntity actionEntity) {
        actionEntities.add(actionEntity);
        actionAdapter.updateAdapter();
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

    @Override
    protected void initList() {
        actionAdapter = new ActionListAdapter(getContext(), actionEntities, this);
        entityListView.setAdapter(actionAdapter);
        implementListViewClickListener();
    }

    private void implementListViewClickListener() {
        entityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    onListItemSelect(position);
                }
            }
        });
        entityListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
