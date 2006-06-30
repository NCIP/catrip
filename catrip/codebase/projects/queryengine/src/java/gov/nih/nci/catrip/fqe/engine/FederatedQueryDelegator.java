package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.catrip.fqe.data.ForeignQueryContext;

import java.util.Iterator;
import java.util.List;

public class FederatedQueryDelegator {
    public FederatedQueryDelegator() {
    }
    
    public void deligateToDataService(List<ForeignQueryContext> queryContextList) {
        Iterator qryContextItr = queryContextList.iterator();
        
        while (qryContextItr.hasNext()) {
            
        }
        
    }
}
