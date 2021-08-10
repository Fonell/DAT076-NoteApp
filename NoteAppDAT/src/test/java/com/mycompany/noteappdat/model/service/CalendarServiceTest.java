package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import com.mycompany.noteappdat.model.service.Almanac.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.*;
import java.util.*;

@RunWith(Arquillian.class)
public class CalendarServiceTest {
    private final GregorianCalendar date = new GregorianCalendar(2000, 10, 10);
    private final String eventName = "test_event_name";
    private final String noteName = "test_note_name";
    @Inject
    CalendarService calendarService;
    @EJB
    private EventDAO eventDAO;
    @EJB
    private NoteDAO noteDAO;
    @Inject
    private UserTransaction userTransaction;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(FolderDAO.class, Folder.class, NoteDAO.class, Note.class, EventDAO.class, Event.class, CalendarService.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void startTransaction() throws SystemException, NotSupportedException {
        userTransaction.begin();
    }

    @After
    public void commitTransaction() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException {
        userTransaction.commit();
    }

    @Test
    public void createEvent() {
        //Create an event
        Event event = calendarService.createEvent(eventName, date.getTime());

        //Assert that the event exists in the database
        Assert.assertTrue(eventDAO.findByName(eventName).contains(event));

        eventDAO.remove(event);
    }

    @Test
    public void setDate() {
        //Create an event
        Event event = new Event(eventName, date);
        eventDAO.create(event);

        //Assert that the event date matches original date
        Assert.assertEquals(eventDAO.findById(event.getId()).getDate(), date);

        //Set the date
        calendarService.setDate(event, new Date(0, 0, 0, 0, 0));

        //Assert that the event date doesn't match original date
        Assert.assertNotEquals(eventDAO.findById(event.getId()).getDate(), date);
    }

    @Test
    public void getPeriod() {
        //Create three events with different dates
        Event first = new Event(eventName, new GregorianCalendar(2000, 0, 0));
        eventDAO.create(first);
        Event second = new Event(eventName, new GregorianCalendar(2003, 0, 0));
        eventDAO.create(second);
        Event third = new Event(eventName, new GregorianCalendar(2005, 0, 0));
        eventDAO.create(third);

        //Create two dates representing a time period
        GregorianCalendar from = new GregorianCalendar(2001, 0, 0);
        GregorianCalendar to = new GregorianCalendar(2004, 0, 0);

        //Get all events in the period between from and to
        Almanac almanac = calendarService.getPeriod(from, to);

        List<Event> events = new ArrayList<>();
        for (Year y : almanac.getYears()) {
            for (Month m : y.getMonths()) {
                for (Week w : m.getWeeks()) {
                    for (Day d : w.getDays()) {
                        events.addAll(d.getEvents());
                    }
                }
            }
        }

        //Assert that the second event is in the list
        Assert.assertTrue(events.contains(second));

        //Assert that the first and third events are not in the list
        Assert.assertFalse(events.contains(first));
        Assert.assertFalse(events.contains(third));
    }

    @Test
    public void setNote() {
        //Create an event
        Event event = new Event(eventName, date);
        eventDAO.create(event);

        //Create a note
        Note note = new Note(noteName);
        noteDAO.create(note);

        //Set the note on the event
        calendarService.setNote(event, note);
        Assert.assertEquals(eventDAO.findById(event.getId()).getNote(), noteDAO.findById(note.getId()));
    }
}