package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import net.sf.memoranda.util.Local;
/*
 * Added by AK 4/25/17
 * US-4
 */
public class PhasePanel extends JPanel {

	BorderLayout borderLayout = new BorderLayout();
	JToolBar phaseToolBar = new JToolBar();
	JButton newPhaseB = new JButton();
	JButton phaseRemoveB = new JButton();
	JScrollPane scrollPane = new JScrollPane();
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
		
		newPhaseB.setIcon(
				new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/event_new.png")));
		newPhaseB.setEnabled(true);
		newPhaseB.setMaximumSize(new Dimension(24, 24));
		newPhaseB.setMinimumSize(new Dimension(24, 24));
		newPhaseB.setToolTipText(Local.getString("New Phase"));
		newPhaseB.setRequestFocusEnabled(false);
		newPhaseB.setPreferredSize(new Dimension(24, 24));
		newPhaseB.setFocusable(false);
		//addActionListener
		newPhaseB.setBorderPainted(false);
		
		this.setLayout(borderLayout);
		
		phaseToolBar.add(newPhaseB, null);
		
		this.add(phaseToolBar, BorderLayout.NORTH);
		
		scrollPane.getViewport().setBackground(Color.white);
		
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
}
