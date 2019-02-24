package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.ui.SelectorForListOfEntities;

import java.util.List;

public class EmployerListAdapter extends ArrayAdapter<EmployerEntity> implements BaseVacationAdapter{
    private Context context;
    private EmployerAdapterCallback employerAdapterCallback;
    private List<EmployerEntity> employerEntities;
    private SelectorForListOfEntities selectorOfActionEntities;

    public EmployerListAdapter(Context context, List<EmployerEntity> employerEntities, EmployerAdapterCallback employerAdapterCallback) {
        super(context, 0, employerEntities);
        this.context = context;
        this.employerAdapterCallback = employerAdapterCallback;
        this.employerEntities = employerEntities;
        this.selectorOfActionEntities = new SelectorForListOfEntities();
    }

    @Override
    public void removeSelection() {

    }

    @Override
    public void updateAdapter() {
        notifyDataSetChanged();
    }
}
