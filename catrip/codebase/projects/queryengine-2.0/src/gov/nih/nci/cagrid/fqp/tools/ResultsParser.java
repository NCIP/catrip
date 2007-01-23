package gov.nih.nci.cagrid.fqp.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.ExternalObjects;
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.message.MessageElement;

import org.globus.wsrf.encoding.ObjectDeserializer;
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
    private String target = "";
    
    public ResultsParser(DCQLQuery dcql,String targetObjectClassname) {
        javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery");
        document = buildDocument(dcql,q);
        this.target = "TargetObject";
        this.targetObjectClassname=targetObjectClassname;
    }
    public ResultsParser(CQLQuery cql,String targetObjectClassname) {
        javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
        document = buildDocument(cql,q);
        this.target = "Target";
        this.targetObjectClassname=targetObjectClassname;
    }

    private Document buildDocument(Object query, javax.xml.namespace.QName q) {        
        Writer w = new StringWriter();        
        Document doc = null;
        try {
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
 //   public Map getResultMap (MessageElement msgsElement) {
//           System.out.println(msgsElement);
  //         return parseMessageElement(msgsElement);
  //  }
    public List convertMessageElementToListOfMaps (MessageElement msgsElement) {
    //           System.out.println(msgsElement);
           parseMessageElement(msgsElement);
            return resultList;
    }
    private static final Pattern fINITIAL_A = Pattern.compile( "(xsi:type+=\"ns+[0-9]+:QueryExpressionDialect+\")" );
    private String getEditedText(String aText){
        StringBuffer result = new StringBuffer();
        Matcher matcher = fINITIAL_A.matcher(aText);
        while ( matcher.find() ) {
          matcher.appendReplacement(result, "");//getReplacement(matcher));
        }
        matcher.appendTail(result);
        return result.toString();
      }

    
    public Map processForeignObjects(MessageElement messageElement){       
    
        StringBuffer buf = new StringBuffer(getEditedText(messageElement.toString()));
        char[] chars = new char[buf.length()];
        buf.getChars(0, chars.length, chars, 0);
        
        CharArrayReader car = new CharArrayReader(chars);
        InputSource source = new InputSource(car);
        java.lang.Object obj = null ;        
        
        try {
           obj = ObjectDeserializer.deserialize(source,Class.forName("gov.nih.nci.cagrid.cqlquery.ExternalObjects"));
        } catch (Exception e) {
           e.printStackTrace();
        }        
        ExternalObjects eos =  (ExternalObjects)obj;
        Object foreignObject = eos.getExternalObject();
        if (foreignObject != null) {
           Map resultMap = (Map)foreignObject;
           return resultMap;
        }
        return null;
        // Object[] o2 = (Object[])m1.get("T74718");
        //  Map m2 = (Map)o2[1];
        //  System.out.println(m2.get("edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade-totalScore"));
        
    }
     public List getResultList (CQLQueryResults results) throws Exception {
         return getResultList(results,null,null);
     }
    public List getResultList (CQLQueryResults results,String cde, String cdeClassName) 
                                        throws Exception{
        
        CQLObjectResult[] objectResult = results.getObjectResult();
        int arraySize = objectResult.length;
        MessageElement forignAssociationResults = objectResult[arraySize-1].get_any()[0];

        Map foreignObjectCollection = null;
        if (forignAssociationResults.getElementName().getQualifiedName().endsWith("ExternalObjects")) {
            // PROCESS FOREIGN ATTRIBS
           //  System.out.println(forignAssociationResults);
             foreignObjectCollection = processForeignObjects(forignAssociationResults);
             arraySize = objectResult.length-1;
         } else {
            arraySize = objectResult.length;
        }

        for (int i = 0; i < arraySize ; i++) {
                CQLObjectResult objResult = objectResult[i];
                MessageElement msgsElement = objResult.get_any()[0];
                //System.out.println(msgsElement);
                parseMessageElement(msgsElement);
        }
        //add FA Attributes
        List finalList = new ArrayList();
        if (foreignObjectCollection != null) {
            Iterator resultListItr = resultList.iterator();
            while (resultListItr.hasNext()) {
                Map map = (Map)resultListItr.next();
                Object cedObj = map.get(cdeClassName+"-"+cde);
                String cdeValue = "";
                if (cedObj != null) {
                    cdeValue = cedObj.toString();
                } else {
                    throw new Exception ("CDE Attribure : "+ cde + " not fetched for object : " + cdeClassName);
                }
                
                
            //    System.out.println(cdeValue);
                // get Array of Maps
                 Object[] objs = (Object[])foreignObjectCollection.get(cdeValue);
                 for (int i=0;i<objs.length;i++) {
                     Map ExternalAttributeMap = (Map)objs[i];
                     
                     map.putAll(ExternalAttributeMap);
                     Map newMap = new HashMap();
                     newMap.putAll(map);
                     finalList.add(newMap);
                 }
                 
                 
            }
            return finalList;
        }
        
        
        return resultList;
        
/*
 try {
      Mapping mapping = new Mapping();
    //  mapping.loadMapping("C:\\tmp\\castor\\cgems-xml-mapping.xml");

      // Create a Reader to the file to unmarshal from
      Reader reader = new FileReader("C:\\tmp\\castor\\test.xml");

      // Create a new Unmarshaller
      Unmarshaller unmarshaller = new Unmarshaller(java.util.HashMap.class);
     // unmarshaller.setMapping(mapping);
      // Unmarshal the person object
      java.util.Map fo = (java.util.Map)unmarshaller.unmarshal(reader);

      //Map m = fo.getObjectMap();
     // System.out.println(m.get("ZV3436").toString());
  } catch (Exception e ) {
      e.printStackTrace();
  }
 */
        
        /*//System.out.println(objectResult.length);
         CQLObjectResult objResult1 = objectResult[objectResult.length-1];
        MessageElement msgsElement1 = objResult1.get_any()[0];
        System.out.println(msgsElement1.toString());  
        
        StringBuffer buf = new StringBuffer(msgsElement1.toString());

        char[] chars = new char[buf.length()];
        buf.getChars(0, chars.length, chars, 0);

        CharArrayReader car = new CharArrayReader(chars);
        InputSource source = new InputSource(car);
        java.lang.Object obj = null ;
        gov.nih.nci.cagrid.data.cql.tools.ForeignObjects fo = new ForeignObjects();
        /*
        try {
            ObjectDeserializer.deserialize(source,Class.forName("gov.nih.nci.cagrid.data.cql.tools.ForeignObjects"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //

        QName qn = new QName("gme://caTissueCAE.caBIG/1.0/edu.pitt.cabig.cae.domain.general","ns4");
        try {
            org.apache.axis.encoding.Deserializer ds = fo.getDeserializer(msgsElement1.toString(),Class.forName("gov.nih.nci.cagrid.data.cql.tools.ForeignObjects"),qn);
            Object o = ds.getValue();
            fo = (ForeignObjects)o;
            System.out.println(fo.getObjectMap().size());
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */

    }
    private Map loadAssociatedObject(List list ,Map resultMap){
        Iterator itr1 = list.iterator();
        Map newMap = new HashMap();
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
               if (!a.getName().equals("id") && !a.getName().equals("type")) {
                resultMap.put(className+"-"+a.getName(),a.getValue());
               }
               //System.out.print(className+"-"+a.getName() + " : " + a.getValue() + " ");
           }
                
                newMap.putAll(resultMap);
                resultList.add(newMap);
        }
        return newMap;
    }
    private void processSubList(List list,Map resultMap){
        for (int i= 0;i<list.size();i++) {
            Element e = (Element)list.get(i);
            List outlist = processChildren(e.getChildren(),resultMap);
            if (outlist == null) {
                outlist = new ArrayList();
                outlist.add(e);
            }
            loadAssociatedObject(outlist,resultMap);
        }
    }
    private List processChildren(List list,Map resultMap){
        List lastGoodList = null;

        while (list.size() != 0) {
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
     //   System.out.println( s.toString());     
        Element r = document.getRootElement();
        Namespace nameSpace = r.getNamespace();
       // System.out.println(n.getURI());
      //  System.out.println(n.getPrefix());
        
     //   System.out.println(nameSpace.getURI());
     //   System.out.println(nameSpace.getPrefix());      
        
        Element te = r.getChild(target,nameSpace);
        Element assoc = te.getChild("Association",nameSpace);
      //  System.out.println(assoc.getAttributeValue("roleName"));
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
                if (!a.getName().equals("id")) {
                    resultMap.put(targetObjectClassname+"-"+a.getName(),a.getValue());
                }
           //     System.out.print(rootEle.getName()+"-"+a.getName() + " : " + a.getValue() + " ");
            }
            
            // System.out.println(rootEle.getChildren().size());
             
             List children = rootEle.getChildren();

             //System.out.println(lastGoodElement.getName());
             List listToLoad = processChildren(children,resultMap);
             if (listToLoad == null ) {
                 resultList.add(resultMap);
             }
             if (listToLoad!=null && listToLoad.size() > 0) {
                 resultMap = loadAssociatedObject(listToLoad,resultMap);
             }
             
            
       
        } catch (Exception e) {
            e.printStackTrace();
        }
     //   return resultMap;
    }
    
}
