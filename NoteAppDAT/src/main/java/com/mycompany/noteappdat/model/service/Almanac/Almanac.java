package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;

import java.util.*;

/**
 * A self sorting structure for storing and retrieving events.
 * Events can be inserted and removed without breaking the structure.
 * Events are grouped into years, months, weeks and days.
 *
 * @author Emil Holmsten
 */
public class Almanac<E extends DateInterface> {

    private final YearCollection years = new YearCollection();

    public Almanac(List<E> events) {
        for (E e : events) {
            insert(e);
        }
    }

    public void insert(E event) {
        years.insert(event);
    }

    public Day getDay(int year, int month, int day) {
        return years.getYear(year).getMonth(month).getDay(day);
    }

    public Week getWeek(int year, int month, int week) {
        return years.getYear(year).getMonth(month).getWeek(week);
    }

    public Month getMonth(int year, int month) {
        return years.getYear(year).getMonth(month);
    }

    public Year getYear(int year) {
        return years.getYear(year);
    }

    public List<Year> getYears() {
        return years.getYears();
    }

    private abstract class TimePeriod<T> {
        Map<Integer, T> periodMap = new TreeMap<>();

        public void insert(E event) {
            int eventDate = getDate(event);

            T period = periodMap.get(eventDate);
            if (period == null) {
                period = newPeriod();
                insertIntoPeriod(period, event);
                periodMap.put(eventDate, period);
                return;
            }
            insertIntoPeriod(period, event);
        }

        protected abstract void insertIntoPeriod(T period, E event);

        protected abstract T newPeriod();

        protected abstract Integer getDate(DateInterface event);
    }

    public class YearCollection extends TimePeriod<Year> {

        @Override
        protected void insertIntoPeriod(Year period, E event) {
            period.insert(event);
        }

        @Override
        protected Year newPeriod() {
            return new Year();
        }

        @Override
        protected Integer getDate(DateInterface event) {
            return event.getYear();
        }

        public Year getYear(int year) {
            return periodMap.get(year);
        }

        public List<Year> getYears() {
            return new ArrayList<>(periodMap.values());
        }
    }

    public class Year extends TimePeriod<Month> {
        @Override
        protected void insertIntoPeriod(Month period, E event) {
            period.insert(event);
        }

        @Override
        protected Month newPeriod() {
            return new Month();
        }

        @Override
        protected Integer getDate(DateInterface event) {
            return event.getMonth();
        }

        public Month getMonth(int month) {
            return periodMap.get(month);
        }

        public List<Month> getMonths() {
            return new ArrayList<>(periodMap.values());
        }
    }

    public class Month extends TimePeriod<Week> {

        @Override
        protected void insertIntoPeriod(Week period, E event) {
            period.insert(event);
        }

        @Override
        protected Week newPeriod() {
            return new Week();
        }

        @Override
        protected Integer getDate(DateInterface event) {
            return event.getWeek();
        }

        public Week getWeek(int week) {
            return periodMap.get(week);
        }

        public List<Week> getWeeks() {
            return new ArrayList<>(periodMap.values());
        }

        public Day getDay(int day) {
            return getDays().get(day);
        }

        public List<Day> getDays() {
            List<Day> days = new ArrayList<>();
            for (Week w : periodMap.values()) {
                days.addAll(w.getDays());
            }
            return days;
        }
    }

    public class Week extends TimePeriod<Day> {

        @Override
        protected void insertIntoPeriod(Day period, E event) {
            period.insert(event);
        }

        @Override
        protected Day newPeriod() {
            return new Day();
        }

        @Override
        protected Integer getDate(DateInterface event) {
            return event.getDay();
        }

        public Day getDay(int day) {
            return periodMap.get(day);
        }

        public List<Day> getDays() {
            return new ArrayList<>(periodMap.values());
        }
    }

    public class Day {
        TreeSet<E> events = new TreeSet<>();

        public void insert(E event) {
            events.add(event);
        }

        public List<E> getEvents() {
            return new ArrayList<>(events);
        }
    }
}