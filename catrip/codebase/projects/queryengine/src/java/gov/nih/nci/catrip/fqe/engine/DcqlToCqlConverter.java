package gov.nih.nci.catrip.fqe.engine;

//~--- non-JDK imports --------------------------------------------------------


import caBIG.cql.x1.govNihNciCagridCQLQuery.Attribute;
import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLQuery.Object;

import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

//~--- classes ----------------------------------------------------------------

public class DcqlToCqlConverter {
    private CQLQueryDocument.CQLQuery cqlQry;
    
    public DcqlToCqlConverter() {}

    //~--- methods ------------------------------------------------------------

    public CQLQueryDocument convert(gov.nih.nci.cagrid.dcql.Object dcqlObject) {
        
        // Create instance of CQL target object.
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject =  caBIG.cql.x1.govNihNciCagridCQLQuery.Object.Factory.newInstance();
        cqlObject.setName(dcqlObject.getName());        
        cqlObject.setAttribute(dcqlObject.getAttribute());
        
        //Create instance of CQLQueryDocument
        CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
        cqlQry = cqlQueryDoc.addNewCQLQuery();
        cqlQry.setTarget(cqlObject);

        //return CQLQueryDocument
        return cqlQueryDoc;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
