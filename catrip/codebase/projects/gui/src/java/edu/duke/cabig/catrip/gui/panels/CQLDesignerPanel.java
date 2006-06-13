/*
 * CQLDesignerPanel.java
 *
 * Created on May 16, 2006, 3:18 PM
 */

package edu.duke.cabig.catrip.gui.panels;


import edu.duke.cabig.catrip.gui.components.CPanel;
import javax.swing.*;


/**
 *
 * @author  Sanjeev Agarwal
 */
public class CQLDesignerPanel extends CPanel {
    
    /** Creates new form CQLDesignerPanel */
    public CQLDesignerPanel() {
        initComponents();
    }

    public javax.swing.JTextArea getCqlQueryTextArea() {
        return cqlQueryTextArea;
    }

   
    public String getCqlQueryText() {
        return getCqlQueryTextArea().getText(); 
    }
    
    public void setCqlQueryText(String cqlTxt) {
        getCqlQueryTextArea().setText(cqlTxt); 
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane = new javax.swing.JScrollPane();
        cqlQueryTextArea = new javax.swing.JTextArea();

        cqlQueryTextArea.setColumns(20);
        cqlQueryTextArea.setEditable(false);
        cqlQueryTextArea.setFont(new java.awt.Font("Courier New", 1, 24));
        cqlQueryTextArea.setRows(5);
        cqlQueryTextArea.setText("Generated CQL Query Here.");
        cqlQueryTextArea.setToolTipText("Generated CQL Query");
        jScrollPane.setViewportView(cqlQueryTextArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea cqlQueryTextArea;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
    
}
