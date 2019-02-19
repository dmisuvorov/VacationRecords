package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.view.View;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.employer.EmployerFactory;

import java.util.List;

import co.ceryle.fitgridview.FitGridAdapter;

public class EmployerGridAdapter extends FitGridAdapter  {
    private EmployerFactory employerFactory = new EmployerFactory();
    private List<EmployerEntity> employerEntities;
    private Context context;

    public EmployerGridAdapter(Context context, List<EmployerEntity> employerEntities) {
        super(context, R.layout.grid_item);
        this.context = context;
        this.employerEntities = employerEntities;
    }

    @Override
    public void onBindView(int position, View view) {

    }
}
