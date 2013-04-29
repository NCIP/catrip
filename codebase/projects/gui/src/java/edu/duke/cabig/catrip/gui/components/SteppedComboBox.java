/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.duke.cabig.catrip.gui.components;

/**
 *
 * @author Sanjeev Agarwal
 */

import java.awt.Dimension;
import javax.swing.JComboBox;

/**
 *
 * @author Sanjeev Agarwal
 */
public class SteppedComboBox extends JComboBox {
    
    /** Creates a new instance of SteppedComboBox */
    public SteppedComboBox() {
    }
    
    private boolean layingOut = false;

    public void doLayout() {
        try {
            layingOut = true;
            super.doLayout();
        }
        finally {
            layingOut = false;
            
        }
    }

    public Dimension getSize() {
        
        Dimension sz = super.getSize(); 
        if (!layingOut) {
            sz.width = Math.max(sz.width, getPreferredSize().width);
        }
        return sz;
    }
    
    
}
