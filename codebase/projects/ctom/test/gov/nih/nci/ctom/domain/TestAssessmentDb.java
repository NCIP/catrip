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

public class TestAssessmentDb extends TestCase {

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	@SuppressWarnings("unchecked")
/*	public static void testAdverseEvent(){
		Set <AdverseEventTherapy> s = new HashSet();
		AdverseEvent adverseEvent = new AdverseEvent();
		adverseEvent.setActionTakenCode("code");
		adverseEvent.setConditionPatternCode("Condition pattern code");
		//adverseEvent.setId(4);
		for (int i = 0; i < 3; i++) {
			AdverseEventTherapy o = new AdverseEventTherapy();
			//o.setIdd(i); autogenerated
			o.setDelayDurationUnitOfMeasureCode("UOM CODE " + i);
			
			s.add(o);
		}
		adverseEvent.setAdverseEventTherapyCollection(s);
		create(adverseEvent);
	
	}
	
	
	public static void testDiagnosis(){
		Set <CancerStage> cancerSiteSet = new HashSet();
		Diagnosis obj = new Diagnosis();
		obj.setAgeAtDiagnosis(34);
		obj.setDiseaseDiagnosisCode("Disease diag code");
		obj.setConfirmationDate(new java.sql.Date(0));
		//create CancerStage objects
		for (int i = 0; i < 3; i++) {
			CancerStage o = new CancerStage();
			//o.setIdd(i); autogenerated
			o.setStageCode("cancer CODE " + i);
			o.setTnmStage("tnm stage" + i);
			//create MetastasisSite objects for each CancerStage object
			Set <MetastasisSite> mSet = new HashSet();
			for (int j = 0; j < 3; j++) {
				MetastasisSite mSite = new MetastasisSite();
				mSite.setAnatomicSiteCode("anotomic Site Code " + j);
				mSite.setAnatomicSiteCodeSystem("anatomic site code system " + j);
				mSet.add(mSite);
			}
			o.setMetastasisSiteCollection(mSet);
			cancerSiteSet.add(o);
			
		}
		obj.setCancerStage(cancerSiteSet);
		
		create(obj);
	
	}
	
	//  DOES NOT WORK - class cast exception  *****************
	*/
	public static void testQualitativeEvaluation(){
		QualitativeEvaluation obj = new QualitativeEvaluation();
		obj.setEvaluationDate(new Date(0));
		obj.setAnamResultAccuracyPercent(15);
		obj.setEvaluationDate(new Date(0));
		obj.setMenstrualIndicator("false");
		obj.setMenstrualPatternTypeCode("pattern");
		obj.setMenstrualPatternTypeCode("type code");
		obj.setPainIndexCode("100");
		obj.setPainIndexCodeSystem("pain index code system");
		obj.setPerformanceStatusCode("25");
		obj.setPerformanceStatusCodeSystem("performance status code system");
		obj.setSurvivalStatusCode("10");
		obj.setSurvivalStatusDescriptionText("survival status desc text");
		
		// test adding a collection of Observations
		Set <LesionDescription> ldSet = new HashSet();
		for (int j = 0; j < 3; j++) {
			LesionDescription lDesc = new LesionDescription();
			lDesc.setAnatomicSiteCodeSystem("anatomic site code system " + j);
			lDesc.setAppearanceTypeCode("apperance type code");
			lDesc.setContactAnatomicSiteCode("ContactAnatomicSiteCode");
			lDesc.setContactAnatomicSiteCodeSystem("ContactAnatomicSiteCode SYSTEM");
			lDesc.setDimensionProduct(10);
			//lDesc.setActivityId(1);
			ldSet.add(lDesc);
		}
		obj.setObservationCollection(ldSet);
		create(obj);
		
	}
	
	/*
	public static void testDiseaseResponse(){
		DiseaseResponse obj = new DiseaseResponse();
		obj.setBestResponseCode("best response code");
		//obj.setAnamResultAccuracyPercent(15);
		//obj.setEvaluationDate(new Date(0));
		//obj.setMenstrualIndicator("false");
		//obj.setMenstrualPatternTypeCode("pattern");
		//obj.setMenstrualPatternTypeCode("type code");
		//obj.setPainIndexCode("100");
		//obj.setPainIndexCodeSystem("pain index code system");
		//obj.setPerformanceStatusCode("25");
		//obj.setPerformanceStatusCodeSystem("performance status code system");
		//obj.setSurvivalStatusCode(10);
		//obj.setSurvivalStatusDescriptionText("survival status desc text");
		create(obj);
		
	}
	
	
	public static void testDeathSummary(){
		DeathSummary obj = new DeathSummary();
		obj.setAutopsiedIndicator("1");
		obj.setDeathCauseCode("death cause code");
		obj.setDeathCauseText("death cause text");
		obj.setDeathDate(new Date(0));
		create(obj);
		
	}

	
	
	public static void testLesionEvaluation(){
		LesionEvaluation obj = new LesionEvaluation();
		//createObservations();
		//obj.setEvaluationDate(new Date(0));
		obj.setEvaluationCode("eval code");
		obj.setId(13);
		obj.setAssessmentRelationshipCollection(createAssesmentRelationships());
		create(obj);
	
	}

	
	@SuppressWarnings("unchecked")
	private static Set createAssesmentRelationships(){
	Set s = new HashSet();
	for (int i = 0; i < 3; i++) {
		AssessmentRelationship o = new AssessmentRelationship();
		o.setCommentText("this is comment " +i);
		//o.setId(i);
		s.add(o);
	
	}
	return s;
}
*/
	public static void create(Assessment obj){
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