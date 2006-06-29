package gov.nih.nci.ctom.domain;

import java.sql.Date;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:04:06 PM
 */
public class StudyInvestigator {

	private int id;
	/**
	 * Examples include: Primary Investigator, Co-Investigator, etc.
	 */
	private String responsibilityRoleCode;
	/**
	 * [Additional Documentation] A code specifying whether and how the participant
	 * has attested his participation through a signature and or whether such a
	 * signature is needed. [BRIDG] [End Documentation]
	 */
	private boolean signatureIndicator;
	/**
	 * [Additional Documentation] A textual or multimedia depiction of the signature
	 * by which the participant endorses his or her participation in the Act as
	 * specified in the Participation.typeCode and that he or she agrees to assume the
	 * associated accountability. [BRIDG] [End Documentation]
	 */
	private String signatureText;
	private Date startDate;
	private String statusCode;
	private Date stopDate;
	//private Protocol protocol;
	//private Investigator investigator;

	public StudyInvestigator(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResponsibilityRoleCode() {
		return responsibilityRoleCode;
	}

	public void setResponsibilityRoleCode(String responsibilityRoleCode) {
		this.responsibilityRoleCode = responsibilityRoleCode;
	}

	public boolean isSignatureIndicator() {
		return signatureIndicator;
	}

	public void setSignatureIndicator(boolean signatureIndicator) {
		this.signatureIndicator = signatureIndicator;
	}

	public String getSignatureText() {
		return signatureText;
	}

	public void setSignatureText(String signatureText) {
		this.signatureText = signatureText;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

}