package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.LOC;




public class LOCTest {
	LOC testLOC;
	
	@Before
	public void setUp() throws Exception {
		testLOC = new LOC("testProj", "testClass", 55);
	}

	@Test
	public void testLOC() {
		LOC newtestLOC = new LOC("verify", "constructor", 0);
		assertNotNull(newtestLOC);
	}

	@Test
	public void testLOCString() {
		LOC testOtherCon = new LOC("Onlyproject string");
		assertNotNull(testOtherCon);
	}

	@Test
	public void testGetProject() {
		assertTrue(testLOC.getProject().equalsIgnoreCase("testProj"));
	}

	@Test
	public void testGetLinesClass() {
		assertTrue(testLOC.getLinesClass().equalsIgnoreCase("testClass"));
	}

	@Test
	public void testGetLines() {
		assertTrue(testLOC.getLines() == 55);
	}

}
