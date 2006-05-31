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
package org.netbeans.graph.examples.dnd;

import org.netbeans.graph.api.model.ability.IDirectionable;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.control.IGraphNodeRenderer;
import org.netbeans.graph.api.control.GraphHelper;

/**
 * @author David Kaspar
 */
public class TriggerNode extends GenericNode {

    public TriggerNode () {
        addPort (new DirectionPort (IDirectionable.BOTTOM));
        addPort (new DirectionPort (IDirectionable.LEFT));
        addPort (new DirectionPort (IDirectionable.RIGHT));
    }

    public IGraphNodeRenderer createNodeRenderer (GraphHelper helper, IGraphNode node) {
        return new ScreenNodeRenderer (helper, node);
    }

}
