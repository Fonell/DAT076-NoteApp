package com.mycompany.noteappdat.model.dao;

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
import java.util.List;


@RunWith(Arquillian.class)
public class NoteDAOTest {
    private final String noteName = "test_note_name";
    private final String folderName = "test_folder_name";
    @EJB
    private NoteDAO noteDAO;
    @EJB
    private FolderDAO folderDAO;
    @Inject
    private UserTransaction userTransaction;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(NoteDAO.class, Note.class, FolderDAO.class, Folder.class)
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
    public void findById() {
        //Create the note
        Note note = new Note(noteName);
        noteDAO.create(note);
        int id = note.getId();

        //Assert that it can be found by id
        Assert.assertEquals(noteDAO.findById(id), note);

        noteDAO.remove(note);
    }

    @Test
    public void findByName() {
        //Create the note
        Note note = new Note(noteName);
        noteDAO.create(note);

        //Assert that it can be found in the database
        List<Note> notes = noteDAO.findByName(noteName);
        Assert.assertTrue(notes.contains(note));

        noteDAO.remove(note);
    }

    @Test
    public void findAllInRoot() {
        //Create the note and check that it is in root (aka has no folder)
        Note note = new Note(noteName);
        noteDAO.create(note);
        List<Note> notes = noteDAO.findAllInRoot();
        Assert.assertTrue(notes.contains(note));

        //Create a folder and add the note to it, check that the note is no longer in root
        Folder folder = new Folder(folderName);
        note.setFolder(folder);
        noteDAO.update(note);
        notes = noteDAO.findAllInRoot();
        Assert.assertFalse(notes.contains(note));

        folderDAO.remove(folder);
        noteDAO.remove(note);
    }

    @Test
    public void findAllInFolder() {
        //Create the note and check that it is in root (aka has no folder)
        Note note = new Note(noteName);
        noteDAO.create(note);
        List<Note> notes = noteDAO.findAllInRoot();
        Assert.assertTrue(notes.contains(note));

        //Create a folder and add the note to it, check that the note is in its parent folder
        Folder folder = new Folder(folderName);
        note.setFolder(folder);
        noteDAO.update(note);
        notes = noteDAO.findAllInRoot();
        Assert.assertFalse(notes.contains(note));

        folderDAO.remove(folder);
        noteDAO.remove(note);
    }
}