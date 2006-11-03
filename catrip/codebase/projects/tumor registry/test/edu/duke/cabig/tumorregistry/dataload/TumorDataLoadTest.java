package edu.duke.cabig.tumorregistry.dataload;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;

import edu.duke.cabig.tumorregistry.domain.DataLoadUtils;
import edu.duke.cabig.tumorregistry.domain.HibernateUtil;
import edu.duke.cabig.tumorregistry.domain.*;

import junit.framework.TestCase;

public class TumorDataLoadTest extends TestCase {
	public final static int  PATIENT_LIMIT = 1000;
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testPatientLoad() throws Exception {
		DataLoadUtils.loadProperties();
		PatientDataLoad patientData = new PatientDataLoad();
		Set p  = patientData.getPatients();
		DiagnosisDataLoad diagnosisData = new DiagnosisDataLoad();
		Set diagnosises = diagnosisData.getDiagnosises();

		try{
			int i = 1;
			for (Iterator iter = p.iterator(); iter.hasNext();) {
				Patient patient = (Patient) iter.next();
				
				Set diagnosisCollection = diagnosisData.getDiagnosisCollection(patient.getId());
				if (diagnosisCollection.size() > 0)
					patient.setDiagnosisCollection(diagnosisCollection);
				Iterator rows = diagnosisCollection.iterator(); 
				while( rows.hasNext() ) {   
					DiagnosisData row = (DiagnosisData) rows.next();
					if (row.getActivityCollection().size() != 0)
						System.out.println(i++);
					else
						break;
				}
				
				HibernateUtil.create(patient);
			}
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
