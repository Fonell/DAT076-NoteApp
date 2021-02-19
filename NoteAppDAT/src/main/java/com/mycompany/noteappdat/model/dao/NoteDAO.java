package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Note;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class NoteDAO extends AbstractDAO<Note> {
    @Getter @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public NoteDAO() {
        super(Note.class);
    }
    
    public Note findNoteMatchingTitle(String title) {
        return entityManager.find(Note.class, title);
    }
}
