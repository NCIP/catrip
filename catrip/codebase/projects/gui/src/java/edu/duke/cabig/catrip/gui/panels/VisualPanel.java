
package edu.duke.cabig.catrip.gui.panels;

import edu.duke.cabig.catrip.gui.components.CPanel;
import edu.duke.cabig.catrip.gui.query.DCQLGenerator;
import edu.duke.cabig.catrip.gui.simplegui.SimpleGuiRegistry;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import edu.duke.cabig.catrip.gui.wizard.MainFrame;
import javax.swing.*;
import org.apache.xmlbeans.XmlOptions;


/**
 * Panel holding Visual Query Designer and the DCQL viewer as different Tabs.
 * More tabs can be added by adding Panels into this.
 *
 * @author  Sanjeev Agarwal
 */
public class VisualPanel extends CPanel {
    
    /** Creates new form VisualPanel */
    public VisualPanel() {
        initComponents();
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        cQLDesignerPanel.setMainFrame(mainFrame);
        visualQueryDesignerPanel.setMainFrame(mainFrame);
        getSimpleSearchPanel().setMainFrame(mainFrame);
    }
    
    
    public edu.duke.cabig.catrip.gui.panels.CQLDesignerPanel getCQLDesignerPanel() {
        return cQLDesignerPanel;
    }
    
    public edu.duke.cabig.catrip.gui.panels.VisualQueryDesignerPanel getVisualQueryDesignerPanel() {
        return visualQueryDesignerPanel;
    }
    
    public edu.duke.cabig.catrip.gui.panels.SimpleSearchPanel getSimpleSearchPanel() {
        return simpleSearchPanel;
    }
    
    public javax.swing.JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        tabbedPane = new javax.swing.JTabbedPane();
        visualQueryDesignerPanel = new edu.duke.cabig.catrip.gui.panels.VisualQueryDesignerPanel();
        cQLDesignerPanel = new edu.duke.cabig.catrip.gui.panels.CQLDesignerPanel();
        simpleSearchPanel = new edu.duke.cabig.catrip.gui.panels.SimpleSearchPanel();

        setLayout(new java.awt.GridLayout(1, 0));

        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle"); // NOI18N
        tabbedPane.addTab(bundle.getString("VISUAL_PANEL_TAB_ONE"), visualQueryDesignerPanel); // NOI18N

        tabbedPane.addTab(bundle.getString("VISUAL_PANEL_TAB_TWO"), cQLDesignerPanel); // NOI18N

        tabbedPane.addTab("Simple Query Interface", simpleSearchPanel);

        add(tabbedPane);

    }// </editor-fold>//GEN-END:initComponents
    
    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        // parse the DCQL and show here...
        
        int id = getTabbedPane().getSelectedIndex();
        if (id == 1){ // it is the dcql tab.. now show the DCQL..
            XmlOptions xmlOptions = new XmlOptions();
            xmlOptions.setSavePrettyPrint();
            xmlOptions.setSavePrettyPrintIndent(4);
            xmlOptions.setUseDefaultNamespace();
            
            // if the simple gui was changed.. than show the DCQL from simple gui.. after preparing the SimpleGuiRegistry for DCQL.
            if (GUIConstants.simpleGui && SimpleGuiRegistry.isSimpleGuiChanged()){ 
                SimpleGuiRegistry.prepareForDcql();
            }
            
            getCQLDesignerPanel().setDcqlQueryText(DCQLGenerator.getDCQLText(xmlOptions));
        }
    }//GEN-LAST:event_tabbedPaneStateChanged
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.duke.cabig.catrip.gui.panels.CQLDesignerPanel cQLDesignerPanel;
    private edu.duke.cabig.catrip.gui.panels.SimpleSearchPanel simpleSearchPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private edu.duke.cabig.catrip.gui.panels.VisualQueryDesignerPanel visualQueryDesignerPanel;
    // End of variables declaration//GEN-END:variables
    
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame jf = new JFrame("Testing this panel");
                VisualPanel vp = new VisualPanel();
                VisualQueryDesignerPanel vv = new VisualQueryDesignerPanel();
                vp.getVisualQueryDesignerPanel().setDnDGraphView(new ListServicesPanel());
                jf.getContentPane().add(vp);
                jf.setBounds(10,10,400,200);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setVisible(true);
            }
        });
    }
    
    
    
    
    
}
