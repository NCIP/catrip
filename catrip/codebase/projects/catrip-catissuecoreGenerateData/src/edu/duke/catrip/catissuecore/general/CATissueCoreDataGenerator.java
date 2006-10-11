/**
 * Created on Aug 24, 2006 by PEEDI002
 */
package edu.duke.catrip.catissuecore.general;

//my import statements
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.catrip.catissuecore.util.HibernateUtil;
import edu.duke.catrip.datagenerator.DataGeneratorToolKit;
//import edu.wustl.catissuecore.domainobject.Address;
//import edu.wustl.catissuecore.domainobject.CancerResearchGroup;
//import edu.wustl.catissuecore.domainobject.Department;
//import edu.wustl.catissuecore.domainobject.Institution;
import edu.wustl.catissuecore.domainobject.Institution;
import edu.wustl.catissuecore.domainobject.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domainobject.impl.AddressImpl;
import edu.wustl.catissuecore.domainobject.impl.CancerResearchGroupImpl;
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolEventImpl;
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolImpl;
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolRegistrationImpl;
import edu.wustl.catissuecore.domainobject.impl.DepartmentImpl;
import edu.wustl.catissuecore.domainobject.impl.InstitutionImpl;
import edu.wustl.catissuecore.domainobject.impl.ParticipantImpl;
import edu.wustl.catissuecore.domainobject.impl.ParticipantMedicalIdentifierImpl;
import edu.wustl.catissuecore.domainobject.impl.SiteImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenCollectionGroupImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenProtocolImpl;
import edu.wustl.catissuecore.domainobject.impl.StorageContainerCapacityImpl;
import edu.wustl.catissuecore.domainobject.impl.StorageContainerImpl;
import edu.wustl.catissuecore.domainobject.impl.StorageTypeImpl;
import edu.wustl.catissuecore.domainobject.impl.UserImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenImpl;
import edu.wustl.catissuecore.domainobject.impl.TissueSpecimenImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenCharacteristicsImpl;

public class CATissueCoreDataGenerator extends DataGeneratorToolKit
{
		
	/**
	 * Whether to print out debug messages
	 */
	public static final boolean DEBUG = true;
	

	/**
	 * Constructor 
	 */
	public CATissueCoreDataGenerator()
	{
	super();
	
	if (DEBUG) System.out.println("\tInside Constructor: DataGenerator()");

	}


	//builds participant recs in db
	public void buildParticipant(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildParticipant()");
		
		String[][] dataInsertTable = new String[maxrecs][10];
		int insrow=0;
		Object[] objsArr = new Object[2];
		int objcnt=0;
		
		List result = new ArrayList();

		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
			
			//Participant
				//lname
				//dataInsertTable[insrow][0]=removeChar(col1[randomInRange(0,maxrecs-1)],' ');
				dataInsertTable[insrow][0]=removeChar(col1[rowcnt],' ');

				//fname
				//dataInsertTable[insrow][1]=removeChar(col2[randomInRange(0,maxrecs-1)],' ');
				dataInsertTable[insrow][1]=removeChar(col2[rowcnt],' ');

				//mname
				dataInsertTable[insrow][2]=removeChar(col2[randomInRange(0,maxrecs-1)],' ');

				//System.out.println("\t\t\t\tgender= "+dataInsertTable[insrow][2]);
				//dob
				//dataInsertTable[insrow][3]=col4[randomInRange(0,maxrecs-1)];
				dataInsertTable[insrow][3]=col4[rowcnt];

				//race
				dataInsertTable[insrow][4]=removeChar(col5[randomInRange(0,maxrecs-1)],' ');

				//ethnicity
				dataInsertTable[insrow][5]=removeChar(col6[randomInRange(0,maxrecs-1)],' ');
				//ssn
				//dataInsertTable[insrow][6]=col7[randomInRange(0,maxrecs-1)];
				dataInsertTable[insrow][6]=col7[rowcnt];
				//participant id
				curID=removeChars(col8[randomInRange(0,maxrecs-1)],"  ");
				if(curID.length()==1)
					curID="PatID000"+curID;
				else if (curID.length()==2)
					curID="PatID00"+curID;
				else if (curID.length()==3)
					curID="PatID0"+curID;
				else if (curID.length()==4)
					curID="PatID"+curID;
				
				dataInsertTable[insrow][7]=curID;
				
				//gender
				dataInsertTable[insrow][8]="Female";
				
			//ParticipantIdent
				//MRN
				dataInsertTable[insrow][9]=col9[rowcnt];
				
				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			objcnt=-1;
			ParticipantImpl p = new ParticipantImpl();
			//p.setId(Long.valueOf(globalId));
			p.setLastName(dataInsertTable[rowcnt][0]);
			p.setFirstName(dataInsertTable[rowcnt][1]);
			p.setMiddleName(dataInsertTable[rowcnt][2]);
			SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
			Date dob = sdf.parse( dataInsertTable[rowcnt][3] );
			p.setBirthDate(dob);
			p.setRace(dataInsertTable[rowcnt][4]);
			p.setEthnicity(dataInsertTable[rowcnt][5]);
			p.setSocialSecurityNumber(dataInsertTable[rowcnt][6]);
			p.setGender(dataInsertTable[rowcnt][8]);
			p.setActivityStatus("Active");

    		System.out.println("\t\t\t\tAdding Participant object to objsArr, that contains: "+p.getFirstName()+", "+p.getLastName());

			++objcnt;
			objsArr[objcnt]=p;
			
			ParticipantMedicalIdentifierImpl pmi = new ParticipantMedicalIdentifierImpl();
			
			pmi.setMedicalRecordNumber(dataInsertTable[rowcnt][9]);
			pmi.setParticipant(p);
			
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();

		    result = session.createQuery("from SiteImpl").list();
		    tx.commit();
		    
		    HibernateUtil.closeSession();
		    if(result.size()>0){
			    for (int i = 0; i<maxrecs; i++) {
			    	SiteImpl obj = (SiteImpl) result.get(i);

			    	if(i==rowcnt){
			    		pmi.setSite(obj);
			    		System.out.println("\t\t\t\tFound Site Name: "+obj.getName());
			    		break;
			    	}
			    }
		    }
			
    		System.out.println("\t\t\t\tAdding ParticipantIdent object to objsArr, that contains: "+pmi.getMedicalRecordNumber());

			++objcnt;
			objsArr[objcnt]=pmi;
			   		
			System.out.println("\t\t\t\t("+rowcnt+") Calling create(objsArr) to save/commit all associated objects, in the array of objects...");
			create(objsArr);
	     }

	}
	
	public void buildUser(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9,String[] col10,String[] col11,String[] col12,String[] col13,String[] col14,String[] col15,String[] col16,String[] col17,String[] col18,String[] col19) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildUser()");
			
			String[][] dataInsertTable = new String[maxrecs][20];
			
			Object[] objsArr = new Object[5];
			
			String[] nodupInst = new String[maxrecs];
			int dupcnt=0;
			String[] nodupSite = new String[maxrecs];
			int dupcntSite=0;
			int insrow=0;

			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {

				//USER STUFF
					//UserComments
					dataInsertTable[insrow][0]=rmvTrailingBlanks(col1[rowcnt]);
					//EmailAddress
					dataInsertTable[insrow][1]=col2[rowcnt];
					//FirstNamesFemales
					dataInsertTable[insrow][2]=col3[rowcnt];
					//LastNames
					dataInsertTable[insrow][3]=col4[rowcnt];
					//Logins
					dataInsertTable[insrow][4]=col5[rowcnt];
					//Passwords
					dataInsertTable[insrow][5]=col6[rowcnt];
					//Dates
					dataInsertTable[insrow][6]=col7[rowcnt];
				//INST AND DEPT STUFF
					//institutions
					dataInsertTable[insrow][7]=col8[rowcnt];
					//departments
					dataInsertTable[insrow][8]=col9[rowcnt];
				//ADDRESS STUFF
					//streets
					dataInsertTable[insrow][9]=col10[rowcnt];
					//cities
					dataInsertTable[insrow][10]=col11[rowcnt];
					//states
					dataInsertTable[insrow][11]=col12[rowcnt];
					//zip
					dataInsertTable[insrow][12]=col13[rowcnt];
					//phone
					dataInsertTable[insrow][13]=col14[rowcnt];
					//fax
					dataInsertTable[insrow][14]=col15[rowcnt];
				//CANCER GROUP STUFF
					//ResearchGroup
					dataInsertTable[insrow][15]=col16[rowcnt];
					
				//Site
					//int nxt = randomInRange(0,maxrecs-1);
					//SITE
					if(col17.length > rowcnt){
						dataInsertTable[insrow][16]=col17[rowcnt];
						System.out.println("SITE = "+dataInsertTable[insrow][16]);
						//SITE TYPE
						dataInsertTable[insrow][17]=col18[rowcnt];
						//EMAIL
						dataInsertTable[insrow][18]=col19[rowcnt];
					}
					
					insrow++;
			}

			int objcnt=0;
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
							
				objcnt=-1;
			//ADDRESS STUFF
				AddressImpl a = new AddressImpl();
				a.setStreet(dataInsertTable[rowcnt][9]);
				a.setCity(dataInsertTable[rowcnt][10]);
				a.setState(dataInsertTable[rowcnt][11]);
				a.setZipCode(dataInsertTable[rowcnt][12]);
				a.setCountry("United States");
				a.setPhoneNumber(dataInsertTable[rowcnt][13]);
				a.setFaxNumber(dataInsertTable[rowcnt][14]);
				System.out.println("\n\t\t\t\tAdding Address object to objsArr, that contains: "+a.getStreet()+", "+a.getCity()+", "+a.getState()+", "+a.getZipCode()+", "+a.getCountry()+", "+a.getPhoneNumber()+", "+a.getFaxNumber());
				++objcnt;
				objsArr[objcnt]=a;

			//INSTITUTION STUFF
				InstitutionImpl i = new InstitutionImpl();
				
				//check for dups
				for (int j=0;j<maxrecs;j++){
					if(rowcnt>0 && rowcnt<maxrecs){				
						for (int cnt = 0; cnt < rowcnt; cnt++) {
							if(dataInsertTable[rowcnt][7].equals(nodupInst[cnt])){
								++rowcnt;
							}
						}
					}else{
						break;
					}
				}
				
				//add selected institution into nodupInst array
				nodupInst[dupcnt++]=dataInsertTable[rowcnt][7];
				
				if(dataInsertTable[rowcnt][7].length()>50)
					i.setName(dataInsertTable[rowcnt][7].substring(0,49));
				else
					i.setName(dataInsertTable[rowcnt][7]);
				System.out.println("\t\t\t\tAdding Institution object to objsArr, that contains: "+i.getName());
				++objcnt;
				objsArr[objcnt]=i;

			//DEPARTMENT STUFF
				DepartmentImpl d = new DepartmentImpl();	
				if(dataInsertTable[rowcnt][8].length()>50)
					d.setName(dataInsertTable[rowcnt][8].substring(0,49));
				else
					d.setName(dataInsertTable[rowcnt][8]);
				System.out.println("\t\t\t\tAdding Department object to objsArr, that contains: "+d.getName());
				++objcnt;
				objsArr[objcnt]=d;
				
			//CANCER GROUP STUFF
				CancerResearchGroupImpl crg = new CancerResearchGroupImpl();
				crg.setName(dataInsertTable[rowcnt][15]);
				System.out.println("\t\t\t\tAdding CancerGroup object to objsArr, that contains: "+crg.getName());			
				++objcnt;
				objsArr[objcnt]=crg;
						
			//USER STUFF
				UserImpl u = new UserImpl();
				u.setComments(rmvTrailingBlanks(dataInsertTable[rowcnt][0]));
				u.setEmailAddress(dataInsertTable[rowcnt][1]);
				u.setFirstName(dataInsertTable[rowcnt][2]);
				u.setLastName(dataInsertTable[rowcnt][3]);
				u.setLoginName(dataInsertTable[rowcnt][4]);
				u.setPassword(dataInsertTable[rowcnt][5]);
				SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
				Date sdate = sdf.parse( dataInsertTable[rowcnt][6] );
				u.setStartDate(sdate);
				u.setActivityStatus("Active");

			//ADDING THE OBJECTS TO THE USER OBJECT
				u.setAddress(a);
				u.setDepartment(d);
				u.setInstitution(i);
				u.setCancerResearchGroup(crg);
				
				System.out.println("\t\t\t\tAdding User object to objsArr, that contains: "+u.getActivityStatus()+", "+u.getComments()+", "+u.getEmailAddress()+", "+u.getFirstName()+", "+u.getLastName()+", "+u.getLoginName()+", "+u.getPassword()+", "+u.getStartDate());
				//create(u);
				++objcnt;
				objsArr[objcnt]=u;
				
				System.out.println("\t\t\t\t("+rowcnt+") Calling create(objsArr) to save/commit all associated objects, in the array of objects...");
				
				create(objsArr);
				
			//Site STUFF
				
				//check for dup sites
				boolean nodups=true;
				for (int cnt = 0; cnt < dupcntSite; cnt++) {
					if(dataInsertTable[rowcnt][16].equals(nodupInst[cnt])){
						nodups=false;
					}
				}
				
				if(nodups){
					//then, add the site
					nodupSite[dupcntSite++]=dataInsertTable[rowcnt][16];
				
					SiteImpl s = new SiteImpl();
					s.setActivityStatus("Active");
					s.setName(dataInsertTable[rowcnt][16]);
					s.setType(dataInsertTable[rowcnt][17]);
					s.setEmailAddress(dataInsertTable[rowcnt][18]);
					
					//Objects attached to Site
						//Address STUFF
			    		s.setAddress(a);

			    		//Coordinator STUFF
			    		s.setCoordinator(u);
		
			    		System.out.println("\t\t\t\tAdding Site object to objsArr, that contains: "+s.getName());
			    		
						System.out.println("\t\t\t\t("+rowcnt+") Calling create(objsArr) to save/commit all associated objects, in the array of objects...");
						
						create(s);
				}
				

		     }
		    
			System.out.println("\n\n\t\t\t\tCreated ("+maxrecs+") Records, Times ("+objcnt+") objects...\n");

	}
	
	//builds users recs in db
//	public void buildSpecimen(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9,String[] col10,String[] col11,String[] col12,String[] col13,String[] col14,String[] col15,String[] col16,String[] col17,String[] col18,String[] col19,String[] col20,String[] col21,String[] col22,String[] col23,String[] col24,String[] col25,String[] col26,String[] col27,String[] col28,String[] col29,String[] col30,String[] col31,String[] col32,String[] col33,String[] col34,String[] col35,String[] col36,String[] col37) throws ParseException
	public void buildSpecimen(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9,String[] col10,String[] col11,String[] col12,String[] col13,String[] col14,String[] col15,String[] col16,String[] col17,String[] col18,String[] col19,String[] col20,String[] col21,String[] col22,String[] col23,String[] col27,String[] col28,String[] col29,String[] col30,String[] col31,String[] col32,String[] col33,String[] col34,String[] col35,String[] col36,String[] col37) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildSpecimen()");
		
		String[][] dataInsertTable = new String[maxrecs][37];

		Object[] objsArr = new Object[10];
		
		String[] nodupInst = new String[maxrecs];
		int dupcnt=0;
		
		int insrow=0;

		String curID="";
		
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        List result = new ArrayList();
        
        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
        Date sdate = null;
        
		//load the arrays
		//for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			//Specimen
				//SpecimenType
				dataInsertTable[insrow][0]=col1[rowcnt];
				//SpecimenQuantity
				dataInsertTable[insrow][1]=col2[rowcnt];
				//SpecimenQuantityAvail
				dataInsertTable[insrow][2]=col3[rowcnt];
				//SpecimenCmmts
				dataInsertTable[insrow][3]=col4[rowcnt];
				//SpecimenBarcodes
				dataInsertTable[insrow][4]=col5[rowcnt];
				//PositionDimensionOne
				dataInsertTable[insrow][5]=col6[rowcnt];
				//PositionDimensionTwo
				dataInsertTable[insrow][6]=col7[rowcnt];
				
			//SpecimenChar
				//Tissue Site
				dataInsertTable[insrow][7]=col8[rowcnt];
				//Tissue Side
				dataInsertTable[insrow][8]=col9[rowcnt];
				//Path Stat
				dataInsertTable[insrow][9]=col10[rowcnt];
				
			//SpecimenCollectionGroup
				//ClinicalDiag
				dataInsertTable[insrow][10]=col11[rowcnt];
				//ClinicalStatus
				dataInsertTable[insrow][11]=col12[rowcnt];
				
			//CollectionProtocolEvent
				//Clinical Status
				dataInsertTable[insrow][12]=col13[rowcnt];
				//StudyCalendarEventPoint
				dataInsertTable[insrow][13]=col14[rowcnt];
				
			//CollectionProtocol (extends SpecimenProtocol)
				//DescriptionURL
				dataInsertTable[insrow][14]=col15[rowcnt];
				//EndDate
				dataInsertTable[insrow][15]=col16[rowcnt];
				//Enrollment
				dataInsertTable[insrow][16]=col17[rowcnt];
				//IrbIdentifier
				dataInsertTable[insrow][17]=col18[rowcnt];
				//ShortTitle
				dataInsertTable[insrow][18]=col19[rowcnt];
				//StartDate
				dataInsertTable[insrow][19]=col20[rowcnt];
				//Title
				dataInsertTable[insrow][20]=col21[rowcnt];
	
			//CollectionProtocolRegistration
				//ProtocolParticipantIdentifier
				dataInsertTable[insrow][21]=col22[rowcnt];
				//RegistrationDate
				dataInsertTable[insrow][22]=col23[rowcnt];
	
//			//Site
//				//SITE
//				dataInsertTable[insrow][23]=col24[randomInRange(0,maxrecs-1)];
//				//SITE TYPE
//				dataInsertTable[insrow][24]=col25[randomInRange(0,maxrecs-1)];
//				//EMAIL
//				dataInsertTable[insrow][25]=col26[randomInRange(0,maxrecs-1)];
	
			//StorageContainer
				//Barcode
				dataInsertTable[insrow][26]=col27[rowcnt];
				//Number
				//dataInsertTable[insrow][27]=col28[rowcnt];
				dataInsertTable[insrow][27]=removeChars(col28[rowcnt],"  ");
				
				//PositionDimensionOne
				dataInsertTable[insrow][28]=col29[rowcnt];
				//PositionDimensionTwo
				dataInsertTable[insrow][29]=col30[rowcnt];
				//TempratureInCentigrade
				dataInsertTable[insrow][30]=col31[rowcnt];
	
			//StorageContainerCapacity 
				//OneDimensionCapacity 
				dataInsertTable[insrow][31]=col32[rowcnt];
				//TwoDimensionCapacity 
				dataInsertTable[insrow][32]=col33[rowcnt];
	
			//StorageType  
				//DefaultTempratureInCentigrade 
				dataInsertTable[insrow][33]=col34[rowcnt];
				//OneDimensionLabel 
				dataInsertTable[insrow][34]=col35[rowcnt];
				//TwoDimensionLabel 
				dataInsertTable[insrow][35]=col36[rowcnt];
				//Type 
				dataInsertTable[insrow][36]=col37[rowcnt];

				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		int fndcnt=0;
		int objcnt=0;
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			objcnt=-1;
			
		//TissueSpecimen STUFF
			TissueSpecimenImpl ts = new TissueSpecimenImpl();
			
			ts.setType(dataInsertTable[rowcnt][0]);
			ts.setQuantityInGram(Double.valueOf(dataInsertTable[rowcnt][1]));
			ts.setAvailableQuantityInGram(Double.valueOf(Float.parseFloat(dataInsertTable[rowcnt][2])-.5));
			//ts.setAvailableQuantityInGram(Double.valueOf(dataInsertTable[rowcnt][2]));
			ts.setComments(dataInsertTable[rowcnt][3]);
			ts.setBarcode(dataInsertTable[rowcnt][4]);
			ts.setPositionDimensionOne(Integer.valueOf(dataInsertTable[rowcnt][5]));
			ts.setPositionDimensionTwo(Integer.valueOf(dataInsertTable[rowcnt][6]));
			ts.setActivityStatus("Active");
			ts.setAvailable(Boolean.TRUE);
			
			//objects attached, that need to be populated
			//SpecimenCharacteristics STUFF
				SpecimenCharacteristicsImpl sc = new SpecimenCharacteristicsImpl();
				sc.setTissueSite(dataInsertTable[rowcnt][7]);
				sc.setTissueSide(dataInsertTable[rowcnt][8]);
				sc.setPathologicalStatus(dataInsertTable[rowcnt][9]);
				System.out.println("\n\t\t\t\tAdding SpecimenCharacteristics object to objsArr, that contains: "+sc.getPathologicalStatus()+", "+sc.getTissueSide()+", "+sc.getTissueSite());			

				++objcnt;
				objsArr[objcnt]=sc;
				
			ts.setSpecimenCharacteristics(sc);
			
			//SpecimenCollectionGroup STUFF
				SpecimenCollectionGroupImpl scg = new SpecimenCollectionGroupImpl();
				scg.setActivityStatus("Active");
				scg.setClinicalDiagnosis(dataInsertTable[rowcnt][10]);
				scg.setClinicalStatus(dataInsertTable[rowcnt][11]);
			
				//Objects attached to SpecimenCollectionGroup, that need to be populated
				//CollectionProtocolEvent STUFF
					CollectionProtocolEventImpl cpe = new CollectionProtocolEventImpl();
					cpe.setClinicalStatus(dataInsertTable[rowcnt][12]);
					cpe.setStudyCalendarEventPoint(Double.valueOf(dataInsertTable[rowcnt][13]));
					//Objects attached to CollectionProtocolEvent
					//CollectionProtocol STUFF (extends SpecimenProtocol)
					
//						SpecimenProtocolImpl sp = new SpecimenProtocolImpl();
//						sp.setActivityStatus("Active");
//						sp.setDescriptionURL(dataInsertTable[rowcnt][14]);
//	
//						sdate = sdf.parse( dataInsertTable[rowcnt][15]);
//						System.out.println("end date: "+sdate);
//						sp.setEndDate(sdate);	
//						sp.setEnrollment(Integer.valueOf(dataInsertTable[rowcnt][16]));
//						sp.setIrbIdentifier(dataInsertTable[rowcnt][17]);
//						sp.setShortTitle(dataInsertTable[rowcnt][18]);
//						sdate = sdf.parse( dataInsertTable[rowcnt][19]);
//						System.out.println("start date: "+sdate);
//						sp.setStartDate(sdate);
//						if(dataInsertTable[rowcnt][20].length()>50)
//							sp.setTitle(dataInsertTable[rowcnt][20].substring(0,49));
//						else
//							sp.setTitle(dataInsertTable[rowcnt][20]);
//						
//						//PrincipalInvestigator STUFF
//				        result = session.createQuery("from UserImpl").list();
//				        tx.commit();
//					    
//				        //HibernateUtil.closeSession();
//			        	if(result.size()>0){
//			        		for (int i = 0; i<maxrecs; i++) {
//			        			UserImpl obj = (UserImpl) result.get(i);
//			        			if(i==fndcnt){
//			        				sp.setPrincipalInvestigator(obj);
//						    		System.out.println("\t\t\t\tFound PI: "+obj.getFirstName()+", "+obj.getLastName());
//						    		break;
//			        			}
//			        		}
//			        	}
//			        	System.out.println("\t\t\t\tAdding SpecimenProtocol object to objsArr, that contains: "+sp.getStartDate()+", "+sp.getEndDate());
//						++objcnt;
//						objsArr[objcnt]=sp;
					
					CollectionProtocolImpl cp = new CollectionProtocolImpl();
					
					cp.setActivityStatus("Active");
					cp.setDescriptionURL(dataInsertTable[rowcnt][14]);
				
					sdate = sdf.parse( dataInsertTable[rowcnt][15]);
					cp.setEndDate(sdate);	
					cp.setEnrollment(Integer.valueOf(dataInsertTable[rowcnt][16]));
					cp.setIrbIdentifier(dataInsertTable[rowcnt][17]);
					cp.setShortTitle(dataInsertTable[rowcnt][18]);
					sdate = sdf.parse( dataInsertTable[rowcnt][19]);
					cp.setStartDate(sdate);
					if(dataInsertTable[rowcnt][20].length()>50)
						cp.setTitle(dataInsertTable[rowcnt][20].substring(0,49));
					else
						cp.setTitle(dataInsertTable[rowcnt][20]);
					//Objects attached to CollectionProtocol
//						List collProtEvt = new ArrayList();
//						collProtEvt.add("New Diagnosis");
//						cp.setCollectionProtocolEventCollection(collProtEvt);
					
//						List collDistProt = new ArrayList();
//						collDistProt.add("Active");
//						cp.setDistributionProtocolCollection(collDistProt);

						List collUsr = new ArrayList();
				        result = session.createQuery("from UserImpl").list();
				        tx.commit();
				        //HibernateUtil.closeSession();
				        //for (int rcnt = 0; rcnt < maxrecs; rcnt++) {
				        	if(result.size()>0){
				        		for (int i = 0; i<maxrecs; i++) {
				        			UserImpl obj = (UserImpl) result.get(i);
				        			//if(i==rcnt){
				        			if(i==fndcnt){
				        				collUsr.add(obj);
							    		System.out.println("\t\t\t\tFound PI: "+obj.getFirstName()+", "+obj.getLastName());
							    		break;
				        			}
				        		}
				        	}
				        //}
				        					        
						cp.setUserCollection(collUsr);
							System.out.println("\t\t\t\tAdding CollectionProtocol object to objsArr, that contains: "+cp.getStartDate()+", "+cp.getEndDate());
						++objcnt;
						objsArr[objcnt]=cp;
						
					cpe.setCollectionProtocol(cp);
	
					
//					List collSpecReq = new ArrayList();
//					collSpecReq.add("Tissue");
//					cpe.setSpecimenRequirementCollection(collSpecReq);
					
					System.out.println("\t\t\t\tAdding CollectionProtocolEvent object to objsArr, that contains: "+cpe.getClinicalStatus());
						
					++objcnt;
					objsArr[objcnt]=cpe;
					
				scg.setCollectionProtocolEvent(cpe);

					//CollectionProtocolRegistration STUFF
						CollectionProtocolRegistrationImpl cpr = new CollectionProtocolRegistrationImpl();
						cpr.setActivityStatus("Active");
						cpr.setProtocolParticipantIdentifier(dataInsertTable[rowcnt][21]);
						sdate = sdf.parse( dataInsertTable[rowcnt][22]);
						cpr.setRegistrationDate(sdate);
						//Objects attached
							cpr.setCollectionProtocol(null);
							
						//Participant STUFF

					        result = session.createQuery("from ParticipantImpl").list();
					        tx.commit();
						    
					        //HibernateUtil.closeSession();
					        //for (int rcnt = 0; rcnt < maxrecs; rcnt++) {
					        	if(result.size()>0){
					        		for (int i = 0; i<maxrecs; i++) {
					        			ParticipantImpl obj = (ParticipantImpl) result.get(i);

					        			//if(i==rcnt){
					        			if(i==fndcnt){
					        				cpr.setParticipant(obj);
								    		System.out.println("\t\t\t\tFound Part. Name: "+obj.getLastName());
								    		break;
					        			}
					        		}
					        	}
					        //}
			
					    System.out.println("\t\t\t\tAdding CollectionProtocolRegistration object to objsArr, that contains: "+cpr.getProtocolParticipantIdentifier());
						++objcnt;
						objsArr[objcnt]=cpr;
							
				scg.setCollectionProtocolRegistration(cpr);
						
		        result = session.createQuery("from SiteImpl").list();
		        tx.commit();
			    
		        //HibernateUtil.closeSession();
		        //for (int rcnt = 0; rcnt < maxrecs; rcnt++) {
		        	if(result.size()>0){
		        		for (int i = 0; i<maxrecs; i++) {
		        			SiteImpl obj = (SiteImpl) result.get(i);

		        			//if(i==rcnt){
		        			if(i==fndcnt){
		        				scg.setSite(obj);
					    		System.out.println("\t\t\t\tFound Site Name: "+obj.getName());
					    		break;
		        			}
		        		}
		        	}
		        //}
						
				//scg.setSite(s);
				
				//collection
//				List collSpec = new ArrayList();
//				collSpecReq.add(null);
//				scg.setSpecimenCollection(collSpec);
		
				System.out.println("\t\t\t\tAdding SpecimenCollectionGroup object to objsArr, that contains: "+scg.getClinicalStatus());
				++objcnt;
				objsArr[objcnt]=scg;
				
			ts.setSpecimenCollectionGroup(scg);
			
			//StorageContainer STUFF
				StorageContainerImpl stc = new StorageContainerImpl();
				stc.setActivityStatus("Active");
				stc.setBarcode(dataInsertTable[rowcnt][26]);
				stc.setIsFull(Boolean.TRUE);
				stc.setNumber(Integer.valueOf(dataInsertTable[rowcnt][27]));
				stc.setPositionDimensionOne(Integer.valueOf(dataInsertTable[rowcnt][28]));
				stc.setPositionDimensionTwo(Integer.valueOf(dataInsertTable[rowcnt][29]));
				stc.setTempratureInCentigrade(Double.valueOf(dataInsertTable[rowcnt][30]));
				//Objects attached
				//Site STUFF
	        		result = session.createQuery("from SiteImpl").list();
	        		tx.commit();
		    
	        		//HibernateUtil.closeSession();
	        		//boolean fnd=false;
	        		//for (int rcnt = 0; rcnt < maxrecs; rcnt++) {
		        		if(result.size()>0){
		        			for (int i = 0; i<maxrecs; i++) {
		        				SiteImpl obj = (SiteImpl) result.get(i);
	
		        				//if(i==rcnt){
		        				if(i==fndcnt){
		        					stc.setSite(obj);
		        					System.out.println("\t\t\t\tFound Site: "+obj.getName());
		        					//fnd=true;
		           					break;
		        				}
		        			}
		        		}
		        		//if(fnd) break;
		        	//}
				
				//StorageContainerCapacity STUFF
					StorageContainerCapacityImpl scc = new StorageContainerCapacityImpl();
					scc.setOneDimensionCapacity(Integer.valueOf(dataInsertTable[rowcnt][31]));
					scc.setTwoDimensionCapacity(Integer.valueOf(dataInsertTable[rowcnt][32]));
					
					System.out.println("\t\t\t\tAdding StorageContainerCapacity object to objsArr, that contains: "+scc.getOneDimensionCapacity());
					++objcnt;
					objsArr[objcnt]=scc;
					
				stc.setStorageContainerCapacity(scc);
				
				//StorageType STUFF
					StorageTypeImpl st = new StorageTypeImpl();
					st.setDefaultTempratureInCentigrade(Double.valueOf(dataInsertTable[rowcnt][33]));
					st.setOneDimensionLabel(dataInsertTable[rowcnt][34]);
					st.setTwoDimensionLabel(dataInsertTable[rowcnt][35]);
					st.setType(dataInsertTable[rowcnt][36]);
					//Objects attached
					//DefaultStorageCapacity STUFF
					st.setDefaultStorageCapacity(scc);
					
					System.out.println("\t\t\t\tAdding StorageType object to objsArr, that contains: "+st.getType());
					++objcnt;
					objsArr[objcnt]=st;
					
				stc.setStorageType(st);
				
				System.out.println("\t\t\t\tAdding StorageContainer object to objsArr, that contains: "+stc.getNumber());
				++objcnt;
				objsArr[objcnt]=stc;
				
			ts.setStorageContainer(stc);
			
			System.out.println("\t\t\t\tAdding TissueSpecimen object to objsArr, that contains: "+ts.getType());
			++objcnt;
			objsArr[objcnt]=ts;
			
			System.out.println("\t\t\t\t("+rowcnt+") Calling create(objsArr) to create all the objects...\n");

			create(objsArr);
			
			++fndcnt;

	     }
		
		System.out.println("\n\n\t\t\t\tCreated ("+fndcnt+") Records, Times ("+objcnt+") objects...\n");
		HibernateUtil.closeSession();
	}
	
//	public void buildCollProt(int maxrecs) throws ParseException
//	{
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        List result = new ArrayList();
//		SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
//		CollectionProtocolImpl cp = new CollectionProtocolImpl();
//		
//		cp.setActivityStatus("Active");
//		cp.setDescriptionURL("http://biospecimen_protocol_bloodsample1");
//		cp.setEndDate(sdf.parse("12/1/2006"));	
//		cp.setEnrollment(Integer.valueOf(390));
//		cp.setIrbIdentifier("IRB0000001");
//		cp.setShortTitle("BR 10+ NODES");
//		cp.setStartDate(sdf.parse("12/1/2005"));	
//		cp.setTitle("Breast, Adj: 10+ Nodes: CAF> CCB/BR");
//		//Objects attached to CollectionProtocol
//			List collProtEvt = new ArrayList();
//			collProtEvt.add("New Diagnosis");
//			cp.setCollectionProtocolEventCollection(collProtEvt);
//		
//			List collDistProt = new ArrayList();
//			collDistProt.add("Active");
//			cp.setDistributionProtocolCollection(collDistProt);
//		
//			List collUsr = new ArrayList();
//	        result = session.createQuery("from UserImpl").list();
//	        tx.commit();
//
//	        if(result.size()>0){
//	        	UserImpl obj = (UserImpl) result.get(0);
//	        	collUsr.add(obj);
//        	}
//		        
//		cp.setUserCollection(collUsr);
//		create(cp);
//	}
}