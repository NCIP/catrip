/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.pitt.cabig.cae.domain.breast;


import java.lang.String;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.pitt.cabig.cae.dao.util.HibernateUtil;

public class BreastCancerTNMFindingTest extends TestCase {

	public BreastCancerTNMFindingTest(String sTestName) {
		super(sTestName);
	}


	public void setUp() {

	}

	public void tearDown() {
	}


	public static Test suite() {
		return new TestSuite(BreastCancerTNMFindingTest.class);
	}



	public void testSelect() throws Exception  {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from BreastCancerTNMFinding").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i<result.size(); i++) {
			BreastCancerTNMFinding obj = (BreastCancerTNMFinding) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("getDistantMetastasisFinding is " + obj.getDistantMetastasisFinding());

			System.out.println("LOCATIONS : ");
			List sites = (List)obj.getMetastasisAnatomicSite();
			Iterator itr = sites.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}

		}
	}
}




