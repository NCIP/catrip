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
import org.netbeans.graph.api.model.builtin.GraphDocument;
import org.netbeans.graph.examples.control.SimpleDocumentRenderer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This test opens a dialog with a single-document/single-view examples view with loaded test data.
 *
 * @author David Kaspar
 */
public final class FirstTimeTest {

    public static void main (String[] args) {
        // ---- creating test document (data model)
        GraphDocument document = new GraphDocument ();

        // ---- load document with some data
        Document1.loadDocument (document);

        // ---- creating dialog
        JDialog dialog = new JDialog ();

        // ---- creating a test-view
        JComponent view = GraphFactory.createView (document, new SimpleDocumentRenderer (), null, new SimpleEventHandler (document));
        // ---- add the test-view to the dialog
        dialog.getContentPane ().add (view);

        // ---- showing up the dialog
        dialog.setSize (600, 600);
        dialog.setVisible (true);
        dialog.addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                System.exit (0);
            }
        });
    }

}
