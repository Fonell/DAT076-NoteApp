package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.service.CalendarService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EventManager implements Comparable<EventManager>, Serializable {

    @Inject
    private CalendarService cs;

    Event event;

    public void update() {
        if (cs == null) {
            System.out.println("ERR: eventDAO does not exist");
        }
        System.out.println("TEST: This method runs");
        //event.setName();
        //cs.setName(event, "updated");
        //eventDAO.update(event);
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public int compareTo(EventManager o) {
        return event.compareTo(o.event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
