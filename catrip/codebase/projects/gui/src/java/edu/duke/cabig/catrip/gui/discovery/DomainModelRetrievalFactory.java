/*
 * DomainModelRetrievalFactory.java
 *
 * Created on July 3, 2006, 1:08 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DomainModelRetrievalFactory {
    
    /** Creates a new instance of DomainModelRetrievalFactory */
    public DomainModelRetrievalFactory() {
    }
    
    public static DomainModelRetrievalStrategy  getDefaultRetrievalStrategy(){
        
        return new JavaObjectDeSerializationDomainModelRetrievalStrategy();
    } 
    
    
}
