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
import com.media.dmitry68.vacationrecords.color.ColorCallback;
import com.media.dmitry68.vacationrecords.color.ColorPickPopup;

import java.util.List;

public class ActionListAdapter extends ActionAdapter implements BaseVacationAdapter, ColorCallback {
    private SparseBooleanArray selectedActionEntities;
    private ActionAdapterCallback actionAdapterCallback;
    private ColorPickPopup colorPickPopup;
    private int updatePosition;

    public ActionListAdapter(Context context, List<ActionEntity> actionEntities, ActionAdapterCallback actionAdapterCallback) {
        super(context, actionEntities);
        selectedActionEntities = new SparseBooleanArray();
        this.actionAdapterCallback = actionAdapterCallback;
        colorPickPopup = new ColorPickPopup(context, this);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ActionViewHolder actionViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_action_item, parent, false);
            actionViewHolder = new ActionViewHolder(convertView, R.id.text_action, R.id.color_image_action);
            convertView.setTag(actionViewHolder);
        } else {
            actionViewHolder = (ActionViewHolder) convertView.getTag();
        }
        final ActionEntity actionEntity = actionEntities.get(position);

        ImageView imageColorAction = actionViewHolder.getColorImageView();
        TextView textAction = actionViewHolder.getActionTextView();

        imageColorAction.setBackgroundColor(Color.parseColor(actionEntity.getColorHex()));
        imageColorAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePosition = position;
                colorPickPopup.setColorHex(actionEntity.getColorHex());
                colorPickPopup.build();
            }
        });
        textAction.setText(actionEntity.getName());

        convertView.setBackgroundColor(selectedActionEntities.get(position) ? 0x9934B5E4 : Color.TRANSPARENT);

        return convertView;
    }

    @Override
    public void removeSelection() {
        selectedActionEntities = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    @Override
    public void onColorPick(String colorHex) {
        actionAdapterCallback.onUpdateItemColor(actionEntities.get(updatePosition), colorHex);
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
