package gov.nih.nci.catrip.fqe.service;

import javax.naming.InitialContext;

public class QueryHandlerServiceLocator {
    private static QueryHandlerServiceLocator srvcLocator;
    InitialContext context = null;
    
 
    
    // Returns the instance of QueryHandlerServiceLocator class
      public static QueryHandlerServiceLocator getInstance() {
        if (srvcLocator == null) {
          srvcLocator = new QueryHandlerServiceLocator();
        }
        return srvcLocator;
      }
      
      public LocalQueryHandlerService getQueryHandlerService(String serviceURL){
          
          QueryHandlerServiceFactory factory = new QueryHandlerServiceFactory();
          return factory.getQueryHandlerService(serviceURL);
          
      }
}
