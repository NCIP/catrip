/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.ctom.domain;

//import gov.nih.nci.ctom.domain.domain.StudyTimePoint;

import java.sql.Date;
import java.util.Set;

/**
 * 
 * 
 * @version 1.0
 * @created 19-Jun-2006 11:34:40 AM
 */
public abstract class Activity {

	public String descriptionText;
	/**
	 * Values include: MN-Minutes, HR-Hours, DY-Days, etc.
	 */
	private String durationUnitOfMeasureCode; 
	private int durationValue;
	private int id;
	/**
	 * Values include: Bone Scan, Colonoscopy, CAT Scan, etc.
	 */
	private String name;
	private boolean plannedIndicator;
	/**
	 * Values include: Diagnostic, Research, Treatment.
	 * 
	 */
	private String reasonCode;
	private Date startDate;
	private Date stopDate;
	private String type;
	private Set studyTimePoint;
	private Set activityRelationshipCollection;
	private Set observationCollection;

	public Activity(){

	}

	public void finalize() throws Throwable {

	}

	public Set getActivityRelationshipCollection() {
		return activityRelationshipCollection;
	}

	public void setActivityRelationshipCollection(
			Set activityRelationshipCollection) {
		this.activityRelationshipCollection = activityRelationshipCollection;
	}
	/**
	 * @hibernate.property
	 * column="DESCRIPTION_TEXT"
	 * length="200"
	 * type="string"
	 **/
	public String getDescriptionText() {
		return descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	public String getDurationUnitOfMeasureCode() {
		return durationUnitOfMeasureCode;
	}

	public void setDurationUnitOfMeasureCode(String durationUnitOfMeasureCode) {
		this.durationUnitOfMeasureCode = durationUnitOfMeasureCode;
	}

	public int getDurationValue() {
		return durationValue;
	}

	public void setDurationValue(int durationValue) {
		this.durationValue = durationValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getObservationCollection() {
		return observationCollection;
	}

	public void setObservationCollection(Set observationCollection) {
		this.observationCollection = observationCollection;
	}

	public boolean isPlannedIndicator() {
		return plannedIndicator;
	}

	public void setPlannedIndicator(boolean plannedIndicator) {
		this.plannedIndicator = plannedIndicator;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	//public StudyTimePoint getStudyTimePoint() {
	//	return studyTimePoint;
	//}

	//public void setStudyTimePoint(StudyTimePoint studyTimePoint) {
	//	this.studyTimePoint = studyTimePoint;
	//}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set getStudyTimePoint() {
		return studyTimePoint;
	}

	public void setStudyTimePoint(Set studyTimePoint) {
		this.studyTimePoint = studyTimePoint;
	}

}