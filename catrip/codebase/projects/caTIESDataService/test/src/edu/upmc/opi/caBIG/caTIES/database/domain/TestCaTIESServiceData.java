package edu.upmc.opi.caBIG.caTIES.database.domain;


import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.types.URI.MalformedURIException;
import org.hibernate.Session;
import junit.framework.TestCase;
import edu.upmc.opi.caBIG.caTIES.database.domain.client.CaTIESClient;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.data.utilities.CQLQueryResultsIterator;


public class TestCaTIESServiceData extends TestCase { 
	private CaTIESClient client;
	private CQLQuery cqlQuery;
	private gov.nih.nci.cagrid.cqlquery.Object target;
	private String wsdd = "src/edu/upmc/opi/caBIG/caTIES/database/domain/client/client-config.wsdd";
	private int count = 1;
	public TestCaTIESServiceData(String sTestName) {
		super(sTestName);
	}

	public void setUp() {
		count = 1;
		try {
			client = new CaTIESClient("http://localhost:8181/wsrf/services/cagrid/CaTIES");
			cqlQuery = new CQLQuery();
			target = new gov.nih.nci.cagrid.cqlquery.Object();
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void tearDown() {
	}

	public void testPatientSelect() throws Exception{
		CQLQueryResultsIterator iter = query("edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPatient");
		while (iter.hasNext()) {
			edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPatient de = (edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPatient) iter.next();
			if (de == null)
				System.out.println("object is null");
			else{
				System.out.println("*******  Identified Patient " + (count++) +"  *******");
				System.out.println(" id : " + de.getId());
				System.out.println(" version : " + de.getVersion());
				System.out.println(" uuid : " + de.getUuid());
				System.out.println(" deidentifiedid : " + de.getDeidentifiedId());
				System.out.println(" mrn : " + de.getMedicalRecordNumber());
				System.out.println(" first : " + de.getFirstName());
				System.out.println(" last : " + de.getLastName());
				System.out.println(" middle : " + de.getMiddleName());
				System.out.println(" birthdate : " + de.getBirthDate());
				System.out.println(" ssn : " + de.getSocialSecurityNumber());
				System.out.println(" gender : " + de.getGender());
				System.out.println(" race : " + de.getRace());
				System.out.println(" ethinicity : " + de.getEthnicity());
				System.out.println(" marital status : " + de.getMaritalStatus());
			}
		}
	}



	public void testIdentifiedPathologyReportSelect() throws Exception{
		CQLQueryResultsIterator iter = query("edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReport");
		while (iter.hasNext()) {
			edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReport de = (edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReport) iter.next();
			if (de == null)
				System.out.println("object is null");
			else{
				System.out.println("*******  IdentifiedPathologyReport " + (count++) +"  *******");
				System.out.println(" id : " + de.getId());
				System.out.println(" version : " + de.getVersion());
				System.out.println(" uuid : " + de.getUuid());
				System.out.println(" de-identified id : " + de.getDeidentifiedId());
				System.out.println(" accession Number : " + de.getAccessionNumber());
				System.out.println(" collection Date time : " + de.getCollectionDateTime());
				System.out.println(" ordering Physician Id : " + de.getOrderingPhysicianId());
				System.out.println(" honest broker comment : " + de.getHonestBrokerComment());
				System.out.println(" document Text : " + de.getDocumentText());
			}
		}
	}
	private CQLQueryResultsIterator query(String targetObject) throws Exception{
		try{
			target.setName(targetObject);
			cqlQuery.setTarget(target);
			CQLQueryResults results = client.query(cqlQuery);
			return new CQLQueryResultsIterator(results, new FileInputStream(new File(wsdd)));
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}
	public void tttestSelect() throws Exception {

		Session session = HibernateUtil.currentSession();

		List result = new ArrayList();
		result = session.createQuery("FROM IdentifiedPathologyReport").list();
		for (int i = 0; i < result.size(); i++) {
			edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReport obj = (edu.upmc.opi.caBIG.caTIES.database.domain.IdentifiedPathologyReport) result.get(i);
			System.out.println(" email address : " + (obj.getDocumentText()));
			//System.out.println(" patientCollection : " + (obj.getPatientCollection().size()));
		}

		System.out.println("size = " + result.size());
		HibernateUtil.closeSession();

	}

} // end of class

