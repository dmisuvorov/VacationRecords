package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckedTextView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionEntity;
import com.media.dmitry68.vacationrecords.color.ColorImageFactory;

import java.util.List;

public class ActionPickAdapter extends ActionAdapter {
    private ColorImageFactory colorImageFactory;
    private int selectedPosition = 0;

    public ActionPickAdapter(Context context, List<ActionEntity> actionEntities) {
        super(context, actionEntities);
        colorImageFactory = new ColorImageFactory(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ActionViewHolder actionViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.checked_action_item, null);
            actionViewHolder = new ActionViewHolder(convertView, R.id.checked_action_text);
            convertView.setTag(actionViewHolder);
        } else {
            actionViewHolder = (ActionViewHolder) convertView.getTag();
        }
        ActionEntity actionEntity = actionEntities.get(position);

        Drawable colorImage = colorImageFactory.getImageColor(actionEntity.getColorHex());

        final CheckedTextView checkedTextView = actionViewHolder.getCheckedTextView();
        checkedTextView.setText(actionEntity.getName());
        checkedTextView.setCompoundDrawablesWithIntrinsicBounds(colorImage, null, null, null);
        checkedTextView.setChecked(position == selectedPosition);
        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
