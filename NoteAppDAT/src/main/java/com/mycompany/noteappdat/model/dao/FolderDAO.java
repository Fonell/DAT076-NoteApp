package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    //Find note in folder
    
    //Find folder in folder
    
    //Get parent folder
    
    //Get child folders
}
