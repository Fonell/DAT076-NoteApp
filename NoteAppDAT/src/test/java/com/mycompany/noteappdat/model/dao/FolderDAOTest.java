package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
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
public class FolderDAOTest {
    private final String folderName = "test_folder_name";
    private final String parentFolderName = "test_parent_folder_name";
    private final String noteName = "test_note_name";
    @EJB
    private FolderDAO folderDAO;
    @EJB
    private NoteDAO noteDAO;
    @Inject
    private UserTransaction userTransaction;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(FolderDAO.class, Folder.class, NoteDAO.class, Note.class)
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
        //Create the folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);
        int id = folder.getId();
        folderDAO.flush();

        //Assert that it can be found by id in the database
        Assert.assertEquals(folderDAO.findById(id), folder);

        folderDAO.remove(folder);
    }

    @Test
    public void findByName() {
        //Create the folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Assert that it can be found by name
        List<Folder> notes = folderDAO.findByName(folderName);
        Assert.assertTrue(notes.contains(folder));

        folderDAO.remove(folder);
    }

    @Test
    public void findAllInRoot() {
        //Create the folder and check that it is in root (aka folder is null)
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);
        List<Folder> folders = folderDAO.findAllInRoot();
        Assert.assertTrue(folders.contains(folder));

        //Create a new folder and add the folder to it, assert that the folder is no longer in root
        Folder parentFolder = new Folder(parentFolderName);
        folder.setParent(parentFolder);
        folderDAO.update(folder);
        folders = folderDAO.findAllInRoot();
        Assert.assertFalse(folders.contains(folder));

        folderDAO.remove(parentFolder);
        folderDAO.remove(folder);
    }

    @Test
    public void remove() {
        //Create folder and note
        Folder parentFolder = new Folder(parentFolderName);
        folderDAO.create(parentFolder);

        Note note = new Note(noteName);
        noteDAO.create(note);

        //Add note to folder
        note.setFolder(parentFolder);
        noteDAO.flush();
        //noteDAO.update(note); //Behövs antagligen bara i test, kontrollera andra ställen

        //Assert that note has folder as parent
        Assert.assertEquals(note.getFolder(), parentFolder);

        //Assert that note is in folder
        folderDAO.refresh(parentFolder);
        List<Note> notes = parentFolder.getNotes();
        Assert.assertTrue(notes.contains(note));

        //Remove the folder
        folderDAO.remove(parentFolder);

        //Assert that folder does not exist in database
        Assert.assertNull(folderDAO.findById(parentFolder.getId()));

        //Assert that note has no folder
        Assert.assertNull(note.getFolder());

        noteDAO.remove(note);
    }
}