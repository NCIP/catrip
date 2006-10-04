/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AddressInsertTest extends TestCase {
	
  private static int maxrecs = 5;
	
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StreetNames.txt";
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CityNames.txt";
  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StateNames.txt";
  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ZipCodes.txt";
  private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PhoneNums.txt";
   
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  public String[] dataarr4 = new String[1000];
  public String[] dataarr5 = new String[1000];
  public String[] dataarr6 = new String[1000];
     
  public AddressInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(AddressInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//STREETS
		dataarr1=dg.randomReadFile(maxrecs,inFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSTREET: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
			
		//CITIES
		dataarr2=dg.randomReadFile(maxrecs,inFile2);

		if (DEBUG) {
			int arrsize=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tCITY: " + dataarr2[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//STATES
		dataarr3=dg.randomReadFile(maxrecs,inFile3);

		if (DEBUG) {
			int arrsize=dataarr3.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSTATE: " + dataarr3[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//ZIPS
		dataarr4=dg.randomReadFile(maxrecs,inFile4);

		if (DEBUG) {
			int arrsize=dataarr4.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tZIP: " + dataarr4[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//PHONES
		dataarr5=dg.randomReadFile(maxrecs,inFile5);
		//FAXS
		dataarr6=dg.randomReadFile(maxrecs,inFile5);

		if (DEBUG) {
			int arrsize=dataarr5.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tPHONE: " + dataarr5[row]);
       	    	System.out.println("\t\t\tFAX: " + dataarr6[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		
		dg.buildAddress(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




