package edu.pitt.cabig.cae.domain.breast;


import java.lang.String;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.pitt.cabig.cae.dao.util.HibernateUtil;

public class BreastSpecimenCharacteristicsTest extends TestCase {

	public BreastSpecimenCharacteristicsTest(String sTestName) {
		super(sTestName);
	}


	public void setUp() {

	}

	public void tearDown() {
	}


	public static Test suite() {
		return new TestSuite(BreastSpecimenCharacteristicsTest.class);
	}

	public void testInsert() throws Exception {

		BreastSpecimenCharacteristics obj = new BreastSpecimenCharacteristics();
		obj.setLaterality("laterality");
		obj.setLateralityMVR("laterality MVR");
		obj.setLymphNodeSamplingProcedure("lymphNodeSamplingProcedure");
		create(obj);
	}

	public void testSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from BreastSpecimenCharacteristics").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i<result.size(); i++) {
			BreastSpecimenCharacteristics obj = (BreastSpecimenCharacteristics) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("LateralityMVR is " + obj.getLateralityMVR());
			System.out.println("Laterality is " + obj.getLaterality());
			System.out.println("LymphNodeSamplingProcedure is " + obj.getLymphNodeSamplingProcedure());
		}
	}
	public static void create(Object obj) {
		Transaction tx = null;
		Session session = HibernateUtil.currentSession();
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			assertTrue(false);
		}
	}
}




