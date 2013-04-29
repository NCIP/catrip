/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.pitt.cabig.cae.domain.breast;

import java.lang.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.pitt.cabig.cae.dao.util.HibernateUtil;

public class BreastCancerAccessionCharacteristicsTest extends TestCase {

	public BreastCancerAccessionCharacteristicsTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(BreastCancerAccessionCharacteristicsTest.class);
	}

	@SuppressWarnings("unchecked")
	public void testInsert() throws Exception {

		BreastCancerAccessionCharacteristics obj = new BreastCancerAccessionCharacteristics();
		//obj.setId(Long.valueOf(72));
		obj.setOtherSurgicalProcedure("otherSurgicalProcedure");
		
		Set <String> surgicalSet = new HashSet();
		surgicalSet.add("breast surgery");
		obj.setSurgicalProcedure(surgicalSet);
		create(obj);
	}

	public void testSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from AccessionCharacteristics").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i < result.size(); i++) {
			BreastCancerAccessionCharacteristics obj = (BreastCancerAccessionCharacteristics) result.get(i);
			System.out.println(obj.getId());
			System.out.println(obj.getOtherSurgicalProcedure());
			Set sp = obj.getSurgicalProcedure();
			Iterator iter = sp.iterator();
			while (iter.hasNext()) {
				String procedure = (String) iter.next();
				System.out.println(procedure);
			}
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
