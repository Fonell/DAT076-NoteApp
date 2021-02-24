package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FolderService {
    
    @EJB
    private FolderDAO folderDAO;
    
    public void createFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        folderDAO.create(new Folder(folderName));
    }
    
    public void removeFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        folderDAO.remove(folderDAO.findFolderByName(folderName));
    }

    public List<Folder> getAllFoldersWithoutFolder() {
        //FacesMessage message = new FacesMessage();
        return folderDAO.findAllFoldersWithoutFolder();
    }
    
    public List<Note> getAllNotesInFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        Folder folder = folderDAO.findFolderByName(folderName);
        return folder.getNotes();
    }
    
    public List<Folder> getAllFoldersInFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        Folder folder = folderDAO.findFolderByName(folderName);
        return folder.getChildren();
    }
    
    public Folder getFolderByName(String folderName) {
        //FacesMessage message = new FacesMessage();
        return folderDAO.findFolderByName(folderName);
    }
    
    public void setFolderName(String oldFolderName, String newFolderName) {
        //FacesMessage message = new FacesMessage();
        Folder folderToUpdate = folderDAO.findFolderByName(oldFolderName);
        folderToUpdate.setName(newFolderName);
        folderDAO.update(folderToUpdate);
    }
    
    public void setFolderParentFolder(String folderName, String parentName) {
        Folder child = folderDAO.findFolderByName(folderName);
        Folder parent = folderDAO.findFolderByName(parentName);
        
        if(parentChildRelationIsValid(parent, child)) {
            child.setParent(parent);
            folderDAO.update(child);
        }
        else System.out.print("A folder can not contain itself."); //FacesMessage message = new FacesMessage();
    }    
    
    public boolean parentChildRelationIsValid(Folder parent, Folder child) {
        Folder grandParent = parent.getParent();
        
        //If the parents parent is null there is no way the child is an ancestor of the parent.
        if(grandParent == null) return true;
        
        //If the child is the parents parent, the relationship is not valid.
        if(grandParent.getName().equals(child.getName())) return false;
        
        //Apply this logic recursively to each ancestor of the parent.
        return parentChildRelationIsValid(grandParent, child);
    }
    
    /* Won't be needed?
    public Folder findFolderInFolderByName(String folderName, String parentFolderName) {
        return folderDAO.findFolderInFolderByName(folderName, parentFolderName);
    }
    */
    
}
