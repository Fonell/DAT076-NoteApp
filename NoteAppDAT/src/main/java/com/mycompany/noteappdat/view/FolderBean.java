package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Folder;
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
public class FolderBean implements Serializable {

    @Inject
    private FileSystemService fss;

    private Note selectedNote;
    private Folder selectedFolder;

    private String noteName;
    private String noteText;
    private String noteFolder;
    private Date noteDate;

    public void createNote() {
        selectedNote = fss.createNote(noteName, noteText);
        //noteDAO.create(new Note(noteName));
    }

    public void setNoteText(Note note, String newNoteText) {
        //FacesMessage message = new FacesMessage();
        note.setText(newNoteText);
    }

    public void deleteNote() {
        fss.removeNote(selectedNote);
    }

    public List<Note> getNotes() {
        return fss.getAllRootNotes();
    }

    public void createNoteInFolder() { //TODO: tror inte detta funkar som det ska
        fss.createNote(noteName, noteText);
        fss.setNoteParentFolder(selectedNote, selectedFolder);
    }
}