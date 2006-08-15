
package edu.duke.cabig.catrip.gui.config;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;
import edu.duke.cabig.catrip.gui.common.ServiceBean;
import edu.duke.cabig.catrip.gui.security.LoginConfiguration;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import edu.duke.catrip.config.CatripConfigurationDocument;
import edu.duke.catrip.config.CatripService;
import edu.duke.catrip.config.CatripServicesConfigurationDocument;
import edu.duke.catrip.config.GuiConfiguration;
import edu.duke.catrip.config.IndentityProvider;
import java.io.File;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Loads the configuration file "catrip-config.xml" and return a populated GUIConfigurationBean object.
 *
 * @author Sanjeev Agarwal
 */
public  class GUIConfigurationLoader {
    
    private static GUIConfigurationBean configBean =null;
    /**
     * Creates a new instance of GUIConfigurationLoader
     */
    
    public GUIConfigurationLoader() {
    }
    
    /** Return the GUIConfigurationBean populated with Configuration data. */
    public static GUIConfigurationBean getGUIConfiguration(){
        if (configBean == null){
            createConfig();
        }
        return configBean;
    }
    
    public static LoginConfiguration getLoginConfiguration(){
        return null;
    }
    
    /** Loads the data from  "catrip-config.xml" into GUIConfigurationBean object using XMLBeans.*/
    private static synchronized void createConfig(){
        configBean = new GUIConfigurationBean();
        CatripConfigurationDocument conf = null;
        String configXMLFile = GUIConstants.CATRIP_CONFIG_FILE_LOCATION;
        try {
            conf = CatripConfigurationDocument.Factory.parse(new File(configXMLFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        GuiConfiguration config =  conf.getCatripConfiguration().getGuiConfiguration();
        
        
        configBean.setConfigRootLocation(config.getRootDirectory());
        configBean.setIndexServiceUrl(config.getIndexService().getUrl());
        configBean.setIndexServiceUrl(config.getIndexService().getName());
        configBean.setQueryEngineServiceUrl(config.getFederatedQueryEngineService().getUrl());
        configBean.setQueryEngineServiceUrl(config.getFederatedQueryEngineService().getName());
        configBean.setDomainModelMetadataLocation(configBean.getConfigRootLocation()+File.separator+config.getMetadataCache().getDomainModelMetadataLocation());
        configBean.setServiceMetadataLocation(configBean.getConfigRootLocation()+File.separator+config.getMetadataCache().getServiceMetadataLocation());
        configBean.setMetadataMappingFileName(config.getMetadataCache().getMetadataMappingFilename());
        
        
        IndentityProvider[] iProvider = config.getGridIndentityProviders().getIndentityProviderArray();
        
        for (int i=0; i < iProvider.length;i++){
            IndentityProviderBean idBean = new IndentityProviderBean();
            idBean.setDisplayName(iProvider[i].getName());
            idBean.setUrl(iProvider[i].getUrl());
            idBean.setType(iProvider[i].getType());
            idBean.setKeystore(iProvider[i].getKeystore());
            configBean.addGridIndentityProvider(idBean);
        }
        
        CatripServicesConfigurationDocument serviceList = null;
        String servicesXMLFile = configBean.getConfigRootLocation() + File.separator + GUIConstants.CATRIP_SERVICES_CONFIG_FILE_NAME;
        try {
            serviceList = CatripServicesConfigurationDocument.Factory.parse(new File(servicesXMLFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        CatripService[] services = serviceList.getCatripServicesConfiguration().getCatripServiceArray();
        for (int i=0; i < services.length;i++){
            ServiceBean sBean = new ServiceBean();
            sBean.setUrl(services[i].getUrl());
            sBean.setServiceMetadataFileName(services[i].getServiceMetadataFileName());
            sBean.setDomainModelFileName(services[i].getDomainModelFileName());
            configBean.addServices(sBean);
        }
        
        
    }
    
    
}
