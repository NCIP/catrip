package edu.pitt.cabig.cae.domain.breast;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.pitt.cabig.cae.dao.util.HibernateUtil;

public class BreastPositiveSurgicalMarginTest extends TestCase {
  private static Log log = LogFactory.getLog(BreastPositiveSurgicalMarginTest.class);

  public BreastPositiveSurgicalMarginTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(BreastPositiveSurgicalMarginTest.class);
   }




   public void testSelect() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           List result = new ArrayList();
           result = session.createQuery("from BreastPositiveSurgicalMargin").list();

           tx.commit();
           HibernateUtil.closeSession();

               for (int i = 0; i<result.size(); i++) {
                   BreastPositiveSurgicalMargin obj = (BreastPositiveSurgicalMargin) result.get(i);
                   System.out.println(obj.getId());
                   System.out.println(obj.getMVR());
               }

   }
}



