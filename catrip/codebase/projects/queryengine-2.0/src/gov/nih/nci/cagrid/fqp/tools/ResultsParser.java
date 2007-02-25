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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.axis.message.MessageElement;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;

import org.xml.sax.InputSource;


public class ResultsParser {
    private List resultList = new ArrayList();
    //  private List rows = new ArrayList();
    private Document document;
    private String targetObjectClassname;
    private String target = "";
    private String cdeClassName=null;
    private String cdeMemberName=null;
    private String outboundRoleName=null;
    private Map roleClassMap = new HashMap();
    private DCQLQuery dcql=null;
    private CQLQuery cqlQuery = null;
    private static final Pattern pattern = Pattern.compile( "(xsi:type+=\"ns+[0-9]+:QueryExpressionDialect+\")" );
    
    public ResultsParser(DCQLQuery dcql) {
        javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://caGrid.caBIG/1.0/gov.nih.nci.cagrid.dcql","DCQLQuery");
        document = buildDocument(dcql,q);
        this.target = "TargetObject";
        this.targetObjectClassname=dcql.getTargetObject().getName();
        checkFAInfo(dcql);
        roleClassMap = DCQLParseHelper.loadRoleClassMap(dcql);
        this.dcql=dcql;
    }
    public ResultsParser(CQLQuery cqlQuery,DCQLQuery dcql) {
        javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
        document = buildDocument(cqlQuery,q);
        this.target = "Target";
        this.targetObjectClassname=cqlQuery.getTarget().getName();
        roleClassMap = DCQLParseHelper.loadRoleClassMap(dcql);
        this.cqlQuery = cqlQuery;
        this.dcql=dcql;
    }
    private void checkFAInfo(DCQLQuery dcql){
        
        gov.nih.nci.cagrid.dcql.Object targetObj = dcql.getTargetObject();
        
        if ( targetObj.getForeignAssociation() != null ) {
            cdeClassName = targetObj.getName();
            cdeMemberName = targetObj.getForeignAssociation().getJoinCondition().getLocalAttributeName();
            return ;
        }
        
        if (targetObj.getGroup() != null ) {
            if (lookupGroup(targetObj.getGroup(),targetObj.getName(),null))
                return;
        }
        if (targetObj.getAssociation() != null ) {
            if (lookupAssociation(targetObj.getAssociation()))  //lookupAssoc;
                return;
        }
    }
    
    private boolean lookupGroup(gov.nih.nci.cagrid.dcql.Group group,String assocName,String roleName){
        boolean found = false;
        if ( group.getForeignAssociation() != null ) {
            cdeMemberName = group.getForeignAssociation(0).getJoinCondition().getLocalAttributeName();
            cdeClassName = assocName;
            outboundRoleName = roleName;
            return found;
        }
        
        if (group.getGroup() != null) {
            gov.nih.nci.cagrid.dcql.Group[]  groupList = group.getGroup();
            for (int i=0;i<groupList.length;i++) {
                lookupGroup(groupList[i],assocName,roleName);
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
            outboundRoleName = association.getRoleName();
            cdeMemberName = association.getForeignAssociation().getJoinCondition().getLocalAttributeName();
            return found;
        }
        
        if (association.getAssociation() != null ) {
            lookupAssociation(association.getAssociation());
        }
        if (association.getGroup() != null) {
            lookupGroup(association.getGroup(),association.getName(),association.getRoleName());
        }
        return found;
        
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
    
    public List convertMessageElementToListOfMaps(MessageElement msgsElement) {
        //           System.out.println(msgsElement);
        this.processME(msgsElement);
        return resultList;
    }
    private String getEditedText(String aText){
        StringBuffer result = new StringBuffer();
        Matcher matcher = pattern.matcher(aText);
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
        
    }
    
    public List getResultList(CQLQueryResults results)
    throws Exception{
        
        CQLObjectResult[] objectResult = results.getObjectResult();
        int arraySize = objectResult.length;
        MessageElement forignAssociationResults = objectResult[arraySize-1].get_any()[0];
        
        Map foreignObjectCollection = null;
        if (forignAssociationResults.getElementName().getQualifiedName().endsWith("ExternalObjects")) {
            // PROCESS FOREIGN ATTRIBS
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
        List finalList = new ArrayList();
        
        if (foreignObjectCollection != null) {
            Iterator resultListItr = resultList.iterator();
            while (resultListItr.hasNext()) {
                DataGroup oDg = new DataGroup();
                DataGroup dg = (DataGroup)resultListItr.next();
                List dataGroupRows = dg.getDataRows();
                
                for (Object i:dataGroupRows) {
                    Map map = (Map)i;
                    Object cedObj = map.get(cdeClassName+"-"+this.cdeMemberName);
                    String cdeValue = "";
                    if (cedObj != null) {
                        cdeValue = cedObj.toString();
                    } else {
                        throw new Exception("CDE Attribure : "+ this.cdeMemberName + " not fetched for object : " + cdeClassName);
                    }
                    // get Array of Maps
                    Object[] objs = (Object[])foreignObjectCollection.get(cdeValue);
                    for (int j=0;j<objs.length;j++) {
                        Map ExternalAttributeMap = (Map)objs[j];                        
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
        
    }
    private List addEachChildToList(Element ele,List list,Map dataMap , List<Map> prevMaps){
        List children = ele.getChildren();
        for (Object i:children) {
            Element element = (Element)i;
            Map elementMap = new HashMap();
            elementMap.putAll(processRow(element));
            elementMap.putAll(dataMap);
            for (Map j : prevMaps) {
                Map map = j;                
                elementMap.putAll(map);
            }
            if (element.getChildren().size() == 0 ) {
                list.add(elementMap);
            } else {
                list = addEachChildToList(element,list,dataMap,prevMaps);
                prevMaps.add(elementMap);
            }
        }
        return list;
    }
    private void processME(MessageElement msgsElement){
        //System.out.println(msgsElement);
        Document jdomDoc = null;
        
        try {
            org.w3c.dom.Document doc = msgsElement.getAsDocument();
            jdomDoc = convertDOMtoJDOM(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Element currentRow = jdomDoc.getRootElement();
        Map dataMap = processRow(currentRow);
        Map tempMap = new HashMap();
        List<Map> localList = new ArrayList<Map>();
        List<Map> prevMaps = new ArrayList<Map>();
        List children = currentRow.getChildren();
        if (children.size() == 0) {
            localList.add(dataMap);
        }
        
        for (Object i:children) {
            Element element = (Element)i;
            //System.out.println(ele.getName());
            if (element.getName().equals(outboundRoleName)) {
                tempMap.putAll(processRow(element));
            }
            Map elementMap = new HashMap();
            //localList = addEachChildToList(ele,localList,dataMap,prevMaps);
            
            elementMap.putAll(processRow(element));
            elementMap.putAll(dataMap);
            prevMaps.add(elementMap);
            if (element.getChildren().size() == 0 ) {
                localList.add(elementMap);
            } else {
                localList = addEachChildToList(element,localList,dataMap,prevMaps);
            }
        }
        //List row = new ArrayList();
        DataGroup dataGroup = new DataGroup();
        for (Map i : localList) {
            Map map = i;
            map.putAll(tempMap);
            dataGroup.addDataRow(map);            
        }
        resultList.add(dataGroup);
        
    }
    private Map processRow(Element currentRow) {
        Map dataMap = new HashMap();
        List columns = currentRow.getAttributes();
        
       for (Object i : columns) {
            Attribute attr = (Attribute)i;
            if (attr.getName() != "id" ) {
                if (currentRow.isRootElement()) {
                    String target = "";
                    if (cqlQuery != null) {
                        target = cqlQuery.getTarget().getName();
                    } else {
                        target = dcql.getTargetObject().getName();
                    }
                    
                    dataMap.put(target+"-"+attr.getName(),attr.getValue());
                } else {
                    dataMap.put(roleClassMap.get(currentRow.getName())+"-"+attr.getName(),attr.getValue());
                }
            }
        }// All columns captured
        return dataMap;
    }
    
    private Map loadAssociatedObject(List list ,Map resultMap){
        Map newMap = new HashMap();
        for (Object i:list ) {
            Element element = (Element)i;
            String className = roleClassMap.get(element.getName()).toString();
            
            List attribs = element.getAttributes();
            for (Object j:attribs ) {
                Attribute attr = (Attribute)j;
                if (!attr.getName().equals("id") && !attr.getName().equals("type")) {
                    resultMap.put(className+"-"+attr.getName(),attr.getValue());
                }
                //System.out.print(className+"-"+a.getName() + " : " + a.getValue() + " ");
            }
            
            newMap.putAll(resultMap);
            resultList.add(newMap);
        }
        return newMap;
    }
    private void processSubList(List list,Map resultMap){
        for (Object i : list) {
            Element e = (Element)i;
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
            for (Object i : list) {
                Element e = (Element)i;
                list = e.getChildren();
                processSubList(list,resultMap);
            }
        }
        return lastGoodList;
    }
    
}    
                 /*
                 // TODO -- MAKE IT RECURSIVE LOOP ...
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
                                 if (ele2.getChildren().size() == 0 ) {
                                      localList.add(eleMap2);
                  
                                 } else {
                                     List children3 = ele2.getChildren();
                                     Iterator itr3 = children3.iterator();
                                     while (itr3.hasNext()) {
                                         Element ele3 = (Element)itr3.next();
                                         Map eleMap3 = new HashMap();
                                         eleMap3.putAll(processRow(ele3));
                                         eleMap3.putAll(dataMap);
                                         eleMap3.putAll(eleMap);
                                         eleMap3.putAll(eleMap1);
                                         eleMap3.putAll(eleMap2);
                                         localList.add(eleMap3);
                                     }
                                 }
                             }
                  
                         }
                     }
                 }
                  */

