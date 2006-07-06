package gov.nih.nci.catrip.fqe.utils;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;

import org.apache.xmlbeans.XmlOptions;

public class XmlUtil {
    public XmlUtil() {
    }
    
    // test the CQL FORMATION
    public static void printCQLQueryObject(CQLQueryDocument cqlQueryDoc ){
    
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

