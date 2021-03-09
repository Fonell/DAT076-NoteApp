package com.mycompany.noteappdat.model;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;
import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

public class AlmanacTest {

    Almanac<Event> almanac = new Almanac<>();

    @Test
    public void insert() {
        Event event1 = new Event("2001, 1, 0, 0, 0", new GregorianCalendar(2001, 1, 0, 0, 1));
        Event event2 = new Event("2001, 2, 0, 0, 0", new GregorianCalendar(2001, 2, 0, 0, 1));

        almanac.insert(event1);
        almanac.insert(event2);

        Assert.assertTrue(almanac.getDay(2001, 0, 0).getEvents().contains(event1));
        Assert.assertFalse(almanac.getDay(2001, 0, 0).getEvents().contains(event2));

        Assert.assertTrue(almanac.getDay(2001, 1, 0).getEvents().contains(event2));
        Assert.assertFalse(almanac.getDay(2001, 1, 0).getEvents().contains(event1));

    }
}