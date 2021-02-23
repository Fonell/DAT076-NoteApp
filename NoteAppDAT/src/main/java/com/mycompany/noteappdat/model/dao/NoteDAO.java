package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Note;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.Getter;

@Stateless
public class NoteDAO extends AbstractDAO<Note> {
    @Getter @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public NoteDAO() {
        super(Note.class);
    }
    
    public String createNote(String name) {
        Note note = entityManager.find(Note.class, name);
        if(note != null) return "A note with this name already exists.";
        else create(new Note(name));
        return name + " was created successfully!";
    }
    
    public Note findNoteByName(String name) {
        return entityManager.find(Note.class, name);
    }
    
    public Note findNoteByNameAndFolder(String noteName, String folderName) {
        TypedQuery<Note> query = entityManager.createNamedQuery("Note.findNoteByNameAndFolder", Note.class);
        query.setParameter("fName", folderName);
        query.setParameter("nName", noteName);
        return query.getSingleResult();
    }
}
