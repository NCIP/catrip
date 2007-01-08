package gov.nih.nci.cagrid.data.cql.tools;

import edu.duke.catrip.cae.domain.general.Participant;

import edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade;
import edu.pitt.cabig.cae.domain.general.AnnotatableEntity;
import edu.pitt.cabig.cae.domain.general.AnnotationEventParameters;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.cacore.HibernateUtil;
import gov.nih.nci.cagrid.data.cql.cacore.LocalCQL2HQL;

import gov.nih.nci.common.util.HQLCriteria;

import java.io.CharArrayReader;
import java.io.File;

import java.io.StringWriter;
import java.io.Writer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.Set;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;

import org.globus.wsrf.encoding.ObjectSerializer;

import org.globus.wsrf.encoding.SerializationException;

import org.hibernate.Session;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import org.xml.sax.InputSource;


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
             doc = builder.build(new File("C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\test2.xml"));
         } catch (Exception e) {
             e.printStackTrace();
         }
        return doc;
    }
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
    private CQLQuery convertStringToCQL(String cqlString){
        
        
        StringBuffer buf = new StringBuffer(cqlString);

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
        return ((CQLQuery)obj);
    }
    
    private List executeQry (String cqlStr){
        CQLQuery cqlQuery = convertStringToCQL(cqlStr);
                        
        LocalCQL2HQL cql2hql = new LocalCQL2HQL("org.hibernate.dialect.Oracle9Dialect");
        String hql="";
        try {
            hql = cql2hql.translate(cqlQuery, false);
        } catch (QueryProcessingException e) {
            e.printStackTrace();
        }
        HQLCriteria hqlCriteria = new HQLCriteria(hql);
        
        Session session = HibernateUtil.currentSession();
        List resultObjects = session.createQuery(hqlCriteria.getHqlString()).list(); 
        HibernateUtil.closeSession();
        return resultObjects;                
    }
    
    private List build(List targetObjects,
                        String targetObjectClassName, 
                        String cqlStr,
                        int level,
                        Map xMap_rename){        
        Class targetObjectClass = null;
        try {
            targetObjectClass = Class.forName(targetObjectClassName);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        
         for (int i=0;i<targetObjects.size();i++){
              Object obj = targetObjects.get(i);
              Class[] par=new Class[0];
              Method mthd;


             try {
                 Object[] parms = new Object[0];
                 mthd = targetObjectClass.getMethod("getId",par);
                 Object outputObj= mthd.invoke(obj,parms);
                 String id = outputObj.toString();
                 String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",id);
                 int counter = 1;
                 while(level != counter  ) {
                     BuildInfoBean bib = (BuildInfoBean)xMap_rename.get(counter+"");
                     //
                     String methodName = "get"+bib.getRoleName().substring(0,1).toUpperCase()+bib.getRoleName().substring(1,bib.getRoleName().length());
                     mthd = targetObjectClass.getMethod(methodName,par);
                     outputObj= mthd.invoke(obj,parms);
                     if(bib.getRoleName().endsWith("Collection")) {
                         Collection c = (Collection)outputObj;
                         Iterator itr= c.iterator();
                         while (itr.hasNext()){
                             Object obj1 = itr.next();
                              mthd = Class.forName(bib.getAssociationClassName()).getMethod("getId",par);
                              Object id1 = mthd.invoke(obj1,parms);
                              cqlStr1 = cqlStr.replaceAll(bib.getAssociationClassName()+"_ID",id1.toString());
                             System.out.println(cqlStr1);
                             
                         }
                     }
                     
                     counter ++;
                 }
                 
                 
                 
                 /*
                 CQLQuery cqlQuery = convertStringToCQL(cqlStr1);
                 
                 LocalCQL2HQL cql2hql = new LocalCQL2HQL("org.hibernate.dialect.Oracle9Dialect");
                 String hql = cql2hql.translate(cqlQuery, false);
                 HQLCriteria hqlCriteria = new HQLCriteria(hql);
                 
                 Session session = HibernateUtil.currentSession();
                 List targetObjects1 = session.createQuery(hqlCriteria.getHqlString()).list();
                 
                 if (cardinality.equals("many")) {
                     
                     
                     // get type of collection 
                     Class classTemp= ToolUtil.getClassToCheck(roleName,targetObjectClass);
                     Field f = classTemp.getDeclaredField(roleName);
                     Class collectionType = f.getType();
                     //System.out.println(collectionType.getName());
                     Set hs = null;
                     List al = null;
                     if (collectionType.getName().equals("java.util.Collection")) {
                         // implementation can be HashSet,ArrayList
                          hs = new HashSet(targetObjects1);
                          al = new ArrayList(targetObjects1);
                         
                     } else if (collectionType.getName().equals("java.util.List") || collectionType.getName().equals("java.util.ArrayList")) {
                         // implementation can be ArrayList
                         
                     
                     } else if (collectionType.getName().equals("java.util.Set") || collectionType.getName().equals("java.util.HashSet")) {
                         // implementation can be HashSet  
                         hs = new HashSet(targetObjects1);
                     }
                     
                     String methodName = "set"+roleName.substring(0,1).toUpperCase()+roleName.substring(1,roleName.length());
                     Class[] p1 =new Class[1];
                     p1[0] = collectionType;
      
                     Object[] p2 = new Object[1];
                     p2[0] = al;                   
                     
                     Method m1 = targetObjectClass.getMethod(methodName,p1);
                     
                     try {
                         m1.invoke(obj,p2);
                     } catch (Exception e1){
                         p2[0] = hs; 
                         m1.invoke(obj,p2);
                         e1.printStackTrace();
                     }
                     
                 }
                 */
             } catch (Exception e) {
                e.printStackTrace();
             }
             HibernateUtil.closeSession();
         }
        return targetObjects;
  }        
    
    private List buildX(List targetObjects,
                        String targetObjectClassName, 
                        String targetObjectOfCQL,
                        String roleName,
                        String setMethodOnThisObject,
                        String cqlStr){
        Class primaryTargetObjectClass = null;
        try {
            primaryTargetObjectClass = Class.forName(targetObjectClassName);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] roleArray = roleName.split("-");
        
        for (int i=0;i<targetObjects.size();i++){
            Object targetObject = targetObjects.get(i);
            
                //get Ids to replace in dynamic CQL
                try {
                    
                    if (roleArray.length>1){
                        for (int j=0; j<roleArray.length-1;j++){
                            String tempRole = roleArray[0];
                            String getMethod = "get"+tempRole.substring(0,1).toUpperCase()+tempRole.substring(1,tempRole.length());
                            Method getMethodOnTargetObject = primaryTargetObjectClass.getMethod(getMethod,new Class[0]);
                            Object result = getMethodOnTargetObject.invoke(targetObject,new Object[0]);
                            Class returnType = getMethodOnTargetObject.getReturnType();
                            
                            
                            
                        }
                    }
                    
                    
                    Class classTemp= ToolUtil.getClassToCheck(roleName,primaryTargetObjectClass);
                    Field f = classTemp.getDeclaredField(roleName);
                    Class collectionType = f.getType();
                    Class[] p1 =new Class[1];
                    p1[0] = collectionType;
                    
                   Method targetObjectIdMethod = primaryTargetObjectClass.getMethod("getId",new Class[0]);
                   Object idObj= targetObjectIdMethod.invoke(targetObject,new Object[0]);
                   String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",idObj.toString());

                   String setMethodName = "set"+roleName.substring(0,1).toUpperCase()+roleName.substring(1,roleName.length());
                   Method setterMethodOnTargetObject = primaryTargetObjectClass.getMethod(setMethodName,p1);
                   List nextLevelObjects = executeQry(cqlStr1);
                    
                    // SET THESE OBJECT(S) TO TARGET OBJECT 

                   
                } catch (Exception e) {
                    e.printStackTrace();
                }

            
        } 
        return targetObjects;
    }


    public List buildResultObjects (List targetObjects,CQLQuery query) {     
        Document doc = buildDocument(query);
        
        CQLBuilder builder = new CQLBuilder();
       // builder.setDocument(doc);
        
        Element targetObjectEle = doc.getRootElement().getChild("Target");
        String targetObjectClassName = targetObjectEle.getAttributeValue("name");

        int c = 1;
        String cqlStr="";
//      Loop first level
         Class objectClass = null;
        try {
         objectClass = Class.forName(targetObjectClassName);
         String  roleName = targetObjectEle.getChild("Association").getAttributeValue("roleName");
            cqlStr = builder.buildCQL(c);
            cqlStr=cqlStr.replaceAll("<CQLQuery","<CQLQuery xmlns=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\"");
         for (int i=0;i<targetObjects.size();i++){
             Object targetObject = targetObjects.get(i);
             System.out.println(targetObject.getClass().getName());
             Method targetObjectIdMethod = objectClass.getMethod("getId",new Class[0]);
             Object idObj= targetObjectIdMethod.invoke(targetObject,new Object[0]); 
             String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",idObj.toString());
             System.out.println(cqlStr1);
             List assocObjects = this.executeQry(cqlStr1);
             // SET ON TARGET OBJECT
              Class classTemp= ToolUtil.getClassToCheck(roleName,objectClass);
              Field f = classTemp.getDeclaredField(roleName);
              Class dataType = f.getType(); 
             String methodName = "set"+roleName.substring(0,1).toUpperCase()+roleName.substring(1,roleName.length());

             Class[] p1 =new Class[1];
             p1[0] = dataType;
             
             if (roleName.endsWith("Collection")){                  
                 Set hs = null;
                 List al = null;
                 if (dataType.getName().equals("java.util.Collection")) {
                     // implementation can be HashSet,ArrayList
                      hs = new HashSet(assocObjects);
                      al = new ArrayList(assocObjects);
                     
                 } else if (dataType.getName().equals("java.util.List") || dataType.getName().equals("java.util.ArrayList")) {
                     // implementation can be ArrayList
                      al = new ArrayList(assocObjects);
                 
                 } else if (dataType.getName().equals("java.util.Set") || dataType.getName().equals("java.util.HashSet")) {
                     // implementation can be HashSet  
                     hs = new HashSet(assocObjects);
                 }
                 
                 Object[] p2 = new Object[1];
                 p2[0] = al;                   
                 
                 Method m1 = objectClass.getMethod(methodName,p1);
                 
                 try {
                     m1.invoke(targetObject,p2);
                 } catch (Exception e1){
                     p2[0] = hs; 
                     m1.invoke(targetObject,p2);
                     e1.printStackTrace();
                 }
             } else {
                 Method m1 = objectClass.getMethod(methodName,p1);
                 Object[] p2 = new Object[1];
                 
                 p2[0] = assocObjects.get(0);  
                 m1.invoke(targetObject,p2);
             }
                   
         }    
        } catch (Exception e ){
            e.printStackTrace();
        }
        
        c++;
// Level2

 try {
            cqlStr = builder.buildCQL(c);
            cqlStr=cqlStr.replaceAll("<CQLQuery","<CQLQuery xmlns=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\"");

        for (int i=0;i<targetObjects.size();i++){
            Object targetObject = targetObjects.get(i);
            System.out.println(targetObject.getClass().getName());
            Method targetObjectIdMethod = objectClass.getMethod("getId",new Class[0]);
            Object idObj= targetObjectIdMethod.invoke(targetObject,new Object[0]); 
            String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",idObj.toString());
            System.out.println(cqlStr1);
            // FIRTS GET
            String roleName = targetObjectEle.getChild("Association").getAttributeValue("roleName");;
            String assocClassName = targetObjectEle.getChild("Association").getAttributeValue("name");;
            String methodName = "get"+roleName.substring(0,1).toUpperCase()+roleName.substring(1,roleName.length());
            Method mthd = objectClass.getMethod(methodName,new Class[0]);
            Object outputObj= mthd.invoke(targetObject,new Object[0]);
            Class assocClass = Class.forName(assocClassName);
            //next role 
             String roleNameX = targetObjectEle.getChild("Association").getChild("Association").getAttributeValue("roleName");;
             //String assocClassNameX = targetObjectEle.getChild("Association").getChild("Association").getAttributeValue("name");;
            //Class assocClassX = Class.forName(assocClassNameX);
            
            if(roleName.endsWith("Collection")) {
                Collection coll = (Collection)outputObj;
                Iterator itr= coll.iterator();
                while (itr.hasNext()){
                    Object obj1 = itr.next();
                     mthd = assocClass.getMethod("getId",new Class[0]);
                     Object id1 = mthd.invoke(obj1,new Object[0]);
                     cqlStr1 = cqlStr1.replaceAll(assocClassName+"_ID",id1.toString());
                     System.out.println(cqlStr1);
                     List assocObjectsX = this.executeQry(cqlStr1);
                     System.out.println(assocObjectsX.size());
                     methodName = "set"+roleNameX.substring(0,1).toUpperCase()+roleNameX.substring(1,roleNameX.length());
                     //mthd = assocClass.getMethod(methodName,new Class[0]); 
                      Class classTemp= ToolUtil.getClassToCheck(roleNameX,assocClass);
                      Field f = classTemp.getDeclaredField(roleNameX);
                      Class dataType = f.getType();     
  
                    Class[] p1 =new Class[1];
                    p1[0] = dataType;
                    
                      
                    if (roleNameX.endsWith("Collection")){

                        Set hs = null;
                        List al = null;
                        if (dataType.getName().equals("java.util.Collection")) {
                            // implementation can be HashSet,ArrayList
                             hs = new HashSet(assocObjectsX);
                             al = new ArrayList(assocObjectsX);
                            
                        } else if (dataType.getName().equals("java.util.List") || dataType.getName().equals("java.util.ArrayList")) {
                            // implementation can be ArrayList
                             al = new ArrayList(assocObjectsX);
                        
                        } else if (dataType.getName().equals("java.util.Set") || dataType.getName().equals("java.util.HashSet")) {
                            // implementation can be HashSet  
                            hs = new HashSet(assocObjectsX);
                        }

                        
                        Object[] p2 = new Object[1];
                        p2[0] = al;                   
                        
                        Method m1 = assocClass.getMethod(methodName,p1);
                        
                        try {
                            m1.invoke(obj1,p2);
                        } catch (Exception e1){
                            p2[0] = hs; 
                            m1.invoke(obj1,p2);
                            e1.printStackTrace();
                        }
                        
                        
                    } else {
                        Object[] p2 = new Object[1];
                        p2[0] = assocObjectsX.get(0);                   
                        
                        Method m1 = assocClass.getMethod(methodName,p1);
                        m1.invoke(obj1,p2);
                        
                    }
                     
                    
                }
            } else {

                 mthd = assocClass.getMethod("getId",new Class[0]);
                 Object id1 = mthd.invoke(outputObj,new Object[0]);
                 cqlStr1 = cqlStr1.replaceAll(assocClassName+"_ID",id1.toString());
                 System.out.println(cqlStr1);
                 List assocObjectsX = this.executeQry(cqlStr1);
                 System.out.println(assocObjectsX.size());
                 methodName = "set"+roleNameX.substring(0,1).toUpperCase()+roleNameX.substring(1,roleNameX.length());
                 //mthd = assocClass.getMethod(methodName,new Class[0]); 
                  Class classTemp= ToolUtil.getClassToCheck(roleNameX,assocClass);
                  Field f = classTemp.getDeclaredField(roleNameX);
                  Class dataType = f.getType();     
                  
                Class[] p1 =new Class[1];
                p1[0] = dataType;
                
                
                
                 if (roleNameX.endsWith("Collection")){

                     Set hs = null;
                     List al = null;
                     if (dataType.getName().equals("java.util.Collection")) {
                         // implementation can be HashSet,ArrayList
                          hs = new HashSet(assocObjectsX);
                          al = new ArrayList(assocObjectsX);
                         
                     } else if (dataType.getName().equals("java.util.List") || dataType.getName().equals("java.util.ArrayList")) {
                         // implementation can be ArrayList
                          al = new ArrayList(assocObjectsX);
                     
                     } else if (dataType.getName().equals("java.util.Set") || dataType.getName().equals("java.util.HashSet")) {
                         // implementation can be HashSet  
                         hs = new HashSet(assocObjectsX);
                     }

                     
                     Object[] p2 = new Object[1];
                     p2[0] = assocObjectsX.get(0);                   
                     
                     Method m1 = assocClass.getMethod(methodName,p1);
                     
                     try {
                         m1.invoke(outputObj,p2);
                     } catch (Exception e1){
                         p2[0] = hs; 
                         m1.invoke(outputObj,p2);
                         e1.printStackTrace();
                     }
                     
                     
                 } else {
                     Object[] p2 = new Object[1];
                     p2[0] = assocObjectsX.get(0);                   
                     
                     Method m1 = assocClass.getMethod(methodName,p1);
                     m1.invoke(outputObj,p2);
                     
                 }
                 
                 
            }
            //List assocObjects = this.executeQry(cqlStr1);
            
            
            
        }
        } catch (Exception e ){
            e.printStackTrace();
        }
        /*
        String roleName = "";
        String objectName = "";

                
        
        targetObjectClassName = targetObjectEle.getAttributeValue("name");
        String setMethodOnThisObject = targetObjectClassName;
        String lastRole = "";
        String lastObject = targetObjectClassName;
        List idsToReplace = new ArrayList();
        Map stuffObjects = new HashMap();
        Map mapx = new HashMap();
        while (targetObjectEle.getChild("Association") != null ) {
            lastRole = roleName;
            //lastObject = objectName;
            if (!lastRole.equals("")) {
                lastObject = objectName;
            }
            
            roleName = targetObjectEle.getChild("Association").getAttributeValue("roleName");
            objectName = targetObjectEle.getChild("Association").getAttributeValue("name");
            idsToReplace.add(lastObject+"_ID");
            

            
            cqlStr = builder.buildCQL(c);
            cqlStr=cqlStr.replaceAll("<CQLQuery","<CQLQuery xmlns=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\"");
            System.out.println(cqlStr);
            try {
                Class objectClass = Class.forName(lastObject);    
                for (int i=0;i<targetObjects.size();i++){
                    Object targetObject = targetObjects.get(i);
                    System.out.println(targetObject.getClass().getName());
                    Method targetObjectIdMethod = objectClass.getMethod("getId",new Class[0]);
                    Object idObj= targetObjectIdMethod.invoke(targetObject,new Object[0]); 
                    String cqlStr1 = cqlStr.replaceAll(lastObject+"_ID",idObj.toString());
                    System.out.println(cqlStr1);
                    List assocObjects = this.executeQry(cqlStr1);
                    
                    
                    // SET ON TARGET OBJECT
                    //stuffObjects.put(idObj.toString(),assocObjects);                    
                }
                */
                /*
                if (stuffObjects.size() == 0) {
                    for (int i=0;i<targetObjects.size();i++){
                        Object targetObject = targetObjects.get(i);
                        System.out.println(targetObject.getClass().getName());
                        Method targetObjectIdMethod = objectClass.getMethod("getId",new Class[0]);
                        Object idObj= targetObjectIdMethod.invoke(targetObject,new Object[0]); 
                        String cqlStr1 = cqlStr.replaceAll(lastObject+"_ID",idObj.toString());
                        System.out.println(cqlStr1);
                        List assocObjects = this.executeQry(cqlStr1);
                        // SET ON TARGET OBJECT
                        stuffObjects.put(idObj.toString(),assocObjects);                    
                    }
                } else {
                    Set keys = stuffObjects.keySet();
                    Iterator keyItr = keys.iterator();
                    
                    while (keyItr.hasNext()){
                        String id = keyItr.next().toString();
                        List l = (List)stuffObjects.get(id);
                        String cqlStr1 = cqlStr.replaceAll("edu.duke.catrip.cae.domain.general.Participant_ID",id);
                        Map mapy = new HashMap();
                        for (int i=0;i<l.size();i++){                            
                            Object targetObject = l.get(i);
                            System.out.println(targetObject.getClass().getName());
                            Method targetObjectIdMethod = objectClass.getMethod("getId",new Class[0]);
                            Object idObj= targetObjectIdMethod.invoke(targetObject,new Object[0]); 
                            cqlStr1 = cqlStr1.replaceAll(lastObject+"_ID",idObj.toString());
                            System.out.println(cqlStr1);
                            List assocObjects = this.executeQry(cqlStr1);  
                            mapy.put(idObj.toString(),assocObjects);                            
                        }
                        mapx.put(id,mapy);                        
                    }
                }
                
            } catch (Exception e ){
                e.printStackTrace();
            }
            
            targetObjectEle = targetObjectEle.getChild("Association");
            
            //cqlMap.put(c+"",cqlStr);            
            c++;
        }
        
        Iterator keys = mapx.keySet().iterator();
        while (keys.hasNext()) {
            String id= keys.next().toString();
            Map mapy = (Map)mapx.get(id);
            System.out.println(id + "   " + mapy.size());
        }
                 */

                 for (int i=0;i<targetObjects.size();i++){
                     NottinghamHistopathologicGrade n = (NottinghamHistopathologicGrade)targetObjects.get(i);
                     AnnotatableEntity ae = n.getAnnotationEventParameters().getAnnotatableEntity();
                     
                     Participant p = (Participant)ae;
                     
                     System.out.println(n.getTotalScore() + "   " + p.getLastName());
                     
                 }
                 
     for (int i=0;i<targetObjects.size();i++){
         Participant p = (Participant)targetObjects.get(i);
         
         System.out.println(p.getFirstName() + "   " + p.getAnnotationEventParametersCollection().size());
         
         Collection l = p.getAnnotationEventParametersCollection();
         Iterator ltr = l.iterator();
         while (ltr.hasNext()) {
             AnnotationEventParameters aep = (AnnotationEventParameters)ltr.next();
             Collection l1 = aep.getAnnotationSetCollection();
             Iterator ltr1 = l1.iterator();
             while (ltr1.hasNext()) {
                 NottinghamHistopathologicGrade n = (NottinghamHistopathologicGrade)ltr1.next();
                 System.out.println("   " + n.getTotalScore());
             }
         }
         
     }
     return targetObjects;   
    }
    
    public static void main (String[] args ) {
        String queryDir = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\";
        String qryFile = "demo-cae.xml";
    }
}


/*
 
 
 //Class[] constructorParams =new Class[1];
 //constructorParams[0] = Class.forName("java.util.Collection");
 //Constructor c1 = collectionType.getConstructor(constructorParams);
 //Object arglist[] = new Object[1];
 //arglist[0] = targetObjects1;
 
 //Object retobj = c1.newInstance(arglist); 
  
 
 */