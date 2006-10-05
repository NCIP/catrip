package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;

public class CollaborativeStagingTest extends TestCase {
	public CollaborativeStagingTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(CollaborativeStagingTest.class);
	}
	public void testInsert() throws Exception {
		CollaborativeStaging staging = new CollaborativeStaging();
		staging.setId(getNextId());
		staging.setDerivedAJCCStage("derivedAJCCStage");
		staging.setDerivedMetDescriptor("DerivedMetDescriptor");
		staging.setDerivedMetStage("DerivedMetStage");
		staging.setDerivedNodeDescriptor("DerivedNodeDescriptor");
		staging.setDerivedTumorDescriptor("DerivedTumorDescriptor");
		staging.setDerivedTumorStage("DerivedTumorStage");
		staging.setLymphNodes("LymphNodes");
		staging.setLymphNodesAtEvaluation("LymphNodesAtEvaluation");
		staging.setMetAtDiagnosis("DerivedAJMetAtDiagnosisCCStage");
		staging.setMetEvaluation("MetEvaluation");
		staging.setSiteSpecificFactor1("SiteSpecificFactor1");
		staging.setSiteSpecificFactor2("SiteSpecificFactor2");
		staging.setSiteSpecificFactor4("SiteSpecificFactor4");
		staging.setSiteSpecificFactor5("SiteSpecificFactor5");
		staging.setSiteSpecificFactor6("SiteSpecificFactor6");
		staging.setDerivedNodeStage("derivedNodeStage");
		staging.setSiteSpecificFactor3("siteSpecificFactor3");
		staging.setDerivedSEERSummary1977("derivedSEERSummary1977");
		staging.setDerivedSEERSummary2000("derivedSEERSummary2000");
		staging.setExtension("extension");
		try{
			HibernateUtil.create(staging);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testSelect() throws Exception {

		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery("from CollaborativeStaging").list();

		tx.commit();
		HibernateUtil.closeSession();

		for (int i = 0; i < result.size(); i++) {
			CollaborativeStaging obj = (CollaborativeStaging) result.get(i);
			System.out.println("\n*******  CollaborativeStaging " + i +"  *******");
				System.out.println("collaborativeStaging ID : " + obj.getId());
				System.out.println("DerivedAJCCStage : " + obj.getDerivedAJCCStage());
				System.out.println("DerivedMetDescriptor : " + obj.getDerivedMetDescriptor());
				System.out.println("DerivedMetStage : " + obj.getDerivedMetStage());
				System.out.println("DerivedNodeDescriptor : " + obj.getDerivedNodeDescriptor());
				System.out.println("DerivedNodeStage( : " + obj.getDerivedNodeStage());
				System.out.println("DerivedSEERSummary1977 : " + obj.getDerivedSEERSummary1977());
				System.out.println("DerivedSEERSummary2000 : " + obj.getDerivedSEERSummary2000());
				System.out.println("DerivedTumorDescriptor : " + obj.getDerivedTumorDescriptor());
				System.out.println("DerivedTumorStage : " + obj.getDerivedTumorStage());
				System.out.println("Extension : " + obj.getExtension());
				System.out.println("LymphNodes : " + obj.getLymphNodes());
				System.out.println("LymphNodesAtEvaluation : " + obj.getLymphNodesAtEvaluation());
				System.out.println("DerivedAJMetAtDiagnosisCCStage : " + obj.getMetAtDiagnosis());
				System.out.println("MetEvaluation : " + obj.getMetEvaluation());
				System.out.println("SiteSpecificFactor1 : " + obj.getSiteSpecificFactor1());
				System.out.println("SiteSpecificFactor2 : " + obj.getSiteSpecificFactor2());
				System.out.println("SiteSpecificFactor3 : " + obj.getSiteSpecificFactor3());
				System.out.println("SiteSpecificFactor4 : " + obj.getSiteSpecificFactor4());
				System.out.println("SiteSpecificFactor5 : " + obj.getSiteSpecificFactor5());
				System.out.println("SiteSpecificFactor6 : " + obj.getSiteSpecificFactor6());
				
			}

	}
	public Long getNextId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from Diagnosis where id = (select max(id) from Diagnosis)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			Diagnosis obj = (Diagnosis) result.get(0);
			maxId = obj.getId();
		}
		
		maxId = new Long((maxId.longValue()));
System.out.println("********** id ********  " + maxId);
		return maxId;
	}

} // end of class

