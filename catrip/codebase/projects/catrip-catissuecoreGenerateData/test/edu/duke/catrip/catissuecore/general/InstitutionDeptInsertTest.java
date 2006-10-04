/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class InstitutionDeptInsertTest extends TestCase {
	
  private static int maxrecs = 5;
	
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CancerInstitutes.txt";							
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CancerDepts.txt";
   
  public String[][] dataarr = new String[1000][2];
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
    
  public InstitutionDeptInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(InstitutionDeptInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		dataarr=dg.randomReadFile(maxrecs,inFile1,inFile2);
        for (int row=0; row<maxrecs; row++) {
        	//INSTITUTIONS
   	    	dataarr1[row]=dataarr[row][0];
   	    	//DEPARTMENTS
   	    	dataarr2[row]=dataarr[row][1];
       	}
        
		//INSTITUTIONS
		//dataarr1=dg.randomReadFile(maxrecs,inFile1);

		if (DEBUG) {
			//print fields
	        for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tINST: " + dataarr1[row]);
       	    	System.out.println("\t\t\tDEPT: " + dataarr2[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		dg.buildInstitutionDept(maxrecs,dataarr1,dataarr2);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




