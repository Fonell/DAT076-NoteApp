package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
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
import java.util.Calendar;

@RunWith(Arquillian.class)
public class CalendarServiceTest {
    private final Calendar date = Calendar.getInstance();
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
    public void setCalendar() {
        date.set(2000, 10, 10);
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
        Event event = calendarService.createEvent(eventName);

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
        Assert.assertEquals(eventDAO.findById(event.getId()).getEventDate(), date);

        //Set the date
        calendarService.setDate(event, 0, 0, 0, 0, 0);

        //Assert that the event date doesn't match original date
        Assert.assertNotEquals(eventDAO.findById(event.getId()).getEventDate(), date);
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