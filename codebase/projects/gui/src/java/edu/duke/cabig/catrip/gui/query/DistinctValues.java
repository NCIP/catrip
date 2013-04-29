/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.duke.cabig.catrip.gui.query;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.QueryModifier;
import gov.nih.nci.cagrid.cqlquery.ReturnAttributes;
import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.client.DataServiceClient;
import java.util.HashSet;
import java.util.Set;
import org.apache.axis.message.MessageElement;

/**
 *
 * @author srini
 */
public class DistinctValues {
    
    /** Creates a new instance of DistinctValues */
    public DistinctValues() {
    }
    
    public static Set getDistinctValues( String serviceURL, String fullyQualifiedClassName, String attributeName)
                                                                                        throws Exception {
        Set results = new HashSet();
        CQLQuery cqlQuery = new CQLQuery();
        gov.nih.nci.cagrid.cqlquery.Object targetObject = new gov.nih.nci.cagrid.cqlquery.Object();
        targetObject.setName(fullyQualifiedClassName);
        ReturnAttributes ra = new ReturnAttributes();
        String[] ras = new String[1];
        ras[0] = attributeName;
        
        ra.setReturnAttribute(ras);
        targetObject.setReturnAttributes(ra);
        
        cqlQuery.setTarget(targetObject);
        
        //QueryModifier qm = new QueryModifier();
        //qm.setDistinctAttribute(attributeName);
        //cqlQuery.setQueryModifier(qm);
        
        
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
                           // System.out.println(msgsElement);
                    String value = msgsElement.getAttributeValue(attributeName);
                //MessageElement msgsElement = objResult.get_any()[0];  
                    if (value != null) {
                     results.add(value.trim());
                    }

            }
        }        
        return results;
    }
    
    public static void main(String[] args) {
        try {
        Set results = DistinctValues.getDistinctValues("https://152.16.96.114:443/wsrf/services/cagrid/CAE",
                    "edu.pitt.cabig.cae.domain.breast.BreastCancerBiomarkers",
                    "estrogenReceptor");
        for (Object i:results) {
            System.out.println(i);
        }
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
