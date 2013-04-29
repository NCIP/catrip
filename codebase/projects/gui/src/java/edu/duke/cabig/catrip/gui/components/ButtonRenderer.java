/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.components;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Button renderer for the JTable cell.
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
