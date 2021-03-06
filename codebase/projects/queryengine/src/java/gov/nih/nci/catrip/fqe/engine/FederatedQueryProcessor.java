/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.engine;


import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;


import gov.nih.nci.catrip.dcql.Association;
import gov.nih.nci.catrip.dcql.DCQLQueryDocument;
import gov.nih.nci.catrip.dcql.ForeignAssociation;
import gov.nih.nci.catrip.dcql.Group;
import gov.nih.nci.catrip.dcql.TargetObject;
import gov.nih.nci.catrip.fqe.exception.QueryExecutionException;

import java.util.List;

/**
 * FederatedQueryProcessor decomposes the DCQL into individual CQLs
 * Each individual CQL is executed by specified grid service in serviceURL 
 * Results obtained from DCQLQueryDocument are joined by Result Aggregator
 * @author Srini Akkala
 */
class FederatedQueryProcessor {
    
    public FederatedQueryProcessor() {
    }
    
    /**
     * process root element DCQLQuery Element
     * @param dcqlQryPlan
     * @return
     */
    CQLQuery processDCQLQueryPlan(DCQLQueryDocument dcqlQryPlan) throws QueryExecutionException {        
        CQLQuery cqlQuery = null;
        
        // Get the target object.        
        TargetObject targetObject = dcqlQryPlan.getDCQLQuery().getTargetObject();
        gov.nih.nci.cagrid.cqlquery.Object cqlObject = processTargetObject(targetObject);             
        cqlQuery = new CQLQuery();
        cqlQuery.setTarget(cqlObject);
        return cqlQuery;
    }

    /**
     * process TargetObject
     *  A DCQL target object can contain
     *   Attribute (OR)
     *   GROUP (OR)
     *   Association (OR)
     *   ForeignAssociation 
     *   Check for the existance of above elements and process them . 
     * @param dcqlObject
     * @return
     */
    private gov.nih.nci.cagrid.cqlquery.Object processTargetObject (TargetObject dcqlObject)  throws QueryExecutionException{
        
        // initialize CQLObject .all the nested Queries would get resolved and attached to this CQL object .
        gov.nih.nci.cagrid.cqlquery.Object cqlObject =  new gov.nih.nci.cagrid.cqlquery.Object();
        
        cqlObject.setName(dcqlObject.getName()); 


         
        // check for any attribute 
        if (dcqlObject.isSetAttribute()) {
            gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = processAttribute(dcqlObject.getAttribute());
            cqlObject.setAttribute(cqlAttribute);
        }
        
        //check for group
        if (dcqlObject.isSetGroup()) {
            //convert group and attach group to CQL object
             gov.nih.nci.cagrid.cqlquery.Group cqlGroup = processGroup(dcqlObject.getGroup());     
             cqlObject.setGroup(cqlGroup);              
        }
        
        //check for Association
        if (dcqlObject.isSetAssociation()) {
            //Convert into CQL Associoation 
            gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = processAssociation(dcqlObject.getAssociation());             
             cqlObject.setAssociation(cqlAssociation);
        }       
        
        //check for ForeignAssociation
        if (dcqlObject.isSetForeignAssociation()){
            gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation(dcqlObject.getForeignAssociation());            
            if (resultedGroup.getAttribute().length > 0 ) cqlObject.setGroup(resultedGroup);
            
        }

        return cqlObject;
        
    }
    
    /**
     * process Attribute .
     * builds CQL Attribute. name,value and predicate of dcql Attribute would be set to name, value and prediccate of CQL Attribute
     * @param dcqlAttribute
     * @return
     */
    private gov.nih.nci.cagrid.cqlquery.Attribute processAttribute(gov.nih.nci.catrip.cqlquery.Attribute dcqlAttribute) {
        gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = new gov.nih.nci.cagrid.cqlquery.Attribute(); 
        cqlAttribute.setName(dcqlAttribute.getName());
        cqlAttribute.setValue(dcqlAttribute.getValue());
        cqlAttribute.setPredicate(gov.nih.nci.cagrid.cqlquery.Predicate.fromValue(dcqlAttribute.getPredicate().toString()));
        
        return cqlAttribute;
     
    }

    /**
     * process Group
     * builds CQL Group . 
     * Group can contain nested Groups or Association or ForeignAssociation
     * Based on the existance of above elements , the elements are processed and DCQL Groups or Associations
     * are converted into CQL Groups or Associations and those would get attached to CQL Group
     * @param dcqlGroup
     * @return
     * @throws QueryExecutionException
     */
     private gov.nih.nci.cagrid.cqlquery.Group processGroup(Group  dcqlGroup)  throws QueryExecutionException {
         //convert basic group information and attach group to CQL object
          gov.nih.nci.cagrid.cqlquery.Group cqlGroup = new gov.nih.nci.cagrid.cqlquery.Group();
          //attach logical relationship and any aatributes .
          
              cqlGroup.setLogicRelation(gov.nih.nci.cagrid.cqlquery.LogicalOperator.fromValue(dcqlGroup.getLogicRelation().toString()));
              cqlGroup.setAttribute(convertDcql2CqlAttributeArray(dcqlGroup.getAttributeArray()));
              

              //associations
              if (dcqlGroup.getAssociationArray().length > 0) {
                  cqlGroup = attachAssociationArrayToGroup(dcqlGroup,cqlGroup);
              }
             //groups
              if (dcqlGroup.getGroupArray().length > 0) {
                  cqlGroup = attachNestedGroupArray(dcqlGroup,cqlGroup);
              }
             //foreign associations
             if (dcqlGroup.getForeignAssociationArray().length > 0) {
                 cqlGroup = attachForeignAssociationArrayToGroup(dcqlGroup,cqlGroup);
             }

         return cqlGroup;

         
     }
    
    /**
     * process Association
     * convert DCQL Association into CQL Association.
     * @param dcqlAssociation
     * @return
     * @throws QueryExecutionException
     */
    private gov.nih.nci.cagrid.cqlquery.Association processAssociation (Association  dcqlAssociation) throws QueryExecutionException {

        gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = new gov.nih.nci.cagrid.cqlquery.Association();
        cqlAssociation.setRoleName(dcqlAssociation.getRoleName());        
        
        //gov.nih.nci.cagrid.dcql.Object associatedObject = dcqlAssociation.getObject();
        cqlAssociation.setName(dcqlAssociation.getName());
        
        //check for nested associations 
         if (dcqlAssociation.isSetAssociation()) {
             Association nestedAssociation = dcqlAssociation.getAssociation();
             //call this method recursively...
             cqlAssociation.setAssociation(processAssociation(nestedAssociation));
         } 
         //check for attributes 
         if (dcqlAssociation.isSetAttribute()) cqlAssociation.setAttribute(processAttribute(dcqlAssociation.getAttribute()));
         //check for foreign associations         
         if (dcqlAssociation.isSetForeignAssociation()) {
            gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation(dcqlAssociation.getForeignAssociation());
            if (resultedGroup.getAttribute().length > 0 ) cqlAssociation.setGroup(resultedGroup);
         }
         //check for groups          
         if (dcqlAssociation.isSetGroup()) cqlAssociation.setGroup(processGroup(dcqlAssociation.getGroup()));
         
         return cqlAssociation;
    } 
    
    /**
     * process ForeignAssociation
     * ForeignAssociation is basically a Query that can be executed by a grid service mentioned in serviceURL attribute
     * As ForeignAssocitaion itself is a DCQL Object , the Object is processed and CQL Query would be passed to FederatedQueryExecutor
     * Obtained results from services are then aggreated using CDEs by ResultAggregator
     * @param foreignAssociation
     * @return
     * @throws QueryExecutionException
     */
    private gov.nih.nci.cagrid.cqlquery.Group  processForeignAssociation (ForeignAssociation  foreignAssociation)  throws QueryExecutionException {
        //get Foreign Object 
         TargetObject dcqlObject = foreignAssociation.getForeignObject();
         gov.nih.nci.cagrid.cqlquery.Object cqlObject = processTargetObject(dcqlObject);
         
         CQLQuery cqlQuery = new CQLQuery();
         //CQLQueryDocument.CQLQuery cqlQry = cqlQueryDoc.addNewCQLQuery();
        
         cqlQuery.setTarget(cqlObject);
         
         //Execute Foreign Query  ..... 
         FederatedQueryExecutor fqexe = new FederatedQueryExecutor();
         CQLQueryResults cqlResults = fqexe.executeCQLQuery(cqlQuery,dcqlObject.getServiceURL());
         
         //ResultAggregator resultAggregator = new ResultAggregator(foreignAssociation.getJoinCondition() , foreignAssociation.getForeignObject().getName());
          ResultAggregator resultAggregator = new ResultAggregator(foreignAssociation.getJoinCondition());
         List cdeList = resultAggregator.processResults(cqlResults);
         gov.nih.nci.cagrid.cqlquery.Group criteriaGroup = resultAggregator.buildGroup(cdeList);
          //gov.nih.nci.cagrid.cqlquery.Group criteriaGroup= new gov.nih.nci.cagrid.cqlquery.Group();
         return criteriaGroup;
    }
    
    /**
     * get the attribute Array from DCQL and convert into CQL Attribute 
     * @param dcqlAttributeArray
     * @return
     */
    private gov.nih.nci.cagrid.cqlquery.Attribute[] convertDcql2CqlAttributeArray(gov.nih.nci.catrip.cqlquery.Attribute[] dcqlAttributeArray )  {
        
        gov.nih.nci.cagrid.cqlquery.Attribute[] cqlAttributeArray = new gov.nih.nci.cagrid.cqlquery.Attribute[dcqlAttributeArray.length];

        for (int i=0;i<dcqlAttributeArray.length;i++){
             gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = processAttribute((gov.nih.nci.catrip.cqlquery.Attribute)dcqlAttributeArray[i]);
             cqlAttributeArray[i]=cqlAttribute;
        }

        return cqlAttributeArray;
        
    }
   

    /**
     * Converts and attach dcqlGroup Associations to CQL asssociations
     * @param dcqlGroup
     * @param cqlGroup
     * @return
     * @throws QueryExecutionException
     */
    private gov.nih.nci.cagrid.cqlquery.Group attachAssociationArrayToGroup(Group dcqlGroup,
                                                                                gov.nih.nci.cagrid.cqlquery.Group cqlGroup) throws QueryExecutionException {
        //associations
        Association[] dcqlAssociationArray = dcqlGroup.getAssociationArray();
        //build cqlAssociationArray
        gov.nih.nci.cagrid.cqlquery.Association[] cqlAssociationArray = new gov.nih.nci.cagrid.cqlquery.Association[dcqlAssociationArray.length];

        for (int i=0;i<dcqlAssociationArray.length;i++){
            gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = processAssociation((Association)dcqlAssociationArray[i]);
            cqlAssociationArray[i]=cqlAssociation;
        }
        cqlGroup.setAssociation(cqlAssociationArray);    

        return cqlGroup;
    }
    
    /**
     * Converts and attach nested dcqlGroup to CQL asssociations
     * @param dcqlGroup
     * @param cqlGroup
     * @return
     * @throws QueryExecutionException
     */
    private gov.nih.nci.cagrid.cqlquery.Group attachNestedGroupArray(Group dcqlGroup,
                                                                                gov.nih.nci.cagrid.cqlquery.Group cqlGroup) throws QueryExecutionException {
        //groups
         Group[] dcqlGroupArray =  dcqlGroup.getGroupArray();
         gov.nih.nci.cagrid.cqlquery.Group[] cqlGroupArray = new gov.nih.nci.cagrid.cqlquery.Group[dcqlGroupArray.length];
         
         for (int i=0;i<dcqlGroupArray.length;i++){
            gov.nih.nci.cagrid.cqlquery.Group cqlNestedGroup = processGroup((Group)dcqlGroupArray[i]);
            cqlGroupArray[i]=cqlNestedGroup;           
         }
         //cqlGroup.setGroup(cqlGroupArray);
          cqlGroup.setGroup(mergeGroups(cqlGroup,cqlGroupArray));
        return cqlGroup;
    }
    
    /**
     * process ForeignAssocation and attache the result Group to higher level Object.
     * @param dcqlGroup
     * @param cqlGroup
     * @return
     * @throws QueryExecutionException
     */
    private gov.nih.nci.cagrid.cqlquery.Group attachForeignAssociationArrayToGroup(Group dcqlGroup,
                                                                                gov.nih.nci.cagrid.cqlquery.Group cqlGroup) throws QueryExecutionException{
                                                                                 
         //foreign associations
         ForeignAssociation[] foreignAssociationArray = dcqlGroup.getForeignAssociationArray();         
         
         //gov.nih.nci.cagrid.cqlquery.Group[] groupsTomerge =  new gov.nih.nci.cagrid.cqlquery.Group[foreignAssociationArray.length];
             gov.nih.nci.cagrid.cqlquery.Group[] groupArray  = new gov.nih.nci.cagrid.cqlquery.Group[foreignAssociationArray.length];
             for (int i=0;i<foreignAssociationArray.length;i++){
                 // need to attach the results as crieteria ... 
                 gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation((ForeignAssociation)foreignAssociationArray[i]);
                 //groupsTomerge[i] = resultedGroup; 
                 if (resultedGroup.getAttribute().length > 0 ) {
                   groupArray[i]=resultedGroup;          
                   
                 }
             }
             //cqlGroup.setGroup(groupArray);
              cqlGroup.setGroup(mergeGroups(cqlGroup,groupArray));

        return cqlGroup;
    }
    
    /**
     * check for existing groups in a group. Append to the existing groups 
     * @param cqlGroup
     * @param groupArray
     * @return
     */
    private gov.nih.nci.cagrid.cqlquery.Group[] mergeGroups(gov.nih.nci.cagrid.cqlquery.Group cqlGroup,gov.nih.nci.cagrid.cqlquery.Group[] groupArray){
        gov.nih.nci.cagrid.cqlquery.Group[] existingGroupArray = cqlGroup.getGroup();
        
        // merge Group Arrays . 
        if (existingGroupArray != null) {            
            gov.nih.nci.cagrid.cqlquery.Group[] mergedGroups = new gov.nih.nci.cagrid.cqlquery.Group[existingGroupArray.length+groupArray.length];
            gov.nih.nci.cagrid.cqlquery.Group[] lerfOverGroups;
            int i,j,rest,merged;
            i=j=merged=0;
            
            // add groups of both arrays to merged groups . 
            while (i< existingGroupArray.length && j<groupArray.length) {
                mergedGroups[merged++] = existingGroupArray[i++];
                mergedGroups[merged++] = groupArray[j++];
            }
            
            // check for exahusted array .  Get the current index of un exhausted array and assign it to varible "rest"            
            if (i == existingGroupArray.length) {
                rest = j;
                lerfOverGroups=groupArray;
            } else {
                rest = i;
                lerfOverGroups=existingGroupArray;
            }
            
            // add the left over groups to mergedGroups.
            while (rest < lerfOverGroups.length) {
                mergedGroups[merged++] = lerfOverGroups[rest++];
            }
            return mergedGroups; 
        }        
        // return the same input array if there is no Group exists 
        return groupArray;        
    }


}