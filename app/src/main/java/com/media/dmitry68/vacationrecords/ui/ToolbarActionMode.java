package com.media.dmitry68.vacationrecords.ui;


import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.adapters.BaseVacationAdapter;

public class ToolbarActionMode implements ActionMode.Callback {
    private ToolbarActionModeCallback toolbarActionModeCallback;
    private BaseVacationAdapter baseVacationAdapter;

    ToolbarActionMode(ToolbarActionModeCallback toolbarActionModeCallback, BaseVacationAdapter baseVacationAdapter) {
        this.toolbarActionModeCallback = toolbarActionModeCallback;
        this.baseVacationAdapter = baseVacationAdapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.findItem(R.id.menu_action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_delete:
                toolbarActionModeCallback.deleteRows();
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        baseVacationAdapter.removeSelection();
        toolbarActionModeCallback.setNullToActionMode();
    }
}
