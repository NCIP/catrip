/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * QueryServiceUI.java
 *
 * Created on April 13, 2007, 12:51 AM
 */

package edu.duke.cabig.catrip.gui.querysharing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.CDEComboboxBeanComparator;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.CJDialog;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.components.PreferredHeightMarginBorderBoxLayout;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.panels.CQLDesignerPanel;
import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import edu.duke.cabig.catrip.gui.wizard.MainFrame;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;
import javax.xml.XMLConstants;

import javax.xml.namespace.QName;
import org.globus.wsrf.encoding.ObjectSerializer;

/**
 *
 * @author  Administrator
 */
public class QueryServiceUI extends CPanel {
    
    Collection<QueryFilterRowPanel> filterCollection = new Vector<QueryFilterRowPanel>();
    
    private QueryDb queryData;// = new QueryDb();  //  @jve:decl-index=0:
    private DefaultTableModel tableModel = null ;
    
    public MainFrame mainFrame;
    private ArrayList<CDEComboboxBean> filterList = new ArrayList<CDEComboboxBean>(500);
    String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService"; // default  //  @jve:decl-index=0:
    
    
    
    /** Creates new form QueryServiceUI */
    public QueryServiceUI() {
        initComponents();
        if (GUIConstants.simpleGui){
            initComp();
            init();
        }
    }
    
    
    private void initComp(){
        PreferredHeightMarginBorderBoxLayout layout = new PreferredHeightMarginBorderBoxLayout(getFilterPanel(), PreferredHeightMarginBorderBoxLayout.Y_AXIS);
        filterPanel.setLayout(layout);
    }
    
    
    private void init(){
        
        ArrayList<GraphObject> objs = SimpleGuiRegistry.getAllSimpleGuiXMLObjectList();
        
        for (int i=0;i<objs.size();i++) {
            GraphObject gObj = objs.get(i);
            if (gObj.isDisplayable()){
                ClassBean cBean = gObj.getClassBean();
                ArrayList attributes = cBean.getAttributes();
                
                for (int j = 0; j < attributes.size(); j++) {
                    AttributeBean aBean = (AttributeBean)attributes.get(j);
                    CDEComboboxBean cdeBean = new CDEComboboxBean();
                    cdeBean.setGraphObject(gObj);
                    cdeBean.setAttributeBean(aBean);
                    filterList.add(cdeBean);
                }
                // add the class only entry..
                CDEComboboxBean cdeBean = new CDEComboboxBean();
                cdeBean.setGraphObject(gObj);
                cdeBean.setAttributeBean(new AttributeBean()); // add a null bean..
                filterList.add(cdeBean);
                
            }
        }
        
        // sanjeev: add them in sorted order.. add all the filters in an array list than use collections to sort than add tham to combo.
        Collections.sort(filterList, new CDEComboboxBeanComparator());
        
        // get the service URL from the SystemProperties..
        // TODO - change this to config file later on..
        String querySharingServiceUrl = System.getProperty("query.sharing.url"); // move this to gui config file..
        
        System.out.println("querySharingServiceUrl = " + querySharingServiceUrl);
        if (querySharingServiceUrl != null){
            System.out.println("querySharingServiceUrl != null");
            serviceURI = querySharingServiceUrl;
        }
        
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQueryName = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        filterPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnAddFilter = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Details"));
        jLabel1.setText("Query name:");

        jLabel2.setText("First Name:");

        jLabel3.setText("Last Name:");

        jLabel4.setText("Description:");

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/a11y/a11yBundle"); // NOI18N
        txtQueryName.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.txtQueryName.name")); // NOI18N
        txtQueryName.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.txtQueryName.description")); // NOI18N
        txtQueryName.getAccessibleContext().setAccessibleParent(this);

        txtFirstName.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.txtFirstName.name")); // NOI18N
        txtFirstName.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.txtFirstName.description")); // NOI18N
        txtFirstName.getAccessibleContext().setAccessibleParent(this);

        txtLastName.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.txtLastName.name")); // NOI18N
        txtLastName.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.txtLastName.description")); // NOI18N
        txtLastName.getAccessibleContext().setAccessibleParent(this);

        txtDescription.setColumns(20);
        txtDescription.setLineWrap(true);
        txtDescription.setRows(2);
        jScrollPane1.setViewportView(txtDescription);
        txtDescription.getAccessibleContext().setAccessibleName("Query Description text input field");
        txtDescription.getAccessibleContext().setAccessibleDescription("Description of the saved query.");
        txtDescription.getAccessibleContext().setAccessibleParent(jPanel1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(jLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(txtFirstName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtLastName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                            .add(txtQueryName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtQueryName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtFirstName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtLastName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1.getAccessibleContext().setAccessibleName("Query Details Panel");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filters"));
        jScrollPane2.setBorder(null);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        org.jdesktop.layout.GroupLayout filterPanelLayout = new org.jdesktop.layout.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 634, Short.MAX_VALUE)
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 243, Short.MAX_VALUE)
        );
        jScrollPane2.setViewportView(filterPanel);
        filterPanel.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.filterPanel.name")); // NOI18N
        filterPanel.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.filterPanel.description")); // NOI18N
        filterPanel.getAccessibleContext().setAccessibleParent(this);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Results"));
        jScrollPane3.setBorder(null);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(resultTable);
        resultTable.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.resultTable.name")); // NOI18N
        resultTable.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.resultTable.description")); // NOI18N
        resultTable.getAccessibleContext().setAccessibleParent(this);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/search.gif")));
        btnSearch.setMnemonic('e');
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSearch.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.btnSearch.name")); // NOI18N
        btnSearch.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.btnSearch.description")); // NOI18N

        btnAddFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/add.gif")));
        btnAddFilter.setMnemonic('a');
        btnAddFilter.setText("Add Filter");
        btnAddFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFilterActionPerformed(evt);
            }
        });

        btnAddFilter.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.btnAddFilter.name")); // NOI18N
        btnAddFilter.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.btnAddFilter.description")); // NOI18N

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/clear.gif")));
        btnClear.setMnemonic('c');
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnClear.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.btnClear.name")); // NOI18N
        btnClear.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.querysharing.btnClear.description")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(btnClear, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnAddFilter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 175, Short.MAX_VALUE)
                        .add(btnSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(btnClear)
                            .add(btnAddFilter)
                            .add(btnSearch))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String wsddURI = GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation()+ File.separator +"query-client-config.wsdd";
        try {
            
            // create a new instance so that old values are not cached.
            queryData = new QueryDb();
            
            // fill query data with query Meta data:
            queryData.setFirstName(txtFirstName.getText());
            queryData.setLastName(txtLastName.getText());
            queryData.setName(txtQueryName.getText());
            queryData.setDescription(txtDescription.getText());
            
            
            // fill query data with the Concepts selected
            Collection<ClassDb> classCollection = new Vector<ClassDb>();
            //filterCollection.add(getFilterRowPanel());
            for (Iterator iter = filterCollection.iterator(); iter.hasNext();) {
                QueryFilterRowPanel element = (QueryFilterRowPanel) iter.next();
                classCollection.add(element.getSelectedClass()); 
            }
            queryData.setClassCollection(classCollection);
            // populateTable(QueryServiceClient.search(queryData));
            CQLQuery cqlQuery = CqlParser.parse(queryData);
            populateTable(QueryServiceClient.search(cqlQuery, serviceURI, wsddURI));  // pass the url of the service as well..
        } catch (Exception qe) {
            qe.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    	txtQueryName.setText("");
    	txtDescription.setText("");
    	txtFirstName.setText("");
    	txtLastName.setText("");
    	
        // create a new instance so that old values are not cached.
        queryData = new QueryDb();
        filterCollection = new Vector<QueryFilterRowPanel>();
        
    	// clear the filters
   	    //getFilterPanel().remove(panel);
        getFilterPanel().revalidate();
        getFilterPanel().repaint();
        getFilterPanel().removeAll();

    }//GEN-LAST:event_btnClearActionPerformed
    
    private void btnAddFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFilterActionPerformed
        addFilterRow();
    }//GEN-LAST:event_btnAddFilterActionPerformed
    
    
    
    private QueryFilterRowPanel getFilterRowPanel(){
//        QueryFilterRowPanel filterRow = new QueryFilterRowPanel(this);
        QueryFilterRowPanel filterRow = new QueryFilterRowPanel(this, filterList); 
        filterRow.setPreferredSize(new Dimension(100, 40));
        return filterRow;
    }
    
    
    private void addFilterRow(){
        QueryFilterRowPanel filterRow = getFilterRowPanel();
        filterCollection.add(filterRow);
//		for (Iterator iter = filterCollection.iterator(); iter.hasNext();) {
//			FilterRowPanel element = (FilterRowPanel) iter.next();
//			//System.out.println("adding " + element.getSelectedClass().getName());
//		}
        getFilterPanel().add(filterRow);
        getFilterPanel().revalidate();
        getFilterPanel().repaint();
        
    }
    
    public JPanel getFilterPanel() {
        return filterPanel;
    }
     public void removeFilter(edu.duke.cabig.catrip.gui.querysharing.QueryFilterRowPanel panel){
         Collection<QueryFilterRowPanel> tempfilterCollection = new Vector<QueryFilterRowPanel>();
        getFilterPanel().remove(panel);
        getFilterPanel().revalidate();
        getFilterPanel().repaint();
        if (filterCollection != null){
            // remove from array
            boolean wasRemoved = false;
            //System.out.println("before : " + classCollection.size());
            for (Iterator iter = filterCollection.iterator(); iter.hasNext();) {
            	QueryFilterRowPanel element = (QueryFilterRowPanel) iter.next();
             		if (element.getSelectedClass().getId() == panel.getSelectedClass().getId()){
            			tempfilterCollection.add(element);
            		}
            	}
            
            for (Iterator iter = tempfilterCollection.iterator(); iter.hasNext();) {
                QueryFilterRowPanel element = (QueryFilterRowPanel) iter.next();
                wasRemoved = filterCollection.remove(element);
                System.out.println("after : " + filterCollection.size() + " was removed ? " + wasRemoved);
            }
        }
        
    }
    
    
    private void executeDcql(String dcql){
//        StringBuffer buf = new StringBuffer(dcql);
//        char[] chars = new char[buf.length()];
//        buf.getChars(0, chars.length, chars, 0);
//        CharArrayReader car = new CharArrayReader(chars);
        System.out.println("executeDcql " + dcql);
        java.io.Reader reader = new java.io.StringReader(dcql);
        InputSource source = new InputSource(reader);//source.setEncoding();
        DCQLQuery dcqlObj = null;
        try {
            dcqlObj = (DCQLQuery) ObjectDeserializer.deserialize(source,DCQLQuery.class);
        } catch (Exception e){e.printStackTrace();}
        executeDcql(dcqlObj);
    }
    
    // execute DCQL in the outPut Pane of the SimpleGUI..
    private void executeDcql(DCQLQuery dcql){
        getMainFrame().getCommandPanel().runExternalDcql(dcql);
    }
    
    private void displayDCQL(String dcql) {
        String frmtDcqlText = "";
        
        System.out.println("Formatting saved DCQL for display. " + dcql);
        java.io.Reader reader = new java.io.StringReader(dcql);
        InputSource source = new InputSource(reader);//source.setEncoding();
        DCQLQuery dcqlObj = null;
        try {
            dcqlObj = (DCQLQuery) ObjectDeserializer.deserialize(source,DCQLQuery.class);
            frmtDcqlText = ObjectSerializer.toString(dcqlObj,new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery", XMLConstants.NULL_NS_URI));//xmlText(xmlOptions);
        } catch (Exception e){e.printStackTrace();}
        
        CQLDesignerPanel  dcqlPanel = new CQLDesignerPanel(); 
        dcqlPanel.setDcqlQueryText(frmtDcqlText);
        CJDialog jd = new CJDialog(this.mainFrame, "View the Saved DCQL Query");  
        jd.add(dcqlPanel);
        jd.setBounds(0,0,1000, 500);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
    }
    
    private void populateTable(Vector collection) {
        String firstName = "";
        String lastName = "";
        tableModel = new DefaultTableModel();
        tableModel.setColumnCount(5);
        Vector<String> columnHeaders = new Vector<String>(5);
        columnHeaders.add("Query Name");
        columnHeaders.add("User Name");
        columnHeaders.add("Description");
        columnHeaders.add("");
        columnHeaders.add("");
        tableModel.setColumnIdentifiers(columnHeaders);
        // loop over the  vector, getting out the entry object
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            QueryDb e = (QueryDb) iter.next();
            //System.out.println("e is null ? " + (e == null));
            // create another vector, this one will be used as a row in the table
            Vector<Serializable> rowData = new Vector<Serializable> () ;
            
            // populate this row with the data out of the objects, using wrapper classes where primitive
            // data types are used.
            rowData.add(e.getName()) ;
            if (e.getFirstName() != null)
                firstName = e.getFirstName();
            if (e.getLastName() != null)
                lastName = e.getLastName();
            rowData.add(firstName + " " + lastName) ;
            rowData.add(e.getDescription()) ;
            rowData.add("View") ;//rowData.add("View/Modify") ;
            rowData.add("Run") ;
            // add row to model
            tableModel.addRow(rowData) ;
        }
        resultTable.getColumnModel();
        resultTable.setModel(tableModel);
        new ButtonColumn(resultTable, 3, collection, true);
        new ButtonColumn(resultTable, 4, collection, true);
        
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFilter;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable resultTable;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtQueryName;
    // End of variables declaration//GEN-END:variables
    
    
    
    
    class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener{
        private static final long serialVersionUID = 1L;
        JTable table;
        JButton renderButton;
        JButton editButton;
        String text;
        Vector<QueryDb> data;
        
        public ButtonColumn(JTable table, int column, Vector<QueryDb> data, boolean enable){
            super();
            this.table = table;
            renderButton = new JButton();
            this.data = data;
            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.setEnabled(enable);
            renderButton.setEnabled(enable);
            editButton.addActionListener( this );
            
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
        }
        
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            if (hasFocus){
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            } else if (isSelected){
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            } else{
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }
            
            renderButton.setText( (value == null) ? "" : value.toString() );
            return renderButton;
        }
        
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column){
            text = (value == null) ? "" : value.toString();
            editButton.setText( text );
            return editButton;
        }
        
        public Object getCellEditorValue(){
            return text;
        }
        
        public void actionPerformed(ActionEvent e){
            fireEditingStopped();
            if (e.getActionCommand().equalsIgnoreCase("run")){
                String dcql = data.get(table.getSelectedRow()).getDcql();
                executeDcql(dcql);
            } else if (e.getActionCommand().equalsIgnoreCase("View")){
                String dcql = data.get(table.getSelectedRow()).getDcql();
                displayDCQL(dcql);
            } else if(e.getActionCommand().equalsIgnoreCase("x")){
                DefaultTableModel t = (DefaultTableModel) table.getModel();
                t.removeRow(table.getSelectedRow());
            } else{
                System.out.println(e.getActionCommand()  + " : " + table.getSelectedRow() + " \n" + table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
            }
        }
        
        
    }
    
    class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        
        public MyComboBoxRenderer(String[] items) {
            super(items);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            
            // Select the current value
            setSelectedItem(value);
            return this;
        }
    }
    
    class MyComboBoxEditor extends DefaultCellEditor {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        
        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }
    
}
