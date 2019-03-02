package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;
import com.media.dmitry68.vacationrecords.action.ActionFactory;
import com.media.dmitry68.vacationrecords.adapters.ActionAdapterCallback;
import com.media.dmitry68.vacationrecords.adapters.ActionListAdapter;
import com.media.dmitry68.vacationrecords.ui.BaseListFragment;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderEntity;

import java.util.List;

public class SettingsActionFragment extends BaseListFragment implements ActionSettingsCallback, ActionAdapterCallback {
    public static final String SETTINGS_ACTION_FRAGMENT_TAG = "settings_action_fragment_tag";
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
        if (getActivity() != null)
            initList();
    }

    @Override
    protected void initList() {
        entityAdapter = new ActionListAdapter(getActivity(), actionEntities, this);
        entityListView.setAdapter((ActionListAdapter) entityAdapter);
        implementListViewClickListener(entityAdapter);
    }

    @Override
    public void onDeleteAction(ActionEntity actionEntity) {
        actionEntities.remove(actionEntity);
        entityAdapter.updateAdapter();
    }

    @Override
    public void onAddAction(ActionEntity actionEntity) {
        actionEntities.add(actionEntity);
        entityAdapter.updateAdapter();
    }

    @Override
    public void onUpdateAction(ActionEntity actionEntity) {
        actionEntities.set(actionEntities.indexOf(actionEntity), actionEntity);
        entityAdapter.updateAdapter();
    }

    @Override
    public void onDataNotAvailable() {
        throw new IllegalStateException("Error: ActionEntity not available");
    }

    @Override
    public void deleteEntity(SparseBooleanArray selected) {
        for (int i = (selected.size() - 1); i >= 0; i--) {
            actionFactory.deleteActionEntity(this, actionEntities.get(selected.keyAt(i)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add:
                DialogBuilderEntity dialogBuilderAddAction = new DialogBuilderEntity(getContext(), this);
                dialogBuilderAddAction.showDialog(getString(R.string.title_dialog_new_action), getString(R.string.action), "");
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
}
