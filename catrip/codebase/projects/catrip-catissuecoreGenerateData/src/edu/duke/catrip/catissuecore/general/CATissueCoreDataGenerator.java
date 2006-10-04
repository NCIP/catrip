/**
 * Created on Aug 24, 2006 by PEEDI002
 */
package edu.duke.catrip.catissuecore.general;

//my import statements
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolImpl;
import edu.wustl.catissuecore.domainobject.impl.CollectionProtocolRegistrationImpl;
import edu.wustl.catissuecore.domainobject.impl.DepartmentImpl;
import edu.wustl.catissuecore.domainobject.impl.InstitutionImpl;
import edu.wustl.catissuecore.domainobject.impl.ParticipantImpl;
import edu.wustl.catissuecore.domainobject.impl.ParticipantMedicalIdentifierImpl;
import edu.wustl.catissuecore.domainobject.impl.SiteImpl;
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
	public void buildParticipant(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildParticipant()");
		
		String[][] dataInsertTable = new String[maxrecs][10];
		int insrow=0;

		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
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
				
				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]+", "+dataInsertTable[insrow][7]+", "+dataInsertTable[insrow][8]);
				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
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
			//p.setAnnotationEventParametersCollection(accessionSet);
			
			//if(rowcnt<50) {
				System.out.println("\t\t\t\tCalling create(p) to insert: "+p.getFirstName()+", "+p.getMiddleName()+", "+p.getLastName()+", "+p.getGender()+", "+p.getBirthDate()+", "+p.getRace()+", "+p.getEthnicity()+", "+p.getSocialSecurityNumber());
				create(p);
			//}
	     }

	}
	
	//builds address recs in db
	public void buildAddress(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildAddress()");
		
		String[][] dataInsertTable = new String[maxrecs][6];
		int insrow=0;

		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
				//streets
				dataInsertTable[insrow][0]=col1[rowcnt];

				//cities
				dataInsertTable[insrow][1]=col2[rowcnt];

				//states
				dataInsertTable[insrow][2]=col3[rowcnt];

				//zip
				dataInsertTable[insrow][3]=col4[rowcnt];

				//phone
				dataInsertTable[insrow][4]=col5[rowcnt];

				//fax
				dataInsertTable[insrow][5]=col6[rowcnt];
				
				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]);
				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			AddressImpl a = new AddressImpl();
			//p.setId(Long.valueOf(globalId));
			a.setStreet(dataInsertTable[rowcnt][0]);
			a.setCity(dataInsertTable[rowcnt][1]);
			a.setState(dataInsertTable[rowcnt][2]);
			a.setZipCode(dataInsertTable[rowcnt][3]);
			a.setCountry("United States");
			a.setPhoneNumber(dataInsertTable[rowcnt][4]);
			a.setFaxNumber(dataInsertTable[rowcnt][5]);
			
			//if(rowcnt<50) {
				System.out.println("\t\t\t\tCalling create(p) to insert: "+a.getStreet()+", "+a.getCity()+", "+a.getState()+", "+a.getZipCode()+", "+a.getCountry()+", "+a.getPhoneNumber()+", "+a.getFaxNumber());
				create(a);
			//}
	     }

	}
	
	
	//builds InstitutionInstitution recs in db
	public void buildInstitutionDept(int maxrecs, String[] col1, String[] col2) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildInstitution()");
		
		String[][] dataInsertTable = new String[maxrecs][2];
		int insrow=0;

		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
				//institutions
				dataInsertTable[insrow][0]=col1[rowcnt];
				
				//departments
				dataInsertTable[insrow][1]=col2[rowcnt];

				
				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]);
				insrow++;
		}

		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			InstitutionImpl i = new InstitutionImpl();
			DepartmentImpl d = new DepartmentImpl();
			i.setName(dataInsertTable[rowcnt][0]);
			System.out.println("\t\t\t\tCalling create(i) to insert: "+i.getName());
			create(i);
			d.setName(dataInsertTable[rowcnt][1]);
			System.out.println("\t\t\t\tCalling create(d) to insert: "+d.getName());
			create(d);

	     }

	}
	
	//builds CancerResearchGroup recs in db
	public void buildCancerResearchGroup(int maxrecs, String[] col1) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildCancerResearchGroup()");
		
		String[][] dataInsertTable = new String[maxrecs][1];
		int insrow=0;

		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
				//institutions
				dataInsertTable[insrow][0]=col1[rowcnt];
				
				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]);
				insrow++;
		}

		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			CancerResearchGroupImpl crg = new CancerResearchGroupImpl();
			crg.setName(dataInsertTable[rowcnt][0]);
			System.out.println("\t\t\t\tCalling create(i) to insert: "+crg.getName());
			create(crg);

	     }

	}
	
	//builds users recs in db
	public void buildUser(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildUser()");
		
		String[][] dataInsertTable = new String[maxrecs][7];
		int insrow=0;
		List result=null;
		List result2=null;
		List result3=null;
		List result4=null;

		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
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

				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]+", "+dataInsertTable[insrow][6]);
				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        result = new ArrayList();
	    result = session.createQuery("from AddressImpl").list();
	    tx.commit();
	    
        result2 = new ArrayList();
	    result2 = session.createQuery("from CancerResearchGroupImpl").list();
	    tx.commit();
	    
        result3 = new ArrayList();
	    result3 = session.createQuery("from DepartmentImpl").list();
	    tx.commit();
	    
        result4 = new ArrayList();
	    result4 = session.createQuery("from InstitutionImpl").list();
	    tx.commit();
	    
	    HibernateUtil.closeSession();
	    
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			UserImpl u = new UserImpl();
			//p.setId(Long.valueOf(globalId));
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
			
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	AddressImpl a = (AddressImpl) result.get(i);

			    	if(i==rowcnt){
			    		u.setAddress(null);
			    		System.out.println("\t\t\t\tFound Address: "+a.getCity());
			    		break;
			    	}
			    }
		    }

		    if(result2.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	CancerResearchGroupImpl crg = (CancerResearchGroupImpl) result2.get(i);
			    	if(i==rowcnt){
			    		u.setCancerResearchGroup(crg);
			    		System.out.println("\t\t\t\tFound CancerResearchGroup: "+crg.getName());
			    		break;
			    	}
			    }
		    }

		    if(result3.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	DepartmentImpl d = (DepartmentImpl) result3.get(i);
			    	if(i==rowcnt){
			    		u.setDepartment(d);
			    		System.out.println("\t\t\t\tFound Department: "+d.getName());
			    		break;
			    	}
			    }
		    }

		    if(result4.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	InstitutionImpl in = (InstitutionImpl) result4.get(i);
			    	if(i==rowcnt){
			    		u.setInstitution(in);
			    		System.out.println("\t\t\t\tFound Institution: "+in.getName());
			    		break;
			    	}
			    }
		    }		

				System.out.println("\t\t\t\tCalling create(u) to insert: "+u.getActivityStatus()+", "+u.getComments()+", "+u.getEmailAddress()+", "+u.getFirstName()+", "+u.getLastName()+", "+u.getLoginName()+", "+u.getPassword()+", "+u.getStartDate());

				create(u);

	     }

	}
	
	public void buildSite(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildSite()");
			
			String[][] dataInsertTable = new String[maxrecs][3];
			int insrow=0;
			
			System.out.println("\t\t\t\tArray size is: "+col1.length);
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//SITE
					dataInsertTable[insrow][0]=col1[randomInRange(0,maxrecs-1)];
					//SITE TYPE
					dataInsertTable[insrow][1]=col2[randomInRange(0,maxrecs-1)];
					//EMAIL
					dataInsertTable[insrow][2]=col3[randomInRange(0,maxrecs-1)];
					insrow++;
			}
				
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	
	        result = new ArrayList();
		    result = session.createQuery("from AddressImpl").list();
		    tx.commit();
		    HibernateUtil.closeSession();
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				SiteImpl s = new SiteImpl();
				s.setName(dataInsertTable[rowcnt][0]);
				s.setType(dataInsertTable[rowcnt][1]);
				s.setEmailAddress(dataInsertTable[rowcnt][2]);
				s.setAddress(null);
				s.setCoordinator(null);

			    if(result.size()>0){
				    //for (int i = 0; i<result.size(); i++) {
				    for (int i = 0; i<maxrecs; i++) {
				    	AddressImpl a = (AddressImpl) result.get(i);

				    	if(i==rowcnt){
				    		s.setAddress(a);
				    		System.out.println("\t\t\t\tFound Street Address: "+a.getStreet());
				    		break;
				    	}
				    }
			    }

				System.out.println("\t\t\t\tCalling create(s) to insert: "+s.getName()+", "+s.getType()+", "+s.getEmailAddress());

				create(s);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSite()");
	}
	
	public void buildStorageContCapacity(int maxrecs,String[] col1,String[] col2) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildStorageContCapacity()");
			
			String[][] dataInsertTable = new String[maxrecs][3];
			int insrow=0;
			
			System.out.println("\t\t\t\tArray size is: "+col1.length);
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//ONE DIM
					dataInsertTable[insrow][0]=col1[rowcnt];
					//TWO DIM
					dataInsertTable[insrow][1]=col1[rowcnt];
					insrow++;
			}
				
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				StorageContainerCapacityImpl c = new StorageContainerCapacityImpl();
				c.setOneDimensionCapacity(Integer.valueOf(dataInsertTable[rowcnt][0]));
				c.setTwoDimensionCapacity(Integer.valueOf(dataInsertTable[rowcnt][1]));

				System.out.println("\t\t\t\tCalling create(c) to insert: "+c.getOneDimensionCapacity()+", "+c.getTwoDimensionCapacity());

				create(c);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSite()");
	}
	
	public void buildStorageType(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildStorageType()");
			
			String[][] dataInsertTable = new String[maxrecs][3];
			int insrow=0;
			
			System.out.println("\t\t\t\tArray size is: "+col1.length);
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//Storage Type
					dataInsertTable[insrow][0]=col1[rowcnt];
					//One Dim Label
					dataInsertTable[insrow][1]=col2[rowcnt];
					//Two Dim Label
					dataInsertTable[insrow][2]=col3[rowcnt];
					insrow++;
			}
				
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				StorageTypeImpl s = new StorageTypeImpl();
				s.setType(dataInsertTable[rowcnt][0]);
				System.out.println("\t\t\t\tType: "+s.getType());

				s.setOneDimensionLabel(dataInsertTable[rowcnt][1]);
				s.setTwoDimensionLabel(dataInsertTable[rowcnt][2]);
				int end = dataInsertTable[rowcnt][0].indexOf("-");
				if(end==0){
					s.setDefaultTempratureInCentigrade(Double.valueOf(dataInsertTable[rowcnt][0].substring(0, 3)));
				}else{
					s.setDefaultTempratureInCentigrade(Double.valueOf(0));
				}

				s.setDefaultStorageCapacity(null);
				System.out.println("\t\t\t\tCalling create(s) to insert: "+s.getType()+", "+s.getOneDimensionLabel()+", "+s.getTwoDimensionLabel()+", "+s.getDefaultTempratureInCentigrade());

				I GET THE FOLLOWING ERROR:
					junit.framework.AssertionFailedError
					at junit.framework.Assert.fail(Assert.java:47)
					at junit.framework.Assert.assertTrue(Assert.java:20)
					at junit.framework.Assert.assertTrue(Assert.java:27)
				create(s);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSite()");
	}
	
	public void buildStorageContainer(int maxrecs,String[] col1) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildStorageContainer()");
			
			String[][] dataInsertTable = new String[maxrecs][1];
			int insrow=0;
							
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//Storage Container
					dataInsertTable[insrow][0]=col1[rowcnt];
					insrow++;
			}
			
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				StorageContainerImpl c = new StorageContainerImpl();
				c.setNumber(Integer.valueOf(rowcnt));
				c.setTempratureInCentigrade(Double.valueOf(0));
				c.setIsFull(Boolean.TRUE);
				c.setBarcode(dataInsertTable[rowcnt][0]);
				c.setActivityStatus("Active");
				c.setStorageType(null);
				c.setSite(null);
				c.setStorageContainerCapacity(null);
				c.setPositionDimensionOne(Integer.valueOf(randomInRange(0,9)));
				c.setPositionDimensionTwo(Integer.valueOf(randomInRange(0,9)));
				

				System.out.println("\t\t\t\tCalling create(c) to insert: "+c.getNumber()+", "+c.getTempratureInCentigrade()+", "+c.getIsFull()+", "+c.getBarcode()+", "+c.getActivityStatus()+", "+c.getPositionDimensionOne()+", "+c.getPositionDimensionTwo());

				I GET THE FOLLOWING ERROR:
					junit.framework.AssertionFailedError
					at junit.framework.Assert.fail(Assert.java:47)
					at junit.framework.Assert.assertTrue(Assert.java:20)
					at junit.framework.Assert.assertTrue(Assert.java:27)
				create(c);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSite()");
	}
	
	public void buildCollProtReg(int maxrecs,String[] col1,String[] col2) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildCollProtReg()");
			
			String[][] dataInsertTable = new String[maxrecs][2];
			int insrow=0;
			
			System.out.println("\t\t\t\tArray size is: "+col1.length);
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//ProtPartID
					dataInsertTable[insrow][0]=col1[randomInRange(0,maxrecs-1)];
					//RegDate
					dataInsertTable[insrow][1]=col2[rowcnt];
					insrow++;
			}
				
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				CollectionProtocolRegistrationImpl r = new CollectionProtocolRegistrationImpl();
				r.setProtocolParticipantIdentifier(dataInsertTable[rowcnt][0]);
				SimpleDateFormat sdf = new SimpleDateFormat( "MM/dd/yyyy" );
				Date sdate = sdf.parse( dataInsertTable[rowcnt][1] );
				r.setRegistrationDate(sdate);
				r.setActivityStatus("Active");
				r.setCollectionProtocol(null);
				r.setParticipant(null);
			
				System.out.println("\t\t\t\tCalling create(s) to insert: "+r.getProtocolParticipantIdentifier()+", "+r.getRegistrationDate()+", "+r.getActivityStatus());

				I GET THE FOLLOWING ERROR:
					junit.framework.AssertionFailedError
					at junit.framework.Assert.fail(Assert.java:47)
					at junit.framework.Assert.assertTrue(Assert.java:20)
					at junit.framework.Assert.assertTrue(Assert.java:27)
				create(r);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSite()");
	}
	
	
	public void buildSpecimenChar(int maxrecs,String[] col1,String[] col2,String[] col3) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildSpecimenChar()");
			
			String[][] dataInsertTable = new String[maxrecs][3];
			int insrow=0;
			
			System.out.println("\t\t\t\tArray size is: "+col1.length);
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//Tissue Site
					dataInsertTable[insrow][0]=col1[rowcnt];
					//Tissue Side
					dataInsertTable[insrow][1]=col2[rowcnt];
					//Path Stat
					dataInsertTable[insrow][2]=col3[rowcnt];
					insrow++;
			}
				
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				SpecimenCharacteristicsImpl sc = new SpecimenCharacteristicsImpl();
				
				sc.setTissueSite(dataInsertTable[rowcnt][0]);
				sc.setTissueSide(dataInsertTable[rowcnt][1]);
				sc.setPathologicalStatus(dataInsertTable[rowcnt][2]);

				System.out.println("\t\t\t\tCalling create(s) to insert: "+sc.getTissueSite()+", "+sc.getTissueSide()+", "+sc.getPathologicalStatus());

				create(sc);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSpecimenChar()");
	}
	
	//builds users recs in db
	public void buildSpecimen(int maxrecs, String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6) throws ParseException

	{
		
		if (DEBUG) System.out.println("\tInside buildSpecimen()");
		
		String[][] dataInsertTable = new String[maxrecs][6];
		int insrow=0;
		List result=null;

		String curID="";
		System.out.println("\t\t\t\tArray size is: "+col1.length);
		//load the arrays
		for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
			System.out.println("\t\t\t\tInside loop");
				//SpecimenClass
				dataInsertTable[insrow][0]=col1[rowcnt];

				//SpecimenType
				dataInsertTable[insrow][1]=col2[rowcnt];

				//SpecimenQuantity
				dataInsertTable[insrow][2]=col3[rowcnt];

				//SpecimenQuantityAvail
				dataInsertTable[insrow][3]=col4[rowcnt];

				//SpecimenCmmts
				dataInsertTable[insrow][4]=col5[rowcnt];

				//SpecimenBarcodes
				dataInsertTable[insrow][5]=col6[rowcnt];

				System.out.println("\t\t\t\tLoading Data Array["+insrow+"] with: "+dataInsertTable[insrow][0]+", "+dataInsertTable[insrow][1]+", "+dataInsertTable[insrow][2]+", "+dataInsertTable[insrow][3]+", "+dataInsertTable[insrow][4]+", "+dataInsertTable[insrow][5]);
				insrow++;
		}
			//TODO: WRITE CODE TO insert current tables' recs into db
		//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
		for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
			//SpecimenImpl s = new SpecimenImpl();
			
			TissueSpecimenImpl ts = new TissueSpecimenImpl();
			
			ts.setType(dataInsertTable[rowcnt][1]);
			ts.setActivityStatus("Active");
			ts.setAvailable(true);
			ts.setBarcode(dataInsertTable[rowcnt][5]);
			ts.setComments(dataInsertTable[rowcnt][4]);
			ts.setPositionDimensionOne(null);
			ts.setPositionDimensionTwo(null);

			
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();
	
	        result = new ArrayList();
		    result = session.createQuery("from AddressImpl").list();
		    tx.commit();
		    //HibernateUtil.closeSession();
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	Address a = (Address) result.get(i);

			    	if(i==rowcnt){
			    		u.setAddress(null);
			    		System.out.println("\t\t\t\tFound Address"+a);
			    		break;
			    	}
			    }
		    }
		    
	        result = new ArrayList();
		    result = session.createQuery("from CancerResearchGroupImpl").list();
		    tx.commit();
		    //HibernateUtil.closeSession();
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	CancerResearchGroup crg = (CancerResearchGroup) result.get(i);
			    	if(i==rowcnt){
			    		u.setCancerResearchGroup(crg);
			    		System.out.println("\t\t\t\tFound CancerResearchGroup"+crg);
			    		break;
			    	}
			    }
		    }
		    
	        result = new ArrayList();
		    result = session.createQuery("from DepartmentImpl").list();
		    tx.commit();
		    //HibernateUtil.closeSession();
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	Department d = (Department) result.get(i);
			    	if(i==rowcnt){
			    		u.setDepartment(d);
			    		System.out.println("\t\t\t\tFound Department"+d);
			    		break;
			    	}
			    }
		    }
		    
	        result = new ArrayList();
		    result = session.createQuery("from InstitutionImpl").list();
		    tx.commit();
		    HibernateUtil.closeSession();
		    if(result.size()>0){
			    //for (int i = 0; i<result.size(); i++) {
			    for (int i = 0; i<maxrecs; i++) {
			    	Institution in = (Institution) result.get(i);
			    	if(i==rowcnt){
			    		u.setInstitution(in);
			    		System.out.println("\t\t\t\tFound Institution: "+in);
			    		break;
			    	}
			    }
		    }		

				System.out.println("\t\t\t\tCalling create(u) to insert: "+u.getActivityStatus()+", "+u.getComments()+", "+u.getEmailAddress()+", "+u.getFirstName()+", "+u.getLastName()+", "+u.getLoginName()+", "+u.getPassword()+", "+u.getStartDate());
I GET THE FOLLOWING ERROR:
	junit.framework.AssertionFailedError
	at junit.framework.Assert.fail(Assert.java:47)
	at junit.framework.Assert.assertTrue(Assert.java:20)
	at junit.framework.Assert.assertTrue(Assert.java:27)
				create(u);

	     }

	}
	
	public void buildParticipantIdent(int maxrecs,String[] col1) throws ParseException
	{
			
			if (DEBUG) System.out.println("\tInside buildParticipantIdent()");
			
			String[][] dataInsertTable = new String[maxrecs][4];
			int insrow=0;
			List result=null;
			List result2=null;
			
			//load the array
			for (int rowcnt = 0; rowcnt < col1.length; rowcnt++) {
					//MRN
					dataInsertTable[insrow][0]=col1[randomInRange(0,maxrecs-1)];
					insrow++;
			}
				
	        Session session = HibernateUtil.currentSession();
	        Transaction tx = session.beginTransaction();

	        result = new ArrayList();
		    result = session.createQuery("from ParticipantImpl").list();
		    tx.commit();

	        result2 = new ArrayList();
		    result2 = session.createQuery("from SiteImpl").list();
		    tx.commit();
		    
		    HibernateUtil.closeSession();
		    
			//for (int rowcnt = 0; rowcnt < dataInsertTable.length; rowcnt++) {
			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
				ParticipantMedicalIdentifierImpl pmi = new ParticipantMedicalIdentifierImpl();
						
				pmi.setMedicalRecordNumber(dataInsertTable[rowcnt][0]);
			       	
			    if(result.size()>0){
				    for (int i = 0; i<maxrecs; i++) {
				    	ParticipantImpl obj = (ParticipantImpl) result.get(i);

				    	if(i==rowcnt){
				    		pmi.setParticipant(obj);
				    		System.out.println("\t\t\t\tFound Part. Name: "+obj.getLastName());
				    		break;
				    	}
				    }
			    }
			    
			    if(result2.size()>0){
				    for (int i = 0; i<maxrecs; i++) {
				    	SiteImpl obj = (SiteImpl) result2.get(i);

				    	if(i==rowcnt){
				    		pmi.setSite(obj);
				    		System.out.println("\t\t\t\tFound Site Name: "+obj.getName());
				    		break;
				    	}
				    }
			    }
			       		
					//if(i<5) {
						//System.out.println("\t\t\t\tCalling create(pmi) to insert: "+pmi.getMedicalRecordNumber()+", "+pmi.getParticipant());
						create(pmi);
					//}
			    }
		    
		    System.out.println("\t\t\t\tExiting buildParticipantIdent()");
	}
	
	public void buildUserInstDeptAdd(int maxrecs,String[] col1,String[] col2,String[] col3,String[] col4,String[] col5,String[] col6,String[] col7,String[] col8,String[] col9,String[] col10,String[] col11,String[] col12,String[] col13,String[] col14,String[] col15,String[] col16) throws ParseException
	{
			List result=null;
			if (DEBUG) System.out.println("\tInside buildSiteInstDeptAdd()");
			
			String[][] dataInsertTable = new String[maxrecs][16];
			
			Object[] objsArr = new Object[5];
			
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
					
					insrow++;
			}

			for (int rowcnt = 0; rowcnt < maxrecs; rowcnt++) {
							
				int objcnt=-1;
			//ADDRESS STUFF
				AddressImpl a = new AddressImpl();
				a.setStreet(dataInsertTable[rowcnt][9]);
				a.setCity(dataInsertTable[rowcnt][10]);
				a.setState(dataInsertTable[rowcnt][11]);
				a.setZipCode(dataInsertTable[rowcnt][12]);
				a.setCountry("United States");
				a.setPhoneNumber(dataInsertTable[rowcnt][13]);
				a.setFaxNumber(dataInsertTable[rowcnt][14]);
				System.out.println("\t\t\t\tAdding Address object to objsArr, that contains: "+a.getStreet()+", "+a.getCity()+", "+a.getState()+", "+a.getZipCode()+", "+a.getCountry()+", "+a.getPhoneNumber()+", "+a.getFaxNumber());
				++objcnt;
				objsArr[objcnt]=a;

			//INSTITUTION STUFF
				InstitutionImpl i = new InstitutionImpl();
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
				System.out.println("\t\t\t\tAdding Department object to objsArr, that contains: "+crg.getName());			
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
				
				System.out.println("\t\t\t\tAdding Institution object to objsArr, that contains: "+u.getActivityStatus()+", "+u.getComments()+", "+u.getEmailAddress()+", "+u.getFirstName()+", "+u.getLastName()+", "+u.getLoginName()+", "+u.getPassword()+", "+u.getStartDate());
				//create(u);
				++objcnt;
				objsArr[objcnt]=u;
				
				System.out.println("\t\t\t\tCalling create(objsArr) to save/commit all associated objects, in the array of objects...");
				
			//CALLING THE CREATE METHOD, TO USE THE HIBERNATE APIS (save,commit,etc)
				create(objsArr);
				
				//create(u);

		     }
		    
		    System.out.println("\t\t\t\tExiting buildSite()");
	}
	
//	private edu.wustl.catissuecore.domainobject.Department department; 
//    public edu.wustl.catissuecore.domainobject.Department getDepartment(){ 
//               ApplicationService applicationService = ApplicationService.getApplicationService(); 
//      edu.wustl.catissuecore.domainobject.User thisIdSet = new edu.wustl.catissuecore.domainobject.impl.UserImpl(); 
//      thisIdSet.setId(this.getId()); 
//       
//      try { 
//         java.util.List resultList = applicationService.search("edu.wustl.catissuecore.domainobject.Department", thisIdSet);                      
//            if (resultList!=null && resultList.size()>0) { 
//               department = (edu.wustl.catissuecore.domainobject.Department)resultList.get(0); 
//            } 
//          
//      } catch(Exception ex)  
//      {  
//               System.out.println("User:getDepartment throws exception ... ..."); 
//                 ex.printStackTrace();  
//      } 
//      return department;                
//             } 
}