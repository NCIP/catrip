package edu.pitt.cabig.cae.domain.general;


import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.pitt.cabig.cae.dao.util.HibernateUtil;

public class ThreeDimensionalSizeTest extends TestCase {

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

   public void testSelect() throws Exception {

	   Session session = HibernateUtil.currentSession();
	   Transaction tx = session.beginTransaction();

	   List result = new ArrayList();
	   result = session.createQuery("from ThreeDimensionalSize").list();

	   tx.commit();
	   HibernateUtil.closeSession();

	   for (int i = 0; i<result.size(); i++) {
		   ThreeDimensionalTumorSize obj = (ThreeDimensionalTumorSize) result.get(i);
		   System.out.println("ID is " + obj.getId());
		   System.out.println("getAdditionalDimensionY is " + obj.getAdditionalDimensionY());
	   }
   }
}




