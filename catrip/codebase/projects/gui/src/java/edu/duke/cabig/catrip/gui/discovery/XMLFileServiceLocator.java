
package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.util.SwingUtils;
import edu.duke.catrip.config.CatripService;
import edu.duke.catrip.config.CatripServicesConfigurationDocument;
import java.util.ArrayList;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.common.PointOfContact;
import gov.nih.nci.cagrid.metadata.common.ResearchCenter;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.PropertyResourceBundle;


/**
 * Subclass of ServiceLocator which uses locally serialized Service Metadata XML files.
 *
 * @author Sanjeev Agarwal
 */
public class XMLFileServiceLocator extends ServiceLocator{
    HashMap fileMap = new HashMap(10);
    /** Creates a new instance of XMLFileServiceLocator */
    public XMLFileServiceLocator() {
    }
    
    public ArrayList<ServiceMetaDataBean> discoverAllLocalServices() {

        ArrayList<ServiceMetaDataBean> serviceBeanlist = new ArrayList(10);
        
        try{
            
            GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
            
            String configXMLFile = guiConfiguration.getConfigRootLocation() + File.separator + "services-config.xml";
            CatripServicesConfigurationDocument configDocument = CatripServicesConfigurationDocument.Factory.parse(new File(configXMLFile));
            CatripService[] cachedServices = configDocument.getCatripServicesConfiguration().getCatripServiceArray();
            
            
            String serviceMetadatafile = "";
            String domainModelFile = "";
            String serviceUrl="";
            
            
            for (int i = 0; i < cachedServices.length; i++) {
                serviceMetadatafile = cachedServices[i].getServiceMetadataFileName();
                domainModelFile = cachedServices[i].getDomainModelFileName();
                serviceUrl=cachedServices[i].getUrl();
                
                serviceMetadatafile = guiConfiguration.getServiceMetadataLocation()+File.separator+serviceMetadatafile;
                domainModelFile = guiConfiguration.getDomainModelMetadataLocation()+File.separator+domainModelFile;
                addNode(serviceMetadatafile, domainModelFile, serviceBeanlist, serviceUrl);
            }
            
        } catch (Exception ee){ return null;}
        
        return serviceBeanlist;
    }
    
    public ArrayList<ServiceMetaDataBean> discoverServices() {
        return discoverAllLocalServices();
    }
    
    
    
    private void addNode(String file, String domainModelFile, ArrayList serviceBeanlist, String serviceUrl) throws Exception{
        
        GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
        
        PropertyResourceBundle bundle = new PropertyResourceBundle(new FileInputStream(guiConfiguration.getConfigRootLocation() + File.separator +"metadataMappings.properties"));
        
        ServiceMetaDataBean serviceMetaDataBean = new ServiceMetaDataBean();
        ServiceMetadata commonMetadata = (ServiceMetadata)Utils.deserializeDocument(file, ServiceMetadata.class);
        
        serviceMetaDataBean = new ServiceMetaDataBean();
        
        serviceMetaDataBean.setDomainModelEndPointRef(domainModelFile);
        String serviceName = commonMetadata.getServiceDescription().getService().getName();
        serviceMetaDataBean.setServiceName(serviceName);
        serviceMetaDataBean.setServiceUrl(serviceUrl);
        // generate the icon dynamically from the service name..
        serviceMetaDataBean.setIcon(SwingUtils.getTextAsRandomColorImage(serviceMetaDataBean.getServiceName().trim())); 
//        serviceMetaDataBean.setIcon("edu/duke/cabig/catrip/gui/dnd/resources/"+serviceMetaDataBean.getServiceName().trim()+".png");
        serviceMetaDataBean.setDescription(commonMetadata.getServiceDescription().getService().getDescription());
        
        PointOfContact pointOfContact = commonMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
        serviceMetaDataBean.setPointOfContact(pointOfContact.getFirstName()+" "+pointOfContact.getLastName()+":"+pointOfContact.getEmail()+":"+pointOfContact.getRole());
        
        ResearchCenter researchCenter = commonMetadata.getHostingResearchCenter().getResearchCenter();
        serviceMetaDataBean.setHostingResearchCenter(researchCenter.getDisplayName() + "("+researchCenter.getShortName()+")"+":"+serviceMetaDataBean.getPointOfContact());
        
        // TODO - remove this later.  only for the old caCORE generated system..
        try {
        String needImpl = bundle.getString(serviceName);
        if ((needImpl != null) && (Boolean.valueOf(needImpl))){
            serviceMetaDataBean.needImpl(true);
        }
        } catch (Exception e){}
        // only for the old caCORE generated system..
        
        serviceBeanlist.add(serviceMetaDataBean);
        
        
    }
    
    
}
