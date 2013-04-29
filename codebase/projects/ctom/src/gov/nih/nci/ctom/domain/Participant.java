/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.util.Set;

/**
 * @version 1.0
 * @created 26-Jun-2006 8:01:15 PM
 */
public class Participant extends Person {

	public boolean confidentialityIndicator;
	/**
	 * [Additional Documentation] NOTE: This should be considered as identifying
	 * information and should not be part of research database --(Still TBD). [End
	 * Documentation]
	 */
	public String initials;
	/**
	 * Values include: 1-Private Insurance, 2-Medicare, 3- Medicare And Private
	 * Insurance, 4-Medicaid, etc. 
	 */
	public String paymentMethodCode;
	public String zipCode;
	public Set femaleReproductiveCharacteristicCollection;
	public Set studyParticipantAssignmentCollection;
	public Set healthcareSiteParticipantCollection;
	public Set participantEligibilityAnswerCollection;

	public Participant(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public boolean isConfidentialityIndicator() {
		return confidentialityIndicator;
	}

	public void setConfidentialityIndicator(boolean confidentialityIndicator) {
		this.confidentialityIndicator = confidentialityIndicator;
	}

	public Set getFemaleReproductiveCharacteristicCollection() {
		return femaleReproductiveCharacteristicCollection;
	}

	public void setFemaleReproductiveCharacteristicCollection(Set femaleReproductiveCharacteristic) {
		this.femaleReproductiveCharacteristicCollection = femaleReproductiveCharacteristic;
	}

	public Set getHealthcareSiteParticipantCollection() {
		return healthcareSiteParticipantCollection;
	}

	public void setHealthcareSiteParticipantCollection(
			Set healthcareSiteParticipantCollection) {
		this.healthcareSiteParticipantCollection = healthcareSiteParticipantCollection;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Set getParticipantEligibilityAnswerCollection() {
		return participantEligibilityAnswerCollection;
	}

	public void setParticipantEligibilityAnswerCollection(
			Set participantEligibilityAnswerCollection) {
		this.participantEligibilityAnswerCollection = participantEligibilityAnswerCollection;
	}

	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}

	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}

	public Set getStudyParticipantAssignmentCollection() {
		return studyParticipantAssignmentCollection;
	}

	public void setStudyParticipantAssignmentCollection(
			Set studyParticipantAssignmentCollection) {
		this.studyParticipantAssignmentCollection = studyParticipantAssignmentCollection;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}