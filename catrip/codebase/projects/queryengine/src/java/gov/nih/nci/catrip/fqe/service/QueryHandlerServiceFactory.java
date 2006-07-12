package gov.nih.nci.catrip.fqe.service;

import gov.nih.nci.catrip.fqe.data.DummyQueryHandlerService;
import gov.nih.nci.catrip.fqe.data.DummyQueryHandlerService1;

public class QueryHandlerServiceFactory {
    public QueryHandlerServiceFactory() {
    }
    
    public LocalQueryHandlerService getQueryHandlerService(String serviceURL){
        
        // return dummy service ....
        LocalQueryHandlerService queryHandlerService = null; 
        if (serviceURL.equals("http://ctom")) {
            queryHandlerService = new DummyQueryHandlerService();
        } else if(serviceURL.equals("http://caTissue")) {
            queryHandlerService = new DummyQueryHandlerService1();
        }
        return queryHandlerService;
    }
}
