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

import org.netbeans.graph.api.model.builtin.GraphPort;
import org.netbeans.graph.api.model.ability.IDirectionable;

/**
 * @author David Kaspar
 */
public class DirectionPort extends GraphPort implements IDirectionable {

    private int direction;

    public DirectionPort (int direction) {
        this.direction = direction;
        setSource (true);
        setTarget (true);
    }

    public int getDirection () {
        return direction;
    }

}
