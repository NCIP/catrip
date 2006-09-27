package edu.duke.catrip.cae.domain.breast;
//InvasiveBreastCarcinoma.txt

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;


public class BreastPositiveSurgicalMarginInsertTest extends TestCase {
	
	  private static int maxrecs = 500;
	  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\neoplasmPresent.txt";
	  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\neoplasmPresentV2.txt";
	  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\extentInvolvement.txt";
	  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\extentInvolvementV2.txt";
	  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\otherextentInvolvement.txt";
	  private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\otherextentInvolvementV2.txt";
	  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\SpatialRelationshipToPatient.txt";
	  private static String newFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\SpatialRelationshipToPatientV2.txt";

	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  public String[] dataarr3 = new String[1000];
	  public String[] dataarr4 = new String[1000];
	  
	  CAEDataGenerator dg = new CAEDataGenerator();

	  public BreastPositiveSurgicalMarginInsertTest(String sTestName) {
	      super(sTestName);
	  }

		   public void setUp() {

		   }

		   public void tearDown() {
		   }


		   public static Test suite() {
		       return new TestSuite(BreastPositiveSurgicalMarginInsertTest.class);
		   }

		   //test reading data files into an array and insert into db
			public void testRead_Insert() throws Exception {

				final boolean DEBUG = true;

				//DataGenerator dg = new DataGenerator();
				
				if (DEBUG) System.out.println("Inside testReadFile()...");

				//neoplasmPresent
		 		dg.buildDataFile(maxrecs,inFile1,newFile1);
				dataarr1=dg.ReadFile(maxrecs,newFile1);

				if (DEBUG) {
					int arrsize=dataarr1.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\tneoplasmPresent: " + dataarr1[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
				//extentInvolvement
		 		dg.buildDataFile(maxrecs,inFile2,newFile2);
				dataarr2=dg.ReadFile(maxrecs,newFile2);
				if (DEBUG) {
					int arrsize=dataarr2.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\textentInvolvement: " + dataarr2[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");

				//otherextentInvolvement
		 		dg.buildDataFile(maxrecs,inFile3,newFile3);
				dataarr3=dg.ReadFile(maxrecs,newFile3);

				if (DEBUG) {
					int arrsize=dataarr3.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\totherextentInvolvement: " + dataarr3[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
				//SpatialRelationship
		 		dg.buildDataFile(maxrecs,inFile4,newFile4);
				dataarr4=dg.ReadFile(maxrecs,newFile4);

				if (DEBUG) {
					int arrsize=dataarr4.length;
			        //print fields
			        for (int row=0; row<arrsize; row++) {
		       	    	System.out.println("\t\t\tSpatialRelationship: " + dataarr4[row]);
			       	}
				}
				
				if (DEBUG) System.out.println("\tBack Inside testReadFile()");
				
				dg.buildBreastPositiveSurgicalMargin(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4);
				
				if (DEBUG) System.out.println("\tEnd Of testReadFile...");
				
			}
}




