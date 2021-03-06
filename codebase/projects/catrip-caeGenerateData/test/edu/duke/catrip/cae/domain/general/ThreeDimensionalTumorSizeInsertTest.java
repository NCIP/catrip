/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.general;

//import junit.awtui.TestRunner;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class ThreeDimensionalTumorSizeInsertTest extends TestCase {
  
	  private static String inFile1 = "data\\GreatestDim.txt";
	  private static String inFile2 = "data\\DimY.txt";
	  private static String inFile3 = "data\\DimX.txt";
	  private static String newFile1 = "data\\GreatestDimV2.txt";
	  private static String newFile2 = "data\\DimYV2.txt";
	  private static String newFile3 = "data\\DimXV2.txt";
	 
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  private static int maxrecs = 500;
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public ThreeDimensionalTumorSizeInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = false;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//Greatest Dim
 		dg.buildDataFile(maxrecs,inFile1,newFile1);
		dataarr1=dg.ReadFile(maxrecs,newFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tGREATEST_DIMENSION: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//Dim Y
 		dg.buildDataFile(maxrecs,inFile2,newFile2);
		dataarr2=dg.ReadFile(maxrecs,newFile2);

		if (DEBUG) {
			int arrsize=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tDIMENSION Y: " + dataarr2[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//Dim X
 		dg.buildDataFile(maxrecs,inFile3,newFile3);
		dataarr3=dg.ReadFile(maxrecs,newFile3);
		
		int arrsiz=dataarr3.length;
		for (int row=0; row<arrsiz; row++) {
			System.out.println("\t\t\tDIMENSION X: " + dataarr3[row]);
		}
		
		dg.buildThreeDimensionalTumorSize(maxrecs,dataarr1,dataarr2,dataarr3);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(ThreeDimensionalTumorSizeInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




