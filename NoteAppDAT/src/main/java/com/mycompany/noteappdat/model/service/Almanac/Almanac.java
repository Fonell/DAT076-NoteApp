package com.mycompany.noteappdat.model.service.Almanac;

import java.util.*;

/**
 * A self sorting structure for storing and retrieving events.
 * Events can be inserted and removed without breaking the structure.
 * Events are grouped into years, months, weeks and days.
 *
 * @author Emil Holmsten
 */
public class Almanac<E extends DateInterface> {

    private final YearCollection years = new YearCollection(42);

    public Almanac(List<E> events) {
        for (E e : events) {
            insert(e);
        }
    }

    public Almanac() {

    }

    public void insert(E event) {
        years.insert(event);
    }

    public Day getDay(int year, int month, int day) {
        return years.getYear(year).getMonth(month).getDay(day);
    }

    public List<Year> getYears() {
        return years.getYears();
    }



    private abstract class TimePeriod<T> {
        private int date;
        Map<Integer, T> periodMap = new TreeMap<>();

        public TimePeriod(int eventDate) {
            this.date = eventDate;
        }

        public void insert(E event) {
            int eventDate = getDate(event);

            T period = periodMap.get(eventDate);
            if (period == null) {
                period = newSubPeriod(eventDate);
                insertIntoPeriod(period, event);
                periodMap.put(eventDate, period);
                return;
            }
            insertIntoPeriod(period, event);
        }

        protected abstract void insertIntoPeriod(T period, E event);

        protected abstract T newSubPeriod(int eventDate);

        protected abstract int getDate(DateInterface event);

        public int getTime() {
            return this.date;
        }
    }



    public class YearCollection extends TimePeriod<Year> {

        public YearCollection(int eventDate) {
            super(eventDate);
        }

        @Override
        protected void insertIntoPeriod(Year period, E event) {
            period.insert(event);
        }

        @Override
        protected Year newSubPeriod(int eventDate) {
            return new Year(eventDate);
        }

        @Override
        protected int getDate(DateInterface event) {
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

        public Year(int eventDate) {
            super(eventDate);
        }

        @Override
        protected void insertIntoPeriod(Month period, E event) {
            period.insert(event);
        }

        @Override
        protected Month newSubPeriod(int eventDate) {
            return new Month(eventDate);
        }

        @Override
        protected int getDate(DateInterface event) {
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

        public Month(int eventDate) {
            super(eventDate);
        }

        @Override
        protected void insertIntoPeriod(Week period, E event) {
            period.insert(event);
        }

        @Override
        protected Week newSubPeriod(int eventDate) {
            return new Week(eventDate);
        }

        @Override
        protected int getDate(DateInterface event) {
            return event.getWeek();
        }

        public Week getWeek(String week) {
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

        public String getStringRepresentation() {
            //TODO: Make this not awful. Probably will never happen.
            return this.getDays().get(0).getEvents().get(0).getDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        }
    }



    public class Week extends TimePeriod<Day> {

        public Week(int eventDate) {
            super(eventDate);
        }

        @Override
        protected void insertIntoPeriod(Day period, E event) {
            period.insert(event);
        }

        @Override
        protected Day newSubPeriod(int eventDate) {
            return new Day();
        }

        @Override
        protected int getDate(DateInterface event) {
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
        private int date;
        TreeSet<E> events = new TreeSet<>();

        public void insert(E event) {
            events.add(event);
        }

        public List<E> getEvents() {
            return new ArrayList<>(events);
        }

        public int getTime() {
            return date;
        }

        public String getStringRepresentation() {
            //TODO: Make this not awful. Probably will never happen.
            return getEvents().get(0).getDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
        }
    }
}