/**
 * EventsTable.java
 * Created on 09.03.2003, 9:52:02 Alex
 * Package: net.sf.memoranda.ui
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import net.sf.memoranda.Event;
import net.sf.memoranda.EventsManager;
import net.sf.memoranda.LogsImpl;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.date.DateListener;
import net.sf.memoranda.util.Local;
/**
 *
 */
/*$Id: LogsTable.java,4/13/17 $*/
public class LogsTable extends JTable {

    public static final int EVENT = 100;
    public static final int EVENT_ID = 101;

    Vector<LogsImpl> logs = new Vector();
    /**
     * Constructor for EventsTable.
     */
    public LogsTable() {
        super();
        setModel(new EventsTableModel());
        initTable(CurrentDate.get());
        this.setShowGrid(false);
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                //updateUI();
                initTable(d);
            }
        });
    }
    
    public void addLogs(LogsImpl logsImpl)
    {
    	logs.add(logsImpl);
    	updateUI();
    }
    
    public LogsImpl removeLogs(int index){
    	LogsImpl removed = logs.remove(index);
    	updateUI();
    	return removed;
    }

    public void initTable(CalendarDate d) {
        //events = (Vector)EventsManager.getEventsForDate(d);
       // getColumnModel().getColumn(0).setPreferredWidth(60);//only needed to change columns width
        //getColumnModel().getColumn(0).setMaxWidth(60);
	clearSelection();
        updateUI();
    }

    public void refresh() {
        initTable(CurrentDate.get());
    }

     public TableCellRenderer getCellRenderer(int row, int column) {
        return new javax.swing.table.DefaultTableCellRenderer() {

            public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
                Component comp;
                comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                LogsImpl ev = (LogsImpl)getModel().getValueAt(row, EVENT);
                comp.setForeground(java.awt.Color.gray);
                /*if (ev.isRepeatable())
                    comp.setFont(comp.getFont().deriveFont(Font.ITALIC));*/
                /*if (CurrentDate.get().after(CalendarDate.today())) {
                  comp.setForeground(java.awt.Color.black);
                }                
                else if (CurrentDate.get().equals(CalendarDate.today())) {
                  if (ev.getTime().after(new Date())) {
                    comp.setForeground(java.awt.Color.black);
                    //comp.setFont(new java.awt.Font("Dialog", 1, 12));
                    comp.setFont(comp.getFont().deriveFont(Font.BOLD));
                  }
                }*/
                return comp;
            }
        };

    }

    class EventsTableModel extends AbstractTableModel {
    	
    	//this sets title for columns
        String[] columnNames = {
            Local.getString("Log Title"),
                Local.getString("Defect No."),
                	Local.getString("Date"),
                		Local.getString("Type"),
                			Local.getString("Inject"),
                				Local.getString("Reason"),
                					Local.getString("Fix Time"),
                						Local.getString("Fixing Defect"),
                							Local.getString("Description")
        };

        EventsTableModel() {
            super();
        }

        public int getColumnCount() {
            return columnNames.length; //lists all columns from top ^
        }

        public int getRowCount() {
			int i;
			try {
				i = logs.size();
			}
			catch(NullPointerException e) {
				i = 1;
			}
			return i;
        }

        public Object getValueAt(int row, int col) {
           LogsImpl ev = logs.get(row);
           if (col == 0)
                return ev.get_textTitle();
           else if (col == 1)
                return ev.get_textNumber();
           else if (col == 2)
                return ev.get_textDate();
           else if (col == 3)
        	   	return ev.get_textType();
           else if (col == 4)
        	   	return ev.get_textInject();
           else if (col == 5)
        	   	return ev.get_textReason();
           else if (col == 6)
        	   	return ev.get_textFixTime();
           else if (col == 7)
        	   	return ev.get_textFixingDef();
           else if (col == 8)
        	   	return ev.get_textDesc();
           else return ev;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }
    }
}
