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
public class FolderDAOTest {
	@Deployment
	public static WebArchive createDeployment() {
            return ShrinkWrap.create(WebArchive.class)
                .addClasses(FolderDAO.class, Folder.class, NoteDAO.class, Note.class, Event.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	FolderDAO FolderDAO;

        private final String folderName = "test_folder_name";
        private final String parentFolderName = "test_parent_folder_name";
        
        
	@Before
	public void init() {
            FolderDAO.createFolder(folderName);
            FolderDAO.createFolder(parentFolderName);
	}
        
	@Test
	public void findFolderByName() {
            Assert.assertTrue(FolderDAO.findFolderByName(folderName) != null);
	}
        
        @Test
        public void setParentFolder() {
            FolderDAO.setParentFolder(parentFolderName, folderName);
            Assert.assertTrue(FolderDAO.findFolderInFolderByName(folderName, parentFolderName) != null);
        }
        
        @Test
        public void parentChildRelationIsValid() {
            FolderDAO.setParentFolder(parentFolderName, folderName);

            Assert.assertTrue(!FolderDAO.parentChildRelationIsValid(FolderDAO.findFolderByName(folderName), FolderDAO.findFolderByName(parentFolderName)));
            Assert.assertTrue(FolderDAO.parentChildRelationIsValid(FolderDAO.findFolderByName(parentFolderName), FolderDAO.findFolderByName(folderName)));
        }
        
        @After
        public void cleanup() {
            FolderDAO.remove(FolderDAO.findFolderByName(folderName));
            FolderDAO.remove(FolderDAO.findFolderByName(parentFolderName));
	}
}