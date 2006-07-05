package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;

import caBIG.cql.x1.govNihNciCagridCQLQuery.Group;

import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;

import org.apache.xmlbeans.XmlOptions;


public class FederatedQueryProcessor {
    
    public FederatedQueryProcessor() {
    }
    
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object  initializeCQLObject(String targetName){
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject =  caBIG.cql.x1.govNihNciCagridCQLQuery.Object.Factory.newInstance();
        cqlObject.setName(targetName);          
        return cqlObject;
    }

    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object processDCQLObject (gov.nih.nci.cagrid.dcql.Object dcqlObject) {
        
        
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = initializeCQLObject(dcqlObject.getName());
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
            processForeignAssociation(dcqlObject.getForeignAssociation());
        }
        
        return cqlObject;
        
    }
    
    // test the CQL FORMATION
    private static void testCQLQueryObject(CQLQueryDocument cqlQueryDoc ){
    
     // Format XML 
     XmlOptions xmlOptions = new XmlOptions();
     // Requests use of whitespace for easier reading
     xmlOptions.setSavePrettyPrint();
     
     // Requests that nested levels of the xml
     // document to be indented by multiple of 4
     // whitespace characters
     xmlOptions.setSavePrettyPrintIndent(4);
     
     System.out.println(cqlQueryDoc.xmlText(xmlOptions));
    }
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Group processForeignAssociation (ForeignAssociation  foreignAssociation) {
        //get Join Condition 
        // CODE HERE 
        
        //get Foreign Object 
         gov.nih.nci.cagrid.dcql.Object dcqlObject = foreignAssociation.getForeignObject();
         caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processDCQLObject(dcqlObject);
         CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
         CQLQueryDocument.CQLQuery cqlQry = cqlQueryDoc.addNewCQLQuery();
        
         cqlQry.setTarget(cqlObject);
         System.out.println(" -------- Resolved Foreign Object BEGIN ----------");
          testCQLQueryObject(cqlQueryDoc);
         System.out.println(" --------- Resolved Foreign Object END -----------");
         
         FederatedQueryExecutor fqexe = new FederatedQueryExecutor();
         return fqexe.executeQry(cqlQueryDoc);
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
         gov.nih.nci.cagrid.dcql.Association[] dcqlAssociationArray = dcqlGroup.getAssociationArray();
         
         //build cqlAssociationArray
         caBIG.cql.x1.govNihNciCagridCQLQuery.Association[] cqlAssociationArray = new caBIG.cql.x1.govNihNciCagridCQLQuery.Association[dcqlAssociationArray.length];
         
         for (int i=0;i<dcqlAssociationArray.length;i++){
             caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociation = processDCQLAssociation((gov.nih.nci.cagrid.dcql.Association)dcqlAssociationArray[i]);
             cqlAssociationArray[i]=cqlAssociation;
         }
         cqlGroup.setAssociationArray(cqlAssociationArray);

        //groups
         gov.nih.nci.cagrid.dcql.Group[] dcqlGroupArray =  dcqlGroup.getGroupArray();
         
         caBIG.cql.x1.govNihNciCagridCQLQuery.Group[] cqlGroupArray = new caBIG.cql.x1.govNihNciCagridCQLQuery.Group[dcqlGroupArray.length];
        
        for (int i=0;i<dcqlGroupArray.length;i++){
            caBIG.cql.x1.govNihNciCagridCQLQuery.Group cqlGroup1 = this.processDCQLGroup((gov.nih.nci.cagrid.dcql.Group)dcqlGroupArray[i]);
            cqlGroupArray[i]=cqlGroup1;
           
        }
        cqlGroup.setGroupArray(cqlGroupArray);
        
         //foreign associations
         ForeignAssociation[] foreignAssociationArray = dcqlGroup.getForeignAssociationArray();
         for (int i=0;i<foreignAssociationArray.length;i++){
             caBIG.cql.x1.govNihNciCagridCQLQuery.Group resultedGroup = processForeignAssociation((ForeignAssociation)foreignAssociationArray[i]);
             cqlGroup.addNewGroup();
             cqlGroup.setGroupArray(i+1,resultedGroup);
             //cqlGroup.setGroupArray(0,resultedGroup);
         }

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
         if (associatedObject.isSetAttribute()) {
             cqlAssociation.setAttribute(associatedObject.getAttribute());
         }
         
         if (associatedObject.isSetForeignAssociation()) {
             processForeignAssociation(associatedObject.getForeignAssociation());
         }
         
         if (associatedObject.isSetGroup()) {
             cqlAssociation.setGroup(processDCQLGroup(associatedObject.getGroup()));
         }
         return cqlAssociation;
    }    
    
    public caBIG.cql.x1.govNihNciCagridCQLQuery.Object process(FederatedQueryPlanDocument federatedQryPlan) {
        /** Get the target object.
        */
        gov.nih.nci.cagrid.dcql.Object targetObject = federatedQryPlan.getFederatedQueryPlan().getTargetObject();        
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processDCQLObject(targetObject);         
        return cqlObject;
    }

    
  
    /*
    public List <QueryContext> decomposeGroup(gov.nih.nci.cagrid.dcql.Object targetObject) {
        List queryContextList =  decompose(targetObject.getGroup().getAssociationArray()[0].getObject().getForeignAssociation());
        return queryContextList;
    }
    
    
    public List <QueryContext> decompose(ForeignAssociation foreignAssociation) {        
         // decompose foreign objects .. 
         // start with one foreign object for proto type ..
        
         List queryContextList = new ArrayList();
        
         // decompose foreign objecs and convert into CQL Query 
         
         // for each foreign association        
         DcqlToCqlConverter converter = new DcqlToCqlConverter();
         CQLQueryDocument cqlQueryDoc = converter.convertForeignAssociation(foreignAssociation);

        
         // build query context
         QueryContext qryContext = new QueryContext();
         qryContext.setCqlQryDoc(cqlQueryDoc);
         qryContext.setSequence(0);
         
         queryContextList.add(qryContext);
         
            
         // end loop         
         return queryContextList;        
    }
    */
}
