
package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.components.FilterRowPanel;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class SimpleSearchPanel extends CPanel {
    int filterRows = 0;
    /** Creates new form SimpleSearchPanel */
    public SimpleSearchPanel() {
        initComponents();
        init();
    }
    
    
    private void init(){
        filterRows = 0;
        for (int i = 0; i < 6; i++) {
            JPanel jp =  new JPanel();
            jp.setPreferredSize(new java.awt.Dimension(200, 40));
            filterPanel.add(jp);
        }
        filterPanel.revalidate();
        filterPanel.repaint();
        GridLayout gl = (GridLayout)filterPanel.getLayout();
        gl.setRows(7);
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        targetPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        targetObjCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        targetServiceCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        filterPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnPanel = new javax.swing.JPanel();
        targetObjBtn = new javax.swing.JButton();
        clearFilterBtn = new javax.swing.JButton();

        jLabel1.setText("Select");

        targetObjCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("from");

        targetServiceCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("where :");

        org.jdesktop.layout.GroupLayout targetPanelLayout = new org.jdesktop.layout.GroupLayout(targetPanel);
        targetPanel.setLayout(targetPanelLayout);
        targetPanelLayout.setHorizontalGroup(
            targetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(targetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(20, 20, 20)
                .add(targetObjCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(22, 22, 22)
                .add(jLabel2)
                .add(23, 23, 23)
                .add(targetServiceCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(16, 16, 16)
                .add(jLabel3)
                .addContainerGap(368, Short.MAX_VALUE))
        );
        targetPanelLayout.setVerticalGroup(
            targetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(targetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(targetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(targetObjCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
                    .add(jLabel3)
                    .add(targetServiceCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("List of filters"));
        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        filterPanel.setLayout(new java.awt.GridLayout(2, 1));

        jScrollPane1.setViewportView(filterPanel);

        jLabel4.setText("Attribute");

        jLabel5.setText("Comparision");

        jLabel6.setText("Value");

        org.jdesktop.layout.GroupLayout jpanelLayout = new org.jdesktop.layout.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                    .add(jpanelLayout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .add(209, 209, 209)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(52, 52, 52)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(90, 90, 90)))
                .addContainerGap())
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jLabel6)
                    .add(jLabel5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Restrictions"));
        targetObjBtn.setText(org.openide.util.NbBundle.getMessage(SimpleSearchPanel.class, "SimpleSearchPanel.targetObjBtn.text")); // NOI18N
        targetObjBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetObjBtnActionPerformed(evt);
            }
        });

        clearFilterBtn.setText("Clear Filters");
        clearFilterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFilterBtnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout btnPanelLayout = new org.jdesktop.layout.GroupLayout(btnPanel);
        btnPanel.setLayout(btnPanelLayout);
        btnPanelLayout.setHorizontalGroup(
            btnPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(btnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(btnPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(targetObjBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .add(clearFilterBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                .addContainerGap())
        );
        btnPanelLayout.setVerticalGroup(
            btnPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(btnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(targetObjBtn)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 244, Short.MAX_VALUE)
                .add(clearFilterBtn)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, targetPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jpanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(targetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(btnPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jpanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilterBtnActionPerformed
        filterPanel.removeAll();
        filterPanel.revalidate();
        filterPanel.repaint();
        init();
    }//GEN-LAST:event_clearFilterBtnActionPerformed
    
    private void targetObjBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetObjBtnActionPerformed
        
        filterRows++;
        
        FilterRowPanel jp =  new FilterRowPanel();
        jp.setPreferredSize(new java.awt.Dimension(200, 40));
        
        jp.getValueBox().setText(""+filterRows);
        
        
        if (filterRows < 7){
            filterPanel.remove(filterRows-1);
            filterPanel.add(jp, filterRows-1);
        } else {
            GridLayout gl = (GridLayout)filterPanel.getLayout();
            gl.setRows(filterRows);
            filterPanel.add(jp);
        }
        
        filterPanel.revalidate();
        filterPanel.repaint();

        
    }//GEN-LAST:event_targetObjBtnActionPerformed
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame jf = new JFrame();
                jf.setBounds(10,10,800,700);
                jf.getContentPane().add(new SimpleSearchPanel());
                jf.setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton clearFilterBtn;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JButton targetObjBtn;
    private javax.swing.JComboBox targetObjCombo;
    private javax.swing.JPanel targetPanel;
    private javax.swing.JComboBox targetServiceCombo;
    // End of variables declaration//GEN-END:variables
    
}
