package com.mycompany.noteappdat.model.service;

import com.mycompany.noteappdat.model.dao.FolderDAO;
import com.mycompany.noteappdat.model.dao.NoteDAO;
import com.mycompany.noteappdat.model.entity.Folder;
import com.mycompany.noteappdat.model.entity.Note;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class FileSystemServiceTest {
	@Deployment
	public static WebArchive createDeployment() {
            return ShrinkWrap.create(WebArchive.class)
                .addClasses(FolderDAO.class, Folder.class, NoteDAO.class, Note.class, FileSystemService.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
        
        @Inject
        FileSystemService fileSystemService;

	@EJB
	private	FolderDAO folderDAO;
        
        @EJB
        private NoteDAO noteDAO;

        private final String folderName = "test_folder_name";
        private final String differentFolderName = "test_different_folder_name";
        private final String noteName = "test_note_name";
        
       /*
	@Test
	public void createFolder() { 
            folderService.createFolder(folderName);
            Assert.assertTrue(folderDAO.findFolderByName(folderName) != null);
            folderDAO.remove(folderDAO.findFolderByName(folderName));
        }

        @Test
        public void removeFolder() {
            folderDAO.create(new Folder(folderName));
            folderService.removeFolder(folderName);
            Assert.assertTrue(folderDAO.findFolderByName(folderName) == null);
        }
        
        @Test
        public void getAllFoldersWithoutFolder() {
            folderDAO.create(new Folder(folderName));
            Assert.assertTrue(folderService.getAllFoldersWithoutFolder() != null);
            folderDAO.remove(folderDAO.findFolderByName(folderName));
        }

        @Test
        public void getAllNotesInFolder() {
            folderDAO.create(new Folder(folderName));
            noteDAO.create(new Note(noteName));
            
            Note note = noteDAO.findNoteByName(noteName);
            Folder folder = folderDAO.findFolderByName(folderName);
            note.setFolder(folder);
            noteDAO.update(note);
            
            Assert.assertTrue(folderService.getAllNotesInFolder(folderName) != null);
            
            noteDAO.remove(noteDAO.findNoteByName(noteName)); //This should be able to be last
            folderDAO.remove(folderDAO.findFolderByName(folderName)); //Can these be avoided?
            //noteDAO.remove(noteDAO.findNoteByName(noteName));
        }
        


        
        @Test
        public void getAllFoldersInFolder() {
            folderDAO.create(new Folder(folderName));
            folderDAO.create(new Folder(differentFolderName));
            
            Folder folder = folderDAO.findFolderByName(folderName);
            Folder parentFolder = folderDAO.findFolderByName(differentFolderName);
            folder.setParent(parentFolder);
            folderDAO.update(folder);
            
            Assert.assertTrue(folderService.getAllFoldersInFolder(folderName) != null);
            
            folderDAO.remove(folderDAO.findFolderByName(folderName));
            folderDAO.remove(folderDAO.findFolderByName(differentFolderName));
        }
        

        @Test
        public void getFolderByName() {
            folderDAO.create(new Folder(folderName));
            Assert.assertTrue(folderService.getFolderByName(folderName) != null);
            folderDAO.remove(folderDAO.findFolderByName(folderName));
        }

        @Test
        public void setFolderName() {
            folderDAO.create(new Folder(folderName));
            folderService.setFolderName(folderName, differentFolderName);
            Assert.assertTrue(folderDAO.findFolderByName(differentFolderName) != null);
            folderDAO.remove(folderDAO.findFolderByName(differentFolderName));
        }
                   
        @Test
        public void setFolderParentFolder() {
            folderDAO.create(new Folder(folderName));
            folderDAO.create(new Folder(differentFolderName));
            
            folderService.setFolderParentFolder(folderName, differentFolderName);
            
            Assert.assertTrue(folderDAO.findFolderByName(folderName).getParent() != null);
            
            folderDAO.remove(folderDAO.findFolderByName(folderName)); //same as previous
            folderDAO.remove(folderDAO.findFolderByName(differentFolderName));
            //folderDAO.remove(folderDAO.findFolderByName(folderName));
        }    
        
        @Test    
        public void parentChildRelationIsValid() {
            folderDAO.create(new Folder(folderName));
            folderDAO.create(new Folder(differentFolderName));
            
            Folder folder = folderDAO.findFolderByName(folderName);
            Folder parentFolder = folderDAO.findFolderByName(differentFolderName);
            folder.setParent(parentFolder);
            folderDAO.update(folder);
            
            Assert.assertTrue(folderService.parentChildRelationIsValid(parentFolder, folder));
            Assert.assertFalse(folderService.parentChildRelationIsValid(folder, parentFolder));
            
            folderDAO.remove(folderDAO.findFolderByName(folderName));
            folderDAO.remove(folderDAO.findFolderByName(differentFolderName));
        }

        */
}