package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Note;
import java.util.List;
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
       
    public Note findNoteByName(String name) {
        return entityManager.find(Note.class, name);
    }

    public List<Note> findAllNotesWithoutFolder() {
        TypedQuery<Note> query = entityManager.createNamedQuery("Note.findAllNotesWithoutFolder", Note.class);
        return query.getResultList();
    }
    
    /* Most likely won't be needed.
    public Note findNoteByNameAndFolder(String noteName, String folderName) {
        TypedQuery<Note> query = entityManager.createNamedQuery("Note.findNoteByNameAndFolder", Note.class);
        query.setParameter("folderName", folderName);
        query.setParameter("noteName", noteName);
        return query.getSingleResult();
    }
    */
}
