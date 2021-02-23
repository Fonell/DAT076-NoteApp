package com.mycompany.noteappdat.view;

import com.mycompany.noteappdat.model.entity.Note;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import java.io.Serializable;
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
	private NoteDAO NoteDAO;

	private List<Note> notes;

	@PostConstruct
	private void init() {
		notes = NoteDAO.findAll();
	}
        
        public void createNote(String name) {
                NoteDAO.createNote(name);
        }
        
        public void createNoteInFolder(String noteName, String folderName) {
                NoteDAO.createNote(noteName);
                NoteDAO.setNoteFolder(noteName, folderName);
        }
}
