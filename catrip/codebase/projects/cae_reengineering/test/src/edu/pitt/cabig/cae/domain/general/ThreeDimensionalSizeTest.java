package edu.pitt.cabig.cae.domain.general;

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

public class ThreeDimensionalSizeTest extends TestCase {
  private static Log log = LogFactory.getLog(ThreeDimensionalSizeTest.class);

  public ThreeDimensionalSizeTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(ThreeDimensionalSizeTest.class);
   }


   public void testInsert() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           ThreeDimensionalSize obj = null;

           for (int i=7;i<=8;i++)  {
               obj = new ThreeDimensionalSize();
               obj.setId(new Long(i));
               obj.setAdditionalDimensionY(123);
               obj.setAdditionalDimensionZ(456);
               obj.setMVR("1 mvr");

               //session.delete(obj);
               session.saveOrUpdate(obj);
           }



           tx.commit();
           //dbCleanup(session);
           HibernateUtil.closeSession();
       System.out.println("Inserted/Updated successfully ");

   }

   public void testSelect() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           List result = new ArrayList();
           result = session.createQuery("from ThreeDimensionalSize").list();

           tx.commit();
           HibernateUtil.closeSession();

           for (int i = 0; i<result.size(); i++) {
               ThreeDimensionalSize obj = (ThreeDimensionalSize) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println("getAdditionalDimensionY is " + obj.getAdditionalDimensionY());



           }

   }
}




