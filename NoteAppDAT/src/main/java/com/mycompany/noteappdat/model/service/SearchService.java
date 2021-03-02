package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.EventDAO;
import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;

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
    private NoteDAO noteDAO;
    @EJB
    private FolderDAO folderDAO;
    @EJB
    private EventDAO eventDAO;

    public List<Note> findNoteByName(String noteName) {
        //FacesMessage message = new FacesMessage();
        return noteDAO.findByName(noteName);
    }

    public List<Note> findNoteByContent(String noteName) {
        //FacesMessage message = new FacesMessage();
        return noteDAO.findByName(noteName);
    }

    public List<Folder> findFolderByName(String folderName) {
        //FacesMessage message = new FacesMessage();
        return folderDAO.findByName(folderName);
    }

    public List<Event> findEventByName(String eventName) {
        //FacesMessage message = new FacesMessage();
        return eventDAO.findByName(eventName);
    }
}
