package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.*;

public class Day implements Comparable<Day> {

    final int day;
    final TreeSet<Event> events = new TreeSet<>();

    public Day(int day) {
        this.day = day;
    }

    @Override
    public int compareTo(Day o) {
        return this.day - o.day;
    }

    public void insert(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public String getDayOfWeek() {
        return events.first().getDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
    }
}
