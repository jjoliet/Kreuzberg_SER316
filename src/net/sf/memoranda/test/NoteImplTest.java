package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.NoteImpl;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectImpl;
import nu.xom.Attribute;
import nu.xom.Element;

public class NoteImplTest {
	static NoteImpl project;
	private static Project _project;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		final Element _root = new Element("projects-lists");
		
		Element el = new Element("project");
        el.addAttribute(new Attribute("id", "testFile"));
        _root.appendChild(el);
        project = new NoteImpl(el, _project);
	}
	
	@Test
	public void testTitle()
	{
		project.setTitle("testTitle");
		assertTrue(project.getTitle().equals("testTitle"));
	}

}