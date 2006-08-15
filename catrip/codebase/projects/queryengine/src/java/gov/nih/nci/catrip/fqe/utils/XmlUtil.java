package gov.nih.nci.catrip.fqe.utils;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import java.io.StringWriter;
import java.io.Writer;

import javax.xml.namespace.QName;

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

