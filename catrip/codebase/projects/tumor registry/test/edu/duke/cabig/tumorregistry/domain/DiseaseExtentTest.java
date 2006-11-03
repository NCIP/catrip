package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;

public class DiseaseExtentTest extends TestCase {
	public DiseaseExtentTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(DiseaseExtentTest.class);
	}
	public void testActivityToDiagnosis() throws Exception {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(Long.valueOf(105));
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("infection");
				
		HormoneTherapy h = new HormoneTherapy();
		h.setId(Long.valueOf((108)));
		h.setAtLocalFacility(Boolean.valueOf(true));
		//activityCollection.add(h);		
		h.setDiagnosis(diagnosis);
		try{
			HibernateUtil.create(h);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testRecurranceToDistantSite() throws Exception {
		Recurrence recurrence = new Recurrence();
		recurrence.setId(Long.valueOf(105));
		recurrence.setDate(new java.util.Date(0));
		recurrence.setType("test");
				
		DistantSite distantSite = new DistantSite();
		distantSite.setId(Long.valueOf(105));
		distantSite.setName("test");
		Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
		distantSiteCollection.add(distantSite);
		
		try{
			//distantSite.setRecurrence(recurrence);
			//HibernateUtil.create(distantSite);

			recurrence.setDistantSiteCollection(distantSiteCollection);
			HibernateUtil.create(recurrence);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testFollowupToDiagnosis() throws Exception {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(Long.valueOf(104));
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("infection");
				
		Followup followup = new Followup();
		followup.setId(Long.valueOf((22)));
		followup.setContactMethod("contactMethod");
		followup.setCancerStatus("cancerStatus");
		followup.setDate(new java.sql.Date(0));
		
		followup.setDiagnosis(diagnosis);
		try{
			HibernateUtil.create(followup);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testDistantSiteToRecurrence() throws Exception {
		Recurrence recurrence = new Recurrence();
		recurrence.setId(Long.valueOf((1000)));
		recurrence.setType("type");
		recurrence.setDate(new java.sql.Date(0));
		
		DistantSite distantSite = new DistantSite();
		distantSite.setId(getNextDistantSiteId());
		distantSite.setName("name0");
		Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
		distantSiteCollection.add(distantSite);
		try{
			recurrence.setDistantSiteCollection(distantSiteCollection);
			HibernateUtil.create(recurrence);

			//distantSite.setRecurrence(recurrence);
			//HibernateUtil.create(distantSite);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testDistantSiteToDiseaseExtent() throws Exception {
		DiseaseExtent diseaseExtent = new DiseaseExtent();
		diseaseExtent.setId(getNextId());
		diseaseExtent.setBestAJCCStage("bestAJCCStage");
		diseaseExtent.setBestSEERSummaryStage("bestSEERsummaryStage");
		diseaseExtent.setClinicalAJCCStage("clinicalAJCCStage");
		
		DistantSite distantSite = new DistantSite();
		distantSite.setId(getNextDistantSiteId());
		distantSite.setName("name0");
		
		distantSite.setDiseaseExtent(diseaseExtent);
		try{
			HibernateUtil.create(distantSite);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	public void testDiseaseExtentToDiagnosis() throws Exception {
		DiseaseExtent diseaseExtent = new DiseaseExtent();
		diseaseExtent.setId(getNextId());
		diseaseExtent.setBestAJCCStage("bestAJCCStage");
		diseaseExtent.setBestSEERSummaryStage("bestSEERsummaryStage");
		diseaseExtent.setClinicalAJCCStage("clinicalAJCCStage");
		
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(Long.valueOf(103));
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("infection");
		
		diseaseExtent.setDiagnosis(diagnosis);
		try{
			HibernateUtil.create(diseaseExtent);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	/// TBD - class cast exception
	public void testInsert() throws Exception {
		// get this id so duplicate ids are created when this is run multiple times
		Long nextid = getNextDistantSiteId();
		DiseaseExtent diseaseExtent = new DiseaseExtent();
		diseaseExtent.setId(nextid);
		diseaseExtent.setBestAJCCStage("bestAJCCStage");
		diseaseExtent.setBestSEERSummaryStage("bestSEERsummaryStage");
		diseaseExtent.setClinicalAJCCStage("clinicalAJCCStage");
		diseaseExtent.setClinicalAJCCStageDescriptor("clinicalAJCCStageDescriptor");
		diseaseExtent.setClinicalMetStage("clinicalMetStage");
		diseaseExtent.setClinicalNodeStage("clinicalNodeStage");
		diseaseExtent.setClinicalTumorStage("clinicalTumorStage");
		diseaseExtent.setPathologicAJCCStage("pathologicAJCCStage");
		diseaseExtent.setPathologicAJCCStageDescriptor("pathologicAJCCStageDescriptor");
		diseaseExtent.setPathologicMetStage("pathologicMetStage");
		diseaseExtent.setPathologicNodeStage("pathologicNodeStage");
		diseaseExtent.setPathologicTumorStage("pathologicTumorStage");
		diseaseExtent.setRegionalNodesExamined(Integer.valueOf("23"));
		//diseaseExtent.setRegionalNodesPositive(Integer.valueOf("23"));
		diseaseExtent.setTnmEdition("tnmEdition");
		diseaseExtent.setTumorMarker1("tumorMarker1");
		diseaseExtent.setTumorMarker2("tumorMarker2");
		diseaseExtent.setTumorMarker3("tumorMarker3");
		diseaseExtent.setRegionalNodesExaminedQualifier("regionalNodesExaminedQualifier");
		diseaseExtent.setRegionalNodesPositiveQualifier("regionalNodesPositiveQualifier");
		diseaseExtent.setTumorSize(Float.valueOf("33.4"));

		Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
		Long distantSiteId = nextid;
		for (int i = 0; i <2; i++) {
			distantSiteId = new Long(distantSiteId.longValue() + 1);
			DistantSite distantSite = new DistantSite();
			distantSite.setId(distantSiteId);
			distantSite.setName("name0");
			distantSiteCollection.add(distantSite);
		}
		diseaseExtent.setDistantSiteCollection(distantSiteCollection);
		try{
			HibernateUtil.create(diseaseExtent);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

	public void testSelect() throws Exception {
		int k = 1;
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from DiseaseExtent").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i < result.size(); i++) {
			DiseaseExtent diseaseExtent = (DiseaseExtent) result.get(i);
			System.out.println("*******  Diseast Extent " + (k) + " *******");
			System.out.println("ID is " + diseaseExtent.getId());
			System.out.println("bestAJCCStage : " + diseaseExtent.getBestAJCCStage());
			System.out.println("bestSEERsummaryStage : " + diseaseExtent.getBestSEERSummaryStage());
			System.out.println("clinicalAJCCStage : " + diseaseExtent.getClinicalAJCCStage());
			System.out.println("clinicalAJCCStageDescriptor : " + diseaseExtent.getClinicalAJCCStageDescriptor());
			System.out.println("clinicalMetStage : " + diseaseExtent.getClinicalMetStage());
			System.out.println("clinicalNodeStage : " + diseaseExtent.getClinicalNodeStage());
			System.out.println("clinicalTumorStage : " + diseaseExtent.getClinicalTumorStage());
			System.out.println("pathologicAJCCStage : " + diseaseExtent.getPathologicAJCCStage());
			System.out.println("pathologicAJCCStageDescriptor : " + diseaseExtent.getPathologicAJCCStageDescriptor());
			System.out.println("pathologicMetStage : " + diseaseExtent.getPathologicMetStage());
			System.out.println("pathologicNodeStage : " + diseaseExtent.getPathologicNodeStage());
			System.out.println("pathologicTumorStage : " + diseaseExtent.getPathologicTumorStage());
			System.out.println("regionalNodesExamined : " + diseaseExtent.getRegionalNodesExamined());
			System.out.println("regionalNodesPositive : " + diseaseExtent.getRegionalNodesPositive());
			System.out.println("tnmEdition : " + diseaseExtent.getTnmEdition());
			System.out.println("tumorMarker1 : " + diseaseExtent.getTumorMarker1());
			System.out.println("tumorMarker2 : " + diseaseExtent.getTumorMarker2());
			System.out.println("tumorMarker3 : " + diseaseExtent.getTumorMarker3());
			System.out.println("TumorSize : " + diseaseExtent.getTumorSize());
			Set distantSiteCollection = diseaseExtent.getDistantSiteCollection();
			Iterator itr = distantSiteCollection.iterator();
			if (distantSiteCollection != null){
				System.out.println("*******  Distant Site " + (k++) + " *******");
				while (itr.hasNext()) {
					DistantSite distantSite = (DistantSite) itr.next();
					System.out.println("ID : " + distantSite.getId());
					System.out.println("Name : " + distantSite.getName());
				}
			}
		
		}

	}
	public Long getNextId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from DiseaseExtent where id = (select max(id) from DiseaseExtent)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			DiseaseExtent obj = (DiseaseExtent) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	public Long getNextDistantSiteId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from DistantSite where id = (select max(id) from DistantSite)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			DistantSite obj = (DistantSite) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}
	

} // end of class

