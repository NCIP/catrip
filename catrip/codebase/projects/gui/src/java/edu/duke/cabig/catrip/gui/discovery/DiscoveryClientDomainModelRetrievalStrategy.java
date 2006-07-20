/*
 * DiscoveryClientDomainModelRetrievalStrategy.java
 *
 * Created on July 5, 2006, 9:27 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import gov.nih.nci.cagrid.discovery.MetadataConstants;
import gov.nih.nci.cagrid.discovery.ResourcePropertyHelper;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import javax.xml.namespace.QName;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.w3c.dom.Element;

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
        DomainModel model = null;
        
        try {
            Element resourceProperty = ResourcePropertyHelper.getResourceProperty(endPontRef,new QName(MetadataConstants.CAGRID_DATA_MD_NAMESPACE, "DomainModel"));
            model = (DomainModel) ObjectDeserializer.toObject(resourceProperty, DomainModel.class);
            System.out.println("Loading the Domain Model for Project: "+model.getProjectLongName());
        } catch (Exception e){
            System.out.println("Couldn't load the Domain Model for End Point Address:"+endPontRef.getAddress());
            e.printStackTrace();
        }
        return model;
    }
}
