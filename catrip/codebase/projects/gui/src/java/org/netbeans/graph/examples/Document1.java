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

import org.netbeans.graph.api.model.GraphEvent;
import org.netbeans.graph.api.model.ability.IDirectionable;
import org.netbeans.graph.api.model.builtin.GraphDocument;
import org.netbeans.graph.api.model.builtin.GraphLink;
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.builtin.GraphPort;
import org.openide.util.Utilities;

/**
 * @author David Kaspar
 */
final class Document1 {

    public static void loadDocument (GraphDocument document) {
        // ---- loading test data - node1
        GraphNode node1 = new GraphNode ();
        node1.setID ("node1");
        node1.setDisplayName ("Node 1");
        node1.setIcon (Document2.image1); // NOI18N
        node1.setTooltipText ("First Node");
        document.addComponents (GraphEvent.createSingle (node1));

        GraphPort port10 = new GraphPort ();
        port10.setDisplayName ("Target Port 1");
        port10.setDirection (IDirectionable.LEFT);
        node1.addPort (port10);
        node1.setDefaultPort (port10);

        GraphPort port11 = new GraphPort ();
        port11.setDisplayName ("Source Port 1");
        port11.setDirection (IDirectionable.RIGHT);
        node1.addPort (port11);


        // ---- loading test data - node2
        GraphNode node2 = new GraphNode ();
        node2.setID ("node2");
        node2.setDisplayName ("Node 2");
        node2.setIcon (Document2.image2); // NOI18N
        node2.setTooltipText ("Second Node");
        document.addComponents (GraphEvent.createSingle (node2));

        GraphPort port20 = new GraphPort ();
        port20.setDisplayName ("Target Port 2");
        port20.setDirection (IDirectionable.LEFT);
        node2.addPort (port20);
        node2.setDefaultPort (port20);

        GraphPort port21 = new GraphPort ();
        port21.setDisplayName ("Source Port 2");
        port21.setDirection (IDirectionable.RIGHT);
        node2.addPort (port21);


        // ---- loading test data - links
        GraphLink link1 = new GraphLink ();
        link1.setSourcePort (port11);
        link1.setTargetPort (port20);
        document.addComponents (GraphEvent.createSingle (link1));

        GraphLink link2 = new GraphLink ();
        link2.setSourcePort (port21);
        link2.setTargetPort (port10);
        document.addComponents (GraphEvent.createSingle (link2));
    }

}
