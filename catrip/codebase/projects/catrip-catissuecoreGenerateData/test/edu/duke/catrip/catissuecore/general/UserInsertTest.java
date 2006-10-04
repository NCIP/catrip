/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserInsertTest extends TestCase {

  private static int maxrecs = 5;
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\UserComments.txt";
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\EmailAddress.txt";
  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\FirstNamesFemales.txt";
  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\LastNamesV2.txt";
  private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\Logins.txt";
  private static String inFile6 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\Passwords.txt";
  private static String inFile7 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\Dates.txt";

  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  public String[] dataarr4 = new String[1000];
  public String[] dataarr5 = new String[1000];
  public String[] dataarr6 = new String[1000];
  public String[] dataarr7 = new String[1000];
  
  CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
    
  public UserInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(UserInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;
		int arrsiz;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//COMMENTS
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			arrsiz=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tCOMMENT: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
		//EMAIL
		dataarr2=dg.ReadFile(maxrecs,inFile2);
		if (DEBUG) {
			arrsiz=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tEMAIL: " + dataarr2[row]);
	       	}
		}
		
		//FName
		dataarr3=dg.ReadFile(maxrecs,inFile3);
		if (DEBUG) {
			arrsiz=dataarr3.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tFNAME: " + dataarr3[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//LNAME
		dataarr4=dg.ReadFile(maxrecs,inFile4);

		if (DEBUG) {
			arrsiz=dataarr4.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tLNAME: " + dataarr4[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//LOGIN
		dataarr5=dg.ReadFile(maxrecs,inFile5);
		if (DEBUG) {
			arrsiz=dataarr5.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tLOGIN: " + dataarr5[row]);
	       	}
		}
		
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//PASSWORD
		dataarr6=dg.ReadFile(maxrecs,inFile6);

		if (DEBUG) {
			arrsiz=dataarr6.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tPASSWORD: " + dataarr6[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
			
		//START DATES
		dataarr7=dg.ReadFile(maxrecs,inFile7);

		if (DEBUG) {
			arrsiz=dataarr7.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tDATES: " + dataarr7[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
			
		dg.buildUser(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




