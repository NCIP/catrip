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
package org.netbeans.graph.examples;

import org.netbeans.graph.api.GraphFactory;
import org.netbeans.graph.api.model.GraphEvent;
import org.netbeans.graph.api.model.builtin.GraphDocument;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.builtin.GraphPort;
import org.netbeans.graph.vmd.VMDDocumentRenderer;
import org.netbeans.graph.vmd.VMDOrderingLogic;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This test opens a dialog with a single-document/single-view examples view with loaded examples data.
 *
 * @author David Kaspar
 */
public final class VMDTest {

    private static void addNode (GraphDocument document, int nodeIndex, int portsCount) {
        GraphNode node = new GraphNode ();
        node.setID ("Node" + nodeIndex);
        node.setIcon (Document2.image1);
        node.setDisplayName ("Node " + nodeIndex);
        node.setPortsOrderingLogic (new VMDOrderingLogic ());
        for (int a = 0; a < portsCount; a++) {
            GraphPort port = new GraphPort ();
            port.setID ("Port" + a);
            port.setDisplayName ("Port " + a);
            port.setSource (true);
            port.setTarget (true);
            if (a == 0)
                port.setPreferredOrderPosition (new Integer (10));
            node.addPort (port);
            if (a == 0)
                node.setDefaultPort (port);
        }
        document.addComponents (GraphEvent.createSingle (node));
    }

    public static void main (String[] args) {
        GraphDocument document = new GraphDocument ();

        addNode (document, 1, 15);
        addNode (document, 2, 4);
        addNode (document, 3, 6);

        JDialog dialog = new JDialog ();
        JComponent view = GraphFactory.createView (document, new VMDDocumentRenderer (), null, new EventHandlerTest (document));
        dialog.getContentPane ().add (new JScrollPane (view));

        dialog.setSize (600, 600);
        dialog.setVisible (true);
        dialog.addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                System.exit (0);
            }
        });
    }

}
