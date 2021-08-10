package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.*;

public class Week implements Comparable<Week> {

    final int week;
    private final TreeMap<Integer, Day> days = new TreeMap<>();

    public Week(int week) {
        this.week = week;
    }

    @Override
    public int compareTo(Week o) {
        return this.week - o.week;
    }

    public void insert(Event event) {
        int eventDate = event.getDay();

        Day period = days.get(eventDate);
        if (period == null) period = new Day(eventDate);

        period.insert(event);
        days.put(eventDate, period);
    }

    public List<Day> getDays() {
        return new ArrayList<>(days.values());
    }

    public Day getDay(int day) {
        return days.get(day);
    }

    public int getIntegerRepresentation() {
        return week;
    }
}
