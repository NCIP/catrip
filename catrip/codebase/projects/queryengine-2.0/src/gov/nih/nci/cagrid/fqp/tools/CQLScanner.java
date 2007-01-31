package gov.nih.nci.cagrid.fqp.tools;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;

public class CQLScanner {
    public CQLScanner() {
    }
    
    public static boolean isAttributesRequested(CQLQuery query){
        gov.nih.nci.cagrid.cqlquery.Object target = query.getTarget();

      //  if (target.getReturnAttributes() != null ) {
       //     return true;
        //}
        
        Association assoc = target.getAssociation();
        
        while (assoc != null ) {            
            if (assoc.getReturnAttributes() != null ) {
                return true;
            }
            assoc = assoc.getAssociation();
        }
        return false;
    }
}
