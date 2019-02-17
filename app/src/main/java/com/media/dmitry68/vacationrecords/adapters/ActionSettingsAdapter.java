package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import com.media.dmitry68.vacationrecords.action.ActionEntity;

import java.util.List;

public class ActionSettingsAdapter extends ActionAdapter implements BaseVacationAdapter {
    private SparseBooleanArray selectedActionEntities;

    public ActionSettingsAdapter(Context context, List<ActionEntity> actionEntities) {
        super(context, actionEntities);
        selectedActionEntities = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        convertView.setBackgroundColor(selectedActionEntities.get(position) ? 0x9934B5E4 : Color.TRANSPARENT);
        return view;
    }

    @Override
    public void removeSelection() {
        selectedActionEntities = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedActionEntities.get(position));
    }

    public int getSelectedCount() {
        return selectedActionEntities.size();
    }

    public SparseBooleanArray getSelectedActionEntities() {
        return selectedActionEntities;
    }

    private void selectView(int position, boolean value) {
        if (value) {
            selectedActionEntities.put(position, true);
        } else {
            selectedActionEntities.delete(position);
        }
        notifyDataSetChanged();
    }
}
