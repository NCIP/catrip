/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.pitt.cabig.cae.domain.general;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.cfg.*;

import edu.duke.catrip.cae.domain.general.Accession;
import edu.duke.catrip.cae.domain.general.Participant;
import edu.duke.catrip.cae.domain.general.Specimen;
import edu.pitt.cabig.cae.domain.breast.*;

import junit.framework.TestCase;
/** 
 * This unit test tests the retrieve of all the CAE objects. This also tests the insert of
 * Specimen, Accession and Participant.
 * @testType unit 
 */ 

public class HarmonizedObjectsTest extends TestCase {
	private static int globalId = 44;
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public static void testParticipant() {
		Participant p = new Participant();
		p.setId(Long.valueOf(globalId));
		p.setLastName("last name");
		p.setFirstName("first name");
		p.setRace("race");
		p.setEthnicity("ethnicity");
		Set<edu.duke.catrip.cae.domain.general.Accession> accessionSet = new HashSet<Accession>();
		for (int i = 0; i < 3; i++) {
			edu.duke.catrip.cae.domain.general.Accession acc = new Accession();
			acc.setAccessionDate(new Date(0));
			acc.setDiseaseType("diseasetype");
			acc.setSurgicalPathologyNumber("SurgicalPathologyNumber");
			accessionSet.add(acc);
		}
		p.setAccessionCollection(accessionSet);
		create(p);
	}

	public static void testBreastSpecimenCharacteristics() {
		BreastSpecimenCharacteristics p = new BreastSpecimenCharacteristics();
		p.setId(Long.valueOf(globalId));
		p.setLaterality("laterality");
		p.setLateralityMVR("lateralityMVR");
		p.setLymphNodeSamplingProcedure("lymphNodeSamplingProcedure");
		create(p);
	}

	public static void testSpecimen() {
		edu.duke.catrip.cae.domain.general.Specimen p = new edu.duke.catrip.cae.domain.general.Specimen();
		//p.setId(Long.valueOf(globalId));
		p.setSurgicalLabel("SurgicalLabel");
		Set<Specimen> objSet = new HashSet<Specimen>();
		for (int i = 0; i < 2; i++) {
			edu.duke.catrip.cae.domain.general.Specimen s = new edu.duke.catrip.cae.domain.general.Specimen();
			s.setSurgicalLabel("SurgicalLabel " + i);
			objSet.add(s);
		}
		p.setSpecimenCollection(objSet);
		create(p);
	}

	public static void testEventParameters() {
		Set<AnnotationSet> annSet = new HashSet<AnnotationSet>();
		AnnotationEventParameters aep = new AnnotationEventParameters();
		aep.setId(Long.valueOf(globalId));
		aep.setTimeStamp(new Date(0));
		aep.setSource("source");
		aep.setSourceDate(new Date(0));

		// create some annotations sets
		for (int i = 0; i < 2; i++) {
			ThreeDimensionalTumorSize set = new ThreeDimensionalTumorSize();
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
		Set<Accession> objSet = new HashSet<Accession>();
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

	public static void create(Object obj) {
		Transaction tx = null;
		Session session = currentSession();
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

	public static void testRetrieveOtherBreastCancerHistopathologicGrade() throws Exception  {
		Session session = currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from OtherBreastCancerHistopathologicGrade").list();

		tx.commit();
		closeSession();

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
		Session session = currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from NottinghamHistopathologicGrade").list();

		tx.commit();
		closeSession();

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

	public static void testRetrieveParticipantAccession() throws Exception  {
		Session session = currentSession();
		Transaction tx = session.beginTransaction();
		int j;

		List result = new ArrayList();
		result = session.createQuery("from edu.duke.catrip.cae.domain.general.Participant").list();

		tx.commit();
		closeSession();
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



	public static void testRetrieveInvasiveBreastCarcinoma() throws Exception  {
		Session session = currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from InvasiveBreastCarcinoma").list();

		tx.commit();
		closeSession();

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

	public static void testRetrieveInvasiveBreastCarcinomaNeoplasmHistologicType() throws Exception  {
		Session session = currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from InvasiveBreastCarcinomaNeoplasmHistologicType").list();

		tx.commit();
		closeSession();

		for (int i = 0; i<result.size(); i++) {
			InvasiveBreastCarcinomaNeoplasmHistologicType obj = (InvasiveBreastCarcinomaNeoplasmHistologicType) result.get(i);

			System.out.println("ID is " + obj.getId());
			System.out.println("Name is " + obj.getName());
			System.out.println("name mvr is " + obj.getNameMVR());
			System.out.println("other name is " + obj.getOtherName());
		}
	}

	public static void testRetreiveThreeDimensionalSize() throws Exception  {
		Session session = currentSession();
		Transaction tx = session.beginTransaction();
		int j;

		List result = new ArrayList();
		result = session.createQuery("from ThreeDimensionalSize").list();

		tx.commit();
		closeSession();
		for (int i = 0; i<result.size(); i++) {
			ThreeDimensionalTumorSize obj = (ThreeDimensionalTumorSize) result.get(i);
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
		Session session = currentSession();
		Transaction tx = session.beginTransaction();


		List result = new ArrayList();
		result = session.createQuery("from AdditionalFindings").list();

		tx.commit();
		closeSession();


		for (int i = 0; i<result.size(); i++) {
			AdditionalFindings obj = (AdditionalFindings) result.get(i);

			System.out.println("ID is : " + obj.getId());
			System.out.println("other Findings is : " + obj.getOtherFindings());
		}
	}

	public static void testRetrieveBreastCancerTNMFinding() throws Exception  {
		Session session = currentSession();
		Transaction tx = session.beginTransaction();
		//int j;

		List result = new ArrayList();
		result = session.createQuery("from BreastCancerTNMFinding").list();

		tx.commit();
		closeSession();

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


	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		// Open a new Session, if this Thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);
		if (s != null)
			s.close();
	}
}