package net.sf.memoranda;

import java.util.Vector;

import nu.xom.Document;

/**
Interface:	LOCList

Description: The general structure of a LOCList item.
*/
public interface LOCList {
		Vector getAllLOCs();
	    
	    LOC getLOC(String proj, String isClass);
	    
	    void addLOC(String proj, String isClass, int isLines);
	    
	    void removeLOC(String path, String isClass);
	        
	    int getAllLOCCount();
	    
	    Document getXMLContent();

}
