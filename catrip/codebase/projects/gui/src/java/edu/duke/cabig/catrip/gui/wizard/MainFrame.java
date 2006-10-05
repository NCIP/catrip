
package edu.duke.cabig.catrip.gui.wizard;


import edu.duke.cabig.catrip.gui.components.CJDialog;
import edu.duke.cabig.catrip.gui.components.CJFrame;
import edu.duke.cabig.catrip.gui.panels.*;
import edu.duke.cabig.catrip.gui.util.HTMLResultExporter;

/**
 * Main Window containing all the different Panels.
 *
 * @author  Sanjeev Agarwal
 */
public class MainFrame extends CJFrame {
    
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        buildView();
    }
    
    
    private void buildView(){
        getVisualPanel().getVisualQueryDesignerPanel().setDnDGraphView(getListServicesPanel());
        repaint();
        
        //set main frame in all the panels..
        getVisualPanel().setMainFrame(this);
        commandPanel.setMainFrame(this);
        listServicesPanel.setMainFrame(this);
        outputPanel.setMainFrame(this);
        propertiesPanel.setMainFrame(this);
        
        setExtendedState(MAXIMIZED_BOTH);
        
        
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        rootpanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        listServicesPanel = new edu.duke.cabig.catrip.gui.panels.ListServicesPanel();
        propertiesPanel = new edu.duke.cabig.catrip.gui.panels.PropertiesPanel();
        rightPanel = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        commandPanel = new edu.duke.cabig.catrip.gui.panels.CommandPanel();
        visualPanel = new edu.duke.cabig.catrip.gui.panels.VisualPanel();
        outputPanel = new edu.duke.cabig.catrip.gui.panels.OutputPanel();
        menuBar = new javax.swing.JMenuBar();
        servicesMenu = new javax.swing.JMenu();
        add = new javax.swing.JMenuItem();
        delete = new javax.swing.JMenuItem();
        queryMenu = new javax.swing.JMenu();
        retrieve = new javax.swing.JMenuItem();
        create = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        deleteQuery = new javax.swing.JMenuItem();
        execute = new javax.swing.JMenuItem();
        preferencesMenu = new javax.swing.JMenu();
        addSearchEngineServices = new javax.swing.JMenuItem();
        selectSearchEngine = new javax.swing.JMenuItem();
        addSearchEngineURL = new javax.swing.JMenuItem();
        resultMenu = new javax.swing.JMenu();
        exportResultHtml = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle"); // NOI18N
        setTitle(bundle.getString("TITLE_MAIN_FRAME")); // NOI18N
        rootpanel.setPreferredSize(new java.awt.Dimension(1024, 768));
        jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setDividerSize(7);
        jSplitPane1.setFocusable(false);
        jSplitPane1.setOneTouchExpandable(true);
        jSplitPane2.setDividerLocation(450);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setLeftComponent(listServicesPanel);

        jSplitPane2.setRightComponent(propertiesPanel);

        org.jdesktop.layout.GroupLayout leftPanelLayout = new org.jdesktop.layout.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );
        jSplitPane1.setLeftComponent(leftPanel);

        jSplitPane3.setDividerLocation(540);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(commandPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .add(visualPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(506, Short.MAX_VALUE)
                .add(commandPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(visualPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .add(34, 34, 34))
        );
        jSplitPane3.setTopComponent(jPanel1);

        jSplitPane3.setRightComponent(outputPanel);

        org.jdesktop.layout.GroupLayout rightPanelLayout = new org.jdesktop.layout.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 482, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSplitPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );
        jSplitPane1.setRightComponent(rightPanel);

        org.jdesktop.layout.GroupLayout rootpanelLayout = new org.jdesktop.layout.GroupLayout(rootpanel);
        rootpanel.setLayout(rootpanelLayout);
        rootpanelLayout.setHorizontalGroup(
            rootpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(rootpanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 741, Short.MAX_VALUE)
                .addContainerGap())
        );
        rootpanelLayout.setVerticalGroup(
            rootpanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(rootpanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jSplitPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap())
        );
        getContentPane().add(rootpanel);

        servicesMenu.setText(bundle.getString("MAIN_FRAME_WIZARD_SERVICE_MENU")); // NOI18N
        add.setText(bundle.getString("MAIN_FRAME_WIZARD_SERVICE_MENU_ADD")); // NOI18N
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        servicesMenu.add(add);

        delete.setText(bundle.getString("MAIN_FRAME_WIZARD_SERVICE_MENU_DELETE")); // NOI18N
        servicesMenu.add(delete);

        menuBar.add(servicesMenu);

        queryMenu.setText(bundle.getString("MAIN_FRAME_WIZARD_QUERY_MENU")); // NOI18N
        retrieve.setText(bundle.getString("MAIN_FRAME_WIZARD_QUERY_MENU_RETRIEVE")); // NOI18N
        queryMenu.add(retrieve);

        create.setText(bundle.getString("MAIN_FRAME_WIZARD_QUERY_MENU_CREATE")); // NOI18N
        queryMenu.add(create);

        save.setText(bundle.getString("MAIN_FRAME_WIZARD_QUERY_MENU_SAVE")); // NOI18N
        queryMenu.add(save);

        deleteQuery.setText(bundle.getString("MAIN_FRAME_WIZARD_QUERY_MENU_DELETE")); // NOI18N
        queryMenu.add(deleteQuery);

        execute.setText(bundle.getString("MAIN_FRAME_WIZARD_QUERY_MENU_EXECUTE")); // NOI18N
        queryMenu.add(execute);

        menuBar.add(queryMenu);

        preferencesMenu.setText(bundle.getString("MAIN_FRAME_WIZARD_PREFERENCES_MENU")); // NOI18N
        addSearchEngineServices.setText(bundle.getString("MAIN_FRAME_WIZARD_PREFERENCES_MENU_ADD")); // NOI18N
        addSearchEngineServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSearchEngineServicesActionPerformed(evt);
            }
        });

        preferencesMenu.add(addSearchEngineServices);

        selectSearchEngine.setText(bundle.getString("MAIN_FRAME_WIZARD_PREFERENCES_MENU_SELECT")); // NOI18N
        selectSearchEngine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSearchEngineActionPerformed(evt);
            }
        });

        preferencesMenu.add(selectSearchEngine);

        addSearchEngineURL.setText(bundle.getString("MAIN_FRAME_WIZARD_PREFERENCES_MENU_ADD_URL")); // NOI18N
        addSearchEngineURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSearchEngineURLActionPerformed(evt);
            }
        });

        preferencesMenu.add(addSearchEngineURL);

        menuBar.add(preferencesMenu);

        resultMenu.setText("Results");
        exportResultHtml.setText("Export Results to HTML");
        exportResultHtml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportResultHtmlActionPerformed(evt);
            }
        });

        resultMenu.add(exportResultHtml);

        menuBar.add(resultMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void exportResultHtmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportResultHtmlActionPerformed
        try {
            HTMLResultExporter.exportToHtml( getOutputPanel().getOutputTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_exportResultHtmlActionPerformed
    
    private void addSearchEngineURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSearchEngineURLActionPerformed
        
        QueryEngineServiceAddURLPanel ws= new QueryEngineServiceAddURLPanel();
        CJDialog jd = new CJDialog(this, "Add Search Engine Service URL");
        jd.add(ws);
        jd.setBounds(10,10,650,220);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
        
        
    }//GEN-LAST:event_addSearchEngineURLActionPerformed
    
    private void selectSearchEngineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSearchEngineActionPerformed
        
        QueryEngineServiceSelectPanel ws= new QueryEngineServiceSelectPanel();
        CJDialog jd = new CJDialog(this, "Search Services");
        jd.add(ws);
        jd.setBounds(10,10,650,142);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
    }//GEN-LAST:event_selectSearchEngineActionPerformed
    
    private void addSearchEngineServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSearchEngineServicesActionPerformed
        
        QueryEngineServiceSearchPanel ws= new QueryEngineServiceSearchPanel();
        CJDialog jd = new CJDialog(this, "Search Services");
        jd.add(ws);
        jd.setBounds(10,10,850,450);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
    }//GEN-LAST:event_addSearchEngineServicesActionPerformed
    
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // here open this panel into a new Dialog instead of a JFrame... and set the panel parent type to Dialog.
        
        CJDialog jd = new CJDialog(this, "Search Services");
        ServicesSearchPanel ws= new ServicesSearchPanel(jd);
        jd.add(ws);
        jd.setBounds(10,10,850,450);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
        
    }//GEN-LAST:event_addActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                MainFrame ws= new MainFrame();
//                ws.center();
                new MainFrame().setVisible(true);
                
            }
        });
    }
    
    public edu.duke.cabig.catrip.gui.panels.CommandPanel getCommandPanel() {
        return commandPanel;
    }
    
    public edu.duke.cabig.catrip.gui.panels.ListServicesPanel getListServicesPanel() {
        return listServicesPanel;
    }
    
    public edu.duke.cabig.catrip.gui.panels.OutputPanel getOutputPanel() {
        return outputPanel;
    }
    
    public edu.duke.cabig.catrip.gui.panels.PropertiesPanel getPropertiesPanel() {
        return propertiesPanel;
    }
    
    public edu.duke.cabig.catrip.gui.panels.VisualPanel getVisualPanel() {
        return visualPanel;
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem add;
    private javax.swing.JMenuItem addSearchEngineServices;
    private javax.swing.JMenuItem addSearchEngineURL;
    private edu.duke.cabig.catrip.gui.panels.CommandPanel commandPanel;
    private javax.swing.JMenuItem create;
    private javax.swing.JMenuItem delete;
    private javax.swing.JMenuItem deleteQuery;
    private javax.swing.JMenuItem execute;
    private javax.swing.JMenuItem exportResultHtml;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JPanel leftPanel;
    private edu.duke.cabig.catrip.gui.panels.ListServicesPanel listServicesPanel;
    private javax.swing.JMenuBar menuBar;
    private edu.duke.cabig.catrip.gui.panels.OutputPanel outputPanel;
    private javax.swing.JMenu preferencesMenu;
    private edu.duke.cabig.catrip.gui.panels.PropertiesPanel propertiesPanel;
    private javax.swing.JMenu queryMenu;
    private javax.swing.JMenu resultMenu;
    private javax.swing.JMenuItem retrieve;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel rootpanel;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem selectSearchEngine;
    private javax.swing.JMenu servicesMenu;
    private edu.duke.cabig.catrip.gui.panels.VisualPanel visualPanel;
    // End of variables declaration//GEN-END:variables
    
    // List of components need to be linked.
    
    
    
    
}
