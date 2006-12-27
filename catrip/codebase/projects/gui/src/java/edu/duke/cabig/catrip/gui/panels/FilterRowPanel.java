

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.CDEComboboxBeanComparator;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class FilterRowPanel extends javax.swing.JPanel {
    
    private CDEComboboxBean currentFilter = null;
    
    // sanjeev: for grouping similar Target objects... String key = cBean.getCDEName() + "_" +aBean.getCDEName()+"-"+cBean.getServiceName();
    private Hashtable uniqueFilterMap = new Hashtable();
    
    private SimpleSearchPanel containerPanel;
    
    /** Creates new form FilterRowPanel */
    public FilterRowPanel() {
        initComponents();
        init();
    }
    
    public FilterRowPanel(SimpleSearchPanel ssp) {
        initComponents();
        init();
        containerPanel = ssp;
    }
    
    
    private void init(){
        
        fillPredicateCombo();
        
//        fillCdeCombo1();
        
    }
    
    
    public JTextField getValueBox(){
        return valueTextBox;
    }
    
    public javax.swing.JComboBox getCdeCombo() {
        return cdeCombo;
    }
    
    public javax.swing.JComboBox getPredicateCombo() {
        return predicateCombo;
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        cdeCombo = new javax.swing.JComboBox();
        cdeCombo = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();
        valueTextBox = new javax.swing.JTextField();
        predicateCombo = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();
        delFilterBtn = new javax.swing.JButton();

        cdeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cdeComboItemStateChanged(evt);
            }
        });

        valueTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valueTextBoxKeyReleased(evt);
            }
        });

        predicateCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                predicateComboItemStateChanged(evt);
            }
        });

        delFilterBtn.setFont(new java.awt.Font("Tahoma", 1, 11));
        delFilterBtn.setForeground(new java.awt.Color(255, 0, 0));
        delFilterBtn.setText(org.openide.util.NbBundle.getMessage(FilterRowPanel.class, "FilterRowPanel.delFilterBtn.text")); // NOI18N
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
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(delFilterBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cdeCombo, 0, 369, Short.MAX_VALUE)
                .add(17, 17, 17)
                .add(predicateCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(14, 14, 14)
                .add(valueTextBox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(valueTextBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(delFilterBtn)
                .add(predicateCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(cdeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(new java.awt.Component[] {cdeCombo, predicateCombo, valueTextBox}, org.jdesktop.layout.GroupLayout.VERTICAL);

    }// </editor-fold>//GEN-END:initComponents
    
    private void delFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delFilterBtnActionPerformed
        if (currentFilter != null){ // sanjeev: that means a filter was already set on this row...
            currentFilter.remove();
        }
        
        SimpleGuiRegistry.getTargetGraphObject().getClassBean().removeAllUniqueAssociations();
        HashMap allBeans = SimpleGuiRegistry.getCurrentClassBeanMap();//getBeanMap();
        
        Iterator itt = (allBeans.values()).iterator();
        while(itt.hasNext()) {
            ClassBean cBean = (ClassBean) itt.next();
            cBean.removeAllUniqueAssociations();
        }
        SimpleGuiRegistry.setSimpleGuiChanged(true);
        
        this.removeAll();
//        repaint();
        containerPanel.removeFilter(this);
    }//GEN-LAST:event_delFilterBtnActionPerformed
    
    private void cdeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cdeComboItemStateChanged
// sanjeev: clean filter from previous CDEComboboxBean and reset predicate and value fields..
        if (currentFilter != null){ // sanjeev: that means a filter was already set on this row...
            currentFilter.remove();
            getPredicateCombo().setSelectedIndex(0);
            getValueBox().setText("");
            
            SimpleGuiRegistry.getTargetGraphObject().getClassBean().removeAllUniqueAssociations();
            HashMap allBeans = SimpleGuiRegistry.getCurrentClassBeanMap();//getBeanMap();
            
            Iterator itt = (allBeans.values()).iterator();
            while(itt.hasNext()) {
                ClassBean cBean = (ClassBean) itt.next();
                cBean.removeAllUniqueAssociations();
            }
            SimpleGuiRegistry.setSimpleGuiChanged(true);
        }
        
    }//GEN-LAST:event_cdeComboItemStateChanged
    
    public String getFilterTextValue (){
        CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
        String filterValue = cdeBean.toString()+"  "+cdeBean.getAttributeBean().getPredicate()+"  "+cdeBean.getAttributeBean().getAttributeValue();
        return filterValue;
    }
    
    private void valueTextBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valueTextBoxKeyReleased
//        if (!valueTextBox.getText().trim().equalsIgnoreCase("")){
        CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
        cdeBean.getAttributeBean().setAttributeValue(valueTextBox.getText().trim());
        currentFilter = cdeBean;
//        }
    }//GEN-LAST:event_valueTextBoxKeyReleased
    
    private void predicateComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_predicateComboItemStateChanged
        
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//        } else
        
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
            cdeBean.getAttributeBean().setPredicate(getPredicateCombo().getSelectedItem().toString());
        }
        
    }//GEN-LAST:event_predicateComboItemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cdeCombo;
    private javax.swing.JButton delFilterBtn;
    private edu.duke.cabig.catrip.gui.components.SteppedComboBox predicateCombo;
    private javax.swing.JTextField valueTextBox;
    // End of variables declaration//GEN-END:variables
    
    
    
    private void fillPredicateCombo(){
        getPredicateCombo().addItem("LIKE");
        getPredicateCombo().addItem("EQUAL_TO");
        getPredicateCombo().addItem("NOT_EQUAL_TO");
        getPredicateCombo().addItem("LESS_THAN");
        getPredicateCombo().addItem("LESS_THAN_EQUAL_TO");
        getPredicateCombo().addItem("GREATER_THAN");
        getPredicateCombo().addItem("GREATER_THAN_EQUAL_TO");
        getPredicateCombo().addItem("IS_NULL");
        getPredicateCombo().addItem("IS_NOT_NULL");
        
        getPredicateCombo().setSelectedIndex(0);
    }
    
    
    
    public void fillCdeCombo2(List<GraphObject> objs) {
        ArrayList<CDEComboboxBean> attributeList = new ArrayList<CDEComboboxBean>(100); // for sorting.
        
        for (int i=0;i<objs.size();i++) {
            GraphObject gObj = objs.get(i);
            gObj = gObj.clone(); // used to clone the filters for multiple different values..
            
            if (gObj.isDisplayable()){
                ClassBean cBean = gObj.getClassBean();
                ArrayList attributes = cBean.getAttributes();
                
                for (int j = 0; j < attributes.size(); j++) {
                    AttributeBean aBean = (AttributeBean)attributes.get(j);
                    CDEComboboxBean cdeBean = new CDEComboboxBean();
                    cdeBean.setGraphObject(gObj); 
//                    cdeBean.setClassBean(cBean); // set the new cloned ClassBean instead of graph object..
                    cdeBean.setAttributeBean(aBean);
                    attributeList.add(cdeBean);
//                    getCdeCombo().addItem(cdeBean);
                }
            }
        }
        
        // sanjeev: add them in sorted order.. add all the filters in an array list than use collections to sort than add tham to combo.
        Collections.sort(attributeList, new CDEComboboxBeanComparator());
        for (int i = 0; i < attributeList.size(); i++) {
//            CDEComboboxBean cdeBean = (CDEComboboxBean)attributeList.get(i);
//            System.out.println(cdeBean.getClassBean().getServiceName()+": Class name"+cdeBean.getClassBean().getFullyQualifiedName()+":\n \t CDE name in Displayed Combobox:"+cdeBean.toString());
//            System.out.println("\t Attribute name:"+cdeBean.getAttributeBean().getAttributeName()+": Actual Attribute CDE name:"+cdeBean.getAttributeBean().getCDEName()+"\n");
            getCdeCombo().addItem(attributeList.get(i));
        }
        
        
        
        
    }
    
    
    
    // not in use...
//    public void fillCdeCombo(List<ClassBean> objs) {
//        for (int i=0;i<objs.size();i++) {
//            ClassBean cBean = objs.get(i);
//            ArrayList attributes = cBean.getAttributes();
//            for (int j = 0; j < attributes.size(); j++) {
//                AttributeBean aBean = (AttributeBean)attributes.get(j);
//                CDEComboboxBean cdeBean = new CDEComboboxBean();
//                cdeBean.setClassBean(cBean);cdeBean.setAttributeBean(aBean);
//                getCdeCombo().addItem(cdeBean);
//            }
//        }
//    }
    
    
    // String key = cBean.getCDEName() + "_" +aBean.getCDEName()+"-"+cBean.getServiceName();

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}




