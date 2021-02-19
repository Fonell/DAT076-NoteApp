package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.dao.key.NotePK;
import com.mycompany.noteappdat.model.entity.Client;
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
                .addClasses(NoteDAO.class, Note.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	NoteDAO NoteDAO;
        //@EJB
	//private	ClientDAO ClientDAO;

        private final String testTite = "TITLE_TEST";
        private final String testText = "TEXT_TEST";

        /*
	@Before
	public void init() {
            NoteDAO.create(new Note(testTite, testText));
            NoteDAO.remove(NoteDAO.findNoteMatchingTitle(testTite));
	}
        */


	@Test
	public void findNoteMatchingTitle() {
            NoteDAO.create(new Note(testTite, testText));
            NoteDAO.remove(NoteDAO.findNoteMatchingTitle(testTite));
            //Assert.assertTrue(NoteDAO.findNoteMatchingTitle(testTite) != null);
	}
        /*
        @Test
	public void findNoteMatchingCIDAndTitle() {
            //Assert.assertTrue(true);
            //Assert.assertTrue(NoteDAO.findNoteMatchingCIDAndTitle(ClientDAO.findClientMatchingCID(testCID), testTite) != null);
	}
        
        @After
        public void cleanup() {
            //NoteDAO.remove(NoteDAO.findNoteMatchingCIDAndTitle(ClientDAO.findClientMatchingCID(testCID), testTite));
            //ClientDAO.remove(ClientDAO.findClientMatchingCID(testCID));
	}
*/
}