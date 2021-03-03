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

    /* Probably not necessary
    public Note getNoteById(int id) {
        return noteDAO.findById(id);
    }

    public Folder getFolderById(int id) {
        return folderDAO.findById(id);
    }
     */

    public Note createNote(String noteName) {
        Note note = new Note(noteName);
        noteDAO.create(note);
        return note;
    }

    public Folder createFolder(String folderName) {
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);
        return folder;
    }

    public void removeNote(Note note) {
        noteDAO.remove(note);
    }

    public void removeFolder(Folder folder) {
        folderDAO.remove(folder);
    }

    public void setNoteText(Note note, String newNoteText) {
        note.setText(newNoteText);
        noteDAO.update(note); //testa om det fungerar utan
    }

    public void setNoteName(Note note, String newNoteName) {
        note.setName(newNoteName);
        noteDAO.update(note);
    }

    public void setFolderName(Folder folder, String newFolderName) {
        folder.setName(newFolderName);
        folderDAO.update(folder);
    }

    public List<Note> getAllRootNotes() {
        return noteDAO.findAllInRoot();
    }

    public List<Folder> getAllRootFolders() {
        return folderDAO.findAllInRoot();
    }

    public List<Note> getAllNotesInFolder(Folder folder) {
        return folder.getNotes();
    }

    public List<Folder> getAllFoldersInFolder(Folder folder) {
        return folder.getChildFolders();
    }

    public void setNoteParentFolder(Note note, Folder folder) {
        note.setFolder(folder);
        noteDAO.update(note);
    }

    public void setFolderParentFolder(Folder child, Folder parent) {
        if (parentChildRelationIsValid(parent, child)) {
            child.setParent(parent);
            folderDAO.update(child);
        } else System.out.print("A folder can not contain itself."); //FacesMessage message = new FacesMessage();
    }

    boolean parentChildRelationIsValid(Folder parent, Folder child) {
        Folder grandParent = parent.getParent();

        //If the parents parent is null there is no way the child is an ancestor of the parent.
        if (grandParent == null) return true;

        //If the child is the parents parent, the relationship is not valid.
        if (grandParent.equals(child)) return false;

        //Apply this logic recursively to each ancestor of the parent.
        return parentChildRelationIsValid(grandParent, child);
    }
}
