package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.catrip.fqe.data.ForeignQueryContext;

import java.util.ArrayList;
import java.util.List;

public class FederatedQueryDecomposer {
    public FederatedQueryDecomposer() {
    }
    
    public List <ForeignQueryContext> decompose(gov.nih.nci.cagrid.dcql.Object dcqlObject) {
        
        // decompose foreign objects .. 
        // start with one foreign object for proto type ..
        
        List queryContextList = null;
        
        // decompose foreign objecs and convert into cql objects 
         
         // for each foreign object
            gov.nih.nci.cagrid.dcql.Object foriegnObject = dcqlObject.getForeignAssociation().getForeignObject();
            DcqlToCqlConverter converter = new DcqlToCqlConverter();
            caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = converter.convert(foriegnObject);
            // build foreign Query Context 
            
             ForeignQueryContext qryContext = new ForeignQueryContext();
             qryContext.setCqlObject(cqlObject);
             qryContext.setSequence(0);
             
             queryContextList.add(qryContext);
            
        // end loop 
        
        
        return queryContextList;
        
    }
}
