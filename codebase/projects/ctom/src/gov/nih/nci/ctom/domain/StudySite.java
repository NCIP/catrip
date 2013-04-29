/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

import java.sql.Date;
import java.util.Set;

/**
 * @version 1.0
 * @created 15-Jun-2006 2:11:18 PM
 */
public class StudySite {

	private int id;
	private Date irbApprovalDate;
	/**
	 * Examples include: Lead organization, participating organization, etc.
	 */
	private String roleCode;
	private Date startDate;
	private String statusCode;
	private Date stopDate;
	private String targetAccrualNumber;
	//private Protocol protocol;
	private Set studyParticipantCollection;

	public StudySite(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getIrbApprovalDate() {
		return irbApprovalDate;
	}

	public void setIrbApprovalDate(Date irbApprovalDate) {
		this.irbApprovalDate = irbApprovalDate;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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

	public Set getStudyParticipantCollection() {
		return studyParticipantCollection;
	}

	public void setStudyParticipantCollection(Set studyParticipantCollection) {
		this.studyParticipantCollection = studyParticipantCollection;
	}

	public String getTargetAccrualNumber() {
		return targetAccrualNumber;
	}

	public void setTargetAccrualNumber(String targetAccrualNumber) {
		this.targetAccrualNumber = targetAccrualNumber;
	}

}