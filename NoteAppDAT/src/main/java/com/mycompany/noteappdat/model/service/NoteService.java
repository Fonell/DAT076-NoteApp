package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Note;
import javax.ejb.EJB;

public class NoteService {

    @EJB
    private NoteDAO noteDAO;
    
    public void createNote(String noteName) {
        //FacesMessage message = new FacesMessage();
        noteDAO.create(new Note(noteName));
    }
    
    public Note findNoteByName(String noteName) {
        return noteDAO.findNoteByName(noteName);
    }
    
    public void removeNote(String noteName) {
        //FacesMessage message = new FacesMessage();
        noteDAO.remove(noteDAO.findNoteByName(noteName));
    }
    
    public void setNoteName(String oldNoteName, String newNoteName) {
        //FacesMessage message = new FacesMessage();
        Note noteToUpdate = noteDAO.findNoteByName(oldNoteName);
        noteToUpdate.setName(newNoteName);
        noteDAO.update(noteToUpdate);
    }
    
    public void setNoteText(String noteName, String newNoteText) {
        //FacesMessage message = new FacesMessage();
        Note noteToUpdate = noteDAO.findNoteByName(noteName);
        noteToUpdate.setText(newNoteText);
        noteDAO.update(noteToUpdate);
    }
}
