/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;

public class TestProtocolDb extends TestCase {

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static void testEligibilityCriteria() {
		EligibilityCriteria eCriteria = new Inclusion();
		
		eCriteria.setExpectedAnswerIndicator(true);
		
		
		eCriteria.setQuestionNumber(3000);
		eCriteria.setQuestionText("the question");
		
		//create(eCriteria);

		EligibilityCriteria exclusion = new Exclusion();
		exclusion.setExpectedAnswerIndicator(true);
		//exclusion();
		//exclusion();
		exclusion.setQuestionNumber(3000);
		exclusion.setQuestionText("the question");
		
		// add ParticipantEligibilityAnswer
		Set <ParticipantEligibilityAnswer> set = new HashSet();
		for (int i = 0; i < 3; i++) {
			ParticipantEligibilityAnswer pea= new ParticipantEligibilityAnswer();
			pea.setAnswerText("answer text");
			pea.setChecklistNumber("check list number");
			set.add(pea);
		}
		exclusion.setParticipantEligibilityAnswerCollection(set);
		Set <Protocol> pset = new HashSet();
		Protocol protocol = new Protocol();
		protocol.setDescriptionText("description text");
		protocol.setAmendmentDate(new Date(0));
		protocol.setAmendmentIdentifier(1029);
		protocol.setBlindedIndicator(true);
		protocol.setDescriptionText("desc text");
		protocol.setDiseaseCode("disease Code");
		protocol.setIntentCode("intent Code");
		protocol.setLongTitleText("long title text");
		protocol.setMonitorCode("monitor code");
		pset.add(protocol);
		exclusion.setProtocolCollection(pset);
		create(exclusion);
		
	}
/*	public static void testProtocolOccurrence() {
		// create a protocol
		Protocol protocol = new Protocol();
		protocol.setDescriptionText("description text");
		protocol.setAmendmentDate(new Date(0));
		protocol.setAmendmentIdentifier(1029);
		protocol.setBlindedIndicator(true);
		protocol.setDescriptionText("desc text");
		protocol.setDiseaseCode("disease Code");
		protocol.setIntentCode("intent Code");
		protocol.setLongTitleText("long title text");
		protocol.setMonitorCode("monitor code");
		protocol.setMultiInstitutionIndicator(false);
		protocol.setNavyNCIIdentifier("navy nci id");
		protocol.setNciIdentifier("nci identifier");
		protocol.setPhaseCode("phase code");
		protocol.setPrecisText("precis text");
		protocol.setRandomizedIndicator(false);
		protocol.setShortTitleText("short title text");
		protocol.setSponsorCode("sponser Code");
		protocol.setTargetAccrualNumber(233);
		//protocol.setEligibilityCriteriaCollection();
		
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
		protocol.setStudyInvestigatorCollection(siSet);
		
		// create a protocol status
		Set <ProtocolStatus> psSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			ProtocolStatus protocolStatus = new ProtocolStatus();
			protocolStatus.setStatusCode("status code " + i);
			protocolStatus.setStatusDate(new Date(0));
			
			psSet.add(protocolStatus);
		}
		protocol.setProtocolStatusCollection(psSet);
		
		//studysites
		Set <StudySite> sSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			StudySite studySite = new StudySite();
			studySite.setIrbApprovalDate(new Date(0));
			studySite.setRoleCode("role Code " + i);
			studySite.setStartDate(new Date(0));
			studySite.setStatusCode("status code");
			studySite.setStopDate(new Date(0));
			studySite.setTargetAccrualNumber("accrual number");
			sSet.add(studySite);
		}
		protocol.setStudySiteCollection(sSet);
		//StudyAgents
		Set <StudyAgent> saSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			StudyAgent studyAgent = new StudyAgent();
			studyAgent.setInvestigationalIndicator(true);
			studyAgent.setInvestigationalNewDrugIdentifier("investigational new drug id " + i);
			studyAgent.setStatusCode("status code");
			studyAgent.setStatusDate(new Date(0));
			Set <SubstanceAdministration> subSet = new HashSet();
			for (int k = 0; k < 3; k++) {
				SubstanceAdministration obj = new SubstanceAdministration();
				// activity attributes
				obj.setDescriptionText("SpecimenCollection descritption text");
				obj.setDurationUnitOfMeasureCode("DurationUnitOfMeasureCode");
				obj.setDurationValue(100);
				obj.setName("name");
				obj.setPlannedIndicator(true);
				obj.setReasonCode("reason code");
				obj.setStartDate(new Date(0));
				obj.setStopDate(new Date(0));
				obj.setType("type");

				// SubstanceAdministration attributes
				obj.setDoseChangeCode("dose change Code");
				obj.setDoseChangeIndicatorCode(100);
				obj.setDoseFrequencyCode("frequency code");
				obj.setDoseFrequencyText("dose requency text");
				obj.setRouteCode("route code");
				obj.setSingleDose(125);
				obj.setSingleDoseUnitOfMeasureCode("SingleDoseUnitOfMeasureCode");
				obj.setTotalDose(400);
				obj.setTotalDoseUnitOfMeasureCode("TotalDoseUnitOfMeasureCode");
				subSet.add(obj);
			}
			studyAgent.setSubstanceAdministrationCollection(subSet);
			
			saSet.add(studyAgent);
		}
		protocol.setStudyAgentCollection(saSet);
		create(protocol);
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
