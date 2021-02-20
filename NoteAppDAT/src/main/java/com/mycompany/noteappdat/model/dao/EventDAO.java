package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Event;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class EventDAO extends AbstractDAO<Event> {
    @Getter @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public EventDAO() {
        super(Event.class);
    }
    
    public Event findEventByName(String title) {
        return entityManager.find(Event.class, title);
    }
}
