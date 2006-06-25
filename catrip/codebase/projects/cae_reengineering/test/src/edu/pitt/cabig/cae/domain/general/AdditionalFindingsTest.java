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

public class AdditionalFindingsTest extends TestCase {
  private static Log log = LogFactory.getLog(AdditionalFindingsTest.class);

  public AdditionalFindingsTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(AdditionalFindingsTest.class);
   }


   public void testInsert() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           AdditionalFindings obj = null;

           for (int i=9;i<=10;i++)  {
               obj = new AdditionalFindings();
               obj.setId(new Long(i));
               obj.setOtherFindings("of123");

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
           result = session.createQuery("from AdditionalFindings").list();

           tx.commit();
           HibernateUtil.closeSession();

           for (int i = 0; i<result.size(); i++) {
               AdditionalFindings obj = (AdditionalFindings) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println("getOtherFindings is " + obj.getOtherFindings());
           }

   }
}




