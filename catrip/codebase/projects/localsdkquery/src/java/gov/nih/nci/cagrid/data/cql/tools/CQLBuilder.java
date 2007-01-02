package gov.nih.nci.cagrid.data.cql.tools;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import java.lang.reflect.Field;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class CQLBuilder {
    public CQLBuilder() {
    }
    private Document doc = null;

    public void loadDocument(String filename){
        try {
            SAXBuilder builder = new SAXBuilder();
            doc = builder.build(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setDocument (Document doc){
        this.doc=doc;
    }
    public Document getDocument (){
        return doc;
    }  
    private Element buildAssociationAndAttachPKAttribute(String name,String roleName){
        Element assocEle = new Element("Association");
        assocEle.setAttribute("name",name);
        assocEle.setAttribute("roleName",roleName);      
        
        Element attrEle = new Element("Attribute");
        attrEle.setAttribute("name","id");
        attrEle.setAttribute("value",name+"_ID");
        attrEle.setAttribute("predicate","EQUAL_TO");  

        assocEle.setContent(attrEle);
        return assocEle;
    }
    
    private Element buildGroup(Element e1, Element e2){
        Element groupEle = new Element("Group");
        groupEle.setAttribute("logicRelation","AND");
        
        if (e1 != null) {
            groupEle.addContent(e1.detach());
        }
        
        if (e2 != null) {
            groupEle.addContent(e2.detach());
        }
        
        return groupEle;
    }

    private boolean checkFiled(String property, Class objectType){
        Field[] declaredFields = objectType.getDeclaredFields();
        Field field = null;
        boolean found = false;
        for (int i=0; i<declaredFields.length; i++) {
            field = (Field)declaredFields[i];
            if (property.equals(field.getName())) {
                found = true;
                break;
            }
            
        }
        return found;
    }

    private Class getClassToCheck(String property , Class cls) {
        boolean found = checkFiled(property,cls); 
        
        if(!found){
            Class superClass  = cls.getSuperclass();
            while (superClass != null) {
                found = checkFiled(property,superClass);  
                
                if (found) {
                    cls = superClass;
                    break;
                } else {
                    superClass = superClass.getSuperclass();
                }
            }
        }  
        
        return cls;
    }
    
    private String getRoleName(String sourceObj,String targetObj){
        String roleName = "";
        try {
           
            // check this class or super class 
            Class sourceClass =Class.forName(sourceObj);
           
            boolean found = false;
            Class targetClass = Class.forName(targetObj);
            String[] classTokens = targetClass.getName().split("\\.");            
            String className = classTokens[classTokens.length-1];            
            roleName = className.substring(0,1).toLowerCase()+className.substring(1,className.length());
            
            sourceClass = getClassToCheck(roleName,sourceClass);
            found = checkFiled(roleName,sourceClass);
            if (!found) {
                roleName = roleName+"Collection";
                sourceClass = getClassToCheck(roleName,sourceClass);
                found = checkFiled(roleName,sourceClass);
            }
            
            if (!found) {
                Class superClass  = targetClass.getSuperclass();
                
                while (superClass != null) {
                    targetClass = superClass;
                    classTokens = targetClass.getName().split("\\.");
                    className = classTokens[classTokens.length-1];
                    roleName = className.substring(0,1).toLowerCase()+className.substring(1,className.length());
                    targetClass = getClassToCheck(roleName,sourceClass);
                    found = checkFiled(roleName,targetClass);
                    if (!found) {
                        roleName = roleName+"Collection";
                        targetClass = getClassToCheck(roleName,sourceClass);
                        found = checkFiled(roleName,targetClass);
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
        loadDocument("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\demo-cae.xml");
        Element root = new Element("CQLQuery");
        Element targetEle = new Element("Target");        

        //String associationPath = "/CQLQuery/Target";
        Element te = doc.getRootElement().getChild("Target");
        
        //first Associtaion
         for (int i=1;i<=level;i++){
             te = te.getChild("Association");
             //associationPath = associationPath+"/Association";
         }
        //Element te = getNodeForXpath(associationPath);
        
        String targetObjectName = te.getAttributeValue("name");
        targetEle.setAttribute("name",targetObjectName);     
        
        //get attributes 
        List attrList = te.getChildren("Attribute");

        for (int j=0; j < attrList.size();j++){
            Element e = (Element)attrList.get(j);
            targetEle.addContent(e.detach());
        }
        
        
        Element e = te.getParentElement();
        
        Element e1 = buildAssociationAndAttachPKAttribute(e.getAttributeValue("name"),getRoleName(targetObjectName,e.getAttributeValue("name")));
        //System.out.println(e1.getAttributeValue("name"));
        
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
        
        Element e2 = te.getChild("Association");
        //Element group = buildGroup(e1,e2);        
        //targetEle.addContent(group);

        if (e1 != null) {
            targetEle.addContent(e1.detach());
        }
        
        if (e2 != null) {
            targetEle.addContent(e2.detach());
        }
        
       targetEle=renderGroups(targetEle);
       root.addContent(targetEle);
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
        List l = ele.getChildren("Association");
        Element group = buildGroup((Element)l.get(0),(Element)l.get(1));
        ele.addContent(group);
        ele.removeChildren("Association");    
        
        return ele;
    }
    private Element buildAssociationAttributeGroup(Element ele) {
        Element group = buildGroup(ele.getChild("Association"),ele.getChild("Attribute"));
        ele.addContent(group);
        ele.removeChild("Association");
        ele.removeChild("Attribute"); 
        
        return ele;
    }    
    
    private Element renderGroups(Element targetEle){
        
        if (targetEle.getChildren("Association").size() >=2 ) {
            buildAssociationGroup(targetEle);
        }

        if (targetEle.getChildren("Association").size() > 0 && targetEle.getChildren("Attribute").size() >0 ) {
            buildAssociationAttributeGroup(targetEle);
        }
        
        // chech for associations 
        Element assoc = targetEle.getChild("Group").getChild("Association");
        while (assoc != null) {
            if (assoc.getChild("Association") != null && assoc.getChild("Attribute") !=null ) {
                assoc = buildAssociationAttributeGroup(assoc);     
                //targetEle.getChild("Group").setContent(assoc);
            }  
            if (assoc.getChild("Group") != null ) {
                assoc = assoc.getChild("Group").getChild("Association");
            } else {
                assoc = null;
            }
            
        }

        return targetEle;
    }

    private void run(){
        System.out.println("Executing ....");
        Long t1 = System.currentTimeMillis(); 

        loadDocument("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\demo-cae.xml");
     //   Element ell = getNodeForXpath("/CQLQuery/Target");
        Element ell = doc.getRootElement().getChild("Target");

        int c = 1;
        
        Element childAssoc = ell.getChild("Association");
        while (childAssoc != null ) {
            childAssoc = childAssoc.getChild("Association");
            System.out.println(buildCQL(c));
            c++;
        }
        Long t2 = System.currentTimeMillis(); 
        System.out.println("Time Taken " + (t2-t1)/1000);        
    }
    public static void main(String[] args) {
        CQLBuilder c = new CQLBuilder();
        c.run();

    }
    
    
    //check for association 

    
    /*
    String cql= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
    "<CQLQuery xmlns=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\n" + 
    "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\data\\schema\\1_gov.nih.nci.cagrid.CQLQuery.xsd\">\n" + 
    "       <Target name=\"edu.duke.catrip.cae.domain.general.Participant\">\n" + 
    "               <Association name=\"edu.pitt.cabig.cae.domain.general.AnnotationEventParameters\" roleName=\"annotationEventParametersCollection\">\n" + 
    "                       <Association name=\"edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade\" roleName=\"annotationSetCollection\">\n" + 
    "                               <Attribute name=\"totalScore\" predicate=\"GREATER_THAN\" value=\"5\"/>\n" + 
    //  "                             <ReturnAttributes>\n" +
    //  "                                             <ReturnAttribute>totalScore</ReturnAttribute>\n" +
    //  "                             </ReturnAttributes>\n" +
    "                       </Association>\n" + 
    "               </Association>\n" + 
    "       </Target>\n" + 
    "</CQLQuery>\n";
    Document doc = null;
    try {
        SAXBuilder builder = new SAXBuilder();
        doc = builder.build(new File("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\demo-cae.xml"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
    StringBuffer buf = new StringBuffer(cql);

    char[] chars = new char[buf.length()];
    buf.getChars(0, chars.length, chars, 0);
    
    CharArrayReader car = new CharArrayReader(chars);
    InputSource source = new InputSource(car);
     java.lang.Object obj = null ;

    try {
        obj = ObjectDeserializer.deserialize(source,CQLQuery.class);
    } catch (DeserializationException e) {
        e.printStackTrace();
    }
    CQLBuilder cQLBuilder = new CQLBuilder();
    CQLQuery cqlQuery = cQLBuilder.getCQL((CQLQuery)obj,"edu.pitt.cabig.cae.domain.general.AnnotationEventParameters","edu.duke.catrip.cae.domain.general.Participant");
    print(cqlQuery);
    cqlQuery = cQLBuilder.getCQL((CQLQuery)obj,"edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade","edu.pitt.cabig.cae.domain.general.AnnotationEventParameters");
    print(cqlQuery);
    
    */         
         /*
         Element firstAssoc = targetEle.getChild("Association");
         
         if (firstAssoc.getChild("Association") != null && firstAssoc.getChild("Attribute") !=null ) {
             Element group = buildGroup(firstAssoc.getChild("Association"),firstAssoc.getChild("Attribute"));
             firstAssoc.addContent(group);
             firstAssoc.removeChild("Association");
             firstAssoc.removeChild("Attribute");
         } else {
             return targetEle;
         }
         targetEle.setContent(firstAssoc);
         */
         
   /* 
    private static void print(CQLQuery cqlQuery){
        
        Writer w = new StringWriter();
           
           javax.xml.namespace.QName q= new javax.xml.namespace.QName("http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery","CQLQuery");

        try {
            ObjectSerializer.serialize(w,cqlQuery,q);
        } catch (SerializationException e) {
            e.printStackTrace();
        }
        System.out.println(w);
    }
    */
}


    /*
     public CQLQuery getCQL(CQLQuery cqlQryIn,String nextTarget, String currentTarget) {
         Association immediateAssociation = cqlQryIn.getTarget().getAssociation();        
         boolean found = false;
         
         while (!found) {
             if (immediateAssociation.getName().equals(nextTarget)) {
                 found = true;
             } else {
                 immediateAssociation = immediateAssociation.getAssociation();
             }
         }
         
         gov.nih.nci.cagrid.cqlquery.Object targetObject = buildTargetObject(immediateAssociation,currentTarget);
         
         CQLQuery cqlQueryOut = new CQLQuery();
         cqlQueryOut.setTarget(targetObject);
         
         return cqlQueryOut;
         
     }
     */
     /*
     private gov.nih.nci.cagrid.cqlquery.Object buildTargetObject(Association associationIn,String currentTarget){
         gov.nih.nci.cagrid.cqlquery.Object targetObject = new gov.nih.nci.cagrid.cqlquery.Object();
         targetObject.setName(associationIn.getName());
         
         targetObject.setAttribute(associationIn.getAttribute());
         
         // the target objcet should be the association now 
         Association currentTargetAsAssociation = new Association();
         currentTargetAsAssociation.setName(currentTarget);
         currentTargetAsAssociation.setRoleName("imaginary role");
         
         //set id attribute 
         Attribute idAttr = new Attribute ("ID1",Predicate.EQUAL_TO, "VALUE1");
         currentTargetAsAssociation.setAttribute(idAttr);
         
         // Get associations of the InAssocation (which became target object )
          Association association = associationIn.getAssociation();
          
          //add to group 
          Group group = new Group();
          group.setLogicRelation(LogicalOperator.AND);
          Association[] assocationsToAdd = new Association[2]; 
          assocationsToAdd[0] = currentTargetAsAssociation;
          assocationsToAdd[1] = association;
         
          group.setAssociation(assocationsToAdd);
          
          targetObject.setGroup(group);
         
         return targetObject;
     }
     */
     
      /*
          public Element getNodeForXpath(String xpathStr){
              XPath xpath;
              Object node=null;
              try {
                  xpath = XPath.newInstance(xpathStr);
                  node = xpath.selectSingleNode(doc);
              } catch (JDOMException e) {
                  e.printStackTrace();
              }
              return (Element)node;
          }

          private Element getParent(String xpath) {
              Element e = getNodeForXpath(xpath);
              return e.getParentElement();
          }

          private Element getChild(String xpath) {
              Element e = getNodeForXpath(xpath);
              return e.getChild("Association");
          }
       */ 
       
       /*
           private void printXML(Document doc1){
               try{
               XMLOutputter outputter =   new XMLOutputter(Format.getPrettyFormat());
               
               Writer s = new  StringWriter();
               
               
               outputter.output(doc1 , s);
               
               System.out.println(s.toString());
               }
               catch (java.io.IOException e){
                   e.printStackTrace();
               }      
           }
       */