/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.pitt.cabig.cae.domain.breast;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;

public class NottinghamHistopathologicGradeInsertTest extends TestCase {

  private static int maxrecs = 500;
	  
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\TubuleForm.txt";
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\NuclearPleo.txt";
  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-GenerateData\\data\\MitosisCnt.txt";
 
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public NottinghamHistopathologicGradeInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(NottinghamHistopathologicGradeInsertTest.class);
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

	
}




