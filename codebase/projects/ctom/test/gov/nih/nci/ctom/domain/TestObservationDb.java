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

public class TestObservationDb extends TestCase {

	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@SuppressWarnings("unchecked")
	public static void testClinicalResult(){
		
		ClinicalResult obj = new ClinicalResult();
		// Observation superclass atributes
		//obj.setAssessmentCollection(); @TODO
		obj.setConfidentialityCode("clinical Result");
		//obj.setObservationRelationshipCollection(); @TODO
		obj.setReportingDate(new Date(0));
		obj.setStatusCode("from " + obj.getClass().getName());
		obj.setUncertaintyCode("UncertaintyCode");
		
		//LesionDescription attributes
		obj.setAssayMethodCode("assay method Code");
		obj.setBiomarkerIndicator(true);
		obj.setBodyPositionCode("body position");
		obj.setLabReferenceRangeCode("LabReferenceRangeCode");
		obj.setLabTechniqueCode("LabTechniqueCode");
		obj.setMeansVitalStatusObtainedCode("MeansVitalStatusObtainedCode");
		obj.setNormalAbnormalIndicator("NormalAbnormalIndicator");
		obj.setPanelName("panel name");
		obj.setSignificanceIndicator(true);
		obj.setValue("value");
		obj.setValueUnitOfMeasureCode("ValueUnitOfMeasureCode");
		

		create(obj);

	
	}
	
	
	@SuppressWarnings("unchecked")
	public static void testHistopathology(){
		Histopathology obj = new Histopathology();
		// Observation superclass atributes
		//obj.setAssessmentCollection(); @TODO
		obj.setConfidentialityCode("clinical Result");
		//obj.setObservationRelationshipCollection(); @TODO
		obj.setReportingDate(new Date(0));
		obj.setStatusCode("from " + obj.getClass().getName());
		obj.setUncertaintyCode("UncertaintyCode");
		
		//LesionDescription attributes
		obj.setGrossExamResultCode("GrossExamResultCode");
		obj.setInvolvedSurgicalMarginIndicator(true);
		//obj.setNeoplasmCollection();
		obj.setReportDescriptiveText("ReportDescriptiveText");
		Set <HistopathologyGrade> mSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			HistopathologyGrade grade = new HistopathologyGrade();
			grade.setCommentText("Grade Comment " + i);
			grade.setGradeCode("grade code " + i);
			grade.setGradeCodeSystem("GradeCodeSystem");
			mSet.add(grade);
		}
		obj.setHistopathologyGradeCollection(mSet);
		Set <Neoplasm> plasmSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			Neoplasm grade = new Neoplasm();
			grade.setCellTypeCode("CellTypeCode " + i);
			plasmSet.add(grade);
		}
		obj.setNeoplasmCollection(plasmSet);
		create(obj);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void testLesionDescription(){
		
		LesionDescription obj = new LesionDescription();
		// Observation superclass atributes
		//obj.setAssessmentCollection(); @TODO
		obj.setConfidentialityCode("confidentiality Code");
		//obj.setObservationRelationshipCollection(); @TODO
		obj.setReportingDate(new Date(0));
		obj.setStatusCode("from " + obj.getClass().getName());
		obj.setUncertaintyCode("UncertaintyCode");
		
		//LesionDescription attributes
		obj.setAnatomicSiteCode("anatomic site Code");
		obj.setAnatomicSiteCodeSystem("anatomic site code system");
		obj.setAppearanceTypeCode("apperance type code");
		obj.setContactAnatomicSiteCode("ContactAnatomicSiteCode");
		obj.setContactAnatomicSiteCodeSystem("ContactAnatomicSiteCode SYSTEM");
		obj.setDimensionProduct(10);
		obj.setEvaluationNumber(100);
		obj.setLesionNumber("lesion number");
		obj.setMeasurableIndicator("true");
		obj.setMethodCode("Method code");
		obj.setPreviouslyIrradiatedSiteIndicator("PreviouslyIrradiatedSiteIndicator");
		obj.setTargetNonTargetCode("TargetNonTargetCode");
		obj.setXDimension(1);
		obj.setYDimension(2);
		obj.setZDimension(3);
		create(obj);
	}

		
		
	
	
	public static void create(Observation obj){
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
