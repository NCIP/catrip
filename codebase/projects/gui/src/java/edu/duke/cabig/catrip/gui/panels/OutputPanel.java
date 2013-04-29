/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.query.OnDemandCQLExecutor;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import edu.duke.cabig.catrip.gui.util.LargeTextDialog;
import edu.duke.cabig.catrip.gui.util.TableSorter;
import java.util.Set;

/**
 * Out Panel to show the results from the DCQL Query execution.
 * It has a dynamic Table Model, where the Table columns are shown based on the Target object selected on the Graph.
 *
 * @author  Sanjeev Agarwal
 */
public class OutputPanel extends CPanel {
    private final boolean SORT_ON = false;
    /** Creates new form OutputPanel */
    public OutputPanel() {
        initComponents();
        if (SORT_ON){
            //Set up tool tips for column headers.
            getOutputTable().getTableHeader().setToolTipText(
                    "Click to specify sorting; Control-Click to specify secondary sorting");
        }
        outputTable.addMouseListener(new java.awt.event.MouseListener() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2)  {
                    String columnName = outputTable.getColumnName(outputTable.getSelectedColumn());
                    String strValue = (String)outputTable.getValueAt(outputTable.getSelectedRow(), outputTable.getSelectedColumn());
                    
                    //boolean bigText = (strValue.length() > GUIConstants.LARGE_TEXT_LIMIT)?true:false;
                    boolean bigText = strValue.startsWith("bigId:")?true:false;
                    if (bigText){
                        
                        String report = "";
                        
                        try {
                            //LargeTextDialog  f = new LargeTextDialog(columnName, strValue);
                            //get id data from caTIES service
                            
                            // get the service URL from table model for that column..
                            String url = "https://localhost:8443/wsrf/services/cagrid/CaTIES";
                            ColoredDefaultTableModel colModel = (ColoredDefaultTableModel) outputTable.getModel();
                            url = colModel.getServiceURL( outputTable.getSelectedColumn() );
                            
                            Set results = OnDemandCQLExecutor.execute(url,OnDemandCQLExecutor.getCaTIESCQL(strValue),"documentText");
                            for (Object i:results) {
                                report = i.toString();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        LargeTextDialog  f = new LargeTextDialog(columnName, report);
                        f.setLocationRelativeTo(null);
                        // f.setLocation(e.getX(), e.getY());
                        f.setVisible(true);
                    }
                }
            }
            public void mousePressed(java.awt.event.MouseEvent e) {
            }
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }
            public void mouseEntered(java.awt.event.MouseEvent e) {
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
            }
        });
    }
    
    public javax.swing.JTable getOutputTable() {
        return outputTable;
    }
    
    public void setResults(ArrayList resultArray){
        Color[] rowColors = new Color[resultArray.size()];
        for (int i = 0; i < rowColors.length; i++) {
            rowColors[i]=Color.white; // rowColors[i]=new Color(235, 235, 235);
            if (i%2 == 0){
                rowColors[i]=new Color(235, 235, 235);
            }
        }
        
        ((ColoredJTable)getOutputTable()).setModel(getTableModel(resultArray), rowColors);
    }
    
    public void setMapResults(List resultArray, HashMap colNamesMap, String[] keys){
        ColoredDefaultTableModel colModel = (ColoredDefaultTableModel)getMapTableModel(resultArray, colNamesMap, keys);
        
        ((ColoredJTable)getOutputTable()).setModel(colModel, colModel.getRowColors());
        
        TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
        int numColumns = getOutputTable().getColumnCount();
        for (int i = 0; i < numColumns; i++) {
            getOutputTable().getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
    
    public void setMapResults(List resultArray, HashMap colNamesMap, String[] keys, boolean[] alternate){
        ColoredDefaultTableModel colModel = (ColoredDefaultTableModel)getMapTableModel(resultArray, colNamesMap, keys, alternate);
        
        ((ColoredJTable)getOutputTable()).setModel(colModel, colModel.getRowColors());
        
        TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
        int numColumns = getOutputTable().getColumnCount();
        for (int i = 0; i < numColumns; i++) {
            getOutputTable().getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        
    }
    
    
    // show results with column binding to the service URLs for those CDEs.
    public void setMapResults(List resultArray, HashMap colNamesMap, String[] keys, boolean[] alternate, HashMap serviceURLsMap){
        ColoredDefaultTableModel colModel = (ColoredDefaultTableModel)getMapTableModel(resultArray, colNamesMap, keys, alternate, serviceURLsMap);
        
        ((ColoredJTable)getOutputTable()).setModel(colModel, colModel.getRowColors());
        
        TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
        int numColumns = getOutputTable().getColumnCount();
        for (int i = 0; i < numColumns; i++) {
            getOutputTable().getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
    
    
    
    
    public void cleanResults(){
        getOutputTable().setModel(getTableModel());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        outputTable = new javax.swing.JTable();
        outputTable = new ColoredJTable();

        setLayout(new java.awt.GridLayout(1, 0));

        outputTable.setModel(getTableModel());
        jScrollPane1.setViewportView(outputTable);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/a11y/a11yBundle"); // NOI18N
        outputTable.getAccessibleContext().setAccessibleName(bundle.getString("edu.duke.cabig.catrip.gui.panels.OutputPanel.outputTable.name")); // NOI18N
        outputTable.getAccessibleContext().setAccessibleDescription(bundle.getString("edu.duke.cabig.catrip.gui.panels.OutputPanel.outputTable.description")); // NOI18N
        outputTable.getAccessibleContext().setAccessibleParent(this);

        add(jScrollPane1);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable outputTable;
    // End of variables declaration//GEN-END:variables
    
    private DefaultTableModel getTableModel(){
        return new javax.swing.table.DefaultTableModel();
    }
    
    /** This method assumes that the Target object and the results are an instance of type ClassBean. */
    private DefaultTableModel getTableModel(ArrayList array){
        Vector rowV = new Vector();
        Vector colNames = null;
        
        for (int i = 0; i < array.size(); i++){  // TODO - restrict it to only 100 rows or so.
            
            ClassBean b = (ClassBean) array.get(i);
            ArrayList at = b.getAttributes();
            Vector colV = new Vector();
            colNames = new Vector();
            for (int j = 0; j< at.size();j++){
                AttributeBean att = (AttributeBean)at.get(j);
                colV.add(att.getAttributeValue());
                colNames.add(att.getCDEName()); // don't do it everytime...
            }
            rowV.add(colV);
            
        }
        
        DefaultTableModel tb = new javax.swing.table.DefaultTableModel(rowV, colNames){
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        
        return tb;
    }
    
    
    private DefaultTableModel getMapTableModel(List array, HashMap colNamesMap, String[] keys){
        boolean[] alternates = new boolean[array.size()];
        
        for (int i = 0; i < alternates.length; i++) {
            if (i % 2 == 0){
                alternates[i]=true;
            } else {
                alternates[i]=false;
            }
        }
        
        return getMapTableModel(array, colNamesMap, keys, alternates);
    }
    
    private DefaultTableModel getMapTableModel(List array, HashMap colNamesMap, String[] keys, boolean[] alternates ){
        HashMap serviceURLsMap = new HashMap();
        for (int i = 0; i < keys.length; i++) {
            serviceURLsMap.put(keys[i],"ServiceUrl");
        }
        return getMapTableModel( array,  colNamesMap,  keys,  alternates,  serviceURLsMap );
    }
    
    private DefaultTableModel getMapTableModel(List array, HashMap colNamesMap, String[] keys, boolean[] alternates, HashMap serviceURLsMap ){
        Color[] rowColors = new Color[alternates.length];
        String[] serviceURLs = new String[keys.length];
        
        for (int i = 0; i < alternates.length; i++) {
            if (alternates[i]){
                rowColors[i]=new Color(235, 235, 235);
            } else {
//                rowColors[i]=Color.white; // white is already the default color of JTable.
            }
        }
        
        Vector rowV = new Vector();
        Vector colNames = new Vector();
        
        for (int i = 0; i < keys.length; i++) {
            colNames.add(colNamesMap.get(keys[i]));
            serviceURLs[i] = (String)serviceURLsMap.get(keys[i]);
        }
        
        for (int i = 0; i < array.size(); i++){
            Object obj = array.get(i);
            
            HashMap map = (HashMap)obj;
            Vector colV = new Vector();
            for (int j = 0; j< keys.length;j++){
                String value = (String)map.get(keys[j]);
                String dateFormatExp = "\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d[:]\\d\\d[.]\\d\\d\\d[-]\\d\\d[:]\\d\\d";
                boolean b = false;
                if (value!=null && !value.equals("")){
                    b = Pattern.matches(dateFormatExp, value);
                }
                try{
                    if (b){
                        Date javaDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")).parse(value);
                        value = new SimpleDateFormat("MMM-yyyy").format(javaDate);
                    }
                } catch (java.text.ParseException pe){
                    pe.printStackTrace();
                    // eat the exception here..
                }
                colV.add(value);
            }
            rowV.add(colV);
        }
        
//        ColoredDefaultTableModel tb = new ColoredDefaultTableModel(rowV, colNames, rowColors);
        ColoredDefaultTableModel tb = new ColoredDefaultTableModel(rowV, colNames, rowColors, serviceURLs);
        
//        DefaultTableModel tb = new javax.swing.table.DefaultTableModel(rowV, colNames){
//            public boolean isCellEditable(int row, int col) {
//                return false;
//            }
//        };
        
        return tb;
    }
    
}


class ColoredDefaultTableModel extends javax.swing.table.DefaultTableModel{
    private Color[] rowColors;
    private String[] serviceURLs;
    public ColoredDefaultTableModel(Vector rowV, Vector colNames, Color[] rColors){
        super(rowV, colNames);
        rowColors = rColors;
    }
    public ColoredDefaultTableModel(Vector rowV, Vector colNames, Color[] rColors, String[] sURLs){
        super(rowV, colNames);
        rowColors = rColors;
        serviceURLs =  sURLs;
    }
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    public void setRowColors(Color[] colors){
        rowColors = colors;
    }
    public Color[] getRowColors(){
        return rowColors;
    }
    
    public String getServiceURL(int i){
        return serviceURLs[i];
    }
}



class ColoredJTable extends JTable {
    private Color[] rowColors;
    private final boolean SORT_ON = false;
    
    public void setModel(TableModel model, Color[] rColors ){
        this.setRowColors(rColors);
        if (SORT_ON){
            TableSorter sorter = new TableSorter(model);
            sorter.setTableHeader(this.getTableHeader());
            super.setModel(sorter);
        } else
            super.setModel(model);
    }
    
    public Color  getRowColor(int row){
        return rowColors[row];
    }
    
    public void setRowColors(Color[] colors){
        rowColors = colors;
    }
    
    public Component prepareRenderer(TableCellRenderer renderer,
            int rowIndex, int vColIndex) {
        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
        c.setBackground(rowColors[rowIndex]);
        return c;
    }
    
}



class TextWithIconCellRenderer extends DefaultTableCellRenderer {
    
    
    public Component getTableCellRendererComponent(JTable tblDataTable, Object value, boolean isSelected, boolean hasFocus, int markedRow, int col){
        JLabel origRet=(JLabel)super.getTableCellRendererComponent(tblDataTable,value,isSelected,hasFocus,markedRow,col);
        
        if (value != null ) {
            String strValue = value.toString().trim();
            //boolean bigText = (strValue.length() > GUIConstants.LARGE_TEXT_LIMIT)?true:false;
            boolean bigText = strValue.startsWith("bigId:")?true:false;
            if (bigText){
                origRet.setIcon(new javax.swing.ImageIcon(GUIConstants.LARGE_TEXT_ICON) );
                origRet.setHorizontalTextPosition(SwingConstants.RIGHT);
                origRet.setVerticalTextPosition(SwingConstants.CENTER);
                origRet.setHorizontalAlignment(SwingConstants.LEFT);
                origRet.setVerticalAlignment(SwingConstants.CENTER);
            } else {
                origRet.setIcon(null);
            }
            
        } else {
            origRet.setIcon(null);
        }
        return origRet;
    }
    
    
}