package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;


public class ResultObjectAssembler extends AbstractResultObjectAssembler {


    public ResultObjectAssembler(Session session,String dialect) {
        super(session,dialect);
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
   //-------SRINI UNCOMMENT          if(targetObjectEle.getAssociation().getReturnAttributes() != null ) {
   //-------SRINI UNCOMMENT               returnAttrbs = targetObjectEle.getAssociation().getReturnAttributes().getReturnAttribute();
   //-------SRINI UNCOMMENT           }

            try {

                cqlStr = builder.buildCQL(c);
                //System.out.println(cqlStr);
                //cqlStr=cqlStr.replaceAll("<CQLQuery","<CQLQuery xmlns=\"http://CQL.caBIG/1/gov.nih.nci.cagrid.CQLQuery\"");

                for (int i=0;i<targetObjects.size();i++){
                    Object targetObject = targetObjects.get(i);
                    String id = ToolUtil.performGetOperation(objectClass,targetObject,"id").toString();
                    String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",id);
                  //  System.out.println(cqlStr1);
                    if (c >1 ) {
                        Object outputObj= ToolUtil.performGetOperation(objectClass,targetObject,prevRole);
                        Class assocClass = Class.forName(prevClassName);

                        if(prevRole.endsWith("Collection")) {
                            Collection coll = (Collection)outputObj;
                            Iterator itr= coll.iterator();
                            while (itr.hasNext()){
                                 Object obj1 = itr.next();
                                 String id1 = ToolUtil.performGetOperation(assocClass,obj1,"id").toString();
                                 String cqlStr2 = cqlStr1.replaceAll(prevClassName+"_ID",id1);
                                // System.out.println(cqlStr2);
                                 List assocObjectsX = executeQry(cqlStr2);
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
            
            System.out.print (p.getFirstName() + "   " + p.getLastName());
            
            Collection l = p.getAnnotationEventParametersCollection();
            Iterator ltr = l.iterator();
            while (ltr.hasNext()) {
                AnnotationEventParameters aep = (AnnotationEventParameters)ltr.next();
                Collection l1 = aep.getAnnotationSetCollection();
                Iterator ltr1 = l1.iterator();
                while (ltr1.hasNext()) {
                    NottinghamHistopathologicGrade n = (NottinghamHistopathologicGrade)ltr1.next();
                    System.out.print ("   " + n.getTotalScore() + " " + n.getMitoticCount() + " ");
                }
            }
            System.out.println("   ");
            
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