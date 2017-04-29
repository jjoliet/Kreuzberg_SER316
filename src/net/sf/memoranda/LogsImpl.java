/**
 * EventImpl.java
 * Created on 08.03.2003, 13:20:13 Alex
 * Package: net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * 
 */
/*$Id: EventImpl.java,v 1.9 2004/10/06 16:00:11 ivanrise Exp $*/
public class LogsImpl {
    
	private String _textTitle;
	private String _textNumber;
	private String _textDate;
	private String _textType;
	private String _textInject;
	private String _textReason;
	private String _textFixTime;
	private String _textFixingDef;
	private String _textDesc;

    /**
     * Constructor for EventImpl.
     */
    public LogsImpl(String textTitle, String textNumber, String textDate, String textType, 
    				String textInject, String textReason, String textFixTime, 
    				String textFixingDef, String textDesc) {
        
    	_textTitle = textTitle;
    	_textNumber = textNumber;
    	_textDate = textDate;
    	_textType = textType;
    	_textInject = textInject;
    	_textReason = textReason;
    	_textFixTime = textFixTime;
    	_textFixingDef = textFixingDef;
    	_textDesc = textDesc;
    }

	public String get_textTitle() {
		return _textTitle;
	}

	public void set_textTitle(String _textTitle) {
		this._textTitle = _textTitle;
	}

	public String get_textNumber() {
		return _textNumber;
	}

	public void set_textNumber(String _textNumber) {
		this._textNumber = _textNumber;
	}

	public String get_textDate() {
		return _textDate;
	}

	public void set_textDate(String _textDate) {
		this._textDate = _textDate;
	}

	public String get_textType() {
		return _textType;
	}

	public void set_textType(String _textType) {
		this._textType = _textType;
	}

	public String get_textInject() {
		return _textInject;
	}

	public void set_textInject(String _textInject) {
		this._textInject = _textInject;
	}

	public String get_textReason() {
		return _textReason;
	}

	public void set_textReason(String _textReason) {
		this._textReason = _textReason;
	}

	public String get_textFixTime() {
		return _textFixTime;
	}

	public void set_textFixTime(String _textFixTime) {
		this._textFixTime = _textFixTime;
	}

	public String get_textFixingDef() {
		return _textFixingDef;
	}

	public void set_textFixingDef(String _textFixingDef) {
		this._textFixingDef = _textFixingDef;
	}

	public String get_textDesc() {
		return _textDesc;
	}

	public void set_textDesc(String _textDesc) {
		this._textDesc = _textDesc;
	}

}
