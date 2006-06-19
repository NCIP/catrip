/*
 * ServiceLocator.java
 *
 * Created on June 7, 2006, 4:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import java.util.ArrayList;

/**
 *
 * @author Sanjeev Agarwal
 */
public abstract class ServiceLocator {
    
    /** Creates a new instance of ServiceLocator */
    public ServiceLocator() {
    }
    
    public abstract ArrayList discoverServices();
    
    
} 
