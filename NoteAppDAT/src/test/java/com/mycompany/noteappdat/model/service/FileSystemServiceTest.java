package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.*;

@RunWith(Arquillian.class)
public class FileSystemServiceTest {
    private final String folderName = "test_folder_name";
    private final String differentFolderName = "test_different_folder_name";
    private final String noteName = "test_note_name";
    private final String differentNoteName = "test_different_note_name";
    private final String noteText = "test_note_text";

    @Inject
    FileSystemService fileSystemService;
    @EJB
    private FolderDAO folderDAO;
    @EJB
    private NoteDAO noteDAO;
    @Inject
    private UserTransaction userTransaction;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(FolderDAO.class, Folder.class, NoteDAO.class, Note.class, FileSystemService.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void startTransaction() throws SystemException, NotSupportedException {
        userTransaction.begin();
    }

    @After
    public void commitTransaction() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException {
        userTransaction.commit();
    }

    @Test
    public void createNote() {
        //Create note
        Note note = fileSystemService.createNote(noteName, "PLACEHOLDER");

        //Assert that it exists in database
        Assert.assertTrue(noteDAO.findByName(noteName).contains(note)); //Better test than DAO equivalent?

        noteDAO.remove(note);
    }

    @Test
    public void createFolder() {
        //Create folder
        Folder folder = fileSystemService.createFolder(folderName);

        //Assert that it exists in database
        Assert.assertTrue(folderDAO.findByName(folderName).contains(folder));

        folderDAO.remove(folder);
    }

    @Test
    public void removeNote() {
        //Create note
        Note note = new Note(noteName);
        noteDAO.create(note);
        //Assert that it exists
        Assert.assertTrue(noteDAO.findByName(noteName).contains(note));

        //Remove it
        fileSystemService.removeNote(note);
        //Assert that it no longer exists in database
        Assert.assertFalse(noteDAO.findByName(noteName).contains(note));
    }

    @Test
    public void removeFolder() {
        //Create folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Create a note and set its parent to folder
        Note note = fileSystemService.createNote(noteName, "PLACEHOLDER");
        note.setFolder(folder);
        folderDAO.flush();
        folderDAO.refresh(folder);
        Assert.assertTrue(folder.getNotes().contains(note));

        //Set the folder name
        fileSystemService.removeFolder(folder);

        //Assert that folder doesn't exists in database
        Assert.assertFalse(folderDAO.findByName(folderName).contains(folder));

        //Assert that note parent is updated
        Assert.assertNull(note.getFolder());

        noteDAO.remove(note);
    }

    @Test
    public void setNoteText() {
        //Create note
        Note note = new Note(noteName);
        noteDAO.create(note);

        //Set the note text
        fileSystemService.setNoteText(note, noteText);
        noteDAO.update(note); //testa om det fungerar utan

        //Assert that note text matches
        Assert.assertSame(noteDAO.findById(note.getId()).getText(), noteText);

        noteDAO.remove(note);
    }

    @Test
    public void setNoteName() {
        //Create note
        Note note = new Note(noteName);
        noteDAO.create(note);

        //Set the note name
        fileSystemService.setNoteName(note, differentNoteName);

        //Assert that note exists in database with new name
        Assert.assertTrue(noteDAO.findByName(differentNoteName).contains(note));

        noteDAO.remove(note);
    }

    @Test
    public void setFolderName() {
        //Create folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Create a note and set its parent to folder
        Note note = fileSystemService.createNote(noteName, "PLACEHOLDER");
        note.setFolder(folder);
        folderDAO.flush();
        folderDAO.refresh(folder);
        Assert.assertTrue(folder.getNotes().contains(note));

        //Set the folder name
        fileSystemService.setFolderName(folder, differentFolderName);

        //Assert that folder exists in database with new name
        Assert.assertTrue(folderDAO.findByName(differentFolderName).contains(folder));

        //Assert that note parent is updated
        Assert.assertEquals(note.getFolder().getName(), differentFolderName);

        folderDAO.remove(folder);
        noteDAO.remove(note);
    }

    @Test
    public void getAllRootNotes() {
        //Create note
        Note note = fileSystemService.createNote(noteName, "PLACEHOLDER");

        //Assert that it exists in database root (aka parent is null)
        Assert.assertTrue(fileSystemService.getAllRootNotes().contains(note)); //Better test than DAO equivalent?

        noteDAO.remove(note);
    }

    @Test
    public void getAllRootFolders() {
        //Create folder
        Folder folder = fileSystemService.createFolder(folderName);

        //Assert that it exists in database root (aka parent is null)
        Assert.assertTrue(fileSystemService.getAllRootFolders().contains(folder));

        folderDAO.remove(folder);
    }

    @Test
    public void getAllNotesInFolder() {
        //Create folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Create a note and set its parent to folder
        Note note = fileSystemService.createNote(noteName, "PLACEHOLDER");
        note.setFolder(folder);
        folderDAO.flush();
        folderDAO.refresh(folder);
        Assert.assertTrue(fileSystemService.getAllNotesInFolder(folder).contains(note));

        folderDAO.remove(folder);
        noteDAO.remove(note);
    }

    @Test
    public void getAllFoldersInFolder() {
        //Create folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Create a different folder and set its parent to folder
        Folder parentFolder = fileSystemService.createFolder(differentFolderName);
        folder.setParent(parentFolder);
        folderDAO.flush();
        folderDAO.refresh(parentFolder);
        Assert.assertTrue(fileSystemService.getAllFoldersInFolder(parentFolder).contains(folder));

        folderDAO.remove(folder);
        folderDAO.remove(parentFolder);
    }

    @Test
    public void setNoteParentFolder() {
        //Create folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Create a note and set its parent to folder
        Note note = fileSystemService.createNote(noteName, "PLACEHOLDER");
        fileSystemService.setNoteParentFolder(note, folder);
        folderDAO.flush();
        folderDAO.refresh(folder);
        Assert.assertTrue(folder.getNotes().contains(note));

        folderDAO.remove(folder);
        noteDAO.remove(note);
    }

    @Test
    public void setFolderParentFolder() {
        //Create folder
        Folder childFolder = new Folder(folderName);
        folderDAO.create(childFolder);

        //Create a different folder and set its parent to folder
        Folder parentFolder = fileSystemService.createFolder(differentFolderName);
        fileSystemService.setFolderParentFolder(childFolder, parentFolder);
        folderDAO.flush();
        folderDAO.refresh(parentFolder);
        Assert.assertTrue(parentFolder.getChildFolders().contains(childFolder));

        folderDAO.remove(childFolder);
        folderDAO.remove(parentFolder);
    }

    @Test
    public void parentChildRelationIsValid() {
        //Create folder
        Folder childFolder = new Folder(folderName);
        folderDAO.create(childFolder);

        //Create a different folder and set its parent to folder
        Folder parentFolder = fileSystemService.createFolder(differentFolderName);
        childFolder.setParent(parentFolder);
        folderDAO.flush();
        folderDAO.refresh(parentFolder);
        Assert.assertTrue(parentFolder.getChildFolders().contains(childFolder));

        Assert.assertTrue(fileSystemService.parentChildRelationIsValid(parentFolder, childFolder));
        Assert.assertFalse(fileSystemService.parentChildRelationIsValid(childFolder, parentFolder));

        folderDAO.remove(childFolder);
        folderDAO.remove(parentFolder);
    }
}