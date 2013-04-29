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
import org.netbeans.graph.api.control.IGraphLinkRouter;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.ability.IDirectionable;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author David Kaspar
 */
public final class SimpleOrthogonalLinkRouter implements IGraphLinkRouter {

    private static int LINK_SPACE = 8;

    public void routeLink (Graphics2D gr, GraphHelper helper, IGraphLink link) {
        ArrayList points = new ArrayList ();
        IGraphPort sourcePort = helper.getLinkSourcePort (link);
        Point sourcePoint = sourcePort != null ? helper.getPortLocation (sourcePort) : null;
        IGraphPort targetPort = helper.getLinkTargetPort (link);
        Point targetPoint = targetPort != null ? helper.getPortLocation (targetPort) : null;

        if (sourcePoint != null)
            points.add (sourcePoint);
        if (sourcePoint != null && targetPoint != null) {
            Point sp = getLinkControlPointAtNodeBounds (helper, sourcePort);
            Point tp = getLinkControlPointAtNodeBounds (helper, targetPort);
            if (sp != null  &&  tp != null) {
                points.add (sp);
                if (sp.x <= tp.x) {
                    int midx = (tp.x + sp.x) / 2;
                    points.add (new Point (midx, sp.y));
                    points.add (new Point (midx, tp.y));
                } else {
                    int midy = (tp.y + sp.y) / 2;
                    Rectangle sourceBounds = helper.getBounds (helper.getPortNode (sourcePort));
                    Rectangle targetBounds = helper.getBounds (helper.getPortNode (targetPort));
                    if (isInsideY (sourceBounds, midy) || isInsideY (targetBounds, midy)) {
                        if (sourcePoint.y < targetPoint.y)
                            midy = Math.min (sourceBounds.y, targetBounds.y) - getPortOrder (helper, sourcePort) * LINK_SPACE;
                        else
                            midy = Math.max (sourceBounds.y + sourceBounds.height, targetBounds.y + targetBounds.height) + getPortOrder (helper, sourcePort) * LINK_SPACE;
                    } else {
                        if (sourcePoint.y < targetPoint.y)
                            midy -= LINK_SPACE / 4;
                    }
                    points.add (new Point (sp.x, midy));
                    points.add (new Point (tp.x, midy));
                }
                points.add (tp);
            }
        }
        if (targetPoint != null)
            points.add (targetPoint);

        Point[] pointsArray = (Point[]) points.toArray (new Point[points.size ()]);
//        if (pointsArray.length > 2)
//            pointsArray = OrthogonalLinkRouterUtils.preprocessLinkCorners (pointsArray);
        helper.setControlPoints (link, pointsArray);
    }

    public void controlPointLocationSuggested (IGraphLink link, Point[] controlPoints, int index, Point suggestedLocation) {
        // TODO
    }

    private boolean isInsideY (Rectangle rect, int y) {
        return rect.y <= y && y <= rect.y + rect.height;
    }

    private Point getLinkControlPointAtNodeBounds (GraphHelper helper, IGraphPort port) {
        IGraphNode node = helper.getPortNode (port);
        Point location = helper.getPortLocation (port);
        Rectangle bounds = helper.getBounds (node);
        if (location.x < helper.getNodeLocation (node).x)
            return new Point (bounds.x - LINK_SPACE, location.y);
        return new Point (bounds.x + bounds.width + LINK_SPACE * (getPortOrder (helper, port) + 1), location.y);
    }

    protected int getPortOrder (GraphHelper helper, IGraphPort portToResolve) {
        int mask = IDirectionable.ANY;
        if (portToResolve == null)
            return -1;
        final IDirectionable directionable = (IDirectionable) portToResolve.getLookup ().lookup (IDirectionable.class);
        if (directionable != null) {
            final int direction = directionable.getDirection ();
            if ((direction & IDirectionable.LEFT) != 0)
                mask = IDirectionable.LEFT;
            else if ((direction & IDirectionable.RIGHT) != 0)
                mask = IDirectionable.RIGHT;
            else if (direction == IDirectionable.TOP)
                mask = direction;
            else if (direction == IDirectionable.BOTTOM)
                mask = direction;
        }

        IGraphNode node = helper.getPortNode (portToResolve);
        IGraphPort[] ports = helper.getNodePorts (node);
        int order = 0;
        if (ports != null)
            for (int i = 0; i < ports.length; i++) {
                IGraphPort port = ports[i];
                if (port == portToResolve)
                    break;
                final IDirectionable directionablePeer = (IDirectionable) port.getLookup ().lookup (IDirectionable.class);
                if (directionablePeer != null  &&  (directionablePeer.getDirection () & mask) != 0)
                    order ++;
            }
        return order;
    }

}
