
package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.components.CJFrame;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.Service;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class SimpleSearchPanel extends CPanel {
    int filterRows = 0;
    
    ObjectGraphProcessor processor  = null;
    private boolean targetSetChanged = true;
    
    private ArrayList<FilterRowPanel> filters = new ArrayList(10);
    
    /** Creates new form SimpleSearchPanel */
    public SimpleSearchPanel() {
        
        if (GUIConstants.simpleGui){
            initBefore();
            initComponents();
            initAfter();
        }
        
    }
    
    
    private void initBefore(){
        // TODO - call these methods from an initail screen so that we have enough time to load these..
        processor = SimpleGuiRegistry.getProcessor();
        SimpleGuiRegistry.loadMetadata();
    }
    
    private void initAfter(){
        initFilters();
//        initServiceCombo();
        initTargetObjectCombo();
    }
    
    private void initFilters(){
        filterRows = 0;
        for (int i = 0; i < 4; i++) {
            JPanel jp =  new JPanel();
            jp.setPreferredSize(new java.awt.Dimension(200, 40));
            filterPanel.add(jp);
        }
        filterPanel.revalidate();
        filterPanel.repaint();
        GridLayout gl = (GridLayout)filterPanel.getLayout();
        gl.setRows(4);
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
        targetObjCombo = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();
        jLabel2 = new javax.swing.JLabel();
        targetServiceCombo = new javax.swing.JComboBox();
        targetServiceCombo = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();
        jpanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        filterPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addFilterBtn = new javax.swing.JButton();
        clearFilterBtn = new javax.swing.JButton();

        jLabel1.setText("Select");

        targetObjCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetObjComboActionPerformed(evt);
            }
        });

        jLabel2.setText("from");

        targetServiceCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetServiceComboActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout targetPanelLayout = new org.jdesktop.layout.GroupLayout(targetPanel);
        targetPanel.setLayout(targetPanelLayout);
        targetPanelLayout.setHorizontalGroup(
            targetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(targetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(16, 16, 16)
                .add(targetObjCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19)
                .add(jLabel2)
                .add(23, 23, 23)
                .add(targetServiceCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 227, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(396, Short.MAX_VALUE))
        );
        targetPanelLayout.setVerticalGroup(
            targetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(targetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(targetPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(targetObjCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2)
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
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                    .add(jpanelLayout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                        .add(84, 84, 84)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(63, 63, 63)
                        .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(204, 204, 204)))
                .addContainerGap())
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jLabel5)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );

        addFilterBtn.setText("Add Filter");
        addFilterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFilterBtnActionPerformed(evt);
            }
        });

        clearFilterBtn.setText("Clear Filters");
        clearFilterBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFilterBtnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jpanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, targetPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(addFilterBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 208, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(clearFilterBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(targetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addFilterBtn)
                    .add(clearFilterBtn))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void targetObjComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetObjComboActionPerformed
        
//        cleanClean();
        targetSetChanged = true;
        getTargetServiceCombo().removeAllItems();
        
        GraphObject selectedTargetObject = (GraphObject)getTargetObjCombo().getSelectedItem();
        Service serv = SimpleGuiRegistry.getServiceFromMap(selectedTargetObject.getServiceName().trim());
        getTargetServiceCombo().addItem(serv);
        
    }//GEN-LAST:event_targetObjComboActionPerformed
    
    private void targetServiceComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetServiceComboActionPerformed
        
//        cleanClean();
//        targetSetChanged = false;
//        getTargetObjCombo().removeAllItems();
//
//        Service serv = (Service)getTargetServiceCombo().getSelectedItem();
//        List<GraphObject> objs = processor.getTragetObjects(serv.getServiceName());
//
//        for (int i=0;i<objs.size();i++) {
//            getTargetObjCombo().addItem(objs.get(i));
//        }
//        targetSetChanged = true;
        
    }//GEN-LAST:event_targetServiceComboActionPerformed
    
    private void clearFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFilterBtnActionPerformed
        SimpleGuiRegistry.cleanRegistry();
        targetSetChanged = true;
        
        filterPanel.removeAll();
        filterPanel.revalidate();
        filterPanel.repaint();
        initFilters();
        
    }//GEN-LAST:event_clearFilterBtnActionPerformed
    
    private void addFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilterBtnActionPerformed
        
        filterRows++;
        
        FilterRowPanel jp =  new FilterRowPanel();
        jp.setPreferredSize(new java.awt.Dimension(200, 40));
        
        
        if (targetSetChanged){
            Service selectedService = (Service)getTargetServiceCombo().getSelectedItem();
            GraphObject selectedTargetObject = (GraphObject)getTargetObjCombo().getSelectedItem();
            
            List<GraphObject> objs = processor.getAssociatedObjects(selectedTargetObject.getClassName(),selectedService.getServiceName());
            List<GraphObject> forObjs = processor.getAvialbleTargetObjectsToAssociateInRemoteServices(selectedService.getServiceName());
            
            for (int i = 0; i < forObjs.size(); i++) {
                objs.add(forObjs.get(i));
            }
            objs.add(selectedTargetObject);
            targetSetChanged = false;
            SimpleGuiRegistry.setCurrentXMLObjectList(objs);
            SimpleGuiRegistry.setTargetGraphObject(selectedTargetObject);
        }
        
        
        
        jp.fillCdeCombo2(SimpleGuiRegistry.getCurrentXMLObjectList());
//        jp.fillCdeCombo(SimpleGuiRegistry.getCurrentClassBeanList());
        
        if (filterRows < 5){
            filterPanel.remove(filterRows-1);
            filterPanel.add(jp, filterRows-1);
        } else {
            GridLayout gl = (GridLayout)filterPanel.getLayout();
            gl.setRows(filterRows);
            filterPanel.add(jp);
        }
        
        SimpleGuiRegistry.addFilterToList(jp);  // create a list of filters being added currently..
        
        filterPanel.revalidate();
        filterPanel.repaint();
        
        SimpleGuiRegistry.setSimpleGuiChanged(true);
        // after adding each filter.. prepare registry for DCQL..
//        SimpleGuiRegistry.prepareForDcql();
        
    }//GEN-LAST:event_addFilterBtnActionPerformed
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CJFrame jf = new CJFrame();
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setBounds(10,10,800,400);
                jf.center();
                jf.getContentPane().add(new SimpleSearchPanel());
                jf.setVisible(true);
            }
        });
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFilterBtn;
    private javax.swing.JButton clearFilterBtn;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JComboBox targetObjCombo;
    private javax.swing.JPanel targetPanel;
    private javax.swing.JComboBox targetServiceCombo;
    // End of variables declaration//GEN-END:variables
    
    
    
    public javax.swing.JComboBox getTargetObjCombo() {
        return targetObjCombo;
    }
    
    public javax.swing.JComboBox getTargetServiceCombo() {
        return targetServiceCombo;
    }
    
    
    
    
    private void initServiceCombo(){
        
        List<Service> services = processor.getServices();
        Service service;
        for (int i=0;i<services.size();i++) {
            getTargetServiceCombo().addItem(services.get(i));
//            System.out.println(i+1 + ") " + service.getServiceName() + "   " + service.getServiceURL());
        }
        
    }
    
    private void initTargetObjectCombo(){
        targetSetChanged = true;
        List<GraphObject> objs = processor.getTargetObjects();
        
        for (int i=0;i<objs.size();i++) {
            getTargetObjCombo().addItem(objs.get(i));
        }
    }
    
    private void cleanClean(){
        clearFilterBtnActionPerformed(null);
    }
    
    
//    public void disableAll(){
//        getTargetObjCombo().setEnabled(false);
//
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
