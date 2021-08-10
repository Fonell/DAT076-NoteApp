package com.mycompany.noteappdat.model.service.Almanac;

import java.util.Calendar;

public interface DateInterface extends Comparable<DateInterface> {
    Calendar getDate();
    int getYear();
    int getMonth();
    int getWeek();
    int getDay();
    int getHour();
    int getMin();
}
