
package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata;
import gov.nih.nci.cagrid.metadata.common.UMLAttribute;
import gov.nih.nci.cagrid.metadata.common.UMLClass;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class acts as Registry for the Domain Model Metadata. 
 * 
 * @author Sanjeev Agarwal
 */
public class DomainModelMetaDataRegistry {
    
    /** This Map contains the ClassBean instances vs. class RefIds as defined in the Domain Model extract */
    private static HashMap classMap = new HashMap(100);
    
    /** Map of the class List vs service/model name. */
    private static HashMap domainModelMap = new HashMap(20);
    
    
    /** Creates a new instance of DomainModelMetaDataRegistry */
    public DomainModelMetaDataRegistry() {
    }
    
    public static ClassBean lookupClassByRefId(String refId) {
        return (ClassBean) classMap.get(refId);
    }
    
    public static void bindClassByRefId(ClassBean classBean){
        classMap.put(classBean.getId(), classBean);
    }
    
    public static ArrayList<ClassBean> lookupClassListByDomainModelName(String id) {
        return (ArrayList) domainModelMap.get(id);
    }
    
    public static void bindClassListByDomainModelName(String domainModelName, ArrayList<ClassBean> classList){
        domainModelMap.put(domainModelName, classList);
    }
    
    public static ArrayList<ClassBean> lookupClassListByServiceName(String id) {
        return (ArrayList) domainModelMap.get(id);
    }
    
    /** Bind class List with the serviceName. The list is later retrieved to draw the tree.*/
    public static void bindClassListByServiceName(String domainModelName, ArrayList<ClassBean> classList){
        domainModelMap.put(domainModelName, classList);
    }
    
    /** Populate the data from Domain Model extract into ClassBean instances and bind them into registry.  */

    public static void populateDomainModelMetaData(DomainModel domainModel, ServiceMetaDataBean sBean){
//        System.out.println("XXXXX---   Service name :"+sBean.getServiceName());
        ArrayList<ClassBean> serviceClassList = new ArrayList(100); // The collection of classes that belongs to only this DomainModel.
        
        UMLClass[] umlClasses = domainModel.getExposedUMLClassCollection().getUMLClass();
        for (int i = 0; i < umlClasses.length; i++) {
            ClassBean classBean = new ClassBean();
            classBean.setClassName(umlClasses[i].getClassName() );
            classBean.setPackageName(umlClasses[i].getPackageName() );
            classBean.setDescription(umlClasses[i].getDescription());
            classBean.setId(umlClasses[i].getId());
            classBean.setVersion(umlClasses[i].getProjectVersion());
            
            classBean.setServiceName(sBean.getServiceName());
            classBean.setServiceUrl(sBean.getServiceUrl());
            classBean.setIcon(sBean.getIcon());
            classBean.needImpl(sBean.needImpl());

            SemanticMetadata[] semanticMetadata = umlClasses[i].getSemanticMetadataCollection().getSemanticMetadata();
            String classCDEName = "";
            if ((semanticMetadata != null)  &&  (semanticMetadata.length >= 1)){
                for (int k = 0; k < semanticMetadata.length; k++) {
                    classCDEName = classCDEName + " "+ semanticMetadata[k].getConceptName() ;
                }
            }
            classBean.setCDEName(classCDEName);
            
            
            // now set the attributes..
            ArrayList<AttributeBean> attributeList = new ArrayList(100);
            UMLAttribute[] umlAttributes = umlClasses[i].getUmlAttributeCollection().getUMLAttribute();
            if ((umlAttributes != null)  &&  (umlAttributes.length > 1)){
                for (int j = 0; j < umlAttributes.length; j++) {
                    AttributeBean attributeBean = new AttributeBean();
                    attributeBean.setAttributeName(umlAttributes[j].getName());
                    /**   get the CDE name or Display Name by concatenation of the concept names in reverse order. */
                    String cdeName = "";
                    SemanticMetadata[]  attributeMetadata =  umlAttributes[j].getSemanticMetadataCollection().getSemanticMetadata();
                    
                    for (int k = 0; k < attributeMetadata.length; k++) {
                        cdeName = cdeName + " "+ attributeMetadata[k].getConceptName() ;
                    }
                    attributeBean.setDisplayName(cdeName);
                    attributeBean.setCDEName(cdeName);
//                    System.out.println( classBean.getClassName()+ ":" + cdeName );
                    attributeList.add(attributeBean);
                }
            }
            classBean.setAttributes(attributeList);
            
            // now bind the class Obj with the Registry.
            bindClassByRefId(classBean);
            serviceClassList.add(classBean);
        }
        // bind the class list to the service name.
        bindClassListByServiceName(sBean.getServiceName(), serviceClassList); //
        
        // now set the umlAssociations with the classes in registry :
        UMLAssociation[] umlAssociations = domainModel.getExposedUMLAssociationCollection().getUMLAssociation();
        
        for (int i = 0; i < umlAssociations.length; i++) {
            boolean biDirectional = umlAssociations[i].isBidirectional();
            String sourceRef = umlAssociations[i].getSourceUMLAssociationEdge().getUMLAssociationEdge().getUMLClassReference().getRefid();
            String targetRef = umlAssociations[i].getTargetUMLAssociationEdge().getUMLAssociationEdge().getUMLClassReference().getRefid();
            String targetRole = umlAssociations[i].getTargetUMLAssociationEdge().getUMLAssociationEdge().getRoleName();
            
            ClassBean sourceClassBean = null;
            ClassBean targetClassBean = null;
            try {
                sourceClassBean = lookupClassByRefId(sourceRef);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            try {
                targetClassBean = lookupClassByRefId(targetRef);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            if (sourceClassBean != null){
                sourceClassBean.addAssociatedClass(targetRef);
                sourceClassBean.addAssociationRoleName(targetRef, targetRole);
            } // put the cross references for the source into target class...
            if ( (targetClassBean != null ) && biDirectional ) {
                targetClassBean.addAssociatedClass(sourceRef);
                String sourceRole = umlAssociations[i].getSourceUMLAssociationEdge().getUMLAssociationEdge().getRoleName();
                targetClassBean.addAssociationRoleName(sourceRef, sourceRole);
            }
        }
        
        System.out.println("Successful loading of domainModel for Project: "+domainModel.getProjectLongName());
    }
    
}
