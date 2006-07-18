package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.service.ServiceClientFactory;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.lang.reflect.Method;


public class FederatedQueryExecutor {
    public FederatedQueryExecutor() {
    }

    
    public CQLQueryResults executeCQLQuery(CQLQuery cqlQuery,String serviceURL) {
        
        System.out.println(" -------- CQL Query for "+ serviceURL +"----------");
         XmlUtil.serializeQry(cqlQuery);
        
        CQLQueryResults results = null;
        try{
            //get appropriate client class from factory    
            ServiceClientFactory clientFactory = new ServiceClientFactory();
            Object client = clientFactory.getSeviceClient(serviceURL);
            
            // parameter is only CQLQuery which is CQLQuery
            Class paramTypes[] = new Class[1];
            paramTypes[0] = CQLQuery.class;
            
            // need to invoke query method 
            Method queryMethod = client.getClass().getMethod("query",paramTypes);
            //System.out.println(queryMethod.getName());
            
            //pass CQLQuery
            Object[] methodArgs = new Object[1];
            methodArgs[0] = cqlQuery;
            results = (CQLQueryResults)queryMethod.invoke(client,methodArgs);
     
        } catch (Exception e) {
            e.printStackTrace();
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
