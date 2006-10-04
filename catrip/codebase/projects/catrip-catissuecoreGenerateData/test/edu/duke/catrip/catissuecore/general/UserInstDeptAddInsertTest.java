/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserInstDeptAddInsertTest extends TestCase {
	
  private static int maxrecs = 5;
	
  //user files
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\UserComments.txt";
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\EmailAddress.txt";
  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\FirstNamesFemales.txt";
  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\LastNamesV2.txt";
  private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\Logins.txt";
  private static String inFile6 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\Passwords.txt";
  private static String inFile7 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\Dates.txt";

  //inst and dept files
  private static String inFile8 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CancerInstitutes.txt";							
  private static String inFile9 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CancerDepts.txt";

  //address files
  private static String inFile10 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StreetNames.txt";
  private static String inFile11 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CityNames.txt";
  private static String inFile12 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StateNames.txt";
  private static String inFile13 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ZipCodes.txt";
  private static String inFile14 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PhoneNums.txt";

  public String[][] dataarr = new String[500][2];
  public String[] dataarr1 = new String[500];
  public String[] dataarr2 = new String[500];
  public String[] dataarr3 = new String[500];
  public String[] dataarr4 = new String[500];
  public String[] dataarr5 = new String[500];
  public String[] dataarr6 = new String[500];
  public String[] dataarr7 = new String[500];
  public String[] dataarr8 = new String[500];
  public String[] dataarr9 = new String[500];
  public String[] dataarr10 = new String[500];
  public String[] dataarr11 = new String[500];
  public String[] dataarr12 = new String[500];
  public String[] dataarr13 = new String[500];
  public String[] dataarr14 = new String[500];
  public String[] dataarr15 = new String[500];
    
  public UserInstDeptAddInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(UserInstDeptAddInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");
		
//USER STUFF
		//COMMENTS
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tCOMMENT: " + dataarr1[row]);
	       	}
		}
				
		//EMAIL
		dataarr2=dg.ReadFile(maxrecs,inFile2);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tEMAIL: " + dataarr2[row]);
	       	}
		}
		
		//FName
		dataarr3=dg.ReadFile(maxrecs,inFile3);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tFNAME: " + dataarr3[row]);
	       	}
		}
		
		//LNAME
		dataarr4=dg.ReadFile(maxrecs,inFile4);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tLNAME: " + dataarr4[row]);
	       	}
		}

		//LOGIN
		dataarr5=dg.ReadFile(maxrecs,inFile5);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tLOGIN: " + dataarr5[row]);
	       	}
		}
		
		//PASSWORD
		dataarr6=dg.ReadFile(maxrecs,inFile6);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tPASSWORD: " + dataarr6[row]);
	       	}
		}
			
		//START DATES
		dataarr7=dg.ReadFile(maxrecs,inFile7);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++){
       	    	System.out.println("\t\t\tDATES: " + dataarr7[row]);
	       	}
		}
		
//INST AND DEPT STUFF
		dataarr=dg.randomReadFile(maxrecs,inFile8,inFile9);
        for (int row=0; row<maxrecs; row++) {
        	//INSTITUTIONS
   	    	dataarr8[row]=dataarr[row][0];
   	    	//DEPARTMENTS
   	    	dataarr9[row]=dataarr[row][1];
       	}

		if (DEBUG) {
			//print fields
	        for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tINST: " + dataarr8[row]);
       	    	System.out.println("\t\t\tDEPT: " + dataarr9[row]);
	       	}
		}
		
//ADDRESS STUFF
		//STREETS
		dataarr10=dg.randomReadFile(maxrecs,inFile10);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSTREET: " + dataarr10[row]);
	       	}
		}
			
		//CITIES
		dataarr11=dg.randomReadFile(maxrecs,inFile11);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tCITY: " + dataarr11[row]);
	       	}
		}
		
		//STATES
		dataarr12=dg.randomReadFile(maxrecs,inFile12);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSTATE: " + dataarr12[row]);
	       	}
		}
		
		//ZIPS
		dataarr13=dg.randomReadFile(maxrecs,inFile13);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tZIP: " + dataarr13[row]);
	       	}
		}
		
		//PHONES
		dataarr14=dg.randomReadFile(maxrecs,inFile14);
		//FAXS
		dataarr15=dg.randomReadFile(maxrecs,inFile14);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPHONE: " + dataarr14[row]);
       	    	System.out.println("\t\t\tFAX: " + dataarr15[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		dg.buildUserInstDeptAdd(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7,dataarr8,dataarr9,dataarr10,dataarr11,dataarr12,dataarr13,dataarr14,dataarr15);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




