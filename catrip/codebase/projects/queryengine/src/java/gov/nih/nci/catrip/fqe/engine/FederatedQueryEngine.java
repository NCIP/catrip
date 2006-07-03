package gov.nih.nci.catrip.fqe.engine;


import gov.nih.nci.cagrid.dcql.FederatedQueryPlanDocument;

import gov.nih.nci.cagrid.dcql.ForeignAssociation;

import java.io.File;

import java.io.IOException;

import java.util.List;

import org.apache.xmlbeans.XmlException;

import org.w3c.dom.Document;

public class FederatedQueryEngine {
    public FederatedQueryEngine() {
    }
    
    public void process(FederatedQueryPlanDocument federatedQryPlan){

           /** Get the target object.
             */
             gov.nih.nci.cagrid.dcql.Object targetObject = federatedQryPlan.getFederatedQueryPlan().getTargetObject();
             
             // check for foriegn association 
            ForeignAssociation fa = targetObject.getForeignAssociation();

             
             /*
             // if NO foreign associations , convert to CQL 
             
             DcqlToCqlConverter converter = new DcqlToCqlConverter();
             caBIG.cql.x1.govNihNciCagridCQLQuery.Object cqlObject = converter.convert(targetObject);
             */
              //if foreign associations exists ..  Decompose queries . 
              FederatedQueryDecomposer decomposer = new FederatedQueryDecomposer();
              List queryContextList = decomposer.decompose(targetObject);
              
              
             
   }
    
    public static void main ( String[] args) {
        FederatedQueryEngine fqe = new FederatedQueryEngine();
        FederatedQueryPlanDocument fqDoc = null;

        try {
            fqDoc = FederatedQueryPlanDocument.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\SampleFederatedQry.xml"));
            fqe.process(fqDoc);
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
