package com.media.dmitry68.vacationrecords.ui;

import android.util.SparseBooleanArray;

public class SelectorForListOfEntities {
    private SparseBooleanArray selectedEntities;


    public SelectorForListOfEntities() {
        updateSelectedEntities();
    }

    public void updateSelectedEntities() {
        selectedEntities = new SparseBooleanArray();
    }

    public SparseBooleanArray getSelectedEntities() {
        return selectedEntities;
    }

    public void toggleSelection(int position) {
        selectView(position, !getSelectedPosition(position));
    }

    public boolean getSelectedPosition(int position) {
        return selectedEntities.get(position);
    }

    public int getSelectedCount() {
        return selectedEntities.size();
    }

    private void selectView(int position, boolean value) {
        if (value) {
            selectedEntities.put(position, true);
        } else {
            selectedEntities.delete(position);
        }
    }
}
