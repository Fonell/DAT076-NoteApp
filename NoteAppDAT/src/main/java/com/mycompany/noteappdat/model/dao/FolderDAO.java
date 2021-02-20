package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import java.util.List;
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
    
    public Folder findFolderByName(String name) {
        return entityManager.find(Folder.class, name);
    }
    
    //Find note in folder
    
    //Find folder in folder
    
    //Get parent folder
    
    //Get child folders
}
