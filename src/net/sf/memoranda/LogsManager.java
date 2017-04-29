/**
 * EventsManager.java Created on 08.03.2003, 12:35:19 Alex Package:
 * net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net Copyright (c) 2003
 *         Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;
import java.util.Collections;


import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Util;
import nu.xom.Attribute;
//import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParentNode;

/**
 *  
 */
/*$Id: EventsManager.java,v 1.11 2004/10/06 16:00:11 ivanrise Exp $*/
public class LogsManager {
/*	public static final String NS_JNEVENTS =
		"http://www.openmechanics.org/2003/jnotes-events-file";
*/

	
	public static ArrayList<LogsImpl> allLogs = new ArrayList<LogsImpl>();
	static{
		readLogData();
	}
	
	//we need a method that allows us to add a log to our allLogs
	
	public static void addLog(LogsImpl log){
		allLogs.add(log);
	}
	
	//we need a method to remove a log from our allLogs
	
	public static void removeLog(LogsImpl log){
		allLogs.remove(log);
	}
	
	public static ArrayList<LogsImpl> getLogs(){
		return new ArrayList<LogsImpl>(allLogs);
	}
	
	//we need a method to create a storing functionality 
	
	public static void writeLogData(String name) throws IOException{
		
		String str = "";
		
		for(LogsImpl log : allLogs){
			str += log.get_textTitle();
			str += "\n";
			str += log.get_textNumber();
			str += "\n";
			str += log.get_textDate();
			str += "\n";
			str += log.get_textType();
			str += "\n";
			str += log.get_textInject();
			str += "\n";
			str += log.get_textReason();
			str += "\n";
			str += log.get_textFixTime();
			str += "\n";
			str += log.get_textFixingDef();
			str += "\n";
			str += log.get_textDesc();
			str += "\n";
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(name));
		writer.write(str);
		
		writer.close();
	}
	
	public static void readLogData(){
		
		String fileName = Util.getEnvDir() + ".logs";
		
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			try {
				while((line = bufferedReader.readLine()) != null){
					String title = line;
					String number = bufferedReader.readLine();
					String date = bufferedReader.readLine();
					String type = bufferedReader.readLine();
					String inject = bufferedReader.readLine();
					String reason = bufferedReader.readLine();
					String fixTime = bufferedReader.readLine();
					String fixingDef = bufferedReader.readLine();
					String desc = bufferedReader.readLine();
					//String none = bufferedReader.readLine();
					LogsImpl logImpl = new LogsImpl(title, number, date, type, inject, reason, fixTime,
													fixingDef, desc);
					
					LogsManager.addLog(logImpl);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

