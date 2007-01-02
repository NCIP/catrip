package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import java.io.File;

import java.lang.reflect.Method;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class ResultObjectAssembler {
    public ResultObjectAssembler() {
    }
    
    private Document buildDocument(CQLQuery query) {
        /*
        Writer w = new StringWriter();

        Document doc = null;
        
        try {

            //
            javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
            
            ObjectSerializer.serialize(w,query,q);

            
            SAXBuilder builder = new SAXBuilder();
            StringBuffer buf = new StringBuffer(w.toString());

            char[] chars = new char[buf.length()];
            buf.getChars(0, chars.length, chars, 0);
            
            CharArrayReader car = new CharArrayReader(chars);
            InputSource source = new InputSource(car);
            
            doc = builder.build(source);
            //
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
         Document doc = null;
         try {
             SAXBuilder builder = new SAXBuilder();
             doc = builder.build(new File("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\test1.xml"));
         } catch (Exception e) {
             e.printStackTrace();
         }
        return doc;
    }
    public List buildResultObjects (List targetObjects,CQLQuery query) {     
        Document doc = buildDocument(query);
        
        CQLBuilder builder = new CQLBuilder();
        builder.setDocument(doc);
        
        Element targetObjectEle = doc.getRootElement().getChild("Target");
        String targetObjectClassName = targetObjectEle.getAttributeValue("name");
        Class targetObjectClass = null;


        try {
            targetObjectClass = Class.forName(targetObjectClassName);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i=0;i<=targetObjects.size();i++){
             Object obj = targetObjects.get(i);
             Class[] par=new Class[0];
             Method mthd;


            try {
                Object[] parms = new Object[0];
                mthd = targetObjectClass.getMethod("getId",par);
                Object outputObj= mthd.invoke(obj,parms);
                
                System.out.println(outputObj.toString());
                
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        /*
        int c = 1;

        Element childAssoc = ell.getChild("Association");
        while (childAssoc != null ) {
            childAssoc = childAssoc.getChild("Association");
            System.out.println(buildCQL(c));
            c++;
        }
        Long t2 = System.currentTimeMillis();
        System.out.println("Time Taken " + (t2-t1)/1000);

     */
     return null;   
    }
    
    public static void main (String[] args ) {
        String queryDir = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\";
        String qryFile = "demo-cae.xml";
        
        

    }
}
