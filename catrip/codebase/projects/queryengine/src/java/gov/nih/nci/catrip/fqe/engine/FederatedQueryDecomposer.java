package gov.nih.nci.catrip.fqe.engine;


import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;

import gov.nih.nci.cagrid.dcql.ForeignAssociation;
import gov.nih.nci.catrip.fqe.data.ForeignQueryContext;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.XmlOptions;

public class FederatedQueryDecomposer {
    private XmlOptions xmlOptions;
    
    public FederatedQueryDecomposer() {
    }
    
    public List <ForeignQueryContext> decompose(ForeignAssociation foreignAssociation) {
        
        // decompose foreign objects .. 
        // start with one foreign object for proto type ..
        
        List queryContextList = null;
        
        // decompose foreign objecs and convert into CQL Query 
         
         // for each foreign association
        
        DcqlToCqlConverter converter = new DcqlToCqlConverter();
        CQLQueryDocument cqlQueryDoc = converter.convert(foreignAssociation);
        
        // Format XML     
        xmlOptions = new XmlOptions();
        // Requests use of whitespace for easier reading
        xmlOptions.setSavePrettyPrint();

        // Requests that nested levels of the xml
        // document to be indented by multiple of 4
        // whitespace characters
        xmlOptions.setSavePrettyPrintIndent(4);
        
        System.out.println(cqlQueryDoc.xmlText(xmlOptions));
        
        
         
         ForeignQueryContext qryContext = new ForeignQueryContext();
         qryContext.setCqlQryDoc(cqlQueryDoc);
         qryContext.setSequence(0);
         
         //queryContextList.add(qryContext);
         
            
        // end loop 
        
        
        return queryContextList;
        
    }
}
