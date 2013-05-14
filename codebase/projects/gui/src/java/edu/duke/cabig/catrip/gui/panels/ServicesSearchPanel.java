/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import edu.duke.cabig.catrip.gui.components.ButtonRenderer;
import edu.duke.cabig.catrip.gui.components.CJFrame;
import edu.duke.cabig.catrip.gui.components.ToolTipCellRenderer;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
import edu.duke.cabig.catrip.gui.discovery.DomainModelRetrievalFactory;
import edu.duke.cabig.catrip.gui.discovery.DomainModelRetrievalStrategy;
import edu.duke.cabig.catrip.gui.discovery.ServiceLocaterFactory;
import edu.duke.cabig.catrip.gui.discovery.ServiceLocator;
import edu.duke.cabig.catrip.gui.discovery.ServiceMetaDataRegistry;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Panel for searching the Grid services.
 *
 * @author  Sanjeev Agarwal
 */
public class ServicesSearchPanel extends javax.swing.JPanel {
    
    CJFrame parentFrame;
    JDialog parentDialog;
    private boolean dialogParent = false;
    
    /** Creates new form ServicesSearchPanel */
    public ServicesSearchPanel() {
        initComponents();
        init();
    }
    
    public ServicesSearchPanel(CJFrame parent) {
        this();
        this.parentFrame = parent;
        setDialogParent(false);
    }
    
    public ServicesSearchPanel(JDialog parent) {
        this();
        this.parentDialog = parent;
        setDialogParent(true);
    }
    
    /** set the width for the columns. */
    private void init(){
        
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = resultTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            }else if (i == 1 ) {
                column.setPreferredWidth(140);
            }else if (i == 4 ) {
                column.setPreferredWidth(90);
            } else {
                column.setPreferredWidth(260);
                column.setCellRenderer(new ToolTipCellRenderer());
            }
        }
        
        column = resultTable.getColumnModel().getColumn(4);
        column.setCellRenderer(new ButtonRenderer());
        
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        searchPanel = new javax.swing.JPanel();
        searchBtn = new javax.swing.JButton();
        showAllBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        concept = new javax.swing.JTextField();
        commonDataElement = new javax.swing.JTextField();
        resultPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        selectBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle"); // NOI18N
        searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SEARCH_SERVICES_PANEL_BORDER_STR_1"))); // NOI18N
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/search.gif")));
        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/a11y/a11yBundle"); // NOI18N
        searchBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.searchBtn.name")); // NOI18N
        searchBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.searchBtn.description")); // NOI18N
        searchBtn.getAccessibleContext().setAccessibleParent(this);

        showAllBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/showall.png")));
        showAllBtn.setMnemonic('a');
        showAllBtn.setText("Show All");
        showAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllBtnActionPerformed(evt);
            }
        });

        showAllBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.showAllBtn.name")); // NOI18N
        showAllBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.showAllBtn.description")); // NOI18N
        showAllBtn.getAccessibleContext().setAccessibleParent(this);

        clearBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/clear.gif")));
        clearBtn.setMnemonic('c');
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        clearBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.clearBtn.name")); // NOI18N
        clearBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.clearBtn.description")); // NOI18N
        clearBtn.getAccessibleContext().setAccessibleParent(this);

        lbl1.setText(bundle.getString("SEARCH_SERVICES_PANEL_STR_1")); // NOI18N

        lbl2.setText(bundle.getString("SEARCH_SERVICES_PANEL_STR_2")); // NOI18N

        concept.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.concept.name")); // NOI18N
        concept.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.concept.description")); // NOI18N
        concept.getAccessibleContext().setAccessibleParent(this);

        commonDataElement.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.commonDataElement.name")); // NOI18N
        commonDataElement.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.commonDataElement.description")); // NOI18N
        commonDataElement.getAccessibleContext().setAccessibleParent(this);

        org.jdesktop.layout.GroupLayout searchPanelLayout = new org.jdesktop.layout.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lbl1)
                    .add(lbl2))
                .add(30, 30, 30)
                .add(searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(searchPanelLayout.createSequentialGroup()
                        .add(searchBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(showAllBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(clearBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 118, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(commonDataElement, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .add(concept, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(searchPanelLayout.createSequentialGroup()
                .add(searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl1)
                    .add(concept, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lbl2)
                    .add(commonDataElement, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(23, 23, 23)
                .add(searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchBtn)
                    .add(showAllBtn)
                    .add(clearBtn))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchPanelLayout.linkSize(new java.awt.Component[] {clearBtn, showAllBtn}, org.jdesktop.layout.GroupLayout.VERTICAL);

        searchPanel.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.searchPanel.name")); // NOI18N
        searchPanel.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.searchPanel.description")); // NOI18N

        resultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SEARCH_SERVICES_PANEL_BORDER_STR_2"))); // NOI18N
        resultTable.setModel(getTableModel());
        resultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(resultTable);
        resultTable.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.resultTable.name")); // NOI18N
        resultTable.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.resultTable.description")); // NOI18N
        resultTable.getAccessibleContext().setAccessibleParent(resultPanel);

        org.jdesktop.layout.GroupLayout resultPanelLayout = new org.jdesktop.layout.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultPanelLayout.createSequentialGroup()
                .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        resultPanel.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.resultPanel.name")); // NOI18N
        resultPanel.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.resultPanel.description")); // NOI18N

        selectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/ok.gif")));
        selectBtn.setMnemonic('s');
        selectBtn.setText("Select");
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        selectBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.selectBtn.name")); // NOI18N
        selectBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.selectBtn.description")); // NOI18N

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/exit.gif")));
        exitBtn.setMnemonic('e');
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        exitBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.exitBtn.name")); // NOI18N
        exitBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.panels.ServicesSearchPanel.exitBtn.description")); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, resultPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, searchPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(260, 260, 260)
                        .add(selectBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 97, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(47, 47, 47)
                        .add(exitBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(searchPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(resultPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(exitBtn)
                    .add(selectBtn))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {exitBtn, selectBtn}, org.jdesktop.layout.GroupLayout.VERTICAL);

    }// </editor-fold>//GEN-END:initComponents
    
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        commonDataElement.setText("");concept.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed
    
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        new JOptionPane().showMessageDialog(this ,"Search Not implemented. Please use \"Show All\"");
    }//GEN-LAST:event_searchBtnActionPerformed
    
    /** Action for Search All button. */
    private void showAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllBtnActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        // TODO - need to replace these direct calls with the Spring Framework.
        ServiceLocator serviceLocator = ServiceLocaterFactory.getServiceLocator();
        ArrayList serviceList = serviceLocator.discoverServices();
        
        DefaultTableModel tableModel = (DefaultTableModel) getResultTable().getModel();
        
        for (int i = 0; i < serviceList.size(); i++) {
            
            Vector tableRow = new Vector();
            ServiceMetaDataBean serviceMetaDataBean = (ServiceMetaDataBean)serviceList.get(i);
            
            // sanjeev: don't forget to populate the ServiceMetaDataRegistry too..
            ServiceMetaDataRegistry.addService(serviceMetaDataBean);
            
            tableRow.add(new Boolean(false));
            tableRow.add(serviceMetaDataBean.getServiceName());
            tableRow.add(serviceMetaDataBean.getDescription());
            tableRow.add(serviceMetaDataBean.getHostingResearchCenter());
            tableRow.add(new JButton("View"));
            
            tableModel.addRow(tableRow);
        }
        
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_showAllBtnActionPerformed
    
    /**
     * Get the list of selected Services and populate the domain domainModel for those and bind into the registry for later use.
     */
    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        // sanjeev: Here get the list of the Services and then populate the ServiceMetaDataRegistry
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        int numSelectedRows = getResultTable().getRowCount();//getSelectedRows();
        
        for (int i = 0; i < numSelectedRows; i++) {
            Boolean rowChecked = (Boolean)getResultTable().getValueAt(i, 0);
            if (rowChecked){
                String serviceName = (String)getResultTable().getValueAt(i, 1);
                serviceName = serviceName.trim();
                ServiceMetaDataRegistry.addSelectedService(serviceName);
                
                // TODO - need to replace these direct calls with the Spring Framework.
                ServiceMetaDataBean metaDataBean = ServiceMetaDataRegistry.getServiceBeanByName(serviceName);
                DomainModelRetrievalStrategy retrievalStrategy = DomainModelRetrievalFactory.getRetrievalStrategy(metaDataBean);
                DomainModel domainModel = retrievalStrategy.retrievDomainModel();
                
                DomainModelMetaDataRegistry.populateDomainModelMetaData(domainModel, metaDataBean);
            }
        }
        
        // sanjeev: to handle the window.. call parent action..
        parentFrame.fwdAction();
        
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_selectBtnActionPerformed
    
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        if (isDialogParent()){
            parentDialog.setVisible(false);
        } else {
            parentFrame.exit();
        }
    }//GEN-LAST:event_exitBtnActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CJFrame cf = new CJFrame();cf.setTitle("Testing this panel");
                cf.getContentPane().add(new ServicesSearchPanel(cf));
                cf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                cf.setBounds(10,10,850,450);cf.center();
                cf.setVisible(true);
                
            }
        });
    }
    
    // TODO - implement the "View MetaData" jbutton to show the Service Metadata.
    private DefaultTableModel getTableModel(){
        
        Object [][] data = new Object [][] {
//            {new Boolean(false), null, null,null,new JButton("View")},
//            {new Boolean(false), null, null,null,new JButton("View")}
        };
        String [] columNames = new String [] {"  ", "Service Name", "Service Description", "Institution", "View MetaData"};
        
        DefaultTableModel tb = new javax.swing.table.DefaultTableModel(data, columNames) {
            // implemented methods..
            
            public boolean isCellEditable(int row, int col) {
                if (col > 0) // sanjeev: only first column is editable with CheckBox Editor.
                    return false;
                
                return true;
            }
            
            public Class getColumnClass(int c) {
                if (c == 0 || c == 4)
                    return getValueAt(0, c).getClass();
                
                return new String().getClass();
            };
            
        };
        return tb;
    }
    
    
    
    
    public boolean isDialogParent() {
        return dialogParent;
    }
    
    public void setDialogParent(boolean dialogParent) {
        this.dialogParent = dialogParent;
    }
    
    public javax.swing.JTable getResultTable() {
        return resultTable;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField commonDataElement;
    private javax.swing.JTextField concept;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JTable resultTable;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JButton selectBtn;
    private javax.swing.JButton showAllBtn;
    // End of variables declaration//GEN-END:variables
    
}


