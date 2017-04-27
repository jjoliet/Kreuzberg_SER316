package net.sf.memoranda;

import java.util.Vector;

import nu.xom.Document;

/**
Interface:	TimeKeeperList

Description: The general structure of a TimeKeeperList item.
*/
public interface TimeKeeperList {
		Vector getAllTimeKeepers();
	    
	    TimeKeeper getTimeKeeper(String isProj, String isPhase);
	    
	    void addTimeKeeper(String isProj, String isPhase, int isHours);
	    
	    void removeTimeKeeper(String isProj, String isPhase);
	        
	    int getAllTimeKeeperCount();
	    
	    Document getXMLContent();

}
