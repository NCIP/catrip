/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class StorageTypeInsertTest extends TestCase {
  
	  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageType.txt";
	  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\OneDimLab.txt";
	  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TwoDimLab.txt";
	  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageTypeV2.txt";
	  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\OneDimLabV2.txt";
	  private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TwoDimLabV2.txt";
	 
	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  public String[] dataarr3 = new String[1000];
  private static int maxrecs = 5;
  
    
  public StorageTypeInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(StorageTypeInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//Storage Type
 		dg.buildDataFile(maxrecs,inFile1,newFile1);
		dataarr1=dg.ReadFile(maxrecs,newFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tStorage Type: " + dataarr1[row]);
	       	}
		}
		
		dg.buildTwoDataFiles(maxrecs,inFile2,newFile2,inFile3,newFile3);
		
		//OneDimLab
 		dataarr2=dg.ReadFile(maxrecs,newFile2);

		if (DEBUG) {
			int arrsize=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tOneDimLab: " + dataarr2[row]);
	       	}
		}
		
		//TwoDimLab
		dataarr3=dg.ReadFile(maxrecs,newFile3);

		if (DEBUG) {
			int arrsize=dataarr3.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tTwoDimLab: " + dataarr3[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		dg.buildStorageType(maxrecs,dataarr1,dataarr2,dataarr3);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




