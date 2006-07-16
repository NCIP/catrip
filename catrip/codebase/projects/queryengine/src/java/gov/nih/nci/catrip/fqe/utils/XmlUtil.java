package gov.nih.nci.catrip.fqe.utils;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;
import caBIG.cql.x1.govNihNciCagridCQLResultSet.CQLObjectResult;

import gov.nih.nci.cadsr.domain.DataElement;
import gov.nih.nci.cadsr.domain.Protocol;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlOptions;

import org.globus.wsrf.encoding.ObjectSerializer;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import org.w3c.dom.Node;


public class XmlUtil {
    public XmlUtil() {
    }
    
    public static void paintTabularResults (CQLQueryResults results) {
        //List resultList = new ArrayList(); 
        
         try {
         //    XPath xpath = XPath.newInstance(xpathStr);
             
         CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("src/gov/nih/nci/cagrid/client/client-configms.wsdd")));
         
         while (iter.hasNext()) {
         
               Protocol de = (Protocol) iter.next();
               System.out.println(de.getLongName());
               //resultList.add(de.getLongName());// need to get this longname using right join and reflection
         } 
         } catch (Exception e ) {
             e.printStackTrace();
         }
        //return resultList;
    }
    public static void serializeQry(CQLQuery Qry){
        
        try {
            
            
            Writer w = new StringWriter();
            
            QName q= new QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
            
            ObjectSerializer.serialize(w,(Object)Qry,q);
            System.out.println(w);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
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
        
/*
       Map map = new HashMap();
       map.put("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","");
       xmlOptions.setLoadSubstituteNamespaces(map);
  */      
        
     System.out.println(cqlQueryDoc.xmlText(xmlOptions));
    }
    
    public static void main (String[] args) {
         
        try {
            CQLObjectResult obj = CQLObjectResult.Factory.parse(new File("C:\\Development\\FederatedQueryEngine\\schema1\\sampleParticipant.xml"));
            Node n = obj.getDomNode();
            
 
            SAXBuilder saxBuilder=new SAXBuilder("org.apache.xerces.parsers.SAXParser");
            Reader stringReader=new StringReader(obj.xmlText());
            org.jdom.Document jdomDocument;
           // Element e = (Element)n;
          
            jdomDocument = saxBuilder.build(stringReader);
            
            XPath xpath = XPath.newInstance("/Object[@name='edu.pitt.cabig.cae.domain.general.Participant']/Attribute[@name='uniquePatientIdentifier']");
            Element myEl = (Element) xpath.selectSingleNode(jdomDocument);
            System.out.println(myEl.getAttribute("value").getValue());
            

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}

