package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Note;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Calendar;

@Stateless //@Component ??
public class CalendarService {

    @EJB
    private EventDAO eventDAO;
    @EJB
    private NoteDAO noteDAO;

    public void newEvent(String name) {
        Calendar date = Calendar.getInstance();
        Event event = new Event(name, date);
        eventDAO.create(event);
    }

    public void newEvent(String title, int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        Event event = new Event(title, date);
        eventDAO.create(event);
    }

    public void newEvent(String title, int year, int month, int day, int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day, hour, minute);
        Event event = new Event(title, date);
        eventDAO.create(event);
    }

    public Event getEventById(int id) {
        return eventDAO.findById(id);
    }

    public void updateDate(Event event, int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        event.setEventDate(date);
    }

    public void updateDate(Event event, int year, int month, int day, int hour, int minute) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day, hour, minute);
        event.setEventDate(date);
    }

    public void setNote(Event event, Note note) {
        event.setNote(note);
    }

    public void setNewNote(Event event, String noteName) {
        Note note = new Note(noteName);
        noteDAO.create(note);
        event.setNote(note); //Will this word properly?
    }
}
