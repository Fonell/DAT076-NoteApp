package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NoteService {

    @EJB
    private NoteDAO noteDAO;
    
    @EJB
    private FolderDAO folderDAO;
    
    public void createNote(String noteName) {
        //FacesMessage message = new FacesMessage();
        noteDAO.create(new Note(noteName));
    }
    
    public void removeNote(String noteName) {
        //FacesMessage message = new FacesMessage();
        noteDAO.remove(noteDAO.findNoteByName(noteName));
    }
    
    public Note findNoteByName(String noteName) {
        return noteDAO.findNoteByName(noteName);
    }
    
    public List<Note> getAllNotes() {
        //FacesMessage message = new FacesMessage();
        return noteDAO.findAll();
    }

    public List<Note> getAllNotesWithoutFolder() {
        //FacesMessage message = new FacesMessage();
        return noteDAO.findAllNotesWithoutFolder();
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
    
    public void setNoteParentFolder(String noteName, String folderName) {
        //FacesMessage message = new FacesMessage();
        Note note = noteDAO.findNoteByName(noteName);
        Folder folder = folderDAO.findFolderByName(folderName);
        note.setFolder(folder);
        noteDAO.update(note);
    }
}
