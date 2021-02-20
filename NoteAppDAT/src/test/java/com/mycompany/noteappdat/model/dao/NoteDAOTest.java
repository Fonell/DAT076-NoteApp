package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Event;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import javax.ejb.EJB;
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


@RunWith(Arquillian.class)
public class NoteDAOTest {
	@Deployment
	public static WebArchive createDeployment() {
            return ShrinkWrap.create(WebArchive.class)
                .addClasses(NoteDAO.class, Note.class, FolderDAO.class, Folder.class, Event.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	NoteDAO NoteDAO;
        @EJB
	private	FolderDAO FolderDAO;

        private final String noteTitle = "test_note_title";
        private final String noteText = "test_note_text";
        
        private final String folderName = "test_folder_name";
        
	@Before
	public void init() {
            FolderDAO.create(new Folder(folderName));
            NoteDAO.create(new Note(noteTitle));
	}
        
	@Test
	public void findNoteMatchingTitle() {
            Assert.assertTrue(NoteDAO.findNoteByTitle(noteTitle) != null);
	}
        
        @Test
	public void addNoteToFolder() {
            NoteDAO.findNoteByTitle(noteTitle).setFolder(FolderDAO.findFolderByName(folderName));
            //FolderDAO.findFolderByName(folderName).getNotes();
            Assert.assertTrue(NoteDAO.findNoteByTitle(noteTitle) != null);
	}
        
        @After
        public void cleanup() {
            NoteDAO.remove(NoteDAO.findNoteByTitle(noteTitle));
            FolderDAO.remove(FolderDAO.findFolderByName(folderName));
	}
}