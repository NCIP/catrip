
package edu.duke.cabig.catrip.gui.simplegui;

import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
import edu.duke.cabig.catrip.gui.discovery.DomainModelRetrievalFactory;
import edu.duke.cabig.catrip.gui.discovery.DomainModelRetrievalStrategy;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.Service;
import edu.duke.cabig.catrip.gui.util.SwingUtils;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import java.io.File;
import java.util.List;

/**
 *
 * @author Sanjeev Agarwal
 */
public class SimpleGuiRegistry {
    // array of the filterpanel..
    private static ObjectGraphProcessor processor = new ObjectGraphProcessor(GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation()+File.separator+"simplegui"+File.separator+"SimpleGuiObjectGraph.xml");
    
    
    
    /** Creates a new instance of SimpleGuiRegistry */
    public SimpleGuiRegistry() {
    }
    
    
    public static ObjectGraphProcessor getProcessor(){
        return processor;
    }
    
    public static void loadMetadata(){
        GUIConfigurationBean guiConfiguration = GUIConfigurationLoader.getGUIConfiguration();
        List<Service> services = processor.getServices();
        
        Service service;
        for (int i=0;i<services.size();i++) {
            service =  services.get(i);
            String domainModelFile = guiConfiguration.getDomainModelMetadataLocation()+File.separator+service.getMetadataXml().trim();
            
            ServiceMetaDataBean sBean = new ServiceMetaDataBean();
            sBean.setDomainModelEndPointRef(domainModelFile);
            sBean.setServiceName(service.getServiceName());
            sBean.setServiceUrl(service.getServiceURL());
            sBean.setIcon(SwingUtils.getTextAsRandomColorImage(service.getServiceName().trim()));
            sBean.needImpl(false);
            
            DomainModelRetrievalStrategy retrievalStrategy = DomainModelRetrievalFactory.getRetrievalStrategy(sBean);
            DomainModel domainModel = retrievalStrategy.retrievDomainModel();
            DomainModelMetaDataRegistry.populateDomainModelMetaData(domainModel, sBean);
            
        }
        
        
    }
    
    
    
}
