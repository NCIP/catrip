/*
 * DomainModelRetrievalStrategy.java
 *
 * Created on July 3, 2006, 12:58 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;

/**
 *
 * @author Sanjeev Agarwal
 */
public abstract class DomainModelRetrievalStrategy {
    
    public abstract DomainModel retrievDomainModel(Object sName);
    
}
