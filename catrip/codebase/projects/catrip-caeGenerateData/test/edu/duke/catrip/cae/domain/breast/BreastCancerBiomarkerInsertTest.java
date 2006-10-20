/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.cae.domain.breast;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.cae.domain.general.CAEDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class BreastCancerBiomarkerInsertTest extends TestCase {
  private static int maxrecs = 500;
  private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\ER.txt";
  private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\PR.txt";
  private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\HER2status.txt";
  private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\HER2testype.txt";
  private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\EGFRstatus.txt";
  private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\ERv2.txt";
  private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\PRv2.txt";
  private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\HER2statusV2.txt";
  private static String newFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\HER2testypeV2.txt";
  private static String newFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-caeGenerateData\\data\\EGFRstatusV2.txt";
                                
  public String[] dataarr1 = new String[maxrecs];
  public String[] dataarr2 = new String[maxrecs];
  public String[] dataarr3 = new String[maxrecs];
  public String[] dataarr4 = new String[maxrecs];
  public String[] dataarr5 = new String[maxrecs];
  
  CAEDataGenerator dg = new CAEDataGenerator();
    
  public BreastCancerBiomarkerInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(BreastCancerBiomarkerInsertTest.class);
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

	
}




