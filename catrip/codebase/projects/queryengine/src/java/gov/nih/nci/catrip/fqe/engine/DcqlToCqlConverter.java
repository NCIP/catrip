package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute;
import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;

//~--- non-JDK imports --------------------------------------------------------


//~--- classes ----------------------------------------------------------------

public class DcqlToCqlConverter {
    private CQLQueryDocument.CQLQuery cqlQry;
    private CQLQueryDocument cqlQueryDoc;
    //private caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject;
    
    public DcqlToCqlConverter() {}

    //~--- methods ------------------------------------------------------------
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object processAssociation( 
                                caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociationIn,
                                gov.nih.nci.cagrid.dcql.Object dcqlObject,
                                caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject){
        // Create new Association.
        caBIG.cql.x1.govNihNciCagridCQLQuery.Association cqlAssociation = caBIG.cql.x1.govNihNciCagridCQLQuery.Association.Factory.newInstance();     
        gov.nih.nci.cagrid.dcql.Association dcqlAssociation = dcqlObject.getAssociation();
        
        //System.out.println(dcqlAssociation.getRoleName());
        // set association role name . 
        cqlAssociation.setRoleName(dcqlAssociation.getRoleName());
        
        gov.nih.nci.cagrid.dcql.Object childObj = dcqlAssociation.getObject();
        
        // check for any attributes 
        Attribute attr = childObj.getAttribute();
        if (attr != null ) {
            //System.out.println(attr.getName());
            cqlAssociation.setAttribute(attr);
        } 
               
        /**
         * check if association has any objects , In CQL obect is basically association name it self , 
         * so Get the Object of DCQL association and map the name to Association Name of CQL.
         * <Association name="edu.pitt.cabig.cae.domain.general.AnnotationEventParameters">
         */
        
        
        if (childObj != null) {
            cqlAssociation.setName(childObj.getName());
            if (cqlAssociationIn != null ) {                
                cqlAssociationIn.setAssociation(cqlAssociation);
                cqlAssociation = cqlAssociationIn;
                //System.out.println(cqlAssociation.getName());
            } 
            cqlObject.setAssociation(cqlAssociation);
            // check if Object has nested associations 
            gov.nih.nci.cagrid.dcql.Association nestedAssociation = childObj.getAssociation();            
            if (nestedAssociation != null) {                
                processAssociation (cqlAssociation, childObj,cqlObject);   
            } 
        }

        return cqlObject;
    }
    
    private void processAttribute(Attribute attribute){
        
    }
    private void processGroup(gov.nih.nci.cagrid.dcql.Group group){
        
    }
    private caBIG.cql.x1.govNihNciCagridCQLQuery.Object processTargetObject(gov.nih.nci.cagrid.dcql.Object dcqlObject) {
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject =  caBIG.cql.x1.govNihNciCagridCQLQuery.Object.Factory.newInstance();
        cqlObject.setName(dcqlObject.getName());
        
        if (dcqlObject.getAssociation() != null){
            cqlObject = processAssociation(null,dcqlObject,cqlObject);
        }
        
        return cqlObject;
        
    }

    public CQLQueryDocument convertForeignAssociation(ForeignAssociation foreignAssociation) {
        cqlQueryDoc = CQLQueryDocument.Factory.newInstance();

        gov.nih.nci.cagrid.dcql.Object dcqlObject = foreignAssociation.getForeignObject();
        
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processTargetObject(dcqlObject);
        

       //Create instance of CQLQueryDocument
        CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
        cqlQry = cqlQueryDoc.addNewCQLQuery();
        cqlQry.setTarget(cqlObject);

        //return CQLQueryDocument
        return cqlQueryDoc;
    }
    public CQLQueryDocument convertDCQLTragetObject(gov.nih.nci.cagrid.dcql.Object dcqlObject) {
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processTargetObject(dcqlObject);        

        //Create instance of CQLQueryDocument
        CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
        cqlQry = cqlQueryDoc.addNewCQLQuery();
        cqlQry.setTarget(cqlObject);

        //return CQLQueryDocument
        return cqlQueryDoc;
        
    }
    
}


//~ Formatted by Jindent --- http://www.jindent.com
