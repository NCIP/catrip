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

public class InvasiveBreastCarcinomaTest extends TestCase {
  private static Log log = LogFactory.getLog(InvasiveBreastCarcinomaTest.class);

  public InvasiveBreastCarcinomaTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(InvasiveBreastCarcinomaTest.class);
   }


  

   public void testSelect() throws Exception {

           Session session = HibernateUtil.currentSession();
           Transaction tx = session.beginTransaction();

           List result = new ArrayList();
           result = session.createQuery("from InvasiveBreastCarcinoma").list();

           tx.commit();
           HibernateUtil.closeSession();

           for (int i = 0; i<result.size(); i++) {
               InvasiveBreastCarcinoma obj = (InvasiveBreastCarcinoma) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println("getLocationMVR is " + obj.getLocationMVR());

               System.out.println("loc s : ");
               List sites = (List)obj.getLocation();
               Iterator itr = sites.iterator();
               while (itr.hasNext()) {
                   System.out.println(itr.next());
               }

               System.out.println("m loc s : ");
               List sites1 = (List)obj.getMicrocalcificationLocation();
               Iterator itr1 = sites1.iterator();
               while (itr1.hasNext()) {
                   System.out.println(itr1.next());
               }
           }

   }
}




