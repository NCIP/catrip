/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class SpecimenInsertTest extends TestCase {
  
    private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenClass.txt";
    private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenType.txt";
	  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantity.txt";
	  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantityAvail.txt";
	  private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenCmmt.txt";
	  private static String inFile6 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenBarcode.txt";
	  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenClassV2.txt";
	  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenTypeV2.txt";
	  private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantityV2.txt";
	  private static String newFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantityAvailV2.txt";
	  private static String newFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenCmmtV2.txt";
	 
	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  public String[] dataarr3 = new String[1000];
	  public String[] dataarr4 = new String[1000];
	  public String[] dataarr5 = new String[1000];
	  public String[] dataarr6 = new String[1000];
  private static int maxrecs = 5;
  
    
  public SpecimenInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(SpecimenInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		dg.buildThreeDataFiles(maxrecs,inFile1,newFile1,inFile2,newFile2,inFile5,newFile5);
		
		//SPECIMEN CLASS
 		dataarr1=dg.ReadFile(maxrecs,newFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSPECIMEN CLASS: " + dataarr1[row]);
	       	}
		}
		
		//SPECIMEN TYPE
		dataarr2=dg.ReadFile(maxrecs,newFile2);

		if (DEBUG) {
			int arrsize=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSPECIMEN TYPE: " + dataarr2[row]);
	       	}
		}
		
		//SPECIMEN CMMT
 		dataarr5=dg.ReadFile(maxrecs,newFile5);

		if (DEBUG) {
			int arrsize=dataarr5.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSPECIMEN CMMT: " + dataarr5[row]);
	       	}
		}
		
		
		dg.buildTwoDataFiles(maxrecs,inFile3,newFile3,inFile4,newFile4);
		
		//SPECIMEN QUANTITY
 		dataarr3=dg.ReadFile(maxrecs,newFile3);

		if (DEBUG) {
			int arrsize=dataarr3.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSPECIMEN QUANTITY: " + dataarr3[row]);
	       	}
		}
		
		//SPECIMEN QUANTITY AVAIL
		dataarr4=dg.ReadFile(maxrecs,newFile4);

		if (DEBUG) {
			int arrsize=dataarr4.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSPECIMEN QUANTITY AVAIL: " + dataarr4[row]);
	       	}
		}
				
		//SPECIMEN BARCODE
		dataarr6=dg.ReadFile(maxrecs,inFile6);

		if (DEBUG) {
			int arrsize=dataarr6.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tSPECIMEN CMMT: " + dataarr6[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		dg.buildSpecimen(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




