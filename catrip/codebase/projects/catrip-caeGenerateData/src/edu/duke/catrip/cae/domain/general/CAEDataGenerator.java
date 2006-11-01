/**
 * Created on Aug 24, 2006 by PEEDI002
 */
package edu.duke.catrip.cae.domain.general;

//my import statements
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//import edu.duke.catrip.cae.util.HibernateUtil;
import edu.duke.catrip.datagenerator.DataGeneratorToolKit;
import edu.duke.catrip.datagenerator.HibernateUtil;
import edu.pitt.cabig.cae.domain.breast.BreastCancerAccessionCharacteristics;
import edu.pitt.cabig.cae.domain.breast.BreastCancerBiomarkers;
import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFinding;
import edu.pitt.cabig.cae.domain.breast.BreastSpecimenCharacteristics;
import edu.pitt.cabig.cae.domain.breast.InvasiveBreastCarcinoma;
import edu.pitt.cabig.cae.domain.breast.InvasiveBreastCarcinomaNeoplasmHistologicType;
import edu.pitt.cabig.cae.domain.breast.NottinghamHistopathologicGrade;
import edu.pitt.cabig.cae.domain.breast.OtherBreastCancerHistopathologicGrade;
import edu.pitt.cabig.cae.domain.general.AnnotatableEntity;
import edu.pitt.cabig.cae.domain.general.AnnotationEventParameters;
import edu.pitt.cabig.cae.domain.general.AnnotationSet;
import edu.pitt.cabig.cae.domain.general.EventParameters;
import edu.pitt.cabig.cae.domain.general.ThreeDimensionalTumorSize;

public class CAEDataGenerator extends DataGeneratorToolKit
{
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	/* ORACLE:
	 * gets the user tables
	 * select tname from tab where tabtype='TABLE' and tname not like 'BIN$%';
	 *
	 * gets the columns for a table
	 * SELECT TNAME,CNAME,COLTYPE,WIDTH FROM COL where tname='PARTICIPANT';
	 */

	/**
     * http://dev.mysql.com/downloads/connector/j/5.0.html
     */
	public static final String MySQL_DRIVER = "com.mysql.jdbc.Driver";
	/* MySQL:
	 * gets the user tables
	 * 		show tables;
	 * gets the columns for a table
	 *  	show columns from catissue_participant;
	 */
	
	//public static final String MSSQL_DRIVER = "lib.com.mircsoft.jdbc.sqlserver.SQLServerDriver";
	/* MS SQL:
	 * gets the user tables
	 *select @tablename=so.name from sysobjects  as so where so.type = 'U'
	 * gets the columns for a user table
	 * note: type values (38=int, 39=string, 48=boolean, 52=smallint, 111=date)
	 *  select sc.name,sc.type,sc.length from sysobjects as so 
	 *		inner join syscolumns as sc on so.id = sc.id 
	 *		where so.name = @tablename
	 * 		order by sc.type
	 */
	
	/**
	 * Whether to print out debug messages
	 */
	public static final boolean DEBUG = true;
	

	/**
	 * Constructor 
	 */
	public CAEDataGenerator()
	{
	super();
	
	if (DEBUG) System.out.println("\tInside Constructor: DataGenerator()");

	}


//Builds the Participant, Accession and Specimen object recs in db
	public void buildParticipant(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9, String[] col10) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildParticipant()");
		
		String[][] dataInsertTable = new String[maxrecs][10];
		int insrow=0;
		int accdatacnt=0;
		int specdatacnt=0;
		String curDX="";
		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
				//lname
				dataInsertTable[insrow][0]=removeChar(col1[randomInRange(0,maxrecs-1)],' ');
				System.out.println("\t\t\t\tHere 1");
				//fname
				dataInsertTable[insrow][1]=removeChar(col2[randomInRange(0,maxrecs-1)],' ');
				System.out.println("\t\t\t\tHere 2");
				//gender
				//dataInsertTable[insrow][2]=removeChars(col3[rowcnt],"  ");
				dataInsertTable[insrow][2]=col3[rowcnt];
				System.out.println("\t\t\t\tHere 3");
				//System.out.println("\t\t\t\tgender= "+dataInsertTable[insrow][2]);
				//dob
				dataInsertTable[insrow][3]=col4[randomInRange(0,maxrecs-1)];
				System.out.println("\t\t\t\tHere 4");
				//race
				dataInsertTable[insrow][4]=removeChar(col5[randomInRange(0,maxrecs-1)],' ');
				System.out.println("\t\t\t\tHere 5");
				//ethnicity
				dataInsertTable[insrow][5]=removeChar(col6[randomInRange(0,maxrecs-1)],' ');
				//accession date
				dataInsertTable[insrow][6]=col7[randomInRange(0,maxrecs-1)];
				//dx
				dataInsertTable[insrow][7]=removeChars(col8[randomInRange(0,maxrecs-1)],"  ");
				//surgical path number
				dataInsertTable[insrow][8]=removeChar(col9[randomInRange(0,maxrecs-1)],' ');
				//participant id
				curID=removeChars(col10[randomInRange(0,maxrecs-1)],"  ");
				if(curID.length()==1)
					curID="PatID000"+curID;
				else if (curID.length()==2)
					curID="PatID00"+curID;
				else if (curID.length()==3)
					curID="PatID0"+curID;
				else if (curID.length()==4)
					curID="PatID"+curID;
				
				dataInsertTable[insrow][9]=curID;
				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			Participant p = new Participant();
			//p.setId(Long.valueOf(globalId));
			p.setLastName(dataInsertTable[rowcnt][0]);
			p.setFirstName(dataInsertTable[rowcnt][1]);
			p.setGender(dataInsertTable[rowcnt][2]);
			SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
			Date dob = sdf.parse( dataInsertTable[rowcnt][3] );
			p.setBirthDate(dob);
			p.setRace(dataInsertTable[rowcnt][4]);
			p.setEthnicity(dataInsertTable[rowcnt][5]);
			p.setUniquePatientIdentifier(dataInsertTable[rowcnt][9]);
			//System.out.println("\t\t\t\tPopulating Data Object with: "+p.getFirstName()+", "+p.getLastName()+", "+p.getGender()+", "+p.getBirthDate()+", "+p.getRace()+", "+p.getEthnicity());
			Set accessionSet = new HashSet();
			curDX="";

			for (int i = 0; i < 2; i++) {
				edu.duke.catrip.cae.domain.general.Accession acc = new Accession();
				SimpleDateFormat sdf2 = new SimpleDateFormat( "MM/dd/yyyy" );
				Date date = sdf2.parse( dataInsertTable[randomInRange(0,maxrecs-1)][6] );
				acc.setAccessionDate(date);

				while (curDX.equals(dataInsertTable[accdatacnt][7]) || dataInsertTable[accdatacnt][7].equals("Prostate")){
					accdatacnt++;
					if(accdatacnt>maxrecs-1) accdatacnt=0;
					System.out.println("\t\t\t\taccdatacnt= "+accdatacnt);
				}
				curDX=dataInsertTable[accdatacnt][7];
				
				acc.setDiseaseType(curDX);
				acc.setSurgicalPathologyNumber(dataInsertTable[i][8]);
				System.out.println("\t\t\t\tInserting acc Data Rec: "+dataInsertTable[rowcnt][0]+", "+acc.getAccessionDate()+", "+acc.getDiseaseType()+", "+acc.getSurgicalPathologyNumber());
				accessionSet.add(acc);
				
				Set specimenSet = new HashSet();
				String ID="";
				String Label="";
				for (int j = specdatacnt; j < specdatacnt+2; j++) {
					Specimen spec = new Specimen();
					if(j<10){
						ID="SpecID000"+j;
						Label="SurgicalLabel000"+j;
					}else if (j>=10 & j<100){
						ID="SpecID00"+j;
						Label="SurgicalLabel00"+j;
					}else if (j>=100 & j<1000){
						ID="SpecID0"+j;
						Label="SurgicalLabel0"+j;
					}else if (j>=1000){
						ID="SpecID"+j;
						Label="SurgicalLabel"+j;
					}
					spec.setIdentifier(ID);
					spec.setSurgicalLabel(Label);
					
					System.out.println("\t\t\t\t\tInserting spec Data Rec: "+dataInsertTable[rowcnt][0]+", "+spec.getIdentifier()+", "+spec.getSurgicalLabel());

					specimenSet.add(spec);
					
				}
				
				acc.setSpecimenCollection(specimenSet);
				
				//this is to make sure that all the specs are used
				//specdatacnt=specdatacnt+3;
				specdatacnt=specdatacnt+2;
				//if(specdatacnt>maxrecs-1) specdatacnt=0;
				
				//this is to make sure that all the dxs are used
				accdatacnt=accdatacnt+3;
				//if(accdatacnt> dxarrsize) accdatacnt=0;
				if(accdatacnt> maxrecs-1) accdatacnt=0;

			}
			
			p.setAccessionCollection(accessionSet);
			
			//p.setAnnotationEventParametersCollection(accessionSet);
			
			//if(rowcnt<50) {
				System.out.println("\t\t\t\tCalling create(p) to insert: "+p.getFirstName()+", "+p.getLastName()+", "+p.getGender()+", "+p.getBirthDate()+", "+p.getRace()+", "+p.getEthnicity());
				create(p);
			//}
	     }

	}
	
	
//Builds the NottinghamHistopathologicGrade object recs in db
	public void buildBreaseNottHistopathGrades(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException
//	public void buildBreaseNottHistopathGrades(int maxrecs, int globalId) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildParticipant()");
		
		String[][] dataInsertTable = new String[maxrecs][4];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//TubuleFormation
				dataInsertTable[insrow][0]=col1[rowcnt];
				//NuclearPleomorphism
				dataInsertTable[insrow][1]=col2[rowcnt];
				//MitoticCount
				dataInsertTable[insrow][2]=col3[rowcnt];
				//MitoticCount
				dataInsertTable[insrow][3]=String.valueOf(Integer.parseInt(dataInsertTable[insrow][0])+Integer.parseInt(dataInsertTable[insrow][1])+Integer.parseInt(dataInsertTable[insrow][2]));
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			NottinghamHistopathologicGrade n = new NottinghamHistopathologicGrade();
			//n.setId(Long.valueOf(globalId));
			n.setTubuleFormation(Integer.valueOf(dataInsertTable[rowcnt][0]));
			n.setNuclearPleomorphism(Integer.valueOf(dataInsertTable[rowcnt][1]));
			n.setMitoticCount(Integer.valueOf(dataInsertTable[rowcnt][2]));
			n.setTotalScore(Integer.valueOf(dataInsertTable[rowcnt][3]));
			n.setTotalScoreMVR("NO MVR");

			//if(rowcnt < 50) {
				System.out.println("\t\t\t\tCalling create(n) to insert: "+n.getTubuleFormation()+", "+n.getNuclearPleomorphism()+", "+n.getMitoticCount()+", "+n.getTotalScore()+", "+n.getTotalScoreMVR());
				create(n);
			//}
	     }
	}
	
//Builds the ParticipantMedicalIdentifier object recs in db
	public void buildParticipantIdent(int maxrecs,String[] col1) throws ParseException
	{
			
			if (DEBUG) System.out.println("\tInside buildParticipantIdent()");
			
			String[][] dataInsertTable = new String[maxrecs][4];
			int insrow=0;
			
			System.out.println("\t\t\t\tArray size is: "+col1.length);
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//MRN
					dataInsertTable[insrow][0]=col1[randomInRange(0,maxrecs-1)];
					insrow++;
			}
				
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	
	        List result = new ArrayList();
		    result = session.createQuery("from edu.duke.catrip.cae.domain.general.Participant").list();
		    tx.commit();
		    HibernateUtil.closeSession();
		    ParticipantMedicalIdentifier pmi=null;
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	Participant obj = (Participant) result.get(i);
	
			       	pmi = new ParticipantMedicalIdentifier();
			       	//pmi.setId(Long.valueOf(i+1));
			       	pmi.setId(obj.getId());
			       	pmi.setMedicalRecordNumber(dataInsertTable[i][0]);
			       	pmi.setParticipant(obj);
			       		
					//if(i<5) {
						System.out.println("\t\t\t\tCalling create(pmi) to insert: "+pmi.getMedicalRecordNumber()+", "+pmi.getParticipant());
						create(pmi);
					//}
			    }
		    }else{
		    	System.out.println("\t\t\t\tNO ParticipantMedicalIdentifier RECS CREATED BECAUSE THERE ARE NO Participant RECS");
		    }
		    
		    System.out.println("\t\t\t\tExiting buildParticipantIdent()");
	}
	
//Builds the ThreeDimensionalTumorSize object recs in db
	public void buildThreeDimensionalTumorSize(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildThreeDimensionalSize()");
		
		String[][] dataInsertTable = new String[maxrecs][3];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//TubuleFormation
				dataInsertTable[insrow][0]=col1[rowcnt];
				//NuclearPleomorphism
				dataInsertTable[insrow][1]=col2[rowcnt];
				//MitoticCount
				dataInsertTable[insrow][2]=col3[rowcnt];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			ThreeDimensionalTumorSize tredsiz = new ThreeDimensionalTumorSize();
			//tredsiz.setId(Long.valueOf(globalId));
			tredsiz.setGreatestDimension(Integer.valueOf(dataInsertTable[rowcnt][0]));
			tredsiz.setAdditionalDimensionY(Integer.valueOf(dataInsertTable[rowcnt][1]));
			tredsiz.setAdditionalDimensionZ(Integer.valueOf(dataInsertTable[rowcnt][2]));
			tredsiz.setMVR("NO MVR");
			
			System.out.println("\t\t\t\tCalling create(tredsiz) to insert: "+tredsiz.getGreatestDimension()+", "+tredsiz.getAdditionalDimensionY()+", "+tredsiz.getAdditionalDimensionZ()+", "+tredsiz.getMVR());
			create(tredsiz);
	     }
	}
	
//Builds the BreastCancerTNMFinding object recs in db
	public void buildBreastCancerTNM(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastCancerTNM()");
		
		String[][] dataInsertTable = new String[maxrecs][6];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//T
				dataInsertTable[insrow][0]=col1[rowcnt];
				//N
				dataInsertTable[insrow][1]=col2[rowcnt];
				//M
				dataInsertTable[insrow][2]=col3[rowcnt];
				//Nodes examined
				dataInsertTable[insrow][3]=col4[rowcnt];
				//Nodes involved
				dataInsertTable[insrow][4]=col5[rowcnt];
				//Nodes involved
				dataInsertTable[insrow][5]=col6[rowcnt];
				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			BreastCancerTNMFinding tnm = new BreastCancerTNMFinding();
			//tnm.setId(Long.valueOf(globalId));
			if(dataInsertTable[rowcnt][0].equals("0")) {
				tnm.setCategory("Stage 0");
			}else{
				if(dataInsertTable[rowcnt][0].equals("1")) {
					tnm.setCategory("Stage I");
				}else{
					if(dataInsertTable[rowcnt][0].equals("2")) {
						tnm.setCategory("Stage II");
					}else{
						if(dataInsertTable[rowcnt][0].equals("3")) {
							tnm.setCategory("Stage III");
						}else{
							if(dataInsertTable[rowcnt][0].equals("4")) {
								tnm.setCategory("Stage IV");
							}
						}
					}
				}
			}
			tnm.setPrimaryTumorFinding(dataInsertTable[rowcnt][0]);
			tnm.setRegionalLymphNodesFinding(dataInsertTable[rowcnt][1]);
			tnm.setDistantMetastasisFinding(dataInsertTable[rowcnt][2]);
			
			//collection of site location(s)
			List sites = new ArrayList();
			sites.add(dataInsertTable[randomInRange(1,maxrecs-1)][5]);
			tnm.setMetastasisAnatomicSite(sites);
			
			tnm.setNumberLymphNodesExamined(Integer.valueOf(dataInsertTable[rowcnt][3]));
			tnm.setNumberLymphNodesInvolved(Integer.valueOf(dataInsertTable[rowcnt][4]));
;
			//if(rowcnt < 50) {
				System.out.println("\t\t\t\tCalling create(tnm) to insert: "+tnm.getCategory()+", "+tnm.getPrimaryTumorFinding()+", "+tnm.getRegionalLymphNodesFinding()+", "+tnm.getDistantMetastasisFinding());
	               List masites = (List)tnm.getMetastasisAnatomicSite();
	               Iterator imasites = masites.iterator();
	               while (imasites.hasNext()) {
	                   System.out.println("\t\t\t\t and inserted site: "+imasites.next());
	               }
				create(tnm);
			//}
	     }
	}
	
	
//THIS CLASS / TABLE WAS REMOVED IN CAE 1.2
/*	//builds maxrecs recs in db
	public void buildBreastSurgicalPathologySpecimen(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastSurgicalPathologySpecimen()");
		
		String[][] dataInsertTable = new String[maxrecs][6];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//OthSurgProc
				dataInsertTable[insrow][0]=col1[rowcnt];
				//LymphNodeProc
				dataInsertTable[insrow][1]=col2[rowcnt];
				//Laterality
				dataInsertTable[insrow][2]=col3[rowcnt];
				//SurgProc
				dataInsertTable[insrow][3]=col4[rowcnt];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			BreastSurgicalPathologySpecimen bsps = new BreastSurgicalPathologySpecimen();
			//bsps.setId(Long.valueOf(globalId));
			bsps.setOtherSurgicalProcedure(dataInsertTable[rowcnt][0]);	
			if(dataInsertTable[rowcnt][0].equals("N/A")){
				bsps.setSurgicalProcedureMVR("Not Available");
			}else{
				bsps.setSurgicalProcedureMVR("NO MVR");
			}
			bsps.setLymphNodeSamplingProcedure(dataInsertTable[rowcnt][1]);
			bsps.setLaterality(dataInsertTable[rowcnt][2]);
			if(dataInsertTable[rowcnt][2].equals("N/A")){
				bsps.setLateralityMVR("Not Available");
			}else{
				bsps.setLateralityMVR("NO MVR");
			}
			//collection of surgical procedure(s)
			List surgprocs = new ArrayList();
			surgprocs.add(dataInsertTable[randomInRange(1,maxrecs-1)][3]);
			bsps.setSurgicalProcedure(surgprocs);

			//if(rowcnt < 50) {
				System.out.println("\t\t\t\tCalling create(bsps) to insert: "+bsps.getOtherSurgicalProcedure()+", "+bsps.getSurgicalProcedureMVR()+", "+bsps.getLymphNodeSamplingProcedure()+", "+bsps.getLaterality()+", "+bsps.getLateralityMVR());
	               List sp = (List)bsps.getSurgicalProcedure();
	               Iterator isp = sp.iterator();
	               while (isp.hasNext()) {
	                   System.out.println("\t\t\t\t and inserted site: "+isp.next());
	               }
				create(bsps);
			//}
	     }
	}*/
	
//Builds the OtherBreastCancerHistopathologicGrade object recs in db
	public void buildOtherBreastCancerHistopathologicGrade(int maxrecs,String[] col1,String[] col2) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildOtherBreastCancerHistopathologicGrade()");
		
		String[][] dataInsertTable = new String[maxrecs][2];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//System name
				dataInsertTable[insrow][0]=col1[rowcnt];
				//count
				dataInsertTable[insrow][1]=col2[rowcnt];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			OtherBreastCancerHistopathologicGrade othispathgrad = new OtherBreastCancerHistopathologicGrade();
			//othispathgrad.setId(Long.valueOf(globalId));
			othispathgrad.setSystemName(dataInsertTable[rowcnt][0]);
			int chkval = Integer.parseInt(dataInsertTable[rowcnt][1]);
			if(chkval < 8) {
				othispathgrad.setScore(Integer.valueOf(1));
			}else{
				if(chkval > 8 && chkval < 15) {
					othispathgrad.setScore(Integer.valueOf(2));
				}else{
					othispathgrad.setScore(Integer.valueOf(3));
				}
			}
			othispathgrad.setScoreMVR("NO MVR");
			othispathgrad.setMitoticCount(Integer.valueOf(dataInsertTable[rowcnt][1]));

			//if(rowcnt < 50) {
				System.out.println("\t\t\t\tCalling create(bsps) to insert: "+othispathgrad.getSystemName()+", "+othispathgrad.getScore()+", "+othispathgrad.getScoreMVR()+", "+othispathgrad.getMitoticCount());
				create(othispathgrad);
			//}
		}
	}
	
//Builds the InvasiveBreastCarcinoma object recs in db
	public void buildInvasiveBreastCarcinoma(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildOtherBreastCancerHistopathologicGrade()");
		
		String[][] dataInsertTable = new String[maxrecs][3];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//InvasiveBreastCarcinoma
				dataInsertTable[insrow][0]=col1[rowcnt];
				//MC Location
				dataInsertTable[insrow][1]=col2[rowcnt];
				//MC Location
				dataInsertTable[insrow][2]=col3[rowcnt];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			InvasiveBreastCarcinoma ibc = new InvasiveBreastCarcinoma();
			//othispathgrad.setId(Long.valueOf(globalId));
			ibc.setVenousLymphaticInvasion(dataInsertTable[rowcnt][0]);
			ibc.setLocationMVR("NO MVR");
					
			//collection of mclocation(s)
			List mcloca = new ArrayList();
			mcloca.add(dataInsertTable[randomInRange(1,maxrecs-1)][1]);
			ibc.setMicrocalcificationLocation(mcloca);

			//collection of location(s)
			List loca = new ArrayList();
			loca.add(dataInsertTable[randomInRange(1,maxrecs-1)][2]);
			ibc.setLocation(loca);
			
			System.out.println("\t\t\t\tCalling create(ibc) to insert: "+ibc.getVenousLymphaticInvasion()+", "+ibc.getLocationMVR());
            List loc = (List)ibc.getLocation();
            Iterator iloc = loc.iterator();
            while (iloc.hasNext()) {
                System.out.println("\t\t\t\t and inserted location: "+iloc.next());
            }		
            List mcloc = (List)ibc.getMicrocalcificationLocation();
            Iterator imcloc = mcloc.iterator();
            while (imcloc.hasNext()) {
                System.out.println("\t\t\t\t and inserted mclocation: "+imcloc.next());
            }
            create(ibc);

		}
	}
	
//	THIS CLASS / TABLE WAS REMOVED IN CAE 1.2
/*	//BreastNegativeSurgicalMarginInsertTest
	//builds maxrecs recs in db
	public void buildBreastNegativeSurgicalMargin(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastNegativeSurgicalMargin()");
		
		String[][] dataInsertTable = new String[maxrecs][4];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//ClosestNeoplasm
				dataInsertTable[insrow][0]=col1[rowcnt];
				//DistToClosestNeoplasm
				dataInsertTable[insrow][1]=col2[rowcnt];
				//SpatialRelationship
				dataInsertTable[insrow][2]=col3[rowcnt];
				//OtherSpatialRelationship
				dataInsertTable[insrow][3]=col3[randomInRange(0,maxrecs-1)];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			BreastNegativeSurgicalMargin bnsm = new BreastNegativeSurgicalMargin();
			bnsm.setClosestNeoplasmPresent(dataInsertTable[rowcnt][0]);
			bnsm.setDistanceToClosestNeoplasm(Float.valueOf(dataInsertTable[rowcnt][1]));
			bnsm.setSpatialRelationshipToPatient(dataInsertTable[rowcnt][2]);
			bnsm.setOtherSpatialRelationshipToPatient(dataInsertTable[rowcnt][3]);
			bnsm.setMVR("NO MVR");
			
			System.out.println("\t\t\t\tCalling create(bnsm) to insert: "+bnsm.getClosestNeoplasmPresent()+", "+bnsm.getDistanceToClosestNeoplasm()+", "+bnsm.getMVR()+", "+bnsm.getOtherSpatialRelationshipToPatient()+", "+bnsm.getSpatialRelationshipToPatient());

            create(bnsm);

		}
	}*/
	
//	THIS CLASS / TABLE WAS REMOVED IN CAE 1.2
/*	//BreastPositiveSurgicalMarginInsertTest
	//builds maxrecs recs in db
	public void buildBreastPositiveSurgicalMargin(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastPositiveSurgicalMargin()");
		
		String[][] dataInsertTable = new String[maxrecs][5];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//neoplasmPresent
				dataInsertTable[insrow][0]=col1[rowcnt];
				//extentInvolvement
				dataInsertTable[insrow][1]=col2[rowcnt];
				//otherextentInvolvement
				dataInsertTable[insrow][2]=col3[randomInRange(0,maxrecs-1)];
				
				//SpatialRelationship
				dataInsertTable[insrow][3]=col4[rowcnt];
				//OtherSpatialRelationship
				dataInsertTable[insrow][4]=col4[randomInRange(0,maxrecs-1)];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			BreastPositiveSurgicalMargin bpsm = new BreastPositiveSurgicalMargin();
			bpsm.setNeoplasmPresent(dataInsertTable[rowcnt][0]);
			bpsm.setExtentInvolvement(dataInsertTable[rowcnt][1]);
			bpsm.setOtherExtentInvolvement(dataInsertTable[rowcnt][2]);
			
			bpsm.setSpatialRelationshipToPatient(dataInsertTable[rowcnt][3]);
			bpsm.setOtherSpatialRelationshipToPatient(dataInsertTable[rowcnt][4]);
			bpsm.setMVR("NO MVR");

			System.out.println("\t\t\t\tCalling create(bnsm) to insert: "+bpsm.getNeoplasmPresent()+", "+bpsm.getExtentInvolvement()+", "+bpsm.getMVR()+", "+bpsm.getOtherSpatialRelationshipToPatient()+", "+bpsm.getSpatialRelationshipToPatient());

            create(bpsm);

		}
	}*/
	
//Builds the BREAST_NEO_HSTOPTHLGC_TYPES object recs in db
	public void buildInvasiveBreastCarcinomaNeoplasmHistologicType(int maxrecs,String[] col1,String[] col2) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildInvasiveBreastCarcinomaNeoplasmHistologicType()");
		
		String[][] dataInsertTable = new String[maxrecs][2];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//NeoplasmHistologicType
				dataInsertTable[insrow][0]=col1[rowcnt];
				//otherNeoplasmHistologicType
				dataInsertTable[insrow][1]=col2[rowcnt];
//				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]+", "+dataInsertTable[insrow][9]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			InvasiveBreastCarcinomaNeoplasmHistologicType ibcnht = new InvasiveBreastCarcinomaNeoplasmHistologicType();
			ibcnht.setName(dataInsertTable[rowcnt][0]);
			ibcnht.setNameMVR("NO MVR");
			ibcnht.setOtherName(dataInsertTable[rowcnt][1]);

			System.out.println("\t\t\t\tCalling create(ibcnht) to insert: "+ibcnht.getName()+", "+ibcnht.getNameMVR()+", "+ibcnht.getOtherName());
			ibcnht.setAnnotationEventParameters(null);
			ibcnht.setId(null);

            create(ibcnht);

		}
	}
	
//Builds the EVENT_PARAMETERS object recs in db
	public void buildEventParameters() throws ParseException
	{
			
			if (DEBUG) System.out.println("\tInside buildEventParameters()");
						
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	
	        List result = new ArrayList();
		    result = session.createQuery("from edu.duke.catrip.cae.domain.general.Specimen").list();
		    tx.commit();
		    HibernateUtil.closeSession();
		    EventParameters ae=null;
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<result.size(); i++) {
			    	Specimen s = (Specimen) result.get(i);
	
			       	ae = new EventParameters();
			       	ae.setId(s.getId());
			       	ae.setTimeStamp(new Date());
			       		
					//if(i<5) {
						System.out.println("\t\t\t\tCalling create(ae) to insert: "+ae.getId()+", "+ae.getTimeStamp());
						create(ae);
					//}
			    }
		    }else{
		    	System.out.println("\t\t\t\tNO EventParameters RECS CREATED BECAUSE THERE ARE NO Specimen RECS");
		    }
		    
		    System.out.println("\t\t\t\tExiting buildEventParameters()");
	}

	
//Builds the ANNOTATION_EVENT_PARAMETER object recs in db
	public void buildAnnotationEventParameters() throws ParseException
	{
			
			if (DEBUG) System.out.println("\tInside buildAnnotationEventParameters()");
						
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	
	        List result = new ArrayList();
		    result = session.createQuery("from edu.pitt.cabig.cae.domain.general.AnnotatableEntity").list();
		    tx.commit();
		    HibernateUtil.closeSession();
		    AnnotationEventParameters aep=null;
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<result.size(); i++) {
			    	//if(i > 6) break;
			    	AnnotatableEntity aeObj = (AnnotatableEntity) result.get(i);
	
			       	aep = new AnnotationEventParameters();
			       	aep.setAnnotatableEntity(aeObj);
			       	aep.setSource("AnnotatableEntity");
			       	aep.setSourceDate(new Date());

			       	System.out.println("\t\t\t\tCalling create(aep) to insert: "+aep.getAnnotatableEntity());
					create(aep);

			    }
		    }else{
		    	System.out.println("\t\t\t\tNO AnnotationEventParameters RECS CREATED BECAUSE THERE ARE NO AnnotatableEntity RECS");
		    }
		    
		    System.out.println("\t\t\t\tExiting buildAnnotationEventParameters()");
	}
	
//Builds the ANNOTATION_SET object recs in db
	public void buildAnnotationSet() throws ParseException
	{
			
			if (DEBUG) System.out.println("\tInside buildAnnotationSet()");
						
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	
	        List result = new ArrayList();
		    result = session.createQuery("from edu.pitt.cabig.cae.domain.general.AnnotationEventParameters").list();
		    tx.commit();
		    HibernateUtil.closeSession();
		    AnnotationSet as=null;
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<result.size(); i++) {
			    	AnnotationEventParameters aepObj = (AnnotationEventParameters) result.get(i);
	
			       	as = new AnnotationSet();
			       	as.setAnnotationEventParameters(aepObj);

			       	System.out.println("\t\t\t\tCalling create(aep) to insert: "+as.getAnnotationEventParameters());
					create(as);

			    }
		    }else{
		    	System.out.println("\t\t\t\tNO AnnotationEventParameters RECS CREATED BECAUSE THERE ARE NO AnnotatableEntity RECS");
		    }
		    
		    System.out.println("\t\t\t\tExiting buildAnnotationEventParameters()");
	}
	
	
//Builds the Accession_Characteristics object recs in db
	public void buildBreastCancerAccessionCharacteristics(int maxrecs,String[] col1,String[] col2) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastCancerAccessionCharacteristics()");
		
		String[][] dataInsertTable = new String[maxrecs][2];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			
			//
			if(col1[rowcnt] == null){
				dataInsertTable[insrow][0]="n/a";
			}else{
				dataInsertTable[insrow][0]=col1[rowcnt];
			}
			if(col2[rowcnt] == null){
				dataInsertTable[insrow][1]="n/a";
			}else{
				dataInsertTable[insrow][1]=col2[rowcnt];
			}
			
			//System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]);
			insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			if(dataInsertTable[rowcnt][0] == "n/a") break;
			BreastCancerAccessionCharacteristics bcac = new BreastCancerAccessionCharacteristics();
			bcac.setOtherSurgicalProcedure(dataInsertTable[rowcnt][0]);
			System.out.println("\t\t\t\tOtherSurgicalProcedure = "+dataInsertTable[rowcnt][0]);
			Set <String> surgicalSet = new HashSet();
			//Set surgicalSet = new HashSet();
			
			for (int i = 0; i < maxrecs; i++) {
				
				if(dataInsertTable[i][1] == "n/a") break;
				//System.out.println("\t\t\t\t\tChking dataInsertTable[i][1] = "+dataInsertTable[i][1]+"...");
				int loc = dataInsertTable[i][1].toLowerCase().indexOf(dataInsertTable[rowcnt][0].toLowerCase());      
				//System.out.println("\t\t\t\t\tloc = "+loc);
				if(loc > -1){
					surgicalSet.add(dataInsertTable[i][1]);
					System.out.println("\t\t\t\t\t\tsurgicalSet = "+dataInsertTable[i][1]);
				}
			}

			bcac.setSurgicalProcedure(surgicalSet);
			create(bcac);

	     }
	}
	
//Builds the BREAST_SPECMN_CHARACTERSTIC object recs in db
	public void buildBreastSpecimenCharacteristics(int maxrecs,String[] col1,String[] col2) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastSpecimenCharacteristics()");
		
		String[][] dataInsertTable = new String[maxrecs][2];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			
			//LymphNodeSamplingProcedure
			if(col1[rowcnt] == null){
				dataInsertTable[insrow][0]="n/a";
			}else{
				dataInsertTable[insrow][0]=col1[rowcnt];
			}
			
			//Laterality
			if(col2[rowcnt] == null){
				dataInsertTable[insrow][1]="n/a";
			}else{
				dataInsertTable[insrow][1]=col2[rowcnt];
			}
								
			System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]);
			insrow++;
		}

		int fndcnt = 0;
	    
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			if(dataInsertTable[rowcnt][0] == "n/a") break;
			BreastSpecimenCharacteristics bsc = new BreastSpecimenCharacteristics();
			bsc.setLymphNodeSamplingProcedure(dataInsertTable[rowcnt][0]);
			//for (int i = 0; i < maxrecs; i++) {
				if(dataInsertTable[fndcnt][1] == "n/a") fndcnt=0;
				bsc.setLaterality(dataInsertTable[fndcnt][1]);
				System.out.println("\t\t\t\tfndcnt: "+fndcnt);
				System.out.println("\t\t\t\tdataInsertTable[fndcnt][1] "+dataInsertTable[fndcnt][1]);
			    ++fndcnt;
			//}
			bsc.setLateralityMVR("NO MVR");
			System.out.println("\t\t\t\tbsc has: "+bsc.getLymphNodeSamplingProcedure()+", "+bsc.getLaterality());
			create(bsc);

	     }
	}
	
	//builds the Breast_Cancer_Biomarkers  object recs in db
	public void buildBreastCancerBiomarkers(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4,String[] col5) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildBreastCancerBiomarkers()");
		
		String[][] dataInsertTable = new String[maxrecs][5];
		int insrow=0;
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
				//EstrogenReceptor
				dataInsertTable[insrow][0]=col1[rowcnt];
				
				//ProgesteroneReceptor
				dataInsertTable[insrow][1]=col2[rowcnt];
				
				//HER2Status"
				dataInsertTable[insrow][2]=col3[rowcnt];
				
				//HER2TestType
				dataInsertTable[insrow][3]=col4[rowcnt];
				
				//EGFRStatus
				dataInsertTable[insrow][4]=col5[rowcnt];

				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]);
				insrow++;
		}

			//TODO: WRITE CODE TO insert current tables' recs into db
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			BreastCancerBiomarkers bcb = new BreastCancerBiomarkers();
			bcb.setEstrogenReceptor(dataInsertTable[rowcnt][0]);
			bcb.setProgesteroneReceptor(dataInsertTable[rowcnt][1]);
			bcb.setHER2Status(dataInsertTable[rowcnt][2]);
			bcb.setHER2TestType(dataInsertTable[rowcnt][3]);
			bcb.setEGFRStatus(dataInsertTable[rowcnt][4]);
			System.out.println("\t\t\t\tCreating object: "+bcb.getEstrogenReceptor()+", "+bcb.getProgesteroneReceptor()+", "+bcb.getHER2Status()+", "+bcb.getHER2TestType()+", "+bcb.getEGFRStatus());
			create(bcb);

	     }
	}
	
}