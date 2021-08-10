package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Month implements Comparable<Month> {

    final int month;
    final TreeMap<Integer, Week> weeks = new TreeMap<>();

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
}