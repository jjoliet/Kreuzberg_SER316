package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
//import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;

//import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;


/*
File:	LogsPanel.java
Author:	Taylor Stockton
Date:	4/16/2017

Description: This class will read in input from the user via JTextField and organize labels and textfields in dialog.
*/

/*$Id: EventDialog.java,v 1.28 2005/02/19 10:06:25 rawsushi Exp $*/
public class LogsDialog extends JDialog implements WindowListener {	
    public boolean CANCELLED = false;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel logsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    JLabel lblTextTitle = new JLabel();
    JLabel lblTextNumber = new JLabel();
    JLabel lblTextDate = new JLabel();
    JLabel lblTextType = new JLabel();
    JLabel lblTextInject = new JLabel();
    JLabel lblTextReason = new JLabel();
    JLabel lblTextFixTime = new JLabel();
    JLabel lblTextFixingDef = new JLabel();
    JLabel lblTextDesc = new JLabel();
    public JTextField textFieldTitle = new JTextField();
    public JTextField textFieldNumber = new JTextField();
    public JTextField textFieldDate = new JTextField();
    public JTextField textFieldType = new JTextField();
    public JTextField textFieldInject = new JTextField();
    public JTextField textFieldReason = new JTextField();
    public JTextField textFieldFixTime = new JTextField();
    public JTextField textFieldFixingDef = new JTextField();
    public JTextField textFieldDesc = new JTextField();
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton okB = new JButton();
    JButton cancelB = new JButton();
    
    public LogsDialog(Frame frame, String title) {
        super(frame, title, true);
        try {
            jbInit();
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        super.addWindowListener(this);
    }

    void jbInit() throws Exception {
    	this.setResizable(false);
    	this.setMinimumSize(new Dimension(500, 800));
        // Build headerPanel
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Logs"));
        header.setIcon(new ImageIcon(net.sf.memoranda.ui.EventDialog.class.getResource(
            "resources/icons/event48.png")));
        headerPanel.add(header);
        
        lblTextTitle.setText(Local.getString("Log Title"));
        lblTextTitle.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextTitle, gbc);
        textFieldTitle.setMinimumSize(new Dimension(375, 24));
        textFieldTitle.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldTitle, gbc);
        
        lblTextNumber.setText(Local.getString("Defect No."));
        lblTextNumber.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextNumber, gbc);
        textFieldNumber.setMinimumSize(new Dimension(375, 24));
        textFieldNumber.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldNumber, gbc);
        
        lblTextDate.setText(Local.getString("Date."));
        lblTextDate.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextDate, gbc);
        textFieldDate.setMinimumSize(new Dimension(375, 24));
        textFieldDate.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldDate, gbc);
        
        lblTextType.setText(Local.getString("Type"));
        lblTextType.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextType, gbc);
        textFieldType.setMinimumSize(new Dimension(375, 24));
        textFieldType.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldType, gbc);
        
        lblTextInject.setText(Local.getString("Inject"));
        lblTextInject.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextInject, gbc);
        textFieldInject.setMinimumSize(new Dimension(375, 24));
        textFieldInject.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 11;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldInject, gbc);
        
        lblTextReason.setText(Local.getString("Reason"));
        lblTextReason.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 12;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextReason, gbc);
        textFieldReason.setMinimumSize(new Dimension(375, 24));
        textFieldReason.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 13;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldReason, gbc);
        
        lblTextFixTime.setText(Local.getString("Fix Time"));
        lblTextFixTime.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 14;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextFixTime, gbc);
        textFieldFixTime.setMinimumSize(new Dimension(375, 24));
        textFieldFixTime.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 15;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldFixTime, gbc);
        
        lblTextFixingDef.setText(Local.getString("Fixing Defect"));
        lblTextFixingDef.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 16;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextFixingDef, gbc);
        textFieldFixingDef.setMinimumSize(new Dimension(375, 24));
        textFieldFixingDef.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 17;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldFixingDef, gbc);
        
        lblTextDesc.setText(Local.getString("Description"));
        lblTextDesc.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 18;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextDesc, gbc);
        textFieldDesc.setMinimumSize(new Dimension(375, 24));
        textFieldDesc.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 19;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldDesc, gbc);
        
        // Build ButtonsPanel
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(okB);
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        cancelB.setText(Local.getString("Cancel"));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setMaximumSize(new Dimension(100, 26));
        buttonsPanel.add(okB);
        buttonsPanel.add(cancelB);
        
        // Finally build the Dialog
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(logsPanel, BorderLayout.SOUTH);
//        bottomPanel.add(repeatPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    void okB_actionPerformed(ActionEvent e) {
    	if(textFieldTitle.getText().isEmpty()){
    		JOptionPane.showMessageDialog(null,"Error: Please enter an identifier in the text area.",
    		"Error", JOptionPane.WARNING_MESSAGE);
    	}else{
    		this.dispose();
    	}
    }

    void cancelB_actionPerformed(ActionEvent e) {
        CANCELLED = true;
        this.dispose();
    }

    /**
    Method: windowOpened
    Inputs: "new log" button clicked
    Returns: opens log dialog

    Description: log dialog window will open when "new log" button is clicked
  */
    public void windowOpened( WindowEvent e ) {}

    /**
    Method: windowClosing
    Inputs: click of cancel button
    Returns: Closes window

    Description: Dialog window will close when "cancel" is clicked.
  */
    public void windowClosing( WindowEvent e ) {
        CANCELLED = true;
        this.dispose();
    }
    
    /*public void setEventDate(Date d) {
	    eventDate = d;
	}
	
	public Date getEventDate() {
		return eventDate;
	}*/
	
    public void windowClosed( WindowEvent e ) {}

	public void windowIconified( WindowEvent e ) {}

	public void windowDeiconified( WindowEvent e ) {}

	public void windowActivated( WindowEvent e ) {}

	public void windowDeactivated( WindowEvent e ) {}

}