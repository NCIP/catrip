/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SiteInsertTest extends TestCase {
	
  private static int maxrecs = 5;
	
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSite.txt";	
  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSiteV2.txt";

  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSiteType.txt";
  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSiteTypeV2.txt";

  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\EmailAddress.txt";

  public String[] dataarr1 = new String[1000];
  public String[] dataarr2 = new String[1000];
  public String[] dataarr3 = new String[1000];
    
  public SiteInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(SiteInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		//Site
 		dg.buildDataFile(maxrecs,inFile1,newFile1);
		dataarr1=dg.randomReadFile(maxrecs,newFile1);

		if (DEBUG) {
			int arrsiz=dataarr1.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tSITE: " + dataarr1[row]);
	       	}
		}
		
		//Site Type
 		dg.buildDataFile(maxrecs,inFile2,newFile2);
		dataarr2=dg.randomReadFile(maxrecs,newFile2);

		if (DEBUG) {
			int arrsiz=dataarr2.length;
	        //print fields
	        for (int row=0; row<arrsiz; row++) {
       	    	System.out.println("\t\t\tSITE TYPE: " + dataarr2[row]);
	       	}
		}
		
		//EMAIL
		dataarr3=dg.ReadFile(maxrecs,inFile3);

		if (DEBUG) {
			int arrsize=dataarr3.length;
	        //print fields
	        for (int row=0; row<arrsize; row++) {
       	    	System.out.println("\t\t\tEMAIL: " + dataarr3[row]);
	       	}
		}
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");

		dg.buildSite(maxrecs,dataarr1,dataarr2,dataarr3);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




