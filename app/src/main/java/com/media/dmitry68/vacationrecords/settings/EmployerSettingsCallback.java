package com.media.dmitry68.vacationrecords.settings;

import com.media.dmitry68.vacationrecords.employer.EmployerCallback;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;

public interface EmployerSettingsCallback extends EmployerCallback {
    void onDeleteEmployer(EmployerEntity employerEntity);

    void onAddEmployer(EmployerEntity employerEntity);

    void onUpdateEmployer(EmployerEntity employerEntity);

    void onDataNotAvailable();
}
