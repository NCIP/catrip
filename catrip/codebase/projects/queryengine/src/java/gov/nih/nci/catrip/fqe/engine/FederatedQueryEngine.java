package gov.nih.nci.catrip.fqe.engine;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;

import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;


public class FederatedQueryEngine {

    
    public FederatedQueryEngine() {
    }
    
    
    public CQLQueryDocument processDCQLQuery(FederatedQueryPlanDocument federatedQryPlan){
       //craete instance of CQLQueryDocument
       CQLQueryDocument cqlQueryDoc = CQLQueryDocument.Factory.newInstance();
       CQLQueryDocument.CQLQuery cqlQry = cqlQueryDoc.addNewCQLQuery();
       
       // decompose and get resolved CQL Traget Object
       FederatedQueryProcessor processor = new FederatedQueryProcessor();
       caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = processor.process(federatedQryPlan);
       
       cqlQry.setTarget(cqlObject);
       
       return cqlQueryDoc;
       
       
        //check for local associations
        
         /*
        
        // check for foriegn association 
        ForeignAssociation foreignAssociation = targetObject.getForeignAssociation();   
        
        //check for foreign association in Group
        //Association[] associations =  targetObject.getGroup().getAssociationArray();
        
        
        
        
        /*
        // if NO foreign associations , convert to CQL 
        
        DcqlToCqlConverter converter = new DcqlToCqlConverter();
        caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = converter.convert(targetObject);
        */
        //if foreign associations exists ..  Decompose queries . 
        /*
        FederatedQueryDecomposer decomposer = new FederatedQueryDecomposer();
        //List queryContextList = decomposer.decomposeGroup(targetObject);
        
        List queryContextList = decomposer.decompose(foreignAssociation);
        
        FederatedQueryDelegator qryDeligator = new FederatedQueryDelegator();
        qryDeligator.process(queryContextList);
        
        DcqlToCqlConverter converter = new DcqlToCqlConverter();
        CQLQueryDocument cqlQry= converter.convertDCQLTragetObject(targetObject);
        qryDeligator.executeQuery(cqlQry);  
        */
   }


         
    public static void main ( String[] args) {
        FederatedQueryEngine fqe = new FederatedQueryEngine();
        FederatedQueryPlanDocument fqDoc = null;

        try {
            fqDoc = FederatedQueryPlanDocument.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\SampleFederatedQry1.xml"));
            CQLQueryDocument cqlQueryDocument = fqe.processDCQLQuery(fqDoc);
            testCQLQueryObject(cqlQueryDocument);
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    
    // test the CQL FORMATION
    private static void testCQLQueryObject(CQLQueryDocument cqlQueryDoc ){
    
     // Format XML 
     XmlOptions xmlOptions = new XmlOptions();
     // Requests use of whitespace for easier reading
     xmlOptions.setSavePrettyPrint();
     
     // Requests that nested levels of the xml
     // document to be indented by multiple of 4
     // whitespace characters
     xmlOptions.setSavePrettyPrintIndent(4);
     
     System.out.println(cqlQueryDoc.xmlText(xmlOptions));
    }
}
