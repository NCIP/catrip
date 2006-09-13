
package edu.duke.cabig.catrip.gui.config;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;
import edu.duke.cabig.catrip.gui.common.ServiceBean;
import java.util.ArrayList;

/**
 * This class holds the values from catrip-config.xml file.
 *
 * @author Sanjeev Agarwal
 */
public class GUIConfigurationBean {
    
    private boolean useIndexService;
    private String indexServiceUrl;
    private String indexServiceName;
    private String queryEngineServiceUrl;
    private String queryEngineServiceName;
    private String configRootLocation;
    private String serviceMetadataLocation;
    private String domainModelMetadataLocation;
    private String metadataMappingFileName;
    private ArrayList<IndentityProviderBean> gridIndentityProviders = new ArrayList(20);
    
    private ArrayList<ServiceBean> services = new ArrayList(40);
    
    /**
     * Creates a new instance of GUIConfigurationBean
     */
    public GUIConfigurationBean() {
    }
    
    public boolean useIndexService(){
        return useIndexService;
    }
    
    public void useIndexService(boolean use){
        useIndexService = use;
    } 
    
    public String getIndexServiceUrl() {
        return indexServiceUrl;
    }
    
    public void setIndexServiceUrl(String indexServiceUrl) {
        this.indexServiceUrl = indexServiceUrl;
    }
    
    public String getQueryEngineServiceUrl() {
        return queryEngineServiceUrl;
    }
    
    public void setQueryEngineServiceUrl(String queryEngineServiceUrl) {
        this.queryEngineServiceUrl = queryEngineServiceUrl;
    }
    
    public String getConfigRootLocation() {
        return configRootLocation;
    }
    
    public void setConfigRootLocation(String configRootLocation) {
        this.configRootLocation = configRootLocation;
    }
    
    public String getServiceMetadataLocation() {
        return serviceMetadataLocation;
    }
    
    public void setServiceMetadataLocation(String serviceMetadataLocation) {
        this.serviceMetadataLocation = serviceMetadataLocation;
    }
    
    public String getDomainModelMetadataLocation() {
        return domainModelMetadataLocation;
    }
    
    public void setDomainModelMetadataLocation(String domainModelMetadataLocation) {
        this.domainModelMetadataLocation = domainModelMetadataLocation;
    }
    
    public String getMetadataMappingFileName() {
        return metadataMappingFileName;
    }
    
    public void setMetadataMappingFileName(String metadataMappingFileName) {
        this.metadataMappingFileName = metadataMappingFileName;
    }
    
    public ArrayList<IndentityProviderBean> getGridIndentityProviders() {
        return gridIndentityProviders;
    }
    
    public void setGridIndentityProviders(ArrayList<IndentityProviderBean> gridIndentityProviders) {
        this.gridIndentityProviders = gridIndentityProviders;
    }
    
    public void addGridIndentityProvider(IndentityProviderBean  idProvider) {
        this.gridIndentityProviders.add(idProvider);
    }

    public String getIndexServiceName() {
        return indexServiceName;
    }

    public void setIndexServiceName(String indexServiceName) {
        this.indexServiceName = indexServiceName;
    }

    public String getQueryEngineServiceName() {
        return queryEngineServiceName;
    }

    public void setQueryEngineServiceName(String queryEngineServiceName) {
        this.queryEngineServiceName = queryEngineServiceName;
    }

    public ArrayList<ServiceBean> getServices() {
        return services;
    }

    public void setServices(ArrayList<ServiceBean> services) {
        this.services = services;
    }
    public void addServices(ServiceBean sBean) {
        this.services.add(sBean);
    }
}
