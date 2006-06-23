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

public class InvasiveBreastCarcinomaNeoplasmHistologicTypeTest extends TestCase {
  private static Log log = LogFactory.getLog(InvasiveBreastCarcinomaNeoplasmHistologicTypeTest.class); 
  
  public InvasiveBreastCarcinomaNeoplasmHistologicTypeTest(String sTestName) {
      super(sTestName);
  }

  
   public void setUp() {

   }

   public void tearDown() {
   }
  
   
   public static Test suite() {
       return new TestSuite(InvasiveBreastCarcinomaNeoplasmHistologicTypeTest.class);
   }


   public void testInsert(){
       try {
           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();
           
           InvasiveBreastCarcinomaNeoplasmHistologicType obj = null; 
           
           for (int i=19;i<=20;i++)  {
               obj = new InvasiveBreastCarcinomaNeoplasmHistologicType();
               obj.setId(new Long(i));
               obj.setName("name ");
               obj.setNameMVR("n mvr");
               
               
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
           result = session.createQuery("from InvasiveBreastCarcinomaNeoplasmHistologicType").list();
           
           tx.commit();
           HibernateUtil.closeSession();
           
           for (int i = 0; i<result.size(); i++) {
               InvasiveBreastCarcinomaNeoplasmHistologicType obj = (InvasiveBreastCarcinomaNeoplasmHistologicType) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println("name is " + obj.getName());
               
               
               
           } 
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
  

  
          
  