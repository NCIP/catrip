package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;
import java.sql.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;
import edu.duke.cabig.tumorregistry.domain.Patient;
import edu.duke.cabig.tumorregistry.domain.Diagnosis;

public class PatientTest extends TestCase {
	private static long otherId = 200;
	public PatientTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(PatientTest.class);
	}
	public void testInsert() throws Exception {
		Patient patient = new Patient();
		patient.setId(getNextPatientId());
		patient.setDateOfBirth(new Date(0));
		patient.setAutopsy("autopsy");
		patient.setFirstName("Joe");
		patient.setLastName("Brown");
		// add an address
		Address address = new Address();
		address.setId(getNextAddressId());
		address.setAddress1("123 Main Street");
		address.setAddress2("po box 100");
		address.setCity("Atlanta");
		address.setCountry("USA");
		address.setCounty("dekalb");
		address.setZipcode("23233");
		patient.setAddress(address);
		
	// PatientIdentifier
	 
	  PatientIdentifier patientIdentifier = new PatientIdentifier();
		patientIdentifier.setMedicalRecordNumber("mrn300");
		patientIdentifier.setId(getNextPatientIdentifierId());
		//patientIdentifier.setPatient(patient);
		patient.setPatientIdentifier(patientIdentifier);
		
		// add a diagnosis
		Set<Diagnosis> diagnosisCollection = new HashSet<Diagnosis>();
		for (int i = 0; i <2; i++) {
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setId(Long.valueOf(i));
			diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
			diagnosis.setCauseOfDeath("strangulation");
			diagnosis.setClassOfCaseCode(Integer.valueOf(2));
			//add followup(s)
			Set<Followup> followupCollection = new HashSet<Followup>();
			for (int j = 0; j <3; j++) {
				Followup followup = new Followup();
				followup.setId(Long.valueOf(otherId++));
				followup.setContactMethod("contactMethod");
				followup.setCancerStatus("cancerStatus");
				followup.setDate(new java.sql.Date(0));
				
				followupCollection.add(followup);
			}	
				
			diagnosis.setFollowUpCollection(followupCollection);
			//add disease extent(s)
			Set<DiseaseExtent> diseaseExtentCollection = new HashSet<DiseaseExtent>();
			for (int z = 0; z <5; z++) {
				DiseaseExtent diseaseExtent = new DiseaseExtent();
				diseaseExtent.setId(Long.valueOf(otherId++));
				//add distant Site(s)
				Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
				for (int k = 0; k <23; k++) {
					DistantSite distantSite = new DistantSite();
					distantSite.setId(Long.valueOf((otherId++)));
					distantSite.setName("name");
					distantSiteCollection.add(distantSite);
				}	
				diseaseExtent.setDistantSiteCollection(distantSiteCollection);
				diseaseExtentCollection.add(diseaseExtent);
			}	
			
			
			diagnosis.setDiseaseExtentCollection(diseaseExtentCollection);
			diagnosisCollection.add(diagnosis);
			patient.setDiagnosisCollection(diagnosisCollection);
		}
		try{
			HibernateUtil.create(patient);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testSelect() throws Exception {
if (1==1) return;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from Patient").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i < result.size(); i++) {
			Patient obj = (Patient) result.get(i);
			System.out.println("ID is " + obj.getId());
			PatientIdentifier patientId = obj.getPatientIdentifier();
			if (patientId != null) {
				System.out.println("patitent Identifier ID : "
						+ patientId.getId());
				System.out.println("MRN : "
						+ patientId.getMedicalRecordNumber());
			} else {
				System.out.println("patient has no MRN");
			}
			Address address = obj.getAddress();
			if (address != null) {
				System.out.println("Address ID : " + address.getId());
				System.out.println("Address 1 : " + address.getAddress1());
			} else {
				System.out.println("patient has no Address");
			}
			Set diagnosisCollection = obj.getDiagnosisCollection();
			if (diagnosisCollection != null) {
				Iterator itr = diagnosisCollection.iterator();
				while (itr.hasNext()) {
					Diagnosis diagnosis = (Diagnosis) itr.next();
					System.out.println("diagnosis ID : " + diagnosis.getId());
					// System.out.println("Address 1 : " +
				}
			} else {
				System.out.println("patient has no diagnosises");
			}
		}

	}
	
	public Long getNextPatientId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from Patient where id = (select max(id) from Patient)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			Patient obj = (Patient) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	
	
	public Long getNextAddressId() throws Exception {
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
	public Long getNextPatientIdentifierId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from PatientIdentifier where id = (select max(id) from PatientIdentifier)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			PatientIdentifier obj = (PatientIdentifier) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}

} // end of class

