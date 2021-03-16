package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Note;
import com.mycompany.noteappdat.model.service.FileSystemService;
import lombok.Data;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Named
@ViewScoped
public class NoteBackingBean implements Serializable {
    @EJB
    private NoteDAO noteDAO;

    @Inject
    private FileSystemService fss;

    private Note selectedNote;

    
    private String noteName;
    private String noteText;
    private String noteFolder;
    private Date noteDate;

    //todo value stuff

    private void init() {
    }

    public void createNote() {
        fss.createNote(noteName, noteText);
        //noteDAO.create(new Note(noteName));
    }

    public void setNoteText(Note note, String newNoteText) {
        //FacesMessage message = new FacesMessage();
        note.setText(newNoteText);
        noteDAO.update(note);
    }

    public void deleteNote() {
        fss.removeNote(selectedNote);
        //noteDAO.remove(noteDAO.findNoteByName(noteName));
    }

    public List<Note> getNotes() {
        //List<Note> reversedNotes = noteDAO.findAll();
        //Collections.reverse(reversedNotes);
        return fss.getAllRootNotes();
    }

    public void createNoteInFolder(String noteName, String folderName) {
        fss.createFolder(folderName);
        //fss.createNote(noteName);
        fss.setNoteParentFolder(fss.getNoteById(0), fss.getFolderById(0));
        //noteDAO.createNote(noteName);
        //noteDAO.setNoteFolder(noteName, folderName);
    }
}