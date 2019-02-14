package com.media.dmitry68.vacationrecords.calendar;

import java.util.Calendar;

public interface OnFragmentCalendarInteractionListener {
    void onStartDatePick(Calendar startDate);

    void onDateRangePick(Calendar startDate, Calendar endDate);
}
