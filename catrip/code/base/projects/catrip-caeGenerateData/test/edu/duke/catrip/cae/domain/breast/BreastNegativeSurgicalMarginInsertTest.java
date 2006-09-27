package edu.duke.catrip.cae.domain.breast;
//InvasiveBreastCarcinoma.txt

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;


public class BreastNegativeSurgicalMarginInsertTest extends TestCase {
	
	  private static int maxrecs = 500;
	  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\ClosestNeoplasm.txt";
	  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\ClosestNeoplasmV2.txt";
	  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\DistToClosestNeoplasm.txt";
	  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\SpatialRelationshipToPatient.txt";
	  private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\SpatialRelationshipToPatientV2.txt";
	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  public String[] dataarr3 = new String[1000];
	  
	  CAEDataGenerator dg = new CAEDataGenerator();

	  public BreastNegativeSurgicalMarginInsertTest(String sTestName) {
	      super(sTestName);
	  }

		   public void setUp() {

		   }

		   public void tearDown() {
		   }


		   public static Test suite() {
		       return new TestSuite(BreastNegativeSurgicalMarginInsertTest.class);
		   }

		   //test reading data files into an array and insert into db
			public void testRead_Insert() throws Exception {

				final boolean DEBUG = true;

				//DataGenerator dg = new DataGenerator();
				
				if (DEBUG) System.out.println("Inside testReadFile()...");

				//ClosestNeoplasm
		 		dg.buildDataFile(maxrecs,inFile1,newFile1);
				dataarr1=dg.ReadFile(maxrecs,newFile1);

				if (DEBUG) {
					int arrsize=dataarr1.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\tClosestNeoplasm: " + dataarr1[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
				//DistToClosestNeoplasm
		 		dg.buildDataFile(inFile2,1,10);
				dataarr2=dg.ReadFile(maxrecs,inFile2);
				if (DEBUG) {
					int arrsize=dataarr2.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\tDistToClosestNeoplasm: " + dataarr2[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");

				//SpatialRelationship
		 		dg.buildDataFile(maxrecs,inFile3,newFile3);
				dataarr3=dg.ReadFile(maxrecs,newFile3);

				if (DEBUG) {
					int arrsize=dataarr3.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\tSpatialRelationship: " + dataarr3[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
				dg.buildBreastNegativeSurgicalMargin(maxrecs,dataarr1,dataarr2,dataarr3);
				
				if (DEBUG) System.out.println("\tEnd Of testReadFile...");
				
			}
}




