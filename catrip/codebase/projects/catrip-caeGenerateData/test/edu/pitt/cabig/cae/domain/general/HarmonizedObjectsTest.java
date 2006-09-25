package edu.pitt.cabig.cae.domain.general;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.catrip.cae.domain.general.Accession;
import edu.duke.catrip.cae.domain.general.Participant;
import edu.pitt.cabig.cae.dao.util.HibernateUtil;
import edu.pitt.cabig.cae.domain.breast.BreastCancerTNMFindingTest;
/** 
 * This unit test tests the retrieve of all the CAE objects. This also tests the insert of
 * Specimen, Accession and Participant.
 * @testType unit 
 */ 

public class HarmonizedObjectsTest extends TestCase {
	private static int globalId = 44;
	private static Log log = LogFactory.getLog(BreastCancerTNMFindingTest.class);
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


//	public static void testParticipant() {
//		Participant p = new Participant();
//		p.setId(Long.valueOf(globalId));
//		p.setLastName("simple ttttest");
//		p.setFirstName("first name");
//		p.setRace("race");
//		p.setEthnicity("ethnicity");
//		Set accessionSet = new HashSet();
//		for (int i = 0; i < 3; i++) {
//			edu.duke.catrip.cae.domain.general.Accession acc = new Accession();
//			acc.setAccessionDate(new Date(0));
//			acc.setDiseaseType("diseasetype");
//			acc.setSurgicalPathologyNumber("SurgicalPathologyNumber");
//			accessionSet.add(acc);
//		}
//		p.setAccessionCollection(accessionSet);
//		create(p);
//	}
/*	
	public static void testSpecimen() {
		edu.duke.catrip.cae.domain.general.Specimen p = new edu.duke.catrip.cae.domain.general.Specimen();
		//p.setId(Long.valueOf(globalId));
		p.setIdentifier("identifier");
		p.setSurgicalLabel("SurgicalLabel");
		Set objSet = new HashSet();
		for (int i = 0; i < 2; i++) {
			edu.duke.catrip.cae.domain.general.Specimen s = new edu.duke.catrip.cae.domain.general.Specimen();
			s.setIdentifier("identifier " + i);
			s.setSurgicalLabel("SurgicalLabel " + i);
			objSet.add(s);
		}
		p.setSpecimenCollection(objSet);
		create(p);
	}
	
	public static void testEventParameters() {
		Set<AnnotationSet> annSet = new HashSet();
		AnnotationEventParameters aep = new AnnotationEventParameters();
		aep.setId(Long.valueOf(globalId));
		aep.setTimeStamp(new Date(0));
		aep.setSource("source");
		aep.setSourceDate(new Date(0));
		
		// create some annotations sets
		for (int i = 0; i < 2; i++) {
			ThreeDimensionalSize set = new ThreeDimensionalSize();
			set.setId(Long.valueOf(i));
			annSet.add(set);
		}
		aep.setAnnotationSetCollection(annSet);
		create(aep);
		
	}



	public static void testAccession() {
		Participant pp = new Participant();
		pp.setId(Long.valueOf(globalId));
		pp.setLastName("last name" );
		pp.setFirstName("first name");
		pp.setRace("race");
		pp.setEthnicity("ethnicity");
		//p.setId(Long.valueOf(100));
		Set objSet = new HashSet();
		for (int i = 0; i < 3; i++) {
			edu.duke.catrip.cae.domain.general.Accession p = new edu.duke.catrip.cae.domain.general.Accession();
			p.setAccessionDate(new Date(0));
			p.setDiseaseType("disease type " + i);
			p.setSurgicalPathologyNumber("surgicalPathologyNumber");
			objSet.add(p);
		}
		pp.setAccessionCollection(objSet);
		create(pp);
	}
	

	 public static void testRetrieveOtherBreastCancerHistopathologicGrade() throws Exception  {
			Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from OtherBreastCancerHistopathologicGrade").list();

	         tx.commit();
	         HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 OtherBreastCancerHistopathologicGrade obj = (OtherBreastCancerHistopathologicGrade) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("systemName is " + obj.getSystemName());
	             System.out.println("score is " + obj.getScore());
	             System.out.println("mitoticCount is " + obj.getMitoticCount());
	             System.out.println("systemName is " + obj.getScoreMVR());
	         }
	 }
	 
	 public static void testRetrieveNottinghamHistopathologicGrade() throws Exception  {
			Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from NottinghamHistopathologicGrade").list();

	         tx.commit();
	         HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 NottinghamHistopathologicGrade obj = (NottinghamHistopathologicGrade) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("systemName is " + obj.getTotalScoreMVR());
	             System.out.println("MitoticCount is " + obj.getMitoticCount());
	             System.out.println("NuclearPleomorphism is " + obj.getNuclearPleomorphism());
	             System.out.println("total score is " + obj.getTotalScore());
	             System.out.println("tubuleFormation " + obj.getTubuleFormation());
	         }
	 }
*/
	 public static void testRetrieveParticipantAccession() throws Exception  {
	        Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();
	         int j;

		         List result = new ArrayList();
		         result = session.createQuery("from edu.duke.catrip.cae.domain.general.Participant").list();

		           tx.commit();
		           HibernateUtil.closeSession();
		         for (int i = 0; i<result.size(); i++) {
		        	 Participant obj = (Participant) result.get(i);
		        	 System.out.println("*********************");
		        	 j = i;
		        	 System.out.print("#"+(j+1) + " - ");
		        	 System.out.println("ID is : " + obj.getId());
		             System.out.println("Last name is : " + obj.getLastName());
		             System.out.println("First name is : " + obj.getFirstName());
		             System.out.println("Accessions : ");
		             System.out.println("is null ? : " + (obj.getAccessionCollection() == null));
		              Collection sites = (Collection)obj.getAccessionCollection();
		               Iterator itr = sites.iterator();
		             while (itr.hasNext()) {
		            	 Accession a = (Accession)itr.next();
		                 System.out.println(a.getId());
		                 System.out.println(a.getDiseaseType());
		             }
		             
		         }
		 }

/*
	 public static void testRetrieveBreastSurgicalPathologySpecimen() throws Exception  {
			Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from BreastSurgicalPathologySpecimen").list();

	         tx.commit();
	         HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 BreastSurgicalPathologySpecimen obj = (BreastSurgicalPathologySpecimen) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("LateralityMVR is " + obj.getLateralityMVR());
	             System.out.println("Laterality is " + obj.getLaterality());
	             System.out.println("LymphNodeSamplingProcedure is " + obj.getLymphNodeSamplingProcedure());
	             System.out.println("OtherSurgicalProcedure is " + obj.getOtherSurgicalProcedure());
	             System.out.println("getSurgicalProcedureMVR " + obj.getSurgicalProcedureMVR());
	             
	               List sites = (List)obj.getSurgicalProcedure();
	               Iterator itr = sites.iterator();
	               while (itr.hasNext()) {
	                   System.out.println(itr.next());
	               }
	         }
	 }
	 
	 public static void testRetrieveInvasiveBreastCarcinoma() throws Exception  {
			Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from InvasiveBreastCarcinoma").list();

	         tx.commit();
	         HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 InvasiveBreastCarcinoma obj = (InvasiveBreastCarcinoma) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("LocationMVR is " + obj.getLocationMVR());
	             System.out.println("VenousLymphaticInvasion is " + obj.getVenousLymphaticInvasion());
	             
	             
	               List sites = (List)obj.getLocation();
	               Iterator itr = sites.iterator();
	               while (itr.hasNext()) {
	                   System.out.println(itr.next());
	               }
	               sites = (List)obj.getMicrocalcificationLocation();
	               itr = sites.iterator();
	               while (itr.hasNext()) {
	                   System.out.println(itr.next());
	               }
	         }
	 }
 
	 public static void testRetrieveBreastPositiveSurgicalMargin() throws Exception  {
	        Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from BreastPositiveSurgicalMargin").list();

	           tx.commit();
	           HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 BreastPositiveSurgicalMargin obj = (BreastPositiveSurgicalMargin) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("ExtentInvolvement is " + obj.getExtentInvolvement());
	             System.out.println("mvr is " + obj.getMVR());
	             System.out.println("NeoplasmPresent is " + obj.getNeoplasmPresent());
	             System.out.println("OtherExtentInvolvement is " + obj.getOtherExtentInvolvement());
	             System.out.println("OtherSpatialRelationshipToPatient is " + obj.getOtherSpatialRelationshipToPatient());
	             System.out.println("SpatialRelationshipToPatient is " + obj.getSpatialRelationshipToPatient());
	               
	         }
	 }
	
	 public static void testRetrieveBreastNegativeSurgicalMargin() throws Exception  {
			Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from BreastNegativeSurgicalMargin").list();

	         tx.commit();
	         HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 BreastNegativeSurgicalMargin obj = (BreastNegativeSurgicalMargin) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("ClosestNeoplasmPresent is " + obj.getClosestNeoplasmPresent());
	             System.out.println("mvr is " + obj.getMVR());
	             System.out.println("DistanceToClosestNeoplasm is " + obj.getDistanceToClosestNeoplasm());
	             System.out.println("OtherSpatialRelationshipToPatient is " + obj.getOtherSpatialRelationshipToPatient());
	             System.out.println("SpatialRelationshipToPatient is " + obj.getSpatialRelationshipToPatient());
	               
	         }
	 }

	 public static void testRetrieveInvasiveBreastCarcinomaNeoplasmHistologicType() throws Exception  {
			Session session = HibernateUtil.currentSession();
	         Transaction tx = session.beginTransaction();

	         List result = new ArrayList();
	         result = session.createQuery("from InvasiveBreastCarcinomaNeoplasmHistologicType").list();

	         tx.commit();
	         HibernateUtil.closeSession();

	         for (int i = 0; i<result.size(); i++) {
	        	 InvasiveBreastCarcinomaNeoplasmHistologicType obj = (InvasiveBreastCarcinomaNeoplasmHistologicType) result.get(i);
	        	 
	        	    System.out.println("ID is " + obj.getId());
	             System.out.println("Name is " + obj.getName());
	             System.out.println("name mvr is " + obj.getNameMVR());
	             System.out.println("other name is " + obj.getOtherName());
	         }
	 }
	 
	 public static void testRetreiveThreeDimensionalSize() throws Exception  {
        Session session = HibernateUtil.currentSession();
         Transaction tx = session.beginTransaction();
         int j;

	         List result = new ArrayList();
	         result = session.createQuery("from ThreeDimensionalSize").list();

	           tx.commit();
	           HibernateUtil.closeSession();
	         for (int i = 0; i<result.size(); i++) {
	        	 ThreeDimensionalSize obj = (ThreeDimensionalSize) result.get(i);
	        	 System.out.println("*********************");
	        	 j = i;
	        	 System.out.print("#"+(j+1) + " - ");
	        	 System.out.println("ID is : " + obj.getId());
	             System.out.println("dditionalDimensionY is : " + obj.getAdditionalDimensionY());
	             System.out.println("name mvr is : " + obj.getMVR());
	             System.out.println("additionalDimensionZ : " + obj.getAdditionalDimensionZ());
	             System.out.println("GreatestDimension : " + obj.getGreatestDimension());
	             
	         }
	 }

	 public static void testRetrieveAdditionalFindings() throws Exception  {
         Session session = HibernateUtil.currentSession();
         Transaction tx = session.beginTransaction();


	         List result = new ArrayList();
	         result = session.createQuery("from AdditionalFindings").list();

	           tx.commit();
	           HibernateUtil.closeSession();
	          

	         for (int i = 0; i<result.size(); i++) {
	        	 AdditionalFindings obj = (AdditionalFindings) result.get(i);
	        	 
	        	    System.out.println("ID is : " + obj.getId());
	             System.out.println("other Findings is : " + obj.getOtherFindings());
	         }
	 }
	 
	 public static void testRetrieveBreastCancerTNMFinding() throws Exception  {
         Session session = HibernateUtil.currentSession();
         Transaction tx = session.beginTransaction();
         int j;

	         List result = new ArrayList();
	         result = session.createQuery("from BreastCancerTNMFinding").list();

	           tx.commit();
	           HibernateUtil.closeSession();
	           
		         for (int i = 0; i<result.size(); i++) {
		        	 BreastCancerTNMFinding obj = (BreastCancerTNMFinding) result.get(i);
	        	 //System.out.print("#"+(j+1) + " - ");
	        	 System.out.println("ID is : " + obj.getId());
	             System.out.println("DistantMetastasisFinding is : " + obj.getDistantMetastasisFinding());
	             System.out.println("category is : " + obj.getCategory());
	             System.out.println("OtherMetastaticAnatomicSite : " + obj.getOtherMetastaticAnatomicSite());
	             System.out.println("PrimaryTumorFinding : " + obj.getPrimaryTumorFinding());
		         }
	 }
*/
	public static void create(Object obj) {
		Transaction tx = null;
		Session session = HibernateUtil.currentSession();
		try {
			tx = session.beginTransaction();
			session.save(obj); 
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null )
				tx.rollback();
			assertTrue(false);
		}
	}
	 
	   
}