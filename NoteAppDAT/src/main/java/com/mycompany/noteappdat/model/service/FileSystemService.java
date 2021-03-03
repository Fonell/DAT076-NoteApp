package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/** Service for browsing and editing in the file system.
 * @author Emil Holmsten
 */
@Stateless
public class FileSystemService {

    @EJB
    private NoteDAO noteDAO;
    @EJB
    private FolderDAO folderDAO;

    public Note getNoteById(int id) {
        return noteDAO.findById(id);
    }

    public Folder getFolderById(int id) {
        return folderDAO.findById(id);
    }

    public void createNote(String noteName) {
        //FacesMessage message = new FacesMessage();
        noteDAO.create(new Note(noteName));
    }

    public void createFolder(String folderName) {
        //FacesMessage message = new FacesMessage();
        folderDAO.create(new Folder(folderName));
    }

    public void removeNote(Note note) {
        //FacesMessage message = new FacesMessage();
        noteDAO.remove(note);
    }

    public void removeFolder(Folder folder) {
        //FacesMessage message = new FacesMessage();
        folderDAO.remove(folder);
    }

    public void setNoteText(Note note, String newNoteText) {
        //FacesMessage message = new FacesMessage();
        note.setText(newNoteText);
        noteDAO.update(note); //testa om det fungerar utan
    }

    public void setNoteName(Note note, String newNoteName) {
        //FacesMessage message = new FacesMessage();
        note.setName(newNoteName);
        noteDAO.update(note);
    }

    public void setFolderName(Folder folder, String newFolderName) {
        //FacesMessage message = new FacesMessage();
        folder.setName(newFolderName);
        folderDAO.update(folder);
    }

    public List<Note> getAllRootNotes() {
        //FacesMessage message = new FacesMessage();
        return noteDAO.findAllInRoot();
    }

    public List<Folder> getAllRootFolders() {
        //FacesMessage message = new FacesMessage();
        return folderDAO.findAllInRoot();
    }

    public List<Note> getAllNotesInFolder(Folder folder) {
        //FacesMessage message = new FacesMessage();
        return folder.getNotes();
    }

    public List<Folder> getAllFoldersInFolder(Folder folder) {
        //FacesMessage message = new FacesMessage();
        return folder.getChildFolders();
    }

    public void setNoteParentFolder(Note note, Folder folder) {
        //FacesMessage message = new FacesMessage();
        note.setFolder(folder);
        noteDAO.update(note);
    }

    public void setFolderParentFolder(Folder child, Folder parent) {
        if (parentChildRelationIsValid(parent, child)) {
            child.setParent(parent);
            folderDAO.update(child);
        } else System.out.print("A folder can not contain itself."); //FacesMessage message = new FacesMessage();
    }

    private boolean parentChildRelationIsValid(Folder parent, Folder child) {
        Folder grandParent = parent.getParent();

        //If the parents parent is null there is no way the child is an ancestor of the parent.
        if (grandParent == null) return true;

        //If the child is the parents parent, the relationship is not valid.
        if (grandParent.getName().equals(child.getName())) return false; //Is there a better way to write this equals?

        //Apply this logic recursively to each ancestor of the parent.
        return parentChildRelationIsValid(grandParent, child);
    }
}
