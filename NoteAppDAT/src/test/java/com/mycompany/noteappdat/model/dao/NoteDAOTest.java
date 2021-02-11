package com.mycompany.noteappdat.model.dao;

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
        @EJB
	private	ClientDAO ClientDAO;
        
        private final String testCID = "CID_TEST";
        private final String testName = "NAME_TEST";

        private final String testTite = "TITLE_TEST";
        private final String testText = "TEXT_TEST";
        
        private Client testClient = new Client(testName, testText);

	@Before
	public void init() {
            NoteDAO.create(new Note(testClient, testTite, testText));
	}
        
        @Test
	public void findNoteMatchingCIDAndTitle() {
            Assert.assertTrue(NoteDAO.findNoteMatchingCIDAndTitle(testClient, testTite) != null);
	}
        
        @After
        public void cleanup() {
            NoteDAO.remove(NoteDAO.findNoteMatchingCIDAndTitle(testClient, testTite));
	}
}




ArquillianServletRunner not found. Could not determine ContextRoot from ProtocolMetadata, please contact DeployableContainer developer.
java.lang.IllegalArgumentException
	at org.jboss.arquillian.protocol.servlet.ServletUtil.determineBaseURI(ServletUtil.java:61)
	at org.jboss.arquillian.protocol.servlet.ServletURIHandler.locateTestServlet(ServletURIHandler.java:54)
	at org.jboss.arquillian.protocol.servlet.ServletMethodExecutor.invoke(ServletMethodExecutor.java:75)
	at org.jboss.arquillian.container.test.impl.execution.RemoteTestExecuter.execute(RemoteTestExecuter.java:103)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:86)
	at org.jboss.arquillian.core.impl.EventContextImpl.invokeObservers(EventContextImpl.java:103)
	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:90)
	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:133)
	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:105)
	at org.jboss.arquillian.core.impl.EventImpl.fire(EventImpl.java:62)
	at org.jboss.arquillian.container.test.impl.execution.ClientTestExecuter.execute(ClientTestExecuter.java:52)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:86)
	at org.jboss.arquillian.core.impl.EventContextImpl.invokeObservers(EventContextImpl.java:103)
	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:90)
	at org.jboss.arquillian.container.test.impl.client.ContainerEventController.createContext(ContainerEventController.java:128)
	at org.jboss.arquillian.container.test.impl.client.ContainerEventController.createTestContext(ContainerEventController.java:118)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:86)
	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:95)
	at org.jboss.arquillian.test.impl.TestContextHandler.createSuiteContext(TestContextHandler.java:69)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:86)
	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:95)
	at org.jboss.arquillian.test.impl.TestContextHandler.createClassContext(TestContextHandler.java:83)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:86)
	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:95)
	at org.jboss.arquillian.test.impl.TestContextHandler.createTestContext(TestContextHandler.java:116)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.jboss.arquillian.core.impl.ObserverImpl.invoke(ObserverImpl.java:86)
	at org.jboss.arquillian.core.impl.EventContextImpl.proceed(EventContextImpl.java:95)
	at org.jboss.arquillian.core.impl.ManagerImpl.fire(ManagerImpl.java:133)
	at org.jboss.arquillian.test.impl.EventTestRunnerAdaptor.test(EventTestRunnerAdaptor.java:122)
	at org.jboss.arquillian.junit.Arquillian$8.evaluate(Arquillian.java:330)
	at org.jboss.arquillian.junit.Arquillian$4.evaluate(Arquillian.java:212)
	at org.jboss.arquillian.junit.Arquillian.multiExecute(Arquillian.java:376)
	at org.jboss.arquillian.junit.Arquillian.access$200(Arquillian.java:54)
	at org.jboss.arquillian.junit.Arquillian$5.evaluate(Arquillian.java:223)
	at org.jboss.arquillian.junit.Arquillian$7.evaluate(Arquillian.java:293)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.jboss.arquillian.junit.Arquillian$2.evaluate(Arquillian.java:174)
	at org.jboss.arquillian.junit.Arquillian.multiExecute(Arquillian.java:376)
	at org.jboss.arquillian.junit.Arquillian.access$200(Arquillian.java:54)
	at org.jboss.arquillian.junit.Arquillian$3.evaluate(Arquillian.java:185)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.jboss.arquillian.junit.Arquillian.run(Arquillian.java:140)
	at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)
	at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)
	at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)
	at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)
	at org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)
	at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)
	at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)
