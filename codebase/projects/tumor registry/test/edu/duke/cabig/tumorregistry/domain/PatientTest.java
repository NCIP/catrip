/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

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
	public void testAddressToPatientAssociation(){
		try{
			Long patientId = getNextPatientId();
			Address address = new Address();
			address.setId(getNextAddressId());
			address.setAddress1("123 Main Street");
			address.setAddress2("po box 100");
			address.setCity("Atlanta");
			address.setCountry("USA");

			Patient patient = new Patient();
			patient.setId(patientId);
			patient.setDateOfBirth(new Date(0));
			patient.setDateOfDeath(new Date(0));
			// inserts both
			patient.setAddress(address);
			HibernateUtil.create(patient);

			// inserts both
			//address.setPatient(patient);
			//HibernateUtil.create(address);
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}
	public void testReadDiagnosisToAddress(){
		
	}
	public void testDiagnosisToAddress(){
		try{
			//Long patientId = getNextPatientId();
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setId(Long.valueOf(102));
			diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
			diagnosis.setCauseOfDeath("infection");
			Address address = new Address();
			address.setId(getNextAddressId());
			address.setAddress1("123 Main Street");
			address.setAddress2("po box 100");
			address.setCity("Atlanta");
			address.setCountry("USA");
			// inserts both
			//diagnosis.setAddress(address);
			//HibernateUtil.create(diagnosis);
			// inserts address only
			address.setDiagnosis(diagnosis);
			HibernateUtil.create(address);
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}
	public void testDiagnosisToPatientAssociation(){
		try{
		Long patientId = getNextPatientId();
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(patientId);
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("infection");
		Patient patient = new Patient();
		patient.setId(patientId);
		patient.setDateOfBirth(new Date(0));
		patient.setDateOfDeath(new Date(0));
		Set<Diagnosis> diagnosisCollection = new HashSet<Diagnosis>();
		diagnosisCollection.add(diagnosis);
		patient.setDiagnosisCollection(diagnosisCollection);
		HibernateUtil.create(patient);

		
		//diagnosis.setPatient(patient);
		//	HibernateUtil.create(diagnosis);
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}

	public void testDiagnosisToDiseaseExtent(){
		try{
		Long patientId = getNextPatientId();
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(patientId);
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("infection");
		
		DiseaseExtent diseaseExtent = new DiseaseExtent();
		diseaseExtent.setId(patientId);
		//patient.setDateOfBirth(new Date(0));
		//patient.setDateOfDeath(new Date(0));
		Set<DiseaseExtent> diseaseExtentCollection = new HashSet<DiseaseExtent>();
		diseaseExtentCollection.add(diseaseExtent);
		diagnosis.setDiseaseExtentCollection(diseaseExtentCollection);
		HibernateUtil.create(diagnosis);

		
		//diseaseExtent.setDiagnosis(diagnosis);
		//	HibernateUtil.create(diseaseExtent);
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}

	public void testPatientIdentifierToPatientAssociation(){
		try{
			Long patientId = getNextPatientId();
			PatientIdentifier patientIdentifier = new PatientIdentifier();
			patientIdentifier.setId(patientId);
			patientIdentifier.setMedicalRecordNumber("200");

			Patient patient = new Patient();
			patient.setId(patientId);
			patient.setDateOfBirth(new Date(0));
			patient.setDateOfDeath(new Date(0));
			patientIdentifier.setPatient(patient);
//			 inserts patient and updates patientIdentifer
			//patient.setPatientIdentifier(patientIdentifier);
			//HibernateUtil.create(patient);
			
			//inserts both patient and patientIdentifier - uses the patient ID for both tables
			patientIdentifier.setPatient(patient);
			HibernateUtil.create(patientIdentifier); 
		} 
		catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}		
	}

	public void testInsert() throws Exception {
		Patient patient = new Patient();
		patient.setId(getNextPatientId());
		patient.setDateOfBirth(new Date(0));
		patient.setDateOfDeath(new Date(0));
		patient.setAutopsy("0"); //0-2,9
		patient.setEthnicGroup("Hispanic");
		patient.setRace("0");
		patient.setSex("Male"); //1-4,9
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
			diagnosis.setCauseOfDeath("infection");
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
				for (int k = 0; k <2; k++) {
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
//		try{
//			HibernateUtil.create(patient);
//		} 
//		catch (HibernateException e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
	}

	public void testSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("FROM Patient a, Diagnosis b, Activity c WHERE a.getId() = b.PATIENT_ID AND b.getId() = c.DIAGNOSIS_ID AND c.getPerformedIndicator() IS NOT NULL").list();

		tx.commit();
System.out.println("size = " + result.size());
//		for (int i = 0; i < result.size(); i++) {
//			Patient obj = (Patient) result.get(i);
//			System.out.println("ID is " + obj.getId());
//			PatientIdentifier patientId = obj.getPatientIdentifier();
//			if (patientId != null) {
//				System.out.println("patitent Identifier ID : "
//						+ patientId.getId());
//				System.out.println("MRN : "
//						+ patientId.getMedicalRecordNumber());
//			} else {
//				System.out.println("patient has no MRN");
//			}
//			Address address = obj.getAddress();
//			if (address != null) {
//				System.out.println("Address ID : " + address.getId());
//				System.out.println("Address 1 : " + address.getAddress1());
//			} else {
//				System.out.println("patient has no Address");
//			}
//			Set diagnosisCollection = obj.getDiagnosisCollection();
//			if (diagnosisCollection != null) {
//				Iterator itr = diagnosisCollection.iterator();
//				while (itr.hasNext()) {
//					Diagnosis diagnosis = (Diagnosis) itr.next();
//					System.out.println("diagnosis ID : " + diagnosis.getId());
//					// System.out.println("Address 1 : " +
//				}
//			} else {
//				System.out.println("patient has no diagnosises");
//			}
//		}
		HibernateUtil.closeSession();

	}
	public void testSelectRecurrenceToFollowup() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from Recurrence").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i < result.size(); i++) {
			Recurrence obj = (Recurrence) result.get(i);
			System.out.println("id = " + obj.getId());
			Followup d = obj.getFollowup();
			//Diagnosis d = obj.getDiagnosis();
			System.out.println(d.getId());
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

