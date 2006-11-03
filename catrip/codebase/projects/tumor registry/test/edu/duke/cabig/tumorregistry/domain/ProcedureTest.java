package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;

public class ProcedureTest extends TestCase {
	public ProcedureTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(ProcedureTest.class);
	}

	public void testHemTransplantEndocrineInsert() throws Exception {
		HemTransplantEndocrineProcedure endocrine = new HemTransplantEndocrineProcedure();
		endocrine.setId(getNextId());
		endocrine.setAtLocalFacility(Boolean.valueOf(true));
		endocrine.setCharacterization("characterization");
		endocrine.setStartDate(new Date(0));
		endocrine.setCourse(Integer.valueOf(2222));
		try{
			HibernateUtil.create(endocrine);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testOtherProcedureInsert() throws Exception {
		OtherProcedure obj = new OtherProcedure();
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

	public void testRadiationInsert() throws Exception {
		Radiation obj = new Radiation();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("radiation characterization");
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

	public void testFirstCourseRadiationInsert() throws Exception {
		FirstCourseRadiation obj = new FirstCourseRadiation();
		obj.setId(getNextId());
		obj.setAtLocalFacility(Boolean.valueOf(true));
		obj.setCharacterization("Chemotherapy characterization");
		obj.setStartDate(new Date(0));
		obj.setCourse(Integer.valueOf(2222));
		obj.setStopDate(new Date(0));
		obj.setNumberOfTreatments("25");
		obj.setBoostDose(Integer.valueOf(4));
		obj.setRegionalDose(Integer.valueOf(3));
		obj.setBoostModality("5");
		obj.setLocation("location");
		obj.setRegionalModality("regionalModality");
		obj.setVolume("volume");
		try{
			HibernateUtil.create(obj);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testHemTransplantEndocrineSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from HemTransplantEndocrineProcedure").list();

		tx.commit();
		HibernateUtil.closeSession();
		System.out.println("*********** HemTransplantEndocrine ***********");
		for (int i = 0; i < result.size(); i++) {
			HemTransplantEndocrineProcedure obj = (HemTransplantEndocrineProcedure) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}
	}

	public void testOtherProcedureSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from OtherProcedure").list();

		tx.commit();
		HibernateUtil.closeSession();
		System.out.println("*********** OtherProcedure ***********");
		for (int i = 0; i < result.size(); i++) {
			OtherProcedure obj = (OtherProcedure) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}
	}

	public void testRadiationSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from Radiation").list();

		tx.commit();
		HibernateUtil.closeSession();
		System.out.println("*********** Radiation ***********");
		for (int i = 0; i < result.size(); i++) {
			Radiation obj = (Radiation) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
		}
	}

	public void testFirstCourseRadiationSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from FirstCourseRadiation").list();

		tx.commit();
		HibernateUtil.closeSession();
		System.out.println("*********** Radiation ***********");
		for (int i = 0; i < result.size(); i++) {
			FirstCourseRadiation obj = (FirstCourseRadiation) result.get(i);
			System.out.println("ID is " + obj.getId());
			System.out.println("AtLocalFacility is " + obj.getAtLocalFacility());
			System.out.println("Characterization is " + obj.getCharacterization());
			System.out.println("start date is " + obj.getStartDate());
			System.out.println("course is " + obj.getCourse());
			System.out.println("Stop Date is " + obj.getStopDate());
			System.out.println("Number of Treatements is " + obj.getNumberOfTreatments());
			System.out.println("Boost Dose is " + obj.getBoostDose());
			System.out.println("Boost Modality is " + obj.getBoostModality());
			System.out.println("Location is " + obj.getLocation());
			System.out.println("Regioinal Dose is " + obj.getRegionalDose());
			System.out.println("Regional Modality is " + obj.getRegionalModality());
			System.out.println("Volume is " + obj.getVolume());		
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

