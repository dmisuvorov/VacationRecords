package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;

import java.util.List;

public class ActionListAdapter extends ActionAdapter implements BaseVacationAdapter {
    private SparseBooleanArray selectedActionEntities;

    public ActionListAdapter(Context context, List<ActionEntity> actionEntities) {
        super(context, actionEntities);
        selectedActionEntities = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ActionViewHolder actionViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_action_item, parent, false);
            actionViewHolder = new ActionViewHolder(convertView, R.id.text_action, R.id.color_image_action);
            convertView.setTag(actionViewHolder);
        } else {
            actionViewHolder = (ActionViewHolder) convertView.getTag();
        }
        ActionEntity actionEntity = actionEntities.get(position);

        ImageView imageColorAction = actionViewHolder.getColorImageView();
        TextView textAction = actionViewHolder.getActionTextView();

        imageColorAction.setBackgroundColor(Color.parseColor(actionEntity.getColorHex()));
        textAction.setText(actionEntity.getName());

        convertView.setBackgroundColor(selectedActionEntities.get(position) ? 0x9934B5E4 : Color.TRANSPARENT);

        return convertView;
    }

    @Override
    public void removeSelection() {
        selectedActionEntities = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedActionEntities() {
        return selectedActionEntities;
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedActionEntities.get(position));
    }

    public int getSelectedCount() {
        return selectedActionEntities.size();
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
