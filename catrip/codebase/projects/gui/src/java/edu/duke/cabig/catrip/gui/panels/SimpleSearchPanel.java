
package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.components.CJDialog;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.components.PreferredHeightMarginBorderBoxLayout;
import edu.duke.cabig.catrip.gui.simplegui.FilterGroup;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.Service;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author  Sanjeev Agarwal
 */
public class SimpleSearchPanel extends CPanel {
    int filterRows = 0;
    
    ObjectGraphProcessor processor  = null;
    private boolean targetSetChanged = true;
    
    private ArrayList<FilterRowPanel> filters = new ArrayList(10);
    
    // sanjeev: for grouping similar Target objects... String key = GraphObject.toString()+""+GraphObject.getServiceName();
    private Hashtable targetObjectServiceMap = new Hashtable();
    
    /** Creates new form SimpleSearchPanel */
    public SimpleSearchPanel() {
        
        // TODO - comment these (except initComponents();) if you want to change the gui...
        
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
//        initFilters(); // commented for box layout..
//        initServiceCombo();
        initTargetObjectCombo();
        
        // set the custom Box layout here.
        // filterPanel
        PreferredHeightMarginBorderBoxLayout layout = new PreferredHeightMarginBorderBoxLayout(filterPanel, PreferredHeightMarginBorderBoxLayout.Y_AXIS);
        filterPanel.setLayout(layout);
        
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
    
    public void removeFilter(FilterRowPanel fp){
        
        
//        if (filterRows < 5){
//            filterRows--;
//            JPanel jp =  new JPanel();
//            jp.setPreferredSize(new java.awt.Dimension(200, 40));
//            filterPanel.add(jp);
//        } else {
//            filterRows--;
//            GridLayout gl = (GridLayout)filterPanel.getLayout();
//            gl.setRows(filterRows);
//        }
        
        // check if this filter is in group or not.. if it is in a group.. then reArrange.. otherwise normal old call...
        if (fp.getParentGroup() == null){
            filterPanel.remove(fp);
            filterPanel.revalidate();
            filterPanel.repaint();
            SimpleGuiRegistry.getFilterList().remove(fp);
        } else {
            filterPanel.revalidate();
            filterPanel.repaint();
            // TODO - sanju AND/OR
//            removeAndReArrange( fp );
        }
        
        
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
        filterPanelScrollPane = new javax.swing.JScrollPane();
        filterPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addFilterBtn = new javax.swing.JButton();
        clearFilterBtn = new javax.swing.JButton();
        addGroupBtn = new javax.swing.JButton();

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
        filterPanelScrollPane.setBorder(null);
        filterPanelScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        filterPanel.setLayout(new java.awt.GridLayout(2, 1));

        filterPanelScrollPane.setViewportView(filterPanel);

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
                    .add(filterPanelScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
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
                .add(filterPanelScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
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

        addGroupBtn.setText(org.openide.util.NbBundle.getMessage(SimpleSearchPanel.class, "SimpleSearchPanel.addGroupBtn.text")); // NOI18N
        addGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupBtnActionPerformed(evt);
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
                        .add(clearFilterBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 213, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(addGroupBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(targetPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jpanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addFilterBtn)
                    .add(clearFilterBtn)
                    .add(addGroupBtn))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void addGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGroupBtnActionPerformed
        
        FilterGroupPanel fgp = new FilterGroupPanel(this);
        
        CJDialog jd = new CJDialog(getMainFrame(), "Create Group between Filters or Groups");
        jd.add(fgp);
        jd.setBounds(10,10,750, 320);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
        
    }//GEN-LAST:event_addGroupBtnActionPerformed
    
    private void targetObjComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetObjComboActionPerformed
        
//        cleanClean();
        targetSetChanged = true;
        getTargetServiceCombo().removeAllItems();
        
        GraphObject selectedTargetObject = (GraphObject)getTargetObjCombo().getSelectedItem();
        String name = selectedTargetObject.toString().trim();
        
        // sanjeev: when the combos are showing.. than
        Enumeration e = getTargetObjectServiceMap().keys();
        while (e.hasMoreElements()){
            String mixedKey = (String)e.nextElement();
//                System.out.println("### name:"+name+": key:"+mixedKey);
            if (mixedKey.startsWith(name)){
//                    System.out.println("### key:"+mixedKey);
                // sanjeev: add the services to the service combo..
                GraphObject obj = (GraphObject)getTargetObjectServiceMap().get(mixedKey);
                getTargetServiceCombo().addItem(SimpleGuiRegistry.getServiceFromMap(obj.getServiceName().trim()));
            }
        }
        
        
        
//        GraphObject selectedTargetObject = (GraphObject)getTargetObjCombo().getSelectedItem();
//        Service serv = SimpleGuiRegistry.getServiceFromMap(selectedTargetObject.getServiceName().trim());
//        getTargetServiceCombo().addItem(serv);
        
        //------------------------
        if(getTargetServiceCombo().isShowing() ){
            
            cleanPanel();//clearFilterBtnActionPerformed(null);
            Service selectedService = (Service)getTargetServiceCombo().getSelectedItem();
            
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
        //-------------------------
        
        
    }//GEN-LAST:event_targetObjComboActionPerformed
    
    private void targetServiceComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetServiceComboActionPerformed
        
        boolean itIsSelf = getTargetServiceCombo().isPopupVisible();
        
        if(getTargetServiceCombo().isShowing() && itIsSelf){
            
            cleanPanel();//clearFilterBtnActionPerformed(null);
            
            Service selectedService = (Service)getTargetServiceCombo().getSelectedItem();
            String key = ((GraphObject)getTargetObjCombo().getSelectedItem()).toString()+"_"+selectedService.getServiceName();
            
            GraphObject selectedTargetObject = (GraphObject)getTargetObjectServiceMap().get(key);//(GraphObject)getTargetObjCombo().getSelectedItem();
            
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
        
        cleanPanel();
        
        Service selectedService = (Service)getTargetServiceCombo().getSelectedItem();
        String key = ((GraphObject)getTargetObjCombo().getSelectedItem()).toString()+"_"+selectedService.getServiceName();
        
        GraphObject selectedTargetObject = (GraphObject)getTargetObjectServiceMap().get(key);//(GraphObject)getTargetObjCombo().getSelectedItem();
        
        List<GraphObject> objs = processor.getAssociatedObjects(selectedTargetObject.getClassName(),selectedService.getServiceName());
        List<GraphObject> forObjs = processor.getAvialbleTargetObjectsToAssociateInRemoteServices(selectedService.getServiceName());
        
        for (int i = 0; i < forObjs.size(); i++) {
            objs.add(forObjs.get(i));
        }
        objs.add(selectedTargetObject);
        targetSetChanged = false;
        SimpleGuiRegistry.setCurrentXMLObjectList(objs);
        SimpleGuiRegistry.setTargetGraphObject(selectedTargetObject);
    }//GEN-LAST:event_clearFilterBtnActionPerformed
    
    private void cleanPanel(){
        SimpleGuiRegistry.cleanRegistry();
        targetSetChanged = true;
        
        filterPanel.removeAll();
        filterPanel.revalidate();
        filterPanel.repaint();
        //initFilters(); // and or
    }
    
    private void addFilterBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFilterBtnActionPerformed
        
        filterRows++;
        
        FilterRowPanel jp =  new FilterRowPanel(this);
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
        
        
        
        
        // and or
//        if (filterRows < 5){
//            filterPanel.remove(filterRows-1);
//            filterPanel.add(jp, filterRows-1);
//        } else {
//            GridLayout gl = (GridLayout)filterPanel.getLayout();
//            gl.setRows(filterRows);
//            filterPanel.add(jp);
//        }
        
        filterPanel.add(jp);
        
        
        
        
        SimpleGuiRegistry.addFilterToList(jp);  // sanjeev: create a list of filters being added currently..
        
        filterPanel.revalidate();
        filterPanel.repaint();
        
        SimpleGuiRegistry.setSimpleGuiChanged(true);
        // sanjeev: after adding each filter.. prepare registry for DCQL..
//        SimpleGuiRegistry.prepareForDcql();
        
    }//GEN-LAST:event_addFilterBtnActionPerformed
    
//
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                CJFrame jf = new CJFrame();
//                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                jf.setBounds(10,10,800,400);
//                jf.center();
//                jf.getContentPane().add(new SimpleSearchPanel());
//                jf.setVisible(true);
//            }
//        });
//    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addFilterBtn;
    private javax.swing.JButton addGroupBtn;
    private javax.swing.JButton clearFilterBtn;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JScrollPane filterPanelScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    
    public javax.swing.JScrollPane getFilterPanelScrollPane(){
        return filterPanelScrollPane;
    }
    
    public javax.swing.JPanel getFilterPanel(){
        return filterPanel;
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
        List<GraphObject> objss = processor.getTargetObjects();
        ArrayList tmpList = new ArrayList();
        ArrayList tmpListObj = new ArrayList();
        
        for (int i=0;i<objss.size();i++) {
            String name = objss.get(i).toString();
            String serviceName = objss.get(i).getServiceName();
            getTargetObjectServiceMap().put(name+"_"+serviceName, objss.get(i));
            if (!tmpList.contains(name)){
                tmpList.add(name);
                tmpListObj.add(objss.get(i));
//                System.out.println("XXX Target Object Name: "+name);
            }
        }
        
        for (int i = 0; i < tmpListObj.size(); i++) {
            getTargetObjCombo().addItem(tmpListObj.get(i));
        }
        
        
        
        
//        for (int i=0;i<objss.size();i++) {
//            getTargetObjCombo().addItem(objss.get(i));
//            System.out.println("XXX Target Object Name: "+objss.get(i).getClassName());
//        }
        
        
        
        
        // sanjeev: ------------- The default selected Target Object is there in DCQL -------------
        
        GraphObject selectedTargetObject = (GraphObject)getTargetObjCombo().getSelectedItem();
        Service selectedService = SimpleGuiRegistry.getServiceFromMap(selectedTargetObject.getServiceName().trim());//(Service)getTargetServiceCombo().getSelectedItem();
        
        List<GraphObject> objs = processor.getAssociatedObjects(selectedTargetObject.getClassName(),selectedService.getServiceName());
        List<GraphObject> forObjs = processor.getAvialbleTargetObjectsToAssociateInRemoteServices(selectedService.getServiceName());
        
        for (int i = 0; i < forObjs.size(); i++) {
            objs.add(forObjs.get(i));
        }
        objs.add(selectedTargetObject);
        targetSetChanged = false;
        SimpleGuiRegistry.setCurrentXMLObjectList(objs);
        SimpleGuiRegistry.setTargetGraphObject(selectedTargetObject);
        //---------------------------
        
        
        
        
        
        
    }
    
    private void cleanClean(){
        clearFilterBtnActionPerformed(null);
    }
    
    
//    public void disableAll(){
//        getTargetObjCombo().setEnabled(false);
//
//    }
    
    public Hashtable getTargetObjectServiceMap() {
        return targetObjectServiceMap;
    }
    public void setTargetObjectServiceMap(Hashtable targetObjectServiceMap) {
        this.targetObjectServiceMap = targetObjectServiceMap;
    }
    public void addToTargetObjectServiceMap(String targetObject_Service, GraphObject targetObject) {
        this.targetObjectServiceMap.put(targetObject_Service, targetObject);
    }
    
    
    
    
    
    //------------------------ AND / OR grouping methods.. //------------------------
    
    public void reArrangeFilters(){
        // this will recalculate and redraw the filters and groups in Simple GUI..
        // I guess by now.. everything was set up properly in registry.. so just take things from registry and draw the things..
//        System.out.println("### available filters / groups :"+SimpleGuiRegistry.getNumGroupableEntities());
        
        filterPanel.removeAll(); // remove current laid out filter/groups...
        
        
        // lay out groups first...
        ArrayList subGroups = SimpleGuiRegistry.getFilterSubGroupList();
        for (int i = 0; i < subGroups.size(); i++) {
            FilterGroup group = (FilterGroup)subGroups.get(i);
            filterPanel.add(getSubGroupPanel(group ));
        }
        
        // lay out remaining filters now...
        ArrayList filters = SimpleGuiRegistry.getNonGroupFilters();
        for (int i = 0; i < filters.size(); i++) {
            FilterRowPanel filterP = (FilterRowPanel)filters.get(i);
            filterPanel.add(filterP);
        }
        
        
        filterPanel.revalidate();
        filterPanel.repaint();
        
    }
    
    public JPanel getSubGroupPanel(FilterGroup grp){
        // draw the groups first and then the filters..
        JPanel jpp = new JPanel();
        
        PreferredHeightMarginBorderBoxLayout layout = new PreferredHeightMarginBorderBoxLayout(jpp, PreferredHeightMarginBorderBoxLayout.Y_AXIS);
        jpp.setLayout(layout);
        jpp.setBorder(new LineBorder(Color.BLUE, 1));
        
        
        ArrayList subGroups = grp.getGroupList();
        for (int i = 0; i < subGroups.size(); i++) {
            FilterGroup group = (FilterGroup)subGroups.get(i);
//            jpp.add(Box.createVerticalStrut(5));
            jpp.add(getSubGroupPanel(group));
            
            if (i < subGroups.size()-1){
                jpp.add(Box.createVerticalStrut(10));
                jpp.add(new JButton(group.getConditionString()));
                jpp.add(Box.createVerticalStrut(5));
            }
        }
        
        
        ArrayList filters = grp.getFilterList();
        
        if (subGroups.size() > 0 && filters.size() > 0){ // add a button in between groups and filters..
            jpp.add(Box.createVerticalStrut(10));
            jpp.add(new JButton(grp.getConditionString()));
            jpp.add(Box.createVerticalStrut(5));
        }
        for (int j = 0; j < filters.size(); j++) {
            FilterRowPanel filterP = (FilterRowPanel)filters.get(j);
            jpp.add(filterP); // add the filter here..
            if (j < filters.size()-1){
                jpp.add(Box.createVerticalStrut(5));
                jpp.add(new JButton(grp.getConditionString()));
                jpp.add(Box.createVerticalStrut(5));
            }
        }
        // check if subGroups size is 0 then add only 5 pix in the height.. and 5 pix in width
        int margin = 10;
        if (subGroups.size() == 0){
            margin = 5;
        }
        jpp.setPreferredSize(new Dimension(jpp.getPreferredSize().width+margin, jpp.getPreferredSize().height+margin));
        
        
        return jpp;
    }
    
    
    
    private void removeAndReArrange(FilterRowPanel fp){
        FilterGroup group = fp.getParentGroup();
        group.getFilterList().remove(fp); // remove the filter from the group and everything else..
        SimpleGuiRegistry.getFilterList().remove(fp); // remove from the main list of filterPanels as well..
        
        boolean noFilters = group.getFilterList().size() == 0;
        boolean oneFilter = group.getFilterList().size() == 1;
        boolean noGroups = group.getGroupList().size() == 0;
        boolean oneGroup = group.getGroupList().size() == 1;
        
        
        // check if there is no remaining filters but has only group..  transfer it to outside..
        if (noFilters && oneGroup){
            // check if this has a parent group..
            if (group.getParentGroup() != null ){
                // remove this group from it's parent
                FilterGroup pGroup = group.getParentGroup();
                pGroup.getGroupList().remove(group);
                FilterGroup innerGroup = group.getGroupList().get(0);
                // transfer inner group to it's parent group...
                pGroup.getGroupList().add(innerGroup);
                // set the new parent..
                innerGroup.setParentGroup(pGroup);
            } else {
                // this was a sub group.. return the inner group to the outer one.. by adding it as a sub-group..
                FilterGroup innerGroup = group.getGroupList().get(0);
                SimpleGuiRegistry.addFilterSubGroup(innerGroup);
                // also set it's parent to null for next time calculation..
                innerGroup.setParentGroup(null);
                SimpleGuiRegistry.getFilterSubGroupList().remove(group);
            }
        }
        
        
        
        // check if there is no group and no filters.. or one filter..
        if ((noFilters || oneFilter) && noGroups){
            
            if (group.getParentGroup() != null ){
                FilterGroup pGroup = group.getParentGroup();
                // remove this group from parent
                pGroup.getGroupList().remove(group);
                if (oneFilter){
                    // return the remaining filter to it's parent group..
                    FilterRowPanel innerFilter = group.getFilterList().get(0);
                    pGroup.getFilterList().add(innerFilter);
                    // set new parentGroup to this filter..
                    innerFilter.setParentGroup(pGroup);
                }
            } else {
                // as this has no parent... this was a subgroup so delete this group from subGrouplist
                SimpleGuiRegistry.getFilterSubGroupList().remove(group);
                if (oneFilter){
                    //and return the remaining filter to the non-groupable filters..
                    FilterRowPanel innerFilter = group.getFilterList().get(0);
                    SimpleGuiRegistry.addNonGroupFilter(innerFilter);
                    // set null parentGroup to this filter..
                    innerFilter.setParentGroup(null);
                }
            }
            
        }
        
        
        // now redraw filters and groups..
        reArrangeFilters();
    }
    
    
    //------------------------ AND / OR grouping methods.. //------------------------
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
