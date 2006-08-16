/*
 * ServicesSearchPanel.java
 *
 * Created on June 12, 2006, 9:34 AM
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
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
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
        
        
//        resultTable.setSelectionMode();
        
        
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
        searchBtn.setText("Search");

        showAllBtn.setText("Show All");
        showAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAllBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");

        lbl1.setText(bundle.getString("SEARCH_SERVICES_PANEL_STR_1")); // NOI18N

        lbl2.setText(bundle.getString("SEARCH_SERVICES_PANEL_STR_2")); // NOI18N

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
                        .add(searchBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(showAllBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(clearBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(commonDataElement, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .add(concept, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
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

        resultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SEARCH_SERVICES_PANEL_BORDER_STR_2"))); // NOI18N
        resultTable.setModel(getTableModel());
        resultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(resultTable);

        org.jdesktop.layout.GroupLayout resultPanelLayout = new org.jdesktop.layout.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultPanelLayout.createSequentialGroup()
                .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addContainerGap())
        );

        selectBtn.setText("Select");
        selectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

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
                        .add(174, 174, 174)
                        .add(selectBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(45, 45, 45)
                        .add(exitBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
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
    }// </editor-fold>//GEN-END:initComponents
    
    private void showAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showAllBtnActionPerformed
        // Search for all the services from the Index Service and show here.
        // Get the ServiceMetaData for all those services.
        
        // Calls to populate the ServiceMetaDataRegistry
        
        // need to replace these direct calls with the Spring Framework stuff..
        ServiceLocator sl = ServiceLocaterFactory.getServiceLocator();
        ArrayList alist = sl.discoverServices();
        
        DefaultTableModel tb = (DefaultTableModel) getResultTable().getModel();
        
        for (int i = 0; i < alist.size(); i++) { 
            
            Vector v = new Vector();
            ServiceMetaDataBean sb = (ServiceMetaDataBean)alist.get(i);
            
            // don't forget to populate the ServiceMetaDataRegistry too..
            ServiceMetaDataRegistry.addService(sb);
            
            v.add(new Boolean(false));
            v.add(sb.getServiceName());
            v.add(sb.getDescription());
            v.add(sb.getHostingResearchCenter());
            v.add(new JButton("View"));
            
            tb.addRow(v);
        }
        
    }//GEN-LAST:event_showAllBtnActionPerformed
    
    private void selectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectBtnActionPerformed
        // Here get the list of the Services and then populate the ServiceMetaDataRegistry
        int srows = getResultTable().getRowCount();//getSelectedRows();
        
        for (int i = 0; i < srows; i++) {
            Boolean rowChecked = (Boolean)getResultTable().getValueAt(i, 0);
            if (rowChecked){
                String serviceName = (String)getResultTable().getValueAt(i, 1);
                serviceName = serviceName.trim();
                ServiceMetaDataRegistry.addSelectedService(serviceName);
                
                
//                DomainModelRetrievalStrategy dr = DomainModelRetrievalFactory.getDefaultRetrievalStrategy(); 
//                DomainModel model = dr.retrievDomainModel("C:\\tmp\\objs\\a.obj");  
                // need to replace these direct calls with the Spring Framework stuff..
                ServiceMetaDataBean sBean = ServiceMetaDataRegistry.getServiceBeanByName(serviceName);
                DomainModelRetrievalStrategy dr = DomainModelRetrievalFactory.getRetrievalStrategy(sBean);  
                DomainModel model = dr.retrievDomainModel();   
                
                // populate the DomainModel MetaData also here. 
//                DomainModelMetaDataRegistry.populateDomainModelMetaData(model); 
                DomainModelMetaDataRegistry.populateDomainModelMetaData(model, sBean); 
            }
        }
        
        // to handle the window.. call parent action..
        parentFrame.fwdAction(); 
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
    
    
    private DefaultTableModel getTableModel(){
        
        Object [][] data = new Object [][] {
//            {new Boolean(false), null, null,null,new JButton("View")},
//            {new Boolean(false), null, null,null,new JButton("View")}
        };
        String [] columNames = new String [] {"  ", "Service Name", "Service Description", "Institution", "View MetaData"};
        
        DefaultTableModel tb = new javax.swing.table.DefaultTableModel(data, columNames) {
            // implemented methods..
            
            public boolean isCellEditable(int row, int col) {
                if (col > 0) // only first column is editable with CheckBox Editor.
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



