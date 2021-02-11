package com.mycompany.noteappdat.model.dao;

import com.mycompany.noteappdat.model.entity.Client;
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
public class ClientDAOTest {
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
			.addClasses(ClientDAO.class, Client.class)
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	private	ClientDAO ClientDAO;
        
        private final String testCID = "CID_TEST";
        private final String testName = "NAME_TEST";

	@Before
	public void init() {
            ClientDAO.create(new Client(testCID, testName));
	}

	@Test
	public void findClientMatchingCID() {
            Assert.assertTrue(ClientDAO.findClientMatchingCID(testCID) != null);
	}
        
        @After
        public void cleanup() {
            ClientDAO.remove(ClientDAO.findClientMatchingCID(testCID));
	}
}
