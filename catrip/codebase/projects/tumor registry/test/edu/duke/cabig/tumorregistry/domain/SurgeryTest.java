package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;

public class SurgeryTest extends TestCase {
	public SurgeryTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(SurgeryTest.class);
	}
	public void testRegionalDistantSurgeryInsert() throws Exception {
		RegionalDistantSurgery regionalSurgery = new RegionalDistantSurgery();
		regionalSurgery.setId(getNextId());
		regionalSurgery.setAtLocalFacility(Boolean.valueOf(true));
		regionalSurgery.setCharacterization("characterization");
		regionalSurgery.setStartDate(new Date(0));
		regionalSurgery.setCourse(Integer.valueOf(2222));
		try{
			HibernateUtil.create(regionalSurgery);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testNonCancerDirectedSurgeryInsert() throws Exception {
		NonCancerDirectedSurgery obj = new NonCancerDirectedSurgery();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("Immunotherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testPrimarySiteSurgeryInsert() throws Exception {
		PrimarySiteSurgery obj = new PrimarySiteSurgery();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("OtherTherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testRegionalLymphNodeSurgeryInsert() throws Exception {
		RegionalLymphNodeSurgery obj = new RegionalLymphNodeSurgery();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("Chemotherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	public void testRegionalDistantSurgerySelect() throws Exception {
		int j=1;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from RegionalDistantSurgery").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			RegionalDistantSurgery obj = (RegionalDistantSurgery) result.get(i);
			System.out.println("\n*********** RegionalDistantSurgery " + (j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}

	}
	
	public void testNonCancerDirectedSurgerySelect() throws Exception {
		int j = 1;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from NonCancerDirectedSurgery").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			NonCancerDirectedSurgery obj = (NonCancerDirectedSurgery) result.get(i);
			System.out.println("\n*********** NonCancerDirectedSurgery " + (j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}

	}

	public void testPrimarySiteSurgerySelect() throws Exception {
		int j=1;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from PrimarySiteSurgery").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			PrimarySiteSurgery obj = (PrimarySiteSurgery) result.get(i);
			System.out.println("\n*********** PrimarySiteSurgery " + (j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}

	}

	public void testRegionalLymphNodeSurgerySelect() throws Exception {
		int j = 1;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from RegionalLymphNodeSurgery").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			RegionalLymphNodeSurgery obj = (RegionalLymphNodeSurgery) result.get(i);
			System.out.println("\n*********** RegionalLymphNodeSurgery " + (j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}

	}

	public Long getNextId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from Activity where id = (select max(id) from Activity)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			Activity obj = (Activity) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}

} // end of class

