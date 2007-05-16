
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
                            String url = "https://localhost:8443/wsrf/services/cagrid/CaTIES";
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
    
    
    private DefaultTableModel getMapTableModel(List array, HashMap colNamesMap, String[] keys, boolean[] alternates){
        Color[] rowColors = new Color[alternates.length];
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
        
        ColoredDefaultTableModel tb = new ColoredDefaultTableModel(rowV, colNames, rowColors);
        
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
    public ColoredDefaultTableModel(Vector rowV, Vector colNames, Color[] rColors){
        super(rowV, colNames);
        rowColors = rColors;
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
    	}
    	else
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
                
                // set an action handler also..
                
                String str = "<html> **REVISED DIAGNOSIS**:<BR>C. \"\"<?xml:namespace prefix = " +
                        "maw3 ns = \"\"http://maw3.duhs.duke.edu\"\" />USNCB RIGHT BREAST, NUMBER OF " +
                        "CORES FIVE, 9:00 POSITION\"\":<BR><BR>&nbsp;&nbsp;&nbsp; INVASIVE DUCTAL CARCINOMA." +
                        "<BR>&nbsp;&nbsp;&nbsp;&nbsp; NOTTINGHAM COMBINED HISTOLOGIC GRADE, AT LEAST 2 OF 3.<BR>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; TUBULE FORMATION SCORE: 3.<BR>&nbsp;&nbsp;&nbsp;" +
                        "&nbsp;&nbsp;&nbsp; NUCLEAR PLEOMORPHISM SCORE: 3.<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        " MITOTIC ACTIVITY SCORE: TOO LIMITED TO PERFORM.<BR>&nbsp;&nbsp;&nbsp; DUCTAL CARCINOMA " +
                        "IN-SITU, NUCLEAR GRADE 3.<BR>&nbsp;&nbsp;&nbsp; INTRADUCTAL PAPILLOMA.<BR><BR>COMMENT:&nbsp; " +
                        "Diagnosis C has been revised secondary to inadvertent verification<BR>with the incorrect " +
                        "biopsy site. The original diagnosis indicated the biopsy<BR>was obtained from the left side. " +
                        "The diagnosis has been changed reflect that<BR>the biopsy material was obtained from the right " +
                        "breast as indicated in the<BR>mammography report. Otherwise, the diagnosis remains unchanged." +
                        "&nbsp; <BR><BR>A. \"\"STNCB RIGHT BREAST, NUMBER OF CORES THREE WITH CALCIUM 10:00 POSITION\"\":" +
                        "<BR><BR>&nbsp;&nbsp;&nbsp; DUCTAL CARCINOMA IN SITU, MICROPAPILLARY TYPE, FOCALLY SUSPICIOUS FOR" +
                        "<BR>&nbsp;&nbsp;&nbsp; INVASION.<BR>&nbsp;&nbsp;&nbsp; NUCLEAR GRADE 3 WITH NECROSIS.<BR>&nbsp;" +
                        "&nbsp;&nbsp; MICROCALCIFICATIONS ASSOCIATED WITH DCIS.<BR><BR><BR><BR>B. \"\"STNCB RIGHT BREAST, " +
                        "NUMBER OF CORES FIVE WITHOUT CALCIUM 10:00 POSITION\"\":<BR><BR>&nbsp;&nbsp;&nbsp; DUCTAL " +
                        "CARCINOMA IN SITU, CRIBIFORM TYPE.<BR>&nbsp;&nbsp;&nbsp; NUCLEAR GRADE 3 WITH NECROSIS.<BR>" +
                        "&nbsp;&nbsp;&nbsp; NO INVASIVE CARCINOMA IS SEEN.<BR>&nbsp;&nbsp;&nbsp; NO CALCIFICATINS ARE SEEN." +
                        "<BR><BR><BR>C. *** SEE REVISION ***<BR>REPORT REVISED ON [__Apr.2004] AT 1519 BY [__unrecognizedName]" +
                        "<BR><BR>COMMENT:<BR>Ancillary studies have been requested on specimen C. The results will be<BR>issued " +
                        "in an addendum.<BR><BR>CI ADDENDUM 1:<BR>Please see Image Cytometry Report [__accession] for results of " +
                        "supplementary<BR>tests.<BR><br></html>";
                
                
                
            } else {
                origRet.setIcon(null);
            }
            
        } else {
            origRet.setIcon(null);
        }
        return origRet;
    }
    
    
}