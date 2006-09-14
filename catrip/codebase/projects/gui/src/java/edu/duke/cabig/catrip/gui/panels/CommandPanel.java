
package edu.duke.cabig.catrip.gui.panels;


import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.query.DCQLGenerator;
import edu.duke.cabig.catrip.gui.query.DCQLRegistry;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;
import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.fqe.engine.FederatedQueryEngine;
import gov.nih.nci.catrip.fqe.engine.FederatedQueryEngineImpl;
import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Panel which contains the Execute button. More commands can be added here.
 *
 * @author  Sanjeev Agarwal
 */
public class CommandPanel extends CPanel {
    // TODO - set the variable somewhere else...
    
    
    /** Creates new form CommandPanel */
    public CommandPanel() {
        initComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlOne = new javax.swing.JPanel();
        resultCountLbl = new javax.swing.JLabel();
        pnlTwo = new javax.swing.JPanel();
        ExecuteCommand = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout());

        pnlOne.setLayout(new java.awt.GridLayout());

        resultCountLbl.setFont(new java.awt.Font("Arial", 1, 14));
        pnlOne.add(resultCountLbl);

        add(pnlOne);

        ExecuteCommand.setText("Execute Query");
        ExecuteCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteCommandActionPerformed(evt);
            }
        });

        pnlTwo.add(ExecuteCommand);

        add(pnlTwo);

    }// </editor-fold>//GEN-END:initComponents
    
    private void ExecuteCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExecuteCommandActionPerformed
        getMainFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        if (GUIConstants.simpleGui){
            executeSimpleGuiQuery();
        }else {
            executeVisualGuiQuery();
        }
        
        getMainFrame().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_ExecuteCommandActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExecuteCommand;
    private javax.swing.JPanel pnlOne;
    private javax.swing.JPanel pnlTwo;
    private javax.swing.JLabel resultCountLbl;
    // End of variables declaration//GEN-END:variables
    
    private void executeSimpleGuiQuery(){
        
        if (SimpleGuiRegistry.isSimpleGuiChanged()){
            SimpleGuiRegistry.prepareForDcql();
        }
        executeVisualGuiQuery();
        
    }
    
    private void executeVisualGuiQuery(){
        try {
            /** can dump the generated XML into a file also. */
//            FileWriter fop = new FileWriter(new File("dcql.xml"), false);
//            fop.write(DCQLGenerator.getDCQLText());
//            fop.close();
            
            FederatedQueryEngine fqe = new FederatedQueryEngineImpl();
            DCQLQueryDocument dcqlQueryDocument = DCQLGenerator.getDCQLDocument();
            
            // print the formatted DCQL on console or to a log file.
//            XmlOptions xmlOptions = new XmlOptions();
//            xmlOptions.setSavePrettyPrint();
//            xmlOptions.setSavePrettyPrintIndent(4);
//            xmlOptions.setUseDefaultNamespace();
//            System.out.println("\n\n ========= Executing this DCQL ================\n");
//            System.out.println(DCQLGenerator.getDCQLText(xmlOptions));
//            System.out.println("\n\n ==============================================\n\n\n\n\n");
            
            // clean the result table before you even try the new query...
            getMainFrame().getOutputPanel().cleanResults();
            
            CQLQueryResults results = fqe.execute(dcqlQueryDocument);
            
            if ( (results == null) || (results.getObjectResult() == null) || (results.getObjectResult().length == 0) ){
                JOptionPane.showMessageDialog(getMainFrame(), "No results found. Please check your query.");
            } else {
                
                // TODO - put the client config files of the individual service also in the caTRIP-config.xml or the services-mapping file some how.
                CQLQueryResultsIterator iterator = new CQLQueryResultsIterator(results, new FileInputStream(new File(GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation() + File.separator +"client-config.wsdd")));
                
                ArrayList classBeanList = new ArrayList();
                
                ClassBean tmpObject = DCQLRegistry.getTargetNode().getAssociatedClassObject();
                
                while (iterator.hasNext()) {
                    Object obj = iterator.next();
                    ClassBean classBeanTmp = tmpObject.clone();
                    
                    ArrayList attributeList = classBeanTmp.getAttributes();
                    
                    for (int i = 0; i < attributeList.size(); i++) {
                        AttributeBean aBean = (AttributeBean) attributeList.get(i);
                        String attributeName = aBean.getAttributeName();
                        String methodName ="get"+attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
                        String value = "";
                        try{
                            Object attributeValue = ((Method)obj.getClass().getMethod(methodName)).invoke(obj);
                            if (attributeValue != null){
                                value = attributeValue.toString();
                            }
                        } catch (Exception eex) {
                            eex.printStackTrace();
                        }
                        aBean.setAttributeValue(value);
                    }
                    classBeanList.add(classBeanTmp);
                }
                
                resultCountLbl.setText("   Total Row Count : "+classBeanList.size());
                getMainFrame().getOutputPanel().setResults(classBeanList);
            }
        } catch (Exception ex) {
            resultCountLbl.setText(" ");
            ex.printStackTrace();
        }
    }
    
    
}
