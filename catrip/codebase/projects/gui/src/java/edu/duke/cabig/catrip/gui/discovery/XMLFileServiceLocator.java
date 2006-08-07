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
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.catrip.config.CatripService;
import edu.duke.catrip.config.CatripServicesConfigurationDocument;
import java.util.ArrayList;
import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.metadata.ServiceMetadata;
import gov.nih.nci.cagrid.metadata.common.PointOfContact;
import gov.nih.nci.cagrid.metadata.common.ResearchCenter;
import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;


/**
 *
 * @author Sanjeev Agarwal
 */
public class XMLFileServiceLocator extends ServiceLocator{
    HashMap fileMap = new HashMap(10);
    /** Creates a new instance of XMLFileServiceLocator */
    public XMLFileServiceLocator() {
    }
    
    public ArrayList<ServiceMetaDataBean> discoverAllLocalServices() {
        
        
        ArrayList<ServiceMetaDataBean> alist = new ArrayList(10);
        
        try{
            
            
            
            GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
            
            String configXMLFile = guiConfiguration.getConfigRootLocation() + File.separator + "services-config.xml";
            CatripServicesConfigurationDocument sDom = CatripServicesConfigurationDocument.Factory.parse(new File(configXMLFile));
            CatripService[] cachedServices = sDom.getCatripServicesConfiguration().getCatripServiceArray();
            
            
            String serviceMetadatafile = "";
            String domainModelFile = "";
            String serviceUrl="";
            
            
            for (int i = 0; i < cachedServices.length; i++) {
                serviceMetadatafile = cachedServices[i].getServiceMetadataFileName();
                domainModelFile = cachedServices[i].getDomainModelFileName();
                serviceUrl=cachedServices[i].getUrl();
                
                serviceMetadatafile = guiConfiguration.getServiceMetadataLocation()+File.separator+serviceMetadatafile;
                domainModelFile = guiConfiguration.getDomainModelMetadataLocation()+File.separator+domainModelFile;
                addNode(serviceMetadatafile, domainModelFile, alist, serviceUrl);
            }
            
            
            
            // services ..  ideally load all the xmls from this directory.
//            serviceUrl = "http://localhost:8080/CAE";
//            icon = "edu/duke/cabig/catrip/gui/dnd/resources/CAE.png";
//            serviceMetadatafile = guiConfiguration.getServiceMetadataLocation()+File.separator+"CaTRIPcaTissueCAE_serviceMetadata.xml";//"caTISSUE_Core_serviceMetadata.xml";
//            domainModelFile = guiConfiguration.getDomainModelMetadataLocation()+File.separator+"CaTRIPcaTissueCAE_domainModel.xml";//"caTISSUE_Core.xml";
//            addNode(serviceMetadatafile, domainModelFile, alist, serviceUrl, icon);
//
//
//            serviceUrl = "http://localhost:8081/wsrf/services/cagrid/CaTissueCore_Full";
//            icon = "edu/duke/cabig/catrip/gui/dnd/resources/caTissueCore.png";
//            serviceMetadatafile = guiConfiguration.getServiceMetadataLocation()+File.separator+"caTissueCore_serviceMetadata.xml";//"caTISSUE_Core_serviceMetadata.xml";
//            domainModelFile = guiConfiguration.getDomainModelMetadataLocation()+File.separator+"caTissueCore_domainModel.xml";//"caTISSUE_Core.xml";
//            addNode(serviceMetadatafile, domainModelFile, alist, serviceUrl, icon);
            
            
            
            
            
        } catch (Exception ee){ return null;}
        
        return alist;
    }
    
    public ArrayList<ServiceMetaDataBean> discoverServices() {
        return discoverAllLocalServices();
//        ArrayList<ServiceMetaDataBean> alist = new ArrayList(5);
        
//        try{
        
        
        
//            String baseDir = "C:\\java_exps\\caGrid_MetaDatas\\service_and_domain\\xmls\\";
//            String file = "";
//            String domainModelFile = "";
        
        // service ..
//            file = baseDir+"caTIES_serviceMetadata.xml";
//            domainModelFile = baseDir+"caTIES_domainModel.obj";
//            addNode(file, domainModelFile, alist);
//
        
        // service ..
//            file = baseDir+"caTISSUE_CAE_serviceMetadata.xml";
//            domainModelFile = baseDir+"caTISSUE_CAE.obj";
//            addNode(file, domainModelFile, alist);
        
        
        // service ..
//            file = baseDir+"caTISSUE_Core_serviceMetadata.xml";
//            domainModelFile = baseDir+"caTISSUE_Core.xml";
//            addNode(file, domainModelFile, alist);
        
        
//        } catch (Exception ee){
//            ee.printStackTrace();
//            return null;
//        }
//
//        return alist;
    }
    
    
    
    private void addNode(String file, String domainModelFile, ArrayList alist, String serviceUrl) throws Exception{
        // read the file for which you have to add impl or not..
        ResourceBundle rb = ResourceBundle.getBundle("metadataMappings.properties");
        
        ServiceMetaDataBean sb = new ServiceMetaDataBean();
        ServiceMetadata commonMetadata = (ServiceMetadata)Utils.deserializeDocument(file, ServiceMetadata.class);
        
        sb = new ServiceMetaDataBean();
        
        sb.setDomainModelEndPointRef(domainModelFile);
        String serviceName = commonMetadata.getServiceDescription().getService().getName();
        sb.setServiceName(serviceName);
        sb.setServiceUrl(serviceUrl);
        // TODO - change it later.. to generate the icon dunamically from the service name..
        sb.setIcon("edu/duke/cabig/catrip/gui/dnd/resources/"+sb.getServiceName().trim()+".png");
        sb.setDescription(commonMetadata.getServiceDescription().getService().getDescription());
        
        PointOfContact pc = commonMetadata.getServiceDescription().getService().getPointOfContactCollection().getPointOfContact(0);
        sb.setPointOfContact(pc.getFirstName()+" "+pc.getLastName()+":"+pc.getEmail()+":"+pc.getRole());
        
        ResearchCenter rc = commonMetadata.getHostingResearchCenter().getResearchCenter();
        sb.setHostingResearchCenter(rc.getDisplayName() + "("+rc.getShortName()+")"+":"+sb.getPointOfContact());
        
         // TODO - remove this later...
        String needImpl = rb.getString(serviceName);
        if ((needImpl == null) && (Boolean.valueOf(needImpl))){
            sb.needImpl(true);
        }

        alist.add(sb); 
        
        
    }
    
    
}
