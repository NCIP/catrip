package gov.nih.nci.catrip.fqe.engine;



import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;

import gov.nih.nci.cagrid.dcql.ForeignAssociation;

import gov.nih.nci.catrip.fqe.exception.FederatedQueryException;

import java.util.List;


public class FederatedQueryProcessor {
    
    public FederatedQueryProcessor() {
    }
    
    /**
     * 
     * @param federatedQryPlan
     * @return
     */
    public CQLQuery processFederatedQueryPlan(FederatedQueryPlanDocument federatedQryPlan) throws FederatedQueryException {        
        CQLQuery cqlQuery = null;        
        try {
            /** Get the target object.
            */
            gov.nih.nci.cagrid.dcql.Object targetObject = federatedQryPlan.getFederatedQueryPlan().getTargetObject();        
            gov.nih.nci.cagrid.cqlquery.Object cqlObject = processDCQLObject(targetObject);             
            cqlQuery = new CQLQuery();
            cqlQuery.setTarget(cqlObject);
        } catch (Exception e) {
            throw new FederatedQueryException("Failed  to process DCQL Query",e);
        }
        return cqlQuery;
    }

    /**
     * 
     * @param dcqlObject
     * @return
     */
    private gov.nih.nci.cagrid.cqlquery.Object processDCQLObject (gov.nih.nci.cagrid.dcql.Object dcqlObject)  {
        
        // initialize CQLObject .all the nested Queries would get resolved and attached to this CQL object .
        gov.nih.nci.cagrid.cqlquery.Object cqlObject =  new gov.nih.nci.cagrid.cqlquery.Object();
        
        cqlObject.setName(dcqlObject.getName()); 
        //gov.nih.nci.cagrid.cqlquery.Object cqlObject = initializeCQLObject(dcqlObject.getName());
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
            gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = processAttribute(dcqlObject.getAttribute());
            cqlObject.setAttribute(cqlAttribute);
        }
        
        //check for group
        if (dcqlObject.isSetGroup()) {
            //convert basic group information and attach group to CQL object
             gov.nih.nci.cagrid.cqlquery.Group cqlGroup = processDCQLGroup(dcqlObject.getGroup());     
             cqlObject.setGroup(cqlGroup);              
        }
        
        //check for Association
        if (dcqlObject.isSetAssociation()) {
            //Association contains DCQL object
            //Convert into CQL Associoation and Process DCQL Object
             gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = processDCQLAssociation(dcqlObject.getAssociation());             
             cqlObject.setAssociation(cqlAssociation);
        }       
        
        //check for ForeignAssociation
        if (dcqlObject.isSetForeignAssociation()){
            //processForeignAssociation(dcqlObject.getForeignAssociation());
            gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation(dcqlObject.getForeignAssociation());
            cqlObject.setGroup(resultedGroup);
        }

        return cqlObject;
        
    }
    

    private gov.nih.nci.cagrid.cqlquery.Attribute processAttribute(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute dcqlAttribute) {
        gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = new gov.nih.nci.cagrid.cqlquery.Attribute(); 
        cqlAttribute.setName(dcqlAttribute.getName());
        cqlAttribute.setValue(dcqlAttribute.getValue());
        cqlAttribute.setPredicate(gov.nih.nci.cagrid.cqlquery.Predicate.fromValue(dcqlAttribute.getPredicate().toString()));

        
        return cqlAttribute;
        //gov.nih.nci.cagrid.cqlquery.Predicate cqlPredicate = gov.nih.nci.cagrid.cqlquery.Predicate.        
    }

    private gov.nih.nci.cagrid.cqlquery.Attribute[] getAttributeArray(caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute[] dcqlAttributeArray )  {
        
        gov.nih.nci.cagrid.cqlquery.Attribute[] cqlAttributeArray = new gov.nih.nci.cagrid.cqlquery.Attribute[dcqlAttributeArray.length];

        for (int i=0;i<dcqlAttributeArray.length;i++){
             gov.nih.nci.cagrid.cqlquery.Attribute cqlAttribute = processAttribute((caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute)dcqlAttributeArray[i]);
             cqlAttributeArray[i]=cqlAttribute;
        }

        return cqlAttributeArray;
        
    }
   
    private gov.nih.nci.cagrid.cqlquery.Group processDCQLGroup(gov.nih.nci.cagrid.dcql.Group  dcqlGroup)  {
        //convert basic group information and attach group to CQL object
         gov.nih.nci.cagrid.cqlquery.Group cqlGroup = new gov.nih.nci.cagrid.cqlquery.Group();
         //attach logical relationship and any aatributes .
         
             cqlGroup.setLogicRelation(gov.nih.nci.cagrid.cqlquery.LogicalOperator.fromValue(dcqlGroup.getLogicRelation().toString()));
             cqlGroup.setAttribute(getAttributeArray(dcqlGroup.getAttributeArray()));
             
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

        
    }
    
    private gov.nih.nci.cagrid.cqlquery.Group setGroupAssociationArray(gov.nih.nci.cagrid.dcql.Group dcqlGroup,
                                                                                gov.nih.nci.cagrid.cqlquery.Group cqlGroup) {
        //associations
        gov.nih.nci.cagrid.dcql.Association[] dcqlAssociationArray = dcqlGroup.getAssociationArray();
        //build cqlAssociationArray
        gov.nih.nci.cagrid.cqlquery.Association[] cqlAssociationArray = new gov.nih.nci.cagrid.cqlquery.Association[dcqlAssociationArray.length];

        for (int i=0;i<dcqlAssociationArray.length;i++){
            gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = processDCQLAssociation((gov.nih.nci.cagrid.dcql.Association)dcqlAssociationArray[i]);
            cqlAssociationArray[i]=cqlAssociation;
        }
        cqlGroup.setAssociation(cqlAssociationArray);    

        return cqlGroup;
    }
    private gov.nih.nci.cagrid.cqlquery.Group setNestedGroupArray(gov.nih.nci.cagrid.dcql.Group dcqlGroup,
                                                                                gov.nih.nci.cagrid.cqlquery.Group cqlGroup) {
        //groups
         gov.nih.nci.cagrid.dcql.Group[] dcqlGroupArray =  dcqlGroup.getGroupArray();
         gov.nih.nci.cagrid.cqlquery.Group[] cqlGroupArray = new gov.nih.nci.cagrid.cqlquery.Group[dcqlGroupArray.length];
         
         for (int i=0;i<dcqlGroupArray.length;i++){
            gov.nih.nci.cagrid.cqlquery.Group cqlNestedGroup = processDCQLGroup((gov.nih.nci.cagrid.dcql.Group)dcqlGroupArray[i]);
            cqlGroupArray[i]=cqlNestedGroup;           
         }
         cqlGroup.setGroup(cqlGroupArray);

        return cqlGroup;
    }
    private gov.nih.nci.cagrid.cqlquery.Group setGroupForeignAssociationArray(gov.nih.nci.cagrid.dcql.Group dcqlGroup,
                                                                                gov.nih.nci.cagrid.cqlquery.Group cqlGroup){
                                                                                 
         //foreign associations
         ForeignAssociation[] foreignAssociationArray = dcqlGroup.getForeignAssociationArray();         
         
         //gov.nih.nci.cagrid.cqlquery.Group[] groupsTomerge =  new gov.nih.nci.cagrid.cqlquery.Group[foreignAssociationArray.length];
             gov.nih.nci.cagrid.cqlquery.Group[] g = new gov.nih.nci.cagrid.cqlquery.Group[foreignAssociationArray.length];
             for (int i=0;i<foreignAssociationArray.length;i++){
                 // need to attach the results as crieteria ... 
                 gov.nih.nci.cagrid.cqlquery.Group resultedGroup = processForeignAssociation((ForeignAssociation)foreignAssociationArray[i]);
                 //groupsTomerge[i] = resultedGroup; 
                 
                 // DONT DELETE
                //----cqlGroup.addNewGroup();
               //System.out.println(cqlGroup.getLogicRelation().toString());
//               cqlGroup.setGroup(i,resultedGroup);
               g[i]=resultedGroup;          
               cqlGroup.setGroup(g); 
                 //
             }
              /*
               gov.nih.nci.cagrid.cqlquery.Group aggregatedGroup = ResultAggregator.aggregateGroups(groupsTomerge);
               cqlGroup.addNewGroup();
               cqlGroup.setGroupArray(0,aggregatedGroup);  
              */
        return cqlGroup;
    }
    private gov.nih.nci.cagrid.cqlquery.Association processDCQLAssociation (gov.nih.nci.cagrid.dcql.Association  dcqlAssociation) {
        //convert basic group information and attach group to CQL object
        gov.nih.nci.cagrid.cqlquery.Association cqlAssociation = new gov.nih.nci.cagrid.cqlquery.Association();
        cqlAssociation.setRoleName(dcqlAssociation.getRoleName());        
        
        gov.nih.nci.cagrid.dcql.Object associatedObject = dcqlAssociation.getObject();
        cqlAssociation.setName(associatedObject.getName());
        
        //check for nested associations 
         if (associatedObject.isSetAssociation()) {
             gov.nih.nci.cagrid.dcql.Association nestedAssociation = associatedObject.getAssociation();
             //---cqlAssociation.addNewAssociation();             
             //call this method recursively...
             cqlAssociation.setAssociation(processDCQLAssociation(nestedAssociation));
         } 
         //check for attributes 
         if (associatedObject.isSetAttribute()) cqlAssociation.setAttribute(processAttribute(associatedObject.getAttribute()));
         //check for foreign associations         
         if (associatedObject.isSetForeignAssociation()) processForeignAssociation(associatedObject.getForeignAssociation());
         //check for groups          
         if (associatedObject.isSetGroup()) cqlAssociation.setGroup(processDCQLGroup(associatedObject.getGroup()));
         
         return cqlAssociation;
    }    

    private gov.nih.nci.cagrid.cqlquery.Group  processForeignAssociation (ForeignAssociation  foreignAssociation)  {
        //get Foreign Object 
         gov.nih.nci.cagrid.dcql.Object dcqlObject = foreignAssociation.getForeignObject();
         gov.nih.nci.cagrid.cqlquery.Object cqlObject = processDCQLObject(dcqlObject);
         
         CQLQuery cqlQuery = new CQLQuery();
         //CQLQueryDocument.CQLQuery cqlQry = cqlQueryDoc.addNewCQLQuery();
        
         cqlQuery.setTarget(cqlObject);
         
         //Execute Foreign Query  ..... 
         FederatedQueryExecutor fqexe = new FederatedQueryExecutor();
         CQLQueryResults cqlResults = fqexe.executeQry(cqlQuery,foreignAssociation.getServiceURL());
         
         ResultAggregator resultAggregator = new ResultAggregator(foreignAssociation.getJoinCondition());
         List cdeList = resultAggregator.processResults(cqlResults);
         
         gov.nih.nci.cagrid.cqlquery.Group criteriaGroup = resultAggregator.buildGroup(cdeList);
          //gov.nih.nci.cagrid.cqlquery.Group criteriaGroup= new gov.nih.nci.cagrid.cqlquery.Group();
         return criteriaGroup;
    }

}