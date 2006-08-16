
package edu.duke.cabig.catrip.gui.discovery;

import java.util.ArrayList;

/**
 * ServiceLocator abstract class.
 *
 * @author Sanjeev Agarwal
 */
public abstract class ServiceLocator {
    
    /** Creates a new instance of ServiceLocator */
    public ServiceLocator() {
    }
    
    /** Implemented by the subclasses. */
    public abstract ArrayList discoverServices();
    
    
} 
