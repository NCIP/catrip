
package edu.duke.cabig.catrip.gui.components;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Subclass of the DefaultTableCellRenderer to show custom formatted Multi-line ToolTip in the JTable.
 *
 * @author Sanjeev Agarwal
 */
public class ToolTipCellRenderer extends DefaultTableCellRenderer{
    
    /** Creates a new instance of ToolTipCellRenderer */
    public ToolTipCellRenderer() {
    }
    
    /** Specific to the search services panel */
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
    
    
    /** Construct a formatted multi-line ToolTip for the Point of Contact in JTable. */
    private String formatPOC(String str){
       String[] strr = str.split(":");
       String html = "<html>"+strr[0]+"<br><pre>"+
               "Point of Contact:" +"<br>"+
               "Name:   " + strr[1]+"<br>"+
               "E-Mail: " + strr[2]+"<br>"+
               "Role:   " + strr[3]+"</pre></html>";    
       return html;
    }
    
    
    
}
