package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:23 PM
 */
public class Patient {

	private Long id;
	/**
	 * maps to : pt_first_name
	 */
	private String firstName;
	private String middleName;
	/**
	 * maps to : pt_last_nm
	 */
	private String lastName;
	private String suffix;
	/**
	 * maps to : pt_birth_dt
	 */
	private Date dateOfBirth;
	private Date dateOfDeath;
	/**
	 * maps to :pt_race
	 */
	private String race;
	/**
	 * maps to : ps_sex
	 */
	private String sex;
	/**
	 * maps to : pt_eth
	 */
	private String ethnicGroup;
	private String autopsy;
	private Address address;
	private PatientIdentifier patientIdentifier;
	private java.util.Set diagnosisCollection;

	public Patient(){

	}

	public void finalize() throws Throwable {

	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public java.util.Set getDiagnosisCollection() {
		return diagnosisCollection;
	}

	public void setDiagnosisCollection(java.util.Set diagnosisCollection) {
		this.diagnosisCollection = diagnosisCollection;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address patientAddress) {
		this.address = patientAddress;
	}

	public PatientIdentifier getPatientIdentifier() {
		return patientIdentifier;
	}

	public void setPatientIdentifier(PatientIdentifier patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getAutopsy() {
		return autopsy;
	}

	public void setAutopsy(String autopsy) {
		this.autopsy = autopsy;
	}

}