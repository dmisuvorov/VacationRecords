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
import com.media.dmitry68.vacationrecords.ui.DialogBuilderCallback;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderEntity;
import com.media.dmitry68.vacationrecords.ui.SelectorForListOfEntities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class EmployerListAdapter extends ArrayAdapter<EmployerEntity> implements BaseVacationAdapter, DialogBuilderCallback {
    private Context context;
    private EmployerListAdapterCallback employerAdapterCallback;
    private List<EmployerEntity> employerEntities;
    private SelectorForListOfEntities selectorOfActionEntities;
    private DialogBuilderEntity dialogBuilderUpdateEmployer;
    private int updatePosition;

    public EmployerListAdapter(Context context, List<EmployerEntity> employerEntities, EmployerListAdapterCallback employerAdapterCallback) {
        super(context, 0, employerEntities);
        this.context = context;
        this.employerAdapterCallback = employerAdapterCallback;
        this.employerEntities = employerEntities;
        this.selectorOfActionEntities = new SelectorForListOfEntities();
        dialogBuilderUpdateEmployer  = new DialogBuilderEntity(context, this);
        sortEmployerListByName();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        EmployerViewHolder employerViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater
                    .inflate(R.layout.listview_employer_item, parent, false);
            employerViewHolder = new EmployerViewHolder(convertView, R.id.text_employer);
            convertView.setTag(employerViewHolder);
        } else {
            employerViewHolder = (EmployerViewHolder) convertView.getTag();
        }

        final TextView textEmployer = employerViewHolder.getEmployerText();
        textEmployer.setText(employerEntities.get(position).getName());
        textEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePosition = position;
                dialogBuilderUpdateEmployer.showDialog(context.getString(R.string.title_dialog_update_employer),
                        context.getString(R.string.employer), textEmployer.getText().toString());
            }
        });

        convertView.setBackgroundColor(selectorOfActionEntities.getSelectedPosition(position) ? 0x9934B5E4 : Color.TRANSPARENT);
        return convertView;
    }

    @Override
    public void onDialogSetPositiveButton(String entityName) {
        employerAdapterCallback.onUpdateEmployer(employerEntities.get(updatePosition), entityName);
    }

    @Override
    public void removeSelection() {
        selectorOfActionEntities.updateSelectedEntities();
        updateAdapter();
    }

    @Override
    public void updateAdapter() {
        sortEmployerListByName();
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

    private void sortEmployerListByName() {
        Collections.sort(employerEntities, new Comparator<EmployerEntity>() {
            @Override
            public int compare(EmployerEntity o1, EmployerEntity o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
