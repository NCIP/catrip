/*
 * ClassNode.java
 *
 * Created on May 15, 2006, 2:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.dnd;

import edu.duke.cabig.catrip.gui.common.ClassBean;
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
    
    private ClassBean associatedClassObject;
    private boolean isTargetNode=false;
    
    /** Creates a new instance of ClassNode */
    public ClassNode() {
        final DirectionPort defaultPort = new DirectionPort (IDirectionable.RIGHT);
        addPort (defaultPort);
        setDefaultPort (defaultPort);
        
        addPort (new DirectionPort (IDirectionable.LEFT));
        
//        addPort (new DirectionPort (IDirectionable.BOTTOM_LEFT));
//        addPort (new DirectionPort (IDirectionable.BOTTOM_LEFT));
//        addPort (new DirectionPort (IDirectionable.BOTTOM_LEFT));
//        addPort (new DirectionPort (IDirectionable.BOTTOM_LEFT));
//        addPort (new DirectionPort (IDirectionable.BOTTOM_LEFT));
//        addPort (new DirectionPort (IDirectionable.BOTTOM_LEFT));
        
        
        
        
    } 
    
     
    public IGraphNodeRenderer createNodeRenderer (GraphHelper helper, IGraphNode node) {
        //return new ScreenNodeRenderer (helper, node);  // sanjeev
        return new ClassNodeRenderer (helper, node); // sanjeev
    }

    public ClassBean getAssociatedClassObject() {
        return associatedClassObject;
    }

    public void setAssociatedClassObject(ClassBean associatedClassObject) {
        this.associatedClassObject = associatedClassObject;
    }

    public boolean isTargetNode() {
        return isTargetNode;
    }
    public void setAsTargetNode() {
        this.isTargetNode = true;
    }
    public void isNotTargetNode(){
        this.isTargetNode = false;
    }

}
