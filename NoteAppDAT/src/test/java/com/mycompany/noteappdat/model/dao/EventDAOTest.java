package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Calendar;
import java.util.List;


@RunWith(Arquillian.class)
public class EventDAOTest {

    private final String eventName = "test_event_name";
    private final Calendar calendar = Calendar.getInstance();

    @EJB
    private EventDAO eventDAO;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(EventDAO.class, Event.class, NoteDAO.class, Note.class, Folder.class, FolderDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setCalendar() {
        calendar.set(2000, 10, 10);
    }

    @Test
    public void findById() {
        //Create the event
        Event event = new Event(eventName, calendar);
        eventDAO.create(event);
        int id = event.getId();

        //Check that it can be found by id
        Assert.assertEquals(eventDAO.findById(id).getName(), event.getName());
        Assert.assertEquals(eventDAO.findById(id).getEventDate(), event.getEventDate());
        Assert.assertEquals(eventDAO.findById(id).getNote(), event.getNote());

        eventDAO.remove(event);
    }

    @Test
    public void findByName() {
        //Create the event
        Event event = new Event(eventName, calendar);
        eventDAO.create(event);
        int id = event.getId();

        //Check that it can be found by name
        List<Event> events = eventDAO.findByName(eventName);
        Assert.assertEquals(eventDAO.findById(id).getName(), event.getName());
        Assert.assertEquals(eventDAO.findById(id).getEventDate(), event.getEventDate());
        Assert.assertEquals(eventDAO.findById(id).getNote(), event.getNote());

        eventDAO.remove(event);
    }
}