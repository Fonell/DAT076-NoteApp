package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import lombok.Getter;

import java.util.GregorianCalendar;
import java.util.List;

@Stateless
public class EventDAO extends AbstractDAO<Event> {
    @Getter
    @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public EventDAO() {
        super(Event.class);
    }

    public Event findById(int id) {
        return entityManager.find(Event.class, id);
    }

    public List<Event> findByName(String name) {
        TypedQuery<Event> query = entityManager.createNamedQuery("Event.findByName", Event.class);
        query.setParameter("name", "%" + name.toLowerCase() + "%");
        return query.getResultList();
    }

    public List<Event> getEventsInPeriod(GregorianCalendar from, GregorianCalendar to) {
        TypedQuery<Event> query = entityManager.createNamedQuery("Event.getEventsInPeriod", Event.class);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.getResultList();
    }

    public List<Event> getEvents() { //TODO: NOT TESTED
        TypedQuery<Event> query = entityManager.createNamedQuery("Event.getEvents", Event.class);
        return query.getResultList();
    }
}
