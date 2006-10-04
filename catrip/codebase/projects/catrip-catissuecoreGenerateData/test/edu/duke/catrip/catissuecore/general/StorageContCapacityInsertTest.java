/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class StorageContCapacityInsertTest extends TestCase {
  
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageDim.txt";
 
  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  private static int maxrecs = 5;
  
    
  public StorageContCapacityInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(StorageContCapacityInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//One Dim
 		dg.buildDivByFiveFile(1000,10,200,inFile1);
		dataarr1=dg.ReadFile(maxrecs,inFile1);
		dataarr2=dg.ReadFile(maxrecs,inFile1);

		if (DEBUG) {
			int arrsize=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tONE DIMENSION: " + dataarr1[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		dg.buildStorageContCapacity(maxrecs,dataarr1,dataarr2);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




