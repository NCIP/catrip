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
package edu.duke.cabig.catrip.gui.dnd;

import org.netbeans.graph.api.control.IGraphLinkRouter;
import org.netbeans.graph.api.control.GraphHelper;
import org.netbeans.graph.api.model.IGraphLink;
import org.netbeans.graph.api.model.IGraphPort;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author David Kaspar
 */
public class DirectLinkRouter implements IGraphLinkRouter {

    public void routeLink (Graphics2D gr, GraphHelper helper, IGraphLink link) {
        ArrayList points = new ArrayList ();

        IGraphPort sourcePort = helper.getLinkSourcePort (link);
        Point sourcePoint = sourcePort != null ? helper.getPortLocation (sourcePort) : null;
        IGraphPort targetPort = helper.getLinkTargetPort (link);
        Point targetPoint = targetPort != null ? helper.getPortLocation (targetPort) : null;

        if (sourcePoint != null)
            points.add (sourcePoint);
        if (targetPoint != null)
            points.add (targetPoint);

        helper.setControlPoints (link, (Point[]) points.toArray (new Point[points.size ()]));
    }

    public void controlPointLocationSuggested (IGraphLink link, Point[] controlPoints, int index, Point suggestedLocation) {
    }

}
