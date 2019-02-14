package com.media.dmitry68.vacationrecords.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarFactory {
    private Calendar startDateCalendar;
    private Calendar endDateCalendar;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    public void setStartDateCalendar(Calendar startDateCalendar) {
        this.startDateCalendar = startDateCalendar;
    }

    public void setEndDateCalendar(Calendar endDateCalendar) {
        this.endDateCalendar = endDateCalendar;
    }

    public String getStartDateString() {
        return simpleDateFormat.format(startDateCalendar.getTime());
    }

    public String getEndDateString() {
        return simpleDateFormat.format(endDateCalendar.getTime());
    }
}
