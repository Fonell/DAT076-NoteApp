package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.entity.Note;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named
@ViewScoped
public class NoteBackingBean implements Serializable {
	@EJB
	private NoteDAO noteDAO;
        
        private String noteName;

        //todo value stuff
	
	private void init() {
	}
        
        public void createNote() {
                noteDAO.createNote(noteName);
        }
        
        public List<Note> getNotes() {
            List<Note> reversedNotes = noteDAO.findAll();
            Collections.reverse(reversedNotes);
            return reversedNotes;
        }
        
        public void createNoteInFolder(String noteName, String folderName) {
                noteDAO.createNote(noteName);
                noteDAO.setNoteFolder(noteName, folderName);
        }
}
