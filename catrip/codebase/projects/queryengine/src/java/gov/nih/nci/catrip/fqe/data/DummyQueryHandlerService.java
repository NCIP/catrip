package gov.nih.nci.catrip.fqe.data;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLObjectResult;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLQueryResults;
import gov.nih.nci.catrip.fqe.service.LocalQueryHandlerService;

import gov.nih.nci.catrip.fqe.utils.XmlUtil;

import java.io.File;


public class DummyQueryHandlerService implements LocalQueryHandlerService{

    
    public CQLQueryResults executeQuery(CQLQueryDocument Qry) {
        
        System.out.println(" -------- Query Recieved by Dummy Service ----------");
        XmlUtil.printCQLQueryObject(Qry);
        System.out.println(" --------- ---------------- -----------");
        
        CQLQueryResults results = CQLQueryResults.Factory.newInstance(); 
        try {
                
            CQLObjectResult obj1 = CQLObjectResult.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\schema1\\sampleParticipant.xml"));
            CQLObjectResult obj2 = CQLObjectResult.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\schema1\\sampleParticipant2.xml"));
            CQLObjectResult obj3 = CQLObjectResult.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\schema1\\sampleParticipant3.xml"));
            
            CQLObjectResult[] resultArray = new CQLObjectResult[3];
            resultArray[0] = obj1;
            resultArray[1] = obj2;
            resultArray[2] = obj3;
            results.setObjectResultArray(resultArray);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        //CQLObjectResult = CQLObjectResult.Factory.parse()
        return results;

    }
}
