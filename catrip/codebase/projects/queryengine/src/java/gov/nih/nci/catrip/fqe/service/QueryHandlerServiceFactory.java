package gov.nih.nci.catrip.fqe.service;

import gov.nih.nci.catrip.fqe.data.DummyQueryHandlerService;

public class QueryHandlerServiceFactory {
    public QueryHandlerServiceFactory() {
    }
    
    public LocalQueryHandlerService getQueryHandlerService(String param){
        
        // return dummy service ....
        LocalQueryHandlerService queryHandlerService = new DummyQueryHandlerService();
        return queryHandlerService;
    }
}
