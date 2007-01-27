package edu.duke.cabig.catrip.gui.querysharing;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.CDEComboboxBeanComparator;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.PreferredHeightMarginBorderBoxLayout;
import edu.duke.cabig.catrip.gui.simplegui.CDEComboboxBean;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.wizard.MainFrame;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;
import gov.nih.nci.catrip.cagrid.catripquery.server.ClassDb;
import gov.nih.nci.catrip.cagrid.catripquery.server.QueryDb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingConstants;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

public class QueryServiceUI extends JPanel {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton btnSearch = null;
    private JLabel lblFirstName = null;
    private JTextField txtFirstName = null;
    private JScrollPane resultsScrollPane = null;
    private JTable resultTable = null;
    private JTextField txtLastName = null;
    private JLabel lblLastName = null;
    private JPanel metaDataPanel = null;
    private JLabel lblDescriptionl = null;
    private JLabel lblQueryName = null;
    private JTextField txtQueryName = null;
    private JTextField txtDescription = null;
    private JLabel lblFilter = null;
    private JLabel lblConceptl = null;
    private JButton btnAddFilter = null;
    private JLabel lblQueryResult = null;
    
    Collection<QueryFilterRowPanel> filterCollection = new Vector<QueryFilterRowPanel>();  //  @jve:decl-index=0:
    //Collection<ClassDb> classCollection = null;
    // private DefaultTableModel conceptCodeTableModel = new DefaultTableModel();
    private QueryDb queryData = new QueryDb();  //  @jve:decl-index=0:
    private DefaultTableModel tableModel = null ;
    private JPanel filterPanel = null;
    private JScrollPane jScrollPane1 = null;
    
    
    
    
    // --------
    public MainFrame mainFrame;
    private ArrayList<CDEComboboxBean> filterList = new ArrayList<CDEComboboxBean>(500);
    // --------
    
    
    
    
    
    /**
     * This method initializes
     *
     */
    public QueryServiceUI() {
        super();
        initialize();
        init();
    }
    
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
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
//        for (int i = 0; i < attributeList.size(); i++) {
//            getCdeCombo().addItem(attributeList.get(i));
//        }
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method initializes this
     *
     */
    private void initialize() {
        lblQueryResult = new JLabel();
        lblQueryResult.setBounds(new Rectangle(592, 8, 127, 16));
        lblQueryResult.setText("Queries Results :");
        lblConceptl = new JLabel();
        lblConceptl.setText("Concept");
        lblConceptl.setBounds(new Rectangle(116, 197, 47, 16));
        lblFilter = new JLabel();
        lblFilter.setText("Filter");
        lblFilter.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
        lblFilter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFilter.setBounds(new Rectangle(19, 178, 92, 26));
        lblLastName = new JLabel();
        lblLastName.setText("Last Name :");
        lblLastName.setBounds(new Rectangle(288, 93, 83, 20));
        lblFirstName = new JLabel();
        lblFirstName.setText("First Name : ");
        lblFirstName.setBounds(new Rectangle(34, 92, 74, 20));
        this.setLayout(null);
        this.setSize(new Dimension(1256, 752));
        this.add(getBtnSearch(), null);
        this.add(getResultsScrollPane(), null);
        this.add(getMetaDataPanel(), null);
        this.add(getBtnAddFilter(), null);
        this.add(lblQueryResult, null);
        
        this.add(lblFilter, null);
        this.add(lblConceptl, null);
        this.add(getJScrollPane1(), null);
    }
    
    /**
     * This method initializes btnSearch
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnSearch() {
        if (btnSearch == null) {
            btnSearch = new JButton();
            btnSearch.setBounds(new Rectangle(356, 185, 85, 23));
            btnSearch.setText("Search");
            btnSearch.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
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
                        populateTable(QueryServiceClient.search(cqlQuery));
                   } catch (Exception qe) {
                        qe.printStackTrace();
                    }
                }
                
            }
            );
        }
        return btnSearch;
    }
    private QueryFilterRowPanel getFilterRowPanel(){
//        QueryFilterRowPanel filterRow = new QueryFilterRowPanel(this);
        QueryFilterRowPanel filterRow = new QueryFilterRowPanel(this, filterList);
        filterRow.setPreferredSize(new Dimension(538, 30));
        return filterRow;
    }
    @SuppressWarnings("unchecked")
    private void populateTable(Vector collection) {
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
            
            // create another vector, this one will be used as a row in the table
            Vector<Serializable> rowData = new Vector<Serializable> () ;
            
            // populate this row with the data out of the objects, using wrapper classes where primitive
            // data types are used.
            rowData.add(e.getName()) ;
            rowData.add(e.getFirstName()+ " " + e.getLastName()) ;
            rowData.add(e.getDescription()) ;
            rowData.add("View/Modify") ;
            rowData.add("Run") ;
            // add row to model
            tableModel.addRow(rowData) ;
        }
        resultTable.getColumnModel();
        resultTable.setModel(tableModel);
        new ButtonColumn(resultTable, 3, collection, false);
        new ButtonColumn(resultTable, 4, collection, true);
        
        
    }
    
    /**
     * This method initializes txtUserName
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtFirstName() {
        if (txtFirstName == null) {
            txtFirstName = new JTextField();
            txtFirstName.setBounds(new Rectangle(110, 92, 157, 20));
            txtFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryData.setFirstName(txtFirstName.getText());				}
            });
        }
        return txtFirstName;
    }
    
    /**
     * This method initializes resultsScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getResultsScrollPane() {
        if (resultsScrollPane == null) {
            resultsScrollPane = new JScrollPane();
            resultsScrollPane.setBounds(new Rectangle(594, 30, 575, 356));
            resultsScrollPane.setViewportView(getResultTable());
        }
        return resultsScrollPane;
    }
    
    /**
     * This method initializes resultTable
     *
     * @return javax.swing.JTable
     */
    private JTable getResultTable() {
        if (resultTable == null) {
            resultTable = new ButtonTable();
            
        }
        return resultTable;
    }
    
    /**
     * This method initializes txtLastName
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtLastName() {
        if (txtLastName == null) {
            txtLastName = new JTextField();
            txtLastName.setBounds(new Rectangle(379, 93, 157, 20));
            txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryData.setLastName(txtLastName.getText());
                }
            });
        }
        return txtLastName;
    }
    
    /**
     * This method initializes metaDataPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getMetaDataPanel() {
        if (metaDataPanel == null) {
            lblQueryName = new JLabel();
            lblQueryName.setBounds(new Rectangle(26, 15, 82, 20));
            lblQueryName.setText("Query Name : ");
            lblDescriptionl = new JLabel();
            lblDescriptionl.setBounds(new Rectangle(28, 52, 80, 20));
            lblDescriptionl.setText("Description :");
            metaDataPanel = new JPanel();
            metaDataPanel.setLayout(null);
            metaDataPanel.setBounds(new Rectangle(16, 30, 564, 134));
            metaDataPanel.add(lblFirstName, null);
            metaDataPanel.add(getTxtFirstName(), null);
            metaDataPanel.add(lblLastName, null);
            metaDataPanel.add(getTxtLastName(), null);
            metaDataPanel.add(lblDescriptionl, null);
            metaDataPanel.add(lblQueryName, null);
            metaDataPanel.add(getTxtQueryName(), null);
            metaDataPanel.add(getTxtDescription(), null);
            metaDataPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        return metaDataPanel;
    }
    
    /**
     * This method initializes txtQueryName
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtQueryName() {
        if (txtQueryName == null) {
            txtQueryName = new JTextField();
            txtQueryName.setBounds(new Rectangle(110, 16, 426, 20));
            // txtQueryName.setText("not");
            txtQueryName.setName("");
            txtQueryName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    System.out.println(">" + txtQueryName.getText() +"<");
                    queryData.setName(txtQueryName.getText());
                }
            });
        }
        return txtQueryName;
    }
    
    /**
     * This method initializes txtDescription
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtDescription() {
        if (txtDescription == null) {
            txtDescription = new JTextField();
            txtDescription.setBounds(new Rectangle(110, 52, 426, 20));
            // txtDescription.setText("query");
            txtDescription.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryData.setDescription(txtDescription.getText());
                }
            });
        }
        return txtDescription;
    }
    
    /**
     * This method initializes btnAddFilter
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnAddFilter() {
        if (btnAddFilter == null) {
            btnAddFilter = new JButton();
            btnAddFilter.setBounds(new Rectangle(470, 185, 99, 24));
            btnAddFilter.setText("Add Filter");
            btnAddFilter.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    addFilterRow();
                }
            });
        }
        return btnAddFilter;
    }
    private void addFilterRow(){
        QueryFilterRowPanel filterRow = getFilterRowPanel();
        filterCollection.add(filterRow);
//		for (Iterator iter = filterCollection.iterator(); iter.hasNext();) {
//			FilterRowPanel element = (FilterRowPanel) iter.next();
//			//System.out.println("adding " + element.getSelectedClass().getName());
//		}
        getFilterPanel().add(filterRow,null);
        getFilterPanel().revalidate();
        getFilterPanel().repaint();
        
    }
    /**
     * This method initializes filterPanel
     *
     * @return javax.swing.JPanel
     */
    public JPanel getFilterPanel() {
        if (filterPanel == null) {
            filterPanel = new JPanel();
            PreferredHeightMarginBorderBoxLayout layout = new PreferredHeightMarginBorderBoxLayout(getFilterPanel(), PreferredHeightMarginBorderBoxLayout.Y_AXIS);
            filterPanel.setLayout(layout);
            //filterPanel.setLayout(new BoxLayout(getFilterPanel(), BoxLayout.Y_AXIS));
        }
        return filterPanel;
    }
    
    /**
     * This method initializes jScrollPane1
     *
     * @return javax.swing.JScrollPane
     */
    public JScrollPane getJScrollPane1() {
        if (jScrollPane1 == null) {
            jScrollPane1 = new JScrollPane();
            jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane1.setViewportView(getFilterPanel());
            jScrollPane1.setBounds(new Rectangle(16, 215, 564, 159));
        }
        return jScrollPane1;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        QueryServiceUI s = new QueryServiceUI();
        //s.setOpaque(true);
        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(s,BorderLayout.CENTER);
        //  frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    
    // convert the dcql string into object
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
                //System.out.println("dcql : " + dcql);
                executeDcql(dcql); // execute the dcql...
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
    
    
}  //  @jve:decl-index=0:visual-constraint="-7,7"
