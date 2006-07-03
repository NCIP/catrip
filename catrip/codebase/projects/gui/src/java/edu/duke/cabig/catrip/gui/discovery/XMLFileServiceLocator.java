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
            
            String file = "C:\\java_exps\\caGrid_MetaDatas\\service_and_domain\\xmls\\caTIES_serviceMetadata.xml";
            
            ServiceMetadata commonMetadata = (ServiceMetadata)Utils.deserializeDocument(file, ServiceMetadata.class);
            
            ServiceMetaDataBean sb = new ServiceMetaDataBean();
            
            sb.setServiceName(commonMetadata.getServiceDescription().getService().getName());
            sb.setDescription(commonMetadata.getServiceDescription().getService().getDescription());
            
            PointOfContact pc = commonMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
            sb.setPointOfContact(pc.getFirstName()+" "+pc.getLastName()+":"+pc.getEmail()+":"+pc.getRole());
            
            ResearchCenter rc = commonMetadata.getHostingResearchCenter().getResearchCenter();
            sb.setHostingResearchCenter(rc.getDisplayName() + "("+rc.getShortName()+")"+":"+sb.getPointOfContact());
            alist.add(sb);
            
        } catch (Exception ee){ return null;}
        
        return alist;
    }
    
}
