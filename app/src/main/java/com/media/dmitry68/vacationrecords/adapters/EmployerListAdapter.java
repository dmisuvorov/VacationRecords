package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.ui.SelectorForListOfEntities;

import java.util.List;


public class EmployerListAdapter extends ArrayAdapter<EmployerEntity> implements BaseVacationAdapter{
    private Context context;
    private EmployerAdapterCallback employerAdapterCallback;
    private List<EmployerEntity> employerEntities;
    private SelectorForListOfEntities selectorOfActionEntities;
    private int updatePosition;

    public EmployerListAdapter(Context context, List<EmployerEntity> employerEntities, EmployerAdapterCallback employerAdapterCallback) {
        super(context, 0, employerEntities);
        this.context = context;
        this.employerAdapterCallback = employerAdapterCallback;
        this.employerEntities = employerEntities;
        this.selectorOfActionEntities = new SelectorForListOfEntities();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        EmployerViewHolder employerViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.listview_employer_item, parent, false);
            employerViewHolder = new EmployerViewHolder(convertView, R.id.text_employer);
            convertView.setTag(employerViewHolder);
        } else {
            employerViewHolder = (EmployerViewHolder) convertView.getTag();
        }

        TextView textEmployer = employerViewHolder.getEmployerText();
        textEmployer.setText(employerEntities.get(position).getName());
        textEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePosition = position;
            }
        });

        convertView.setBackgroundColor(selectorOfActionEntities.getSelectedPosition(position) ? 0x9934B5E4 : Color.TRANSPARENT);
        return convertView;
    }

    @Override
    public void removeSelection() {
        selectorOfActionEntities.updateSelectedEntities();
        updateAdapter();
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
