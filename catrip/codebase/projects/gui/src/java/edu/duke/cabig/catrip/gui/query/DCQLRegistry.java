
package edu.duke.cabig.catrip.gui.query;

import edu.duke.cabig.catrip.gui.common.ForeignAssociationBean;
import edu.duke.cabig.catrip.gui.dnd.ClassNode;
import java.util.ArrayList;

/**
 * The registry to keep information about the Target object/node set on the graph.
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
    public static void clean(){
        targetNode = null;
    }

}
