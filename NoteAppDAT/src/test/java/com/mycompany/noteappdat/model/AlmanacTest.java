package com.mycompany.noteappdat.model;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.Almanac.*;
import org.junit.Assert;
import org.junit.Test;
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

        //TODO: my fix might not match real use, may need a permanent way to adjust dates to match the gregorian calendar
        Assert.assertTrue(almanac.getYear(2001).getMonth(Calendar.JANUARY).getDay(0).getEvents().contains(event1));
        Assert.assertFalse(almanac.getYear(2001).getMonth(Calendar.JANUARY).getDay(0).getEvents().contains(event2));

        Assert.assertTrue(almanac.getYear(2001).getMonth(Calendar.FEBRUARY).getDay(0).getEvents().contains(event2));
        Assert.assertFalse(almanac.getYear(2001).getMonth(Calendar.FEBRUARY).getDay(0).getEvents().contains(event1));
    }

    @Test
    public void getEvents() {

        Almanac almanac = new Almanac();

        Event event1 = new Event("e1", new GregorianCalendar(2001, Calendar.JANUARY, 1, 0, 0));
        Event event2 = new Event("e2", new GregorianCalendar(2001, Calendar.JANUARY, 1, 0, 0));

        almanac.insert(event1);
        almanac.insert(event2);

        for (Year y : almanac.getYears()) {
            for (Month m : y.getMonths()) {
                for (Week w : m.getWeeks()) {
                    for (Day d : w.getDays()) {
                        //Make sure all events are accounted for
                        Assert.assertTrue(d.getEvents().contains(event1));
                        Assert.assertTrue(d.getEvents().contains(event2));
                    }
                }
            }
        }
    }
}