
package edu.duke.cabig.catrip.gui.simplegui;

import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationBean;
import edu.duke.cabig.catrip.gui.config.GUIConfigurationLoader;
import edu.duke.cabig.catrip.gui.discovery.DomainModelMetaDataRegistry;
import edu.duke.cabig.catrip.gui.discovery.DomainModelRetrievalFactory;
import edu.duke.cabig.catrip.gui.discovery.DomainModelRetrievalStrategy;
import edu.duke.cabig.catrip.gui.panels.FilterRowPanel;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphAssociation;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.GraphObject;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.ObjectGraphProcessor;
import edu.duke.cabig.catrip.gui.simplegui.objectgraph.Service;
import edu.duke.cabig.catrip.gui.util.SwingUtils;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Sanjeev Agarwal
 */
public class SimpleGuiRegistry {
    // array of the filterpanel..
    private static ObjectGraphProcessor processor = new ObjectGraphProcessor(GUIConfigurationLoader.getGUIConfiguration().getConfigRootLocation()+File.separator+"simplegui"+File.separator+"SimpleGuiObjectGraph.xml");
    private static List<GraphObject> currentXMLObjectList = new ArrayList(50);
    private static ArrayList<ClassBean> currentClassBeanList = new ArrayList(50);
    private static GraphObject targetGraphObject = null; // will hold the ref to target object ClassBean object..
    
    private static ArrayList<FilterRowPanel> filters = new ArrayList(10);
    
    private static HashMap beanMap = new HashMap();
    
    
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
            
            // TODO - remove later... get this prop from properties file... same as complex gui...
            if (service.getServiceName().equalsIgnoreCase("caTissueCore")){
                sBean.needImpl(true);
            }
            
            DomainModelRetrievalStrategy retrievalStrategy = DomainModelRetrievalFactory.getRetrievalStrategy(sBean);
            DomainModel domainModel = retrievalStrategy.retrievDomainModel();
            DomainModelMetaDataRegistry.populateDomainModelMetaData(domainModel, sBean);
            
        }
        
        
    }
    
    
    
    
    public static List getCurrentClassBeanList() {
        return currentClassBeanList;
    }
    
    public static void setCurrentClassBeanList(ArrayList list) {
        currentClassBeanList = list;
    }
    
    public static void addClassBeanToList(ClassBean cBean) {
        currentClassBeanList.add(cBean);
    }
    
    
    
    public static void cleanRegistry() {
        setCurrentClassBeanList(new ArrayList(50));
        setCurrentXMLObjectList(new ArrayList(50));
        setFilterList(new ArrayList(10));
        setTargetGraphObject(null);
    }
    
    public static List getCurrentXMLObjectList() {
        return currentXMLObjectList;
    }
    
    public static void setCurrentXMLObjectList(List<GraphObject> objs) {
        currentXMLObjectList = objs;
        
        GraphObject obj;
        GraphAssociation assoc;
        
        for (int i=0;i<objs.size();i++) {
            obj = objs.get(i);
            boolean display = obj.isDisplayable();
            if (display){
                String[] displaybleAttributes = obj.getDisplaybleAttributes().split(",");
                ClassBean cBean = DomainModelMetaDataRegistry.lookupClassByRefId(obj.getRefID()).clone();
                cBean.filterAttributes(displaybleAttributes);
                currentClassBeanList.add(cBean);
                obj.setClassBean(cBean);
            }else {
                ClassBean cBean = DomainModelMetaDataRegistry.lookupClassByRefId(obj.getRefID()).clone();
                obj.setClassBean(cBean);
            }
            
        }
        
    }
    
    public static GraphObject getTargetGraphObject() {
        return targetGraphObject;
    }
    
    public static void setTargetGraphObject(GraphObject aTargetGraphObject) {
        targetGraphObject = aTargetGraphObject;
    }
    
    
    public static ArrayList<FilterRowPanel> getFilterList() {
        return filters;
    }
    
    public static void setFilterList(ArrayList<FilterRowPanel> filters) {
        filters = filters;
    }
    
    public static void addFilterToList(FilterRowPanel filter) {
        filters.add(filter);
    }
    
    public static HashMap getBeanMap() {
        return beanMap;
    }
    
    public static void setBeanMap(HashMap aBeanMap) {
        beanMap = aBeanMap;
    }
    
    public static void addToBeanMap(ClassBean cBean) {
        String fullClassName = cBean.getFullyQualifiedName();
        boolean alreadyThere = beanMap.containsKey(fullClassName);
        if (!alreadyThere){
            beanMap.put(fullClassName, cBean);
        }
        
    }
    
    
    
    
    public void prepareForDcql(){
        // fill the hash map with filled objects only...
        ArrayList<FilterRowPanel> list = getFilterList();
        for (int i = 0; i < list.size(); i++) {
            FilterRowPanel pnl = list.get(i);
            CDEComboboxBean cdeBean = (CDEComboboxBean)pnl.getCdeCombo().getSelectedItem();
            ClassBean cBean = cdeBean.getClassBean();
            addToBeanMap(cBean);
        }
        // pick filters one by one and keep setting the associations in each item...
        
        for (int i = 0; i < list.size(); i++) {
            FilterRowPanel pnl = list.get(i);
            CDEComboboxBean cdeBean = (CDEComboboxBean)pnl.getCdeCombo().getSelectedItem();
            GraphObject targetObject =  getTargetGraphObject();
            GraphObject filterObject = cdeBean.getGraphObject();
            if (filterObject.isLocal()){
                
            }
            
            
//            ClassBean cBean = cdeBean.getClassBean();

        }
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
