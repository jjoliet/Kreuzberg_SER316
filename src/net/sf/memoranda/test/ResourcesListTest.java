package net.sf.memoranda.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.ResourcesListImpl;
import nu.xom.*;

public class ResourcesListTest {
	
	Element root = new Element("root");
	Document testDoc = new Document(root);
	Project testProj = new ProjectImpl(root);
	
	ResourcesListImpl testResList = new ResourcesListImpl(testDoc, testProj);

	@Test
	public void testAddResource() {
		String path = "pathFile";
		double listlen = (double) testResList.getAllResourcesCount();
		assertEquals(0.0, listlen, 0.01);
		testResList.addResource(path);
		listlen = (double) testResList.getAllResourcesCount();
		assertEquals(1.0, listlen, 0.01);
		
	}
	
	@Test
	public void testGetAllResources() {
		Vector listV = testResList.getAllResources();
		String path = "pathFile";
		double listVLen = (double) listV.size();
		assertEquals(0.0, listVLen, 0.01);
		testResList.addResource(path);
		listV = testResList.getAllResources();
		listVLen = (double) listV.size();
		assertEquals(1.0, listVLen, 0.01);
		
	}
	
	@Test
	public void testRemoveResource() {
		String path = "pathFile";
		double listlen = (double) testResList.getAllResourcesCount();
		assertEquals(0.0, listlen, 0.01);
		testResList.addResource(path);
		listlen = (double) testResList.getAllResourcesCount();
		assertEquals(1.0, listlen, 0.01);
		testResList.removeResource(path);
		listlen = (double) testResList.getAllResourcesCount();
		assertEquals(0.0, listlen, 0.01);
		
	}
	

}
