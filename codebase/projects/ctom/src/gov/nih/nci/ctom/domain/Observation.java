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
 * @created 20-Jun-2006 8:11:33 AM
 */
public abstract class  Observation {

	private String confidentialityCode;
	private long id;
	/**
	 * [Additional Documentation] The Date the observation was reported. [End
	 * Documentation]
	 */
	private Date reportingDate;
	private String statusCode;
	/**
	 * [Additional Documentation] For example, a patient might have had a
	 * cholecystectomy procedure in the past (but isn't sure). [End Documentation]
	 */
	private String uncertaintyCode;
	private Set assessmentCollection;
	private Set observationRelationshipCollection;

	public Observation(){

	}

	public Set getAssessmentCollection() {
		return assessmentCollection;
	}

	public void setAssessmentCollection(Set assessmentCollection) {
		this.assessmentCollection = assessmentCollection;
	}

	public String getConfidentialityCode() {
		return confidentialityCode;
	}

	public void setConfidentialityCode(String confidentialityCode) {
		this.confidentialityCode = confidentialityCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set getObservationRelationshipCollection() {
		return observationRelationshipCollection;
	}

	public void setObservationRelationshipCollection(
			Set observationRelationshipCollection) {
		this.observationRelationshipCollection = observationRelationshipCollection;
	}

	public Date getReportingDate() {
		return reportingDate;
	}

	public void setReportingDate(Date reportingDate) {
		this.reportingDate = reportingDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getUncertaintyCode() {
		return uncertaintyCode;
	}

	public void setUncertaintyCode(String uncertaintyCode) {
		this.uncertaintyCode = uncertaintyCode;
	}

}