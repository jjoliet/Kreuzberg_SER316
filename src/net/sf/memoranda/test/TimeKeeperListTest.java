package net.sf.memoranda.test;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.TimeKeeperListImpl;
import nu.xom.Document;
import nu.xom.Element;

public class TimeKeeperListTest {
	
	Element root = new Element("root");
	Document testDoc = new Document(root);
	Project testProj = new ProjectImpl(root);
	
	TimeKeeperListImpl timeKeeper = new TimeKeeperListImpl(testDoc, testProj);

	@Test
	public void testAddTimeKeeper() {
		String path = "pathfile";
		double listLen = (double) timeKeeper.getAllTimeKeeperCount();
		assertEquals(0.0, listLen, 0.01);
		timeKeeper.addTimeKeeper(path, "testPhase", 1);
		listLen = (double) timeKeeper.getAllTimeKeeperCount();
		assertEquals(1.0, listLen, 0.01);
	}
	
	@Test
	public void testGetAllTimeKeepers() {
		Vector listV = timeKeeper.getAllTimeKeepers();
		String path = "pathfile";
		double listVLen = (double) listV.size();
		assertEquals(0.0, listVLen, 0.01);
		timeKeeper.addTimeKeeper(path, "testPhase", 1);
		timeKeeper.addTimeKeeper(path, "otherPhase", 15);
		listV = timeKeeper.getAllTimeKeepers();
		listVLen = (double) listV.size();
		assertEquals(2.0, listVLen, 0.01);
	}
	
	@Test
	public void testRemoveTimeKeepers() {
		String path = "pathfile";
		double listLen = (double) timeKeeper.getAllTimeKeeperCount();
		assertEquals(0.0, listLen, 0.01);
		timeKeeper.addTimeKeeper(path, "testPhase", 1);
		listLen = (double) timeKeeper.getAllTimeKeeperCount();
		assertEquals(1.0, listLen, 0.01);
		timeKeeper.removeTimeKeeper(path, "testPhase");
		listLen = (double) timeKeeper.getAllTimeKeeperCount();
		assertEquals(0.0, listLen, 0.01);
	}

}
