package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import java.util.List;
import javax.ejb.EJB;

public class FolderService {
    
    @EJB
    private NoteDAO noteDAO;
    @EJB
    private FolderDAO folderDAO;
    
    public void createFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        folderDAO.create(new Folder(folderName));
    }
    
    public List<Note> getAllNotes() {
        //FacesMessage message = new FacesMessage();
        return noteDAO.findAll();
    }
    
    public List<Note> getAllNotesInFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        Folder folder = findFolderByName(folderName);
        return folder.getNotes();
    }
    
    public List<Folder> getAllFoldersInFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        Folder folder = findFolderByName(folderName);
        return folder.getChildren();
    }
    
    public Folder findFolderByName(String folderName) {
        //FacesMessage message = new FacesMessage();
        return folderDAO.findFolderByName(folderName);
    }
    
    public void setNoteParentFolder(String noteName, String folderName) {
        //FacesMessage message = new FacesMessage();
        Note note = noteDAO.findNoteByName(noteName);
        Folder folder = folderDAO.findFolderByName(folderName);
        note.setFolder(folder);
        noteDAO.update(note);
    }
    
    public void setFolderParentFolder(String parentName, String childName) {
        Folder child = folderDAO.findFolderByName(childName);
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
