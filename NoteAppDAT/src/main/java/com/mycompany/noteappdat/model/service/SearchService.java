package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.entity.Event;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Service for searching the file system.
 *
 * @author Emil Holmsten
 */
@Stateless
public class SearchService {

    @EJB
    private EventDAO eventDAO;

    public List<Event> findEventByName(String eventName) {
        return eventDAO.findByName(eventName);
    }
}
