/*
 * PropertiesPanel.java
 *
 * Created on May 16, 2006, 2:22 PM
 */

package edu.duke.cabig.catrip.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;


/**
 *
 * @author  Sanjeev Agarwal
 */
public class PropertiesPanel extends MainFramePanel {
    
    /** Creates new form PropertiesPanel */
    public PropertiesPanel() {
        initComponents();
    }
    
    public void showNodeProperties(String nodeName){
        cleanTable();
        // here set the properties table with the attribute names and values for primitive types only.
      DefaultTableModel tm =   (DefaultTableModel) getPropTable().getModel();
      Vector r ;
        if (nodeName.startsWith("caCore.Gene")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("clusterId"); tm.addRow(r);
          r = new Vector(); r.add("symbol"); tm.addRow(r);

        }else if (nodeName.startsWith("caCore.Taxon")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("scientificName"); tm.addRow(r);
          r = new Vector(); r.add("ethnicityStrain"); tm.addRow(r);
          r = new Vector(); r.add("abbreviation"); tm.addRow(r);
          r = new Vector(); r.add("commonName"); tm.addRow(r);
          
        }else if (nodeName.startsWith("caCore.Chromosome")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("name"); tm.addRow(r);
            
        }else if (nodeName.startsWith("caCore.Clone")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("name"); tm.addRow(r);
          r = new Vector(); r.add("accessionNumber"); tm.addRow(r);
          r = new Vector(); r.add("insertSize"); tm.addRow(r);
          r = new Vector(); r.add("version"); tm.addRow(r);
          r = new Vector(); r.add("strain"); tm.addRow(r);
            
        }else if (nodeName.startsWith("caCore.Library")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("name"); tm.addRow(r);
          r = new Vector(); r.add("type"); tm.addRow(r);
          r = new Vector(); r.add("unigeneId"); tm.addRow(r);
          r = new Vector(); r.add("description"); tm.addRow(r);
          r = new Vector(); r.add("labHost"); tm.addRow(r);
          r = new Vector(); r.add("keyword"); tm.addRow(r);
          r = new Vector(); r.add("rsite1"); tm.addRow(r);
            
        }else if (nodeName.startsWith("caCore.Sequence")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("accessionNumber"); tm.addRow(r);
          r = new Vector(); r.add("description"); tm.addRow(r);
          r = new Vector(); r.add("length"); tm.addRow(r);
          r = new Vector(); r.add("accessionNumberVersion"); tm.addRow(r);
          r = new Vector(); r.add("type"); tm.addRow(r);
          r = new Vector(); r.add("asciiString"); tm.addRow(r);
            
        }else if (nodeName.startsWith("caCore.Target")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("name"); tm.addRow(r);
          r = new Vector(); r.add("type"); tm.addRow(r);
            
          
          // other domain model objects..
        }else if (nodeName.startsWith("PIR.Taxon")){
          r = new Vector(); r.add("id"); tm.addRow(r);
            
        }else if (nodeName.startsWith("PIR.Protein")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("uniprotkbPrimaryAccession"); tm.addRow(r);
          r = new Vector(); r.add("uniprotkbEntryName"); tm.addRow(r);
          r = new Vector(); r.add("proteinType"); tm.addRow(r);
        
        }else if (nodeName.startsWith("PIR.SequenceVariant")){
          r = new Vector(); r.add("originalSequence"); tm.addRow(r);
          r = new Vector(); r.add("variantSequence"); tm.addRow(r);
            
        }else if (nodeName.startsWith("PIR.SpliceVariant")){
          r = new Vector(); r.add("originalSequence"); tm.addRow(r);
          r = new Vector(); r.add("variantSequence"); tm.addRow(r);
        
        }else if (nodeName.startsWith("PIR.ProteinSequence")){
          r = new Vector(); r.add("id"); tm.addRow(r);
          r = new Vector(); r.add("checksum"); tm.addRow(r);
          r = new Vector(); r.add("length"); tm.addRow(r);
          r = new Vector(); r.add("value"); tm.addRow(r);
          r = new Vector(); r.add("molecularWeightInDaltons"); tm.addRow(r);
          r = new Vector(); r.add("sequenceInFastaFormat"); tm.addRow(r);
            
        }else if (nodeName.startsWith("PIR.SequenceConflict")){
          r = new Vector(); r.add("originalSequence"); tm.addRow(r);
          r = new Vector(); r.add("variantSequence"); tm.addRow(r);
          
        }else if (nodeName.startsWith("PIR.Gene")){
          r = new Vector(); r.add("id"); tm.addRow(r);
            
        }else if (nodeName.startsWith("PIR.MutagenesisSite")){
          r = new Vector(); r.add("originalSequence"); tm.addRow(r);
          r = new Vector(); r.add("variantSequence"); tm.addRow(r);
        }

      
    }
    
    private void populatePropTable(){
        cleanTable();
    }
    
    
    private void cleanTable(){
        if( getPropTable().getModel() instanceof DefaultTableModel ) {
            ((DefaultTableModel)getPropTable().getModel()).setNumRows(0);
        } else {
            int cols = getPropTable().getModel().getColumnCount();
            getPropTable().setModel( new DefaultTableModel(0, cols) );
        }
    }
    
    
    
    
    
    public javax.swing.JTable getPropTable() {
        return propTable;
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        propertyViewScrollPane = new javax.swing.JScrollPane();
        propTable = new javax.swing.JTable();

        setLayout(new java.awt.GridLayout(1, 0));

        propTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attribute", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        propertyViewScrollPane.setViewportView(propTable);

        add(propertyViewScrollPane);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable propTable;
    private javax.swing.JScrollPane propertyViewScrollPane;
    // End of variables declaration//GEN-END:variables
    
}
