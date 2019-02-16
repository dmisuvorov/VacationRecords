package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;

import java.util.List;

public class ActionAdapter extends ArrayAdapter<ActionEntity> implements BaseVacationAdapter{
    private Context context;
    private List<ActionEntity> actionEntities;
    private SparseBooleanArray selectedActionEntities;

    public ActionAdapter(Context context, List<ActionEntity> actionEntities) {
        super(context, 0, actionEntities);
        this.context = context;
        this.actionEntities = actionEntities;
        selectedActionEntities = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_action_item, parent, false);
        }
        ActionViewHolder actionViewHolder = new ActionViewHolder(convertView);
        ActionEntity actionEntity = actionEntities.get(position);

        ImageView imageColorAction = actionViewHolder.getColorImageView();
        TextView textAction = actionViewHolder.getActionTextView();

        imageColorAction.setBackgroundColor(Color.parseColor(actionEntity.getColorHex()));
        textAction.setText(actionEntity.getName());

        return convertView;
    }

    @Override
    public void removeSelection() {
        selectedActionEntities = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedActionEntities.get(position));
    }

    void selectView(int position, boolean value) {
        if (value) {
            selectedActionEntities.put(position, value);
        } else {
            selectedActionEntities.delete(position);
        }
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return selectedActionEntities.size();
    }
    public SparseBooleanArray getSelectedActionEntities() {
        return selectedActionEntities;
    }
}
