
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
    
    /** Discover all services registered into Index service. The service list is generated using Discovery APIs. */
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
                EndpointReferenceType service = allServices[ii];
                System.out.println("Service End Point Address :" + service.getAddress()+"\n");
                try {
                    ServiceMetadata commonMetadata = MetadataUtils.getServiceMetadata(service);
                    if (commonMetadata != null) {
                        
                        ServiceMetaDataBean serviceBean = new ServiceMetaDataBean();
                        
                        serviceBean.setDomainModelEndPointRef(service);
                        serviceBean.setServiceName(commonMetadata.getServiceDescription().getService().getName());
                        serviceBean.setDescription(commonMetadata.getServiceDescription().getService().getDescription());
                        try {
                            PointOfContact pc = commonMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
                            serviceBean.setPointOfContact(pc.getFirstName()+" "+pc.getLastName()+":"+pc.getEmail()+":"+pc.getRole());
                        } catch (NullPointerException np){
                            np.printStackTrace();
                            serviceBean.setPointOfContact("No Point of Contact found in the Metadata.");
                        }
                        
                        try {
                            ResearchCenter rc = commonMetadata.getHostingResearchCenter().getResearchCenter();
                            serviceBean.setHostingResearchCenter(rc.getDisplayName() + "("+rc.getShortName()+")"+":"+serviceBean.getPointOfContact());
                        } catch (NullPointerException np){
                            np.printStackTrace();
                            serviceBean.setHostingResearchCenter("No Hosting Research Center found in the Metadata.");
                        }
                        
                        serviceList.add(serviceBean);
                        
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
