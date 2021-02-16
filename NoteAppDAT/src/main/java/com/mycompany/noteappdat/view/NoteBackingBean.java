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
	private NoteDAO noteDAO;

	private List<Note> notes;

	@PostConstruct
	private void init() {
		notes = (noteDAO.findAll());
	}
}
