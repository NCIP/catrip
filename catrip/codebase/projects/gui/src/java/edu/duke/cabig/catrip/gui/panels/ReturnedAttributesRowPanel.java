/*
 * ReturnedAttributesRowPanel.java
 *
 * Created on January 22, 2007, 10:45 AM
 */

package edu.duke.cabig.catrip.gui.panels;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class ReturnedAttributesRowPanel extends javax.swing.JPanel {
    
    private ReturnedAttributesPanel containerPanel;
    
    
    /** Creates new form ReturnedAttributesRowPanel */
    public ReturnedAttributesRowPanel() {
        initComponents();
    }
    
     public ReturnedAttributesRowPanel(ReturnedAttributesPanel parent_) {
        initComponents();
        this.containerPanel = parent_;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        returnedAttributeCombo = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();
        delFilterBtn = new javax.swing.JButton();

        delFilterBtn.setFont(new java.awt.Font("Tahoma", 1, 11));
        delFilterBtn.setForeground(new java.awt.Color(255, 0, 0));
        delFilterBtn.setText(org.openide.util.NbBundle.getMessage(ReturnedAttributesRowPanel.class, "ReturnedAttributesRowPanel.delFilterBtn.text")); // NOI18N
        delFilterBtn.setIconTextGap(0);
        delFilterBtn.setMargin(new java.awt.Insets(1, 1, 1, 1));
        delFilterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delFilterBtnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(delFilterBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(returnedAttributeCombo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(delFilterBtn)
            .add(returnedAttributeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void delFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delFilterBtnActionPerformed
        // delete this entry from the parent's list..
        this.removeAll();
        containerPanel.removeRow(this);
    }//GEN-LAST:event_delFilterBtnActionPerformed
    
    public javax.swing.JComboBox getReturnedAttributeCombo(){
        return returnedAttributeCombo;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delFilterBtn;
    private edu.duke.cabig.catrip.gui.components.SteppedComboBox returnedAttributeCombo;
    // End of variables declaration//GEN-END:variables
    
}
