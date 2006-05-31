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

import org.netbeans.graph.api.IGraphEventHandler;
import org.netbeans.graph.api.model.GraphEvent;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.builtin.GraphDocument;

import javax.swing.undo.UndoableEdit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.HashSet;
import java.util.Arrays;

/**
 * @author David Kaspar
 */
public class SimpleEventHandler extends IGraphEventHandler {

    private GraphDocument document;

    public SimpleEventHandler (GraphDocument document) {
        this.document = document;
    }

    public GraphDocument getDocument () {
        return document;
    }

    public void setSourcePort (IGraphLink link, IGraphPort sourcePort) {
    }

    public void setTargetPort (IGraphLink link, IGraphPort targetPort) {
    }

    public boolean isLinkCreateable (IGraphPort sourcePort, IGraphPort targetPort) {
        return false;
    }

    public void createLink (IGraphPort sourcePort, IGraphPort targetPort) {
    }

    public void componentsSelected (GraphEvent event) {
        document.selectComponents (event);
        HashSet set = new HashSet ();
        final IGraphNode[] nodes = event.getNodes ();
        for (int i = 0; i < nodes.length; i++) {
            IGraphNode node = nodes[i];
            final IGraphPort[] ports = node.getPorts ();
            if (ports != null) for (int j = 0; j < ports.length; j++) {
                IGraphPort port = ports[j];
                final IGraphLink[] links = port.getLinks ();
                if (links != null)
                    set.addAll (Arrays.asList (links));
            }
        }
        document.highlightComponents (GraphEvent.create (null, (IGraphLink[]) set.toArray (new IGraphLink[set.size ()])));
    }

    public boolean isAcceptable (IGraphNode node, DataFlavor[] dataFlavors) {
        return false;
    }

    public void accept (IGraphNode node, Transferable transferable) {
    }

    public void undoableEditHappened (UndoableEdit edit) {
    }

    public void notifyModified () {
    }

}
