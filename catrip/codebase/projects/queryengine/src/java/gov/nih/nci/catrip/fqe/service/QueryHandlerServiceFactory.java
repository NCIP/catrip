package gov.nih.nci.catrip.fqe.service;


public class QueryHandlerServiceFactory {
    public QueryHandlerServiceFactory() {
    }
    
    public LocalQueryHandlerService getQueryHandlerService(String serviceURL){
        
        // return dummy service ....
        LocalQueryHandlerService queryHandlerService = null; 
        if (serviceURL.equals("http://ctom")) {
            //queryHandlerService = new DummyQueryHandlerService();
        } else if(serviceURL.equals("http://localhost:8181/wsrf/services/cagrid/DataService2")) {
            //queryHandlerService = new DummyQueryHandlerService();
        }
        return queryHandlerService;
    }
}
