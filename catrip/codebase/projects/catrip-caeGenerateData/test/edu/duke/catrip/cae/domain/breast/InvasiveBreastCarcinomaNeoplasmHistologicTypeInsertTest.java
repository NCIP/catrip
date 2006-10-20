package edu.duke.catrip.cae.domain.breast;
//InvasiveBreastCarcinoma.txt

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;


public class InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest extends TestCase {
	
	  private static int maxrecs = 500;
	  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\NeoplasmHistologicType.txt";
	  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\NeoplasmHistologicTypeV2.txt";
	  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\otherNeoplasmHistologicType.txt";
	  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\otherNeoplasmHistologicTypeV2.txt";

	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  
	  CAEDataGenerator dg = new CAEDataGenerator();

	  public InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest(String sTestName) {
	      super(sTestName);
	  }

		   public void setUp() {

		   }

		   public void tearDown() {
		   }


		   public static Test suite() {
		       return new TestSuite(InvasiveBreastCarcinomaNeoplasmHistologicTypeInsertTest.class);
		   }

		   //test reading data files into an array and insert into db
			public void testRead_Insert() throws Exception {

				final boolean DEBUG = true;

				//DataGenerator dg = new DataGenerator();
				
				if (DEBUG) System.out.println("Inside testReadFile()...");

				//NeoplasmHistologicType 
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
				
				//otherNeoplasmHistologicType
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
			
				dg.buildInvasiveBreastCarcinomaNeoplasmHistologicType(maxrecs,dataarr1,dataarr2);
				
				if (DEBUG) System.out.println("\tEnd Of testReadFile...");
				
			}
}




