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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.MenuElement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.memoranda.EventsManager;
import net.sf.memoranda.EventsScheduler;
import net.sf.memoranda.History;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.date.DateListener;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;
/*
 * Added by AK 4/25/17
 * US-4
 */
public class PhasePanel extends JPanel {

	BorderLayout borderLayout = new BorderLayout();
	JToolBar phaseToolBar = new JToolBar();
	JButton historyBackB = new JButton();
	JButton historyForwardB = new JButton();
	JButton newPhaseB = new JButton();
	JButton removePhaseB = new JButton();
	JButton editPhaseB = new JButton();
	JScrollPane scrollPane = new JScrollPane();
	PhaseTable phaseTable = new PhaseTable();
	JPopupMenu phasePPMenu = new JPopupMenu();
	JMenuItem ppEditPhase = new JMenuItem();
	JMenuItem ppRemovePhase = new JMenuItem();
	JMenuItem ppNewPhase = new JMenuItem();
	DailyItemsPanel parentPanel = null;
	
	public PhasePanel(DailyItemsPanel _parentPanel) {
		try {
			parentPanel = _parentPanel;
			jbInit();
		} catch (Exception e) {
			new ExceptionDialog(e);
		}
	}
	
	void jbInit() throws Exception {
		phaseToolBar.setFloatable(false);
		
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
		
		newPhaseB.setIcon(
				new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_new.png")));
		newPhaseB.setEnabled(true);
		newPhaseB.setMaximumSize(new Dimension(24, 24));
		newPhaseB.setMinimumSize(new Dimension(24, 24));
		newPhaseB.setToolTipText(Local.getString("New Phase"));
		newPhaseB.setRequestFocusEnabled(false);
		newPhaseB.setPreferredSize(new Dimension(24, 24));
		newPhaseB.setFocusable(false);
		newPhaseB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPhaseB_actionPerformed(e);
			}
		});
		newPhaseB.setBorderPainted(false);
		
		editPhaseB.setBorderPainted(false);
        editPhaseB.setFocusable(false);
        //edit Phase button, new dialog window to be created
        editPhaseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editPhaseB_actionPerformed(e);
            }
        });
        
        //edit Phase icon
        editPhaseB.setPreferredSize(new Dimension(24, 24));
        editPhaseB.setRequestFocusEnabled(false);
        editPhaseB.setToolTipText(Local.getString("Edit Phase"));
        editPhaseB.setMinimumSize(new Dimension(24, 24));
        editPhaseB.setMaximumSize(new Dimension(24, 24));
        editPhaseB.setEnabled(true);
        editPhaseB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_edit.png")));
		
        removePhaseB.setBorderPainted(false);
        removePhaseB.setFocusable(false);
        //remove Phase button, new dialog window to be created
        removePhaseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removePhaseB_actionPerformed(e);
            }
        });
        //remove Phase button icon
        removePhaseB.setPreferredSize(new Dimension(24, 24));
        removePhaseB.setRequestFocusEnabled(false);
        removePhaseB.setToolTipText(Local.getString("Remove Phase"));
        removePhaseB.setMinimumSize(new Dimension(24, 24));
        removePhaseB.setMaximumSize(new Dimension(24, 24));
        removePhaseB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_remove.png")));
        
        this.setLayout(borderLayout);
        
        phasePPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
        ppEditPhase.setFont(new java.awt.Font("Dialog", 1, 11));
        ppEditPhase.setText(Local.getString("Edit Phase") + "...");
        ppEditPhase.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ppEditPhase_actionPerformed(e);
        	}
        });
        ppEditPhase.setEnabled(false);
        ppEditPhase.setIcon(
        		new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_edit.png")));
        
        ppRemovePhase.setFont(new java.awt.Font("Dialog", 1, 11));
        ppRemovePhase.setText(Local.getString("Remove Phase") + "...");
        ppRemovePhase.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ppRemovePhase_actionPerformed(e);
        	}
        });
        ppRemovePhase.setEnabled(false);
        ppRemovePhase.setIcon(
        		new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_remove.png")));
        
        ppNewPhase.setFont(new java.awt.Font("Dialog", 1, 11));
        ppNewPhase.setText(Local.getString("New Phase") + "...");
        ppNewPhase.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ppNewPhase_actionPerformed(e);
        	}
        });
        ppNewPhase.setEnabled(false);
        ppNewPhase.setIcon(
        		new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_new.png")));
				
		phaseToolBar.add(historyBackB, null);
		phaseToolBar.add(historyForwardB, null);
		phaseToolBar.addSeparator(new Dimension(8, 24));
		phaseToolBar.add(newPhaseB, null);
		phaseToolBar.add(removePhaseB, null);
		phaseToolBar.addSeparator(new Dimension(8, 24));
		phaseToolBar.add(editPhaseB, null);
		
		this.add(phaseToolBar, BorderLayout.NORTH);
		
		scrollPane.getViewport().setBackground(Color.white);
		
		phaseTable.setMaximumSize(new Dimension(32767, 32767));
		phaseTable.setRowHeight(24);
		scrollPane.getViewport().add(phaseTable, null);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
		PopupListener ppListener = new PopupListener();
		scrollPane.addMouseListener(ppListener);
		phaseTable.addMouseListener(ppListener);
		
		CurrentDate.addDateListener(new DateListener() {
			public void dateChange(CalendarDate d) {
				phaseTable.initTable(d);
				boolean enbl = d.after(CalendarDate.today()) || d.equals(CalendarDate.today());
				newPhaseB.setEnabled(enbl);
				ppNewPhase.setEnabled(enbl);
				editPhaseB.setEnabled(false);
				ppEditPhase.setEnabled(false);
				removePhaseB.setEnabled(false);
				ppRemovePhase.setEnabled(false);
			}
		});
		
		phaseTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = phaseTable.getSelectedRow() > -1;
                editPhaseB.setEnabled(enbl);
                ppEditPhase.setEnabled(enbl);
                removePhaseB.setEnabled(enbl);
                ppRemovePhase.setEnabled(enbl);
            }
        });
		
		editPhaseB.setEnabled(false);
		removePhaseB.setEnabled(false);
		phasePPMenu.add(ppEditPhase);
		phasePPMenu.addSeparator();
		phasePPMenu.add(ppNewPhase);
		phasePPMenu.add(ppRemovePhase);
		
		// remove phase using the DEL key
		phaseTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(phaseTable.getSelectedRows().length > 0
						&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemovePhase_actionPerformed(null);
			}
			public void keyReleased(KeyEvent e) {
				//
			}
			public void keyTyped(KeyEvent e) {
				//
			}
		});
		
	}
	
	void editPhaseB_actionPerformed(ActionEvent e) {
		TimeKeeperDialog dlg = new TimeKeeperDialog(App.getFrame(), Local.getString("Phase"));
		net.sf.memoranda.Event ev =
				(net.sf.memoranda.Event) phaseTable.getModel().getValueAt(
						phaseTable.getSelectedRow(),
						PhaseTable.EVENT);
		//
		dlg.textFieldProject.setText(ev.getText());
		
		Dimension frmSize = App.getFrame().getSize();
		Point loc = App.getFrame().getLocation();
		dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        
        saveEvents();
	}
	
	void newPhaseB_actionPerformed(ActionEvent e) {
        Calendar cdate = CurrentDate.get().getCalendar();
        // round down to hour
        cdate.set(Calendar.MINUTE,0);  
        Util.debug("Default time is " + cdate);
        
    	newPhaseB_actionPerformed(e, null, cdate.getTime(), cdate.getTime());
    }
	
	void newPhaseB_actionPerformed(ActionEvent e, String tasktext, Date startDate, Date endDate) {
    	TimeKeeperDialog dlg = new TimeKeeperDialog(App.getFrame(), Local.getString("New Phase"));
    	Dimension frmSize = App.getFrame().getSize();
    	Point loc = App.getFrame().getLocation();
    	if (tasktext != null) {
    		dlg.textFieldProject.setText(tasktext);
    	}

    	dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
		dlg.setVisible(true);
    	if (dlg.CANCELLED)
    		return;
	}
	
	private void saveEvents() {
		CurrentStorage.get().storeEventsManager();
		phaseTable.refresh();
		EventsScheduler.init();
		parentPanel.calendar.jnCalendar.updateUI();
		parentPanel.updateIndicators();
	}
	
	void removePhaseB_actionPerformed(ActionEvent e) {
		String msg;
		net.sf.memoranda.Event ev;

		if(phaseTable.getSelectedRows().length > 1) 
			msg = Local.getString("Remove") + " " + phaseTable.getSelectedRows().length 
				+ " " + Local.getString("phases") + "\n" + Local.getString("Are you sure?");
		else {
			ev = (net.sf.memoranda.Event) phaseTable.getModel().getValueAt(
                phaseTable.getSelectedRow(),
                PhaseTable.EVENT);
			msg = Local.getString("Remove phase") + "\n'" 
				+ ev.getText() + "'\n" + Local.getString("Are you sure?");
		}

        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove phase"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION) return;

        for(int i=0; i< phaseTable.getSelectedRows().length;i++) {
			ev = (net.sf.memoranda.Event) phaseTable.getModel().getValueAt(
                  phaseTable.getSelectedRows()[i], PhaseTable.EVENT);
        EventsManager.removeEvent(ev);
		}
        phaseTable.getSelectionModel().clearSelection();
        saveEvents();  
	}
	
	class PopupListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			if ((e.getClickCount() == 2) && (phaseTable.getSelectedRow() > -1))
				editPhaseB_actionPerformed(null);
		}
		
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}
		
		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				phasePPMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
	void ppEditPhase_actionPerformed(ActionEvent e) {
		editPhaseB_actionPerformed(e);
	}
	
	void ppRemovePhase_actionPerformed(ActionEvent e) {
		removePhaseB_actionPerformed(e);
	}
	
	void ppNewPhase_actionPerformed(ActionEvent e) {
		newPhaseB_actionPerformed(e);
	}
}
