package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.adapters.EmployerListAdapterCallback;
import com.media.dmitry68.vacationrecords.adapters.EmployerListAdapter;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;
import com.media.dmitry68.vacationrecords.employer.EmployerFactory;
import com.media.dmitry68.vacationrecords.ui.BaseListFragment;
import com.media.dmitry68.vacationrecords.ui.DialogBuilderEntity;

import java.util.List;

public class SettingsEmployerFragment extends BaseListFragment implements EmployerSettingsCallback, EmployerListAdapterCallback {
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
        if (getActivity() != null)
            initList();
    }

    @Override
    protected void initList() {
        entityAdapter = new EmployerListAdapter(getActivity(), employerEntities, this);
        entityListView.setAdapter((EmployerListAdapter) entityAdapter);
        implementListViewClickListener(entityAdapter);
    }

    @Override
    public void onDeleteEmployer(EmployerEntity employerEntity) {
        employerEntities.remove(employerEntity);
        entityAdapter.updateAdapter();
    }

    @Override
    public void onAddEmployer(EmployerEntity employerEntity) {
        employerEntities.add(employerEntity);
        entityAdapter.updateAdapter();
    }

    @Override
    public void onUpdateEmployer(EmployerEntity employerEntity) {
        employerEntities.set(employerEntities.indexOf(employerEntity), employerEntity);
        entityAdapter.updateAdapter();
    }

    @Override
    public void onDataNotAvailable() {
        throw new IllegalStateException("Error: EmployerEntity not available");
    }

    @Override
    protected void deleteEntity(SparseBooleanArray selected) {
        for (int i = (selected.size() - 1); i >= 0; i--) {
            employerFactory.deleteEmployerEntity(this, employerEntities.get(selected.keyAt(i)));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add:
                DialogBuilderEntity dialogBuilderAddEmployer = new DialogBuilderEntity(getContext(), this);
                dialogBuilderAddEmployer.showDialog(getString(R.string.title_dialog_new_employer), getString(R.string.employer), "");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogSetPositiveButton(String employerName) {
        employerFactory.addEmployerEntity(this, employerName);
    }

    @Override
    public void onUpdateEmployer(EmployerEntity updateEmployerEntity, String newEmployerName) {
        employerFactory.updateEmployerEntityName(this, updateEmployerEntity, newEmployerName);
    }
}
