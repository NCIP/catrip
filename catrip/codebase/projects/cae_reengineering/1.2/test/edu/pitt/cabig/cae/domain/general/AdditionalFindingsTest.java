package edu.pitt.cabig.cae.domain.general;


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

public class AdditionalFindingsTest extends TestCase {

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
		AdditionalFindings obj = new AdditionalFindings();
		obj.setOtherFindings("OtherFindings");
		obj.setMvr("mvr");

		create(obj);
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
			System.out.println("mvr is " + obj.getMvr());
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




