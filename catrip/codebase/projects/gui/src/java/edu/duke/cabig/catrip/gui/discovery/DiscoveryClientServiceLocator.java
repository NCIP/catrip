
package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import gov.nih.nci.cagrid.discovery.MetadataUtils;
import gov.nih.nci.cagrid.discovery.client.DiscoveryClient;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.common.PointOfContact;
import gov.nih.nci.cagrid.metadata.common.ResearchCenter;
import java.util.ArrayList;
import org.apache.axis.message.addressing.EndpointReferenceType;

/**
 * This class is a subclass of ServiceLocator which uses Discovery APIs to locate the Service Metadata and the Domain Model.
 *
 * @author Sanjeev Agarwal
 */
public class DiscoveryClientServiceLocator extends ServiceLocator{
    
    /** Creates a new instance of DiscoveryClientServiceLocator */
    public DiscoveryClientServiceLocator() {
    }
    
    /**
     * Discover all services registered into Index endpointReference. The endpointReference list is generated using Discovery APIs.
     */
    public ArrayList<ServiceMetaDataBean> discoverServices() {
        ArrayList<ServiceMetaDataBean> serviceList = new ArrayList(50);
        
        DiscoveryClient client = null;
        EndpointReferenceType[] allServices = null;
        
        
        try {
            client = new DiscoveryClient(GUIConstants.DEFAULT_INDEX_SERVICE_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            allServices = client.getAllServices(true);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        if (allServices != null) {
            for (int ii = 0; ii < allServices.length; ii++) {
                EndpointReferenceType endpointReference = allServices[ii];
                System.out.println("Service End Point Address :" + endpointReference.getAddress()+"\n");
                try {
                    ServiceMetadata serviceMetadata = MetadataUtils.getServiceMetadata(endpointReference);
                    if (serviceMetadata != null) {
                        
                        ServiceMetaDataBean serviceMetaDataBean = new ServiceMetaDataBean();
                        
                        serviceMetaDataBean.setDomainModelEndPointRef(endpointReference);
                        serviceMetaDataBean.setServiceName(serviceMetadata.getServiceDescription().getService().getName());
                        serviceMetaDataBean.setDescription(serviceMetadata.getServiceDescription().getService().getDescription());
                        try {
                            PointOfContact pointOfContact = serviceMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
                            serviceMetaDataBean.setPointOfContact(pointOfContact.getFirstName()+" "+pointOfContact.getLastName()+":"+pointOfContact.getEmail()+":"+pointOfContact.getRole());
                        } catch (NullPointerException np){
                            np.printStackTrace();
                            serviceMetaDataBean.setPointOfContact("No Point of Contact found in the Metadata.");
                        }
                        
                        try {
                            ResearchCenter researchCenter = serviceMetadata.getHostingResearchCenter().getResearchCenter();
                            serviceMetaDataBean.setHostingResearchCenter(researchCenter.getDisplayName() + "("+researchCenter.getShortName()+")"+":"+serviceMetaDataBean.getPointOfContact());
                        } catch (NullPointerException np){
                            np.printStackTrace();
                            serviceMetaDataBean.setHostingResearchCenter("No Hosting Research Center found in the Metadata.");
                        }
                        
                        serviceList.add(serviceMetaDataBean);
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No services found.");
        }
        
        return serviceList;
    }
    
}
