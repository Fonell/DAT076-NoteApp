package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;
import com.mycompany.noteappdat.model.service.Almanac.Almanac.Year;
import com.mycompany.noteappdat.model.service.CalendarService;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Named
@ViewScoped
public class CalendarBean implements Serializable {

    @Inject
    private CalendarService cs;

    private Event selectedEvent;

    private String eventName;
    private Date selectedDate;

    private Date from;
    private Date to;

    
    public void createEvent() {
        selectedEvent = cs.createEvent(eventName);
    }

    public void setEventDate() {
        cs.setDate(selectedEvent, selectedDate);
    }

    public void deleteEvent() {
        cs.removeEvent(selectedEvent);
    }

    public Almanac<Event> getEventsInPeriod() {
        return cs.getPeriod(from, to);
    }

    public List<Almanac<Event>.Year> getEvents() {
        Almanac<Event> almanac = cs.getAllEvents();

        return almanac.getYears();
    }
}