package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.media.dmitry68.vacationrecords.adapters.EmployerAdapterCallback;
import com.media.dmitry68.vacationrecords.adapters.EmployerListAdapter;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.employer.EmployerFactory;
import com.media.dmitry68.vacationrecords.ui.BaseListFragment;

import java.util.List;

public class SettingsEmployerFragment extends BaseListFragment implements EmployerSettingsCallback, EmployerAdapterCallback {
    public static final String SETTINGS_EMPLOYER_FRAGMENT_TAG = "settings_employer_fragment_tag";
    private EmployerFactory employerFactory = new EmployerFactory();
    private List<EmployerEntity> employerEntities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        employerFactory.getEmployerEntities(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onEmployerLoaded(List<EmployerEntity> employerEntities) {
        this.employerEntities = employerEntities;
        initList();
    }

    @Override
    protected void initList() {
        entityAdapter = new EmployerListAdapter(getContext(), employerEntities, this);
        entityListView.setAdapter((EmployerListAdapter) entityAdapter);
        implementListViewClickListener(entityAdapter);
    }

    @Override
    public void onDeleteEmployer(EmployerEntity employerEntity) {

        entityAdapter.updateAdapter();
    }

    @Override
    public void onAddEmployer(EmployerEntity employerEntity) {

        entityAdapter.updateAdapter();
    }

    @Override
    public void onUpdateEmployer(EmployerEntity employerEntity) {

    }

    @Override
    public void onDataNotAvailable() {

    }

    @Override
    public void deleteRows() {

    }

    @Override
    public void setNullToActionMode() {

    }

    @Override
    public void onDialogSetPositiveButton(String actionName) {

    }

    @Override
    public void onPickEmployer(EmployerEntity employerEntity) {

    }
}
