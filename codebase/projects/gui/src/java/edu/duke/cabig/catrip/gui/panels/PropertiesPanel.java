/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.components.SteppedComboBox;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.*;

/**
 * This panel shows the Properties or Attribute Names/Predicates/Values for selected node on the graph.
 *
 * @author  Sanjeev Agarwal
 */
public class PropertiesPanel extends CPanel {
    
    private JComboBox predicates;
    private ClassBean cBean;
    
    /** Creates new form PropertiesPanel */
    public PropertiesPanel() {
        initComponents();
        init();
    }
    
    /** load the predicate list in the combo-box. This should come from a configuration file. */
    private void init(){ 
        predicates = new SteppedComboBox(); //JComboBox();// 
        predicates.addItem("LIKE");
        predicates.addItem("EQUAL_TO");
        predicates.addItem("NOT_EQUAL_TO");
        predicates.addItem("LESS_THAN");
        predicates.addItem("LESS_THAN_EQUAL_TO");
        predicates.addItem("GREATER_THAN");
        predicates.addItem("GREATER_THAN_EQUAL_TO");
        predicates.addItem("IS_NULL");
        predicates.addItem("IS_NOT_NULL");
        
        TableColumn predicateColumn = getPropTable().getColumnModel().getColumn(1);
        predicateColumn.setCellEditor(new DefaultCellEditor(predicates));
        
    }
    
    public void showNodeProperties(ClassBean node){
        cleanTable();
        cBean = node;
        ObjectTableModel tm =   (ObjectTableModel) getPropTable().getModel();
        
        ArrayList attributeBeans = node.getAttributes();
        for (int i = 0; i < attributeBeans.size(); i++) {
            AttributeBean aBean = (AttributeBean)attributeBeans.get(i);
            tm.addObjectRow(aBean);
        }
    }
    
    private void cleanTable(){
        ((ObjectTableModel)getPropTable().getModel()).clean();
    }
    
    public javax.swing.JTable getPropTable() {
        return propTable;
    }
    
    /** 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        propertyViewScrollPane = new javax.swing.JScrollPane();
        propTable = new javax.swing.JTable();

        setLayout(new java.awt.GridLayout(1, 0));

        propTable.setModel(getTableModel());
        propTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                propTablePropertyChange(evt);
            }
        });

        propertyViewScrollPane.setViewportView(propTable);

        add(propertyViewScrollPane);

    }// </editor-fold>//GEN-END:initComponents
    
    /** Set the predicate/attribute value back into the object. */
    private void propTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_propTablePropertyChange

        int row = getPropTable().getSelectedRow();
        int col = getPropTable().getSelectedColumn();
        ObjectTableModel tm = (ObjectTableModel) getPropTable().getModel();
        AttributeBean aBean = null;
        String val = null;
        
        if ( (row != -1) && (col != -1) ){
            val = (String)getPropTable().getValueAt(row, col);
            aBean = tm.getObjectRow(row+1);
            
            if (col == 1){ // it is predicate that has been changed..
                aBean.setPredicate(val);
            }else if (col == 2){// it is attribute value that has been changed..
                aBean.setAttributeValue(val);
            } // else nothing..   
        }
    }//GEN-LAST:event_propTablePropertyChange
    
    private DefaultTableModel getTableModel(){
        
        Object [][] data = new Object [][] { };
        String [] columNames = new String [] {"Attribute","Predicate" ,"Value"};
        DefaultTableModel tb = new ObjectTableModel(data, columNames);
        return tb;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable propTable;
    private javax.swing.JScrollPane propertyViewScrollPane;
    // End of variables declaration//GEN-END:variables
    
}

/** Custom table model which can take an object of type AttributeBean and add a row in the table. */
class ObjectTableModel extends DefaultTableModel {
    private ClassBean object;
    HashMap attributeRows = new HashMap(100);
    Class[] types = new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class};
    
    public ObjectTableModel(Object [][] data, String [] columNames){
        super(data, columNames);
    }
    
    public boolean isCellEditable(int row, int col) {
        if (col == 0) // only thrid column is editable.
            return false;
        
        return true;
    }
    
    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }
    
    public void addObjectRow(AttributeBean aBean){
        Vector  r = new Vector();
        r.add(aBean.getCDEName()); r.add(aBean.getPredicate()); r.add(aBean.getAttributeValue());
        addRow(r);
        attributeRows.put(getRowCount(), aBean);
    }
    public AttributeBean getObjectRow(int row){
        return (AttributeBean)attributeRows.get(row);
    }
    
    public void clean(){
        setNumRows(0);
        attributeRows.clear();
    }
}