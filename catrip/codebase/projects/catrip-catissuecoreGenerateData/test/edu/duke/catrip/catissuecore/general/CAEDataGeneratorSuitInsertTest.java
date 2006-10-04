/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import edu.duke.catrip.catissuecore.general.ParticipantInsertTest;

public class CAEDataGeneratorSuitInsertTest extends TestCase {

  CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
	  
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


}




