package net.sf.memoranda.test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.prism.impl.ps.BaseShaderContext.SpecialShaderType;

import net.sf.memoranda.ui.App;
import net.sf.memoranda.util.Configuration;

public class AutosaveTest {

	App app = null;

	public static boolean splashOrig = false;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//store original splash setting before disabling for test
		
		AutosaveTest.splashOrig = ((boolean)Configuration.get("SHOW_SPLASH").equals("no")) ? false : true; 
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//change back to original setting
		Configuration.put("SHOW_SPLASH", splashOrig == true ? "yes" : "no"); 	
	}

	@Before
	public void setUp() throws Exception {
		Configuration.put("SHOW_SPLASH", "no"); //hide splash for test
		app = new App(false);
		
	}

	@After
	public void tearDown() throws Exception {
		app = null;

	}

	@Test
	public void testRepeats() {
		assertTrue(App.getFrame().getAutoSaveTimer().isRepeats() == true);
	}
	@Test
	public void testDelay() {
		assertTrue(App.getFrame().getAutoSaveTimer().getDelay() == App.getFrame().delay);
	}

}
