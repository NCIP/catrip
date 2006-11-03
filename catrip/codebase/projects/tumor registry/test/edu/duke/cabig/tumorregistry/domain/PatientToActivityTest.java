package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;
import edu.duke.cabig.tumorregistry.domain.Diagnosis;

public class PatientToActivityTest extends TestCase {

	private static long otherId = 100;

	public PatientToActivityTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(PatientToActivityTest.class);
	}

	public void testInsert() throws Exception {
		
		// ******************
		Patient patient = new Patient();
		Long patientId = getNextPatientId();
		patient.setId(patientId);
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
		patientIdentifier.setId(patientId);
		// patientIdentifier.setPatient(patient);
		patient.setPatientIdentifier(patientIdentifier);

		// ***************************
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(getNextDiagnosisId());
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("brain tumor");
		diagnosis.setClassOfCaseCode(Integer.valueOf(2));
		// add an address
		address = new Address();
		address.setId(new Long(getNextAddressId().intValue()+1));
		address.setAddress1("123 Main Street");
		address.setAddress2("po box 100");
		address.setCity("Atlanta");
		address.setCountry("USA");
		address.setCounty("dekalb");
		address.setZipcode("23233");
		diagnosis.setAddress(address);

		// CollaborativeStaging
		CollaborativeStaging staging = new CollaborativeStaging();
		staging.setId(getNextCollaborativeStagingId());
		staging.setDerivedAJCCStage("derivedAJCCStage");
		staging.setDerivedNodeStage("derivedNodeStage");
		staging.setSiteSpecificFactor3("siteSpecificFactor3");
		staging.setDerivedSEERSummary1977("derivedSEERSummary1977");
		staging.setDerivedSEERSummary2000("derivedSEERSummary2000");
		staging.setExtension("extension");
		diagnosis.setCollaborativeStaging(staging);

		// first course treatment
		FirstCourseTreatmentSummary firstCourse = new FirstCourseTreatmentSummary();
		firstCourse.setFirstSurgeryDate(new Date(0));
		firstCourse.setFirstSystemicTreatmentDate(new Date(0));
		firstCourse.setFirstTreatmentDate(new Date(0));
		firstCourse.setId(getNextFirstCourseTreatmentId());
		firstCourse.setMostDefinitiveSurgeryDate(new Date(0));
		diagnosis.setFirstTreatment(firstCourse);

		// activity summary
		ActivitySummary activitySummary = new ActivitySummary();
		activitySummary.setId(getNextActivitySummaryId());
		activitySummary.setLocalDate(new Date(0));
		activitySummary.setSummaryCharacterization("summaryxxCharacterization");
		activitySummary.setSummaryDate(new Date(0));
		diagnosis.setActivitySummary(activitySummary);

		// activities
		Set<HormoneTherapy> activityCollection = new HashSet<HormoneTherapy>();
		for (int i = 10; i < 13; i++) {
			HormoneTherapy h = new HormoneTherapy();
			h.setId(Long.valueOf((i)));
			h.setAtLocalFacility(Boolean.valueOf(true));
			activityCollection.add(h);
		}
		diagnosis.setActivityCollection(activityCollection);
		// disease extent
		Set<DiseaseExtent> diseaseExtentCollection = new HashSet<DiseaseExtent>();
		DiseaseExtent diseaseExtent = new DiseaseExtent();
		diseaseExtent.setId(getNextDiseaseExtentId());
		diseaseExtent.setBestAJCCStage("bestAJCCStage");
		diseaseExtent.setBestSEERSummaryStage("bestSEERsummaryStage");
		diseaseExtent.setClinicalAJCCStage("clinicalAJCCStage");
		diseaseExtent
				.setClinicalAJCCStageDescriptor("clinicalAJCCStageDescriptor");
		diseaseExtent.setClinicalMetStage("clinicalMetStage");
		diseaseExtent.setClinicalNodeStage("clinicalNodeStage");
		diseaseExtentCollection.add(diseaseExtent);

		// distant Site
		Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
		for (int i = 0; i < 3; i++) {
			DistantSite distantSite = new DistantSite();
			distantSite.setId(Long.valueOf((otherId++)));
			distantSite.setName("name");
			distantSiteCollection.add(distantSite);
		}
		diseaseExtent.setDistantSiteCollection(distantSiteCollection);

		// add followup(s)
		Set<Followup> followupCollection = new HashSet<Followup>();
		for (int i = 0; i < 5; i++) {
			Followup followup = new Followup();
			followup.setId(Long.valueOf((otherId)));
			followup.setContactMethod("contactMethod");
			followup.setCancerStatus("cancerStatus");
			followup.setDate(new java.sql.Date(0));
			// add recurrence

			Recurrence recurrence = new Recurrence();
			recurrence.setId(Long.valueOf((otherId++)));
			recurrence.setType("type");
			recurrence.setDate(new java.sql.Date(0));
			followup.setRecurrence(recurrence);

			distantSiteCollection = new HashSet<DistantSite>();
			for (int d = 0; d < 2; d++) {
				DistantSite distantSite = new DistantSite();
				System.out.println("other id = " + otherId);
				distantSite.setId(Long.valueOf((otherId++)));
				distantSite.setName("name" + d);
				distantSiteCollection.add(distantSite);
				System.out.println(distantSite.hashCode());

			}
			recurrence.setDistantSiteCollection(distantSiteCollection);

			followupCollection.add(followup);
		}
		diagnosis.setFollowUpCollection(followupCollection);
		diagnosis.setDiseaseExtentCollection(diseaseExtentCollection);
		Set<Diagnosis> diagnosisCollection = new HashSet<Diagnosis>();

		diagnosisCollection.add(diagnosis);
		patient.setDiagnosisCollection(diagnosisCollection);
		try{
			HibernateUtil.create(patient);
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
		result = session.createQuery("from Patient").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i < result.size(); i++) {
			Patient obj = (Patient) result.get(i);
			System.out.println("******* Patient *******");
			System.out.println("ID is " + obj.getId());
			System.out.println("first name is " + obj.getFirstName());
			System.out.println("last name is " + obj.getLastName());
			PatientIdentifier patientId = obj.getPatientIdentifier();
			if (patientId != null) {
				System.out.println("******* Patient Identifier *******");
				System.out.println("ID : " + patientId.getId());
				System.out.println("MRN : "	+ patientId.getMedicalRecordNumber());
			} else {
				System.out.println("patient has no MRN");
			}
			Address address = obj.getAddress();
			if (address != null) {
				System.out.println("******* Address *******");
				System.out.println("Address ID : " + address.getId());
				System.out.println("Address 1 : " + address.getAddress1());
				System.out.println("Address 2 : " + address.getAddress2());
				System.out.println("City : " + address.getCity());
				System.out.println("Country : " + address.getCountry());
				System.out.println("State : " + address.getState());
			} else {
				System.out.println("patient has no Address");
			}
			Set diagnosisCollection = obj.getDiagnosisCollection();
			if (diagnosisCollection != null) {
				Iterator itr = diagnosisCollection.iterator();
				while (itr.hasNext()) {
					System.out.println("******* Diagnosis *******");
					Diagnosis diagnosis = (Diagnosis) itr.next();
					System.out.println("diagnosis ID : " + diagnosis.getId());
					System.out.println("Cause of Death : " +diagnosis.getCauseOfDeath());
					System.out.println("Behavior : " +diagnosis.getBehavior());
					System.out.println("ClassOfCase : " +diagnosis.getClassOfCase());
					System.out.println("HistologicGrade : " +diagnosis.getHistologicGrade());
					System.out.println("PrimarySite : " +diagnosis.getPrimarySite());
					Set activityCollection = diagnosis.getActivityCollection();
					Address address2 = diagnosis.getAddress();
					if (address2 != null) {
						System.out.println("******* Diagnosis Address *******");
						System.out.println("Address ID : " + address2.getId());
						System.out.println("Address 1 : " + address2.getAddress1());
						System.out.println("Address 2 : " + address2.getAddress2());
						System.out.println("City : " + address2.getCity());
						System.out.println("Country : " + address2.getCountry());
						System.out.println("State : " + address2.getState());
					} else {
						System.out.println("Diagnosis has no Address");
					}
					if (activityCollection != null) {
						System.out.println("******* Activity *******");
						 itr = activityCollection.iterator();
						while (itr.hasNext()) {
							Activity activity = (Activity) itr.next();
							System.out.println("activity ID : " + activity.getId());
							System.out.println("class name : " +activity.getClass().getName());
						}
					} else {
						System.out.println("patient has no activities");
					}
					CollaborativeStaging collaborativeStaging = diagnosis.getCollaborativeStaging();
					System.out.println("*******  CollaborativeStaging  *******");
					if (collaborativeStaging != null){
						System.out.println("collaborativeStaging ID : " + collaborativeStaging.getId());
						System.out.println("DerivedAJCCStage : " + collaborativeStaging.getDerivedAJCCStage());
						
					}
					Set diseaseExtentCollection = diagnosis.getDiseaseExtentCollection();
					itr = diseaseExtentCollection.iterator();
					if (diseaseExtentCollection != null){
						System.out.println("*******  Disease Extent  *******");
						while (itr.hasNext()) {
							DiseaseExtent diseaseExtent = (DiseaseExtent) itr.next();
							System.out.println(" ID : " + diseaseExtent.getId());
							System.out.println("bestAJCCStage : " + diseaseExtent.getBestAJCCStage());
							System.out.println("BestSEERSummaryStage : " + diseaseExtent.getBestSEERSummaryStage());
							System.out.println("ClinicalMetStage : " + diseaseExtent.getClinicalMetStage());
							Set distantSiteCollection = diseaseExtent.getDistantSiteCollection();
							itr = distantSiteCollection.iterator();
							if (distantSiteCollection != null){
								System.out.println("*******  Distant Site  *******");
								while (itr.hasNext()) {
									DistantSite distantSite = (DistantSite) itr.next();
									System.out.println(" ID : " + distantSite.getId());
									System.out.println(" Name : " + distantSite.getName());
								}
							}
							
						}
					}
					Set followupCollection = diagnosis.getFollowUpCollection();
					itr = followupCollection.iterator();
					if (followupCollection != null){
						System.out.println("*******  Followup  *******");
						while (itr.hasNext()) {
							Followup followup = (Followup) itr.next();
							System.out.println(" ID : " + followup.getId());
							System.out.println("CancerStatus : " + followup.getCancerStatus());
							System.out.println("ContactMethod : " + followup.getContactMethod());
							System.out.println("date : " + followup.getDate());
							Recurrence recurrence = followup.getRecurrence();
							if (recurrence != null){
								System.out.println("*******  Recurrence  *******");
								System.out.println(" ID : " + recurrence.getId());
								System.out.println(" Type : " + recurrence.getType());
								System.out.println(" Date : " + recurrence.getDate());
								Set distantSiteCollection = recurrence.getDistantSiteCollection();
								itr = distantSiteCollection.iterator();
								if (distantSiteCollection != null){
									System.out.println("*******  Distant Site  *******");
									while (itr.hasNext()) {
										DistantSite distantSite = (DistantSite) itr.next();
										System.out.println(" ID : " + distantSite.getId());
										System.out.println(" Name : " + distantSite.getName());
									}
								}
								
							}
						}
					}
					FirstCourseTreatmentSummary firstCourseTreatmentSummary = diagnosis.getFirstTreatment();
					if (firstCourseTreatmentSummary != null){
						System.out.println("*******  FirstCourseTreatmentSummary  *******");
						System.out.println(" ID : " + firstCourseTreatmentSummary.getId());
						System.out.println(" FirstSurgeryDate : " + firstCourseTreatmentSummary.getFirstSurgeryDate());
						System.out.println(" FirstSystemicTreatmentDate : " + firstCourseTreatmentSummary.getFirstSystemicTreatmentDate());
						System.out.println(" FirstTreatmentDate : " + firstCourseTreatmentSummary.getFirstTreatmentDate());
						System.out.println(" MostDefinitiveSurgeryDate : " + firstCourseTreatmentSummary.getMostDefinitiveSurgeryDate());
					}
					ActivitySummary activitySummary = diagnosis.getActivitySummary();
					if (activitySummary != null){
						System.out.println("*******  Activity Summary  *******");
						System.out.println(" ID : " + activitySummary.getId());
						System.out.println("LocalCharacterization : " + activitySummary.getLocalCharacterization());
						System.out.println("SummaryCharacterization : " + activitySummary.getSummaryCharacterization());
						System.out.println("LocalDate : " + activitySummary.getLocalDate());
						System.out.println("Summarydate : " + activitySummary.getSummaryDate());
					}
				}
			} else {
				System.out.println("patient has no diagnosises");
			}
			//delete(obj);
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
	public Long getNextDiagnosisId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from Diagnosis where id = (select max(id) from Diagnosis)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			Diagnosis obj = (Diagnosis) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	public Long getNextCollaborativeStagingId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from CollaborativeStaging where id = (select max(id) from CollaborativeStaging)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			CollaborativeStaging obj = (CollaborativeStaging) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	public Long getNextFirstCourseTreatmentId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from FirstCourseTreatmentSummary where id = (select max(id) from FirstCourseTreatmentSummary)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			FirstCourseTreatmentSummary obj = (FirstCourseTreatmentSummary) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	public Long getNextActivitySummaryId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from ActivitySummary where id = (select max(id) from ActivitySummary)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			ActivitySummary obj = (ActivitySummary)result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	public Long getNextDiseaseExtentId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from DiseaseExtent where id = (select max(id) from DiseaseExtent)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			DiseaseExtent obj = (DiseaseExtent)result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}

} // end of class

