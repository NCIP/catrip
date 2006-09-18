package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;

;

public class AddressTest extends TestCase {

	public AddressTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(AddressTest.class);
	}

	public void testInsert() throws Exception {
		Address address = new Address();
		address.setId(getNextId());
		address.setAddress1("123 Main Street");
		address.setAddress2("po box 100");
		address.setCity("Atlanta");
		address.setCountry("USA");
		address.setCounty("dekalb");
		address.setZipcode("23233");
		try{
			HibernateUtil.create(address);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from Address").list();

		tx.commit();
		HibernateUtil.closeSession();
		int j = 1;
		for (int i = 0; i < result.size(); i++) {
			Address obj = (Address) result.get(i);
			System.out.println("\n******* Address " + (j++) + " *******");
			System.out.println("ID is " + obj.getId());
			System.out.println("Address 1 : " + obj.getAddress1());
			System.out.println("Address 2 : " + obj.getAddress2());
			System.out.println("City : " + obj.getCity());
			System.out.println("Country : " + obj.getCountry());
			System.out.println("State : " + obj.getState());
			System.out.println("Zipcode : " + obj.getZipcode());
		}

	}

	public Long getNextId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from Address where id = (select max(id) from Address)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			Address obj = (Address) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}


} // end of class

