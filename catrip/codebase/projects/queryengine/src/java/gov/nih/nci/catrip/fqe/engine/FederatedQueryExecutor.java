package gov.nih.nci.catrip.fqe.engine;


import gov.nih.nci.cagrid.client.HelloWorldClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.service.LocalQueryHandlerService;
import gov.nih.nci.catrip.fqe.service.QueryHandlerServiceLocator;
import gov.nih.nci.catrip.fqe.service.ServiceClientFactory;

import org.xml.sax.InputSource;


public class FederatedQueryExecutor {
    public FederatedQueryExecutor() {
    }

    
    public CQLQueryResults executeQry(CQLQuery cqlQry,String serviceURL) {
        
        CQLQueryResults results = null;
        try{
                
            ServiceClientFactory clientFactory = new ServiceClientFactory();
            HelloWorldClient serviceClient = clientFactory.getSeviceClient(serviceURL);
            serviceClient.getServiceSecurityMetadata();
            results = serviceClient.query(cqlQry);            
        } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
        }
        return results;
        
        /*
        System.out.println(cqlQry.toString());
        gov.nih.nci.cagrid.cqlquery.Object obj = new gov.nih.nci.cagrid.cqlquery.Object();
        
        //InputSource queryInput = new InputSource(cqlQry.xmlText());
        //CQLQuery query;
        //queryInput.toString()
        //ObjectDeserializer.deserialize()
        
        
        // call dummy service to execute the Query .... 
        //try {
        //    query = (CQLQuery) ObjectDeserializer.deserialize(queryInput, CQLQuery.class);
          //  System.out.println(query.getTarget().getName());
            
        //} catch (DeserializationException e) {
          //  e.printStackTrace();
        //}
        QueryHandlerServiceLocator queryHandlerServiceLocator = QueryHandlerServiceLocator.getInstance();
        LocalQueryHandlerService queryHandlerService = queryHandlerServiceLocator.getQueryHandlerService(serviceURL);
        
        CQLQueryResults results = queryHandlerService.executeQuery(cqlQry,serviceURL);
        
        return results;
        */
    }

}
