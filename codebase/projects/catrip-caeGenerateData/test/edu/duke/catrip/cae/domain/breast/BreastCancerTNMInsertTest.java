/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

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

public class BreastCancerTNMInsertTest extends TestCase {
  private static int maxrecs = 500;
  private static String inFile1 = "data\\T_Tumor.txt";
  private static String inFile2 = "data\\N_Nodes.txt";
  private static String inFile3 = "data\\M_Metastasis.txt";
  private static String inFile4 = "data\\NodesExamined.txt";
  private static String inFile5 = "data\\NodesInvolved.txt";
  private static String inFile6 = "data\\sites.txt";
  private static String newFile6 = "data\\sitesV2.txt";
  
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  public String[] dataarr4 = new String[1000];
  public String[] dataarr5 = new String[1000];
  public String[] dataarr6 = new String[1000];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public BreastCancerTNMInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//T
 		dg.buildDataFile(inFile1,0,4);
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tTubuleForm: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//N
		dg.buildDataFile(inFile2,0,3);
		dataarr2=dg.ReadFile(maxrecs,inFile2);

		if (DEBUG) {
			int arrsize=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tNuclearPleo: " + dataarr2[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//M
		dg.buildDataFile(inFile3,0,1);
		dataarr3=dg.ReadFile(maxrecs,inFile3);
		
		if (DEBUG) {
			int arrsiz=dataarr3.length;
			for (int row=0; row<arrsiz; row++) {
				System.out.println("\t\t\tMitosisCnt: " + dataarr3[row]);
			}
		}
			
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
		//Nodes examined
		dg.buildDataFile(inFile4,1,20);
		dataarr4=dg.ReadFile(maxrecs,inFile4);
		if (DEBUG) {
			int arrsiz=dataarr4.length;
			for (int row=0; row<arrsiz; row++) {
				System.out.println("\t\t\tNodes Involved: " + dataarr4[row]);
			}
		}
		
		//Nodes involved
		dg.buildDataFile(inFile5,1,5);
		dataarr5=dg.ReadFile(maxrecs,inFile5);
		if (DEBUG) {
			int arrsiz=dataarr5.length;
			for (int row=0; row<arrsiz; row++) {
				System.out.println("\t\t\tNodes involved: " + dataarr5[row]);
			}
		}
		
		//Sites
 		dg.buildDataFile(maxrecs,inFile6,newFile6);
		dataarr6=dg.ReadFile(maxrecs,newFile6);
		
		int arrsiz=dataarr6.length;
		for (int row=0; row<arrsiz; row++) {
			System.out.println("\t\t\tSites: " + dataarr6[row]);
		}
		
		dg.buildBreastCancerTNM(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(BreastCancerTNMInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




