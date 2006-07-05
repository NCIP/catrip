/*
 * DomainModelMetaDataRegistry.java
 *
 * Created on June 26, 2006, 12:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import gov.nih.nci.cadsr.umlproject.domain.SemanticMetadata;
import gov.nih.nci.cagrid.metadata.common.UMLAttribute;
import gov.nih.nci.cagrid.metadata.common.UMLClass;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociation;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DomainModelMetaDataRegistry {
    
    private static HashMap classMap = new HashMap(100);
//    private static HashMap attributeMap = new HashMap(1000);
    
    private static HashMap domainModelMap = new HashMap(20);
    
    
    /** Creates a new instance of DomainModelMetaDataRegistry */
    public DomainModelMetaDataRegistry() {
    }
    
    public static ClassBean lookupClassByRefId(String refId) {
        return (ClassBean) classMap.get(refId);
    }
//    
//    public static AttributeBean lookupAttributeById(String id) {
//        return (AttributeBean) attributeMap.get(id);
//    }
    
    public static void bindClassByRefId(ClassBean classBean){
        classMap.put(classBean.getId(), classBean);
    }
    
//    public static void bindAttributeById(AttributeBean ab){
//        attributeMap.put(ab.getId(), ab);
//    }
    
    
    public static ArrayList<ClassBean> lookupClassListByDomainModelName(String id) {
        return (ArrayList) domainModelMap.get(id);
    }
    
    public static void bindClassListByDomainModelName(String domainModelName, ArrayList<ClassBean> classList){
        domainModelMap.put(domainModelName, classList);
    }
    
    public static void populateDomainModelMetaData(DomainModel model){
        
//            model.getProjectDescription();
//            model.getProjectLongName();
        ArrayList<ClassBean> classListTmp = new ArrayList(100); // this is the collection of classes belongs only to this DomainModel.
        
        UMLClass[] umlClasses = model.getExposedUMLClassCollection().getUMLClass();
        for (int i = 0; i < umlClasses.length; i++) {
            ClassBean classBean = new ClassBean();
            classBean.setClassName(umlClasses[i].getClassName() );
            classBean.setPackageName(umlClasses[i].getPackageName() );
            classBean.setDescription(umlClasses[i].getDescription());
            classBean.setId(umlClasses[i].getId()); 
            classBean.setVersion(umlClasses[i].getProjectVersion());
//            System.out.println("### got the class name :"+classBean.getClassName());
            // using this one set the CDE information of the class...
            SemanticMetadata[] smt = umlClasses[i].getSemanticMetadataCollection().getSemanticMetadata();
            String classCDEName = "";
            for (int k = 0; k < smt.length; k++) {
                classCDEName = classCDEName + " "+ smt[k].getConceptName() ;
            }
            classBean.setCDEName(classCDEName);
            
            
            // now set the attributes..
            ArrayList<AttributeBean> attList = new ArrayList(100);
            UMLAttribute[] umlAtt = umlClasses[i].getUmlAttributeCollection().getUMLAttribute();
            for (int j = 0; j < umlAtt.length; j++) {
                AttributeBean attributeBean = new AttributeBean();
                attributeBean.setAttributeName(umlAtt[j].getName());
                // now get the CDE name or Display Name by concatanatino of teh concept names in reverse order..
                String cdeName = "";
                SemanticMetadata[]  conCol =  umlAtt[j].getSemanticMetadataCollection().getSemanticMetadata();
                
                for (int k = 0; k < conCol.length; k++) {
                    cdeName = cdeName + " "+ conCol[k].getConceptName() ;
                }
                attributeBean.setDisplayName(cdeName);
                attributeBean.setCDEName(cdeName);
                attributeBean.setClassName(umlClasses[i].getClassName());
                attList.add(attributeBean);
                // not getting Id of the attribute to bind it to registry
            }
            classBean.setAttributes(attList);
            
            // now bind the class Obj with the Registry.
//            System.out.println("##:"+classBean.getClassName()+":"+classBean.getId());
            bindClassByRefId(classBean);
            classListTmp.add(classBean);
        }
        // bind the class list to the project short name.. must match with the service name.
        bindClassListByDomainModelName(model.getProjectShortName(), classListTmp);
        
        
        // now set the associations with the classes in registry :
        UMLAssociation[] associations = model.getExposedUMLAssociationCollection().getUMLAssociation(); 
       
        for (int i = 0; i < associations.length; i++) {
            String sourceRef = associations[i].getSourceUMLAssociationEdge().getUMLAssociationEdge().getUMLClassReference().getRefid();
            String targetRef = associations[i].getTargetUMLAssociationEdge().getUMLAssociationEdge().getUMLClassReference().getRefid();
            lookupClassByRefId(sourceRef).addAssociatedClass(targetRef);
        }
        
        
        System.out.println("Successful loading of domainModel for: "+model.getProjectLongName());
    }
    
    
}