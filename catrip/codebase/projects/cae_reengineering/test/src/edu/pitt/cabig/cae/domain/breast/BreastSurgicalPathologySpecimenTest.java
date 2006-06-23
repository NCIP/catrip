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

public class BreastSurgicalPathologySpecimenTest extends TestCase {
  private static Log log = LogFactory.getLog(BreastSurgicalPathologySpecimenTest.class); 
  
  public BreastSurgicalPathologySpecimenTest(String sTestName) {
      super(sTestName);
  }

  
   public void setUp() {

   }

   public void tearDown() {
   }
  
   
   public static Test suite() {
       return new TestSuite(BreastSurgicalPathologySpecimenTest.class);
   }


   public void testInsert(){
       try {
           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();
           
           BreastSurgicalPathologySpecimen bc = null; 
           
           for (int i=15;i<=16;i++)  {
               bc = new BreastSurgicalPathologySpecimen();
               
               
               bc.setId(new Long(i));
               bc.setLateralityMVR("l mvr");
               bc.setOtherSurgicalProcedure("ot sp");

               
               Collection c = new ArrayList();
               c.add("pr 1 "+i);
               c.add("pr 2 "+i);
               
               bc.setSurgicalProcedure(c);              
               
               session.delete(bc);
               session.save(bc);
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
           result = session.createQuery("from BreastSurgicalPathologySpecimen").list();
           
           tx.commit();
           HibernateUtil.closeSession();
           
           for (int i = 0; i<result.size(); i++) {
               BreastSurgicalPathologySpecimen obj = (BreastSurgicalPathologySpecimen) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println("getOtherSurgicalProcedure is " + obj.getOtherSurgicalProcedure());
               
               System.out.println("Sps : ");
               List sites = (List)obj.getSurgicalProcedure();
               Iterator itr = sites.iterator();
               while (itr.hasNext()) {
                   System.out.println(itr.next());
               }
               
           } 
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
  

  
          
  