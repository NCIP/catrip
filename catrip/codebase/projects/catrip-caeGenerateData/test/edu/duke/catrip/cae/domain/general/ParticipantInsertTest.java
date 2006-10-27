/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.general;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ParticipantInsertTest extends TestCase {

  private static int maxrecs = 500;
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\LastNamesV2.txt";

  private static String inFile2 = "data\\FirstNamesFemales.txt";
  private static String inFile4 = "data\\dob.txt";
  private static String inFile5 = "data\\race.txt";
  private static String inFile7 = "data\\dates.txt";
  private static String inFile8 = "data\\dx.txt";
  private static String inFile9 = "data\\surgicalpath#.txt";
  private static String inFile10 = "data\\ParticipantID.txt";

  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  public String[] dataarr4 = new String[1000];
  public String[] dataarr5 = new String[1000];
  public String[] dataarr6 = new String[1000];
  public String[] dataarr7 = new String[1000];
  public String[] dataarr8 = new String[1000];
  public String[] dataarr9 = new String[1000];
  public String[] dataarr10 = new String[1000];
  public String[][] dataarr2d = new String[1000][2];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public ParticipantInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		int arrsize;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//LastNames
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tLNAME: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
		//FName
		dataarr2=dg.ReadFile(maxrecs,inFile2);
		arrsize=dataarr2.length;
        for (int row=0; row<arrsize; row++) {
        	//Gender
        	dataarr3[row]="Female Gender";
        	if (DEBUG) {
        		System.out.println("\t\t\tFNAME: " + dataarr2[row]);
        	   	System.out.println("\t\t\tGENDER: " + dataarr3[row]);
        	}
       	}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//dob
		dataarr4=dg.ReadFile(maxrecs,inFile4);

		if (DEBUG) {
			arrsize=dataarr4.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tDOB: " + dataarr4[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//race
		dataarr5=dg.ReadFile(maxrecs,inFile5);
		//ethnicity
		int arrsiz=dataarr5.length;
		for (int row=0; row<arrsiz; row++) {
        	dataarr6[row]=dataarr5[row];
		}

		if (DEBUG) {
			arrsize=dataarr5.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tRACE: " + dataarr5[row]);
       	    	System.out.println("\t\t\tETHNICITY: " + dataarr6[row]);
	       	}
		}
		
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//dates
		dataarr7=dg.ReadFile(maxrecs,inFile7);

		if (DEBUG) {
			arrsize=dataarr7.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tDATES: " + dataarr7[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//dx
		dataarr8=dg.ReadFile(maxrecs,inFile8);

		if (DEBUG) {
			arrsize=dataarr8.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tDX: " + dataarr8[row]);
	       	}
		}
		
		//surgicalpath#
		dataarr9=dg.ReadFile(maxrecs,inFile9);

		if (DEBUG) {
			arrsize=dataarr9.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSURGICALPATH#: " + dataarr9[row]);
	       	}
		}
		
		//uniquePatientIdentifier
		dataarr10=dg.ReadFile(maxrecs,inFile10);

		if (DEBUG) {
			arrsize=dataarr10.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tuniquePatientIdentifier: " + dataarr10[row]);
	       	}
		}
		
		dg.buildParticipant(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7,dataarr8,dataarr9,dataarr10);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(ParticipantInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




