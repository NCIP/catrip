package gov.nih.nci.cagrid.fqp.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;

import gov.nih.nci.cagrid.dcql.DCQLQuery;

import java.io.CharArrayReader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis.message.MessageElement;

import org.globus.wsrf.encoding.ObjectSerializer;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import org.xml.sax.InputSource;


public class ResultsParser {
    private List resultList = new ArrayList();
    private Document document;
    private String targetObjectClassname;
    
    public ResultsParser(DCQLQuery dcql,String targetObjectClassname) {
        document = buildDocument(dcql);
        this.targetObjectClassname=targetObjectClassname;
    }

    private Document buildDocument(DCQLQuery query) {        
        Writer w = new StringWriter();        
        Document doc = null;
        try {
            javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery");
            ObjectSerializer.serialize(w,query,q);
            SAXBuilder builder = new SAXBuilder();
            
            String qry = w.toString();
            StringBuffer buf = new StringBuffer(qry);

            char[] chars = new char[buf.length()];
            buf.getChars(0, chars.length, chars, 0);
            
            CharArrayReader car = new CharArrayReader(chars);
            InputSource source = new InputSource(car);            
            doc = builder.build(source);    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }
    
    private org.jdom.Document convertDOMtoJDOM(org.w3c.dom.Document domDoc) throws Exception {
         DOMBuilder builder = new DOMBuilder();
         org.jdom.Document jdomDoc = builder.build(domDoc);
         return jdomDoc;

    }
    public List getResultList (CQLQueryResults results) {
        CQLObjectResult[] objectResult = results.getObjectResult();
        System.out.println(objectResult.length);
        
        for (int i = 0; i < objectResult.length; i++) {
                CQLObjectResult objResult = objectResult[i];
                MessageElement msgsElement = objResult.get_any()[0];
            //   System.out.println(msgsElement);
                parseMessageElement(msgsElement);
        }
        return resultList;
    }
    private void loadAssociatedObject(List list ,Map resultMap){
        Iterator itr1 = list.iterator();
        while (itr1.hasNext()) {
           Element e = (Element)itr1.next();
           //System.out.print(e.getName());
          // Attribute classAttr = e.getAttribute("type",Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance"));
          // String className = classAttr.getValue().split(":")[1];
           String className = getClassName(e.getName());
           
           List l2 = e.getAttributes();
          // l2.remove(classAttr);
           Iterator itr2 = l2.iterator();
           while (itr2.hasNext()) {
               Attribute a = (Attribute)itr2.next();
               resultMap.put(className+"-"+a.getName(),a.getValue());
               //System.out.print(className+"-"+a.getName() + " : " + a.getValue() + " ");
           }
                Map newMap = new HashMap();
                newMap.putAll(resultMap);
                resultList.add(newMap);
        }
    }
    private void processSubList(List list,Map resultMap){
        for (int i= 0;i<list.size();i++) {
            Element e = (Element)list.get(i);
            List outlist = processChildren(e.getChildren(),resultMap);
            loadAssociatedObject(outlist,resultMap);
        }
    }
    private List processChildren(List list,Map resultMap){
        List lastGoodList = null;

        while (list.size() != 0 ) {
            lastGoodList = list;
            
            if (list.size() > 1 ) {
                processSubList(list,resultMap);
                list = new ArrayList();
                lastGoodList = list;
            } else {
                Element e = (Element)list.get(0);
                list = e.getChildren();
            }
            
        }
        
        return lastGoodList;
    }
    private String getClassName(String roleName) {
        Writer s = new  StringWriter();  
        
        try{
             XMLOutputter outputter =   new XMLOutputter(Format.getPrettyFormat());        
             outputter.output(document , s);            
        } catch (Exception ex){
             ex.printStackTrace();
        }         
        System.out.println( s.toString());     
        Element r = document.getRootElement();
        Namespace nameSpace = r.getNamespace();
       // System.out.println(n.getURI());
      //  System.out.println(n.getPrefix());
        
        System.out.println(nameSpace.getURI());
        System.out.println(nameSpace.getPrefix());      
        
        Element te = r.getChild("TargetObject",nameSpace);
        Element assoc = te.getChild("Association",nameSpace);
        System.out.println(assoc.getAttributeValue("roleName"));
        while (!assoc.getAttributeValue("roleName").endsWith(roleName)) {
            assoc = assoc.getChild("Association",nameSpace);
        }
       // return roleName;
        return assoc.getAttributeValue("name");
    }
    private void parseMessageElement(MessageElement msgsElement){

        Map resultMap = new HashMap();
        try {
            org.w3c.dom.Document doc = msgsElement.getAsDocument();
            org.jdom.Document jdomDoc = convertDOMtoJDOM(doc);
            Element rootEle = jdomDoc.getRootElement();
            
            List l = rootEle.getAttributes();
            // TARGET OBJECT ... 
            Iterator itr = l.iterator();
            while (itr.hasNext()) {
                Attribute a = (Attribute)itr.next();
                resultMap.put(targetObjectClassname+"-"+a.getName(),a.getValue());
                //System.out.print(rootEle.getName()+"-"+a.getName() + " : " + a.getValue() + " ");
            }
            
            // System.out.println(rootEle.getChildren().size());
             
             List children = rootEle.getChildren();

             //System.out.println(lastGoodElement.getName());
             List listToLoad = processChildren(children,resultMap);
             if (listToLoad == null ) {
                 resultList.add(resultMap);
             }
             if (listToLoad!=null && listToLoad.size() > 0) {
                 loadAssociatedObject(listToLoad,resultMap);
             }
             
            
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
