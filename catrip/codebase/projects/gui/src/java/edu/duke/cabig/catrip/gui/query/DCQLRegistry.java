/*
 * DCQLRegistry.java
 *
 * Created on July 27, 2006, 10:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.query;

import edu.duke.cabig.catrip.gui.common.ForeignAssociationBean;
import edu.duke.cabig.catrip.gui.dnd.ClassNode;
import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DCQLRegistry {
    
    private static ClassNode targetNode;
    
    
    /** Creates a new instance of DCQLRegistry */
    public DCQLRegistry() { 
    } 

    public static ClassNode getTargetNode() {
        return targetNode;
    }

    public static void setTargetNode(ClassNode aTargetNode) {
        aTargetNode.setAsTargetNode();
        targetNode = aTargetNode;
    }

}
