/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class ParticipantMedicalIdentifierInsertTest extends TestCase {
	
  private static int maxrecs = 500;
	
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\mrn.txt";
 
  public String[] dataarr1 = new String[1000];
     
  public ParticipantMedicalIdentifierInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(ParticipantMedicalIdentifierInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CAEDataGenerator dg = new CAEDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//MRN
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tMRN: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
			
		dg.buildParticipantIdent(maxrecs,dataarr1);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




