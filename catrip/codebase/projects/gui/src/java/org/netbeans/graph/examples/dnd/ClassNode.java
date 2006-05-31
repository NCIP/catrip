/*
 * ClassNode.java
 *
 * Created on May 15, 2006, 2:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.graph.examples.dnd;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.netbeans.graph.api.model.ability.IDirectionable;
import org.netbeans.graph.api.model.IGraphNode;
import org.netbeans.graph.api.control.IGraphNodeRenderer;
import org.netbeans.graph.api.control.GraphHelper;


import javax.swing.*;

/**
 *
 * @author sanju
 */
public class ClassNode extends GenericNode { 


    
    
    /** Creates a new instance of ClassNode */
    public ClassNode() {
        final DirectionPort defaultPort = new DirectionPort (IDirectionable.LEFT);
        addPort (defaultPort);
        setDefaultPort (defaultPort);
        
        addPort (new DirectionPort (IDirectionable.RIGHT));
        
        
    } 
    
     
    public IGraphNodeRenderer createNodeRenderer (GraphHelper helper, IGraphNode node) {
        return new ScreenNodeRenderer (helper, node);  
    }

}
