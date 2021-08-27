package com.mycompany.noteappdat.model.service.Almanac;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.entity.Event;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.*;
import java.io.Serializable;

@Named
@RequestScoped
public class EventManager implements Comparable<EventManager>, Serializable {

    @EJB
    EventDAO eventDAO;

    public Event event;

    public EventManager(Event event) {
        this.event = event;
    }

    public void update() {
        System.out.println("This method runs");
        event.setName("updated");
        //eventDAO.update(event);
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public int compareTo(EventManager o) {
        return event.compareTo(o.event);
    }
}
