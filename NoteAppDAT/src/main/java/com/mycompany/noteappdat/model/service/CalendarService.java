package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Note;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless //TODO: @Component instead?
public class CalendarService {

    @EJB
    private EventDAO eventDAO;
    @EJB
    private NoteDAO noteDAO;

    Almanac<Event> almanac;

    public Event createEvent(String name, Date date) {
        Event event = new Event(name, convertDateToCal(date));
        eventDAO.create(event);
        return event;
    }

    // Uses deprecated class Date, but primefaces calendar component is our best option right now.
    // We will implement a different solution if we have time.
    public void setDate(Event event, Date date) {
        setDate(event, convertDateToCal(date));
    }

    public void setDate(Event event, GregorianCalendar date) {
        event.setEventDate(date);
    }

    public Almanac<Event> getPeriod(Date date1, Date date2) {
        return getPeriod(convertDateToCal(date1), convertDateToCal(date2));
    }

    public Almanac<Event> getPeriod(GregorianCalendar date1, GregorianCalendar date2) {
        List<Event> events = eventDAO.getEventsInPeriod(date1, date2);
        almanac = new Almanac<>(events);
        return almanac;
    }

    public void setNote(Event event, Note note) {
        event.setNote(note);
    }

    private GregorianCalendar convertDateToCal(Date date) {
        return new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay(), date.getHours(), date.getMinutes());
    }

    public void removeEvent(Event selectedEvent) { //TODO: UNTESTED
        eventDAO.remove(selectedEvent);
    }

    public Almanac<Event> getAllEvents() {
        List<Event> events = eventDAO.getEvents();
        almanac = new Almanac<>(events);
        return almanac;
    }
}
