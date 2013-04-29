/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.components;


import edu.duke.cabig.catrip.gui.wizard.MainFrame;
import javax.swing.*;

/**
 * Custom swing Panel component. Has pointer to a parent JFrame.
 *
 * @author Sanjeev Agarwal
 */
public class CPanel extends javax.swing.JPanel {
    protected  MainFrame mainFrame;
    private Object customObject;
    
    /**
     * Creates a new instance of CPanel
     */
    public CPanel() {
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public Object getCustomObject() {
        return customObject;
    }

    public void setCustomObject(Object customObject) {
        this.customObject = customObject;
    }
    
}
