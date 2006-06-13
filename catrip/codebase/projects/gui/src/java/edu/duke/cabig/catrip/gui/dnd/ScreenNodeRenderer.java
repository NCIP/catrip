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

import org.netbeans.graph.api.control.GraphHelper;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.model.IGraphPort;
import org.netbeans.graph.api.model.ability.IDisplayable;
import org.netbeans.graph.api.model.ability.IDirectionable;
import edu.duke.cabig.catrip.gui.control.IconNodeRenderer;

import java.awt.*;

/**
 * @author David Kaspar
 */
public class ScreenNodeRenderer extends IconNodeRenderer {
    
    public ScreenNodeRenderer(GraphHelper helper, IGraphNode node) {
        super(helper, node);
    }
    
    protected void layoutPortsLocationsCore(IGraphNode node, Graphics2D gr) {
        final IDisplayable displayable = (IDisplayable) node.getLookup().lookup(IDisplayable.class);
        Image image = displayable != null ? displayable.getIcon() : null;
        final int imageWidth = image != null ? image.getWidth(null) +20: 0; // extra space between image and points..
        final int imageHeight = image != null ? image.getHeight(null) +10: 0; // extra space between image and points..
        
        Point rightSidePoint = new Point(imageWidth / 2, - imageHeight / 2 + PORT_SPACE_2);
        Point leftSidePoint = new Point(-rightSidePoint.x, rightSidePoint.y);
        IGraphPort[] ports = helper.getNodePorts(node);
        for (int i = 0; i < ports.length; i++) {
            IGraphPort port = ports[i];
            IDirectionable dir = (IDirectionable) port.getLookup().lookup(IDirectionable.class);
            switch (dir.getDirection()) {
                case IDirectionable.LEFT:
                    leftSidePoint.y -= PORT_SPACE_2 ;   
                    break;
                case IDirectionable.RIGHT:
                    rightSidePoint.y -= PORT_SPACE_2;
                    break;
            }
        }
        
        for (int i = 0; i < ports.length; i++) {
            IGraphPort port = ports[i];
            IDirectionable dir = (IDirectionable) port.getLookup().lookup(IDirectionable.class);
            switch (dir.getDirection()) {
                case IDirectionable.LEFT:
                    helper.setPortRelativeLocation(port, new Point(leftSidePoint));
                    leftSidePoint.y += PORT_SPACE;
                    break;
                case IDirectionable.RIGHT:
                    helper.setPortRelativeLocation(port, new Point(rightSidePoint));
                    rightSidePoint.y += PORT_SPACE;
                    break;
                case IDirectionable.TOP:
                    helper.setPortRelativeLocation(port, new Point(0, - imageHeight));
                    break;
                case IDirectionable.BOTTOM:
                    helper.setPortRelativeLocation(port, new Point(0, (textRect != null ? (textRect.y + textRect.height) : 0)));
                    break;
                default:
                    helper.setPortRelativeLocation(port, new Point(0, 0));
            }
        }
        
    }
    
    
    /**
     * Called when a new location is suggested to be set to a port.
     * Implementation of this method should affect the implemenetation of layoutNode method which will be invoked immendiately after this.
     * @param port the port
     * @param suggestedLocation the suggested location
     */
    
    public void portLocationSuggested(IGraphPort port, Point suggestedLocation) {
        // TODO
    }
    
}
