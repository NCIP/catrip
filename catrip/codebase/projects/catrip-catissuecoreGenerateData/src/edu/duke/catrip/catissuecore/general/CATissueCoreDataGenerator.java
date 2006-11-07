/**
 * Created on Aug 24, 2006 by PEEDI002
 */
package edu.duke.catrip.catissuecore.general;

//my import statements
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.catrip.datagenerator.DataGeneratorToolKit;
import edu.duke.catrip.datagenerator.HibernateUtil;
import edu.wustl.catissuecore.domainobject.Address;
import edu.wustl.catissuecore.domainobject.CancerResearchGroup;
import edu.wustl.catissuecore.domainobject.ClinicalReport;
import edu.wustl.catissuecore.domainobject.CollectionProtocolEvent;
import edu.wustl.catissuecore.domainobject.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domainobject.Department;
import edu.wustl.catissuecore.domainobject.Institution;
import edu.wustl.catissuecore.domainobject.Participant;
import edu.wustl.catissuecore.domainobject.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domainobject.SpecimenCharacteristics;
import edu.wustl.catissuecore.domainobject.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domainobject.SpecimenRequirement;
import edu.wustl.catissuecore.domainobject.StorageContainer;
import edu.wustl.catissuecore.domainobject.StorageContainerCapacity;
import edu.wustl.catissuecore.domainobject.StorageType;
import edu.wustl.catissuecore.domainobject.TissueSpecimen;
import edu.wustl.catissuecore.domainobject.User;
import edu.wustl.catissuecore.domainobject.impl.AddressImpl;
import edu.wustl.catissuecore.domainobject.impl.CancerResearchGroupImpl;
import edu.wustl.catissuecore.domainobject.impl.ClinicalReportImpl;
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolEventImpl;
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolRegistrationImpl;
import edu.wustl.catissuecore.domainobject.impl.DepartmentImpl;
import edu.wustl.catissuecore.domainobject.impl.InstitutionImpl;
import edu.wustl.catissuecore.domainobject.impl.ParticipantImpl;
import edu.wustl.catissuecore.domainobject.impl.ParticipantMedicalIdentifierImpl;
import edu.wustl.catissuecore.domainobject.impl.SiteImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenCharacteristicsImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenCollectionGroupImpl;
import edu.wustl.catissuecore.domainobject.impl.SpecimenRequirementImpl;
import edu.wustl.catissuecore.domainobject.impl.StorageContainerCapacityImpl;
import edu.wustl.catissuecore.domainobject.impl.StorageContainerImpl;
import edu.wustl.catissuecore.domainobject.impl.StorageTypeImpl;
import edu.wustl.catissuecore.domainobject.impl.TissueSpecimenImpl;
import edu.wustl.catissuecore.domainobject.impl.UserImpl;

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
	
	if (DEBUG) System.out.println("\tInside Constructor: CATissueCoreDataGenerator()");

	}

//Builds the Address, Institution, Department, CancerResearchGroup, User, Site object recs in db
	public void buildUser(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9,String[] col10,String[] col11,String[] col12,String[] col13,String[] col14,String[] col15,String[] col16,String[] col17,String[] col18,String[] col19) throws ParseException
	{
			if (DEBUG) System.out.println("\tInside buildUser()");
			
			String[][] dataInsertTable = new String[maxrecs][20];
			
			Object[] objsArr = new Object[5];
			
			int insrow=0;
			int statecnt=0;
			
			Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	        List result = new ArrayList();

			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {

				//USER STUFF
					//UserComments
					if(col1[rowcnt] == null){
						dataInsertTable[insrow][0]="n/a";
					}else{
						dataInsertTable[insrow][0]=rmvTrailingBlanks(col1[rowcnt]);
					}
					//EmailAddress
					if(col2[rowcnt] == null){
						dataInsertTable[insrow][1]="n/a";
					}else{
						dataInsertTable[insrow][1]=col2[rowcnt];
					}
					//FirstNamesFemales
					if(col3[rowcnt] == null){
						dataInsertTable[insrow][2]="n/a";
					}else{
						dataInsertTable[insrow][2]=col3[rowcnt];
					}
					//LastNames
					if(col4[rowcnt] == null){
						dataInsertTable[insrow][3]="n/a";
					}else{
						dataInsertTable[insrow][3]=col4[rowcnt];
					}
					//Logins
					if(col5[rowcnt] == null){
						dataInsertTable[insrow][4]="n/a";
					}else{
						dataInsertTable[insrow][4]=col5[rowcnt];
					}
					//Passwords
					if(col6[rowcnt] == null){
						dataInsertTable[insrow][5]="n/a";
					}else{
						dataInsertTable[insrow][5]=col6[rowcnt];
					}
					//Dates
					if(col7[rowcnt] == null){
						dataInsertTable[insrow][6]="n/a";
					}else{
						dataInsertTable[insrow][6]=col7[rowcnt];
					}
				//INST AND DEPT STUFF
					//institutions
					if(col8[rowcnt] == null){
						dataInsertTable[insrow][7]="n/a";
					}else{
						dataInsertTable[insrow][7]=col8[rowcnt];
					}
					//departments
					if(col9[rowcnt] == null){
						dataInsertTable[insrow][8]="n/a";
					}else{
						dataInsertTable[insrow][8]=col9[rowcnt];
					}
				//ADDRESS STUFF
					//streets
					if(col10[rowcnt] == null){
						dataInsertTable[insrow][9]="n/a";
					}else{
						dataInsertTable[insrow][9]=col10[rowcnt];
					}
					//cities
					if(col11[rowcnt] == null){
						dataInsertTable[insrow][10]="n/a";
					}else{
						dataInsertTable[insrow][10]=col11[rowcnt];
					}
					//states
					if(col12[rowcnt] == null){
						dataInsertTable[insrow][11]="n/a";
					}else{
						dataInsertTable[insrow][11]=col12[rowcnt];
					}
					//zip
					if(col13[rowcnt] == null){
						dataInsertTable[insrow][12]="n/a";
					}else{
						dataInsertTable[insrow][12]=col13[rowcnt];
					}
					//phone
					if(col14[rowcnt] == null){
						dataInsertTable[insrow][13]="n/a";
					}else{
						dataInsertTable[insrow][13]=col14[rowcnt];
					}
					//fax
					if(col15[rowcnt] == null){
						dataInsertTable[insrow][14]="n/a";
					}else{
						dataInsertTable[insrow][14]=col15[rowcnt];
					}
				//CANCER GROUP STUFF
					//ResearchGroup
					if(col16[rowcnt] == null){
						dataInsertTable[insrow][15]="n/a";
					}else{
						dataInsertTable[insrow][15]=col16[rowcnt];
					}
					System.out.println("SITE = "+dataInsertTable[insrow][15]);
					
				//Site
					//SITE
					if(col17.length > rowcnt){
						if(col17[rowcnt] == null){
							dataInsertTable[insrow][16]="n/a";
						}else{
							dataInsertTable[insrow][16]=col17[rowcnt];
						}
						//SITE TYPE
						if(col18[rowcnt] == null){
							dataInsertTable[insrow][17]="n/a";
						}else{
							dataInsertTable[insrow][17]=col18[rowcnt];
						}
						//EMAIL
						if(col19[rowcnt] == null){
							dataInsertTable[insrow][18]="n/a";
						}else{
							dataInsertTable[insrow][18]=col19[rowcnt];
						}
					}
					
					insrow++;
			}

			int objcnt=0;
			int fndcnt=-1;
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				objcnt=-1;
				
			//ADDRESS STUFF
				Address a = new AddressImpl();
				a.setStreet(dataInsertTable[rowcnt][9]);
				a.setCity(dataInsertTable[rowcnt][10]);
				//chk to see if more data in array - if not, reset counter
				if(dataInsertTable[statecnt][11] == "n/a") statecnt=0;
				a.setState(dataInsertTable[statecnt][11]);
				++statecnt;
				
				a.setZipCode(dataInsertTable[rowcnt][12]);
				a.setCountry("United States");
				a.setPhoneNumber(dataInsertTable[rowcnt][13]);
				a.setFaxNumber(dataInsertTable[rowcnt][14]);
				System.out.println("\n\t\t\t\tAdding Address object to objsArr, that contains: "+a.getStreet()+", "+a.getCity()+", "+a.getState()+", "+a.getZipCode()+", "+a.getCountry()+", "+a.getPhoneNumber()+", "+a.getFaxNumber());
				++objcnt;
				objsArr[objcnt]=a;

			//INSTITUTION STUFF
				Institution i = null;
				if(dataInsertTable[rowcnt][7] == null || dataInsertTable[rowcnt][7] == "n/a"){
					//no more institution from input, so, need to get a one from db
					result = session.createQuery("from InstitutionImpl").list();
				    tx.commit();
					    
				    int reccnt=result.size();
				    ++fndcnt;
				    if(fndcnt>reccnt-1) fndcnt=0;
				    i = (InstitutionImpl) result.get(fndcnt);
				}else{
					i = new InstitutionImpl();
					if(dataInsertTable[rowcnt][7].length()>50)
						i.setName(dataInsertTable[rowcnt][7].substring(0,49));
					else
						i.setName(dataInsertTable[rowcnt][7]);
					System.out.println("\t\t\t\tAdding Institution object to objsArr, that contains: "+i.getName());
					++objcnt;
					objsArr[objcnt]=i;
				}

			//DEPARTMENT STUFF
				Department d = null;
					
				if(dataInsertTable[rowcnt][8] == null || dataInsertTable[rowcnt][8] == "n/a"){
					//no more Department from input, so, need to get a one from db
					result = session.createQuery("from DepartmentImpl").list();
				    tx.commit();
					    
				    int reccnt=result.size();
				    ++fndcnt;
				    if(fndcnt>reccnt-1) fndcnt=0;
				    d = (DepartmentImpl) result.get(fndcnt);
				}else{
					d = new DepartmentImpl();
					if(dataInsertTable[rowcnt][8].length()>50)
						d.setName(dataInsertTable[rowcnt][8].substring(0,49));
					else
						d.setName(dataInsertTable[rowcnt][8]);
					System.out.println("\t\t\t\tAdding Department object to objsArr, that contains: "+d.getName());
					++objcnt;
					objsArr[objcnt]=d;
				}
				
			//CANCER GROUP STUFF
				CancerResearchGroup crg = null;
				
				if(dataInsertTable[rowcnt][15] == null || dataInsertTable[rowcnt][15] == "n/a"){
					//no more CancerResearchGroup from input, so, need to get a one from db
					result = session.createQuery("from CancerResearchGroupImpl").list();
				    tx.commit();
					    
				    int reccnt=result.size();
				    ++fndcnt;
				    if(fndcnt>reccnt-1) fndcnt=0;
				    crg = (CancerResearchGroupImpl) result.get(fndcnt);
				}else{
					crg = new CancerResearchGroupImpl();
					crg.setName(dataInsertTable[rowcnt][15]);
					System.out.println("\t\t\t\tAdding CancerGroup object to objsArr, that contains: "+crg.getName());			
					++objcnt;
					objsArr[objcnt]=crg;
				}
						
			//USER STUFF
				User u = new UserImpl();
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
				
				if(dataInsertTable[rowcnt][16] == null || dataInsertTable[rowcnt][16] == "n/a"){
					System.out.println("\t\t\t\tNO MORE Sites to add...");
				}else{
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
	
	//builds the Participant, ParticipantMedicalIdentifier and ClinicalReport object recs in db
	public void buildParticipant(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildParticipant()");
		
		String[][] dataInsertTable = new String[maxrecs][11];
		int insrow=0;
		Object[] objsArr = new Object[3];
		int objcnt=0;
		
		List result = new ArrayList();

		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
			
			//Participant
				//lname
				dataInsertTable[insrow][0]=removeChar(col1[rowcnt],' ');

				//fname
				dataInsertTable[insrow][1]=removeChar(col2[rowcnt],' ');

				//mname
				dataInsertTable[insrow][2]=removeChar(col2[randomInRange(0,maxrecs-1)],' ');

				//dob
				dataInsertTable[insrow][3]=col4[rowcnt];

				//race
				dataInsertTable[insrow][4]=removeChar(col5[randomInRange(0,maxrecs-1)],' ');

				//ethnicity
				dataInsertTable[insrow][5]=removeChar(col6[randomInRange(0,maxrecs-1)],' ');
				//ssn
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
				System.out.println("\t\t\t\tMRN = "+dataInsertTable[insrow][9]);
				
			//ClinicalReport
				//SurgicalPathologyNumber
				dataInsertTable[insrow][10]="Z"+col9[rowcnt].substring(0,1)+"0"+col9[rowcnt].substring(5,6)+"-"+col9[rowcnt].substring(1,5);
				System.out.println("\t\t\t\tSurgicalPathologyNumber = "+dataInsertTable[insrow][10]);
				insrow++;
		}

		int fndcnt = -1;
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			objcnt=-1;
			Participant p = new ParticipantImpl();
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
			
			ParticipantMedicalIdentifier pmi = new ParticipantMedicalIdentifierImpl();
			
			pmi.setMedicalRecordNumber(dataInsertTable[rowcnt][9]);
			pmi.setParticipant(p);
			
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();

		    result = session.createQuery("from SiteImpl").list();
		    tx.commit();
		    
		    HibernateUtil.closeSession();
		    if(result.size()>0){
			    int reccnt=result.size();
			    ++fndcnt;
			    if(fndcnt>reccnt-1) fndcnt=0;
			    SiteImpl obj = (SiteImpl) result.get(fndcnt);

			    pmi.setSite(obj);
			   	System.out.println("\t\t\t\tFound Site Name: "+obj.getName());
		    }
			
    		System.out.println("\t\t\t\tAdding ParticipantIdent object to objsArr, that contains: "+pmi.getMedicalRecordNumber());

			++objcnt;
			objsArr[objcnt]=pmi;
			   		
			ClinicalReport cr = new ClinicalReportImpl();
			cr.setParticipantMedicalIdentifier(pmi);
			cr.setSurgicalPathologyNumber(dataInsertTable[rowcnt][10]);
			
    		System.out.println("\t\t\t\tAdding ClinicalReport object to objsArr, that contains: "+cr.getSurgicalPathologyNumber());

			++objcnt;
			objsArr[objcnt]=cr;
			
			System.out.println("\t\t\t\t("+rowcnt+") Calling create(objsArr) to save/commit all associated objects, in the array of objects...");
			create(objsArr);
	     }

	}
	
//Builds SpecimenCharacteristics, CollectionProtocolEvent, CollectionProtocolRegistration, SpecimenCollectionGroup, StorageContainerCapacity, StorageType, StorageContainer, TissueSpecimen object recs in db
	public void buildSpecimen(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9,String[] col10,String[] col11,String[] col12,String[] col13,String[] col14,String[] col15,String[] col16,String[] col17,String[] col18,String[] col19,String[] col20,String[] col21,String[] col22,String[] col23,String[] col27,String[] col28,String[] col29,String[] col30,String[] col31,String[] col32,String[] col33,String[] col34,String[] col35,String[] col36,String[] col37) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildSpecimen()");
		
		String[][] dataInsertTable = new String[maxrecs][37];

		Object[] objsArr = new Object[9];
		
		int insrow=0;

		Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        List result = new ArrayList();
        
        SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
        Date sdate = null;
        
		//load the arrays
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
	
			//StorageContainer
				//Barcode
				dataInsertTable[insrow][26]=col27[rowcnt]+rowcnt;
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
				if(col37[rowcnt] == null){
					dataInsertTable[insrow][36]="n/a";
				}else{
					dataInsertTable[insrow][36]=col37[rowcnt];
				}

				insrow++;
		}

		int fndcnt=0;
		int stccnt=0;
		int regdatecnt=0;
		int defdegcnt=0;
		int objcnt=0;
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			objcnt=-1;
			
		//TissueSpecimen STUFF
			TissueSpecimen ts = new TissueSpecimenImpl();
			
			int loc = dataInsertTable[rowcnt][0].indexOf("Tissue");      
			if(loc > -1){
				ts.setType(dataInsertTable[rowcnt][0]);
			}else{
				//loop until find a 'tissue' type
				for (int i = maxrecs-1; i > 0; i--) {
					loc = dataInsertTable[i][0].indexOf("Tissue");      
					if(loc > -1){
						ts.setType(dataInsertTable[i][0]);
						break;
					}
				}
			}
			//ts.setType(dataInsertTable[rowcnt][0]);
			ts.setQuantityInGram(Double.valueOf(dataInsertTable[rowcnt][1]));
			ts.setAvailableQuantityInGram(Double.valueOf(Float.parseFloat(dataInsertTable[rowcnt][2])-.5));
			//ts.setAvailableQuantityInGram(Double.valueOf(dataInsertTable[rowcnt][2]));
			ts.setComments(dataInsertTable[rowcnt][3]);
			System.out.println("\n barcode: "+dataInsertTable[rowcnt][4]);
			ts.setBarcode(dataInsertTable[rowcnt][4]);
			ts.setPositionDimensionOne(Integer.valueOf(dataInsertTable[rowcnt][5]));
			ts.setPositionDimensionTwo(Integer.valueOf(dataInsertTable[rowcnt][6]));
			ts.setActivityStatus("Active");
			ts.setAvailable(Boolean.TRUE);

			
			//objects attached, that need to be populated
			//SpecimenCharacteristics STUFF
				SpecimenCharacteristics sc = new SpecimenCharacteristicsImpl();
				sc.setTissueSite(dataInsertTable[rowcnt][7]);
				sc.setTissueSide(dataInsertTable[rowcnt][8]);
				sc.setPathologicalStatus(dataInsertTable[rowcnt][9]);
				System.out.println("\n\t\t\t\tAdding SpecimenCharacteristics object to objsArr, that contains: "+sc.getPathologicalStatus()+", "+sc.getTissueSide()+", "+sc.getTissueSite());			

				++objcnt;
				objsArr[objcnt]=sc;
				
			ts.setSpecimenCharacteristics(sc);
			
			//SpecimenCollectionGroup STUFF
				SpecimenCollectionGroup scg = new SpecimenCollectionGroupImpl();
				scg.setActivityStatus("Active");
				scg.setClinicalDiagnosis(dataInsertTable[rowcnt][10]);
				scg.setClinicalStatus(dataInsertTable[rowcnt][11]);
			
				//Objects attached to SpecimenCollectionGroup, that need to be populated

	//				COMMENTED OUT FOR TESTING WITHOUT CP
	/*				CollectionProtocol cp = new CollectionProtocolImpl();
					//CollectionProtocol STUFF (extends SpecimenProtocol)
						
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
	//TODO - SET THE ASSOCIATION OF CP TO CPE AFTER CREATING THEM BOTH
	//					List collProtEvt = new ArrayList();
	//					result = session.createQuery("from  CollectionProtocolEventImpl").list();
	//					if(result.size()>0){
	//		        		for (int i = 0; i<maxrecs; i++) {
	//		        			CollectionProtocolEvent cpeObj = (CollectionProtocolEventImpl) result.get(i);
	//		        			//if(i==rcnt){
	//		        			if(i==fndcnt){
	//		        				collProtEvt.add(cpeObj);
	//					    		break;
	//		        			}
	//		        		}
	//		        	}
	//					cp.setCollectionProtocolEventCollection(collProtEvt);
	
					
	//					List collDistProt = new ArrayList();
	//					collDistProt.add("Active");
	//					cp.setDistributionProtocolCollection(collDistProt);
	
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
					objsArr[objcnt]=cp;*/		
				//SpecimenRequirement
					SpecimenRequirement sr = new SpecimenRequirementImpl();
					sr.setSpecimenType(dataInsertTable[rowcnt][0]);
					sr.setTissueSite(dataInsertTable[rowcnt][7]);
					sr.setPathologyStatus(dataInsertTable[rowcnt][9]);
					System.out.println("\t\t\t\tAdding SpecimenRequirement object to objsArr, that contains: "+sr.getSpecimenType());

					++objcnt;
					objsArr[objcnt]=sr;

				//CollectionProtocolEvent STUFF
					CollectionProtocolEvent cpe = new CollectionProtocolEventImpl();
					cpe.setClinicalStatus(dataInsertTable[rowcnt][12]);
					cpe.setStudyCalendarEventPoint(Double.valueOf(dataInsertTable[rowcnt][13]));
					//Objects attached to CollectionProtocolEvent
//
//					List collSpecReq = new ArrayList();
//					int loc = dataInsertTable[rowcnt][0].indexOf("Tissue");      
//					if(loc > -1){
//						collSpecReq.add("Tissue");
//					}else{
//						collSpecReq.add("Fluid");
//					}
//					
//					System.out.println("\n\t\t\t\tTissue Type: "+dataInsertTable[rowcnt][0]);
//					System.out.println("\n\t\t\t\tTissue Class: "+collSpecReq);
//					//cpe.setSpecimenRequirementCollection(collSpecReq);
//					cpe.setSpecimenRequirementCollection(collSpecReq);
					
					System.out.println("\t\t\t\tAdding CollectionProtocolEvent object to objsArr, that contains: "+cpe.getClinicalStatus());
						
//					TODO - SET THE ASSOCIATION OF CP TO CPE AFTER CREATING CP
//					cpe.setCollectionProtocol(cp);
					
					++objcnt;
					objsArr[objcnt]=cpe;
					
				scg.setCollectionProtocolEvent(cpe);
				
					//CollectionProtocolRegistration STUFF
						CollectionProtocolRegistration cpr = new CollectionProtocolRegistrationImpl();
						cpr.setActivityStatus("Active");
						cpr.setProtocolParticipantIdentifier(dataInsertTable[rowcnt][21]);
						//chk to see if more data in array - if not, reset counter
						if(dataInsertTable[regdatecnt][22] == null) regdatecnt=0;
						sdate = sdf.parse( dataInsertTable[regdatecnt][22]);
						cpr.setRegistrationDate(sdate);
						++regdatecnt;
						//Objects attached
							cpr.setCollectionProtocol(null);
							
						//Participant STUFF

					        result = session.createQuery("from ParticipantImpl").list();
					        tx.commit();
						    
					        //HibernateUtil.closeSession();
					        //for (int rcnt = 0; rcnt < maxrecs; rcnt++) {
					        	if(result.size()>0){
					        		for (int i = 0; i<result.size(); i++) {
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

	        	if(result.size()>0){
	        		for (int i = 0; i<result.size(); i++) {
	        			SiteImpl s = (SiteImpl) result.get(i);

	        			//if(i==rcnt){
	        			if(i==fndcnt){
	        				scg.setSite(s);
				    		System.out.println("\t\t\t\tFound Site Name: "+s.getName());
				    		break;
	        			}
	        		}
	        	}
				
				//collection
//				List collSpec = new ArrayList();
//				collSpecReq.add(null);
//				scg.setSpecimenCollection(collSpec);
		
				System.out.println("\t\t\t\tAdding SpecimenCollectionGroup object to objsArr, that contains: "+scg.getClinicalStatus());
				++objcnt;
				objsArr[objcnt]=scg;
				
			ts.setSpecimenCollectionGroup(scg);
			
			//StorageContainer STUFF
				StorageContainer stc = new StorageContainerImpl();
				stc.setActivityStatus("Active");
				stc.setBarcode(dataInsertTable[rowcnt][26]);
				stc.setIsFull(Boolean.TRUE);
				stc.setNumber(Integer.valueOf(dataInsertTable[rowcnt][27]));
				stc.setPositionDimensionOne(Integer.valueOf(dataInsertTable[rowcnt][28]));
				stc.setPositionDimensionTwo(Integer.valueOf(dataInsertTable[rowcnt][29]));
				//chk to see if more data in array - if not, reset counter
				if(dataInsertTable[stccnt][30] == null) stccnt=0;
				stc.setTempratureInCentigrade(Double.valueOf(dataInsertTable[stccnt][30]));
				++stccnt;
				
				//Objects attached
				//Site STUFF
	        		result = session.createQuery("from SiteImpl").list();
	        		tx.commit();
		    
	        		//HibernateUtil.closeSession();
	        		//boolean fnd=false;
	        		//for (int rcnt = 0; rcnt < maxrecs; rcnt++) {
		        		if(result.size()>0){
		        			for (int i = 0; i<result.size(); i++) {
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
					StorageContainerCapacity scc = new StorageContainerCapacityImpl();
					scc.setOneDimensionCapacity(Integer.valueOf(dataInsertTable[rowcnt][31]));
					scc.setTwoDimensionCapacity(Integer.valueOf(dataInsertTable[rowcnt][32]));
					
					System.out.println("\t\t\t\tAdding StorageContainerCapacity object to objsArr, that contains: "+scc.getOneDimensionCapacity());
					++objcnt;
					objsArr[objcnt]=scc;
					
				stc.setStorageContainerCapacity(scc);
				
				//StorageType STUFF
					StorageType st = null;
				
					if(dataInsertTable[rowcnt][8] == null || dataInsertTable[rowcnt][8] == "n/a"){
						//no more StorageType from input, so, need to get a one from db
						result = session.createQuery("from StorageTypeImpl").list();
					    tx.commit();
						    
					    int reccnt=result.size();
					    ++fndcnt;
					    if(fndcnt>reccnt-1) fndcnt=0;
					    st = (StorageTypeImpl) result.get(fndcnt);
					}else{
						st = new StorageTypeImpl();
						if(dataInsertTable[defdegcnt][33] == null) defdegcnt=0;
						st.setDefaultTempratureInCentigrade(Double.valueOf(dataInsertTable[defdegcnt][33]));
						++defdegcnt;
						st.setOneDimensionLabel(dataInsertTable[rowcnt][34]);
						st.setTwoDimensionLabel(dataInsertTable[rowcnt][35]);
						st.setType(dataInsertTable[rowcnt][36]);
						//Objects attached
						//DefaultStorageCapacity STUFF
						st.setDefaultStorageCapacity(scc);
						
						System.out.println("\t\t\t\tAdding StorageType object to objsArr, that contains: "+st.getType());
						++objcnt;
						objsArr[objcnt]=st;
					}
					
				stc.setStorageType(st);
				
				System.out.println("\t\t\t\tAdding StorageContainer object to objsArr, that contains: "+stc.getNumber());
				++objcnt;
				objsArr[objcnt]=stc;
				
			ts.setStorageContainer(stc);
			
			System.out.println("\t\t\t\tAdding TissueSpecimen object to objsArr, that contains: "+ts.getType());
			++objcnt;
			objsArr[objcnt]=ts;
						
			System.out.println("\t\t\t\t("+rowcnt+") Calling create(objsArr) to create all the objects...\n");

			for(int i=0;i<objsArr.length;i++){
				System.out.println("\t\t\t\t objsArr["+i+"] = "+objsArr[i]);
			}
			create(objsArr);
			
			++fndcnt;

	     }
		
		System.out.println("\n\n\t\t\t\tCreated ("+fndcnt+") Records, Times ("+objcnt+") objects...\n");
		HibernateUtil.closeSession();
	}
	
}