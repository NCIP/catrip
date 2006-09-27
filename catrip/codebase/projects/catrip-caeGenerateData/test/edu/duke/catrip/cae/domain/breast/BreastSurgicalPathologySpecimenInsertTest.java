package edu.duke.catrip.cae.domain.breast;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;

public class BreastSurgicalPathologySpecimenInsertTest extends TestCase {
	
	  private static int maxrecs = 500;
	  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\OthSurgProc.txt";
	  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\LymphNodeProc.txt";
	  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\Laterality.txt";
	  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\SurgProc.txt";

	  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\OthSurgProcV2.txt";
	  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\LymphNodeProcV2.txt";
	  private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\LateralityV2.txt";	 
	  private static String newFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\SurgProcV2.txt";
	  
	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  public String[] dataarr3 = new String[1000];
	  public String[] dataarr4 = new String[1000];
	  
	  CAEDataGenerator dg = new CAEDataGenerator();

  public BreastSurgicalPathologySpecimenInsertTest(String sTestName) {
      super(sTestName);
  }

	   public void setUp() {

	   }

	   public void tearDown() {
	   }


	   public static Test suite() {
	       return new TestSuite(BreastSurgicalPathologySpecimenInsertTest.class);
	   }

	   //test reading data files into an array and insert into db
		public void testRead_Insert() throws Exception {

			final boolean DEBUG = true;

			//DataGenerator dg = new DataGenerator();
			
			if (DEBUG) System.out.println("Inside testReadFile()...");

			//OthSurgProc
	 		dg.buildDataFile(maxrecs,inFile1,newFile1);
			dataarr1=dg.ReadFile(maxrecs,newFile1);

			if (DEBUG) {
				int arrsize=dataarr1.length;
		        //print fields
		        for (int row=0; row<arrsize; row++) {
	       	    	System.out.println("\t\t\tOthSurgProc: " + dataarr1[row]);
		       	}
			}
			
			if (DEBUG) System.out.println("\tBack Inside testReadFile()");
			
			//LymphNodeProc
	 		dg.buildDataFile(maxrecs,inFile2,newFile2);
			dataarr2=dg.ReadFile(maxrecs,newFile2);

			if (DEBUG) {
				int arrsize=dataarr2.length;
		        //print fields
		        for (int row=0; row<arrsize; row++) {
	       	    	System.out.println("\t\t\tLymphNodeProc: " + dataarr2[row]);
		       	}
			}
			
			if (DEBUG) System.out.println("\tBack Inside testReadFile()");

			//Laterality
	 		dg.buildDataFile(maxrecs,inFile3,newFile3);
			dataarr3=dg.ReadFile(maxrecs,newFile3);
			
			if (DEBUG) {
				int arrsiz=dataarr3.length;
				for (int row=0; row<arrsiz; row++) {
					System.out.println("\t\t\tLaterality: " + dataarr3[row]);
				}
			}
			
			//SurgProc
	 		dg.buildDataFile(maxrecs,inFile4,newFile4);
			dataarr4=dg.ReadFile(maxrecs,newFile4);
			
			if (DEBUG) {
				int arrsiz=dataarr4.length;
				for (int row=0; row<arrsiz; row++) {
					System.out.println("\t\t\tSurgProc: " + dataarr4[row]);
				}
			}
			
			dg.buildBreastSurgicalPathologySpecimen(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4);
			
			if (DEBUG) System.out.println("\tEnd Of testReadFile...");
			
		}

}




