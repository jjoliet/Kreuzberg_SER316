package net.sf.memoranda;

import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;

public class TimeKeeperManager {
	public static Document _doc = null;
	static Element _root = null;

	static {
		CurrentStorage.get().openEventsManager();
		if (_doc == null) {
			_root = new Element("timekeeperslist");
			_doc = new Document(_root);
		} else
			_root = _doc.getRootElement();
	}
	
	public static TimeKeeper createTimeKeeper(String proj, String phase, int hour) {
		Element el = new Element("timekeeper");
		el.addAttribute(new Attribute("id", Util.generateId()));
		el.addAttribute(new Attribute("project", proj));
		el.addAttribute(new Attribute("phase", phase));
		el.appendChild(String.valueOf(hour));
		
		return new TimeKeeperImpl(el);
	}

}
