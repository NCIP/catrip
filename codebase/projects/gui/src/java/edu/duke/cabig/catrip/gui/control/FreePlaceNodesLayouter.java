/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

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
package edu.duke.cabig.catrip.gui.control;

import org.netbeans.graph.api.control.GraphHelper;
import org.netbeans.graph.api.control.IGraphNodesLayouter;
import org.netbeans.graph.api.control.builtin.AnimatedNodesLayouter;
import org.netbeans.graph.api.model.IGraphNode;

import java.awt.*;
import java.util.HashMap;

/**
 * @author David Kaspar
 */
// TODO - perfomance - scalability problem
public final class FreePlaceNodesLayouter implements IGraphNodesLayouter, AnimatedNodesLayouter.Computer {

    public void layoutNodesLocations (Graphics2D gr, GraphHelper helper, IGraphNode[] nodes) {
        AnimatedNodesLayouter.setNewPositions (helper, nodes, computeNodesLocations (gr, helper, nodes));
    }

    public AnimatedNodesLayouter.Computer.Result computeNodesLocations (Graphics2D gr, GraphHelper helper, IGraphNode[] nodes) {
        final HashMap positions = new HashMap ();

        IGraphNode[] allNodes = helper.getNodes ();
        if (allNodes != null) for (int i = 0; i < allNodes.length; i++) {
            IGraphNode node = allNodes[i];
            positions.put (node, getNewComponentLocation (helper, positions, allNodes));
        }

        return new AnimatedNodesLayouter.Computer.Result () {
            public Point getNodeLocation (IGraphNode node) {
                return (Point) positions.get (node);
            }
        };
    }

    private Point getNewComponentLocation (GraphHelper helper, HashMap positions, IGraphNode[] nodes) {
        for (int a = 0; ; a++) {
            for (int b = 0; b <= a; b++) {
                int x = 150 + 250 * (a - b);
                int y = 150 * (1 + b);
                if (isThereEmptyPlace (helper, positions, nodes, x, y))
                    return new Point (x, y);
            }
        }
    }

    private boolean isThereEmptyPlace (GraphHelper helper, HashMap positions, IGraphNode[] nodes, int x, int y) {
        Rectangle rectangle = new Rectangle (x, y, 100, 150);
        if (nodes != null)
            for (int i = 0; i < nodes.length; i++) {
                IGraphNode node = nodes[i];
                Point location = (Point) positions.get (node);
                if (location == null)
                    location = helper.getNodeLocation (node);
                if (location != null && rectangle.contains (location))
                    return false;
                final Rectangle bounds = helper.getBounds (node);
                if (bounds != null && rectangle.contains (bounds))
                    return false;
            }
        return true;
    }

}
