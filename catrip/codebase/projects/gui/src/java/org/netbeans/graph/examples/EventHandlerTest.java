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
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.builtin.GraphDocument;
import org.netbeans.graph.api.model.builtin.GraphLink;
import org.netbeans.graph.api.model.builtin.GraphPort;
import org.netbeans.graph.examples.control.SimpleDocumentRenderer;
import org.netbeans.graph.examples.control.SimpleViewController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author David Kaspar
 */
public final class EventHandlerTest extends SimpleEventHandler {

    public EventHandlerTest (GraphDocument document) {
        super (document);
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
        // TODO - call link.setID ()
        link.setSourcePort ((GraphPort) sourcePort);
        link.setTargetPort ((GraphPort) targetPort);
        getDocument ().addComponents (GraphEvent.createSingle (link));
    }

    public static void main (String[] args) {
        // ---- creating document
        GraphDocument document = new GraphDocument ();

        // ---- load document
        Document2.loadDocument (document);

        // ---- creating dialog, adding test view component, and showing up
        JDialog dialog = new JDialog ();

        JComponent view = GraphFactory.createView (document, new SimpleDocumentRenderer (), new SimpleViewController (), new EventHandlerTest (document));
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
