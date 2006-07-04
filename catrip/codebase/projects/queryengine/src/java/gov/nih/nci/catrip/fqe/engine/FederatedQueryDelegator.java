package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;

import gov.nih.nci.catrip.fqe.data.QueryContext;

import java.util.Iterator;
import java.util.List;

import org.apache.xmlbeans.XmlOptions;

public class FederatedQueryDelegator {
    public FederatedQueryDelegator() {
    }
    
    public void process (List<QueryContext> queryContextList) {
        Iterator qryContextItr = queryContextList.iterator();
        
        while (qryContextItr.hasNext()) {
            QueryContext queryContext = (QueryContext)qryContextItr.next();
            executeQuery(queryContext.getCqlQryDoc());
            
            //execute  query ..               
        }
        
    }
    
     public void executeQuery(CQLQueryDocument cqlQueryDocument) {
         // call Data Service and execute Query 
          
          // Format XML 
          XmlOptions xmlOptions = new XmlOptions();
          // Requests use of whitespace for easier reading
          xmlOptions.setSavePrettyPrint();
    
          // Requests that nested levels of the xml
          // document to be indented by multiple of 4
          // whitespace characters
          xmlOptions.setSavePrettyPrintIndent(4);
          
          System.out.println(cqlQueryDocument.xmlText(xmlOptions));
          
         
     }
}
