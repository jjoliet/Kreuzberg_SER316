package net.sf.memoranda.test;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.EventImpl;
import net.sf.memoranda.Project;
import net.sf.memoranda.TaskListImpl;
import nu.xom.Attribute;
import nu.xom.Element;

public class TaskListImplTest {
	static TaskListImpl project;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Project prj = null;
		
		final Element _root = new Element("projects-lists");
		
		Element el = new Element("project");
        el.addAttribute(new Attribute("id", "testFile"));
        _root.appendChild(el);
        project = new TaskListImpl(prj);
	}
	
	@Test
	public void testTitle()
	{
		assertTrue(project.getProject().equals("testFile"));
	}

}
