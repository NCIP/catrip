package edu.duke.cabig.tumorregistry.domain;

import java.lang.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Date;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction; 

import edu.duke.cabig.tumorregistry.domain.HibernateUtil;

public class ActivitySummaryTest extends TestCase {
	public ActivitySummaryTest(String sTestName) {
		super(sTestName);
	}

	public void setUp() {

	}

	public void tearDown() {
	}

	public static Test suite() {
		return new TestSuite(ActivitySummaryTest.class);
	}

	public void testActivitySummaryToDiagnosis() throws Exception {
		ActivitySummary summary = new ActivitySummary();
		summary.setId(Long.valueOf(66));
		summary.setLocalDate(new Date(0));
		summary.setSummaryCharacterization("summaryCharacterization");
		summary.setSummaryDate(new Date(0));
		
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setId(Long.valueOf(66));
		diagnosis.setAgeAtDiagnosis(Integer.valueOf(34));
		diagnosis.setCauseOfDeath("infection");
		
		
		try{
			// does not work
			diagnosis.setActivitySummary(summary);
			HibernateUtil.create(diagnosis);
			
			// works
			//summary.setDiagnosis(diagnosis);
			//HibernateUtil.create(summary);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testInsertActivity() throws Exception {
		ActivitySummary summary = new ActivitySummary();
		summary.setId(Long.valueOf(44));
		summary.setLocalDate(new Date(0));
		
		Set<HormoneTherapy> summaryActivityCollection = new HashSet<HormoneTherapy>();
		for (int i = 0; i <2; i++) {
			HormoneTherapy hormoneTherapy = new HormoneTherapy();
			hormoneTherapy.setId(Long.valueOf(i));
			hormoneTherapy.setCharacterization("characterization " + i);
			summaryActivityCollection.add(hormoneTherapy);
		}
		summary.setSummaryActivityCollection(summaryActivityCollection);
		Set<HormoneTherapy> localActivityCollection = new HashSet<HormoneTherapy>();
		
		for (int i = 3; i <5; i++) {
			HormoneTherapy hormoneTherapy = new HormoneTherapy();
			hormoneTherapy.setId(Long.valueOf(i));
			hormoneTherapy.setCharacterization("characterization " + i);
			localActivityCollection.add(hormoneTherapy);
		}
		
		summary.setLocalActivityCollection(localActivityCollection);
		
		try{
			HibernateUtil.create(summary);
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	public Long getNextId() throws Exception {
		Long maxId = Long.valueOf(0);
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();

		List result = new ArrayList();
		result = session.createQuery(
				"from ActivitySummary where id = (select max(id) from ActivitySummary)").list();

		tx.commit();
		HibernateUtil.closeSession();
		if (result.size() != 0) {
			ActivitySummary obj = (ActivitySummary) result.get(0);
			maxId = obj.getId();
		}
		return new Long((maxId.longValue() + 1));
	}


}
