package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Note;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;

@Stateless //@Component instead?
public class CalendarService {

    @EJB
    private EventDAO eventDAO;
    @EJB
    private NoteDAO noteDAO;

    public Event createEvent(String name) {
        Calendar date = Calendar.getInstance();
        Event event = new Event(name, date);
        eventDAO.create(event);
        return event;
    }

    public void setDate(Event event, int year, int month, int day, int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day, hour, minute);
        event.setEventDate(date);
    }

    public void setNote(Event event, Note note) {
        event.setNote(note);
    }
}
