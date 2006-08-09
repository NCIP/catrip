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

public class OtherBreastCancerHistopathologicGradeTest extends TestCase {
  private static Log log = LogFactory.getLog(OtherBreastCancerHistopathologicGradeTest.class);

  public OtherBreastCancerHistopathologicGradeTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(OtherBreastCancerHistopathologicGradeTest.class);
   }



   public void testSelect() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           List result = new ArrayList();
           result = session.createQuery("from OtherBreastCancerHistopathologicGrade").list();

           tx.commit();
           HibernateUtil.closeSession();

               for (int i = 0; i<result.size(); i++) {
                   OtherBreastCancerHistopathologicGrade obj = (OtherBreastCancerHistopathologicGrade) result.get(i);
                   System.out.println(obj.getId());
                   System.out.println(obj.getSystemName());
               }
   }
}




