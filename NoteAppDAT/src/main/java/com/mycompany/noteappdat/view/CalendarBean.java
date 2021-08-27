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
import java.util.*;

@Data
@Named
@ViewScoped
public class CalendarBean implements Serializable {

    @Inject
    private CalendarService cs;

    private String eventName;
    private String eventText;

    private Date date = new Date();

    public void update(Event event) {
        cs.updateEvent(event);
    }

    public void createEvent() {
        cs.createEvent(eventText, date);
    }

    public void deleteEvent(Event event) {
        cs.removeEvent(event);
    }

    public List<Year> getEvents() {
        Almanac almanac = cs.getAllEvents();
        return almanac.getYears();
    }
}