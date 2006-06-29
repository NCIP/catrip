package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;

public class TestPersonDb extends TestCase {

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public static void testInvestigator() {
		// create an Investigator
		Investigator investigator = new Investigator();
		//create person attributes
		investigator.setAdministrativeGenderCode("AdministrativeGenderCode");
		investigator.setBirthDate(new Date(0));
		investigator.setCountryCode("country code");
		investigator.setEducationLevelCode("education level");
		investigator.setEmploymentStatusCode("EmploymentStatusCode");
		investigator.setEmploymentStatusOtherText("EmploymentStatusOtherText");
		investigator.setEthnicGroupCode("ethnicGroupCode");
		investigator.setFirstName("first name");
		investigator.setHouseholdIncomeCode("household income code");
		investigator.setLastName("last name");
		investigator.setMaritalStatusCode("MaritalStatusCode");
		//investigator.setPersonOccupationCollection();
		investigator.setRaceCode("race code");
		investigator.setTelecomAddress("TelecomAddress");
	
		
		// create investigator attributes
		investigator.setNciIdentifier("nci indentifier");
		Set <StudyInvestigator> siSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			StudyInvestigator studyInvestigator = new StudyInvestigator();
			studyInvestigator.setResponsibilityRoleCode("responsibility Role Code " + i);
			studyInvestigator.setSignatureIndicator(true);
			studyInvestigator.setSignatureText("signature text");
			studyInvestigator.setStartDate(new Date(0));
			studyInvestigator.setStopDate(new Date(0));
			studyInvestigator.setStatusCode("status code");
			
			siSet.add(studyInvestigator);
		}
		investigator.setStudyInvestigatorCollection(siSet);
		Protocol p = new Protocol();
		p.setDescriptionText("test from testpersondb.java");
		p.setStudyInvestigatorCollection(siSet);
		
		create(p);
		
	}
/*
	public static void testParticipant() {
		// create an Investigator
		Participant participant = new Participant();
		//create person attributes
		participant.setAdministrativeGenderCode("Participant AdministrativeGenderCode");
		participant.setBirthDate(new Date(0));
		participant.setCountryCode("country code");
		participant.setEducationLevelCode("education level");
		participant.setEmploymentStatusCode("EmploymentStatusCode");
		participant.setEmploymentStatusOtherText("EmploymentStatusOtherText");
		participant.setEthnicGroupCode("ethnicGroupCode");
		participant.setFirstName("first name");
		participant.setHouseholdIncomeCode("household income code");
		participant.setLastName("last name");
		participant.setMaritalStatusCode("MaritalStatusCode");
		//participant.setPersonOccupationCollection();
		participant.setRaceCode("race code");
		participant.setTelecomAddress("TelecomAddress");
	
		
		// create Participant attributes
		participant.setConfidentialityIndicator(true);
		
		
		participant.setInitials("initials");
		//participant.setParticipantEligibilityAnswerCollection();
		participant.setPaymentMethodCode("payment method code");
		participant.setZipCode("90210");
		Set <HealthcareSiteParticipant> hSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			HealthcareSiteParticipant healthSitePart = new HealthcareSiteParticipant();
			healthSitePart.setParticipantIdentifier("ParticipantIdentifier  " + i);
			hSet.add(healthSitePart);
		}
		participant.setHealthcareSiteParticipantCollection(hSet);
		
		Set <PersonOccupation> set = new HashSet();
		for (int i = 0; i < 3; i++) {
			PersonOccupation occupation = new PersonOccupation();
			occupation.setPrimaryTypeCode("primary type code " + i);
			occupation.setPrimaryTypeCodeSystem("PrimaryTypeCodeSystem");
			occupation.setStartDate(new Date(0));
			occupation.setStopDate(new Date(0));
			set.add(occupation);
		}
		participant.setPersonOccupationCollection(set);	

		Set <FemaleReproductiveCharacteristic> reproSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			FemaleReproductiveCharacteristic reproChar = new FemaleReproductiveCharacteristic();
			reproChar.setAbortionIndicator(false);
			reproChar.setFirstLiveBirthAge(2);
			reproChar.setLiveBirthCount(20);
			reproChar.setMenopauseAge(76);
			reproChar.setMenopauseReasonCode("reason code");
			reproChar.setMenopauseReasonOtherText("reason other");
			reproChar.setMenopauseStartDate(new Date(0));
			reproChar.setStillBirthCount(0);
			reproSet.add(reproChar);
		}
		participant.setFemaleReproductiveCharacteristicCollection(reproSet);	

		Set <StudyParticipantAssignment> particSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			StudyParticipantAssignment studyPart = new StudyParticipantAssignment();
			//studyPart.setActivityCollection();
			studyPart.setArmIdentifier("arm identifier " + i);
			studyPart.setEligibilityWaiverReasonText("ligibilityWaiverReasonText");
			studyPart.setEnrollmentAge(23);
			studyPart.setInformedConsentFormSignedDate(new Date(0));
			studyPart.setOffStudyDate(new Date(0));
			studyPart.setOffStudyReasonCode("off study reason code");
			studyPart.setOffStudyReasonOtherText("OffStudyReasonOtherText");
			studyPart.setStudyAgentDoseLevel(4);
			studyPart.setStudyAgentDoseLevelUnitOfMeasureCode("UOM");
			studyPart.setStudyParticipantIdentifier(12);
			studyPart.setSubgroupCode("subgroup code");
			particSet.add(studyPart);
			//create an activity
			Set <Radiation> radiationSet = new HashSet();
			for (int j = 0; j < 3; j++) {
				Radiation radiation = new Radiation();
				radiation.setDose("dose " + j);
				radiation.setDoseUnitOfMeasureCode("UOM");
				radiation.setScheduleText("schedule text");
				radiation.setTherapyExtentCode("TherapyExtentCode");
				radiationSet.add(radiation);
			}
			studyPart.setActivityCollection(radiationSet);
			particSet.add(studyPart);
			
		}
		participant.setStudyParticipantAssignmentCollection(particSet);
		
		
		create(participant);
	}
*/
	public static void create(Object obj) {
		Transaction tx = null;
		Session session = InitSessionFactory.getInstance().getCurrentSession();
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null && tx.isActive())
				tx.rollback();
			assertTrue(false);
		}
	}

}