/*
 * DiscoveryClientDomainModelRetrievalStrategy.java
 *
 * Created on July 5, 2006, 9:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import org.apache.axis.message.addressing.EndpointReferenceType;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DiscoveryClientDomainModelRetrievalStrategy extends DomainModelRetrievalStrategy{
    
    EndpointReferenceType endPontRef; 
    
    /** Creates a new instance of DiscoveryClientDomainModelRetrievalStrategy */
    public DiscoveryClientDomainModelRetrievalStrategy() {
    }
    
    public DiscoveryClientDomainModelRetrievalStrategy(EndpointReferenceType endPontRef_) {
        endPontRef = endPontRef_;
    }
    
    public DomainModel retrievDomainModel(Object sName) {
        return null;
    }
    
    public DomainModel retrievDomainModel() {
        return null;
    }
}
