/**
 * Created on Aug 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import edu.duke.catrip.catissuecore.general.CATissueCoreDataGenerator;
//import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;

public class SpecimenInsertTest extends TestCase {
  
//	Specimen Chars
	
	private static String inFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenType.txt";
	private static String inFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantity.txt";
	private static String inFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantityAvail.txt";
	private static String inFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenCmmt.txt";
	private static String inFile5 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenBarcode.txt";
	private static String inFile6 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PosDimOne.txt";
	private static String inFile7 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PosDimTwo.txt";
	private static String newFile1 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenTypeV2.txt";
	private static String newFile2 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantityV2.txt";
	private static String newFile3 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenQuantityAvailV2.txt";
	private static String newFile4 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecimenCmmtV2.txt";
	private static String newFile6 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PosDimOneV2.txt";
	private static String newFile7 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PosDimTwoV2.txt";

//Specimen Chars
	private static String inFile8 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TissueSite.txt";
	private static String inFile9 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TissueSide.txt";
	private static String inFile10 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PathStatus.txt";
	private static String newFile8 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TissueSiteV2.txt";
	private static String newFile9 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TissueSideV2.txt";
	private static String newFile10 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\PathStatusV2.txt";

//SpecimenCollectionGroup
	private static String inFile11 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ClinicalDiagnosis.txt";
	private static String inFile12 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ClinicalStatus.txt";
	private static String newFile11 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ClinicalDiagnosisV2.txt";
	private static String newFile12 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\ClinicalStatusV2.txt";

	
//CollectionProtocolEvent
	private static String inFile13 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StudyCalEvtPt.txt";

//CollectionProtocol
	private static String inFile14 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecDescriptURL.txt";
	private static String inFile15 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\dates.txt";
	private static String inFile16 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecProtEnrollment.txt";
	private static String inFile17 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\IRBs.txt";
	private static String inFile18 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecProtSTitle.txt";
	private static String inFile19 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecProtTitle.txt";
	private static String newFile18 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecProtSTitleV2.txt";
	private static String newFile19 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\SpecProtTitlV2.txt";
	private static String inFile20 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\mrn.txt";

////Site
//	  private static String inFile21 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSite.txt";	
//	  private static String newFile21 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSiteV2.txt";
//	  private static String inFile22 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSiteType.txt";
//	  private static String newFile22 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\CollectionSiteTypeV2.txt";
//	  private static String inFile23 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\EmailAddress.txt";

//Storage Container
	  private static String inFile24 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\barcodes.txt";
	  private static String inFile25 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorgeContTemp.txt";	
	  private static String newFile25 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorgeContTempV2.txt";
	  private static String inFile26 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageContNum.txt";
	  
//Storage Container Capacity
	  private static String inFile27 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageDim.txt";

//Storage Type
	  private static String inFile32 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageType.txt";
	  private static String inFile33 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\OneDimLab.txt";
	  private static String inFile34 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TwoDimLab.txt";
	  private static String inFile35 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\DefaultTemp.txt";
	  private static String newFile32 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\StorageTypeV2.txt";
	  private static String newFile33 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\OneDimLabV2.txt";
	  private static String newFile34 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\TwoDimLabV2.txt";
	  private static String newFile35 = "C:\\caTRIP\\catrip\\codebase\\projects\\catrip-catissuecoreGenerateData\\data\\DefaultTempV2.txt";

	public String[] dataarr = new String[500];
	  public String[] dataarr1 = new String[500];
	  public String[] dataarr2 = new String[500];
	  public String[] dataarr3 = new String[500];
	  public String[] dataarr4 = new String[500];
	  public String[] dataarr5 = new String[500];
	  public String[] dataarr6 = new String[500];
	  public String[] dataarr7 = new String[500];
	  public String[] dataarr8 = new String[500];
	  public String[] dataarr9 = new String[500];
	  public String[] dataarr10 = new String[500];
	  public String[] dataarr11 = new String[500];
	  public String[] dataarr12 = new String[500];
	  public String[] dataarr13 = new String[500];
	  public String[] dataarr14 = new String[500];
	  public String[] dataarr15 = new String[500];
	  public String[] dataarr16 = new String[500];
	  public String[] dataarr17 = new String[500];
	  public String[] dataarr18 = new String[500];
	  public String[] dataarr19 = new String[500];
	  public String[] dataarr20 = new String[500];
	  public String[] dataarr21 = new String[500];
	  public String[] dataarr22 = new String[500];
	  public String[] dataarr23 = new String[500];
//SITE
//	  public String[] dataarr24 = new String[500];
//	  public String[] dataarr25 = new String[500];
//	  public String[] dataarr26 = new String[500];
	  public String[] dataarr27 = new String[500];
	  public String[] dataarr28 = new String[500];
	  public String[] dataarr29 = new String[500];
	  public String[] dataarr30 = new String[500];
	  public String[] dataarr31 = new String[500];
	  public String[] dataarr32 = new String[500];
	  public String[] dataarr33 = new String[500];
	  public String[] dataarr34 = new String[500];
	  public String[] dataarr35 = new String[500];
	  public String[] dataarr36 = new String[500];
	  public String[] dataarr37 = new String[500];
  private static int maxrecs = 5;
  
    
  public SpecimenInsertTest(String sTestName) {
      super(sTestName);
  }

   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(SpecimenInsertTest.class);
   }

   //test reading data files into an array and insert into db
	public void testRead_Insert() throws Exception {

		final boolean DEBUG = true;

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
		
	//CollectionProtocol (extends SpecimenProtocol)
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
		

//	//Site
//		dg.buildTwoDataFiles(maxrecs,inFile21,newFile21,inFile22,newFile22);
//		//SITE
//		dataarr24=dg.ReadFile(maxrecs,newFile21);
//		if (DEBUG) {
//			for (int row=0; row<maxrecs; row++) {
//       	    	System.out.println("\t\t\tSITE: " + dataarr24[row]);
//	       	}
//		}
//		//SITE TYPE
//		dataarr25=dg.ReadFile(maxrecs,newFile22);
//		if (DEBUG) {
//			for (int row=0; row<maxrecs; row++) {
//       	    	System.out.println("\t\t\tSITE TYPE: " + dataarr25[row]);
//	       	}
//		}
//		
//		//EMAIL
//		dataarr26=dg.ReadFile(maxrecs,inFile23);
//		if (DEBUG) {
//			for (int row=0; row<maxrecs; row++) {
//       	    	System.out.println("\t\t\tEMAIL: " + dataarr26[row]);
//	       	}
//		}

	//StorageContainer
		//BARCODES
		dataarr27=dg.ReadFile(maxrecs,inFile24);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tBARCODES: " + dataarr27[row]);
	       	}
		}
		
		//Number
		dataarr28=dg.randomReadFile(maxrecs,inFile26);
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
		dg.buildTwoDataFiles(maxrecs,inFile32,newFile32,inFile35,newFile35);
		
		//DefaultTempratureInCentigrade 
		dataarr34=dg.ReadFile(maxrecs,newFile35);
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
		dataarr37=dg.ReadFile(maxrecs,newFile32);
		if (DEBUG) {
			for (int row=0; row<maxrecs; row++) {
       	    	System.out.println("\t\t\tStorage Type: " + dataarr37[row]);
	       	}
		}
		
		
		if (DEBUG) System.out.println("\tBack Inside testReadFile()");
		
//		dg.buildSpecimen(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7,dataarr8,dataarr9,dataarr10,dataarr11,dataarr12,dataarr13,dataarr14,dataarr15,dataarr16,dataarr17,dataarr18,dataarr19,dataarr20,dataarr21,dataarr22,dataarr23,dataarr24,dataarr25,dataarr26,dataarr27,dataarr28,dataarr29,dataarr30,dataarr31,dataarr32,dataarr33,dataarr34,dataarr35,dataarr36,dataarr37);
		dg.buildSpecimen(maxrecs,dataarr1,dataarr2,dataarr3,dataarr4,dataarr5,dataarr6,dataarr7,dataarr8,dataarr9,dataarr10,dataarr11,dataarr12,dataarr13,dataarr14,dataarr15,dataarr16,dataarr17,dataarr18,dataarr19,dataarr20,dataarr21,dataarr22,dataarr23,dataarr27,dataarr28,dataarr29,dataarr30,dataarr31,dataarr32,dataarr33,dataarr34,dataarr35,dataarr36,dataarr37);
		
		if (DEBUG) System.out.println("\tEnd Of testReadFile...");
		
	}

	
}




