package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import java.io.CharArrayReader;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;

import java.util.List;

import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import org.xml.sax.InputSource;


public class CQLBuilder {
    CQLQuery cqlQuery = null;
    public CQLBuilder(CQLQuery cqlQuery) {
            this.cqlQuery = cqlQuery;

    }
    
    private static final Namespace nameSpace = Namespace.getNamespace("ns1","http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery");
  
    private Element buildAssociationAndAttachPKAttribute(String name,String roleName){
        Element assocEle = new Element("Association",nameSpace);
        assocEle.setAttribute("name",name);
        assocEle.setAttribute("roleName",roleName);      
        
        Element attrEle = new Element("Attribute",nameSpace);
        attrEle.setAttribute("name","id");
        attrEle.setAttribute("value",name+"_ID");
        attrEle.setAttribute("predicate","EQUAL_TO");  

        assocEle.setContent(attrEle);
        return assocEle;
    }
    
    private Element buildGroup(Element e1, Element e2){
        Element groupEle = new Element("Group",nameSpace);
        groupEle.setAttribute("logicRelation","AND");        
        if (e1 != null) {
            groupEle.addContent(e1.detach());
        }        
        if (e2 != null) {
            groupEle.addContent(e2.detach());
        }        
        return groupEle;
    }


    
    private String getRoleName(String sourceObj,String targetObj) {
        String roleName = "";
        try {           
            // check this class or super class 
            Class sourceClass =Class.forName(sourceObj);
           
            boolean found = false;
            Class targetClass = Class.forName(targetObj);
            String[] classTokens = targetClass.getName().split("\\.");            
            String className = classTokens[classTokens.length-1];            
            roleName = className.substring(0,1).toLowerCase()+className.substring(1,className.length());
            
            sourceClass = ToolUtil.getClassToCheck(roleName,sourceClass);
            found = ToolUtil.checkFiled(roleName,sourceClass);
            if (!found) {
                roleName = roleName+"Collection";
                sourceClass = ToolUtil.getClassToCheck(roleName,sourceClass);
                found = ToolUtil.checkFiled(roleName,sourceClass);
            }
            
            if (!found) {
                Class superClass  = targetClass.getSuperclass();
                
                while (superClass != null) {
                    targetClass = superClass;
                    classTokens = targetClass.getName().split("\\.");
                    className = classTokens[classTokens.length-1];
                    roleName = className.substring(0,1).toLowerCase()+className.substring(1,className.length());
                    targetClass = ToolUtil.getClassToCheck(roleName,sourceClass);
                    found = ToolUtil.checkFiled(roleName,targetClass);
                    if (!found) {
                        roleName = roleName+"Collection";
                        targetClass = ToolUtil.getClassToCheck(roleName,sourceClass);
                        found = ToolUtil.checkFiled(roleName,targetClass);
                    }             
                    if (found) {
                        break;
                    }
                    superClass = superClass.getSuperclass();
                    if (superClass.getName().equals("java.lang.Object")) {
                        break;
                    }
                } 
            }
            
            if (!found) {
                roleName = "UNDEFINED - THROW ERROR ";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleName;
    }
    public String buildCQL(int level) {
        Document doc = buildDocument(cqlQuery);
        Element root = new Element("CQLQuery",nameSpace);
        Element targetEle = new Element("Target",nameSpace);        

        Element te = doc.getRootElement().getChild("Target",nameSpace);
        //first Associtaion
         for (int i=1;i<=level;i++){
             te = te.getChild("Association",nameSpace);
         }
        
        String targetObjectName = te.getAttributeValue("name");
        targetEle.setAttribute("name",targetObjectName);     
        
        //check for return Attributes ..
        Element retAttr = te.getChild("ReturnAttributes",nameSpace);
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
       
        //get attributes 
        List attrList = te.getChildren("Attribute",nameSpace);

        for (int j=0; j < attrList.size();j++){
            Element e = (Element)attrList.get(j);
            targetEle.addContent(e.detach());
        }
        
        Element e = te.getParentElement();        
        Element e1 = buildAssociationAndAttachPKAttribute(e.getAttributeValue("name"),getRoleName(targetObjectName,e.getAttributeValue("name")));
        // make all the parents as childs  
        // this works only in bi directional scenario 
        Element x = null;
        int c=1;
        while (e.getParentElement() != null) {
            Element pe = e.getParentElement();
            if (!pe.getName().equals("CQLQuery")) {
                Element ae = buildAssociationAndAttachPKAttribute(pe.getAttributeValue("name"),getRoleName(e.getAttributeValue("name"),pe.getAttributeValue("name")));
                if (c == 1 ) {
                    x=ae;
                } else {
                    x = x.addContent(ae);
                }               
            }          
            c++;
            e=pe;
        }
        if (x != null) e1.addContent(x);
        
        Element e2 = te.getChild("Association",nameSpace);

        if (e1 != null) {
            targetEle.addContent(e1.detach());
        }
        
        if (e2 != null) {
            targetEle.addContent(e2.detach());
        }
        
       targetEle=renderGroups(targetEle);
       root.addContent(targetEle);
       root.addContent(qm);
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

    private Element buildAssociationGroup(Element ele) {
        List l = ele.getChildren("Association",nameSpace);
        Element group = buildGroup((Element)l.get(0),(Element)l.get(1));
        ele.addContent(group);
        ele.removeChildren("Association",nameSpace);            
        return ele;
    }
    private Element buildAssociationAttributeGroup(Element ele) {
        Element group = buildGroup(ele.getChild("Association",nameSpace),ele.getChild("Attribute",nameSpace));
        ele.addContent(group);
        ele.removeChild("Association",nameSpace);
        ele.removeChild("Attribute",nameSpace);         
        return ele;
    }    
    
    private Element renderGroups(Element targetEle){        
        if (targetEle.getChildren("Association",nameSpace).size() >=2 ) {
            buildAssociationGroup(targetEle);
        }

        if (targetEle.getChildren("Association",nameSpace).size() > 0 && targetEle.getChildren("Attribute",nameSpace).size() >0 ) {
            buildAssociationAttributeGroup(targetEle);
        }        
        // chech for associations 
        Element assoc = targetEle.getChild("Group",nameSpace).getChild("Association",nameSpace);
        while (assoc != null) {
            if (assoc.getChild("Association",nameSpace) != null && assoc.getChild("Attribute",nameSpace) !=null ) {
                assoc = buildAssociationAttributeGroup(assoc);     
            }  
            if (assoc.getChild("Group",nameSpace) != null ) {
                assoc = assoc.getChild("Group",nameSpace).getChild("Association",nameSpace);
            } else {
                assoc = null;
            }            
        }
        return targetEle;
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
    public static void main(String[] args) {
        String queryDir = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\";
        String qryFile = "demo-cae1.xml";
        java.lang.Object obj = null;

        try {
            obj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream(queryDir+qryFile)),CQLQuery.class);
        } catch (Exception e) {
           e.printStackTrace();
        }
        CQLBuilder cb = new CQLBuilder((CQLQuery)obj);   
        System.out.println("Executing ....");
        Long t1 = System.currentTimeMillis(); 
        
        gov.nih.nci.cagrid.cqlquery.Object target = cb.cqlQuery.getTarget();
        
        int c = 1;
        
        Association childAssoc = target.getAssociation();
        
        while (childAssoc != null ) {            
            System.out.println(cb.buildCQL(c));
            childAssoc = childAssoc.getAssociation();
            c++;
        }
        
        Long t2 = System.currentTimeMillis(); 
        System.out.println("Time Taken " + (t2-t1)/1000);    
        
    }
    

}
    
    