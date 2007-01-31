package gov.nih.nci.cagrid.fqp.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.ExternalObjects;
import gov.nih.nci.cagrid.cqlresultset.CQLObjectResult;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.dcql.DCQLQuery;
import gov.nih.nci.cagrid.dcql.ForeignAssociation;



import java.io.CharArrayReader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
  //  private List rows = new ArrayList();
    private Document document;
    private String targetObjectClassname;
    private String target = "";
    private String cdeClassName=null;
    private String cdeMemberName=null;
    private Map roleClassMap = new HashMap();
    private DCQLQuery dcql=null;
    private CQLQuery cqlQuery = null;
    
    public ResultsParser(DCQLQuery dcql) {
        javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery");
        document = buildDocument(dcql,q);
        this.target = "TargetObject";
        this.targetObjectClassname=dcql.getTargetObject().getName();
        checkFAInfo(dcql);
        loadRoleClassMap(dcql);
       this.dcql=dcql;
    }
    public ResultsParser(CQLQuery cqlQuery) {
        javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
        document = buildDocument(cqlQuery,q);
        this.target = "Target";
        this.targetObjectClassname=cqlQuery.getTarget().getName();
        this.cqlQuery = cqlQuery;
    }
    private void checkFAInfo(DCQLQuery dcql){
    
        gov.nih.nci.cagrid.dcql.Object targetObj = dcql.getTargetObject();
        
        if ( targetObj.getForeignAssociation() != null ) {
            cdeClassName = targetObj.getName();
            cdeMemberName = targetObj.getForeignAssociation().getJoinCondition().getLocalAttributeName();
            return ;
        }
        
        if (targetObj.getGroup() != null ) {
            if (lookupGroup(targetObj.getGroup(),targetObj.getName())) 
                return;
        }
         if (targetObj.getAssociation() != null ) {
            if (lookupAssociation(targetObj.getAssociation()))  //lookupAssoc;
                return;
         }  
    }
    
    private boolean lookupGroup(gov.nih.nci.cagrid.dcql.Group group,String assocName){
      boolean found = false;
      if ( group.getForeignAssociation() != null ) {
          cdeMemberName = group.getForeignAssociation(0).getJoinCondition().getLocalAttributeName();
          cdeClassName = assocName;
          return found;
      }     
      
      if (group.getGroup() != null) {
          gov.nih.nci.cagrid.dcql.Group[]  groupList = group.getGroup();
           for (int i=0;i<groupList.length;i++) {
               lookupGroup(groupList[i],assocName);
           }
      }    
    
      
      if (group.getAssociation() != null) {            
         gov.nih.nci.cagrid.dcql.Association[]  assocList = group.getAssociation();
          for (int i=0;i<assocList.length;i++) {
              lookupAssociation(assocList[i]);
          }
          
      }
      return found;  
    }
    
    private boolean lookupAssociation(gov.nih.nci.cagrid.dcql.Association  association) {         
        boolean found = false;
        if ( association.getForeignAssociation() != null ) {
            cdeClassName = association.getName();
            cdeMemberName = association.getForeignAssociation().getJoinCondition().getLocalAttributeName();
            return found;
        }
        
        if (association.getAssociation() != null ) {            
            lookupAssociation(association.getAssociation());
        }
        if (association.getGroup() != null) {
             lookupGroup(association.getGroup(),association.getName());
        } 
        return found;

    }
    
    private void loadRoleClassMap(DCQLQuery dcql){
        gov.nih.nci.cagrid.dcql.Object targetObj = dcql.getTargetObject();
        processTragetObject(targetObj);
       
    }
    private void processTragetObject(gov.nih.nci.cagrid.dcql.Object targetObj) {
        
        if ( targetObj.getForeignAssociation() != null ) {
            processForeignAssociation(targetObj.getForeignAssociation());
        }
        
        if (targetObj.getGroup() != null ) {
            processGroup(targetObj.getGroup());

        }
         if (targetObj.getAssociation() != null ) {
             processAssociation(targetObj.getAssociation());
         }         
    }
    private void processGroup(gov.nih.nci.cagrid.dcql.Group group){

      if ( group.getForeignAssociation() != null ) {
           gov.nih.nci.cagrid.dcql.ForeignAssociation[]  faList = group.getForeignAssociation();
            for (int i=0;i<faList.length;i++) {
                processForeignAssociation(faList[i]);
            }
       }     
      
      if (group.getGroup() != null) {
          gov.nih.nci.cagrid.dcql.Group[]  groupList = group.getGroup();
           for (int i=0;i<groupList.length;i++) {
               processGroup(groupList[i]);
           }
      }    
    
      
      if (group.getAssociation() != null) {            
         gov.nih.nci.cagrid.dcql.Association[]  assocList = group.getAssociation();
          for (int i=0;i<assocList.length;i++) {
              processAssociation(assocList[i]);
          }
          
      }
 
    }
    
    private void processAssociation(gov.nih.nci.cagrid.dcql.Association  association) {         
        roleClassMap.put(association.getRoleName(),association.getName());
        
        if ( association.getForeignAssociation() != null ) {
            processForeignAssociation(association.getForeignAssociation());
        }
        
        if (association.getAssociation() != null ) {            
            processAssociation(association.getAssociation());
        }
        if (association.getGroup() != null) {
             processGroup(association.getGroup());
        } 
    }
 
    private void processForeignAssociation(ForeignAssociation fa){ 
        
        gov.nih.nci.cagrid.dcql.Object fObject = fa.getForeignObject();
        
        processTragetObject(fObject);
    
        
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
           this.processME(msgsElement);
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

    public List getResultList (CQLQueryResults results) 
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
            //    System.out.println(msgsElement);
                processME(msgsElement);
            //    parseMessageElement(msgsElement);
        }

/*
             while (resultsItr.hasNext()) {
                 DataGroup dg = (DataGroup)resultsItr.next();
                 List list = dg.getDataRows();
                 Iterator listItr = list.iterator();
                 while (listItr.hasNext()) {
                     Map resultMap = (Map)listItr.next();
                     Iterator keys = resultMap.keySet().iterator();
                     while (keys.hasNext()) {
                         String key = keys.next().toString();
                         System.out.print( key + " " + resultMap.get(key).toString() + " ");
                     }
                 }
                 Syst
*/

        //add FA Attributes
        List finalList = new ArrayList();
        if (foreignObjectCollection != null) {
            Iterator resultListItr = resultList.iterator();
            while (resultListItr.hasNext()) {
                    DataGroup oDg = new DataGroup();
                    DataGroup dg = (DataGroup)resultListItr.next();
                    List list = dg.getDataRows();
                    Iterator listItr = list.iterator();
                    
                    while (listItr.hasNext()) {
                        Map map = (Map)listItr.next();
                        Object cedObj = map.get(cdeClassName+"-"+this.cdeMemberName);
                        String cdeValue = "";
                        if (cedObj != null) {
                            cdeValue = cedObj.toString();
                        } else {
                           //continue;
                            throw new Exception ("CDE Attribure : "+ this.cdeMemberName + " not fetched for object : " + cdeClassName);
                        }
                        
                        
                    //    System.out.println(cdeValue);
                        // get Array of Maps
                         Object[] objs = (Object[])foreignObjectCollection.get(cdeValue);
                         for (int i=0;i<objs.length;i++) {
                             Map ExternalAttributeMap = (Map)objs[i];
                             
                             map.putAll(ExternalAttributeMap);
                             Map newMap = new HashMap();
                             newMap.putAll(map);
                             oDg.addDataRow(newMap);                             
                         }
                    } 
                finalList.add(oDg);
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
    private void processME(MessageElement msgsElement){
          //   System.out.println(msgsElement);
             Document jdomDoc = null;
             
             try {
                 org.w3c.dom.Document doc = msgsElement.getAsDocument();
                 jdomDoc = convertDOMtoJDOM(doc);
             } catch (Exception e) {
                 e.printStackTrace();
             }
;
             Element currentRow = jdomDoc.getRootElement();
             Map dataMap = processRow(currentRow);   
             Map tempMap = new HashMap();
             List localList = new ArrayList();
             List children = currentRow.getChildren();
             if (children.size() == 0) {
                 localList.add(dataMap);
             }
             
             
             Iterator itr = children.iterator();
             while(itr.hasNext()){
                 Element ele = (Element)itr.next();
                 //System.out.println(ele.getName());
                 if (ele.getName().equals("patientIdentifier")) {
                     tempMap.putAll(processRow(ele));
                 }
                 Map eleMap = new HashMap();
                 eleMap.putAll(processRow(ele));
                 eleMap.putAll(dataMap);
                 if (ele.getChildren().size() == 0 ) {
                     
                     localList.add(eleMap);
                 } else {
                     
                     List children1 = ele.getChildren();
                     Iterator itr1 = children1.iterator();
                     while(itr1.hasNext()){ 
                         Element ele1 = (Element)itr1.next();
                         Map eleMap1 = new HashMap();
                         eleMap1.putAll(processRow(ele1));
                         eleMap1.putAll(dataMap);
                         eleMap1.putAll(eleMap);                   
                         if (ele1.getChildren().size() == 0 ) {
                             
                             localList.add(eleMap1);
                         } else {
                             List children2 =ele1.getChildren();
                             Iterator itr2 = children2.iterator();
                             while(itr2.hasNext()){ 
                                 Element ele2 = (Element)itr2.next();
                                 Map eleMap2 = new HashMap();
                                 eleMap2.putAll(processRow(ele2));
                                 eleMap2.putAll(dataMap);
                                 eleMap2.putAll(eleMap);  
                                 eleMap2.putAll(eleMap1);
                                 
                                 localList.add(eleMap2);
                             }
                             
                         }
                     }
                 }

             }
             //List row = new ArrayList();
              DataGroup dg = new DataGroup();
             for (int i=0;i<localList.size();i++) {
                 Map m = (Map)localList.get(i);
                 m.putAll(tempMap);
                 dg.addDataRow(m);
                 
             }
             resultList.add(dg);

         }
         private Map processRow(Element currentRow) {
                 Map dataMap = new HashMap();
                 List columns = currentRow.getAttributes();
  
                 for(int i=0;i<columns.size();i++) {
                     Attribute attr = (Attribute)columns.get(i);
                     if (attr.getName() != "id" ) {
                         if (currentRow.isRootElement()) {
                            String target = "";
                            if (dcql == null) {
                                target = cqlQuery.getTarget().getName();
                            } else {
                                target = dcql.getTargetObject().getName();
                            }
                            
                            dataMap.put(target+"-"+attr.getName(),attr.getValue());
                         } else {
                             if (dcql != null) {
                                dataMap.put(roleClassMap.get(currentRow.getName())+"-"+attr.getName(),attr.getValue());
                             } else {
                                 dataMap.put(getClassName(currentRow.getName())+"-"+attr.getName(),attr.getValue());
                             }
                         }
                     }
                 }// All columns captured
                 return dataMap;
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
        while (list.size() != 0 ) {
            for (int i=0;i<list.size();i++) {
                Element e = (Element)list.get(i);
                list = e.getChildren();
                processSubList(list,resultMap);
            }
        }
        
        /*
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
        */
        return lastGoodList;
    }

    
}
