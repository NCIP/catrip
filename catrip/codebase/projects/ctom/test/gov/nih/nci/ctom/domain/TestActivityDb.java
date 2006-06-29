package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;

public class TestActivityDb extends TestCase {

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static void testStudyTimePoint(){
		Radiation radiation = new Radiation();
		radiation.setDoseUnitOfMeasureCode("DoseUnitOfMeasureCode");
		
		Set<StudyTimePoint> stpSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			StudyTimePoint stp = new StudyTimePoint();
			stp.setCourseNumber(899);
			stp.setCourseStartDate(new Date(0));
			stp.setCourseStopDate(new Date(0));
			stp.setEpochName("epochName");
			stp.setVisitName("visit name");
			stpSet.add(stp);
		}
		radiation.setStudyTimePoint(stpSet);
		
		create(radiation);
		
	}
	
	public static void testAllRelationship(){
		ActivityRelationship ar = new ActivityRelationship();
		Radiation radiation_1 = new Radiation();
		radiation_1.setDoseUnitOfMeasureCode("DoseUnitOfMeasureCode for 1");
		
		Radiation radiation_2 = new Radiation();
		radiation_2.setDoseUnitOfMeasureCode("DoseUnitOfMeasureCode for 2");
		
		ar.setActivity_1(radiation_1);
		ar.setActivity_2(radiation_2);
		ar.setCommentText("parent");
		create(ar);
		
		AssessmentRelationship assessment = new AssessmentRelationship();
		LesionEvaluation lesion_1 = new LesionEvaluation();
		lesion_1.setEvaluationCode("eval code for 1");
		
		LesionEvaluation lesion_2 = new LesionEvaluation();
		lesion_2.setEvaluationCode("eval code for 2");
		
		assessment.setAssessment_1(lesion_1);
		assessment.setAssessment_2(lesion_2);
		assessment.setCommentText("parent");
		create(assessment);
		

		ObservationRelationship observation = new ObservationRelationship();
		LesionDescription lesionDesc_1 = new LesionDescription();
		lesionDesc_1.setAnatomicSiteCode("anatomic Site Code for 1");
		
		LesionDescription lesionDesc_2 = new LesionDescription();
		lesionDesc_2.setAnatomicSiteCode("anatomic Site Code for 2");
		
		observation.setObservation_1(lesionDesc_1);
		observation.setObservation_2(lesionDesc_2);
		observation.setCommentText("parent");
		create(observation);

	}

	@SuppressWarnings("unchecked")
	
	  public static void testActivity(){ Set <ActivityRelationship> actRelSet =
	  new HashSet(); Set <Observation> observationSet = new HashSet();
	  Radiation obj = new Radiation(); //activity attributes
	  obj.setDescriptionText("descritption text");
	  obj.setDurationUnitOfMeasureCode("DurationUnitOfMeasureCode");
	  obj.setDurationValue(100); obj.setName("name");
	  obj.setPlannedIndicator(true); obj.setReasonCode("reason code");
	  obj.setStartDate(new Date(0)); obj.setStopDate(new Date(0));
	  obj.setType("type");
	  
	  //obj.setActivityRelationshipCollection(actRelSet); @TODO
	  //obj.setObservationCollection(observationSet); @TODO
	  
	  //Procedure Attributes obj.setAnatomicSiteCode("AnatomicSiteCode");
	  obj.setAnatomicSiteCodeSystem("AnatomicSiteCodeSystem");
	   // Radiation attributes
	  obj.setDoseUnitOfMeasureCode("DoseUnitOfMeasureCode");
	  obj.setDose("dose"); obj.setScheduleText("schedule Text");
	  obj.setTherapyExtentCode("TherapyExtentCode");
	   // //create CancerStage objects
	  
	  create(obj);
	   }
	  
	  public static void testImaging(){ 
		  Imaging obj = new Imaging(); //activity	  attributes 
		  obj.setDescriptionText("descritption text");
	  obj.setDurationUnitOfMeasureCode("DurationUnitOfMeasureCode");
	  obj.setDurationValue(100); obj.setName("name");
	  obj.setPlannedIndicator(true); obj.setReasonCode("reason code");
	  obj.setStartDate(new Date(0)); obj.setStopDate(new Date(0));
	  obj.setType("type");
	  
	  //obj.setActivityRelationshipCollection(actRelSet); @TODO
	  //obj.setObservationCollection(observationSet); @TODO
	  
	  //Procedure Attributes obj.setAnatomicSiteCode("AnatomicSiteCode");
	  obj.setAnatomicSiteCodeSystem("AnatomicSiteCodeSystem");
	   // Imaging attributes obj.setContrastAgentEnhancementIndicator(false);
	  obj.setEnhancementDescriptionText("EnhancementDescriptionText");
	  obj.setEnhancementRateValue(333);
	  obj.setImageIdentifier("ImageIdentifier");
	  
	  create(obj); }
	  
	  public static void testSurgery(){ 
		  Surgery obj = new Surgery();
		  //activity attributes
	   obj.setDescriptionText("descritption text");
	  obj.setDurationUnitOfMeasureCode("DurationUnitOfMeasureCode");
	  obj.setDurationValue(100); obj.setName("name");
	  obj.setPlannedIndicator(true); obj.setReasonCode("reason code");
	  obj.setStartDate(new Date(0)); obj.setStopDate(new Date(0));
	  obj.setType("type");
	  
	  //obj.setActivityRelationshipCollection(actRelSet); @TODO
	  //obj.setObservationCollection(observationSet); @TODO
	  
	  //Procedure Attributes obj.setAnatomicSiteCode("AnatomicSiteCode");
	  obj.setAnatomicSiteCodeSystem("AnatomicSiteCodeSystem");
	   // NO Surgery attributes
	  
	  create(obj); }
	  
	  public static void testSpecimenCollection(){ SpecimenCollection obj = new
	  SpecimenCollection(); //activity attributes
	  obj.setDescriptionText("SpecimenCollection descritption text");
	  obj.setDurationUnitOfMeasureCode("DurationUnitOfMeasureCode");
	  obj.setDurationValue(100); obj.setName("name");
	  obj.setPlannedIndicator(true); obj.setReasonCode("reason code");
	  obj.setStartDate(new Date(0)); obj.setStopDate(new Date(0));
	  obj.setType("type");
	  
	  //obj.setActivityRelationshipCollection(actRelSet); @TODO
	  //obj.setObservationCollection(observationSet); @TODO
	  
	  //Procedure Attributes obj.setAnatomicSiteCode("AnatomicSiteCode");
	  obj.setAnatomicSiteCodeSystem("AnatomicSiteCodeSystem");
	   // SpecimenCollection attributes obj.setMethodCode("method code");
	  obj.setSiteConditionCode("SiteConditionCode"); // create some Specimens
	  Set <Specimen> specSet = new HashSet(); for (int i = 0; i < 3; i++) {
	  Specimen o = new Specimen(); //o.setIdd(i); autogenerated
	  o.setSampleIdentifier(100); o.setSampleTypeCode("SampleTypeCode");
	  o.setVolume(125);
	  o.setVolumeUnitOfMeasureCode("VolumeUnitOfMeasureCode"); specSet.add(o); }
	  obj.setSpecimenCollection(specSet);
	  
	  create(obj); }
	 
	
	public static void testSubstanceAdministration() {
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

		// create some AgentOccurrence
		Set<AgentOccurrence> agentOccSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			AgentOccurrence o = new AgentOccurrence();
			// o.setIdd(i); autogenerated
			o.setExpirationDate(new Date(0));
			o.setFormCode("form code");
			o.setLotNumber("lot number");

			agentOccSet.add(o);
		}
		obj.setAgentOccurranceCollection(agentOccSet);

		Set<LesionDescription> lesionSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			LesionDescription ld = new LesionDescription();
			// o.setIdd(i); autogenerated
			ld.setAnatomicSiteCode("anatomic site code " + i);
			ld.setConfidentialityCode("conf code");
			ld.setDimensionProduct(i);

			lesionSet.add(ld);
		}
		 obj.setObservationCollection(lesionSet);
		create(obj);
	}
	
   //********* DOES NOT WORK  *************
/*	public static void testAgent() {
		// create an Agent
		Agent agent = new Agent();
		agent.setDescriptionText("description text");
		agent.setName("name");
		agent.setStatusCode("status code");
		Set<AgentOccurrence> agentOccSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			AgentOccurrence o = new AgentOccurrence();
			o.setExpirationDate(new Date(0));
			o.setFormCode("form code");
			o.setLotNumber("lot number " + i);
			agentOccSet.add(o);
		}
		agent.setAgentOccurrenceCollection(agentOccSet);
		
		create(agent);
		
		//create substance Administrator
		Set<SubstanceAdministration> saSet = new HashSet();
		SubstanceAdministration obj = new SubstanceAdministration();
		obj.setDoseChangeCode("dose change Code");
		obj.setDoseChangeIndicatorCode(100);
		obj.setDoseFrequencyCode("frequency code");
		obj.setDoseFrequencyText("dose requency text");
		obj.setRouteCode("route code");
		obj.setSingleDose(125);
		obj.setSingleDoseUnitOfMeasureCode("SingleDoseUnitOfMeasureCode");
		obj.setTotalDose(400);
		obj.setTotalDoseUnitOfMeasureCode("TotalDoseUnitOfMeasureCode");
		saSet.add(obj);
		agent.setSubstanceAdministrationCollection(saSet);
		Set<StudyAgent> stAgentSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			StudyAgent o = new StudyAgent();
			o.setInvestigationalIndicator(true);
			o.setInvestigationalNewDrugIdentifier("InvestigationalNewDrugIdentifier");
			o.setStatusCode("status code " + i);
			stAgentSet.add(o);
		}
		agent.setStudyAgentCollection(stAgentSet);

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
