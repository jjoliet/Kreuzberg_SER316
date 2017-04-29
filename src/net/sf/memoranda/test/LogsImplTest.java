package net.sf.memoranda.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.memoranda.LogsImpl;

public class LogsImplTest {
	static LogsImpl project;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*final Element _root = new Element("logs-lists");
		
		Element el = new Element("project");
        el.addAttribute(new Attribute("id", "testFile"));
        _root.appendChild(el);*/
        project = new LogsImpl("textTitle", "textNumber", "textDate", "textType", 
				"textInject", "textReason", "textFixTime", 
				"textFixingDef", "textDesc");
	}
	
	@Test
	public void testMeh()
	{
		fail();
	}
	
/*	@Test
	public void testTitle()
	{
		assertTrue(project.get_textTitle().equals("textTitle"));
		project.set_textTitle("New text title");
		assertTrue(project.get_textTitle().equals("New text title"));
		assertTrue(project.get_textNumber().equals("textNumber"));
		project.set_textNumber("55");
		assertTrue(project.get_textNumber().equals("55"));
		assertTrue(project.get_textDate().equals("textDate"));
		project.set_textDate("4/28/2017");
		assertTrue(project.get_textDate().equals("4/28/20170"));
		assertTrue(project.get_textType().equals("textType"));
		project.set_textType("Silly");
		assertTrue(project.get_textType().equals("Silly"));
		assertTrue(project.get_textInject().equals("textInject"));
		project.set_textInject("Early");
		assertTrue(project.get_textInject().equals("Early"));
		assertTrue(project.get_textReason().equals("textReason"));
		project.set_textReason("Oops");
		assertTrue(project.get_textReason().equals("Oops"));
		assertTrue(project.get_textFixTime().equals("testFixTime"));
		project.set_textFixTime("5 hours");
		assertTrue(project.get_textFixTime().equals("5 hours"));
		assertTrue(project.get_textFixingDef().equals("textFixingDef"));
		project.set_textFixingDef("Assignee");
		assertTrue(project.get_textFixingDef().equals("Assignee"));
		assertTrue(project.get_textDesc().equals("textDesc"));
		project.set_textDesc("Updating defect");
		assertTrue(project.get_textDesc().equals("Updating defect"));
		
	}*/

}
