package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;

/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:22 PM
 */
public class PatientIdentifier {

	private Long id;
	private String medicalRecordNumber;
	private Patient patient;

	public PatientIdentifier(){

	}

	public void finalize() throws Throwable {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(String medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}