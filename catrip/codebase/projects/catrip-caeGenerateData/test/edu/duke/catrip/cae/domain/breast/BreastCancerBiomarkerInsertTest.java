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

public class BreastCancerBiomarkerInsertTest extends TestCase {
  private static int maxrecs = 500;
  private static String inFile1 = "data\\ER.txt";
  private static String inFile2 = "data\\PR.txt";
  private static String inFile3 = "data\\HER2status.txt";
  private static String inFile4 = "data\\HER2testype.txt";
  private static String inFile5 = "data\\EGFRstatus.txt";
  private static String newFile1 = "data\\ERv2.txt";
  private static String newFile2 = "data\\PRv2.txt";
  private static String newFile3 = "data\\HER2statusV2.txt";
  private static String newFile4 = "data\\HER2testypeV2.txt";
  private static String newFile5 = "data\\EGFRstatusV2.txt";
                                
  public String[] dataarr1 = new String[maxrecs];
  public String[] dataarr2 = new String[maxrecs];
  public String[] dataarr3 = new String[maxrecs];
  public String[] dataarr4 = new String[maxrecs];
  public String[] dataarr5 = new String[maxrecs];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public BreastCancerBiomarkerInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//ER
 		dg.buildDataFile(maxrecs,inFile1,newFile1);
 		dataarr1=dg.ReadFile(maxrecs,newFile1);
		
		if (DEBUG){
			for (int row=0; row<dataarr1.length; row++) {
				System.out.println("\t\t\tER: " + dataarr1[row]);
			}
		}
		
		//PR
 		dg.buildDataFile(maxrecs,inFile2,newFile2);
 		dataarr2=dg.ReadFile(maxrecs,newFile2);
		
		if (DEBUG){
			for (int row=0; row<dataarr2.length; row++) {
				System.out.println("\t\t\tPR: " + dataarr2[row]);
			}
		}
		
		//HER2status
 		dg.buildDataFile(maxrecs,inFile3,newFile3);
 		dataarr3=dg.ReadFile(maxrecs,newFile3);
		
		if (DEBUG){
			for (int row=0; row<dataarr3.length; row++) {
				System.out.println("\t\t\tHER2status: " + dataarr3[row]);
			}
		}
		
		//HER2testtype
 		dg.buildDataFile(maxrecs,inFile4,newFile4);
 		dataarr4=dg.ReadFile(maxrecs,newFile4);
		
		if (DEBUG){
			for (int row=0; row<dataarr4.length; row++) {
				System.out.println("\t\t\tHER2testtype: " + dataarr4[row]);
			}
		}
		
		//EGFRstatus
 		dg.buildDataFile(maxrecs,inFile5,newFile5);
 		dataarr5=dg.ReadFile(maxrecs,newFile5);
		
		if (DEBUG){
			for (int row=0; row<dataarr5.length; row++) {
				System.out.println("\t\t\tEGFRstatus: " + dataarr5[row]);
			}
		}
		
		dg.buildBreastCancerBiomarkers(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(BreastCancerBiomarkerInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




