package edu.duke.cabig.tumorregistry.dataload;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import edu.duke.cabig.tumorregistry.domain.*;

import junit.framework.TestCase;

public class TumorDataLoadTest extends TestCase {
	public final static int  PATIENT_LIMIT = 10;
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
		boolean isFound = false;
		List existingPatients = HibernateUtil.selectPatientIds();;

		try{
			//System.out.println(p.size());
			//existingPatients = 
			int i = 1;
			for (Iterator iter = p.iterator(); iter.hasNext();) {
				Patient patient = (Patient) iter.next();
				isFound = existingPatients.contains(patient.getId());
				int len = patient.getPatientIdentifier().getMedicalRecordNumber().length();
				if (!isFound ){
					Set diagnosisCollection = diagnosisData.getDiagnosisCollection(patient.getId());
					if (diagnosisCollection.size() > 0)
						patient.setDiagnosisCollection(diagnosisCollection);
//					Iterator rows = diagnosisCollection.iterator(); 
//					while( rows.hasNext() ) {   
//					DiagnosisData row = (DiagnosisData) rows.next();
//					if (row.getActivityCollection().size() != 0)
//					System.out.println(i++);
//					else
//					break;
//					}
					HibernateUtil.create(patient);
					System.out.println(i++);
				}
			}
		} 
		catch (HibernateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
