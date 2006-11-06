
package edu.duke.cabig.catrip.gui.discovery;

import edu.duke.cabig.catrip.gui.common.AttributeBean;
import edu.duke.cabig.catrip.gui.common.ClassBean;
import edu.duke.cabig.catrip.gui.common.ServiceMetaDataBean;
import gov.nih.nci.cagrid.metadata.common.SemanticMetadata;
import gov.nih.nci.cagrid.metadata.common.UMLAttribute;
import gov.nih.nci.cagrid.metadata.common.UMLClass;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.dataservice.UMLAssociation;
import gov.nih.nci.cagrid.metadata.dataservice.UMLGeneralization;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * This class acts as Registry for the Domain Model Metadata.
 *
 * @author Sanjeev Agarwal
 */
public class DomainModelMetaDataRegistry {
    
    /** This Map contains the ClassBean instances vs. class RefIds as defined in the Domain Model extract */
    private static HashMap classMap = new HashMap(200);
    
    /** This Map contains the ClassBean instances vs. fully classified class names as defined in the Domain Model extract */
    private static HashMap classNameClassMap= new HashMap(200);
    
    /** Map of the class List vs service/model name. */
    private static HashMap domainModelMap = new HashMap(20);
    
    /** Map of umlGeneralizationCollection refIds.. key = subClass refID, value = superClass refID */
    private static HashMap classGeneralizationMap = new HashMap(200);
    
    
    /** Creates a new instance of DomainModelMetaDataRegistry */
    public DomainModelMetaDataRegistry() {
    }
    
    public static ClassBean lookupClassByRefId(String refId) {
        return (ClassBean) classMap.get(refId);
    }
    
    public static ClassBean lookupClassByFullyQualifiedName(String className) {
        return (ClassBean) classNameClassMap.get(className);
    }
    
    public static void bindClassByRefId(ClassBean classBean){
        classMap.put(classBean.getId(), classBean);
        classNameClassMap.put(classBean.getFullyQualifiedName(), classBean);
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

        ArrayList<ClassBean> serviceClassList = new ArrayList(100); // sanjeev: The collection of classes that belongs to only this DomainModel.
        
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
            
            SemanticMetadata[] semanticMetadata = umlClasses[i].getSemanticMetadata(); //umlClasses[i].getSemanticMetadataCollection().getSemanticMetadata();
            String classCDEName = "";
//            if ((semanticMetadata != null)  &&  (semanticMetadata.length >= 1)){
//                for (int k = 0; k < semanticMetadata.length; k++) {
//                    classCDEName = classCDEName + " "+ semanticMetadata[k].getConceptName() ;
//                }
//            }
            
            /**   get the CDE name or Display Name by concatenation of the concept names in reverse order. */
            Hashtable ht = new Hashtable();
            ArrayList v = new ArrayList();
            
            if ((semanticMetadata != null)  &&  (semanticMetadata.length >= 1)){
                int numConcepts = semanticMetadata.length;
                for (int k = 0; k < numConcepts; k++) {
                    Integer order = semanticMetadata[k].getOrder();
                    ht.put(order, semanticMetadata[k].getConceptName());
                    v.add(order);
                }
            }
            Collections.sort(v);
            for (int j = v.size()-1 ; j >= 0; j--) {
                Integer order = (Integer)v.get(j);
                classCDEName = classCDEName + " "+ht.get(order);
            }
            
            classBean.setCDEName(classCDEName.trim());
            
            
            // sanjeev: now set the attributes..
            ArrayList<AttributeBean> attributeList = new ArrayList(100);
            ArrayList<String> attributeListUnique = new ArrayList(100); // sanjeev: just to check for the duplictate entries..
            
            UMLAttribute[] umlAttributes = umlClasses[i].getUmlAttributeCollection().getUMLAttribute();
            if ((umlAttributes != null)  &&  (umlAttributes.length > 1)){
                for (int j = 0; j < umlAttributes.length; j++) {
                    AttributeBean attributeBean = new AttributeBean();
                    attributeBean.setAttributeName(umlAttributes[j].getName());
                    /**   get the CDE name or Display Name by concatenation of the concept names in reverse order. */
                    String cdeName = "";
                    Hashtable htt = new Hashtable();
                    ArrayList vv = new ArrayList();
                    
                    SemanticMetadata[]  attributeMetadata =  umlAttributes[j].getSemanticMetadata(); //umlAttributes[j].getSemanticMetadataCollection().getSemanticMetadata();
//                    for (int k = 0; k < attributeMetadata.length; k++) {
//                        cdeName = cdeName + " "+ attributeMetadata[k].getConceptName() ;
//                    }
                    
                    int numConcepts = attributeMetadata.length;
                    for (int k = 0; k < numConcepts; k++) {
                        Integer order = attributeMetadata[k].getOrder();
                        htt.put(order, attributeMetadata[k].getConceptName());
                        vv.add(order);
                    }
                    Collections.sort(v);
                    for (int jj = numConcepts-1 ; jj >= 0; jj--) {
                        Integer order = (Integer)vv.get(jj);
                        cdeName = cdeName + " "+htt.get(order);
                    }
                    
                    attributeBean.setDisplayName(cdeName);
                    attributeBean.setCDEName(cdeName);
//                    System.out.println( classBean.getClassName()+ ":" + cdeName );
                    // sanjeev: check for duplicate entries..
                    if (!attributeListUnique.contains(attributeBean.getAttributeName())){
                        attributeList.add(attributeBean);
                        attributeListUnique.add(attributeBean.getAttributeName());
                    }
                }
            }
            classBean.setAttributes(attributeList);
            
            // sanjeev: now bind the class Obj with the Registry.
            bindClassByRefId(classBean);
            serviceClassList.add(classBean);
        }
        // sanjeev: bind the class list to the service name.
        bindClassListByServiceName(sBean.getServiceName(), serviceClassList); //
        
        // sanjeev: now set the umlAssociations with the classes in registry :
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
            } // sanjeev: put the cross references for the source into target class...
            if ( (targetClassBean != null ) && biDirectional ) {
                targetClassBean.addAssociatedClass(sourceRef);
                String sourceRole = umlAssociations[i].getSourceUMLAssociationEdge().getUMLAssociationEdge().getRoleName();
                targetClassBean.addAssociationRoleName(sourceRef, sourceRole);
            }
            
        }
        
        
        
        // sanjeev: load association data from the umlGeneralizationCollection also..
        UMLGeneralization[] umlUMLGeneralization = domainModel.getUmlGeneralizationCollection().getUMLGeneralization();
        for (int i = 0; i < umlUMLGeneralization.length; i++) {
            //  sanjeev: get  superRefId subRefId
            String superRefId = umlUMLGeneralization[i].getSuperClassReference().getRefid();
            String subRefId = umlUMLGeneralization[i].getSubClassReference().getRefid();
            
            // sanjeev: add to the umlUMLGeneralization map.. used for recursive action..
            getClassGeneralizationMap().put(subRefId, superRefId);
//            setSuperClassAssociations(subRefId, superRefId, domainModel);
        }
        
        // sanjeev: now as the ClassGeneralizationMap is ready.. set the associations from super class to sub class recursively..
        HashMap subSuperclassMap = getClassGeneralizationMap();
        Object[] subrefs = subSuperclassMap.keySet().toArray();
        for (int i = 0; i < subrefs.length; i++) {
            String subRefId = (String)subrefs[i];
            String superRefId = (String)subSuperclassMap.get(subRefId);
            setSuperClassAssociations(subRefId, superRefId, domainModel);
        }
        
        
        
        // sanjeev: by now the subclasses are added to all super classes. so add the subclasses also with same role.
        addSubClassesAsSameRole(domainModel);
        
        
        System.out.println("Successful loading of domainModel for Project: "+domainModel.getProjectLongName());
    }
    
    
    
    
    public static HashMap getClassGeneralizationMap() {
        return classGeneralizationMap;
    }
    
    public static void setClassGeneralizationMap(HashMap aClassGeneralizationMap) {
        classGeneralizationMap = aClassGeneralizationMap;
    }
    
    
    public static void setSuperClassAssociations(String subRefId, String superRefId, DomainModel domainModel) {
        
        if (getClassGeneralizationMap().get(superRefId) != null){
            // sanjeev: it has super Class also there..
            String superSuperClassrefId = (String) getClassGeneralizationMap().get(superRefId);
            setSuperClassAssociations(superRefId, superSuperClassrefId, domainModel);
        }
        
        
        // sanjeev: locate the subclass and super class..
        ClassBean subClass = null;
        ClassBean superClass = null;
        
        try {
            subClass = lookupClassByRefId(subRefId);
            superClass = lookupClassByRefId(superRefId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//            String superName = superClass.getClassName();
//            String subName = subClass.getClassName();
//            System.out.println("XXXXX   Class:  "+subName+"  --extends->   "+superName);
        
        // sanjeev: set the super Class RefId to the Sub Class..
        try {
            subClass.setSuperClassRefId(superRefId);
            subClass.setSuperClassName(superClass.getClassName());
        } catch (Exception e) {
            System.out.println("#### the Super class Id is:"+superRefId);
            e.printStackTrace();
        }
        
        // sanjeev: set the subclass Id to the super class..
        try {
            superClass.addSubClassId(subRefId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        // sanjeev: attach all the association of super class to sub class.. one by one..
        ArrayList<String>  assClassList = superClass.getAssociatedClasses();
        for (int k = 0; k < assClassList.size(); k++) {
            String refID = assClassList.get(k);
            String roleName = superClass.getAssociationRoleName(refID);
            subClass.addAssociatedClass(refID);
            subClass.addAssociationRoleName(refID, roleName);
//                System.out.println("XXXXX   Class:  "+subName+"  --extends->   "+superName +"  : ClassID:"+refID+ ": RoleName :"+roleName);
            // sanjeev: add it to a seperate list also..
            subClass.addSuperClassAssociatedClassList(refID);
        }
        
    }
    
    
    
    public static void addSubClassesAsSameRole(DomainModel domainModel){
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
                targetClassBean = lookupClassByRefId(targetRef);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            
            if ((sourceClassBean != null) && (targetClassBean != null )){
                
                ArrayList<String> subClasses =  getAllSubClassFromHierarchy( targetClassBean );
                
                for (int j = 0; j < subClasses.size(); j++) {
                    String subClassId = (String)subClasses.get(j);
                    sourceClassBean.addAssociatedClass(subClassId);
                    sourceClassBean.addAssociationRoleName(subClassId, targetRole);
                }
                
                
            } // sanjeev: put the cross references for the source into target class...
            if ( (sourceClassBean != null) && (targetClassBean != null ) && biDirectional ) {
                
                ArrayList<String> subClasses =  getAllSubClassFromHierarchy( sourceClassBean );
                String sourceRole = umlAssociations[i].getSourceUMLAssociationEdge().getUMLAssociationEdge().getRoleName();
                
                for (int j = 0; j < subClasses.size(); j++) {
                    String subClassId = (String)subClasses.get(j);
                    targetClassBean.addAssociatedClass(subClassId);
                    targetClassBean.addAssociationRoleName(subClassId, sourceRole);
                }
                
            }
            
        }
        
        
    }
    
    
    
    
    
    
    
    public static ArrayList<String> getAllSubClassFromHierarchy(ClassBean cBean){
        ArrayList<String> subClasses = new ArrayList<String>(100);
        ArrayList<String> subclassRefs = cBean.getSubClassIds();
        
        ClassBean tmpBean = null;
        
        for (int i = 0; i < subclassRefs.size(); i++) {
            String refId = subclassRefs.get(i);
            try {
                tmpBean = lookupClassByRefId(refId);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            subClasses.add(refId);
            subClasses.addAll(getAllSubClassFromHierarchy(tmpBean));
        }
        
        return subClasses;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
