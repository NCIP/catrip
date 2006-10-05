package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.Integer;
import java.lang.Boolean;
import java.util.Date;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:17 PM
 */
public abstract class Activity {

	private Long id;
	private Integer course;
	private Boolean atLocalFacility;
	private Date startDate;
	private String characterization;
	private Integer performedIndicator;
	private Diagnosis diagnosis;
	private ActivitySummary localActivity;
	private ActivitySummary summaryActivity;
	
	public Integer getPerformedIndicator() {
		return performedIndicator;
	}

	public void setPerformedIndicator(Integer performedIndicator) {
		this.performedIndicator = performedIndicator;
	}

	public Activity(){

	}

	public void finalize() throws Throwable {

	}

	public Boolean getAtLocalFacility() {
		return atLocalFacility;
	}

	public void setAtLocalFacility(Boolean atLocalFacility) {
		this.atLocalFacility = atLocalFacility;
	}

	public String getCharacterization() {
		return characterization;
	}

	public void setCharacterization(String characterization) {
		this.characterization = characterization;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public ActivitySummary getLocalActivity() {
		return localActivity;
	}

	public void setLocalActivity(ActivitySummary localActivity) {
		this.localActivity = localActivity;
	}

	public ActivitySummary getSummaryActivity() {
		return summaryActivity;
	}

	public void setSummaryActivity(ActivitySummary summaryActivity) {
		this.summaryActivity = summaryActivity;
	}

}