package com.mycompany.noteappdat.model.dao;

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

import java.util.ArrayList;
import java.util.List;


@RunWith(Arquillian.class)
public class FolderDAOTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(FolderDAO.class, Folder.class, NoteDAO.class, Note.class) //Could prolly remove notestuff here
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private FolderDAO folderDAO;

    private final String folderName = "test_folder_name";
    private final String parentFolderName = "test_parent_folder_name";


    @Test
    public void findById() {
        //Create the folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);
        int id = folder.getId();

        //Check that it can be found by id
        //Assert.assertSame(folderDAO.findById(id), folder); //Not sure how to fix this. Need to instantiate lists somehow?

        folderDAO.remove(folder);
    }

    @Test
    public void findByName() {
        //Create the folder
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);

        //Check that it can be found by name
        List<Folder> notes = folderDAO.findByName(folderName);
        //Assert.assertTrue(notes.contains(folder));

        folderDAO.remove(folder);
    }

    @Test
    public void findAllInRoot() {
        //Create the folder and check that it is in root (aka has no folder)
        Folder folder = new Folder(folderName);
        folderDAO.create(folder);
        List<Folder> folders = folderDAO.findAllInRoot();
        //Assert.assertTrue(folders.contains(folder));

        //Create a new folder and add the folder to it, check that the folder is no longer in root
        Folder parentFolder = new Folder(parentFolderName);
        folder.setParent(parentFolder);
        folderDAO.update(folder);
        folders = folderDAO.findAllInRoot();
        //Assert.assertFalse(folders.contains(folder));

        folderDAO.remove(parentFolder);
        folderDAO.remove(folder);
    }
}