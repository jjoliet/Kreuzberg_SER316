package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.memoranda.util.Local;

/**
Class:	LOCDialog

Description: A class that creates a JDialog window customized for adding a way of recording lines of code and implements ways of handling
events within the window.
*/
public class LOCDialog extends JDialog implements WindowListener {
	private static final long serialVersionUID = 1L;
	public boolean CANCELLED = false;
	JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel headerPanel= new JPanel();
    public JLabel header = new JLabel();
    GridBagConstraints gbc;
    JPanel LOCPanel = new JPanel();
    JLabel lblProject = new JLabel();
    JLabel lblLOC = new JLabel();
    JLabel lblClass = new JLabel();
    JTextField txtProj = new JTextField();
    JTextField txtLOC = new JTextField();
    JTextField txtClass = new JTextField();                   
    JButton cancelB = new JButton();
    JButton okB = new JButton();

    
    /**
    Method: LOCDialog
    Inputs: Frame, String
    Returns: None

    Description: Creates a Dialog window if successful, otherwise catches exceptions thrown.
  */
	public LOCDialog(Frame frame, String title) {
        super(frame, title, true);
        try {
            jdInit();
            Dimension frmSize = App.getFrame().getSize();
            Point loc = App.getFrame().getLocation();
            this.setLocation((frmSize.width - this.getSize().width) / 2 + loc.x, (frmSize.height - this.getSize().height) / 2 + loc.y);
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
        super.addWindowListener(this);
    }
	
	/**
	  Method:jdInit
	  Inputs: None
	  Returns: None

	  Description: Attempts to create the visual aspects of the Dialog window. Throws Exception if unsuccessful.
	*/
	public void jdInit() throws Exception{
		this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //HeaderPanel
        headerPanel.setBackground(Color.WHITE);
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new java.awt.Color(0, 0, 124));
        header.setText("Lines of Code");

        //Layout for the headerPanel.
        GroupLayout hPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(hPanelLayout);
        hPanelLayout.setHorizontalGroup(
            hPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(hPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(header)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hPanelLayout.setVerticalGroup(
            hPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, hPanelLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(header)
                .addGap(16, 16, 16))
        );
        
        //Labels + TextField implementations
        lblLOC.setText("Lines of Code");
        lblLOC.setMaximumSize(new Dimension(95, 24));
        lblLOC.setMinimumSize(new Dimension(95, 24));
        lblLOC.setPreferredSize(new Dimension(95, 24));

        lblClass.setText("Class");
        lblClass.setMaximumSize(new Dimension(95, 24));
        lblClass.setMinimumSize(new Dimension(95, 24));
        lblClass.setPreferredSize(new Dimension(95, 24));

        lblProject.setText("Project");
        lblProject.setMaximumSize(new Dimension(95, 24));
        lblProject.setMinimumSize(new Dimension(95, 24));
        lblProject.setPreferredSize(new Dimension(95, 24));

//        txtProj.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                txtProjActionPerformed(evt);
//            }
//        });

//        txtLOC.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                txtLOCActionPerformed(evt);
//            }
//        });
//        
//        txtClass.addActionListener(new ActionListener(){
//        	public void actionPerformed(ActionEvent ev) {
//        		txtClassActionPerformed(ev);
//        	}
//        });

        //Cancel + Okay Buttons
        cancelB.setText("Cancel");
        cancelB.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ev) {
        		cancelBActionPerformed(ev);
        	}
        });

        okB.setText("Ok");
        okB.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ev) {
        		okBActionPerformed(ev);
        	}
        });

        javax.swing.GroupLayout bottomPanelLayout = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                        .addComponent(lblLOC, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                        .addGap(253, 253, 253))
                    .addComponent(txtClass)
                    .addComponent(txtProj)
                    .addComponent(txtLOC)
                    .addGroup(bottomPanelLayout.createSequentialGroup()
                        .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClass, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelB)))
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLOC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLOC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelB)
                    .addComponent(okB))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        
        pack();
    }                      

//    private void txtProjActionPerformed(ActionEvent evt) {                                            
//        
//    }                                           
//
//    private void txtLOCActionPerformed(ActionEvent evt) {                                            
//        
//    }  
//    
//    private void txtClassActionPerformed(ActionEvent ev){
//    	
//    }
    
    private void cancelBActionPerformed(ActionEvent ev){
    	CANCELLED = true;
        this.dispose();
    }
    
    private void okBActionPerformed(ActionEvent ev){
    	if(txtProj.getText().isEmpty() || txtLOC.getText().isEmpty()||txtClass.getText().isEmpty() || isNotInt(txtLOC.getText())){
    		JOptionPane.showMessageDialog(null,"Error: Please enter a Project name, class name, and a numerical value for lines of code.",
    		"Error", JOptionPane.WARNING_MESSAGE);
    	}else{
    		this.dispose();
    	}
    }

    private boolean isNotInt(String st){
    	try{
    		Integer.parseInt(st);    		
    	}
    	catch(NumberFormatException ex){
    		return true;
    	}
    	return false;
    }
	/**
    Method: WindowClosing
    Inputs: WindowEvent
    Returns: None

    Description: This happens when the window is closed through the "x" in the top right corner.
    automatically casting the boolean Cancelled to true, so nothing saves, then disposes of the frame.
  */
	public void windowClosing(WindowEvent arg0) {
		CANCELLED = true;
        this.dispose();
	}

	/**
	 * The rest of these are unimplemented, but had to be used for WindowListener.
	 */
	public void windowDeactivated(WindowEvent arg0) {
	
	}

	public void windowDeiconified(WindowEvent arg0) {
		
	}

	public void windowIconified(WindowEvent arg0) {
		
	}

	public void windowOpened(WindowEvent arg0) {
		
	}
	
	public void windowActivated(WindowEvent arg0) {
		
	}

	public void windowClosed(WindowEvent arg0) {
		
	}

}
