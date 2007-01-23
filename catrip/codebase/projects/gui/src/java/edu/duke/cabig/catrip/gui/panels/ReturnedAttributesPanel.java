/*
 * ReturnedAttributesPanel.java
 *
 * Created on January 20, 2007, 5:22 PM
 */

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.PreferredHeightMarginBorderBoxLayout;
import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class ReturnedAttributesPanel extends javax.swing.JPanel {
    
    private SimpleSearchPanel parentFilterPanel;
    private int numEntities = 0;
    private int numAvailableEntities = 0;
    private HashMap entries = new HashMap();
    private ArrayList keyEntries = new ArrayList();
    
    
    /** Creates new form ReturnedAttributesPanel */
    public ReturnedAttributesPanel() {
        initComponents();
        init();
    }
    
    
    /** Creates new form ReturnedAttributesPanel */
    public ReturnedAttributesPanel(SimpleSearchPanel parent) {
        initComponents();
        init();
        this.parentFilterPanel = parent;
    }
    
    
    private void init(){
        PreferredHeightMarginBorderBoxLayout layout = new PreferredHeightMarginBorderBoxLayout(returnedAttributeListPanel, PreferredHeightMarginBorderBoxLayout.Y_AXIS);
        returnedAttributeListPanel.setLayout(layout);
        
        
        
        
        
        // check the filter classes that are being set right now.. and then filter the beans.. then filter the beans based on the flag also..
        // get all the filtersPanels from simple gui.. and filter them for duplicates..
        ArrayList<FilterRowPanel> list = SimpleGuiRegistry.getFilterList();
        ArrayList<CDEComboboxBean> attributeList = new ArrayList<CDEComboboxBean>(100);
        
        for (int i = 0; i < list.size(); i++) {
            CDEComboboxBean cdeBean = list.get(i).getCDEComboboxBean();
            boolean isSelectable = list.get(i).getGraphObject().isSelectable(); // check if the class is selectable than only add it..
            if ( isSelectable && !entries.containsKey(cdeBean.getClassBean().getFullyQualifiedName())){
                
                attributeList.add(cdeBean);
                String fullClassName = cdeBean.getClassBean().getFullyQualifiedName();
                entries.put(fullClassName, cdeBean);
                keyEntries.add(fullClassName);
                
                numAvailableEntities += cdeBean.getClassBean().getAttributes().size();
                
            }
        }
        
        // get all classes in the path of the association tree..
//        SimpleGuiRegistry.prepareForDcql();
//        HashMap map = SimpleGuiRegistry.getBeanMap(); // this is the Map that contains all the classes in the association tree..
//
//        for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
//            String key = (String)iter.next();
//        }
        
        
        
        
        
        // now display the already selected returned attributes from SimpleGuiRegistry.
//        if (SimpleGuiRegistry.isReturnedAttributeListAvailable()){
//            HashMap classAttributeListMap = SimpleGuiRegistry.getClassNameReturnedAttributeMap();
//            for (Iterator iter = classAttributeListMap.keySet().iterator(); iter.hasNext();) {
//                String className = (String)iter.next();
//                List atts = (List)classAttributeListMap.get(className);
//                for (int i = 0; i < atts.size(); i++) {
//                    String attributeName = (String)atts.get(i);
//                    // now find the CDEComboboxBean from the tmpMap.. and add display it to combobox.. after creating and adding a panel..
//                }
//            }
//        }
        
        
        
        
        
        
    }
    
    
    public void removeRow(ReturnedAttributesRowPanel fp){
        
        returnedAttributeListPanel.remove(fp);
        returnedAttributeListPanel.revalidate();
        returnedAttributeListPanel.repaint();
        numEntities--;
    
    }
    
    
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        returnedAttributeListPanel = new javax.swing.JPanel();
        addReturnedAttribute = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        okBtn = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ReturnedAttributesPanel.class, "ReturnedAttributesPanel.jPanel1.border.title"))); // NOI18N
        jScrollPane1.setBorder(null);
        returnedAttributeListPanel.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setViewportView(returnedAttributeListPanel);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );

        addReturnedAttribute.setText(org.openide.util.NbBundle.getMessage(ReturnedAttributesPanel.class, "ReturnedAttributesPanel.addReturnedAttribute.text")); // NOI18N
        addReturnedAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReturnedAttributeActionPerformed(evt);
            }
        });

        cancelBtn.setText(org.openide.util.NbBundle.getMessage(ReturnedAttributesPanel.class, "ReturnedAttributesPanel.cancelBtn.text")); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        okBtn.setText(org.openide.util.NbBundle.getMessage(ReturnedAttributesPanel.class, "ReturnedAttributesPanel.okBtn.text")); // NOI18N
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(addReturnedAttribute, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 245, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 269, Short.MAX_VALUE)
                        .add(okBtn)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cancelBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addReturnedAttribute)
                    .add(cancelBtn)
                    .add(okBtn))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
// clear the current changed settings.. in this instance...
        JDialog parent = (JDialog)getRootPane().getParent();
        parent.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
        // add the returned Attribute List to the SimpleGuiRegistry.
        if (numEntities > 0){
            
            // first reset the entries in the SimpleGuiRegistry..
            SimpleGuiRegistry.setReturnedAttributeListAvailable(false);
            SimpleGuiRegistry.setClassNameReturnedAttributeMap(new HashMap());
            
            
            for (int i = 0; i < numEntities; i++) {
                ReturnedAttributesRowPanel element = (ReturnedAttributesRowPanel)returnedAttributeListPanel.getComponent(i);
                CDEComboboxBean cdeBean = (CDEComboboxBean)element.getReturnedAttributeCombo().getSelectedItem();
                SimpleGuiRegistry.addToClassNameReturnedAttributeMap(cdeBean.getClassBean().getFullyQualifiedName(), cdeBean.getAttributeBean().getAttributeName());
            }
            // signal the simple gui registry that returned attribute list has changed..
            SimpleGuiRegistry.setReturnedAttributeListAvailable(true);
        }
        JDialog parent = (JDialog)getRootPane().getParent();
        parent.dispose();
    }//GEN-LAST:event_okBtnActionPerformed
    
    private void addReturnedAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addReturnedAttributeActionPerformed
        
        if (numEntities < numAvailableEntities ){
            ReturnedAttributesRowPanel rap = new ReturnedAttributesRowPanel(this);
            rap.setPreferredSize(new java.awt.Dimension(200, 40));
            
            for (int i = 0; i < entries.size(); i++) {
                String key = (String)keyEntries.get(i);
                CDEComboboxBean cdeBean = (CDEComboboxBean) entries.get(key);
                
                ArrayList attributes = cdeBean.getClassBean().getAttributes();
                for (int j = 0; j < attributes.size(); j++) {
                    CDEComboboxBean cdeBeanTmp = new CDEComboboxBean();
                    cdeBeanTmp.setAttributeBean( ((AttributeBean)attributes.get(j)).clone() ) ;
                    cdeBeanTmp.setClassBean( ((ClassBean)cdeBean.getClassBean()).clone() ) ;
                    
                    rap.getReturnedAttributeCombo().addItem(cdeBeanTmp);
                }
                
//                if (cdeBean.getGraphObject().isSelectable()){
//                rap.getReturnedAttributeCombo().addItem(cdeBean);
//                }
            }
            
            rap.getReturnedAttributeCombo().setSelectedIndex(numEntities);
            returnedAttributeListPanel.add(rap);
            
            returnedAttributeListPanel.revalidate();
            returnedAttributeListPanel.repaint();
            
            numEntities++;
        }
        
    }//GEN-LAST:event_addReturnedAttributeActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addReturnedAttribute;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okBtn;
    private javax.swing.JPanel returnedAttributeListPanel;
    // End of variables declaration//GEN-END:variables
    
}