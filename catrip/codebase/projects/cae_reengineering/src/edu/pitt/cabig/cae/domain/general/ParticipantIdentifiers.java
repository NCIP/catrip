package edu.pitt.cabig.cae.domain.general;
import java.util.Date;
import java.lang.String;
import java.util.Collection;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:34 PM
 */
public class ParticipantIdentifiers extends AnnotationSet {

	private String lastName;
	private String firstName;
	private String middleName;
	private Date birthDate;
	private String uniquePatientIdentifier;
	private Collection medicalRecordNumber;

	public ParticipantIdentifiers(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setUniquePatientIdentifier(String uniquePatientIdentifier) {
        this.uniquePatientIdentifier = uniquePatientIdentifier;
    }

    public String getUniquePatientIdentifier() {
        return uniquePatientIdentifier;
    }

    public void setMedicalRecordNumber(Collection medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public Collection getMedicalRecordNumber() {
        return medicalRecordNumber;
    }
}
