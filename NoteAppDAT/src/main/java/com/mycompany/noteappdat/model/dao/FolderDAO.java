package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
import lombok.Getter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FolderDAO extends AbstractDAO<Folder> {
    @Getter
    @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public FolderDAO() {
        super(Folder.class);
    }

    public Folder findById(int id) {
        return entityManager.find(Folder.class, id);
    }

    public List<Folder> findByName(String name) {
        TypedQuery<Folder> query = entityManager.createNamedQuery("Folder.findByName", Folder.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Folder> findAllInRoot() {
        TypedQuery<Folder> query = entityManager.createNamedQuery("Note.findAllInRoot", Folder.class);
        return query.getResultList();
    }

    @Override
    public void remove(Folder folder) {
        for (Folder f : folder.getChildFolders()) {
            f.setParent(null);
        }
    }
}
