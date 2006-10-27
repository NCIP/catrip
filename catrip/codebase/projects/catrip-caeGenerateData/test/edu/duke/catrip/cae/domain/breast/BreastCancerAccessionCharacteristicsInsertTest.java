/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.breast;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class BreastCancerAccessionCharacteristicsInsertTest extends TestCase {
  private static int maxrecs = 500;
  private static String inFile1 = "data\\OthProc.txt";
  private static String inFile2 = "data\\SurgProc.txt";
                                
  public String[] dataarr1 = new String[maxrecs];
  public String[] dataarr2 = new String[maxrecs];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public BreastCancerAccessionCharacteristicsInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//Oth Surg Proc
 		dataarr1=dg.ReadFile(maxrecs,inFile1);
		
		if (DEBUG){
			for (int row=0; row<dataarr1.length; row++) {
				System.out.println("\t\t\tOthSurgProc: " + dataarr1[row]);
			}
		}
		
		//Surg Proc
 		dataarr2=dg.ReadFile(maxrecs,inFile2);
		
		if (DEBUG){
			for (int row=0; row<dataarr2.length; row++) {
				System.out.println("\t\t\tSurgProc: " + dataarr2[row]);
			}
		}
		
		dg.buildBreastCancerAccessionCharacteristics(maxrecs,dataarr1,dataarr2);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(BreastCancerAccessionCharacteristicsInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




