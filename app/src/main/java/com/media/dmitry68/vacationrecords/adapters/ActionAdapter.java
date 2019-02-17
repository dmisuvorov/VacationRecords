package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;

import java.util.List;

public class ActionAdapter extends ArrayAdapter<ActionEntity> {
    private Context context;
    private List<ActionEntity> actionEntities;

    public ActionAdapter(Context context, List<ActionEntity> actionEntities) {
        super(context, 0, actionEntities);
        this.context = context;
        this.actionEntities = actionEntities;
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
}
