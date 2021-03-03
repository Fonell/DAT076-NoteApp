package com.mycompany.noteappdat.model.dao;

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
import java.util.List;


@RunWith(Arquillian.class)
public class EventDAOTest {

    private final String eventName = "test_event_name";
    private final Calendar calendar = Calendar.getInstance();

    @EJB
    private EventDAO eventDAO;
    @Inject
    private UserTransaction userTransaction;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(EventDAO.class, Event.class, Note.class, Folder.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setCalendar() {
        calendar.set(2000, 10, 10);
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
    public void findById() {
        //Create the event
        Event event = new Event(eventName, calendar);
        eventDAO.create(event);
        int id = event.getId();

        //Assert that it can be found by id
        Assert.assertEquals(eventDAO.findById(id), event);

        eventDAO.remove(event);
    }

    @Test
    public void findByName() {
        //Create the event
        Event event = new Event(eventName, calendar);
        eventDAO.create(event);

        //Assert that it can be found by name
        List<Event> events = eventDAO.findByName(eventName);
        Assert.assertTrue(events.contains(event));

        eventDAO.remove(event);
    }
}