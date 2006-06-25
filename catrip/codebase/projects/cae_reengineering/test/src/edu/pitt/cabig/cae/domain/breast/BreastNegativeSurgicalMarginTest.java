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

public class BreastNegativeSurgicalMarginTest extends TestCase {
  private static Log log = LogFactory.getLog(BreastNegativeSurgicalMarginTest.class);

  public BreastNegativeSurgicalMarginTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(BreastNegativeSurgicalMarginTest.class);
   }


   public void testInsert() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           BreastNegativeSurgicalMargin obj = null;

           for (int i=3;i<=4;i++)  {
               obj = new BreastNegativeSurgicalMargin();

               obj.setId(new Long(i));
               obj.setMVR("mvr");
               obj.setSpatialRelationshipToPatient("rel");
               obj.setClosestNeoplasmPresent("clo");

              // session.delete(obj);
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
           result = session.createQuery("from BreastNegativeSurgicalMargin").list();

           tx.commit();
           HibernateUtil.closeSession();

               for (int i = 0; i<result.size(); i++) {
                   BreastNegativeSurgicalMargin obj = (BreastNegativeSurgicalMargin) result.get(i);
                   System.out.println(obj.getId());
                   System.out.println(obj.getMVR());
               }

   }
}




