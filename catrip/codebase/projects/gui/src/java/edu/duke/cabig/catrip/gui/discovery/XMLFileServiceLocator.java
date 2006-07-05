/*
 * XMLFileServiceLocator.java
 *
 * Created on June 7, 2006, 4:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import java.util.ArrayList;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.common.PointOfContact;
import gov.nih.nci.cagrid.metadata.common.ResearchCenter;


/**
 *
 * @author Sanjeev Agarwal
 */
public class XMLFileServiceLocator extends ServiceLocator{
    
    /** Creates a new instance of XMLFileServiceLocator */
    public XMLFileServiceLocator() {
    }
    
    public ArrayList<ServiceMetaDataBean> discoverServices() {
        
        ArrayList<ServiceMetaDataBean> alist = new ArrayList(5);
        
        try{
            String baseDir = "C:\\java_exps\\caGrid_MetaDatas\\service_and_domain\\xmls\\";
            String file = baseDir+"caTIES_serviceMetadata.xml";
            String domainModelFile = baseDir+"caTIES_domainModel.obj";
            
            // service one..
            ServiceMetadata commonMetadata = (ServiceMetadata)Utils.deserializeDocument(file, ServiceMetadata.class);
            
            ServiceMetaDataBean sb = new ServiceMetaDataBean();
            
            sb.setDomainModelEndPointRef(domainModelFile);
            
            sb.setServiceName(commonMetadata.getServiceDescription().getService().getName());
            sb.setDescription(commonMetadata.getServiceDescription().getService().getDescription());
            
            PointOfContact pc = commonMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
            sb.setPointOfContact(pc.getFirstName()+" "+pc.getLastName()+":"+pc.getEmail()+":"+pc.getRole());
            
            ResearchCenter rc = commonMetadata.getHostingResearchCenter().getResearchCenter();
            sb.setHostingResearchCenter(rc.getDisplayName() + "("+rc.getShortName()+")"+":"+sb.getPointOfContact());
            alist.add(sb);
            
            
            // service two..
            file = baseDir+"caBIO_serviceMetadata.xml";
            domainModelFile = baseDir+"caBIO_domainModel.obj";
            commonMetadata = (ServiceMetadata)Utils.deserializeDocument(file, ServiceMetadata.class);
            
            sb = new ServiceMetaDataBean();
            
            sb.setDomainModelEndPointRef(domainModelFile);
            
            sb.setServiceName(commonMetadata.getServiceDescription().getService().getName());
            sb.setDescription(commonMetadata.getServiceDescription().getService().getDescription());
            
            pc = commonMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
            sb.setPointOfContact(pc.getFirstName()+" "+pc.getLastName()+":"+pc.getEmail()+":"+pc.getRole());
            
            rc = commonMetadata.getHostingResearchCenter().getResearchCenter();
            sb.setHostingResearchCenter(rc.getDisplayName() + "("+rc.getShortName()+")"+":"+sb.getPointOfContact());
            alist.add(sb);
            
            
        } catch (Exception ee){ return null;}
        
        return alist;
    }
    
}
