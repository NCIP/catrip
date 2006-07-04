package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;

import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.fqe.data.QueryContext;

import java.util.ArrayList;
import java.util.List;


public class FederatedQueryDecomposer {
    
    public FederatedQueryDecomposer() {
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
}
