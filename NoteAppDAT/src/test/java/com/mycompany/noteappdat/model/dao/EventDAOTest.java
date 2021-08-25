package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Event;
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
import java.util.GregorianCalendar;
import java.util.List;


@RunWith(Arquillian.class)
public class EventDAOTest {

    private final String eventName = "test_event_name";
    private final GregorianCalendar date = new GregorianCalendar(2000, 10, 10);

    @EJB
    private EventDAO eventDAO;
    @Inject
    private UserTransaction userTransaction;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(EventDAO.class, Event.class)
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
    public void findById() {
        //Create the event
        Event event = new Event(eventName, "test", date);
        eventDAO.create(event);
        int id = event.getId();

        //Assert that it can be found by id
        Assert.assertEquals(eventDAO.findById(id), event);

        eventDAO.remove(event);
    }

    @Test
    public void findByName() {
        //Create the event
        Event event = new Event(eventName, "test", date);
        eventDAO.create(event);

        //Assert that it can be found by name
        List<Event> events = eventDAO.findByName(eventName);
        Assert.assertTrue(events.contains(event));

        eventDAO.remove(event);
    }

    @Test
    public void getEventsInPeriod() {
        //Create three events with different dates
        Event first = new Event(eventName, "test", new GregorianCalendar(2000, 0, 0));
        eventDAO.create(first);
        Event second = new Event(eventName, "test", new GregorianCalendar(2003, 0, 0));
        eventDAO.create(second);
        Event third = new Event(eventName, "test", new GregorianCalendar(2005, 0, 0));
        eventDAO.create(third);

        //Create two dates representing a time period
        GregorianCalendar from = new GregorianCalendar(2001, 0, 0);
        GregorianCalendar to = new GregorianCalendar(2004, 0, 0);

        eventDAO.flush();

        //Get all events in the period between from and to
        List<Event> events = eventDAO.getEventsInPeriod(from, to);

        //Assert that the second event is in the list
        Assert.assertTrue(events.contains(second));

        //Assert that the first and third events are not in the list
        Assert.assertFalse(events.contains(first));
        Assert.assertFalse(events.contains(third));
    }
}