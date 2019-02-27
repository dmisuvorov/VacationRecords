package com.media.dmitry68.vacationrecords.adapters;

import android.util.SparseBooleanArray;

public interface BaseVacationAdapter {
    void removeSelection();

    void updateAdapter();

    SparseBooleanArray getSelectedActionEntities();

    void toggleSelection(int position);

    int getSelectedCount();
}
