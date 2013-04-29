/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

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
    private String attributeName;
    
    public AttributePort (String displayName, String _attributeName,  int direction) {
        this.direction = direction;
        setSource (true);
        setTarget (true);
        this.setDisplayName(displayName);
        this.setAttributeName(_attributeName);
        
    }
    
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

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String _attributeName) {
        this.attributeName = _attributeName;
    }
}
