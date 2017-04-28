package net.sf.memoranda;

import nu.xom.Element;

public class TimeKeeperImpl {
	
	private Element _elem = null;
	
	public TimeKeeperImpl(Element el) {
		_elem = el;
	}
	
	public String getProject() {
		return _elem.getAttribute("project").getValue();
	}
	
	public String getPhase() {
		return _elem.getAttribute("phase").getValue();
	}
	
	public int getHour() {
		return new Integer(_elem.getAttribute("hour").getValue()).intValue();
	}
	
	public String getText() {
		return _elem.getValue();
	}
	
	public Element getContent() {
		return _elem;
	}
	

}
