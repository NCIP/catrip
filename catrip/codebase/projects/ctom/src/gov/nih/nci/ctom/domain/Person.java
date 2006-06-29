package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.Set;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:08:40 PM
 */
public abstract class Person {

	/**
	 * Values include: Female, Male, Unknown.
	 */
	private String administrativeGenderCode;
	private Date birthDate;
	private String countryCode;
	/**
	 * Values include: Less than High School Diploma, High School Diploma, Some
	 * College, etc.
	 */
	private String educationLevelCode;
	/**
	 * Values include: Disabled, Employed, Homemaker, Retired, etc.
	 */
	private String employmentStatusCode;
	private String employmentStatusOtherText;
	/**
	 * Values include: Hispanic Or Latino, Unknown, Not reported, Not Hispanic Or
	 * Latino.
	 */
	private String ethnicGroupCode;
	private String firstName;
	/**
	 * Values include: Less than $25,000, $25,000 to $50,000, etc.
	 */
	private String householdIncomeCode;
	private int id;
	private String lastName;
	/**
	 * Values include: Married, Widowed, Single, Separated, etc.
	 */
	private String maritalStatusCode;
	/**
	 * Values include: Not Reported, Unknown, Asian, White, etc.
	 */
	private String raceCode;
	private String telecomAddress;
	private Set personOccupationCollection;

	public Person(){

	}

	public String getAdministrativeGenderCode() {
		return administrativeGenderCode;
	}

	public void setAdministrativeGenderCode(String administrativeGenderCode) {
		this.administrativeGenderCode = administrativeGenderCode;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEducationLevelCode() {
		return educationLevelCode;
	}

	public void setEducationLevelCode(String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}

	public String getEmploymentStatusCode() {
		return employmentStatusCode;
	}

	public void setEmploymentStatusCode(String employmentStatusCode) {
		this.employmentStatusCode = employmentStatusCode;
	}

	public String getEmploymentStatusOtherText() {
		return employmentStatusOtherText;
	}

	public void setEmploymentStatusOtherText(String employmentStatusOtherText) {
		this.employmentStatusOtherText = employmentStatusOtherText;
	}

	public String getEthnicGroupCode() {
		return ethnicGroupCode;
	}

	public void setEthnicGroupCode(String ethnicGroupCode) {
		this.ethnicGroupCode = ethnicGroupCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHouseholdIncomeCode() {
		return householdIncomeCode;
	}

	public void setHouseholdIncomeCode(String householdIncomeCode) {
		this.householdIncomeCode = householdIncomeCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaritalStatusCode() {
		return maritalStatusCode;
	}

	public void setMaritalStatusCode(String maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}

	public Set getPersonOccupationCollection() {
		return personOccupationCollection;
	}

	public void setPersonOccupationCollection(Set personOccupationCollection) {
		this.personOccupationCollection = personOccupationCollection;
	}

	public String getRaceCode() {
		return raceCode;
	}

	public void setRaceCode(String raceCode) {
		this.raceCode = raceCode;
	}

	public String getTelecomAddress() {
		return telecomAddress;
	}

	public void setTelecomAddress(String telecomAddress) {
		this.telecomAddress = telecomAddress;
	}

}