package net.sf.memoranda.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.History;
import net.sf.memoranda.HistoryItem;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import net.sf.memoranda.date.CalendarDate;
import nu.xom.Element;



public class HistoryTest {
	HistoryItem testHI;
	@Before
	public void setUp() throws Exception {
		CalendarDate today = new CalendarDate();
		Project testProj = new ProjectImpl(new Element("testProj"));
		testHI = new HistoryItem(today, testProj);
	}

	@Test
	public void testRollBack() {
		History.add(testHI);
		History.add(new HistoryItem(new CalendarDate(), (Project)new ProjectImpl(new Element("SomethingElse"))));
		assertTrue((HistoryItem) History.rollBack() == testHI);
	}

	@Test
	public void testCanRollBack() {
		//Empty should return false
		assertFalse(History.canRollBack());
		//Filling, then testing to make sure it should be possible.
		History.add(testHI);
		History.add(new HistoryItem(new CalendarDate(), (Project)new ProjectImpl(new Element("SomethingElse"))));
		assertTrue(History.canRollBack());
	}

	@Test
	public void testCanRollForward() {
		assertFalse(History.canRollForward());
		History.add(testHI);
		History.add(new HistoryItem(new CalendarDate(), (Project)new ProjectImpl(new Element("SomethingElse"))));
		History.rollBack();
		assertTrue(History.canRollForward());
		
	}

}
