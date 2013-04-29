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

public class NottinghamHistopathologicGradeInsertTest extends TestCase {

  private static int maxrecs = 500;
	  
  private static String inFile1 = "data\\TubuleForm.txt";
  private static String inFile2 = "data\\NuclearPleo.txt";
  private static String inFile3 = "data\\MitosisCnt.txt";
 
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public NottinghamHistopathologicGradeInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		//DataGenerator dg = new DataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//TubuleForm
 		dg.buildDataFile(inFile1,1,3);
		dataarr1=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tTubuleForm: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//NuclearPleo
		dg.buildDataFile(inFile2,1,3);
		dataarr2=dg.ReadFile(maxrecs,inFile2);

		if (DEBUG) {
			int arrsize=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tNuclearPleo: " + dataarr2[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		//MitosisCnt
		dg.buildDataFile(inFile3,1,3);
		dataarr3=dg.ReadFile(maxrecs,inFile3);
		int arrsiz=dataarr3.length;
		for (int row=0; row<arrsiz; row++) {
			System.out.println("\t\t\tMitosisCnt: " + dataarr3[row]);
		}
		
		dg.buildBreaseNottHistopathGrades(maxrecs,dataarr1,dataarr2,dataarr3);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(NottinghamHistopathologicGradeInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
}




