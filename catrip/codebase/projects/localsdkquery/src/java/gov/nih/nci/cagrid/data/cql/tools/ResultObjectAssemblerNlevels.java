package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;


public class ResultObjectAssemblerNlevels extends AbstractResultObjectAssembler {

    public ResultObjectAssemblerNlevels(Session session,String dialect) {
        super(session,dialect);
    }

    private List executeAndSet (String cqlStr,String[] returnAttrbs,String assocClassName){
        List results = executeQry(cqlStr);
        List convertedObjectList = null;

        try {
            convertedObjectList = ToolUtil.buildObjcets(results,returnAttrbs,assocClassName);
        } catch (Exception e) {
           e.printStackTrace();
        }

        return convertedObjectList;
    }

    public List buildResultObjects (List targetObjects,CQLQuery query) {
        CQLBuilder builder = new CQLBuilder(query);
        gov.nih.nci.cagrid.cqlquery.Object targetObjectEle = query.getTarget();
        String targetObjectClassName = targetObjectEle.getName();
        int c = 1;
        String cqlStr="";
        Class targetObjectClass = null;

        String prevRole = "";
        String prevClassName = "";
        Map prevDetailsMap = new HashMap();
        while (targetObjectEle.getAssociation() != null ) {
         String  roleName = targetObjectEle.getAssociation().getRoleName();
         String assocClassName = targetObjectEle.getAssociation().getName();
            String returnAttrbs[] = null;
            if(targetObjectEle.getAssociation().getReturnAttributes() != null ) {
                 returnAttrbs = targetObjectEle.getAssociation().getReturnAttributes().getReturnAttribute();
             }
            try {
                cqlStr = builder.buildCQL(c);
                for (int i=0;i<targetObjects.size();i++){
                    Object targetObject = targetObjects.get(i);
                    targetObjectClass = Class.forName(targetObjectClassName);
                    String id = ToolUtil.performGetOperation(targetObjectClass,targetObject,"id").toString();
                    String cqlStr1 = cqlStr.replaceAll(targetObjectClassName+"_ID",id);
                   // System.out.println(cqlStr1);
                    int level = 1;
                    while ( level <  c ) {
                        Object beanObj = prevDetailsMap.get(level+"");
                        if (beanObj != null) {
                            BuildInfoBean bb = (BuildInfoBean)beanObj;
                            prevRole = bb.getRoleName();
                            prevClassName = bb.getAssociationClassName();
                            Object outputObj= ToolUtil.performGetOperation(targetObjectClass,targetObject,prevRole);

                            targetObjectClass = Class.forName(prevClassName);
                            if(prevRole.endsWith("Collection")) {
                                Collection coll = (Collection)outputObj;
                                Iterator itr= coll.iterator();
                                while (itr.hasNext()) {
                                    Object obj1 = itr.next();
                                    String id1 = ToolUtil.performGetOperation(targetObjectClass,obj1,"id").toString();
                                    String cqlStr2 = cqlStr1.replaceAll(prevClassName+"_ID",id1);
                                    //System.out.println(cqlStr2);
                                    targetObject = obj1;
                                    if (level == c-1 ) {
                                        List results = executeAndSet(cqlStr2,returnAttrbs,assocClassName);
                                        ToolUtil.performSetOperation(roleName,targetObjectClass,targetObject,results);
                                    }
                                }
                            } else {
                                String id1 = ToolUtil.performGetOperation(targetObjectClass,outputObj,"id").toString();
                                cqlStr1 = cqlStr1.replaceAll(prevClassName+"_ID",id1);
                                //System.out.println(cqlStr1);
                                targetObject = outputObj;
                                if (level == c-1 ) {
                                    List results = executeAndSet(cqlStr1,returnAttrbs,assocClassName);
                                    ToolUtil.performSetOperation(roleName,targetObjectClass,targetObject,results);
                                }
                            }
                        }
                        level++;
                    }
                    if (level == 1) {
                        List results = executeAndSet(cqlStr1,returnAttrbs,assocClassName);
                        ToolUtil.performSetOperation(roleName,targetObjectClass,targetObject,results);
                    }
                }
            } catch (Exception e ){
               e.printStackTrace();
            }

            BuildInfoBean bib = new BuildInfoBean();
            bib.setRoleName(roleName);
            bib.setAssociationClassName(assocClassName);
            prevDetailsMap.put(c+"",bib);
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

  for (int i=0;i<targetObjects.size();i++){
      ParticipantMedicalIdentifier  pmi = (ParticipantMedicalIdentifier)targetObjects.get(i);

      System.out.print (pmi.getMedicalRecordNumber() + " ");
      Participant p = pmi.getParticipant();
      System.out.print(p.getFirstName() + "   " + p.getLastName() + " ");

      Collection l = p.getAnnotationEventParametersCollection();
      Iterator ltr = l.iterator();
      while (ltr.hasNext()) {
          AnnotationEventParameters aep = (AnnotationEventParameters)ltr.next();
          Collection l1 = aep.getAnnotationSetCollection();
          Iterator ltr1 = l1.iterator();
          while (ltr1.hasNext()) {
              NottinghamHistopathologicGrade n = (NottinghamHistopathologicGrade)ltr1.next();
              System.out.print("   " + n.getTotalScore() + " " + n.getMitoticCount());
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