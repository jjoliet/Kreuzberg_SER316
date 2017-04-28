package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.EventImpl;
import nu.xom.Attribute;
import nu.xom.Element;

public class EventImplTest {
	static EventImpl project;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final Element _root = new Element("projects-lists");
		
		Element el = new Element("project");
        el.addAttribute(new Attribute("id", "testFile"));
        _root.appendChild(el);
        project = new EventImpl(el);
	}
	
	@Test
	public void testTitle()
	{
		assertTrue(project.getId().equals("testFile"));
	}

}
