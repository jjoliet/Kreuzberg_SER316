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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.LOC;
import net.sf.memoranda.Resource;
import net.sf.memoranda.util.AppList;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.MimeType;
import net.sf.memoranda.util.MimeTypesList;
import net.sf.memoranda.util.Util;
/**
Class:	LOCPanel

Description: The Panel and functionality of the buttons within that panel for the LOC tab.
*/
public class LOCPanel extends JPanel{
	BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    JButton newLOCEntry = new JButton();
    LOCTable LOCTable = new LOCTable();
    JButton removeLOC = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    JButton editLOCb = new JButton();
  JPopupMenu locPPMenu = new JPopupMenu();
  JMenuItem ppEdit = new JMenuItem();
  JMenuItem ppRemoveLOC = new JMenuItem();
  JMenuItem ppNewLOC = new JMenuItem();

  /**
   * Constructor for LOCPanel
   * @param _parentPanel
   */
    public LOCPanel(DailyItemsPanel _parentPanel){
        try {
            jbInit();
        }
        catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }
    
    void jbInit() throws Exception {
        toolBar.setFloatable(false);
        this.setLayout(borderLayout1);
        newLOCEntry.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/addresource.png")));
        newLOCEntry.setEnabled(true);
        newLOCEntry.setMaximumSize(new Dimension(24, 24));
        newLOCEntry.setMinimumSize(new Dimension(24, 24));
        newLOCEntry.setToolTipText(Local.getString("New Entry"));
        newLOCEntry.setRequestFocusEnabled(false);
        newLOCEntry.setPreferredSize(new Dimension(24, 24));
        newLOCEntry.setFocusable(false);
        newLOCEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newLOCB_actionPerformed(e);
            }
        });
        newLOCEntry.setBorderPainted(false);
        LOCTable.setMaximumSize(new Dimension(32767, 32767));
        LOCTable.setRowHeight(24);
        removeLOC.setBorderPainted(false);
        removeLOC.setFocusable(false);
        removeLOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeLOCB_actionPerformed(e);
            }
        });
        removeLOC.setPreferredSize(new Dimension(24, 24));
        removeLOC.setRequestFocusEnabled(false);
        removeLOC.setToolTipText(Local.getString("Remove Entry"));
        removeLOC.setMinimumSize(new Dimension(24, 24));
        removeLOC.setMaximumSize(new Dimension(24, 24));
        removeLOC.setIcon(
            new ImageIcon(
                net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/removeresource.png")));
        removeLOC.setEnabled(false);
        scrollPane.getViewport().setBackground(Color.white);
        toolBar.addSeparator(new Dimension(8, 24));
        toolBar.addSeparator(new Dimension(8, 24));


        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        LOCTable.addMouseListener(ppListener);

        LOCTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = (LOCTable.getRowCount() > 0) && (LOCTable.getSelectedRow() > -1);

                removeLOC.setEnabled(enbl); ppRemoveLOC.setEnabled(enbl);
                ppEdit.setEnabled(enbl);
            }
        });
        editLOCb.setBorderPainted(false);
        editLOCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editB_actionPerformed(e);
            }
        });
        editLOCb.setFocusable(false);
        editLOCb.setPreferredSize(new Dimension(24, 24));
        editLOCb.setRequestFocusEnabled(false);
        editLOCb.setToolTipText(Local.getString("Edit LOC Entry"));
        editLOCb.setMinimumSize(new Dimension(24, 24));
        editLOCb.setMaximumSize(new Dimension(24, 24));
        editLOCb.setEnabled(true);
        editLOCb.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/refreshres.png")));
        locPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
      ppEdit.setFont(new java.awt.Font("Dialog", 1, 11));
      ppEdit.setText(Local.getString("Edit LOC Entry")+"...");
      ppEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppEdit_actionPerformed(e);
            }
        });
      ppEdit.setEnabled(false);

    ppRemoveLOC.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRemoveLOC.setText(Local.getString("Remove LOC"));
    ppRemoveLOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveRes_actionPerformed(e);
            }
        });
    ppRemoveLOC.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/removeresource.png")));
    ppRemoveLOC.setEnabled(false);
    ppNewLOC.setFont(new java.awt.Font("Dialog", 1, 11));
    ppNewLOC.setText(Local.getString("New LOC")+"...");
    ppNewLOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewRes_actionPerformed(e);
            }
        });
    ppNewLOC.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/addresource.png")));
   
    toolBar.add(newLOCEntry, null);
        toolBar.add(removeLOC, null);
        toolBar.addSeparator();
        toolBar.add(editLOCb, null);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(LOCTable, null);
        this.add(toolBar, BorderLayout.NORTH);
    locPPMenu.add(ppEdit);
    locPPMenu.addSeparator();
    locPPMenu.add(ppNewLOC);
    locPPMenu.add(ppRemoveLOC);
    locPPMenu.addSeparator();

	
		// remove resources using the DEL key
		LOCTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(LOCTable.getSelectedRows().length>0 
					&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemoveRes_actionPerformed(null);
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		});
    }

    void newLOCB_actionPerformed(ActionEvent e) {
        LOCDialog dlg = new LOCDialog(App.getFrame(), Local.getString("New LOC"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        else       		
            CurrentProject.getLOCList().addLOC(dlg.txtProj.getText(), dlg.txtClass.getText(), Integer.parseInt(dlg.txtLOC.getText()));        	           
            LOCTable.tableChanged();         
     }
    

    void removeLOCB_actionPerformed(ActionEvent e) {
        int[] toRemove = LOCTable.getSelectedRows();
        String msg = "";
        if (toRemove.length == 1)
            msg =
                Local.getString("Remove the LOC Entry")
                    + "\n'"
                    + LOCTable.getModel().getValueAt(toRemove[0], 0)
                    + "'";

        else
            msg = Local.getString("Remove") + " " + toRemove.length + " " + Local.getString("entrys");
        msg +=
            "\n"
            + Local.getString("Are you sure?");
        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove LOC Entry"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        for (int i = 0; i < toRemove.length; i++) {
        		LOC remove = (LOC)LOCTable.getModel().getValueAt(toRemove[i],  LOCTable.LOC);       	
        		CurrentProject.getLOCList().removeLOC(remove.getProject(), remove.getLinesClass());
        }
        LOCTable.tableChanged();
    }



    class PopupListener extends MouseAdapter {

                public void mousePressed(MouseEvent e) {
                    maybeShowPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    maybeShowPopup(e);
                }

                private void maybeShowPopup(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        locPPMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

    }
   
    void editB_actionPerformed(ActionEvent e) {
        ppEdit_actionPerformed(e);
    }

    void ppEdit_actionPerformed(ActionEvent e) {
    LOCDialog dlg = new LOCDialog(App.getFrame(), Local.getString("Edit LOC"));
    Dimension frmSize = App.getFrame().getSize();
    Point locate = App.getFrame().getLocation();
    dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + locate.x, (frmSize.height - dlg.getSize().height) / 2 + locate.y);
    
    net.sf.memoranda.LOC loc =
    (net.sf.memoranda.LOC) LOCTable.getModel().getValueAt(
    		LOCTable.getSelectedRow(),LOCTable.LOC);
    dlg.txtProj.setText(loc.getProject());
    dlg.txtClass.setText(loc.getLinesClass());
    dlg.txtLOC.setText(String.valueOf(loc.getLines()));
    dlg.setVisible(true);
    if (dlg.CANCELLED)
        return;
    else   		
    		CurrentProject.getLOCList().removeLOC(loc.getProject(), loc.getLinesClass());
        	CurrentProject.getLOCList().addLOC(dlg.txtProj.getText(), dlg.txtClass.getText(), Integer.parseInt(dlg.txtLOC.getText()));   		
       
        LOCTable.tableChanged();    
    
  }
  void ppRemoveRes_actionPerformed(ActionEvent e) {
    removeLOCB_actionPerformed(e);
  }
  void ppNewRes_actionPerformed(ActionEvent e) {
    newLOCB_actionPerformed(e);
  }
  
}
