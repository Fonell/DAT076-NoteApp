package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class CalendarService {

    @EJB
    private EventDAO eventDAO;

    Almanac almanac;
    private GregorianCalendar currentDate = new GregorianCalendar();


    public Event createEvent(String name, String text, Date date) {
        Event event = new Event(name, text, convertDateToCal(date));
        eventDAO.create(event);
        return event;
    }

    public void setText(Event event, String s) {
        event.setText(s);
        eventDAO.update(event);
    }

    public void setName(Event event, String s) {
        event.setName(s);
        eventDAO.update(event);
    }

    // Uses deprecated class Date, but primefaces calendar component is our best option right now.
    // We will implement a different solution if we have time.
    public void setDate(Event event, Date date) {
        setDate(event, convertDateToCal(date));
    }

    public void setDate(Event event, GregorianCalendar date) {
        event.setEventDate(date);
    }

    public Almanac getAllEvents() {
        List<Event> events = eventDAO.getEvents();
        almanac = new Almanac();
        almanac.insert(events);
        return almanac;
    }

    public Almanac getPeriodCurrentMonth() {
        currentDate = snapDateToMonth(currentDate);
        GregorianCalendar endDate = snapDateToMonth(currentDate);
        endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH)+1);

        return getPeriod(currentDate, endDate);
    }

    private GregorianCalendar snapDateToMonth(GregorianCalendar date) {
        GregorianCalendar beginningOfMonth = new GregorianCalendar();
        beginningOfMonth.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), 0, 0, 0);
        return beginningOfMonth;
    }

    public Almanac getPeriod(GregorianCalendar date1, GregorianCalendar date2) {
        List<Event> events = eventDAO.getEventsInPeriod(date1, date2);
        almanac = new Almanac();
        almanac.insert(events);
        return almanac;
    }

    private GregorianCalendar convertDateToCal(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
    }

    public void removeEvent(Event selectedEvent) { //TODO: UNTESTED
        eventDAO.remove(selectedEvent);
    }
}
