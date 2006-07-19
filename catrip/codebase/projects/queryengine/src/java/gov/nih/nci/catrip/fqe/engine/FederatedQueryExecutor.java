package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.service.ServiceClientFactory;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.lang.reflect.Method;


class FederatedQueryExecutor {
    public FederatedQueryExecutor() {
    }

    
    CQLQueryResults executeCQLQuery(CQLQuery cqlQuery,String serviceURL) {
        
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
    }

}
