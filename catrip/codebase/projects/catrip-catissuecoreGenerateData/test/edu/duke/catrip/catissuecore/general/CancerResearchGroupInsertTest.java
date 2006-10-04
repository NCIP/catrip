/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CancerResearchGroupInsertTest extends TestCase {
	
  private static int maxrecs = 5;
	
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ResearchGroup.txt";	

  public String[] dataarr1 = new String[1000];
    
  public CancerResearchGroupInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(CancerResearchGroupInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		dataarr1=dg.randomReadFile(maxrecs,inFile1);

		if (DEBUG) {
			int arrsiz=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tGROUP: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		dg.buildCancerResearchGroup(maxrecs,dataarr1);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




