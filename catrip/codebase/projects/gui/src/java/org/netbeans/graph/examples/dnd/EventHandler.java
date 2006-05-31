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

import org.netbeans.graph.api.IGraphEventHandler;
import org.netbeans.graph.api.model.GraphEvent;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.builtin.GraphLink;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.builtin.GraphPort;
import org.netbeans.graph.examples.DNDTreeTest;

import javax.swing.undo.UndoableEdit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import edu.duke.cabig.catrip.gui.*;

/**
 * @author David Kaspar
 */
public class EventHandler extends IGraphEventHandler {

    private VisualQueryDesignerPanel test;

    public EventHandler (VisualQueryDesignerPanel test) {
        this.test = test;
    }

    public void setSourcePort (IGraphLink link, IGraphPort sourcePort) {
        ((GraphLink) link).setSourcePort ((GraphPort) sourcePort);
    }

    public void setTargetPort (IGraphLink link, IGraphPort targetPort) {
        ((GraphLink) link).setTargetPort ((GraphPort) targetPort);
    }

    public boolean isLinkCreateable (IGraphPort sourcePort, IGraphPort targetPort) {
        return true;
    }

    public void createLink (IGraphPort sourcePort, IGraphPort targetPort) {
        GraphLink link = new GraphLink ();
        link.setID (test.createID ("link"));
        link.setSourcePort ((GraphPort) sourcePort);
        link.setTargetPort ((GraphPort) targetPort);
        test.getDocument ().addComponents (GraphEvent.createSingle (link));
    }

    public void componentsSelected (GraphEvent event) {
        test.getDocument ().selectComponents (event);
        // sanjeev : show the properties of this link in the properties table..
        Object[] selectedComponents =  test.getDocument().getSelectedComponents().getNodes();
        
        if ((selectedComponents != null) && selectedComponents.length == 1){
            Object selectedComponent = selectedComponents[0]; 
                if (selectedComponent instanceof IGraphNode) { 
                    String nodeClass = ((GraphNode)selectedComponent).getID(); //getDisplayName(); 
                    //System.out.println("@#@#@#  "+ nodeClass);
                    //System.out.println("@#@#@#  "+ selectedComponent.getClass().getName());
                    test.showNodePrperties(nodeClass);
                }
            }
        
        
    }

    public boolean isAcceptable (IGraphNode node, DataFlavor[] dataFlavors) {
        return true;
//            final DataFlavor dataFlavor = DataFlavor.selectBestTextFlavor (dataFlavors);
//            System.out.println (dataFlavor);
//            return dataFlavor != null;
    }

    public void accept (IGraphNode dropNode, Transferable transferable) {
        String value = null;
        try {
            value = (String) transferable.getTransferData (DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace (); // TODO
        } catch (IOException e) {
            e.printStackTrace (); // TODO
        }
        final Info[] infos = test.getInfos ();
        for (int i = 0; i < infos.length; i++) {
            final Info info = infos[i];
            if (info.getName ().equals (value)) {
                GraphNode node = null;
                try {
                    node = (GraphNode) info.getNodeClass ().newInstance ();
                    node.setID (test.createID (info.getName ()));
                    node.setDisplayName (info.getDisplayName ());
                    node.setIcon (info.getIcon ());
                    // sanjeev
                    if (node.getID().startsWith("caCore")){
                        node.setTooltipText("gov.nih.nci.cabio.domain."+info.getDisplayName ());
                    } else {
                        node.setTooltipText("edu.georgetown.pir.domain."+info.getDisplayName ());
                    }
                    // sanjeev
                } catch (InstantiationException e) {
                    e.printStackTrace (); // TODO
                } catch (IllegalAccessException e) {
                    e.printStackTrace (); // TODO
                }
                test.getDocument ().addComponents (GraphEvent.createSingle (node));
                return;
            }
        }
    }

    public void undoableEditHappened (UndoableEdit edit) {
    }

    public void notifyModified () {
    }
}
