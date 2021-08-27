package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.*;

public class Month implements Comparable<Month> {

    final int month;
    private final TreeMap<Integer, Week> weeks = new TreeMap<>();

    public Month(int month) {
        this.month = month;
    }


    @Override
    public int compareTo(Month o) {
        return this.month - o.month;
    }

    public void insert(Event event) {
        int eventDate = event.getWeek();

        Week period = weeks.get(eventDate);
        if (period == null) period = new Week(eventDate);

        period.insert(event);
        weeks.put(eventDate, period);
    }

    public List<Week> getWeeks() {
        return new ArrayList<>(weeks.values());
    }

    public Week getWeek(int week) {
        return weeks.get(week);
    }

    public List<Day> getDays() {
        List<Day> daysInMonth = new ArrayList<>();
        for (Week w : getWeeks()) {
            daysInMonth.addAll(w.getDays());
        }
        return daysInMonth;
    }

    public Day getDay(int day) {
        return getDays().get(day);
    }

    public int getIntegerRepresentation() {
        return month + 1;
    }

    public String getStringRepresentation() {
        return getDays().get(0).getEvents().get(0).event.getDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }
}