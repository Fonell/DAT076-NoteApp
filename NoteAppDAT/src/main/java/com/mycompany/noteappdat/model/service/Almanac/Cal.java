package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Cal {

    final TreeMap<Integer, Year> years = new TreeMap<>();

    public void insert(Event event) {
        int eventDate = event.getYear();

        Year period = years.get(eventDate);
        if (period == null) period = new Year(eventDate);

        period.insert(event);
        years.put(eventDate, period);
    }

    public List<Year> getYears() {
        return new ArrayList<>(years.values());
    }
}
