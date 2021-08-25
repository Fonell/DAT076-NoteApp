package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;
import com.mycompany.noteappdat.model.service.Almanac.Year;
import com.mycompany.noteappdat.model.service.CalendarService;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Named
@ViewScoped
public class CalendarBean implements Serializable {

    @Inject
    private CalendarService cs;

    private LocalDate test;

    private Event selectedEvent;

    private String eventName;
    private String eventText;
    private Date selectedDate;

    private GregorianCalendar realSelectedDate = new GregorianCalendar();

    private GregorianCalendar from;
    private GregorianCalendar to;

    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void createEvent() {
        selectedEvent = cs.createEvent(eventName, eventText, date);
    }

    public void deleteEvent() {
        cs.removeEvent(selectedEvent);
    }

    public void deleteEvent(Event event) {
        cs.removeEvent(event);
    }

    public List<Year> getEventsInPeriod() {
        from = (GregorianCalendar) realSelectedDate.clone();
        to = (GregorianCalendar) realSelectedDate.clone();

        from.roll(Calendar.YEAR, false);
        to.roll(Calendar.YEAR, true);

        return cs.getPeriod(from, to).getYears();
    }

    public List<Year> getEvents() {
        Almanac almanac = cs.getAllEvents();

        return almanac.getYears();
    }
}