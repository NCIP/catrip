package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.Set;

/**
 * @version 1.0
 * @created 27-Jun-2006 9:34:20 AM
 */
public class StudyParticipantAssignment {

	/**
	 * [Additional Documentation] NOTE:  When the epochName is "Prior" or "Baseline" --
	 * the arm value will be defaulted to NULL. [End Documentation]
	 */
	private String armIdentifier;
	private String eligibilityWaiverReasonText;
	private int enrollmentAge;
	private int id;
	private Date informedConsentFormSignedDate;
	private Date offStudyDate;
	/**
	 * Added for CTMS requirement
	 */
	private String offStudyReasonCode;
	private String offStudyReasonOtherText;
	private int studyAgentDoseLevel;
	private String studyAgentDoseLevelUnitOfMeasureCode;
	private int studyParticipantIdentifier;
	private String subgroupCode;
	private Set activityCollection;

	public StudyParticipantAssignment(){

	}

	public Set getActivityCollection() {
		return activityCollection;
	}

	public void setActivityCollection(Set activityCollection) {
		this.activityCollection = activityCollection;
	}

	public String getArmIdentifier() {
		return armIdentifier;
	}

	public void setArmIdentifier(String armIdentifier) {
		this.armIdentifier = armIdentifier;
	}

	public String getEligibilityWaiverReasonText() {
		return eligibilityWaiverReasonText;
	}

	public void setEligibilityWaiverReasonText(String eligibilityWaiverReasonText) {
		this.eligibilityWaiverReasonText = eligibilityWaiverReasonText;
	}

	public int getEnrollmentAge() {
		return enrollmentAge;
	}

	public void setEnrollmentAge(int enrollmentAge) {
		this.enrollmentAge = enrollmentAge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInformedConsentFormSignedDate() {
		return informedConsentFormSignedDate;
	}

	public void setInformedConsentFormSignedDate(Date informedConsentFormSignedDate) {
		this.informedConsentFormSignedDate = informedConsentFormSignedDate;
	}

	public Date getOffStudyDate() {
		return offStudyDate;
	}

	public void setOffStudyDate(Date offStudyDate) {
		this.offStudyDate = offStudyDate;
	}

	public String getOffStudyReasonCode() {
		return offStudyReasonCode;
	}

	public void setOffStudyReasonCode(String offStudyReasonCode) {
		this.offStudyReasonCode = offStudyReasonCode;
	}

	public String getOffStudyReasonOtherText() {
		return offStudyReasonOtherText;
	}

	public void setOffStudyReasonOtherText(String offStudyReasonOtherText) {
		this.offStudyReasonOtherText = offStudyReasonOtherText;
	}

	public int getStudyAgentDoseLevel() {
		return studyAgentDoseLevel;
	}

	public void setStudyAgentDoseLevel(int studyAgentDoseLevel) {
		this.studyAgentDoseLevel = studyAgentDoseLevel;
	}

	public String getStudyAgentDoseLevelUnitOfMeasureCode() {
		return studyAgentDoseLevelUnitOfMeasureCode;
	}

	public void setStudyAgentDoseLevelUnitOfMeasureCode(
			String studyAgentDoseLevelUnitOfMeasureCode) {
		this.studyAgentDoseLevelUnitOfMeasureCode = studyAgentDoseLevelUnitOfMeasureCode;
	}

	public int getStudyParticipantIdentifier() {
		return studyParticipantIdentifier;
	}

	public void setStudyParticipantIdentifier(int studyParticipantIdentifier) {
		this.studyParticipantIdentifier = studyParticipantIdentifier;
	}

	public String getSubgroupCode() {
		return subgroupCode;
	}

	public void setSubgroupCode(String subgroupCode) {
		this.subgroupCode = subgroupCode;
	}

}