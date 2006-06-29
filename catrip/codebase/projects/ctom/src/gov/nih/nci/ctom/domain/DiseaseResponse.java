package gov.nih.nci.ctom.domain;

import java.sql.Date;

/**
 * @version 1.0
 * @created 19-Jun-2006 11:34:41 AM
 */
public class DiseaseResponse extends Assessment {
	
	/**
	 * Values include: CR-Complete Response, PD-Progressive Disease, etc.
	 */
	private String bestResponseCode;
	private Date bestResponseDate;
	private String commentText;
	/**
	 * Values include: Completed, Discontinued.
	 */
	private String courseDispositionCode;
	/**
	 * Values include: 9-Unknown, 3-No, 1-Yes Planned, 2-Yes Unplanned.
	 */
	private int doseChangeIndicatorCode;
	private Date progressionDate;
	private int progressionPeriod;
	private String progressionPeriodUnitOfMeasureCode;
	/**
	 * Values include: DU-Disease Unchanged, CR-Complete Response, etc.
	 */
	private String responseCode;
	private String responseCodeSystem;

	public DiseaseResponse(){

	}

	public String getBestResponseCode() {
		return bestResponseCode;
	}

	public void setBestResponseCode(String bestResponseCode) {
		this.bestResponseCode = bestResponseCode;
	}

	public Date getBestResponseDate() {
		return bestResponseDate;
	}

	public void setBestResponseDate(Date bestResponseDate) {
		this.bestResponseDate = bestResponseDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCourseDispositionCode() {
		return courseDispositionCode;
	}

	public void setCourseDispositionCode(String courseDispositionCode) {
		this.courseDispositionCode = courseDispositionCode;
	}

	public int getDoseChangeIndicatorCode() {
		return doseChangeIndicatorCode;
	}

	public void setDoseChangeIndicatorCode(int doseChangeIndicatorCode) {
		this.doseChangeIndicatorCode = doseChangeIndicatorCode;
	}

	public Date getProgressionDate() {
		return progressionDate;
	}

	public void setProgressionDate(Date progressionDate) {
		this.progressionDate = progressionDate;
	}

	public int getProgressionPeriod() {
		return progressionPeriod;
	}

	public void setProgressionPeriod(int progressionPeriod) {
		this.progressionPeriod = progressionPeriod;
	}

	public String getProgressionPeriodUnitOfMeasureCode() {
		return progressionPeriodUnitOfMeasureCode;
	}

	public void setProgressionPeriodUnitOfMeasureCode(
			String progressionPeriodUnitOfMeasureCode) {
		this.progressionPeriodUnitOfMeasureCode = progressionPeriodUnitOfMeasureCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseCodeSystem() {
		return responseCodeSystem;
	}

	public void setResponseCodeSystem(String responseCodeSystem) {
		this.responseCodeSystem = responseCodeSystem;
	}

	

}