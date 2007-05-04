

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.CDEComboboxBeanComparator;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.query.DistinctValues;
import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import edu.duke.cabig.catrip.gui.simplegui.FilterGroup;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import org.apache.commons.logging.Log;
import edu.duke.cabig.catrip.gui.util.Logger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;


/**
 *
 * @author  Sanjeev Agarwal
 */
public class FilterRowPanel extends javax.swing.JPanel {
    
    private CDEComboboxBean currentFilter = null;
    
    // sanjeev: for grouping similar Target objects... String key = cBean.getCDEName() + "_" +aBean.getCDEName()+"-"+cBean.getServiceName();
    private Hashtable uniqueFilterMap = new Hashtable();
    
    private SimpleSearchPanel containerPanel;
    
    // Define Logger..
    static Log log = Logger.getDefaultLogger();
    
//    private long filterId; // unique id of the filter instance.. to manage the AND/OR groups..
    private FilterGroup parentGroup;  // parent group of this filter..  need this when you delete the filter itself..
    
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
        
//        filterId = System.currentTimeMillis();
        fillPredicateCombo();
        getCdeCombo().setRenderer(new CDEComboBoxRenderer());
//        fillCdeCombo1();
        
        
        
        // set an action listner on the editor component of the editable valueCombobox so to listen to the keyTyped events.
        valueComboBox.getEditor().getEditorComponent().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                valueComboBoxTextBoxKeyTyped(evt);
            }
        });
        
        
        
    }
    
    
    
    private void valueComboBoxTextBoxKeyTyped(java.awt.event.KeyEvent evt) {
        
        String typedText = ((JTextField)valueComboBox.getEditor().getEditorComponent()).getText() ;
        valueComboBox.setSelectedItem(typedText);
        
        CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
        cdeBean.getAttributeBean().setAttributeValue(typedText.trim());
//        currentFilter = cdeBean;
        SimpleGuiRegistry.setSimpleGuiChanged(true);
        log.info(" Filter value changed. Filter: "+cdeBean.getClassBean().getFullyQualifiedName()+" | Attribute :"+cdeBean.getAttributeBean().getAttributeName()+" | Value: "+typedText);
    }
    
    
    public void setCDEComboboxBean(CDEComboboxBean cdeBean){
        currentFilter = cdeBean;
//        getCdeCombo().addItem(cdeBean);
//        getCdeCombo().setSelectedIndex(0);
    }
    
    
    public javax.swing.JComboBox getCdeCombo() {
        return cdeCombo;
    }
    
    public javax.swing.JComboBox getPredicateCombo() {
        return predicateCombo;
    }
    
    
    public boolean isEmpty(){
        boolean  empty = true;
        CDEComboboxBean cdeBean = getCDEComboboxBean();
        empty = cdeBean.getAttributeBean().isNull();
        return empty;
    }
    
    public boolean isLocal(){
        boolean  local = true;
        CDEComboboxBean cdeBean = getCDEComboboxBean();
        local = cdeBean.getGraphObject().isLocal();
        return local;
    }
    
    
    
    public CDEComboboxBean getCDEComboboxBean(){
        if (currentFilter == null){
            currentFilter =  (CDEComboboxBean)getCdeCombo().getSelectedItem();
        }
        
        return currentFilter;
    }
    
    private CDEComboboxBean getCurrentFilter(){
        return getCDEComboboxBean();
    }
    
    public AttributeBean getAttributeBean(){
        return getCDEComboboxBean().getAttributeBean();
    }
    
    public ClassBean getClassBean(){
        return getCDEComboboxBean().getClassBean();
    }
    
    public GraphObject getGraphObject(){
        return getCDEComboboxBean().getGraphObject();
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
        predicateCombo = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();
        delFilterBtn = new javax.swing.JButton();
        distinctValueBtn = new javax.swing.JButton();
        valueComboBox = new edu.duke.cabig.catrip.gui.components.SteppedComboBox();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/a11y/a11yBundle"); // NOI18N
        getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.panel.name")); // NOI18N
        getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.panel.description")); // NOI18N
        getAccessibleContext().setAccessibleParent(this);
        cdeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cdeComboItemStateChanged(evt);
            }
        });

        cdeCombo.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.cdeCombo.name")); // NOI18N
        cdeCombo.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.cdeCombo.description")); // NOI18N

        predicateCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                predicateComboItemStateChanged(evt);
            }
        });

        predicateCombo.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.predicateCombo.name")); // NOI18N
        predicateCombo.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.predicateCombo.description")); // NOI18N

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

        delFilterBtn.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.delFilterBtn.name")); // NOI18N
        delFilterBtn.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.delFilterBtn.description")); // NOI18N

        distinctValueBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/find.gif")));
        distinctValueBtn.setText(org.openide.util.NbBundle.getMessage(FilterRowPanel.class, "FilterRowPanel.distinctValueBtn.text")); // NOI18N
        distinctValueBtn.setToolTipText(org.openide.util.NbBundle.getMessage(FilterRowPanel.class, "FilterRowPanel.distinctValueBtn.toolTipText")); // NOI18N
        distinctValueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distinctValueBtnActionPerformed(evt);
            }
        });

        distinctValueBtn.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.distinctValueBtn.name")); // NOI18N
        distinctValueBtn.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.distinctValueBtn.description")); // NOI18N

        valueComboBox.setEditable(true);
        valueComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                valueComboBoxItemStateChanged(evt);
            }
        });

        valueComboBox.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.valueComboBox.name")); // NOI18N
        valueComboBox.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.FilterRowPanel.valueComboBox.description")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(delFilterBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cdeCombo, 0, 303, Short.MAX_VALUE)
                .add(17, 17, 17)
                .add(predicateCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(15, 15, 15)
                .add(valueComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(distinctValueBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(delFilterBtn)
                .add(predicateCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(cdeCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(distinctValueBtn)
                .add(valueComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(new java.awt.Component[] {cdeCombo, predicateCombo}, org.jdesktop.layout.GroupLayout.VERTICAL);

    }// </editor-fold>//GEN-END:initComponents
    
    private void valueComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_valueComboBoxItemStateChanged
//System.out.println("XXXXXX  :"+valueComboBox.getSelectedItem());
        
// capture this also... this is fired after enter is pressed or another item is selected..
//        if (!valueTextBox.getText().trim().equalsIgnoreCase("")){
        boolean itIsSelf = valueComboBox.isPopupVisible();
        
        if (valueComboBox.isShowing() && itIsSelf){
            CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
            String value = valueComboBox.getSelectedItem().toString().trim();
            cdeBean.getAttributeBean().setAttributeValue(value);
//        currentFilter = cdeBean;
            SimpleGuiRegistry.setSimpleGuiChanged(true);
            log.info(" Filter value changed. Filter: "+cdeBean.getClassBean().getFullyQualifiedName()+" | Attribute :"+cdeBean.getAttributeBean().getAttributeName()+" | Value: "+value);
        }
        
    }//GEN-LAST:event_valueComboBoxItemStateChanged
    
    private void distinctValueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distinctValueBtnActionPerformed
        
        // get the set of distinct values from backend. call the backend here.. and cache the valueSet for that class..
        // alternatively you can bind the FullyQualifiedClassName vs distinct values with registry also..
        
        String serviceUrl = getClassBean().getServiceUrl();
        String className = getClassBean().getFullyQualifiedName();
        String attributeName = getAttributeBean().getAttributeName();
        log.info(" Getting Distinct Values for: "+serviceUrl +" -- "+ className+" -- "+ attributeName);
        System.out.println("Getting Distinct Values for  "+serviceUrl +" -- "+ className+" -- "+ attributeName);
        
        Set valueSet = null;
        
        try {
            valueSet= DistinctValues.getDistinctValues(serviceUrl, className, attributeName);
        } catch (Exception e){
            e.printStackTrace();
        }
        
        if (valueSet !=null){
            log.info(" Result count for Distinct Value Search: "+valueSet.size());
            // clean the combo box.
            if (!(valueComboBox.getComponentCount() == 0)){
                valueComboBox.removeAllItems();
            }
            
            Object[] values = valueSet.toArray( new Object[ valueSet.size() ] );
            Arrays.sort(values);
            for (int i = 0; i < values.length; i++) {
                // check if the value is date type
                String value = values[i].toString();
                String dateFormatExp = "\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d[:]\\d\\d[.]\\d\\d\\d[-]\\d\\d[:]\\d\\d";
                boolean b = false;
                if (value!=null && !value.equals("")){
                    b = Pattern.matches(dateFormatExp, value);
                }
                try{
                    if (b){
                        Date javaDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).parse(value);
                        value = new SimpleDateFormat("MM-dd-yyyy").format(javaDate);
                    }
                } catch (java.text.ParseException pe){
                    pe.printStackTrace();
                    // eat the exception here..
                }
                valueComboBox.addItem(value);
            }
            valueComboBoxTextBoxKeyTyped(null); //  this is to fire the event so that the object is filled with the first value in the result combo box.
        }
        
    }//GEN-LAST:event_distinctValueBtnActionPerformed
    
    private void delFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delFilterBtnActionPerformed
        
        
        if (currentFilter != null){ // sanjeev: that means a filter was already set on this row...
            log.info(" Deleting the filter : "+getCdeCombo().getSelectedItem());
            currentFilter.remove();
        } else {
            log.info(" Removing a blank filter row "+getCdeCombo().getSelectedItem());
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
        containerPanel.removeFilter(this);
    }//GEN-LAST:event_delFilterBtnActionPerformed
    
    private void cdeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cdeComboItemStateChanged
// sanjeev: clean filter from previous CDEComboboxBean and reset predicate and value fields..
        if (currentFilter != null){ // sanjeev: that means a filter was already set on this row...
            log.info(" Current filter changed: "+getCdeCombo().getSelectedItem());
            currentFilter.remove();
            getPredicateCombo().setSelectedIndex(0);
            valueComboBox.removeAllItems();
            
            // this will not be necessary as you are cloning the target object bean now..
            SimpleGuiRegistry.getTargetGraphObject().getClassBean().removeAllUniqueAssociations();
            HashMap allBeans = SimpleGuiRegistry.getCurrentClassBeanMap();//getBeanMap();
            
            Iterator itt = (allBeans.values()).iterator();
            while(itt.hasNext()) {
                ClassBean cBean = (ClassBean) itt.next();
                cBean.removeAllUniqueAssociations();
            }
            // this will not be necessary as you are cloning the target object bean now..
            currentFilter = null;
            SimpleGuiRegistry.setSimpleGuiChanged(true);
        }
        
        
    }//GEN-LAST:event_cdeComboItemStateChanged
    
    public String getFilterTextValue(){
        CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
        String filterValue = cdeBean.toString()+"  "+cdeBean.getAttributeBean().getPredicate()+"  "+cdeBean.getAttributeBean().getAttributeValue();
        return filterValue;
    }
    
    public String toString(){
        return getFilterTextValue();
    }
    public String getToolTipText(){
        return toString();
    }
    
    // of no use now...
    public void setDistinctValue(String value){
//        valueTextBox.setText(value);
//        valueTextBoxKeyReleased(null);
    }
    
    private void predicateComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_predicateComboItemStateChanged
        
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//        } else
        log.info(" Predicate value changed, new Predicate: "+getPredicateCombo().getSelectedItem().toString());
        
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
            cdeBean.getAttributeBean().setPredicate(getPredicateCombo().getSelectedItem().toString());
        }
        
    }//GEN-LAST:event_predicateComboItemStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cdeCombo;
    private javax.swing.JButton delFilterBtn;
    private javax.swing.JButton distinctValueBtn;
    private edu.duke.cabig.catrip.gui.components.SteppedComboBox predicateCombo;
    private edu.duke.cabig.catrip.gui.components.SteppedComboBox valueComboBox;
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
    
    
    
    
//    public long getFilterId() {
//        return filterId;
//    }
    
    public FilterGroup getParentGroup() {
        return parentGroup;
    }
    
    public void setParentGroup(FilterGroup parentGroup) {
        this.parentGroup = parentGroup;
    }
    
    public boolean hasParentGroup( ) {
        return (getParentGroup() == null)?false:true ;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}



// this is to show the Service_name + CDE name for the filter combo.. where the CDE has same name.. like Participant.
class CDEComboBoxRenderer extends BasicComboBoxRenderer {
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof CDEComboboxBean){
            CDEComboboxBean cdeBean = (CDEComboboxBean)value;
            boolean foreignFilter = false;
            foreignFilter = !cdeBean.getGraphObject().isLocal();
            
            String toolTip = "";
            if (foreignFilter){
                toolTip = cdeBean.getClassBean().getServiceName() + " -- " +  cdeBean.toString() + " : A Filter from Foreign Service.";
            } else {
                toolTip = cdeBean.getClassBean().getServiceName() + " -- " +  cdeBean.toString();
            }
            
            list.setToolTipText(toolTip);
        }
        return super.getListCellRendererComponent( list,  value, index,  isSelected,  cellHasFocus);
    }
}







/*
private void valueTextBoxKeyReleased(java.awt.event.KeyEvent evt) {
//        if (!valueTextBox.getText().trim().equalsIgnoreCase("")){
        CDEComboboxBean cdeBean = (CDEComboboxBean)getCdeCombo().getSelectedItem();
        cdeBean.getAttributeBean().setAttributeValue(valueTextBox.getText().trim());
//        currentFilter = cdeBean;
        SimpleGuiRegistry.setSimpleGuiChanged(true);
//        }
    }
 */