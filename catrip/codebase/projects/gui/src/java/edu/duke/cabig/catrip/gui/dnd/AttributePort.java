/*
 * AttributePort.java
 *
 * Created on June 5, 2006, 12:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.dnd;

import org.netbeans.graph.api.model.builtin.GraphPort;
import org.netbeans.graph.api.model.ability.IDirectionable;



/**
 *
 * @author Sanjeev Agarwal
 */
public class AttributePort extends GraphPort implements IDirectionable {
    
    /** Creates a new instance of AttributePort */

    private int direction;
    
    public AttributePort (String displayName, int direction) {
        this.direction = direction;
        setSource (true);
        setTarget (true);
        this.setDisplayName(displayName);
    }

    public AttributePort (int direction) {
        this.direction = direction;
        setSource (true);
        setTarget (true);
    }

    public int getDirection () {
        return direction;
    }
}
