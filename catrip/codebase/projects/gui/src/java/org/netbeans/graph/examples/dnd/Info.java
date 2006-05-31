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

import org.openide.util.Utilities;

import java.awt.*;

/**
 * @author David Kaspar
 */
public class Info {

    private static final Image NULL_IMAGE = Utilities.loadImage ("org/netbeans/graph/examples/resources/a.png"); // NOI18N

    private String name;
    private String displayName;
    private Image icon;
    private Class nodeClass;

    public Info (String name, String displayName, String icon, Class nodeClass) {
        this.name = name;
        this.displayName = displayName != null ? displayName : name;
        this.icon = icon != null ? Utilities.loadImage ("org/netbeans/graph/examples/dnd/resources/" + icon) : NULL_IMAGE; // NOI18N
        this.nodeClass = nodeClass;
    }

    public String getName () {
        return name;
    }

    public String getDisplayName () {
        return displayName;
    }

    public Image getIcon () {
        return icon;
    }

    public Class getNodeClass () {
        return nodeClass;
    }
    

    public String toString() {
        return displayName;
    }
    
}

