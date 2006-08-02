/*
 * GUIConfigurationLoader.java
 *
 * Created on June 7, 2006, 4:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

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

/**
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
    
    public static GUIConfigurationBean getGUIConfiguration(){
        if (configBean == null){
            createConfig();
        }
        return configBean;
    }
    
    public static LoginConfiguration getLoginConfiguration(){
        return null;
    }
    
    private static synchronized void createConfig(){
        configBean = new GUIConfigurationBean();
        CatripConfigurationDocument conf = null;
        String configXMLFile = GUIConstants.CATRIP_HOME + File.separator + "catrip-config.xml";
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
        String servicesXMLFile = configBean.getConfigRootLocation() + File.separator + "services-config.xml";
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
