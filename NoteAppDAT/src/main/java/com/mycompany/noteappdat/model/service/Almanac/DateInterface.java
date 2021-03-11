package com.mycompany.noteappdat.model.service.Almanac;

import java.util.Calendar;

public interface DateInterface extends Comparable<DateInterface> {

    int getHour();

    int getMin();

    Calendar getDate();
    int getYear();
    int getMonth();
    int getWeek();
    int getDay();
}
