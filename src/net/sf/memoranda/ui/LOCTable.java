package net.sf.memoranda.ui;

import java.awt.Component;
import java.awt.Font;

import java.util.Vector;


import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.sf.memoranda.CurrentProject;

import net.sf.memoranda.Project;
import net.sf.memoranda.ui.LOCTable.LOCTableModel;

import net.sf.memoranda.ui.table.TableSorter;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.LOC;
/**
Class:	LOCTable

Description: Creates the LOCTable that is used in LOCPanel class.
*/
public class LOCTable extends JTable{
	
	 	Vector locs;
	    TableSorter sorter = null;
	   
	    public LOCTable() {
	        super();
	        initTable();
	        sorter = new TableSorter(new LOCTableModel());
	        sorter.addMouseListenerToHeaderInTable(this);
	        setModel(sorter);
	        this.setShowGrid(false);
	        this.setFont(new Font("Dialog",0,11));
	        initColumsWidth();	        
	    }

	    void initColumsWidth() {
	        for (int i = 0; i < 3; i++) {
	            TableColumn column = getColumnModel().getColumn(i);
	            if (i == 0) {
	                column.setPreferredWidth(32767);
	            }
	            else {
	                column.setMinWidth(100);
	                column.setPreferredWidth(100);
	            }
	        }
	    }
	    /**
	     * Method: tableChanged
	     * Inputs: None
	     * Returns: None
	     * Description: Used to update the Table when the table is changed.
	     */
	    public void tableChanged() {
	        initTable();
	        sorter.tableChanged(null);
	        initColumsWidth();
	        updateUI();
	    }
	    
	    /**
	     * Method: initTable
	     * Inputs: None
	     * Returns: None
	     * Description: Used to initialize the Table when the Table is first created.
	     */
	    public void initTable() {
	        locs = CurrentProject.getLOCList().getAllLOCs();
	        clearSelection();
	        updateUI();
	    }
	    
	     public static final int LOC = 100;

	    public TableCellRenderer getCellRenderer(int row, int column) {
	        return new javax.swing.table.DefaultTableCellRenderer() {

	            public Component getTableCellRendererComponent(
	                JTable table,
	                Object value,
	                boolean isSelected,
	                boolean hasFocus,
	                int row,
	                int column) {
	                JLabel comp;

	                comp = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                LOC l = (LOC)getModel().getValueAt(row, LOC);
	                comp.setForeground(java.awt.Color.gray);
	                return comp;
	            }
	        };

	    }

	    class LOCTableModel extends AbstractTableModel {

	        String[] columnNames = {
	                Local.getString("Project"),
	                Local.getString("Class"),
	                Local.getString("Lines of Code")};

	        public String getColumnName(int i) {
	            return columnNames[i];
	        }

	        public int getColumnCount() {
	            return columnNames.length;
	        }

	        public int getRowCount() {
	        	int i;
				try {
					i = locs.size();
				}
				catch(NullPointerException e) {
					i = 1;
				}
				return i;
	        }
	        
	       
	        
	        public Object getValueAt(int row, int col) {
	        	LOC l = (LOC)locs.get(row);
	            if (col == 0)
	                return l.getProject();
	            else if (col == 1)
	                return l.getLinesClass();
	           else if (col == 2)
	                return l.getLines();
	           else return l;
	        }
	    }

}
