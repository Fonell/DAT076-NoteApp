package com.mycompany.noteappdat.model;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.Almanac.Almanac;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlmanacTest {

    @Test
    public void insert() {

        Almanac almanac = new Almanac();

        Event event1 = new Event("2001, 0, 0, 0, 0", new GregorianCalendar(2001, Calendar.JANUARY, 1, 0, 0));
        Event event2 = new Event("2001, 1, 0, 0, 0", new GregorianCalendar(2001, Calendar.FEBRUARY, 1, 0, 0));

        almanac.insert(event1);
        almanac.insert(event2);

        /*
        Assert.assertTrue(almanac.getDay(2001, 0, 0).getEvents().contains(event1));
        Assert.assertFalse(almanac.getDay(2001, 0, 0).getEvents().contains(event2));

        Assert.assertTrue(almanac.getDay(2001, 1, 0).getEvents().contains(event2));
        Assert.assertFalse(almanac.getDay(2001, 1, 0).getEvents().contains(event1));
         */

        //TODO: my fix might not match real use, may need a permanent way to adjust dates to match the gregorian calendar
        Assert.assertTrue(almanac.getYear(2001).getMonth(Calendar.JANUARY).getDay(0).getEvents().contains(event1));
        Assert.assertFalse(almanac.getYear(2001).getMonth(Calendar.JANUARY).getDay(0).getEvents().contains(event2));

        Assert.assertTrue(almanac.getYear(2001).getMonth(Calendar.FEBRUARY).getDay(0).getEvents().contains(event2));
        Assert.assertFalse(almanac.getYear(2001).getMonth(Calendar.FEBRUARY).getDay(0).getEvents().contains(event1));
    }
}