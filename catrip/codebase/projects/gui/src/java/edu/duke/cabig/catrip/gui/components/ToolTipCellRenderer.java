/*
 * ToolTipCellRenderer.java
 *
 * Created on June 30, 2006, 1:31 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ToolTipCellRenderer extends DefaultTableCellRenderer{
    
    /** Creates a new instance of ToolTipCellRenderer */
    public ToolTipCellRenderer() {
    }
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,column) ; 
        
        if(com instanceof JLabel){
            ((JLabel)com).setToolTipText(value.toString());
            if (column ==3){
            ((JLabel)com).setToolTipText(formatPOC(value.toString()));
            }
        }
        return com;
    }
    
    
    
    private String formatPOC(String str){
//        System.out.println("### "+str);
       String[] strr = str.split(":");
        //  univ, name , mail, role.
       String html = "<html>"+strr[0]+"<br><pre>"+
               "Point of Contact:" +"<br>"+
               "Name:   " + strr[1]+"<br>"+
               "E-Mail: " + strr[2]+"<br>"+
               "Role:   " + strr[3]+"</pre></html>";    
       return html;
    }
    
    
    
}
