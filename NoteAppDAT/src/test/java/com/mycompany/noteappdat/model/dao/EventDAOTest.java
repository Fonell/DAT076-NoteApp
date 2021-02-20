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
public class EventDAOTest {
	@Deployment
	public static WebArchive createDeployment() {
            return ShrinkWrap.create(WebArchive.class)
                .addClasses(EventDAO.class, Event.class, NoteDAO.class, Note.class, Folder.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	EventDAO EventDAO;

        private final String noteTitle = "test_note_title";

        private final String eventTitle = "test_event_title";
        
	@Before
	public void init() {
            EventDAO.create(new Event(eventTitle));
	}
        
	@Test
	public void findEventByName() {
            Assert.assertTrue(EventDAO.findEventByName(eventTitle) != null);
	}
        
        @After
        public void cleanup() {
            EventDAO.remove(EventDAO.findEventByName(eventTitle));
	}
}