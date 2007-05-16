/*
 * OnDemandCQLExecutor.java
 *
 * Created on May 14, 2007, 2:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package edu.duke.cabig.catrip.gui.query;

import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlquery.ReturnAttributes;
import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.client.DataServiceClient;
import java.util.HashSet;
import java.util.Set;
import org.apache.axis.message.MessageElement;

/**
 *
 * @author sakkala
 */
public class OnDemandCQLExecutor {
    
    /** Creates a new instance of OnDemandCQLExecutor */
    public OnDemandCQLExecutor() {
    }
    public static CQLQuery getCaTIESCQL(String bigId) {

        CQLQuery cqlQuery = new CQLQuery();
        
        try {
            gov.nih.nci.cagrid.cqlquery.Object targetObject = new gov.nih.nci.cagrid.cqlquery.Object();
            targetObject.setName("edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReportDocument");
            ReturnAttributes ra = new ReturnAttributes();
            String[] ras = new String[1];
            ras[0] = "documentText";
        
             ra.setReturnAttribute(ras);
             targetObject.setReturnAttributes(ra);
        
             Attribute attr = new Attribute();
             attr.setName("documentId");
             attr.setPredicate(Predicate.EQUAL_TO);
             attr.setValue(bigId);
        
             targetObject.setAttribute(attr);
        
             cqlQuery.setTarget(targetObject);        
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return cqlQuery;
        
    }
    public static Set execute( String serviceURL, CQLQuery cqlQuery, String resultAttribute)throws Exception {
        Set results = new HashSet();
        
        
        CQLQueryResults cqlResults = null;
        try {
            DataServiceClient client = new DataServiceClient(serviceURL);
            cqlResults = client.query(cqlQuery);
        } catch (Exception e) {
            throw new Exception("Problem query data service at URL:" + serviceURL, e);
        }
        
        if (cqlResults != null ) {
            CQLObjectResult[] objectResult = cqlResults.getObjectResult();
            for (int i = 0; i < objectResult.length; i++) {
               CQLObjectResult objResult = objectResult[i];
                
               MessageElement msgsElement = objResult.get_any()[0];                            
               String value = msgsElement.getAttributeValue(resultAttribute);

               if (value != null) {
                  results.add(value.trim());
               }

            }
        }        
        return results;
    }   
    
    
    public static void main(String[] args) {
        try {
            
        CQLQuery cqlQuery = new CQLQuery();
        gov.nih.nci.cagrid.cqlquery.Object targetObject = new gov.nih.nci.cagrid.cqlquery.Object();
        targetObject.setName("edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReportDocument");
        ReturnAttributes ra = new ReturnAttributes();
        String[] ras = new String[1];
        ras[0] = "documentText";
        
        ra.setReturnAttribute(ras);
        targetObject.setReturnAttributes(ra);
        
        Attribute attr = new Attribute();
        attr.setName("documentId");
        attr.setPredicate(Predicate.EQUAL_TO);
        attr.setValue("bigId:1234");
        
        targetObject.setAttribute(attr);
        
        
        
        cqlQuery.setTarget(targetObject);
        
        
        OnDemandCQLExecutor e = new OnDemandCQLExecutor();
        Set results = e.execute("http://localhost:8181/wsrf/services/cagrid/CaTIES",cqlQuery,"documentText");
        
        
        for (Object i:results) {
            System.out.println(i);
        }
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
