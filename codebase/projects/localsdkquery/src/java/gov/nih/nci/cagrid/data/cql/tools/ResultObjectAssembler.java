/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Group;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;


public class ResultObjectAssembler extends AbstractResultObjectAssembler {
    
    private Map subCqlMap = new HashMap();
    String targetObjectClassIDToReplace = "";
    String targetObjectIDToReplace = "";
    
    public ResultObjectAssembler(Session session,String dialect) {
        super(session,dialect);
        
    }
    public ResultObjectAssembler(String hibernateCfgFile,String dataBaseURL,String schemaOrUser,String dialect) {
        super(hibernateCfgFile,dataBaseURL,schemaOrUser,dialect);
        
    }
    private List execute(String cqlStr,String[] returnAttrbs,String assocClassName) throws Exception {
        List results = executeQry(cqlStr);
        List convertedObjectList = null;
        
        try {
            convertedObjectList = ToolUtil.buildObjcets(results,returnAttrbs,assocClassName);
        } catch (Exception e) {
            throw new Exception("Error building Objects for  " + assocClassName, e);
        }
        
        return convertedObjectList;
    }
    
    public List buildResultObjects(List targetObjects,CQLQuery query) throws Exception{
        gov.nih.nci.cagrid.cqlquery.Object target = query.getTarget();
        CQLBuilder builder = new CQLBuilder(query);
        subCqlMap = builder.getSubCQL();
        
        for (int i=0;i<targetObjects.size();i++){
            Object targetObject = targetObjects.get(i);
            Class targetObjectClass = Class.forName(target.getName());
            
            // check for Groups
            if (target.getGroup() != null) {
                //process Group
                processGroup(target.getGroup(),targetObject,targetObjectClass);
            }
            //check for Assoc
            if (target.getAssociation() != null) {
                //process Association
                processAssociation(target.getAssociation(),targetObject,targetObjectClass);
            }
        }
        //tempDisplay(targetObjects);
        return targetObjects;
    }
    
    private void processGroup(Group  group,
            Object targetObject,
            Class targetObjectClass) throws Exception{
        
        if (group.getGroup() != null) {
            Group[]  groupList = group.getGroup();
            for (int i=0;i<groupList.length;i++) {
                processGroup(groupList[i],targetObject,targetObjectClass);
            }
        }
        
        
        if (group.getAssociation() != null) {
            Association[]  assocList = group.getAssociation();
            for (int i=0;i<assocList.length;i++) {
                processAssociation(assocList[i],targetObject,targetObjectClass);
            }
            
        }
        
    }
    
    private void processAssociation(Association  association,
            Object targetObjectValue,
            Class targetObjectClass) throws Exception {
        String assocClassName = association.getName();
        String roleName = association.getRoleName();
        
        String[] returnAttrbs = null;
        if(association.getReturnAttributes() != null ) {
            returnAttrbs = association.getReturnAttributes().getReturnAttribute();
        }
        String key = assocClassName+"-"+roleName;
        Object obj = subCqlMap.get(key);
        if (obj == null) {
            throw new Exception("CQL NOT FOUND FOR THIS ASSOCIATION " + key);
        }
        SubCQL subCQL = (SubCQL)obj;
        
        String cqlStr = subCQL.getCQLString();
        
        //if parent is target , only one ID to replace
        //which is target Class ID
        List parents = subCQL.getParents();
        String targetObjectPK = ToolUtil.performGetOperation(targetObjectClass,targetObjectValue,"id").toString();
        cqlStr = cqlStr.replaceAll(targetObjectClass.getName()+"_ID",targetObjectPK);
        
        if (parents.size() == 0) {
            // System.out.println(cqlStr);
            targetObjectClassIDToReplace = targetObjectClass.getName()+"_ID";
            List results = execute(cqlStr,returnAttrbs,assocClassName);
            //SET RESULTS TO TARGET OBJECT ...
            ToolUtil.performSetOperation(roleName,targetObjectClass,targetObjectValue,results);
        } else {
            //If not Target Object, the executed CQL results needs to set to its Parent class
            //recursively back to Target Object.
            int index = parents.size()-1;
            Parent parent = (Parent)parents.get(index);
            processParentTree(parent,targetObjectClass,targetObjectValue,cqlStr,assocClassName,roleName,returnAttrbs,parents,index);
        }
        if (association.getAssociation() != null ) {
            processAssociation(association.getAssociation(),targetObjectValue,targetObjectClass);
        }
        if (association.getGroup() != null) {
            processGroup(association.getGroup(),targetObjectValue,targetObjectClass);
        }
        
    }
    
    private void processParentTree(Parent parent ,Class targetObjectClass, Object targetObjectValue, String cqlStr,
            String assocClassName, String roleName, String[] returnAttrbs,List parents, int index ) throws Exception {
        
        String parentClassName = parent.getParentAssocationClassName();
        String parentRoleName = parent.getParentAssocationRoleName();
        // System.out.println(cqlStr);
        //Get Parent Object based on role name
        Object parentObject = ToolUtil.performGetOperation(targetObjectClass,targetObjectValue,parentRoleName);
        Class parentClass = Class.forName(parentClassName);
        
        if(parentRoleName.endsWith("Collection")) {
            //process collection
            processCollectionAssociation(parentObject,parentClass,cqlStr,assocClassName, roleName, returnAttrbs, parents, index);
        } else {
            processNonCollectionAssociation(parentObject,parentClass,cqlStr,assocClassName, roleName, returnAttrbs, parents, index);
        }
        //return index;
    }
    
    private void processNonCollectionAssociation(Object parentObject,Class parentClass, String cqlStr,
            String assocClassName, String roleName, String[] returnAttrbs , List parents, int index) throws Exception {
        String id = ToolUtil.performGetOperation(parentClass,parentObject,"id").toString();
        String _cqlStr = cqlStr.replaceAll(parentClass.getName() +"_ID",id);
        
        
        
        index--;
        //System.out.println(_cqlStr);
        if (index >= 0) {
            Parent grandParent = (Parent)parents.get(index);
            processParentTree(grandParent,parentClass,parentObject,_cqlStr,assocClassName, roleName, returnAttrbs, parents, index);
            index++;
        } else {
            //                  System.out.println(_cqlStr);
            List results = execute(_cqlStr,returnAttrbs,assocClassName);
            ToolUtil.performSetOperation(roleName,parentClass,parentObject,results);
        }
        //return index;
    }
    
    private void processCollectionAssociation(Object parentObject,Class parentClass, String cqlStr,
            String assocClassName, String roleName, String[] returnAttrbs , List parents, int index) throws Exception {
        Collection parentObjectCollection = (Collection)parentObject;
        Iterator parentObjectItr= parentObjectCollection.iterator();
        
        while (parentObjectItr.hasNext()){
            Object indivParentObjectValue = parentObjectItr.next();
            // get Grand Parent if any ...
            String id = ToolUtil.performGetOperation(parentClass,indivParentObjectValue,"id").toString();
            String _cqlStr = cqlStr.replaceAll(parentClass.getName()+"_ID",id);
            
            index--;
            //  System.out.println(_cqlStr);
            if (index >= 0) {
                Parent grandParent = (Parent)parents.get(index);
                processParentTree(grandParent,parentClass,indivParentObjectValue,_cqlStr,assocClassName, roleName, returnAttrbs, parents, index);
                index++;
            } else {
                //                  System.out.println(_cqlStr);
                List results = execute(_cqlStr,returnAttrbs,assocClassName);
                ToolUtil.performSetOperation(roleName,parentClass,indivParentObjectValue,results);
            }
        }
        //return index;
    }
    
    // ------------ F I N I S H
    
    // test method to see the results when debugging ...
    private void tempDisplay(List targetObjects) {
        
        
    /*
            for (int i=0;i<targetObjects.size();i++){
     
                ParticipantMedicalIdentifier pmi = (ParticipantMedicalIdentifier)targetObjects.get(i);
     
                Participant p = pmi.getParticipant();
     
                System.out.print (p.getFirstName() + "   " + p.getLastName());
     
                Collection l = p.getAnnotationEventParametersCollection();
                Iterator ltr = l.iterator();
                while (ltr.hasNext()) {
                    AnnotationEventParameters aep = (AnnotationEventParameters)ltr.next();
                    Collection l1 = aep.getAnnotationSetCollection();
                    Iterator ltr1 = l1.iterator();
                    while (ltr1.hasNext()) {
                        NottinghamHistopathologicGrade n = (NottinghamHistopathologicGrade)ltr1.next();
                        System.out.print ("   " + n.getTotalScore() + " " + n.getMitoticCount() + " ");
                    }
                }
                System.out.println("   ");
     
            }
     
     
        for (int i=0;i<targetObjects.size();i++){
            edu.duke.cabig.tumorregistry.domain.Patient p = (edu.duke.cabig.tumorregistry.domain.Patient)targetObjects.get(i);
     
            System.out.print (p.getId());
     
            Collection l = p.getDiagnosisCollection();
            Iterator ltr = l.iterator();
            while (ltr.hasNext()) {
                edu.duke.cabig.tumorregistry.domain.Diagnosis aep = (edu.duke.cabig.tumorregistry.domain.Diagnosis)ltr.next();
                System.out.print ("  DIAG DATE : " + aep.getInitialDiagnosisDate() + " ");
                Collection l1 = aep.getActivityCollection();
                Iterator ltr1 = l1.iterator();
                while (ltr1.hasNext()) {
                    edu.duke.cabig.tumorregistry.domain.Activity n = (edu.duke.cabig.tumorregistry.domain.Activity)ltr1.next();
                    System.out.print (" ACT STRT DATE :  " + n.getStartDate() + " ");
                }
     
                Collection l2 = aep.getFollowUpCollection();
                Iterator ltr2 = l2.iterator();
                while (ltr2.hasNext()) {
                    edu.duke.cabig.tumorregistry.domain.Followup n = (edu.duke.cabig.tumorregistry.domain.Followup)ltr2.next();
                    edu.duke.cabig.tumorregistry.domain.Recurrence r = n.getRecurrence();
                    System.out.print (" TYPE :  " + r.getType() + " ");
                }
     
            }
            System.out.println("   ");
            }
     */
        
        
    }
    
    
    public static void main(String[] args ) {
        String queryDir = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\";
        String qryFile = "demo-cae.xml";
    }
}


    /*
     
     */