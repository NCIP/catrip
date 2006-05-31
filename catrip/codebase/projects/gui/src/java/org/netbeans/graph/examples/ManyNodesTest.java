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
import org.netbeans.graph.examples.control.SimpleDocumentRenderer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author David Kaspar
 */
public final class ManyNodesTest {

    public static void main (String[] args) {
        GraphDocument document = new GraphDocument ();

        for (int a = 0; a < 1000; a ++) {
            GraphNode node = new GraphNode ();
            node.setID ("node" + a);
            node.setDisplayName ("Node " + a);
            document.addComponents (GraphEvent.createSingle (node));
        }


        JDialog dialog = new JDialog ();

        JComponent view = GraphFactory.createView (document, new SimpleDocumentRenderer (), null, new SimpleEventHandler (document));
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
