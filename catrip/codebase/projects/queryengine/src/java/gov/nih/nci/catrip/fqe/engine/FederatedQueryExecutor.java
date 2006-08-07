package gov.nih.nci.catrip.fqe.engine;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.catrip.fqe.exception.QueryExecutionException;
import gov.nih.nci.catrip.fqe.service.ServiceClientFactory;
import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


class FederatedQueryExecutor {

    private static Log log = LogFactory.getLog(FederatedQueryExecutor.class);

    public FederatedQueryExecutor() {
    }

    /**
     *
     * @param cqlQuery
     * @param serviceURL
     * @return
     * @throws QueryExecutionException
     */
    CQLQueryResults executeCQLQuery(CQLQuery cqlQuery,String serviceURL) throws QueryExecutionException {

         System.out.println(" Executing CQL Query on "+ serviceURL +"----------");
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
            log.fatal(e);
            throw new QueryExecutionException("Error in executiong CQL for service URL : " + serviceURL, e);
        }
        return results;
    }
}
