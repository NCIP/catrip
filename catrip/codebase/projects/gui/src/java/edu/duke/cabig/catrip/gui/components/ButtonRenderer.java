/*
 * ButtonRenderer.java
 *
 * Created on June 19, 2006, 10:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.components;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sanjeev Agarwal
 */
public class ButtonRenderer extends JButton implements TableCellRenderer{
    
    /** Creates a new instance of ButtonRenderer */
    public ButtonRenderer () {
    }
    
    public Component getTableCellRendererComponent (
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (JButton)value;
    }
    
}
