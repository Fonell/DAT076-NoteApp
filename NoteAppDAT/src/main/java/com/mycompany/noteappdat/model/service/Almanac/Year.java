package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Year implements Comparable<Year> {

    final int year;
    private final TreeMap<Integer, Month> months = new TreeMap<>();

    public Year(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Year o) {
        return this.year - o.year;
    }

    public void insert(Event event) {
        int eventDate = event.getMonth();

        Month period = months.get(eventDate);
        if (period == null) period = new Month(eventDate);

        period.insert(event);
        months.put(eventDate, period);
    }

    public List<Month> getMonths() {
        return new ArrayList<>(months.values());
    }

    public Month getMonth(int month) {
        return months.get(month);
    }

    public int getIntegerRepresentation() {
        return year;
    }
}
