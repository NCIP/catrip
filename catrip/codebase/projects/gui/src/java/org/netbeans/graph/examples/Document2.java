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
import org.netbeans.graph.api.model.builtin.GraphNode;
import org.netbeans.graph.api.model.builtin.GraphPort;
import org.openide.util.Utilities;

import java.awt.*;

/**
 * @author David Kaspar
 */
final class Document2 {
    static Image image1 = Utilities.loadImage ("org/netbeans/graph/examples/resources/a.png"); // NOI18N
    static Image image2 = Utilities.loadImage ("org/netbeans/graph/examples/resources/b.png"); // NOI18N

    public static void loadDocument (GraphDocument document) {
        for (int a = 0; a < 10; a ++) {
            // ---- loading test data - node
            GraphNode node = new GraphNode ();
            node.setID ("node"  + (a + 1));
            node.setDisplayName ("Node " + (a + 1));
            node.setIcon ((a & 1) == 0 ? image1 : image2);
            document.addComponents (GraphEvent.createSingle (node));

            GraphPort port0 = new GraphPort ();
            port0.setDisplayName ("Target Port");
            port0.setTarget (true);
            port0.setDirection (IDirectionable.LEFT);
            node.addPort (port0);
            node.setDefaultPort (port0);

            GraphPort port1 = new GraphPort ();
            port1.setDisplayName ("Source Port");
            port1.setSource (true);
            port1.setDirection (IDirectionable.RIGHT);
            node.addPort (port1);
        }
    }

}
