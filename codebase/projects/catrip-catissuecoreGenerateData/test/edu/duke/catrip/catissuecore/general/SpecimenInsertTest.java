/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class SpecimenInsertTest extends TestCase {
  
//Variable to determining how many recs to create, per table
	private static int maxrecs = 500;
	  
//INPUT FILES FOR POPULATING THE TABLES
//Specimen
	private static String inFile1 = "data\\SpecimenType.txt";
	private static String inFile2 = "data\\SpecimenQuantity.txt";
	private static String inFile3 = "data\\SpecimenQuantityAvail.txt";
	private static String inFile4 = "data\\SpecimenCmmt.txt";
	private static String inFile5 = "data\\SpecimenBarcode2.txt";
	private static String inFile6 = "data\\PosDimOne.txt";
	private static String inFile7 = "data\\PosDimTwo.txt";
	private static String newFile1 = "data\\SpecimenTypeV2.txt";
	private static String newFile2 = "data\\SpecimenQuantityV2.txt";
	private static String newFile3 = "data\\SpecimenQuantityAvailV2.txt";
	private static String newFile4 = "data\\SpecimenCmmtV2.txt";
	
//Specimen Chars
	private static String inFile8 = "data\\TissueSite.txt";
	private static String inFile9 = "data\\TissueSide.txt";
	private static String inFile10 = "data\\PathStatus.txt";
	private static String newFile8 = "data\\TissueSiteV2.txt";
	private static String newFile9 = "data\\TissueSideV2.txt";
	private static String newFile10 = "data\\PathStatusV2.txt";

//SpecimenCollectionGroup
	private static String inFile11 = "data\\ClinicalDiagnosis.txt";
	private static String inFile12 = "data\\ClinicalStatus.txt";
	private static String newFile11 = "data\\ClinicalDiagnosisV2.txt";
	private static String newFile12 = "data\\ClinicalStatusV2.txt";

	
//CollectionProtocolEvent
	private static String inFile13 = "data\\StudyCalEvtPt.txt";

//CollectionProtocol
	private static String inFile14 = "data\\SpecDescriptURL.txt";
	private static String inFile15 = "data\\dates.txt";
	private static String inFile16 = "data\\SpecProtEnrollment.txt";
	private static String inFile17 = "data\\IRBs.txt";
	private static String inFile18 = "data\\SpecProtSTitle.txt";
	private static String inFile19 = "data\\SpecProtTitle.txt";
	private static String newFile18 = "data\\SpecProtSTitleV2.txt";
	private static String newFile19 = "data\\SpecProtTitlV2.txt";
	private static String inFile20 = "data\\mrn.txt";

//Storage Container
	  private static String inFile24 = "data\\barcodes2.txt";
	  private static String inFile25 = "data\\StorgeContTemp.txt";	
	  private static String inFile26 = "data\\StorageContNum.txt";
	  
//Storage Container Capacity
	  private static String inFile27 = "data\\StorageDim.txt";

//Storage Type
	  private static String inFile32 = "data\\StorageType.txt";
	  private static String inFile33 = "data\\OneDimLab.txt";
	  private static String inFile34 = "data\\TwoDimLab.txt";
	  private static String inFile35 = "data\\DefaultTemp.txt";
	  private static String newFile32 = "data\\StorageTypeV2.txt";
	  private static String newFile33 = "data\\OneDimLabV2.txt";
	  private static String newFile34 = "data\\TwoDimLabV2.txt";
	  private static String newFile35 = "data\\DefaultTempV2.txt";

//DATA ARRAYS TO MANAGE THE INPUT DATA
//Specimen
	  public String[] dataarr = new String[500];
	  public String[] dataarr1 = new String[500];
	  public String[] dataarr2 = new String[500];
	  public String[] dataarr3 = new String[500];
	  public String[] dataarr4 = new String[500];
	  public String[] dataarr5 = new String[500];
	  public String[] dataarr6 = new String[500];
	  public String[] dataarr7 = new String[500];
//SpecimenChars
	  public String[] dataarr8 = new String[500];
	  public String[] dataarr9 = new String[500];
	  public String[] dataarr10 = new String[500];
//SpecimenCollectionGroup
	  public String[] dataarr11 = new String[500];
	  public String[] dataarr12 = new String[500];
//CollectionProtocolEvent
	  public String[] dataarr13 = new String[500];
	  public String[] dataarr14 = new String[500];
//CollectionProtocol
	  public String[] dataarr15 = new String[500];
	  public String[] dataarr16 = new String[500];
	  public String[] dataarr17 = new String[500];
	  public String[] dataarr18 = new String[500];
	  public String[] dataarr19 = new String[500];
	  public String[] dataarr20 = new String[500];
	  public String[] dataarr21 = new String[500];
//CollectionProtocolRegistration
	  public String[] dataarr22 = new String[500];
	  public String[] dataarr23 = new String[500];
//StorageContainer
	  public String[] dataarr27 = new String[500];
	  public String[] dataarr28 = new String[500];
	  public String[] dataarr29 = new String[500];
	  public String[] dataarr30 = new String[500];
	  public String[] dataarr31 = new String[500];
//StorageContainerCapacity
	  public String[] dataarr32 = new String[500];
	  public String[] dataarr33 = new String[500];
//StorageType
	  public String[] dataarr34 = new String[500];
	  public String[] dataarr35 = new String[500];
	  public String[] dataarr36 = new String[500];
	  public String[] dataarr37 = new String[500];
    
  public SpecimenInsertTest(String sTestName) {
      super(sTestName);
  }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = false;

		CATissueCoreDataGenerator dg = new CATissueCoreDataGenerator();
		
		if (DEBUG) System.out.println("Inside testReadFile()...");

		dg.buildTwoDataFiles(maxrecs,inFile1,newFile1,inFile4,newFile4);
		
	//Specimen
		//SPECIMEN TYPE
		dataarr1=dg.ReadFile(maxrecs,newFile1);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSPECIMEN TYPE: " + dataarr1[row]);
	       	}
		}
		
		dg.buildTwoDataFiles(maxrecs,inFile2,newFile2,inFile3,newFile3);
		
		//SPECIMEN QUANTITY
 		dataarr2=dg.ReadFile(maxrecs,newFile2);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSPECIMEN QUANTITY: " + dataarr2[row]);
	       	}
		}
		
		//SPECIMEN QUANTITY AVAIL
		dataarr3=dg.ReadFile(maxrecs,newFile3);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSPECIMEN QUANTITY AVAIL: " + dataarr3[row]);
	       	}
		}
		
		//SPECIMEN CMMT
 		dataarr4=dg.ReadFile(maxrecs,newFile4);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSPECIMEN CMMT: " + dataarr4[row]);
	       	}
		}

		//SPECIMEN BARCODE
		dataarr5=dg.ReadFile(maxrecs,inFile5);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tSPECIMEN BARCODE: " + dataarr5[row]);
	       	}
		}
		
		//PositionDimensionOne
		dg.buildDataFile(inFile6, 1, 24);
		dataarr6=dg.ReadFile(maxrecs,inFile6);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPositionDimensionOne: " + dataarr6[row]);
	       	}
		}

		//PositionDimensionTwo
		dg.buildDataFile(inFile7, 1, 24);
		dataarr7=dg.ReadFile(maxrecs,inFile7);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPositionDimensionTwo: " + dataarr7[row]);
	       	}
		}
		
	//SpecimenChar
		//TISSUE SITE
 		dg.buildDataFile(maxrecs,inFile8,newFile8);
		dataarr8=dg.ReadFile(maxrecs,newFile8);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tTISSUE SITE: " + dataarr8[row]);
	       	}
		}
	
		//TISSUE SIDE
		dg.buildDataFile(maxrecs,inFile9,newFile9);
 		dataarr9=dg.ReadFile(maxrecs,newFile9);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tTISSUE SIDE: " + dataarr9[row]);
	       	}
		}
		
		//PATH STAT
		dg.buildDataFile(maxrecs,inFile10,newFile10);
		dataarr10=dg.ReadFile(maxrecs,newFile10);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPATH STAT: " + dataarr10[row]);
	       	}
		}
		
	//SpecimenCollectionGroup
		//ClinicalDiag
		dg.buildDataFile(maxrecs,inFile11,newFile11);
		dataarr11=dg.ReadFile(maxrecs,newFile11);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPATH STAT: " + dataarr11[row]);
	       	}
		}
		
		//ClinicalStatus
		dg.buildDataFile(maxrecs,inFile12,newFile12);
		dataarr12=dg.ReadFile(maxrecs,newFile12);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tClinicalStatus: " + dataarr12[row]);
	       	}
		}	
		
	//CollectionProtocolEvent
		//Clinical Status
		dg.buildDataFile(maxrecs,inFile12,newFile12);
		dataarr13=dg.ReadFile(maxrecs,newFile12);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tClinicalStatus: " + dataarr13[row]);
	       	}
		}
		
		
		//StudyCalendarEventPoint
 		dg.buildDivByFiveFile(1000,10,200,inFile13);
		dataarr14=dg.ReadFile(maxrecs,inFile13);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tStudyCalendarEventPoint: " + dataarr13[row]);
	       	}
		}
		
	//CollectionProtocol
		//DescriptionURL
		dataarr15=dg.randomReadFile(maxrecs,inFile14);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tDescriptionURL: " + dataarr15[row]);
	       	}
		}
		
        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
		Calendar cal = new GregorianCalendar();
		
		//StartDate
		dataarr20=dg.randomReadFile(maxrecs,inFile15);
		
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tStartDate: " + dataarr20[row]);
	       	}
		}
		
		//EndDate - match to start date
		//So, get the start date and add 365 days 
		for (int row=0; row<dataarr20.length; row++) {
   	    	dataarr[row]=dataarr20[row];
       	}
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       			cal.setTime(sdf.parse(dataarr[row]));
       			cal.add(GregorianCalendar.DATE,365);
       	    	//dataarr16[row]=String.valueOf(cal.getTime());
       			dataarr16[row] = sdf.format(cal.getTime());
 
       	    	//dataarr16[row]=DateFormat.getInstance().format(cal.getTime());


       	    	System.out.println("\t\t\tEndDate2: " + dataarr16[row]);
	       	}
		}
		
		//Enrollment
		dg.buildDivByFiveFile(1000,100,500,inFile16);
		dataarr17=dg.ReadFile(maxrecs,inFile16);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tEnrollment: " + dataarr17[row]);
	       	}
		}
		
		//IrbIdentifier
		dataarr18=dg.randomReadFile(maxrecs,inFile17);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tIrbIdentifier: " + dataarr18[row]);
	       	}
		}
		
		//build title and shorttitle at the same time
		dg.buildTwoDataFiles(maxrecs,inFile18,newFile18,inFile19,newFile19);
		
		//ShortTitle
		dataarr19=dg.ReadFile(maxrecs,newFile18);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tShortTitle: " + dataarr19[row]);
	       	}
		}
				
		//Title
		dataarr21=dg.ReadFile(maxrecs,newFile19);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tTitle: " + dataarr21[row]);
	       	}
		}

	//CollectionProtocolRegistration
		//ProtocolParticipantIdentifier
		dataarr22=dg.randomReadFile(maxrecs,inFile20);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tProtocolParticipantIdentifier: " + dataarr22[row]);
	       	}
		}
		
		//RegistrationDate - match to start date
		for (int row=0; row<dataarr20.length; row++) {
   	    	dataarr23[row]=dataarr20[row];
       	}

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tRegistrationDate: " + dataarr23[row]);
	       	}
		}

	//StorageContainer
		//BARCODES
		dataarr27=dg.ReadFile(maxrecs,inFile24);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tBARCODES: " + dataarr27[row]);
	       	}
		}
		
		//Number
		dataarr28=dg.ReadFile(maxrecs,inFile26);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tStorageContainer#: " + dataarr28[row]);
	       	}
		}
		
		//PositionDimensionOne
		dataarr29=dg.ReadFile(maxrecs,inFile6);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPositionDimensionOne: " + dataarr29[row]);
	       	}
		}
		
		//PositionDimensionTwo
		dataarr30=dg.ReadFile(maxrecs,inFile7);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tPositionDimensionTwo: " + dataarr30[row]);
	       	}
		}
		
		//TempratureInCentigrade
		dataarr31=dg.ReadFile(maxrecs,inFile25);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tTemprature: " + dataarr30[row]);
	       	}
		}
		
	//StorageContainerCapacity 
		
 		dg.buildDivByFiveFile(1000,10,200,inFile27);
 		
 		//OneDimensionCapacity
		dataarr32=dg.ReadFile(maxrecs,inFile27);
		
		//TwoDimensionCapacity
		dataarr33=dg.ReadFile(maxrecs,inFile27);

		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tONE DIMENSION: " + dataarr32[row]);
       	    	System.out.println("\t\t\tTWO DIMENSION: " + dataarr33[row]);
	       	}
		}

	//StorageType  
		
		//keep type and temp files in sync
//		dg.buildTwoDataFiles(maxrecs,inFile32,newFile32,inFile35,newFile35);
		
		//DefaultTempratureInCentigrade 
//		dataarr34=dg.ReadFile(maxrecs,newFile35);
		dataarr34=dg.ReadFile(maxrecs,inFile35);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tDefaultTempratureInCentigrade: " + dataarr34[row]);
	       	}
		}
		
		//keep dimension label files in sync
		dg.buildTwoDataFiles(maxrecs,inFile33,newFile33,inFile34,newFile34);
		
		//OneDimensionLabel 
 		dataarr35=dg.ReadFile(maxrecs,newFile33);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tOneDimLab: " + dataarr35[row]);
	       	}
		}
		
		//TwoDimensionLabel 
		dataarr36=dg.ReadFile(maxrecs,newFile34);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tTwoDimLab: " + dataarr36[row]);
	       	}
		}
		
		//Type 
//		dataarr37=dg.ReadFile(maxrecs,newFile32);
		dataarr37=dg.ReadFile(maxrecs,inFile32);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tStorage Type: " + dataarr37[row]);
	       	}
		}
		
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
		//Call the buildSpecimen method to actually insert the data contained in the arrays
		dg.buildSpecimen(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7,dataarr8,dataarr9,dataarr10,dataarr11,dataarr12,dataarr13,dataarr14,dataarr15,dataarr16,dataarr17,dataarr18,dataarr19,dataarr20,dataarr21,dataarr22,dataarr23,dataarr27,dataarr28,dataarr29,dataarr30,dataarr31,dataarr32,dataarr33,dataarr34,dataarr35,dataarr36,dataarr37);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("\tInside main...");
		TestRunner runner = new TestRunner();
		TestResult result = runner.doRun(new TestSuite(SpecimenInsertTest.class));
		System.exit(result.errorCount() + result.failureCount());
	}
	
}




