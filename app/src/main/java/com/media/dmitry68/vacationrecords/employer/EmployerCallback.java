package com.media.dmitry68.vacationrecords.employer;

import java.util.List;

public interface EmployerCallback {
    void onEmployerLoaded(List<EmployerEntity> employerEntities);
}
