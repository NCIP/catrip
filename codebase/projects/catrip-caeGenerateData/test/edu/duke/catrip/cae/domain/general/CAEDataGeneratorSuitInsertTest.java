/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.breast.BreastCancerTNMInsertTest;
import edu.duke.catrip.cae.domain.breast.InvasiveBreastCarcinomaInsertTest;
import edu.duke.catrip.cae.domain.breast.InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest;
import edu.duke.catrip.cae.domain.breast.NottinghamHistopathologicGradeInsertTest;
import edu.duke.catrip.cae.domain.breast.OtherBreastCancerHistopathologicGradeInsertTest;

public class CAEDataGeneratorSuitInsertTest extends TestCase {

    
  public CAEDataGeneratorSuitInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(CAEDataGeneratorSuitInsertTest.class);
   }

   //Tests inserting into table(s): ACCESSION, PARTICIPANT, SPECIMEN
	public void test_ParticipantInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_ParticipantInsertTest()...");
		
		ParticipantInsertTest test = new ParticipantInsertTest("ParticipantInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_ParticipantInsertTest()...");
		
	}

	//Tests inserting into table(s): BREAST_NOTT_HISTOPATH_GRADES
	public void test_NottinghamHistopathologicGradeInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_NottinghamHistopathologicGradeInsertTest()...");
		
		NottinghamHistopathologicGradeInsertTest test = new NottinghamHistopathologicGradeInsertTest("NottinghamHistopathologicGradeInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_NottinghamHistopathologicGradeInsertTest()...");
		
	}
	
	//Tests inserting into table(s): PARTICIPANT_IDENTIFIERS
	public void test_ParticipantMedicalIdentifierInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_ParticipantMedicalIdentifierInsertTest()...");
		
		ParticipantMedicalIdentifierInsertTest test = new ParticipantMedicalIdentifierInsertTest("ParticipantMedicalIdentifierInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_ParticipantMedicalIdentifierInsertTest()...");
		
	}
	
	//Tests inserting into table(s): THREE_DIMENSIONAL_SIZES
	public void test_ThreeDimensionalSizeInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_ThreeDimensionalSizeInsertTest()...");
		
		ThreeDimensionalTumorSizeInsertTest test = new ThreeDimensionalTumorSizeInsertTest("ThreeDimensionalSizeInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_ThreeDimensionalSizeInsertTest()...");
		
	}
	
	//Tests inserting into table(s): BREAST_CANCER_TNMF_FINDINGS, BREAST_MET_ANATOMIC_SITES
	public void test_BreastCancerTNMInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_BreastCancerTNMInsertTest()...");
		
		BreastCancerTNMInsertTest test = new BreastCancerTNMInsertTest("BreastCancerTNMInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_BreastCancerTNMInsertTest()...");
		
	}
	

	
	//Tests inserting into table(s): BREAST_OTHER_HISTOPATH_GRADES
	public void test_OtherBreastCancerHistopathologicGradeInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_OtherBreastCancerHistopathologicGradeInsertTest()...");
		
		OtherBreastCancerHistopathologicGradeInsertTest test = new OtherBreastCancerHistopathologicGradeInsertTest("OtherBreastCancerHistopathologicGradeInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_OtherBreastCancerHistopathologicGradeInsertTest()...");
		
	}
	
	//Tests inserting into table(s): INVASIVE_BREAST_CARCINOMA, BREAST_LOCATIONS, BREAST_MC_LOCATIONS
	public void test_InvasiveBreastCarcinomaInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_InvasiveBreastCarcinomaInsertTest()...");
		
		InvasiveBreastCarcinomaInsertTest test = new InvasiveBreastCarcinomaInsertTest("InvasiveBreastCarcinomaInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_InvasiveBreastCarcinomaInsertTest()...");
		
	}	
	

	

	
	//Tests inserting into table(s): BREAST_NEO_HSTOPTHLGC_TYPES
	public void test_InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest()...");
		
		InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest test = new InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest("InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest()...");
		
	}
	
	
	public void test_EventParametersInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_EventParametersInsertTest()...");
		
		EventParametersInsertTest test = new EventParametersInsertTest("EventParametersInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_EventParametersInsertTest()...");
		
	}
	
	public void test_AnnotationEventParametersInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_AnnotationEventParametersInsertTest()...");
		
		AnnotationEventParametersInsertTest test = new AnnotationEventParametersInsertTest("AnnotationEventParametersInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_AnnotationEventParametersInsertTest()...");
		
	}
	
	public void test_AnnotationSetInsertTest() throws Exception {

		final boolean DEBUG = true;

		if (DEBUG) System.out.println("Inside test_AnnotationSetInsertTest()...");
		
		AnnotationSetInsertTest test = new AnnotationSetInsertTest("AnnotationSetInsertTest");

		test.testRead_Insert();
		
		if (DEBUG) System.out.println("\tEnd Of test_AnnotationSetInsertTest()...");
		
	}
}




