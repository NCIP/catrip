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

public class SubstanceAdministrationTest extends TestCase {
	public SubstanceAdministrationTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(SubstanceAdministrationTest.class);
	}
	public void testHormoneInsert() throws Exception {
		HormoneTherapy hormone = new HormoneTherapy();
		hormone.setId(getNextId());
		hormone.setAtLocalFacility(Boolean.valueOf(true));
		hormone.setCharacterization("characterization");
		hormone.setStartDate(new Date(0));
		hormone.setCourse(Integer.valueOf(2222));
		hormone.setStopDate(new Date(0));
		try{
			HibernateUtil.create(hormone);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testImmunotherapyInsert() throws Exception {
		Immunotherapy obj = new Immunotherapy();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("Immunotherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		obj.setStopDate(new Date(0));
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testOtherTherapyInsert() throws Exception {
		OtherTherapy obj = new OtherTherapy();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("OtherTherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		obj.setStopDate(new Date(0));
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testChemotherapyInsert() throws Exception {
		Chemotherapy obj = new Chemotherapy();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("Chemotherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		obj.setStopDate(new Date(0));
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	public void testHormoneSelect() throws Exception {
		int j = 1;

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from HormoneTherapy").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			HormoneTherapy obj = (HormoneTherapy) result.get(i);
			System.out.println("*********** Hormone "+(j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
			System.out.println("Stop Date is " + obj.getStopDate());
		}

	}
	
	public void testImmunotherapySelect() throws Exception {
		int j = 1;

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from Immunotherapy").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			Immunotherapy obj = (Immunotherapy) result.get(i);
			System.out.println("*********** Immunotherapy "+(j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
			System.out.println("Stop Date is " + obj.getStopDate());
		}

	}

	public void testOtherTherapySelect() throws Exception {
		int j = 1;

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from OtherTherapy").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			System.out.println("*********** OtherTherapy "+(j++) + " ***********");
			OtherTherapy obj = (OtherTherapy) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
			System.out.println("Stop Date is " + obj.getStopDate());
		}

	}

	public void testChemotherapySelect() throws Exception {
		int j = 1;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from Chemotherapy").list();

		tx.commit();
		HibernateUtil.closeSession();
		for (int i = 0; i < result.size(); i++) {
			Chemotherapy obj = (Chemotherapy) result.get(i);
			System.out.println("*********** Chemotherapy "+(j++) + " ***********");
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
			System.out.println("Stop Date is " + obj.getStopDate());
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

