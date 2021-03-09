package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;
import com.mycompany.noteappdat.model.service.CalendarService;
import com.mycompany.noteappdat.model.service.FileSystemService;
import lombok.Data;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
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

    public Almanac<Event> getEvents() {
        return cs.getAllEvents();
    }
}