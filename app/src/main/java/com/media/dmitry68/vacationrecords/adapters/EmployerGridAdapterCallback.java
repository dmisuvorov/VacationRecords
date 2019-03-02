package com.media.dmitry68.vacationrecords.adapters;

import com.media.dmitry68.vacationrecords.employer.EmployerEntity;

public interface EmployerGridAdapterCallback {
    void onPickEmployer(EmployerEntity employerEntity);

    void updateGridAdapter(int numRows, int numColumns);
}
