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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




import net.sf.memoranda.util.Local;


/**
Class:	TimeKeeperDialog

Description: A class that creates a JDialog window for adding a way of recording time spent in phases of PSP
*/
public class TimeKeeperDialog extends JDialog implements WindowListener {
	private static final long serialVersionUID = 2L;
    public boolean CANCELLED = false;
    JPanel topPanel = new JPanel(new BorderLayout());
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public JLabel header = new JLabel();
    JPanel logsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    JLabel lblTextProject = new JLabel();
    JLabel lblTextPSPPhase = new JLabel();
    JLabel lblTextHours = new JLabel();
    public JTextField textFieldProject = new JTextField();
    public JTextField textFieldPSPPhase = new JTextField();
    public JTextField textFieldHours = new JTextField();
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    JButton okB = new JButton();
    JButton cancelB = new JButton();
    
    public TimeKeeperDialog(Frame frame, String title) {
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
        //header panel
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Time Keeper"));
        header.setIcon(new ImageIcon(net.sf.memoranda.ui.EventDialog.class.getResource(
            "resources/icons/event48.png")));
        headerPanel.add(header);
        
        lblTextProject.setText(Local.getString("Project"));
        lblTextProject.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextProject, gbc);
        textFieldProject.setMinimumSize(new Dimension(375, 24));
        textFieldProject.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldProject, gbc);
        
        lblTextPSPPhase.setText(Local.getString("PSP Phase"));
        lblTextPSPPhase.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextPSPPhase, gbc);
        textFieldPSPPhase.setMinimumSize(new Dimension(375, 24));
        textFieldPSPPhase.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldPSPPhase, gbc);
        
        lblTextHours.setText(Local.getString("Hours Spent"));
        lblTextHours.setMinimumSize(new Dimension(120, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        logsPanel.add(lblTextHours, gbc);
        textFieldHours.setMinimumSize(new Dimension(375, 24));
        textFieldHours.setPreferredSize(new Dimension(375, 24));
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 6;
        gbc.insets = new Insets(5, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        logsPanel.add(textFieldHours, gbc);
        
 
        
        // OK and cancel buttons
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
        
        //Build Window
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(logsPanel, BorderLayout.SOUTH);
        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }

    void okB_actionPerformed(ActionEvent e) {
    	if(textFieldProject.getText().isEmpty()){
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
    Inputs: WindowEvent
    Returns: Opens Window

    Description: When new Time Keeper button is clicked window will open.
  */
    public void windowOpened( WindowEvent e ) {}

    /**
    Method: windowClosing
    Inputs: WindowEvent
    Returns: Closes Window

    Description: Dialog window closes when "x" in top right corner is clicked.
  */
    public void windowClosing( WindowEvent e ) {
        CANCELLED = true;
        this.dispose();
    }
    

    public void windowClosed( WindowEvent e ) {
    	
    }

	public void windowIconified( WindowEvent e ) {
		
	}

	public void windowDeiconified( WindowEvent e ) {
		
	}

	public void windowActivated( WindowEvent e ) {
		
	}

	public void windowDeactivated( WindowEvent e ) {
		
	}

}