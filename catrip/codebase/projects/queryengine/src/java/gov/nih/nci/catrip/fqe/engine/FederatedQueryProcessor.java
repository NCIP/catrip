package gov.nih.nci.catrip.fqe.engine;


import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;

import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;

import gov.nih.nci.cagrid.dcql.ForeignAssociation;

import java.util.List;


public class FederatedQueryProcessor {
    
    public FederatedQueryProcessor() {
    }
    
    /**
     * 
     * @param federatedQryPlan
     * @return
     */
    public CQLQueryDocument processFederatedQueryPlan(FederatedQueryPlanDocument federatedQryPlan) {
        /** Get the target object.
        */
        gov.nih.nci.cagrid.dcql.Object targetObject = federatedQryPlan.getFederatedQueryPlan().getTargetObject();        
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processDCQLObject(targetObject); 
        
        //craete instance of CQLQueryDocument
        CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
        CQLQueryDocument.CQLQuery cqlQry = cqlQueryDoc.addNewCQLQuery();
        cqlQry.setTarget(cqlObject);
        
        return cqlQueryDoc;
    }
    /*
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object  initializeCQLObject(String targetName){
        // initiallize CQLObject , all the nested Queries would get resolved and attached to this CQL object . 
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject =  caBIG.cql.x1.govNihNciCagridCQLQuery.Object.Factory.newInstance();
        cqlObject.setName(targetName);          
        return cqlObject;
    }
    */
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object processDCQLObject (gov.nih.nci.cagrid.dcql.Object dcqlObject) {
        
        // initialize CQLObject .all the nested Queries would get resolved and attached to this CQL object .
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject =  caBIG.cql.x1.govNihNciCagridCQLQuery.Object.Factory.newInstance();
        cqlObject.setName(dcqlObject.getName()); 
        //caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = initializeCQLObject(dcqlObject.getName());
        /**
         * A DCQL target object can contain
         * Attribute (OR)
         * GROUP (OR)
         * Association (OR)
         * ForeignAssociation 
         * 
         * check for any of the above accurances . 
         */
         
        // check for any attribute 
        if (dcqlObject.isSetAttribute()) {
            cqlObject.setAttribute(dcqlObject.getAttribute());
        }
        
        //check for group
        if (dcqlObject.isSetGroup()) {
            //convert basic group information and attach group to CQL object
             caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup = processDCQLGroup(dcqlObject.getGroup());     
             cqlObject.setGroup(cqlGroup);              
        }
        
        //check for Association
        if (dcqlObject.isSetAssociation()) {
            //Association contains DCQL object
            //Convert into CQL Associoation and Process DCQL Object
             caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociation = processDCQLAssociation(dcqlObject.getAssociation());             
             cqlObject.setAssociation(cqlAssociation);
        }       
        
        //check for ForeignAssociation
        if (dcqlObject.isSetForeignAssociation()){
            //processForeignAssociation(dcqlObject.getForeignAssociation());
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group resultedGroup = processForeignAssociation(dcqlObject.getForeignAssociation());
            cqlObject.addNewGroup();
            cqlObject.setGroup(resultedGroup);
            //groupsTomerge[i] = resultedGroup; 
            
            // DONT DELETE
           // cqlGroup.addNewGroup();
           // cqlGroup.setGroupArray(i,resultedGroup);
        }
        
        return cqlObject;
        
    }
    
   
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Group processDCQLGroup(gov.nih.nci.cagrid.dcql.Group  dcqlGroup) {
        //convert basic group information and attach group to CQL object
         caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup = caBIG.cql.x1.govNihNciCagridCQLQuery.Group.Factory.newInstance();
         //attach logical relationship and any aatributes .
         cqlGroup.setLogicRelation(dcqlGroup.getLogicRelation());
         cqlGroup.setAttributeArray(dcqlGroup.getAttributeArray());
         
        /**
         * Group can contain nested Group or Association or ForeignAssociation
         */
         //associations
         if (dcqlGroup.getAssociationArray().length > 0) {
             cqlGroup = setGroupAssociationArray(dcqlGroup,cqlGroup);
         }
        //groups
         if (dcqlGroup.getGroupArray().length > 0) {
             cqlGroup = setNestedGroupArray(dcqlGroup,cqlGroup);
         }
        //foreign associations
        if (dcqlGroup.getForeignAssociationArray().length > 0) {
            cqlGroup = setGroupForeignAssociationArray(dcqlGroup,cqlGroup);
        }
        return cqlGroup;
         /*
         gov.nih.nci.cagrid.dcql.Association[] dcqlAssociationArray = dcqlGroup.getAssociationArray();
         
         
         //build cqlAssociationArray
         caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] cqlAssociationArray = new caBIG.cql.x1.govNihNciCagridCQLQuery.Association[dcqlAssociationArray.length];
         
         for (int i=0;i<dcqlAssociationArray.length;i++){
             caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociation = processDCQLAssociation((gov.nih.nci.cagrid.dcql.Association)dcqlAssociationArray[i]);
             cqlAssociationArray[i]=cqlAssociation;
         }
         cqlGroup.setAssociationArray(cqlAssociationArray);
         */
         
        //groups

         /*
         gov.nih.nci.cagrid.dcql.Group[] dcqlGroupArray =  dcqlGroup.getGroupArray();
         
         caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] cqlGroupArray = new caBIG.cql.x1.govNihNciCagridCQLQuery.Group[dcqlGroupArray.length];
        
        for (int i=0;i<dcqlGroupArray.length;i++){
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup1 = processDCQLGroup((gov.nih.nci.cagrid.dcql.Group)dcqlGroupArray[i]);
            cqlGroupArray[i]=cqlGroup1;           
        }
        cqlGroup.setGroupArray(cqlGroupArray);
        */
        /*
         //foreign associations
         ForeignAssociation[] foreignAssociationArray = dcqlGroup.getForeignAssociationArray();
         
        //
        caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] groupsTomerge =  new caBIG.cql.x1.govNihNciCagridCQLQuery.Group[foreignAssociationArray.length];

        
         for (int i=0;i<foreignAssociationArray.length;i++){
             // need to attach the results as crieteria ... 
             caBIG.cql.x1.govNihNciCagridCQLQuery.Group resultedGroup = processForeignAssociation((ForeignAssociation)foreignAssociationArray[i]);
             //groupsTomerge[i] = resultedGroup; 
             
             // DONT DELETE
            cqlGroup.addNewGroup();
            cqlGroup.setGroupArray(i,resultedGroup); 
             //
         }
         */
         /*
         if (foreignAssociationArray.length > 0) {
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group aggregatedGroup = ResultAggregator.aggregateGroups(groupsTomerge);
            cqlGroup.addNewGroup();
            cqlGroup.setGroupArray(0,aggregatedGroup);      
         }
        */
        
    }
    
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Group setGroupAssociationArray(gov.nih.nci.cagrid.dcql.Group dcqlGroup,
                                                                                caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup){
        //associations
        gov.nih.nci.cagrid.dcql.Association[] dcqlAssociationArray = dcqlGroup.getAssociationArray();
        //build cqlAssociationArray
        caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] cqlAssociationArray = new caBIG.cql.x1.govNihNciCagridCQLQuery.Association[dcqlAssociationArray.length];

        for (int i=0;i<dcqlAssociationArray.length;i++){
            caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociation = processDCQLAssociation((gov.nih.nci.cagrid.dcql.Association)dcqlAssociationArray[i]);
            cqlAssociationArray[i]=cqlAssociation;
        }
        cqlGroup.setAssociationArray(cqlAssociationArray);    
        return cqlGroup;
    }
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Group setNestedGroupArray(gov.nih.nci.cagrid.dcql.Group dcqlGroup,
                                                                                caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup){
        //groups
         gov.nih.nci.cagrid.dcql.Group[] dcqlGroupArray =  dcqlGroup.getGroupArray();
         
         caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] cqlGroupArray = new caBIG.cql.x1.govNihNciCagridCQLQuery.Group[dcqlGroupArray.length];
         
         for (int i=0;i<dcqlGroupArray.length;i++){
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlNestedGroup = processDCQLGroup((gov.nih.nci.cagrid.dcql.Group)dcqlGroupArray[i]);
            cqlGroupArray[i]=cqlNestedGroup;           
         }
         cqlGroup.setGroupArray(cqlGroupArray);
        return cqlGroup;
    }
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Group setGroupForeignAssociationArray(gov.nih.nci.cagrid.dcql.Group dcqlGroup,
                                                                                caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup){
         //foreign associations
         ForeignAssociation[] foreignAssociationArray = dcqlGroup.getForeignAssociationArray();         
         //
         //caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] groupsTomerge =  new caBIG.cql.x1.govNihNciCagridCQLQuery.Group[foreignAssociationArray.length];

         
         for (int i=0;i<foreignAssociationArray.length;i++){
             // need to attach the results as crieteria ... 
             caBIG.cql.x1.govNihNciCagridCQLQuery.Group resultedGroup = processForeignAssociation((ForeignAssociation)foreignAssociationArray[i]);
             //groupsTomerge[i] = resultedGroup; 
             
             // DONT DELETE
            cqlGroup.addNewGroup();
            cqlGroup.setGroupArray(i,resultedGroup); 
             //
         }
              /*
               caBIG.cql.x1.govNihNciCagridCQLQuery.Group aggregatedGroup = ResultAggregator.aggregateGroups(groupsTomerge);
               cqlGroup.addNewGroup();
               cqlGroup.setGroupArray(0,aggregatedGroup);  
              */
        return cqlGroup;
    }
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Association processDCQLAssociation (gov.nih.nci.cagrid.dcql.Association  dcqlAssociation) {
        //convert basic group information and attach group to CQL object
        caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociation = caBIG.cql.x1.govNihNciCagridCQLQuery.Association.Factory.newInstance();
        cqlAssociation.setRoleName(dcqlAssociation.getRoleName());
        
        
        gov.nih.nci.cagrid.dcql.Object associatedObject = dcqlAssociation.getObject();
        cqlAssociation.setName(associatedObject.getName());
        
        //check for nested associations 
         if (associatedObject.isSetAssociation()) {
             gov.nih.nci.cagrid.dcql.Association nestedAssociation = associatedObject.getAssociation();
             cqlAssociation.addNewAssociation();             
             //call this method recursively...
             cqlAssociation.setAssociation(processDCQLAssociation(nestedAssociation));
         } 
         //check for attributes 
         if (associatedObject.isSetAttribute()) cqlAssociation.setAttribute(associatedObject.getAttribute());
         //check for foreign associations         
         if (associatedObject.isSetForeignAssociation()) processForeignAssociation(associatedObject.getForeignAssociation());
         //check for groups          
         if (associatedObject.isSetGroup()) cqlAssociation.setGroup(processDCQLGroup(associatedObject.getGroup()));
         
         return cqlAssociation;
    }    

    private caBIG.cql.x1.govNihNciCagridCQLQuery.Group  processForeignAssociation (ForeignAssociation  foreignAssociation) {
        //get Foreign Object 
         gov.nih.nci.cagrid.dcql.Object dcqlObject = foreignAssociation.getForeignObject();
         caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processDCQLObject(dcqlObject);
         
         CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
         CQLQueryDocument.CQLQuery cqlQry = cqlQueryDoc.addNewCQLQuery();
        
         cqlQry.setTarget(cqlObject);
         
         //Execute Foreign Query  ..... 
         FederatedQueryExecutor fqexe = new FederatedQueryExecutor();
         CQLQueryResults cqlResults = fqexe.executeQry(cqlQueryDoc,foreignAssociation.getServiceURL());
         
         ResultAggregator resultAggregator = new ResultAggregator(foreignAssociation.getJoinCondition());
         List cdeList = resultAggregator.processResults(cqlResults);
         
         caBIG.cql.x1.govNihNciCagridCQLQuery.Group criteriaGroup = resultAggregator.buildGroup(cdeList);
         
         return criteriaGroup;
    }

}