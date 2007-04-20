package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import gov.nih.nci.cagrid.cqlquery.Group;

import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import org.xml.sax.InputSource;


public class CQLBuilder {
    CQLQuery cqlQuery = null;
    private Map subCQL= new HashMap();
    
    
    public CQLBuilder(CQLQuery cqlQuery) {
        this.cqlQuery = cqlQuery;
        
    }
    
    private static final Namespace nameSpace = Namespace.getNamespace("ns1","http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery");
    
    private Element buildAssociationAndAttachPKAttribute(Element parantElement,String roleName){
        List contentList = new ArrayList();
        
        Element assocEle = new Element("Association",nameSpace);
        assocEle.setAttribute("name",parantElement.getAttributeValue("name"));
        assocEle.setAttribute("roleName",roleName);
        
        Element attrEle = new Element("Attribute",nameSpace);
        attrEle.setAttribute("name","id");
        attrEle.setAttribute("value",parantElement.getAttributeValue("name")+"_ID");
        attrEle.setAttribute("predicate","EQUAL_TO");
        
        contentList.add(attrEle);
        
        
        //check if parent have any Attributes
        Element existingAttrEle =  parantElement.getChild("Attribute",nameSpace);
        
        if (existingAttrEle != null ) {
            
            contentList.add(existingAttrEle.detach());
        }
        
        // check for Group <mutiple attributes , just get attributes out of Group and attach
        Element group =  parantElement.getChild("Group",nameSpace);
        if (group != null ) {
            List existingAttrEleList = group.getChildren("Attribute",nameSpace);
            for (int i=0;i<existingAttrEleList.size();i++) {
                Element tmp = (Element)existingAttrEleList.get(i);
                contentList.add(tmp.detach());
            }
        }
        
        assocEle.setContent(contentList);
        return assocEle;
    }
    
    
    private Element buildQueryModifiers(Element retAttr) {
        // Build Query modifiers
        Element qm = new Element("QueryModifier",nameSpace);
        qm.setAttribute("countOnly","false");
        
        Element ra = new Element("AttributeNames",nameSpace);
        ra.setText("id");
        qm.addContent(ra);
        
        if (retAttr != null ) {
            List retAttrList = retAttr.getChildren("ReturnAttribute",nameSpace);
            
            for (int j=0; j < retAttrList.size();j++){
                Element e = (Element)retAttrList.get(j);
                ra = new Element("AttributeNames",nameSpace);
                ra.setText(e.getText());
                qm.addContent(ra);
            }
            
        }
        
        return qm;
    }
    public String buildCQL(int level) {
        Document doc = buildDocument(cqlQuery);
        
        Element te = doc.getRootElement().getChild("Target",nameSpace);
        //first Associtaion
        for (int i=1;i<=level;i++){
            Element groupEle = te.getChild("Group",nameSpace);
            List assocEleList = te.getChildren("Association",nameSpace);
            
            if (groupEle != null ) {
                //process Group
                assocEleList = groupEle.getChildren("Association",nameSpace);
            }
            
            for (int k=0;k<assocEleList.size();k++) {
                te = (Element)assocEleList.get(k);
            }
        }
        
        return buildNewTarget(te,"AND");
    }
    
    public Map getSubCQL() {
        Document doc = buildDocument(cqlQuery);
        Element target = doc.getRootElement().getChild("Target",nameSpace);
        
        // check for Groups
        if (target.getChild("Group",nameSpace) != null) {
            //process Group
            processGroup(target.getChild("Group",nameSpace));
        }
        //check for Assoc
        if (target.getChild("Association",nameSpace) != null) {
            //process Association
            processAssociation(target.getChild("Association",nameSpace),"AND");
        }
        
        return subCQL;
    }
    private List getParents(Element association){
        List parentList = new ArrayList();
        //System.out.println(association.getName() + "  " + association.getAttributeValue("name"));
        Element p = association.getParentElement();
        while ( !p.getName().equals("Target") ) {
            if (p.getName().equals("Association")) {
                Parent par = new Parent();
                par.setParentAssocationClassName(p.getAttributeValue("name"));
                par.setParentAssocationRoleName(p.getAttributeValue("roleName"));
                parentList.add(par);
                //System.out.println(p.getName()  + "  " + p.getAttributeValue("name"));
                p=p.getParentElement();
            } else {
                p = p.getParentElement();
            }
        }
        return parentList;
    }
    
    private void processAssociation(Element  assoctioanElement,String logicRelation) {
        //     System.out.println(assoctioanElement.getAttributeValue("name"));
        SubCQL scqlVo = new SubCQL();
        //if (parentAssoc != null) {
        List parentList = getParents(assoctioanElement);
        scqlVo.setParents(parentList);
        //}
        //   scqlVo.s
        scqlVo.setCQLString(buildNewTarget(assoctioanElement,logicRelation));
        
        subCQL.put(assoctioanElement.getAttributeValue("name")+"-"+assoctioanElement.getAttributeValue("roleName"),scqlVo);
        //subCQL.add(buildNewTarget(assoctioanElement));
        
        //  System.out.println(assoctioanElement.getChildren().size());
        
        if (assoctioanElement.getChild("Association",nameSpace) != null) {
            processAssociation(assoctioanElement.getChild("Association",nameSpace),logicRelation);
        }
        if (assoctioanElement.getChild("Group",nameSpace) != null) {
            processGroup(assoctioanElement.getChild("Group",nameSpace));
        }
    }
    
    private void processGroup(Element  groupElement) {
        
        String logicRelation = groupElement.getAttributeValue("logicRelation");
        
        if (groupElement.getChildren("Group",nameSpace) != null) {
            List groupList = groupElement.getChildren("Group",nameSpace);
            for (int i=0;i<groupList.size();i++) {
                Object tempObj = groupList.get(i);
                Element tempEle = (Element)tempObj;
                processGroup(tempEle);
            }
        }
        
        
        if (groupElement.getChildren("Association",nameSpace) != null) {
            List assocList = groupElement.getChildren("Association",nameSpace);
            //  Iterator assocItr = assocList.iterator();
            for (int i=0;i<assocList.size();i++) {
                Object tempObj = assocList.get(i);
                Element tempEle = (Element)tempObj;
                processAssociation(tempEle,logicRelation);
            }
            
        }
        
    }
    
    
    
    
    public String buildCQL(int level,int assocIndex) {
        Document doc = buildDocument(cqlQuery);
        
        Element te = doc.getRootElement().getChild("Target",nameSpace);
        //first Associtaion
        for (int i=1;i<=level;i++){
            Element groupEle = te.getChild("Group",nameSpace);
            List assocEleList = te.getChildren("Association",nameSpace);
            
            if (groupEle != null ) {
                //process Group
                assocEleList = groupEle.getChildren("Association",nameSpace);
            }
            
            for (int k=0;k<assocEleList.size();k++) {
                te = (Element)assocEleList.get(assocIndex);
            }
        }
        
        return buildNewTarget(te,"AND");
    }
    public String buildNewTarget(Element assocEle,String logicRelation) {
        String targetObjectName = assocEle.getAttributeValue("name");

        Element targetEle = new Element("Target",nameSpace);
        targetEle.setAttribute("name",targetObjectName);
        
        //check for return Attributes ..
        Element retAttr = assocEle.getChild("ReturnAttributes",nameSpace);
        // Build Query modifiers
        Element QryModifiers = buildQueryModifiers(retAttr);
        
        
        //get attribute
        Element attrEle = assocEle.getChild("Attribute",nameSpace);
        if (attrEle != null) {
            targetEle.addContent(attrEle.detach());
        }
        //what if multiple attributes , which means group
        Element grpEle = assocEle.getChild("Group",nameSpace);
        if (grpEle != null) {
            Element tempGrp = (Element)grpEle.clone();
            targetEle.addContent(tempGrp.detach());
        }
        
        //Now parents become Child. Get Parents
        Element parEle = assocEle.getParentElement();
        //Parent can be Group or Association .
        //If Group get the higher Association.
        if (parEle.getName().endsWith("Group")) {
            parEle = parEle.getParentElement();
        }
        String parentClassName = parEle.getAttributeValue("name");
        String roleName = ToolUtil.getRoleName(targetObjectName,parentClassName);
        
        Element newAssocEle = buildAssociationAndAttachPKAttribute(parEle,roleName);
        // make all the parents as childs
        // this works only in bi directional scenario
        Element ele = null;
        int c=1;
        while (parEle.getParentElement() != null) {
            //    System.out.println(parEle.getAttributeValue("name"));
            Element grandParentEle = parEle.getParentElement();
            //    System.out.println(grandParentEle.getAttributeValue("name"));
            //if Parent is Group get higher Associaton
            if (grandParentEle.getName().endsWith("Group")) {
                grandParentEle = grandParentEle.getParentElement();
            }
            
            if (!grandParentEle.getName().equals("CQLQuery")) {
                Element newAssocEle1 = buildAssociationAndAttachPKAttribute(grandParentEle,ToolUtil.getRoleName(parEle.getAttributeValue("name"),grandParentEle.getAttributeValue("name")));
                // keep adding to higherlevels
                if (c == 1 ) {
                    ele=newAssocEle1;
                } else {
                    Element ex = ele;
                    int cl = 0;
                    while (ex.getChild("Association",nameSpace) != null) {
                        ex = ex.getChild("Association",nameSpace);
                        //        System.out.println(ex.getAttributeValue("name"));
                        cl++;
                    }
                    if (cl == 0 ) {
                        ele = ele.addContent(newAssocEle1);
                    } else {
                        ex.addContent(newAssocEle1);
                    }
                }
            }
            c++;
            parEle=grandParentEle;
            
        }
        if (ele != null) newAssocEle.addContent(ele);
        
        //check for any childs
        Element childAssocEle = assocEle.getChild("Association",nameSpace);
        
        if (newAssocEle != null) {
            Element tempAssoc = (Element)newAssocEle.clone();
            targetEle.addContent(tempAssoc.detach());
        }
        
        if (childAssocEle != null) {
            Element tempAssoc = (Element)childAssocEle.clone();
            targetEle.addContent(tempAssoc.detach());
        }
        Element root = new Element("CQLQuery",nameSpace);
        targetEle=renderGroups(targetEle,logicRelation);
        return buildDocString(root,targetEle,QryModifiers);
    }
    private Element renderGroups(Element targetEle,String logicRelation){
        //   System.out.println(targetEle.getChildren().size());
        List children = targetEle.getChildren();
        if (children.size() > 1 ) {
            Element groupEle = buildGroup(children,logicRelation);
            targetEle.removeContent();
            targetEle.addContent(groupEle);
        }
        Element assoc = targetEle.getChild("Association",nameSpace);
        
        if (assoc == null) {
            Element group = targetEle.getChild("Group",nameSpace);
            if (group !=null ) {
                assoc = group.getChild("Association",nameSpace);
            }
        }
        
        while (assoc != null) {
            assoc =   renderAssocGroups(assoc);
        }
        return targetEle;
    }
    
    private Element renderAssocGroups(Element assoc) {
        List assocChildren = assoc.getChildren();
        if (assocChildren.size() > 1 ) {
            Element groupEle = buildGroup(assocChildren,"AND");
            assoc.removeContent();
            assoc.addContent(groupEle);
        }
        if (assoc.getChild("Group",nameSpace) != null ) {
            Element group = assoc.getChild("Group",nameSpace);
            List assocList = group.getChildren("Association",nameSpace);
            for (int i=0;i<assocList.size();i++) {
                assoc = (Element)assocList.get(i);
                assoc = renderAssocGroups(assoc);
            }
            //assoc = assoc.getChild("Group",nameSpace).getChild("Association",nameSpace);
        } else {
            assoc = null;
        }
        return assoc;
    }
    
    private String buildDocString(Element root,Element targetEle,Element queryModifier) {
        root.addContent(targetEle);
        root.addContent(queryModifier);
        Document docOut = new Document(root);
        Writer s = new  StringWriter();
        
        try{
            XMLOutputter outputter =   new XMLOutputter(Format.getPrettyFormat());
            outputter.output(docOut , s);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return s.toString();
    }
    
    
    
    private Element buildGroup(List elements,String logicRelation) {
        Element groupEle = new Element("Group",nameSpace);
        groupEle.setAttribute("logicRelation",logicRelation);
        
        List localList = new ArrayList(elements);
        
        List newContent = new ArrayList();
        for (int i=0; i < localList.size();i++){
            Element newEle = (Element)localList.get(i);
            newContent.add(newEle.detach());
        }
        
        groupEle.setContent(newContent);
        
        return groupEle;
        
    }
    
    
    private Document buildDocument(CQLQuery query) {
        Writer w = new StringWriter();
        Document doc = null;
        try {
            javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");
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
    private void execute(Association childAssoc) {
        int c = 1;
        while (childAssoc != null ) {
            // System.out.println(buildCQL(c));
            
            Group g = childAssoc.getGroup();
            if (g != null ) {
                childAssoc = g.getAssociation(0);
            } else {
                childAssoc = childAssoc.getAssociation();
            }
            
            
            c++;
        }
    }
    public static void main(String[] args) {
        String queryDir = "/Users/sakkala/temp/cql/";
        String qryFile = "tr.xml";
        java.lang.Object obj = null;
        
        try {
            obj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream(queryDir+qryFile)),CQLQuery.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CQLBuilder cb = new CQLBuilder((CQLQuery)obj);
        //  System.out.println("Executing ....");
        Long t1 = System.currentTimeMillis();
        
        Map cqlMap = cb.getSubCQL();
        Set assocKeys = cqlMap.keySet();
        Iterator keyItr = assocKeys.iterator();
        
        while (keyItr.hasNext()){
            String key = keyItr.next().toString();
            Object obj1 = cqlMap.get(key);
            
            SubCQL sc = (SubCQL)obj1;       
            
            System.out.println(key);
            List parents = sc.getParents();
            System.out.println("PARENT : " );
            for (int i=0;i<parents.size();i++){
                Parent p = (Parent)parents.get(i);
                System.out.println(p.getParentAssocationClassName() + "-" + p.getParentAssocationRoleName());
            }
            System.out.println(" - - - - " );
            //System.out.println("PARENT : " +sc.getParentAssocationClassName() + "-" +sc.getParentRoleName());
            System.out.println(sc.getCQLString());
        }
        
        Long t2 = System.currentTimeMillis();
        System.out.println("Time Taken " + (t2-t1)/1000);
        
    }
    
}

