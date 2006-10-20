/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParticipantInsertTest extends TestCase {

//Variable to determining how many recs to create, per table
  private static int maxrecs = 250;
  
//INPUT FILES FOR POPULATING THE TABLES
//Participant
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\LastNamesV2.txt";
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\FirstNamesFemales.txt";
  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\dob.txt";
  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\race.txt";
  private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ssns.txt";
  private static String inFile6 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ParticipantID.txt";
//ParticipantIdent
  private static String inFile7 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\mrn.txt";

//DATA ARRAYS TO MANAGE THE INPUT DATA
//Participant
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  public String[] dataarr4 = new String[1000];
  public String[] dataarr5 = new String[1000];
  public String[] dataarr6 = new String[1000];
  public String[] dataarr7 = new String[1000];
  public String[] dataarr8 = new String[1000];
//ParticipantIdent
  public String[] dataarr9 = new String[1000];
  
  CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
    
  public ParticipantInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(ParticipantInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		int arrsiz;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

	//Participant
		//LastNames
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tLNAME: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
		//FName
		dataarr2=dg.ReadFile(maxrecs,inFile2);
		
		//MName
		arrsiz=dataarr2.length;
		for (int row=0; row<arrsiz; row++) {
        	dataarr3[row]=dataarr2[dg.randomInRange(0,maxrecs-1)];
		}

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tFNAME: " + dataarr2[row]);
       	    	System.out.println("\t\t\tMNAME: " + dataarr3[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//dob
		dataarr4=dg.ReadFile(maxrecs,inFile3);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tDOB: " + dataarr4[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//race
		dataarr5=dg.ReadFile(maxrecs,inFile4);
		//ethnicity
		arrsiz=dataarr5.length;
		for (int row=0; row<arrsiz; row++) {
        	dataarr6[row]=dataarr5[row];
		}

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tRACE: " + dataarr5[row]);
       	    	System.out.println("\t\t\tETHNICITY: " + dataarr6[row]);
	       	}
		}
		
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//ssn
		dg.buildSSNFile(1000,inFile5);
		
		dataarr7=dg.ReadFile(maxrecs,inFile5);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSSNS: " + dataarr7[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//uniquePatientIdentifier
		dataarr8=dg.ReadFile(maxrecs,inFile6);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tuniquePatientIdentifier: " + dataarr8[row]);
	       	}
		}
		
	//ParticipantIdent
		//mrn
		dataarr9=dg.ReadFile(maxrecs,inFile7);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tmrn: " + dataarr9[row]);
	       	}
		}
		
		//Call the buildParticipant method to actually insert the data contained in the arrays
		dg.buildParticipant(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7,dataarr8,dataarr9);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




