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
import org.netbeans.graph.api.control.builtin.OrthogonalLinkRouter;
import org.netbeans.graph.api.model.IGraphLink;
import edu.duke.cabig.catrip.gui.control.SimpleLinkRenderer;

import java.awt.*;

/**
 * @author David Kaspar
 */
public class OrthogonalLinkRouterLinkRenderer extends SimpleLinkRenderer {

    private OrthogonalLinkRouter router;

    public OrthogonalLinkRouterLinkRenderer (GraphHelper helper, IGraphLink link, OrthogonalLinkRouter router) {
        super (helper, link);
        this.router = router;
    }

    public void layoutLinkHook (IGraphLink link, Rectangle bounds) {
//        ArrayList rectangles = router.getRectangles (link);
//        if (rectangles != null)
//            for (int i = 0; i < rectangles.size (); i++) {
//                Rectangle rectangle = (Rectangle) rectangles.get (i);
//                bounds.add (rectangle);
//            }
    }

    public void renderLinkHook (IGraphLink link, Graphics2D gr) {
//        ArrayList rectangles = router.getRectangles (link);
//        if (rectangles != null)
//            for (int i = 0; i < rectangles.size (); i++) {
//                Rectangle rectangle = (Rectangle) rectangles.get (i);
//                int b = 32;
//                if (rectangle instanceof OrthogonalLinkRouterRegion) {
//                    b += 32 * ((OrthogonalLinkRouterRegion) rectangle).getDepth ();
//                    if (b > 255)
//                        b = 255;
//                }
//                gr.setColor (new Color (b, b, b));
//                gr.fill (rectangle);
//            }
    }

}
