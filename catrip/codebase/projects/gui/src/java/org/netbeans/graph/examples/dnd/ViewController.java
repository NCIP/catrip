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
package org.netbeans.graph.examples.dnd;

import org.netbeans.graph.api.control.builtin.DefaultViewController;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.GraphEvent;
import org.netbeans.graph.examples.DNDTreeTest;
import edu.duke.cabig.catrip.gui.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * @author David Kaspar
 */
public class ViewController extends DefaultViewController implements ActionListener {
    
    private JPopupMenu popup;
    private JMenuItem miDelete;
    private JMenuItem miViewProp;
    
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
        
        popup.add(new JMenuItem("Get list of Associated Model Elements"));
        
    }
    
    protected JPopupMenu getPopupMenu() {
        // these 3 lines were added by sanjeev.
        Object[] selectedComponents = getHelper().getSelectedComponents();
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
                    // TODO - delete all links that leads in or out from any ports of the node
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
                    String nodeClass = ((GraphNode)selectedComponent).getID(); //getDisplayName();
                    //System.out.println("@#@#@#  "+ nodeClass);
                    //System.out.println("@#@#@#  "+ ((GraphNode)selectedComponent).getID());
                    
                    popupPropertiesPanel(nodeClass);
                    
                }
            }
        }
        
        
        
        
        
        
        
        
    }
    
    
    private void popupPropertiesPanel(String name){
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


