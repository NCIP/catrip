package edu.duke.cabig.catrip.gui.querysharing;

import edu.duke.cabig.catrip.gui.wizard.MainFrame;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.catrip.cagrid.catripquery.CatripQuery;
import gov.nih.nci.catrip.cagrid.catripquery.client.QueryServiceClient;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.StringWriter;
import java.io.Writer;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.namespace.QName;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class QueryEditUI extends JPanel {
    
    private JLabel lblFirstName = null;
    private JLabel lblLastName = null;
    private JLabel lblDescription = null;
    private JLabel lblQueryName = null;
    private JTextField txtQueryName = null;
    private JTextArea txtDescription = null;
    private JTextField txtFirstName = null;
    private JTextField txtLastName = null;
    private JButton btnSave = null;
    
    private CatripQuery queryFields = new CatripQuery();  //  @jve:decl-index=0:
    private QueryServiceClient client;
    private JButton btnCancel = null;
    private JScrollPane descriptionScrollPane = null;
    private FilteredDocument document = null;
    private JTextPane jTextPane = null;
    
    
    
    // --------
    public MainFrame mainFrame;
    // --------
    
    
    
    
    
    
    /**
     * This method initializes
     *
     */
    public QueryEditUI() {
        super();
        initialize();
    }
    
    public QueryEditUI(String dcql) {
        super();
        initialize();
        queryFields.setDcql(dcql);
    }
    
    public QueryEditUI(DCQLQuery dcql) {
        super();
        initialize();
        queryFields.setDcql(getDCQLString(dcql));
    }
    
    
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    
    
    /**
     * This method initializes this
     *
     */
    private void initialize() {
        lblQueryName = new JLabel();
        lblQueryName.setBounds(new Rectangle(46, 27, 85, 26));
        lblQueryName.setText("Query Name : ");
        lblDescription = new JLabel();
        lblDescription.setBounds(new Rectangle(50, 61, 81, 20));
        lblDescription.setText("Description : ");
        lblLastName = new JLabel();
        lblLastName.setBounds(new Rectangle(298, 139, 81, 26));
        lblLastName.setText("Last Name : ");
        lblFirstName = new JLabel();
        lblFirstName.setBounds(new Rectangle(55, 139, 76, 26));
        lblFirstName.setText("First Name : ");
        document = new FilteredDocument();
        this.setLayout(null);
        this.setSize(new Dimension(584, 239));
        this.add(lblFirstName, null);
        this.add(lblLastName, null);
        this.add(lblDescription, null);
        this.add(lblQueryName, null);
        this.add(getTxtQueryName(), null);
        this.add(getTxtFirstName(), null);
        this.add(getTxtLastName(), null);
        this.add(getBtnSave(), null);
        this.add(getBtnCancel(), null);
        this.add(getDescriptionScrollPane(), null);
        // this.add(document, null);
        
    }
    
    /**
     * This method initializes txtQueryName
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtQueryName() {
        if (txtQueryName == null) {
            txtQueryName = new JTextField();
            txtQueryName.setBounds(new Rectangle(140, 27, 411, 26));
            txtQueryName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryFields.setName(txtQueryName.getText());
                }
            });
        }
        return txtQueryName;
    }
    
    /**
     * This method initializes txtDescription
     *
     * @return javax.swing.JTextArea
     */
    private JTextArea getTxtDescription() {
        if (txtDescription == null) {
            txtDescription = new JTextArea();
            txtDescription.setWrapStyleWord(true);
            txtDescription.setLineWrap(true);
            document.setLengthLimited(255);
            txtDescription.setDocument(document);
            txtDescription.setBorder(BorderFactory.createLineBorder(Color.black));
            txtDescription.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryFields.setDescription(txtDescription.getText());
                }
            });
        }
        return txtDescription;
    }
    
    /**
     * This method initializes txtFirstName
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtFirstName() {
        if (txtFirstName == null) {
            txtFirstName = new JTextField();
            txtFirstName.setBounds(new Rectangle(140, 139, 149, 26));
            txtFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryFields.setFirstName(txtFirstName.getText());
                }
            });
        }
        return txtFirstName;
    }
    
    /**
     * This method initializes txtLastName
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtLastName() {
        if (txtLastName == null) {
            txtLastName = new JTextField();
            txtLastName.setBounds(new Rectangle(386, 139, 167, 26));
            txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusLost(java.awt.event.FocusEvent e) {
                    queryFields.setLastName(txtLastName.getText());
                }
            });
        }
        return txtLastName;
    }
    
    /**
     * This method initializes btnSave
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnSave() {
        if (btnSave == null) {
            btnSave = new JButton();
            btnSave.setBounds(new Rectangle(367, 182, 62, 26));
            btnSave.setText("Save");
            btnSave.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // DEBUG
                    //System.out.println("Query Name :  " + queryFields.getName());
                    //System.out.println("Query Description :  " + queryFields.getDescription());
                    //System.out.println("First Name : " + queryFields.getFirstName());
                    //System.out.println("Last Name : " + queryFields.getLastName());
                    //System.out.println("dcql : " + queryFields.getDcql());
                    save();
                    close();
                    /// DEBUG END
                }
                
            });
        }
        return btnSave;
    }
    
    public CatripQuery getValues(){
        return queryFields;
    }
    
    private String getDCQLString(DCQLQuery dcqlQuery) {
        QName qname = new QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql");
        Writer w = new StringWriter();
        try {
            ObjectSerializer.serialize(w, dcqlQuery, qname);
        } catch (SerializationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("QueryEditUI:getDCQLString()");
        //System.out.println(w);
        //System.out.println("QueryEditUI:getDCQLString()");
        return w.toString().trim();
        
    }
    private void save() {
        String querySharingServiceUrl = System.getProperty("query.sharing.url"); // move this to gui config file..
        String serviceURI = "http://localhost:8181/wsrf/services/cagrid/QueryService"; // default
        if (querySharingServiceUrl != null){
            serviceURI = querySharingServiceUrl;
        }
        
        try {
            client = new QueryServiceClient(serviceURI);
            client.save(queryFields);
        } catch (MalformedURIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            System.out.println("Error while saving query.");
        }
    }
    
    /**
     * This method initializes btnCancel
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnCancel() {
        if (btnCancel == null) {
            btnCancel = new JButton();
            btnCancel.setBounds(new Rectangle(444, 182, 73, 26));
            btnCancel.setText("Cancel");
            btnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    close();
                }
            });
        }
        return btnCancel;
    }
    private void close(){
//		this.setVisible(false);
        JDialog parent = (JDialog)getRootPane().getParent();
        parent.dispose();
    }
    
    /**
     * This method initializes descriptionScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getDescriptionScrollPane() {
        if (descriptionScrollPane == null) {
            descriptionScrollPane = new JScrollPane();
            descriptionScrollPane.setBounds(new Rectangle(139, 58, 414, 74));
            descriptionScrollPane.setBorder(null);
            descriptionScrollPane.setViewportView(getTxtDescription());
        }
        return descriptionScrollPane;
    }
    
    /**
     * This method initializes jTextPane
     *
     * @return javax.swing.JTextPane
     */
    private JTextPane getJTextPane() {
        if (jTextPane == null) {
            jTextPane = new JTextPane();
            jTextPane.setBounds(new Rectangle(30, 168, 211, 56));
        }
        return jTextPane;
    }
    
    
    public static void main(String[] args) {
        QueryEditUI s = new QueryEditUI();
        //s.setOpaque(true);
        JFrame frame = new JFrame("Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(s,BorderLayout.CENTER);
        //  frame.pack();
        frame.setBounds(10,10,570, 250);//setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    
    
    
    
    
    
}  //  @jve:decl-index=0:visual-constraint="10,10"
