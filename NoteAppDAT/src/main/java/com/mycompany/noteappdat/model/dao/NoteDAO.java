package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
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
    
    public void createNote(String noteName) {
        create(new Note(noteName));
    }
    
    public void setNoteFolder(String noteName, String folderName) {
        Note note = entityManager.find(Note.class, noteName);
        Folder folder = entityManager.find(Folder.class, folderName);
        note.setFolder(folder);
        update(note);
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
