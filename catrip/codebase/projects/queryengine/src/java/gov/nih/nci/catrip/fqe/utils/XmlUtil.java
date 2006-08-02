package gov.nih.nci.catrip.fqe.utils;

import caBIG.cql.x1.govNihNciCagridCQLQuery.CQLQueryDocument;


import edu.duke.catrip.cae.domain.general.ParticipantMedicalIdentifier;

import gov.nih.nci.cadsr.domain.DataElement;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlOptions;

import org.globus.wsrf.encoding.ObjectSerializer;


public class XmlUtil {
    public XmlUtil() {
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

}

