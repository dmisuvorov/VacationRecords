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
import com.media.dmitry68.vacationrecords.ui.SelectorForListOfEntities;

import java.util.List;

public class ActionListAdapter extends ActionAdapter implements BaseVacationAdapter, ColorCallback {
    private SelectorForListOfEntities selectorOfActionEntities;
    private ActionAdapterCallback actionAdapterCallback;
    private ColorPickPopup colorPickPopup;
    private int updatePosition;

    public ActionListAdapter(Context context, List<ActionEntity> actionEntities, ActionAdapterCallback actionAdapterCallback) {
        super(context, actionEntities);
        selectorOfActionEntities = new SelectorForListOfEntities();
        this.actionAdapterCallback = actionAdapterCallback;
        colorPickPopup = new ColorPickPopup(context, this);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ActionViewHolder actionViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater
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

        convertView.setBackgroundColor(selectorOfActionEntities.getSelectedPosition(position) ? 0x9934B5E4 : Color.TRANSPARENT);

        return convertView;
    }

    @Override
    public void removeSelection() {
        selectorOfActionEntities.updateSelectedEntities();
        updateAdapter();
    }

    @Override
    public void onColorPick(String colorHex) {
        actionAdapterCallback.onUpdateItemColor(actionEntities.get(updatePosition), colorHex);
    }

    @Override
    public void updateAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public SparseBooleanArray getSelectedActionEntities() {
        return selectorOfActionEntities.getSelectedEntities();
    }

    @Override
    public void toggleSelection(int position) {
        selectorOfActionEntities.toggleSelection(position);
        notifyDataSetChanged();
    }

    @Override
    public int getSelectedCount() {
        return selectorOfActionEntities.getSelectedCount();
    }
}
