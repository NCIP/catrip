import org.apache.axis.types.URI.MalformedURIException;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;

import edu.wustl.catissuecore.domainobject.*;
import edu.wustl.catissuecore.domainobject.impl.*;

import gov.nih.nci.cagrid.cqlquery.Association;
import gov.nih.nci.cagrid.cqlquery.Attribute;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlquery.Object;
import gov.nih.nci.cagrid.cqlquery.Predicate;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import junit.framework.TestCase;

public class TestCATissueCore extends TestCase {

	static CaTissueCore_FullClient client = null;
	static CQLQuery cqlQuery = null;
static int i =0;
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		try { 
			client = new
			CaTissueCore_FullClient("http://localhost:8080/wsrf/services/cagrid/CaTissueCore_Full"); 
			cqlQuery = new CQLQuery();
		}
		catch (MalformedURIException e) { 
			e.printStackTrace(); } 
		catch (RemoteException e) { 
				
				
				 e.printStackTrace(); 
			}
		 

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static void testSpecimenToPart() {
		Object target = new Object();
		target.setName(SpecimenImpl.class.getName());
		//target.setAttribute(new Attribute("type", Predicate.EQUAL_TO, "10"));

		Association ass1 = new Association();
		ass1.setRoleName("specimenCollectionGroup");
		ass1.setName(SpecimenCollectionGroupImpl.class.getName());
		//ass1.setAttribute(new Attribute("clinicalStatus", Predicate.EQUAL_TO,
		//		"test2"));
		Association ass2 = new Association();
		ass2.setRoleName("clinicalReport");
		ass2.setName(ClinicalReportImpl.class.getName());
		Association ass3 = new Association();
		ass3.setRoleName("participantMedicalIdentifier");
		ass3.setName(ParticipantMedicalIdentifierImpl.class.getName());
		ass1.setAssociation(ass2);
		ass2.setAssociation(ass3);
		target.setAssociation(ass1);
		
		try {
			
			CQLQueryResultsIterator iter = query(target);
			while (iter.hasNext()) {
				Specimen de = (Specimen) iter.next();
				System.out.println("********  Record " + ++i + "  *************");
				System.out.println("id = " + de.getId());
				System.out.println("type = " + de.getType() );
				System.out.println("available = " + de.getAvailable());
				System.out.println("PositionDimensionOne  = " + de.getPositionDimensionOne() + " " );
				System.out.println("PositionDimensionTwo = " + de.getPositionDimensionTwo() + " " );
				System.out.println("Barcode = " + de.getBarcode());
				System.out.println("comments = " + de.getComments());
				System.out.println("activityStatus = " + de.getActivityStatus());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testFrozenEPToPart() {
		Object target = new Object();
		target.setName(FrozenEventParametersImpl.class.getName());
		target.setAttribute(new Attribute("method", Predicate.EQUAL_TO, "Frozen"));

		Association ass0 = new Association();
		ass0.setRoleName("specimen");
		ass0.setName(SpecimenEventParametersImpl.class.getName());
		
		Association ass1 = new Association();
		ass1.setRoleName("specimenCollectionGroup");
		ass1.setName(SpecimenCollectionGroupImpl.class.getName());
		ass1.setAttribute(new Attribute("clinicalStatus", Predicate.EQUAL_TO,
				"test2"));
		Association ass2 = new Association();
		ass2.setRoleName("clinicalReport");
		ass2.setName(ClinicalReportImpl.class.getName());
		Association ass3 = new Association();
		ass3.setRoleName("participantMedicalIdentifier");
		ass3.setName(ParticipantMedicalIdentifierImpl.class.getName());
		
		
		

		
		ass0.setAssociation(ass1);
ass1.setAssociation(ass2);
ass2.setAssociation(ass3);
		target.setAssociation(ass0);
		try {
			
			CQLQueryResultsIterator iter = query(target);
			while (iter.hasNext()) {
				FrozenEventParameters de = (FrozenEventParameters) iter.next();
				System.out.println("id = " + de.getId());
/*				System.out.println("type = " + de.getType() );
				System.out.println("available = " + de.getAvailable());
				System.out.println("PositionDimensionOne  = " + de.getPositionDimensionOne() + " " );
				System.out.println("PositionDimensionTwo = " + de.getPositionDimensionTwo() + " " );
				System.out.println("Barcode = " + de.getBarcode());
				System.out.println("comments = " + de.getComments());
				System.out.println("activityStatus = " + de.getActivityStatus());
				
*/			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static CQLQueryResultsIterator query(Object target) throws Exception {
		cqlQuery.setTarget(target);
		CQLQueryResults results = client.query(cqlQuery);
		System.out.println("number of records : " + results.getObjectResult().length);
		//System.out.println(target.getClass().getName());
		CQLQueryResultsIterator iter = new CQLQueryResultsIterator(results, new FileInputStream(new File("client-config.wsdd")));
		return iter;

	}
}
