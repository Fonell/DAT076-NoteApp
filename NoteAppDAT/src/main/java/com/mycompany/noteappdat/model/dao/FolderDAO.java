package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import lombok.Getter;

@Stateless
public class FolderDAO extends AbstractDAO<Folder> {
    @Getter @PersistenceContext(unitName = "db")
    private EntityManager entityManager;

    public FolderDAO() {
        super(Folder.class);
    }
    
    public Folder findFolderByName(String folderName) {
        return entityManager.find(Folder.class, folderName);
    }
    
    public Folder findFolderInFolderByName(String folderName, String parentFolderName) {
        TypedQuery<Folder> query = entityManager.createNamedQuery("Folder.findFolderInFolderByName", Folder.class);
        query.setParameter("folderName", folderName);
        query.setParameter("parentName", parentFolderName);
        return query.getSingleResult();
    }
}
