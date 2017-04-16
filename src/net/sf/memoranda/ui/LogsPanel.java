package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.memoranda.EventsManager;
import net.sf.memoranda.EventsScheduler;
import net.sf.memoranda.History;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.date.DateListener;
import net.sf.memoranda.util.Configuration;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

/*$Id: LogsPanel.java, 4/13/17 $*/
public class LogsPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JButton historyBackB = new JButton();
    JToolBar eventsToolBar = new JToolBar();
    JButton historyForwardB = new JButton();
    JButton newLogB = new JButton();
    JButton editLogB = new JButton();
    JButton removeLogB = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    LogsTable logsTable = new LogsTable();
    JPopupMenu eventPPMenu = new JPopupMenu();
    JMenuItem ppEditEvent = new JMenuItem();
    JMenuItem ppRemoveEvent = new JMenuItem();
    JMenuItem ppNewEvent = new JMenuItem();
    DailyItemsPanel parentPanel = null;

    public LogsPanel(DailyItemsPanel _parentPanel) {
        try {
            parentPanel = _parentPanel;
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    void jbInit() throws Exception {
        eventsToolBar.setFloatable(false);

        historyBackB.setAction(History.historyBackAction);
        historyBackB.setFocusable(false);
        historyBackB.setBorderPainted(false);
        historyBackB.setToolTipText(Local.getString("History back"));
        historyBackB.setRequestFocusEnabled(false);
        historyBackB.setPreferredSize(new Dimension(24, 24));
        historyBackB.setMinimumSize(new Dimension(24, 24));
        historyBackB.setMaximumSize(new Dimension(24, 24));
        historyBackB.setText("");

        historyForwardB.setAction(History.historyForwardAction);
        historyForwardB.setBorderPainted(false);
        historyForwardB.setFocusable(false);
        historyForwardB.setPreferredSize(new Dimension(24, 24));
        historyForwardB.setRequestFocusEnabled(false);
        historyForwardB.setToolTipText(Local.getString("History forward"));
        historyForwardB.setMinimumSize(new Dimension(24, 24));
        historyForwardB.setMaximumSize(new Dimension(24, 24));
        historyForwardB.setText("");
        
        //sets icon for new log
        newLogB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_new.png")));
        newLogB.setEnabled(true);
        newLogB.setMaximumSize(new Dimension(24, 24));
        newLogB.setMinimumSize(new Dimension(24, 24));
        newLogB.setToolTipText(Local.getString("New log"));
        newLogB.setRequestFocusEnabled(false);
        newLogB.setPreferredSize(new Dimension(24, 24));
        newLogB.setFocusable(false);
        //brings up new log dialog, new dialog window will be created
        newLogB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newEventB_actionPerformed(e);
            }
        });
        newLogB.setBorderPainted(false);

        editLogB.setBorderPainted(false);
        editLogB.setFocusable(false);
        //edit log button, new dialog window to be created
        editLogB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editEventB_actionPerformed(e);
            }
        });
        
        //edit log icon
        editLogB.setPreferredSize(new Dimension(24, 24));
        editLogB.setRequestFocusEnabled(false);
        editLogB.setToolTipText(Local.getString("Edit log"));
        editLogB.setMinimumSize(new Dimension(24, 24));
        editLogB.setMaximumSize(new Dimension(24, 24));
        editLogB.setEnabled(true);
        editLogB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_edit.png")));

        removeLogB.setBorderPainted(false);
        removeLogB.setFocusable(false);
        //remove log button, new dialog window to be created
        removeLogB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeEventB_actionPerformed(e);
            }
        });
        //remove log button icon
        removeLogB.setPreferredSize(new Dimension(24, 24));
        removeLogB.setRequestFocusEnabled(false);
        removeLogB.setToolTipText(Local.getString("Remove log"));
        removeLogB.setMinimumSize(new Dimension(24, 24));
        removeLogB.setMaximumSize(new Dimension(24, 24));
        removeLogB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_remove.png")));

        this.setLayout(borderLayout1);
        scrollPane.getViewport().setBackground(Color.white);
        logsTable.setMaximumSize(new Dimension(32767, 32767));
        logsTable.setRowHeight(24);
        eventPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
        ppEditEvent.setFont(new java.awt.Font("Dialog", 1, 11));
        ppEditEvent.setText(Local.getString("Edit log") + "...");
        ppEditEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppEditEvent_actionPerformed(e);
            }
        });
        ppEditEvent.setEnabled(false);
        ppEditEvent.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_edit.png")));
        ppRemoveEvent.setFont(new java.awt.Font("Dialog", 1, 11));
        ppRemoveEvent.setText(Local.getString("Remove Log"));
        ppRemoveEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveEvent_actionPerformed(e);
            }
        });
        ppRemoveEvent.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_remove.png")));
        ppRemoveEvent.setEnabled(false);
        ppNewEvent.setFont(new java.awt.Font("Dialog", 1, 11));
        ppNewEvent.setText(Local.getString("New log") + "...");
        ppNewEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewEvent_actionPerformed(e);
            }
        });
        ppNewEvent.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_new.png")));
        scrollPane.getViewport().add(logsTable, null);
        this.add(scrollPane, BorderLayout.CENTER);
        eventsToolBar.add(historyBackB, null);
        eventsToolBar.add(historyForwardB, null);
        eventsToolBar.addSeparator(new Dimension(8, 24));

        eventsToolBar.add(newLogB, null);
        eventsToolBar.add(removeLogB, null);
        eventsToolBar.addSeparator(new Dimension(8, 24));
        eventsToolBar.add(editLogB, null);

        this.add(eventsToolBar, BorderLayout.NORTH);

        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        logsTable.addMouseListener(ppListener);

        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                logsTable.initTable(d);     
                boolean enbl = d.after(CalendarDate.today()) || d.equals(CalendarDate.today());
                newLogB.setEnabled(enbl);           
                ppNewEvent.setEnabled(enbl);
                editLogB.setEnabled(false);
                ppEditEvent.setEnabled(false);
                removeLogB.setEnabled(false);
                ppRemoveEvent.setEnabled(false);
            }
        });

        logsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = logsTable.getSelectedRow() > -1;
                editLogB.setEnabled(enbl);
                ppEditEvent.setEnabled(enbl);
                removeLogB.setEnabled(enbl);
                ppRemoveEvent.setEnabled(enbl);
            }
        });
        editLogB.setEnabled(false);
        removeLogB.setEnabled(false);
        eventPPMenu.add(ppEditEvent);
        eventPPMenu.addSeparator();
        eventPPMenu.add(ppNewEvent);
        eventPPMenu.add(ppRemoveEvent);
		
		// remove logs using the DEL key
		logsTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(logsTable.getSelectedRows().length>0 
					&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemoveEvent_actionPerformed(null);
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		});
    }

    void editEventB_actionPerformed(ActionEvent e) {
        LogsDialog dlg = new LogsDialog(App.getFrame(), Local.getString("Log"));
        net.sf.memoranda.Event ev =
            (net.sf.memoranda.Event) logsTable.getModel().getValueAt(
                logsTable.getSelectedRow(),
                LogsTable.EVENT);
           
        dlg.textFieldTitle.setText(ev.getText());

        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
      
        String text = dlg.textFieldTitle.getText();
        
	saveEvents();
    }

    void newEventB_actionPerformed(ActionEvent e) {
        Calendar cdate = CurrentDate.get().getCalendar();
        // round down to hour
        cdate.set(Calendar.MINUTE,0);  
        Util.debug("Default time is " + cdate);
        
    	newEventB_actionPerformed(e, null, cdate.getTime(), cdate.getTime());
    }
    
    void newEventB_actionPerformed(ActionEvent e, String tasktext, Date startDate, Date endDate) {
    	LogsDialog dlg = new LogsDialog(App.getFrame(), Local.getString("New Log"));
    	Dimension frmSize = App.getFrame().getSize();
    	Point loc = App.getFrame().getLocation();
    	if (tasktext != null) {
    		dlg.textFieldTitle.setText(tasktext);
    	}

    	dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
		dlg.setVisible(true);
    	if (dlg.CANCELLED)
    		return;
    	
    	//storing input user puts into text field for defects - tstockt
    	String textTitle = dlg.textFieldTitle.getText();
    	String textNumber = dlg.textFieldNumber.getText();
    	String textDate = dlg.textFieldDate.getText();
    	String textType = dlg.textFieldType.getText();
    	String textInject = dlg.textFieldInject.getText();
    	String textReason = dlg.textFieldReason.getText();
    	String textFixTime = dlg.textFieldFixTime.getText();
    	String textFixingDef = dlg.textFieldFixingDef.getText();
    	String textDesc = dlg.textFieldDesc.getText();
    }

    private void saveEvents() {
	CurrentStorage.get().storeEventsManager();
        logsTable.refresh();
        EventsScheduler.init();
        parentPanel.calendar.jnCalendar.updateUI();
        parentPanel.updateIndicators();
    }

    void removeEventB_actionPerformed(ActionEvent e) {
		String msg;
		net.sf.memoranda.Event ev;

		if(logsTable.getSelectedRows().length > 1) 
			msg = Local.getString("Remove") + " " + logsTable.getSelectedRows().length 
				+ " " + Local.getString("events") + "\n" + Local.getString("Are you sure?");
		else {
			ev = (net.sf.memoranda.Event) logsTable.getModel().getValueAt(
                logsTable.getSelectedRow(),
                LogsTable.EVENT);
			msg = Local.getString("Remove log") + "\n'" 
				+ ev.getText() + "'\n" + Local.getString("Are you sure?");
		}

        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove event"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION) return;

        for(int i=0; i< logsTable.getSelectedRows().length;i++) {
			ev = (net.sf.memoranda.Event) logsTable.getModel().getValueAt(
                  logsTable.getSelectedRows()[i], LogsTable.EVENT);
        EventsManager.removeEvent(ev);
		}
        logsTable.getSelectionModel().clearSelection();
        saveEvents();  
  }

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if ((e.getClickCount() == 2) && (logsTable.getSelectedRow() > -1))
                editEventB_actionPerformed(null);
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                eventPPMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

    }
    void ppEditEvent_actionPerformed(ActionEvent e) {
        editEventB_actionPerformed(e);
    }
    void ppRemoveEvent_actionPerformed(ActionEvent e) {
        removeEventB_actionPerformed(e);
    }
    void ppNewEvent_actionPerformed(ActionEvent e) {
        newEventB_actionPerformed(e);
    }
}