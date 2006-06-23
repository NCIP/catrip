
package edu.pitt.cabig.cae.domain.breast;

import edu.pitt.cabig.cae.dao.util.HibernateUtil;

import java.lang.String;


import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class NottinghamHistopathologicGradeTest extends TestCase {
  private static Log log = LogFactory.getLog(NottinghamHistopathologicGradeTest.class); 
  
  public NottinghamHistopathologicGradeTest(String sTestName) {
      super(sTestName);
  }

  
   public void setUp() {

   }

   public void tearDown() {
   }
  
   
   public static Test suite() {
       return new TestSuite(NottinghamHistopathologicGradeTest.class);
   }


   public void testInsert(){
       try {
           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();
           
           NottinghamHistopathologicGrade obj = null;
           
           for (int i=11;i<=12;i++)  {
               obj = new NottinghamHistopathologicGrade();         
               
               obj.setId(new Long(i));
               obj.setMitoticCount(98);
               obj.setNuclearPleomorphism(9898);
               obj.setTubuleFormation(8976);   
               
               session.delete(obj);
               session.save(obj);
           }
                  
           tx.commit();
           //dbCleanup(session);
           HibernateUtil.closeSession();


       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void testSelect() {
       try {
           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();
                      
           List result = new ArrayList(); 
           result = session.createQuery("from NottinghamHistopathologicGrade").list();
           
           tx.commit();
           HibernateUtil.closeSession();
           
               for (int i = 0; i<result.size(); i++) {
                   NottinghamHistopathologicGrade obj = (NottinghamHistopathologicGrade) result.get(i);
                   System.out.println(obj.getId());
                   System.out.println(obj.getTubuleFormation());
               }

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
  

  
          
  