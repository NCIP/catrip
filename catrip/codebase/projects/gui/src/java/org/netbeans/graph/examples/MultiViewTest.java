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
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author David Kaspar
 */
public final class MultiViewTest {

    public static void main (String[] args) {
        // ---- creating test document
        GraphDocument document = new GraphDocument ();
        final SimpleEventHandler eventHandler = new SimpleEventHandler (document);

        // ---- load document
        Document1.loadDocument (document);


        // ---- top views
        JComponent viewTopLeft = GraphFactory.createView (document, new SimpleDocumentRenderer (), null, eventHandler);
	GraphFactory.setZoom (viewTopLeft, 0.8f);
        JComponent viewTopRight = GraphFactory.cloneView (viewTopLeft, null);

        // ---- bottom
        JComponent viewBottomLeft = GraphFactory.createView (document, new SimpleDocumentRenderer (), null, eventHandler);
	GraphFactory.setZoom (viewBottomLeft, 2.0f);
        JComponent viewBottomRight = GraphFactory.cloneView (viewBottomLeft, null);


        // ---- creating dialog, adding test view components, and showing up
        JDialog dialog = new JDialog ();

        Container contentPane = dialog.getContentPane ();
        contentPane.setLayout (new GridLayout (2, 2, 5, 5));
        contentPane.add (new JScrollPane (viewTopLeft));
        contentPane.add (new JScrollPane (viewTopRight));
        contentPane.add (new JScrollPane (viewBottomLeft));
        contentPane.add (new JScrollPane (viewBottomRight));

        dialog.setSize (600, 600);
        dialog.setVisible (true);
        dialog.addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent e) {
                System.exit (0);
            }
        });
    }

}
