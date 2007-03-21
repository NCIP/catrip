
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
    
    /**
     * Loads the data from  "catrip-guiConfig.xml" into GUIConfigurationBean object using XMLBeans.
     */
    private static synchronized void createConfig(){
        configBean = new GUIConfigurationBean();
        CatripConfigurationDocument conf = null;
        String configXMLFile = GUIConstants.CATRIP_CONFIG_FILE_LOCATION;
        try {
            conf = CatripConfigurationDocument.Factory.parse(new File(configXMLFile));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        GuiConfiguration guiConfig =  conf.getCatripConfiguration().getGuiConfiguration();
        
        
        configBean.setConfigRootLocation(guiConfig.getRootDirectory());
        configBean.useIndexService(guiConfig.getIndexService().getUse()); 
        configBean.setIndexServiceName(guiConfig.getIndexService().getName());
        configBean.setIndexServiceUrl(guiConfig.getIndexService().getUrl());
        configBean.setQueryEngineServiceUrl(guiConfig.getFederatedQueryEngineService().getUrl());
        configBean.setQueryEngineServiceUrl(guiConfig.getFederatedQueryEngineService().getName());
        configBean.setDomainModelMetadataLocation(configBean.getConfigRootLocation()+File.separator+guiConfig.getMetadataCache().getDomainModelMetadataLocation());
        configBean.setServiceMetadataLocation(configBean.getConfigRootLocation()+File.separator+guiConfig.getMetadataCache().getServiceMetadataLocation());
        configBean.setMetadataMappingFileName(guiConfig.getMetadataCache().getMetadataMappingFilename());
        
        
        IndentityProvider[] iProvider = guiConfig.getGridIndentityProviders().getIndentityProviderArray();
        
        for (int i=0; i < iProvider.length;i++){
            IndentityProviderBean idBean = new IndentityProviderBean();
            idBean.setDisplayName(iProvider[i].getName());
            idBean.setIdpUrl(iProvider[i].getIdpUrl());
            idBean.setDorianUrl(iProvider[i].getDorianUrl());
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
