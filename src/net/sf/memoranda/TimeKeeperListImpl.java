package net.sf.memoranda;

import java.util.Vector;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
Class:	TimeKeeperListImpl

Description: Implements the TimeKeeperListImpl interface to create a list of TimeKeeper objects to an XML file.
*/
public class TimeKeeperListImpl implements TimeKeeperList{
	private Project _project = null;
    private Document _doc = null;
    private Element _root = null;

    /**
     * Constructor for LOCListImpl
     * @param doc, the Document associated to the TimeKeeperList
     * @param prj, the project associated to the TimeKeeperList
     */
    public TimeKeeperListImpl(Document doc, Project prj) {
        _doc = doc;
        _root = _doc.getRootElement();
        _project = prj;
    }
    /**
     * Constructor for the LOCListImpl
     * @param prj, the project associated
     */
    public TimeKeeperListImpl(Project prj) {
            _root = new Element("TimeKeeperlist");
            _doc = new Document(_root);
            _project = prj;
    }

    /**
    Method: getTimeKeeper
    Inputs: String, String
    Returns: LOC

    Description: Returns a TimeKeeper object for the specified project and phase.
  */
    public TimeKeeper getTimeKeeper(String proj, String isPhase) {
        Elements rs = _root.getChildElements("TimeKeeper");
        for (int i = 0; i < rs.size(); i++)
            if (rs.get(i).getAttribute("proj").getValue().equalsIgnoreCase(proj)&& rs.get(i).getAttribute("isPhase").getValue().equalsIgnoreCase(isPhase))
                return (new TimeKeeper(rs.get(i).getAttribute("proj").getValue(), rs.get(i).getAttribute("isPhase").getValue(), Integer.parseInt(rs.get(i).getAttribute("isHours").getValue())));
        return null;
    }

    /**
    Method: addTimeKeeper
    Inputs: String, String, Int
    Returns: None

    Description: This is the way to Create a new TimeKeeper Item within a TimeKeeperList
  */
    public void addTimeKeeper(String proj, String isPhase, int isHours) {
        Element el = new Element("TimeKeeper");
        el.addAttribute(new Attribute("proj", proj));  
        el.addAttribute(new Attribute("isPhase", isPhase));
        el.addAttribute(new Attribute("isHours", String.valueOf(isHours)));
        _root.appendChild(el);
    }

    /**
    Method: removeTimeKeeper
    Inputs: String, String
    Returns: None

    Description: Searches for the TimeKeeper item that has the project and phase name, then removes it from the TimeKeeperList.
  */
    public void removeTimeKeeper(String proj, String isPhase) {
        Elements rs = _root.getChildElements("TimeKeeper");
        for (int i = 0; i < rs.size(); i++)
            if (rs.get(i).getAttribute("proj").getValue().equals(proj)&&rs.get(i).getAttribute("isPhase").getValue().equals(isPhase)) {
            	_root.removeChild(rs.get(i));
            }
    }
        

    /**
    Method: getAllTimeKeeperCount
    Inputs: None
    Returns: int

    Description: Returns the number of TimeKeeper items in the TimeKeeperList
  */
    public int getAllTimeKeeperCount() {
        return _root.getChildElements("TimeKeeper").size();
    }
    /**
    Method: getXMLContent()
    Inputs: None
    Returns: Document

    Description: Retrieves the XML document associated with the TimeKeeperList item.
  */
    public Document getXMLContent() {
        return _doc;
    }
    
    /**
    Method: getAllTimeKeepers
    Inputs: None
    Returns: Vector

    Description: Returns a Vector of TimeKeeper Objects from the TimeKeeperList.
  */
	public Vector<TimeKeeper> getAllTimeKeepers() {
		 Vector<TimeKeeper> v = new Vector<TimeKeeper>();
	        Elements rs = _root.getChildElements("TimeKeeper");
	        for (int i = 0; i < rs.size(); i++)
	            v.add(new TimeKeeper(rs.get(i).getAttribute("proj").getValue(), rs.get(i).getAttribute("isPhase").getValue(), Integer.parseInt(rs.get(i).getAttribute("isHours").getValue())));
	        return v;
	}
       
}
