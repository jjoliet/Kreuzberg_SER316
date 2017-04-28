package net.sf.memoranda;

import java.util.Vector;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
Class:	LOCLISTImpl

Description: Implements the LOCList interface to create a list of LOC objects to an XML file.
*/
public class LOCListImpl implements LOCList{
	private Project _project = null;
    private Document _doc = null;
    private Element _root = null;

    /**
     * Constructor for LOCListImpl
     * @param doc, the Document associated to the LOCList
     * @param prj, the project associated to the LOCList
     */
    public LOCListImpl(Document doc, Project prj) {
        _doc = doc;
        _root = _doc.getRootElement();
        _project = prj;
    }
    /**
     * Constructor for the LOCListImpl
     * @param prj, the project associated
     */
    public LOCListImpl(Project prj) {
            _root = new Element("LOClist");
            _doc = new Document(_root);
            _project = prj;
    }

    /**
    Method: getLOC
    Inputs: String, String
    Returns: LOC

    Description: Returns a LOC object for the specified project and class.
  */
    public LOC getLOC(String proj, String isClass) {
        Elements rs = _root.getChildElements("LOC");
        for (int i = 0; i < rs.size(); i++)
            if (rs.get(i).getAttribute("proj").getValue().equalsIgnoreCase(proj)&& rs.get(i).getAttribute("isClass").getValue().equalsIgnoreCase(isClass))
                return (new LOC(rs.get(i).getAttribute("proj").getValue(), rs.get(i).getAttribute("isClass").getValue(), Integer.parseInt(rs.get(i).getAttribute("isLines").getValue())));
        return null;
    }

    /**
    Method: addLOC
    Inputs: String, String, Int
    Returns: None

    Description: This is the way to Create a new LOC Item within a LOCList
  */
    public void addLOC(String proj, String isClass, int isLines) {
        Element el = new Element("LOC");
        el.addAttribute(new Attribute("proj", proj));  
        el.addAttribute(new Attribute("isClass", isClass));
        el.addAttribute(new Attribute("isLines", String.valueOf(isLines)));
        _root.appendChild(el);
    }

    /**
    Method: removeLOC
    Inputs: String, String
    Returns: None

    Description: Searches for the LOC item that has the project and class name, then removes it from the LOCList.
  */
    public void removeLOC(String proj, String isClass) {
        Elements rs = _root.getChildElements("LOC");
        for (int i = 0; i < rs.size(); i++)
            if (rs.get(i).getAttribute("proj").getValue().equals(proj)&&rs.get(i).getAttribute("isClass").getValue().equals(isClass)) {
            	_root.removeChild(rs.get(i));
            }
    }
        

    /**
    Method: getAllLOCCount
    Inputs: None
    Returns: int

    Description: Returns the number of LOC items in the LOCList
  */
    public int getAllLOCCount() {
        return _root.getChildElements("LOC").size();
    }
    /**
    Method: getXMLContent()
    Inputs: None
    Returns: Document

    Description: Retrieves the XML document associated with the LOCList item.
  */
    public Document getXMLContent() {
        return _doc;
    }
    
    /**
    Method: getAllLOCs
    Inputs: None
    Returns: Vector

    Description: Returns a Vector of LOC Objects from the LOCList.
  */
	public Vector<LOC> getAllLOCs() {
		 Vector<LOC> v = new Vector<LOC>();
	        Elements rs = _root.getChildElements("LOC");
	        for (int i = 0; i < rs.size(); i++)
	            v.add(new LOC(rs.get(i).getAttribute("proj").getValue(), rs.get(i).getAttribute("isClass").getValue(), Integer.parseInt(rs.get(i).getAttribute("isLines").getValue())));
	        return v;
	}
       
}
