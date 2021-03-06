/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.components.ButtonRenderer;
import edu.duke.cabig.catrip.gui.components.CJFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * Query Engine Service Search Panel.
 *
 * @author  Sanjeev Agarwal
 */
public class QueryEngineServiceSearchPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form QueryEngineServiceSearchPanel
     */
    public QueryEngineServiceSearchPanel() {
        initComponents();
        init();
    }
    
    /** set the width for the columns. */
    private void init (){

        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = serviceListTable.getColumnModel ().getColumn (i);
            if (i == 0) {
                column.setPreferredWidth (32);
            }else if (i == 4) {
                column.setPreferredWidth (90);
            } else {
                column.setPreferredWidth (220);
            }
        }
        
        column = serviceListTable.getColumnModel ().getColumn (4);
        column.setCellRenderer (new ButtonRenderer ());
    }
    
    
    /** 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup = new javax.swing.ButtonGroup();
        addServiceURLPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        serviceListTable = new javax.swing.JTable();
        searchPanel = new javax.swing.JPanel();
        indexServiceListCombo = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();

        addServiceURLPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        serviceListTable.setModel(getTableModel());
        scrollPane.setViewportView(serviceListTable);

        org.jdesktop.layout.GroupLayout addServiceURLPanelLayout = new org.jdesktop.layout.GroupLayout(addServiceURLPanel);
        addServiceURLPanel.setLayout(addServiceURLPanelLayout);
        addServiceURLPanelLayout.setHorizontalGroup(
            addServiceURLPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
        addServiceURLPanelLayout.setVerticalGroup(
            addServiceURLPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        searchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        indexServiceListCombo.setModel(getComboBoxModel());

        jButton1.setText("Search");

        jLabel1.setText(java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle").getString("QUERY_ENGINE_SERVICE_SEARCH_PANEL_LBL0"));

        org.jdesktop.layout.GroupLayout searchPanelLayout = new org.jdesktop.layout.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(indexServiceListCombo, 0, 638, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton1)
                    .add(jLabel1))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(7, 7, 7)
                .add(indexServiceListCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addBtn.setText("Add Service");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addServiceURLPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(searchPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, addBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 102, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(searchPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(addServiceURLPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(addBtn)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CJFrame cf = new CJFrame();cf.setTitle("Testing this panel");
                cf.getContentPane().add(new QueryEngineServiceSearchPanel());
                cf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                cf.setBounds(10,10,850,450);
                cf.setVisible(true);
            }
        });
    }
    
    
    private DefaultComboBoxModel getComboBoxModel(){
        DefaultComboBoxModel cb = new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2" });
        return cb;
    }
    
     // TODO - implement the "View MetaData" jbutton to show the Service Metadata.
   private DefaultTableModel getTableModel (){
        
        Object [][] data = new Object [][] {
            {new Boolean (false), null, null,null,new JButton ("View")},
            {new Boolean (false), null, null,null,new JButton ("View")}  };
        String [] columNames = new String [] {"  ", "Service Name", "Service Description", "Institution", "View MetaData"};
        
        DefaultTableModel tb = new javax.swing.table.DefaultTableModel (data, columNames) {
            // implemented methods..
            
            public boolean isCellEditable (int row, int col) {
                if (col > 0) // only first column is editable with CheckBox Editor.
                    return false;
                
                return true;
            }
            
            public Class getColumnClass (int c) {
                if (c == 0 || c == 4)
                    return getValueAt (0, c).getClass ();
                
                return new String ().getClass ();
            };
            
        };
        return tb;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel addServiceURLPanel;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox indexServiceListCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable serviceListTable;
    // End of variables declaration//GEN-END:variables
    
}
