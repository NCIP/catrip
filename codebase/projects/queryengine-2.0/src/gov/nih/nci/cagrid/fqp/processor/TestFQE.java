/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.fqp.processor;

import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.dcql.DCQLQuery;

import gov.nih.nci.cagrid.fqp.tools.DataGroup;
import gov.nih.nci.cagrid.fqp.tools.ResultsParser;

import java.io.FileInputStream;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.globus.wsrf.encoding.ObjectDeserializer;

import org.xml.sax.InputSource;


public class TestFQE  {
    
    public TestFQE() { }
    
    
    public static void main (String[] args )  throws Exception {        
        String queryDir = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\queryengine-2.0\\test\\resources\\";
       //String qryFile = "complexQuery1.xml";
    //    String qryFile = "SanjeevQry.xml";
    //     String qryFile = "cgemsQuery.xml";
        String qryFile = "myQry.xml";
     //   String qryFile = "DemoUseCase2-b.xml";

        try {
            DCQLQuery dcql = (DCQLQuery) ObjectDeserializer.deserialize(new InputSource(new FileInputStream(queryDir+qryFile)),DCQLQuery.class);        
            

            
            //System.out.println(returnAttribs);
            FederatedQueryEngine fqe = new FederatedQueryEngine();
           // Long t1 = System.currentTimeMillis();  
            CQLQueryResults results = fqe.executeAndAggregateResults(dcql);
          //  Long t2 = System.currentTimeMillis(); 
        //    System.out.println("Time Taken " + (t2-t1)/1000);
      //      Map testMap = fqe.getTestMap();
            ResultsParser parser = new ResultsParser(dcql);
            List resultList = parser.getResultList(results);
     
//  List resultList = parser.getResultList(results,"medicalRecordNumber","edu.duke.cabig.tumorregistry.domain.PatientIdentifier");
            Iterator resultsItr = resultList.iterator();
            while (resultsItr.hasNext()) {
                DataGroup dg = (DataGroup)resultsItr.next();
                List list = dg.getDataRows();
                Iterator listItr = list.iterator();
                while (listItr.hasNext()) {
                    Map resultMap = (Map)listItr.next();
                    Iterator keys = resultMap.keySet().iterator();
                    while (keys.hasNext()) {
                        String key = keys.next().toString();
                        System.out.print( key + " " + resultMap.get(key).toString() + " ");
                    }
                    System.out.println("    ");
                }
                System.out.println("    ");
                
            //    System.out.println("FOREIGN ATTRIBS");
            }
 
        } catch (Exception e) {
        e.printStackTrace();
        }

    }
}
