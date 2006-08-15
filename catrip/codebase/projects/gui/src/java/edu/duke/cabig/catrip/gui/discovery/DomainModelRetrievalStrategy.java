package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

/**
 * The class is based on the Strategy pattern.
 *
 * @author Sanjeev Agarwal
 */
public abstract class DomainModelRetrievalStrategy {
    
    public abstract DomainModel retrievDomainModel(Object sName);
    public abstract DomainModel retrievDomainModel();
    
}
