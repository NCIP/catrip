/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2005 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package edu.duke.cabig.catrip.gui.dnd;

import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.panels.*;
import edu.duke.cabig.catrip.gui.query.DCQLRegistry;
import org.netbeans.graph.api.control.builtin.DefaultViewController;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.GraphEvent;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.netbeans.graph.api.model.builtin.GraphPort;

/**
 * @author David Kaspar
 */
public class ViewController extends DefaultViewController implements ActionListener {
    
    private JPopupMenu popup;
    private JMenuItem miDelete;
    private JMenuItem miViewProp;
    private JMenuItem miShowClass;
    
    private JMenuItem miSetTarget;
    
    
    private JPopupMenu popup2;
    private JMenuItem miLinkCDE;
    
    
    private VisualQueryDesignerPanel test;
    
    public ViewController(VisualQueryDesignerPanel test) {
        this.test = test;
        popup = new JPopupMenu();
        
        miDelete = new JMenuItem("Delete Model element");
        miDelete.addActionListener(this);
        popup.add(miDelete);
        
        miViewProp = new JMenuItem("Edit Model Properties/Attributes");
        miViewProp.addActionListener(this);
        popup.add(miViewProp);
        
        popup.add(new JMenuItem("Get Associated Domain Model Classes"));
        popup.add(new JMenuItem("Get All Domain Model Classes"));
        
        miShowClass = new JMenuItem("Retrieve Semantically Equivalent Classes");
        miShowClass.addActionListener(this);
        popup.add(miShowClass);
        
        
        miSetTarget = new JMenuItem("Set object as Target Object.");
        miSetTarget.addActionListener(this);
        popup.add(miSetTarget);
        
        
        popup2 = new JPopupMenu();
        miLinkCDE = new JMenuItem("Link Classes via CDE");
        miLinkCDE.addActionListener(this);
        popup2.add(miLinkCDE);
        
    }
    
    protected JPopupMenu getPopupMenu() {
        // these 3 lines were added by sanjeev.
        Object[] selectedComponents = getHelper().getSelectedComponents();
        
        if (selectedComponents != null  &&  selectedComponents.length == 2 &&  (selectedComponents[0] instanceof IGraphNode) && (selectedComponents[1] instanceof IGraphNode))
            return popup2;
        
        if (selectedComponents == null  ||  selectedComponents.length != 1 ||  !(selectedComponents[0] instanceof IGraphNode))
            return null; // no popup menu
        
        
        // there is only one component selected and the component is a node, so let's create a popup menu
        
        //useful code here..
//        IGraphNode[] nodes = document.getSelectedComponents().getNodes();
//        nodes[0].getID();
        
        return popup;
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == miDelete) {
            final Object[] selectedComponents = getHelper().getSelectedComponents();
            ArrayList nodes = new ArrayList();
            ArrayList links = new ArrayList();
            if (selectedComponents != null)
                for (int i = 0; i < selectedComponents.length; i++) {
                Object selectedComponent = getHelper().getModelComponent(selectedComponents[i]);
                if (selectedComponent instanceof IGraphNode) {
                    nodes.add(selectedComponent);
                    // sanjeev  // DONE - delete all links that leads in or out from any ports of the node
                    GraphNode gn = (GraphNode) selectedComponent;
                    GraphPort[] gp = (GraphPort[]) gn.getPorts();
                    
                    for (int ii = 0; ii < gp.length; ii++){
                        GraphPort gpp = gp[ii];
                        IGraphLink[] gpl = null;
                        try{
                            gpl = gpp.getLinks();
                        } catch (Exception nulle){
                            gpl = null;
                        }
                        if(!(gpl == null)){
                            for (int jj = 0; jj < gpl.length; jj++){
                                links.add(gpl[jj]);
                            }
                        }
                    }
                    // sanjeev
                    
                    
                } else if (selectedComponent instanceof IGraphLink)
                    links.add(selectedComponent);
                }
            final IGraphNode[] _nodes = (IGraphNode[]) nodes.toArray(new IGraphNode[nodes.size()]);
            final IGraphLink[] _links = (IGraphLink[]) links.toArray(new IGraphLink[links.size()]);
            test.getDocument().removeComponents(GraphEvent.create(_nodes, _links));
            
        }
        //sanjeev : not required as properties are shown on click only..
        else if(e.getSource() == miViewProp){
            // show the properties in the properties pane..
            final Object[] selectedComponents = getHelper().getSelectedComponents();
            if ((selectedComponents != null) && selectedComponents.length == 1){
                Object selectedComponent = getHelper().getModelComponent(selectedComponents[0]);
                if (selectedComponent instanceof IGraphNode) {
                    //nodes.add(selectedComponent);
                    ClassBean node = ((ClassNode)selectedComponent).getAssociatedClassObject();  //getDisplayName();
                    popupPropertiesPanel(node);
                    
                }
            }
            
            // Remove later : sanjeev dummy stitching code...
        } else if(e.getSource() == miShowClass){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    SemanticallyEquivalentClassListPanel ws= new SemanticallyEquivalentClassListPanel();
                    ws.main(null);
                }
            });
        } else if(e.getSource() == miLinkCDE){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    LinkCDEPanel ws= new LinkCDEPanel();
                    ws.main(null);
                }
            });
        }
        // @todo : Remove later, sanjeev dummy stitching code...
        else if(e.getSource() == miSetTarget){
            // set the current node class bean as target object..
            final Object[] selectedComponents = getHelper().getSelectedComponents();
            if (selectedComponents != null && selectedComponents.length == 1){
                Object selectedComponent = getHelper().getModelComponent(selectedComponents[0]);
                if (selectedComponent instanceof ClassNode) {
                    ClassNode cNode = (ClassNode)selectedComponent;
//                    System.out.println("xxxxx TargetObject is:"+cNode.getAssociatedClassObject().getFullyQualifiedName());
                    DCQLRegistry.setTargetNode(cNode);  
                    
                      // TODO -  reverse the foreign associations and other objects for dcql.
                }
            }
        }
        
        
        
        
        
    }
    
    
    private void popupPropertiesPanel(ClassBean name){
        // put this code in a jpanel class.. doesn;t look good here..
        // can have a method like   test.popupPropertiesPanel(nodeClass)
        test.showNodePrperties(name);
        final JFrame jf = new JFrame("Edit Properties");
        
        JPanel jPanel = new javax.swing.JPanel();
        JButton button = new javax.swing.JButton();
        button.setText("Ok");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jf.setVisible(false);
            }
        });
        jPanel.add(button);
        jf.getContentPane().add(jPanel, java.awt.BorderLayout.SOUTH);
        
        
        PropertiesPanel p = new PropertiesPanel();
        p.setMainFrame(test.getMainFrame());
        p.showNodeProperties(name);
        jf.getContentPane().add(p, java.awt.BorderLayout.CENTER);
        jf.setBounds(100,100,300,200);
        jf.setVisible(true);
        //
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


