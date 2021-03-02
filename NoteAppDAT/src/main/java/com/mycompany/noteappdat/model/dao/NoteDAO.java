package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import lombok.Getter;

@Stateless
public class NoteDAO extends AbstractDAO<Note> {
    @Getter
    @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public NoteDAO() {
        super(Note.class);
    }

    public Note findById(int id) {
        return entityManager.find(Note.class, id);
    }

    public List<Note> findByName(String name) {
        TypedQuery<Note> query = entityManager.createNamedQuery("Note.findByName", Note.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Note> findAllInRoot() {
        TypedQuery<Note> query = entityManager.createNamedQuery("Note.findAllInRoot", Note.class);
        return query.getResultList();
    }

    //remove
}
