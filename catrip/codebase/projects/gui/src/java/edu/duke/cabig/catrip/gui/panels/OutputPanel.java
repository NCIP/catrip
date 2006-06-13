/*
 * OutputPanel.java
 *
 * Created on May 16, 2006, 2:22 PM
 */

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.components.CPanel;
import javax.swing.JTextArea;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class OutputPanel extends CPanel {
    
    /** Creates new form OutputPanel */
    public OutputPanel() {
        initComponents();
    }

    public javax.swing.JTextArea getOutPutArea() {
        return outPutArea;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        outPutArea = new javax.swing.JTextArea();

        setLayout(new java.awt.GridLayout(1, 0));

        outPutArea.setColumns(20);
        outPutArea.setRows(5);
        jScrollPane1.setViewportView(outPutArea);

        add(jScrollPane1);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea outPutArea;
    // End of variables declaration//GEN-END:variables
    
}
