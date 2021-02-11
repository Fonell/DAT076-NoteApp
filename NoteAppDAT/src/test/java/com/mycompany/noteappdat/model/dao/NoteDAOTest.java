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
			.addClasses(NoteDAO.class, Note.class, NotePK.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	NoteDAO NoteDAO;
        @EJB
	private	ClientDAO ClientDAO;
        
        private final String testCID = "CID_TEST";
        private final String testName = "NAME_TEST";

        private final String testTite = "TITLE_TEST";
        private final String testText = "TEXT_TEST";
        
        private Client testClient;

	@Before
	public void init() {
            ClientDAO.create(new Client(testName, testText));
            testClient = ClientDAO.findClientMatchingCID(testCID);
            NoteDAO.create(new Note(testClient, testTite, testText));
	}

	@Test
	public void findNoteMatchingPK() {
            NotePK pk = new NotePK(testClient, testTite);
            Assert.assertTrue(NoteDAO.findNoteMatchingPK(pk) != null);
	}
        
        @Test
	public void findNoteMatchingCIDAndTitle() {
            Assert.assertTrue(NoteDAO.findNoteMatchingCIDAndTitle(testClient, testTite) != null);
	}
        
        @After
        public void cleanup() {
            NoteDAO.remove(NoteDAO.findNoteMatchingCIDAndTitle(testClient, testTite));
            ClientDAO.remove(ClientDAO.findClientMatchingCID(testCID));
	}
}