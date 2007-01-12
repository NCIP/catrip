package gov.nih.nci.cagrid.data.cql.tools;

import edu.duke.catrip.cae.domain.general.Participant;

import edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade;
import edu.pitt.cabig.cae.domain.general.AnnotationEventParameters;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.cacore.HibernateUtil;
import gov.nih.nci.cagrid.data.cql.cacore.LocalCQL2HQL;
import gov.nih.nci.common.util.HQLCriteria;

import java.io.CharArrayReader;
import java.io.StringWriter;
import java.io.Writer;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.globus.wsrf.encoding.ObjectSerializer;
import org.globus.wsrf.encoding.SerializationException;

import org.hibernate.Session;

import org.xml.sax.InputSource;


public class ResultObjectAssembler {
    private String hibernateCfgFile ="";
    private String dataBaseURL  ="";
    private String schemaOrUser  ="";
    public ResultObjectAssembler(String hibernateCfgFile,String dataBaseURL,String schemaOrUser) {
        this.hibernateCfgFile=hibernateCfgFile;
        this.dataBaseURL=dataBaseURL;
        this.schemaOrUser=schemaOrUser;
        
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
        
        Session session = HibernateUtil.currentSession(hibernateCfgFile,dataBaseURL,schemaOrUser);
        List resultObjects = session.createQuery(hqlCriteria.getHqlString()).list(); 
        HibernateUtil.closeSession();
        return resultObjects;                
    }
    
   
    public List buildResultObjects (List targetObjects,CQLQuery query) {     
        //Document doc = buildDocument(query);        
        CQLBuilder builder = new CQLBuilder(query);
       // builder.setDocument(doc);
        
        gov.nih.nci.cagrid.cqlquery.Object targetObjectEle = query.getTarget();
        
        //Element targetObjectEle = doc.getRootElement().getChild("Target");
        //String targetObjectClassName = targetObjectEle.getAttributeValue("name");
         String targetObjectClassName = targetObjectEle.getName();
        
        int c = 1;
        String cqlStr="";
        Class objectClass = null;
        try {
            objectClass = Class.forName(targetObjectClassName);
        } catch (Exception e ){
           e.printStackTrace();
        }   
        String prevRole = "";
        String prevClassName = "";
        while (targetObjectEle.getAssociation() != null ) {
         String  roleName = targetObjectEle.getAssociation().getRoleName();
         String assocClassName = targetObjectEle.getAssociation().getName();
            String returnAttrbs[] = null;
            if(targetObjectEle.getAssociation().getReturnAttributes() != null ) {
                returnAttrbs = targetObjectEle.getAssociation().getReturnAttributes().getReturnAttribute();
            }
            
            try {
               
                cqlStr = builder.buildCQL(c);
                //System.out.println(cqlStr);
                //cqlStr=cqlStr.replaceAll("<CQLQuery","<CQLQuery xmlns=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\"");
                
                for (int i=0;i<targetObjects.size();i++){
                    Object targetObject = targetObjects.get(i);
                    String id = ToolUtil.performGetOperation(objectClass,targetObject,"id").toString();                    
                    String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",id);
                 //   System.out.println(cqlStr1);
                    if (c >1 ) {

                        Object outputObj= ToolUtil.performGetOperation(objectClass,targetObject,prevRole);
                        Class assocClass = Class.forName(prevClassName);
                        
                        if(prevRole.endsWith("Collection")) {
                            Collection coll = (Collection)outputObj;
                            Iterator itr= coll.iterator();
                            while (itr.hasNext()){
                                 Object obj1 = itr.next();                
                                 String id1 = ToolUtil.performGetOperation(assocClass,obj1,"id").toString();                           
                                 cqlStr1 = cqlStr1.replaceAll(prevClassName+"_ID",id1);                            
                                 //System.out.println(cqlStr1);
                                 List assocObjectsX = this.executeQry(cqlStr1);                            
                                 List convertedObjectList = ToolUtil.buildObjcets(assocObjectsX,returnAttrbs,assocClassName);
                                 ToolUtil.performSetOperation(roleName,assocClass,obj1,convertedObjectList);                              
                                
                            }
                        } else {
                             String id1 = ToolUtil.performGetOperation(assocClass,outputObj,"id").toString();
                             cqlStr1 = cqlStr1.replaceAll(prevClassName+"_ID",id1);
                            // System.out.println(cqlStr1);
                             
                             List assocObjectsX = this.executeQry(cqlStr1);
                             List convertedObjectList = ToolUtil.buildObjcets(assocObjectsX,returnAttrbs,assocClassName);
                             ToolUtil.performSetOperation(roleName,assocClass,outputObj,convertedObjectList); 
                        }
                    } else {
                        //System.out.println(cqlStr1);
                        List assocObjects = this.executeQry(cqlStr1);  
                        List convertedObjectList = ToolUtil.buildObjcets(assocObjects,returnAttrbs,assocClassName);
                        ToolUtil.performSetOperation(roleName,objectClass,targetObject,convertedObjectList);  
                    }                          
                }                    
            } catch (Exception e ){
               e.printStackTrace();
            }
            prevRole = roleName;
            prevClassName = assocClassName;            
            targetObjectEle = targetObjectEle.getAssociation();
            c++;
        }
        
  /*     
        for (int i=0;i<targetObjects.size();i++){
            Participant p = (Participant)targetObjects.get(i);
            
            System.out.println(p.getFirstName() + "   " + p.getLastName());
            
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
       
   */     
     return targetObjects;   
    }

    
    public static void main (String[] args ) {
        String queryDir = "C:\\CVS-CodeBase\\catrip\\codebase\\projects\\localsdkquery\\testCQL\\test\\";
        String qryFile = "demo-cae.xml";
    }
}


    /*

    */