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

public class BreastCancerBiomarkersTest extends TestCase {

	public BreastCancerBiomarkersTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(BreastCancerBiomarkersTest.class);
	}

	public void testInsert() throws Exception {
		BreastCancerBiomarkers obj = new BreastCancerBiomarkers();
		obj.setEstrogenReceptor("EstrogenReceptor");
		obj.setHER2Status("HER2Status");
		obj.setHER2TestType("HER2TestType");
		obj.setProgesteroneReceptor("ProgesteroneReceptor");

		create(obj);
	}

	public void testSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from BreastCancerBiomarkers").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i<result.size(); i++) {
			BreastCancerBiomarkers obj = (BreastCancerBiomarkers) result.get(i);
			System.out.println("id : " + obj.getId());
			System.out.println("EstrogenReceptor : " + obj.getEstrogenReceptor());
			System.out.println("HER2Status : " + obj.getHER2Status());
			System.out.println("HER2TestType : " + obj.getHER2TestType());
			System.out.println("ProgesteroneReceptor : " + obj.getProgesteroneReceptor());
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




