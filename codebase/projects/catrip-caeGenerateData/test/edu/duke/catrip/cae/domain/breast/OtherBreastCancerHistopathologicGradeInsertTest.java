/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.catrip.cae.domain.breast;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;

public class OtherBreastCancerHistopathologicGradeInsertTest extends TestCase {
	
	  private static int maxrecs = 500;
	  private static String inFile1 = "data\\OthHisPathGradeSystemNam.txt";
	  private static String newFile1 = "data\\OthHisPathGradeSystemNamV2.txt";
	  private static String inFile2 = "data\\OthHisPathGradeMitoticCnt.txt";

	  public String[] dataarr1 = new String[1000];
	  public String[] dataarr2 = new String[1000];
	  
	  CAEDataGenerator dg = new CAEDataGenerator();

	  public OtherBreastCancerHistopathologicGradeInsertTest(String sTestName) {
	      super(sTestName);
	  }

	   //test reading data files into an array and insert into db
		public void testRead_Insert() throws Exception {

			final boolean DEBUG = true;
			
			if (DEBUG) System.out.println("Inside testReadFile()...");

			//System Name
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
			
			//MitoticCnt
			dg.buildDataFile(inFile2,1,20);
			dataarr2=dg.ReadFile(maxrecs,inFile2);

			if (DEBUG) {
				int arrsize=dataarr2.length;
		        //print fields
		        for (int row=0; row<arrsize; row++) {
	       	    	System.out.println("\t\t\tMC Location: " + dataarr2[row]);
		       	}
			}
			
			if (DEBUG) System.out.println("\tBack Inside testReadFile()");
			
			dg.buildOtherBreastCancerHistopathologicGrade(maxrecs,dataarr1,dataarr2);
			
			if (DEBUG) System.out.println("\tEnd Of testReadFile...");
			
		}
		
		public static void main(String[] args) throws Exception
		{
			System.out.println("\tInside main...");
			TestRunner runner = new TestRunner();
			TestResult result = runner.doRun(new TestSuite(OtherBreastCancerHistopathologicGradeInsertTest.class));
			System.exit(result.errorCount() + result.failureCount());
		}
}




